package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.ItemWrench;
import com.astryxion.chaospersists.render.RenderSpiderRobotInfo;
import com.astryxion.chaospersists.util.ChaosMountHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AntRobot extends Mob {
    private static final EntityDataAccessor<Integer> ATTACKING =
            SynchedEntityData.defineId(AntRobot.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Byte> OWNED =
            SynchedEntityData.defineId(AntRobot.class, EntityDataSerializers.BYTE);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private int playing = 0;
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.3f;
    private RenderSpiderRobotInfo renderdata = new RenderSpiderRobotInfo();
    private int didonce = 0;
    private int rideTicker = 0;
    private int owned = 0;
    private int dismountCooldown = 0;
    private UUID lastRiderId = null;

    public AntRobot(EntityType<? extends AntRobot> type, Level level) {
        super(type, level);
        this.TargetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 12.0f));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.xpReward = ChaosPersists.AntRobot_stats.health / 2;
        this.setPersistenceRequired();
    }

    public AntRobot(Level level, double x, double y, double z) {
        this(ChaosPersists.ENTITY_TYPE_ANT_ROBOT.get(), level);
        this.moveTo(x, y + this.getMyRidingOffset(), z, 0.0f, 0.0f);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.AntRobot_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.AntRobot_stats.attack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, 0);
        this.entityData.define(OWNED, (byte) 0);
        this.initLegData();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public void setOwned() {
        this.owned = 1;
        this.entityData.set(OWNED, (byte) 1);
    }

    public int getOwned() {
        return this.entityData.get(OWNED) & 255;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.AntRobot_stats.defense;
    }

    @Override
    protected void updateControlFlags() {
        if (this.getControllingPassenger() != null) {
            this.goalSelector.setControlFlag(Goal.Flag.MOVE, false);
            this.goalSelector.setControlFlag(Goal.Flag.JUMP, false);
            this.goalSelector.setControlFlag(Goal.Flag.LOOK, false);
            return;
        }
        super.updateControlFlags();
    }

    @Override
    public void aiStep() {
        LivingEntity e;
        if (this.isRemoved()) {
            return;
        }
        if (this.getControllingPassenger() != null) {
            this.goalSelector.setControlFlag(Goal.Flag.MOVE, false);
            this.goalSelector.setControlFlag(Goal.Flag.JUMP, false);
            this.goalSelector.setControlFlag(Goal.Flag.LOOK, false);
        }
        super.aiStep();
        if (this.getControllingPassenger() != null) {
            return;
        }
        if (this.getOwned() == 0 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            if (this.level().getRandom().nextInt(20) == 0) {
                this.feetFindSomethingToHit();
            }
            if (this.level().getRandom().nextInt(150) == 0) {
                this.setTarget(null);
            }
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack(2.0f, false);
            }
            if (e != null) {
                MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                if (this.distanceToSqr(e) > 16.0) {
                    double d1 = e.getZ() - this.getZ();
                    double d2 = e.getX() - this.getX();
                    double dd = Math.atan2(d1, d2);
                    this.goThisWay(0.2 * Math.cos(dd), 0.2 * Math.sin(dd));
                }
            } else {
                this.setAttacking(0);
            }
            if (e != null && this.level().getRandom().nextInt(15) == 0) {
                e = this.getTarget();
                if (e == null) {
                    e = this.findSomethingToAttack(2.0f, true);
                }
                if (e != null) {
                    if (this.distanceToSqr(e) < (double) ((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f))) {
                        this.setAttacking(1);
                        this.attackEntityAsMob(e);
                    } else {
                        this.setAttacking(0);
                    }
                } else {
                    this.setAttacking(0);
                }
            }
        }
    }

    @Override
    protected float tickHeadTurn(float yRot, float animStep) {
        if (this.getControllingPassenger() != null) {
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.getYRot();
            return animStep;
        }
        return super.tickHeadTurn(yRot, animStep);
    }

    @Override
    public boolean isControlledByLocalInstance() {
        Entity rider = this.getControllingPassenger();
        return rider instanceof Player player && player.isLocalPlayer();
    }

    private void initLegData() {
        if (this.renderdata == null) {
            this.renderdata = new RenderSpiderRobotInfo();
        }
        for (int i = 0; i < 6; ++i) {
            this.renderdata.ycurrentangle[i] = 0.0f;
            this.renderdata.ywantedangle[i] = 0.0f;
            this.renderdata.ydisplayangle[i] = 0.0f;
            this.renderdata.yvelocity[i] = 0.0f;
            this.renderdata.ymid[i] = 0.0f;
            this.renderdata.yoff[i] = 0.0f;
            this.renderdata.yrange[i] = 0.0f;
            this.renderdata.udcurrentangle[i] = 0.0f;
            this.renderdata.udwantedangle[i] = 0.0f;
            this.renderdata.uddisplayangle[i] = 0.0f;
            this.renderdata.udvelocity[i] = 0.0f;
            this.renderdata.p1xangle[i] = 0.7853981633974483;
            this.renderdata.p2xangle[i] = 0.0;
            this.renderdata.p3xangle[i] = -0.7853981633974483;
            this.renderdata.pxvelocity[i] = 0.0f;
            this.renderdata.foot_xpos[i] = (float) this.getX();
            this.renderdata.foot_ypos[i] = (float) this.getY();
            this.renderdata.foot_zpos[i] = (float) this.getZ();
            this.renderdata.realposx[i] = 0.0f;
            this.renderdata.realposy[i] = 0.0f;
            this.renderdata.realposz[i] = 0.0f;
            this.renderdata.legoff[i] = 0.0f;
            this.renderdata.footup[i] = 1;
            this.renderdata.uppoint[i] = 0.0f;
            this.renderdata.footingticker[i] = 0;
            this.renderdata.gpcounter = 0;
            if (i == 0) {
                this.renderdata.legoff[i] = 0.75f;
                this.renderdata.ymid[i] = 0.0f;
                this.renderdata.yrange[i] = 0.2617994f;
                this.renderdata.pairedwith[i] = 1;
                this.renderdata.yoff[i] = -0.75f;
            }
            if (i == 1) {
                this.renderdata.legoff[i] = 0.75f;
                this.renderdata.ymid[i] = 3.1415927f;
                this.renderdata.yrange[i] = -0.2617994f;
                this.renderdata.pairedwith[i] = 0;
                this.renderdata.yoff[i] = -0.75f;
            }
            if (i == 2) {
                this.renderdata.legoff[i] = 1.0f;
                this.renderdata.ymid[i] = -0.7853982f;
                this.renderdata.yrange[i] = 0.2617994f;
                this.renderdata.pairedwith[i] = 3;
                this.renderdata.yoff[i] = -0.75f;
            }
            if (i == 3) {
                this.renderdata.legoff[i] = 1.0f;
                this.renderdata.ymid[i] = 3.9269907f;
                this.renderdata.yrange[i] = -0.2617994f;
                this.renderdata.pairedwith[i] = 2;
                this.renderdata.yoff[i] = -0.75f;
            }
            if (i == 4) {
                this.renderdata.legoff[i] = 1.15f;
                this.renderdata.ymid[i] = 0.7853982f;
                this.renderdata.yrange[i] = 0.2617994f;
                this.renderdata.pairedwith[i] = 5;
                this.renderdata.yoff[i] = -0.75f;
            }
            if (i != 5) continue;
            this.renderdata.legoff[i] = 1.15f;
            this.renderdata.ymid[i] = 2.3561945f;
            this.renderdata.yrange[i] = -0.2617994f;
            this.renderdata.pairedwith[i] = 4;
            this.renderdata.yoff[i] = -0.75f;
        }
    }

    private float getNewVelocity(float v, float diff, float curval) {
        float tv = v;
        if ((tv *= 18.0f) < 2.0f) {
            tv = 2.0f;
        }
        if (tv > 8.0f) {
            tv = 8.0f;
        }
        if (diff > 0.0f) {
            if ((double) diff < 0.008726646259971648 * (double) tv) {
                curval = 0.0f;
            } else {
                curval = (float) ((double) curval + 0.004363323129985824 * (double) tv);
                if ((double) diff < 0.06981317007977318 * (double) tv) {
                    curval = (float) (0.017453292519943295 * (double) tv);
                }
                if ((double) diff < 0.03490658503988659 * (double) tv) {
                    curval = (float) (0.008726646259971648 * (double) tv);
                }
                if ((double) curval > 0.06981317007977318 * (double) tv) {
                    curval = (float) (0.06981317007977318 * (double) tv);
                }
            }
        } else if ((double) diff > -0.008726646259971648 * (double) tv) {
            curval = 0.0f;
        } else {
            curval = (float) ((double) curval - 0.004363323129985824 * (double) tv);
            if ((double) diff > -0.06981317007977318 * (double) tv) {
                curval = -(float) (0.017453292519943295 * (double) tv);
            }
            if ((double) diff > -0.03490658503988659 * (double) tv) {
                curval = -(float) (0.008726646259971648 * (double) tv);
            }
            if ((double) curval < -0.06981317007977318 * (double) tv) {
                curval = -(float) (0.06981317007977318 * (double) tv);
            }
        }
        return curval;
    }

    @OnlyIn(Dist.CLIENT)
    public void updateLegs() {
        if (!this.level().isClientSide) {
            return;
        }
        float legYaw = this.getYRot();
        legYaw %= 360.0f;
        while (legYaw < 0.0f) {
            legYaw += 360.0f;
        }
        this.setYRot(legYaw);
        ++this.renderdata.gpcounter;
        if (this.didonce == 0) {
            this.didonce = 1;
            this.initLegData();
        }
        float d1 = (float) (this.xo - this.getX());
        float d2 = (float) (this.yo - this.getY());
        float d3 = (float) (this.zo - this.getZ());
        float realv = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
        int i = 0;
        for (i = 0; i < 6; ++i) {
            double rdv;
            int fcount = 0;
            int[] arrn = this.renderdata.footingticker;
            int n = i;
            arrn[n] = arrn[n] + 1;
            this.renderdata.realposx[i] = (float) (this.getX() - (double) this.renderdata.legoff[i] * Math.sin(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i]));
            this.renderdata.realposz[i] = (float) (this.getZ() + (double) this.renderdata.legoff[i] * Math.cos(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i]));
            this.renderdata.realposy[i] = (float) this.getY() + this.renderdata.yoff[i];
            int it = this.renderdata.footingticker[i] + this.renderdata.footingticker[this.renderdata.pairedwith[i]];
            if (it > 50 && this.renderdata.footingticker[i] > this.renderdata.footingticker[this.renderdata.pairedwith[i]]) {
                this.renderdata.footingticker[i] = 0;
            }
            d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
            d2 = this.renderdata.realposy[i] - this.renderdata.foot_ypos[i];
            d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
            float dd = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            dd *= 16.0f;
            float da = (float) (Math.abs((double) this.renderdata.ycurrentangle[i] - (Math.toRadians(Mth.wrapDegrees((double) this.getYRot())) + (double) this.renderdata.ymid[i])) % 6.283185307179586);
            if ((double) da > 3.141592653589793) {
                da = (float) ((double) da - 6.283185307179586);
            }
            if ((double) da < -3.141592653589793) {
                da = (float) ((double) da + 6.283185307179586);
            }
            da = Math.abs(da);
            if (dd > 144.0f || dd < 22.0f || da > Math.abs(this.renderdata.yrange[i]) * 8.0f / 6.0f || (double) Math.abs(this.renderdata.udcurrentangle[i]) > 1.25 || this.renderdata.footingticker[i] == 0) {
                this.findNewFooting(i);
                d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
                d2 = this.renderdata.realposy[i] - this.renderdata.foot_ypos[i];
                d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
                dd = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                dd *= 16.0f;
            }
            float c1 = (float) (49.0 * Math.cos(this.renderdata.p2xangle[i] - this.renderdata.p1xangle[i]));
            float c2 = 49.0f;
            float c3 = (float) (49.0 * Math.cos(this.renderdata.p2xangle[i] - this.renderdata.p3xangle[i]));
            float cc = c1 + c2 + c3;
            float diff = cc - dd;
            this.renderdata.pxvelocity[i] = this.getNewVelocity(realv, (float) ((double) diff * 3.141592653589793 / 360.0), this.renderdata.pxvelocity[i]);
            if (this.renderdata.pxvelocity[i] == 0.0f || Math.abs(diff) < 8.0f) {
                ++fcount;
            }
            double[] arrd = this.renderdata.p1xangle;
            int n2 = i;
            arrd[n2] = arrd[n2] + (double) this.renderdata.pxvelocity[i];
            this.renderdata.p2xangle[i] = 0.0;
            this.renderdata.p3xangle[i] = -this.renderdata.p1xangle[i];
            dd = this.renderdata.uppoint[i] != 0.0f ? (float) Math.atan2(dd, (double) (this.renderdata.realposy[i] - this.renderdata.uppoint[i]) * 16.0) : (float) Math.atan2(dd, (double) (this.renderdata.realposy[i] - this.renderdata.foot_ypos[i]) * 16.0);
            this.renderdata.udwantedangle[i] = (float) ((double) dd - 1.5707963267948966);
            while ((double) this.renderdata.udwantedangle[i] > 3.141592653589793) {
                float[] arrf = this.renderdata.udwantedangle;
                int n3 = i;
                arrf[n3] = (float) ((double) arrf[n3] - 6.283185307179586);
            }
            while ((double) this.renderdata.udwantedangle[i] < -3.141592653589793) {
                float[] arrf = this.renderdata.udwantedangle;
                int n4 = i;
                arrf[n4] = (float) ((double) arrf[n4] + 6.283185307179586);
            }
            double rhm = this.renderdata.udwantedangle[i];
            double rhdir = this.renderdata.udcurrentangle[i];
            for (rdv = (rhm - rhdir) % 6.283185307179586; rdv > 3.141592653589793; rdv -= 6.283185307179586) {
            }
            while (rdv < -3.141592653589793) {
                rdv += 6.283185307179586;
            }
            diff = (float) rdv;
            this.renderdata.udvelocity[i] = this.getNewVelocity(realv * 2.0f, diff, this.renderdata.udvelocity[i]);
            if (this.renderdata.udvelocity[i] == 0.0f || (double) Math.abs(diff) < 0.03490658503988659) {
                this.renderdata.uppoint[i] = 0.0f;
                ++fcount;
            }
            rhdir += (double) this.renderdata.udvelocity[i];
            while (rhdir > 3.141592653589793) {
                rhdir -= 6.283185307179586;
            }
            while (rhdir < -3.141592653589793) {
                rhdir += 6.283185307179586;
            }
            this.renderdata.uddisplayangle[i] = dd = (this.renderdata.udcurrentangle[i] = (float) rhdir);
            d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
            d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
            dd = (float) Math.atan2(d3, d1);
            this.renderdata.ywantedangle[i] = dd;
            rhm = this.renderdata.ywantedangle[i];
            rdv = (rhm - (rhdir = (double) this.renderdata.ycurrentangle[i])) % 6.283185307179586;
            if (rdv > 3.141592653589793) {
                rdv -= 6.283185307179586;
            }
            if (rdv < -3.141592653589793) {
                rdv += 6.283185307179586;
            }
            diff = (float) rdv;
            this.renderdata.yvelocity[i] = this.getNewVelocity(realv, diff, this.renderdata.yvelocity[i]);
            if (this.renderdata.yvelocity[i] == 0.0f || (double) Math.abs(diff) < 0.03490658503988659) {
                ++fcount;
            }
            float[] arrf = this.renderdata.ycurrentangle;
            int n5 = i;
            arrf[n5] = arrf[n5] + this.renderdata.yvelocity[i];
            while ((double) this.renderdata.ycurrentangle[i] > 3.141592653589793) {
                float[] arrf2 = this.renderdata.ycurrentangle;
                int n6 = i;
                arrf2[n6] = (float) ((double) arrf2[n6] - 6.283185307179586);
            }
            while ((double) this.renderdata.ycurrentangle[i] < -3.141592653589793) {
                float[] arrf3 = this.renderdata.ycurrentangle;
                int n7 = i;
                arrf3[n7] = (float) ((double) arrf3[n7] + 6.283185307179586);
            }
            dd = (float) ((double) this.renderdata.ycurrentangle[i] - Math.toRadians(Mth.wrapDegrees((double) this.getYRot())) - 1.5707963267948966);
            while ((double) dd > 3.141592653589793) {
                dd = (float) ((double) dd - 6.283185307179586);
            }
            while ((double) dd < -3.141592653589793) {
                dd = (float) ((double) dd + 6.283185307179586);
            }
            this.renderdata.ydisplayangle[i] = dd;
            if (fcount != 3) continue;
            this.renderdata.footup[i] = 0;
        }
    }

    private void findNewFooting(int i) {
        float dd;
        float fx;
        float fz;
        float d2;
        float fy;
        float f = 9.0f;
        boolean found = false;
        float range = 0.0f;
        double rhdir = Math.toRadians((this.getYRot() + 90.0f) % 360.0f);
        double pi = 3.1415926545;
        this.renderdata.footingticker[i] = 0;
        float d1 = (float) (this.getX() - this.xo);
        float d3 = (float) (this.getZ() - this.zo);
        double rhm = Math.atan2(d3, d1);
        double velocity = Math.sqrt(d1 * d1 + d3 * d3);
        double rdv = Math.abs(rhm - rhdir) % (pi * 2.0);
        if (rdv > pi) {
            rdv -= pi * 2.0;
        }
        rdv = Math.abs(rdv);
        if (Math.abs(velocity) < 0.01) {
            rdv = 0.0;
        }
        range = this.renderdata.yrange[i];
        range *= 0.8f;
        if (Math.abs((this.yRotO - this.getYRot()) % 360.0f) > 0.75f) {
            range = 0.0f;
        }
        if (i >= 4) {
            f = 4.0f;
        }
        if (rdv > 1.5) {
            range = -range;
            f = 4.0f;
            if (i >= 4) {
                f = 9.0f;
            }
        }
        if (i == 0 || i == 1) {
            f = 6.0f;
        }
        float deffx = fx = (float) ((double) this.renderdata.realposx[i] - (double) (f / 2.0f) * Math.sin(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i]));
        float deffz = fz = (float) ((double) this.renderdata.realposz[i] + (double) (f / 2.0f) * Math.cos(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i]));
        float deffy = fy = this.renderdata.realposy[i] - 1.0f;
        float oldf = f;
        int span = 1;
        while (!found && f > 2.5f) {
            fx = (float) ((double) this.renderdata.realposx[i] - (double) f * Math.sin(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i] - (double) range));
            fz = (float) ((double) this.renderdata.realposz[i] + (double) f * Math.cos(Math.toRadians(Mth.wrapDegrees((double) (this.getYRot() + 90.0f))) + (double) this.renderdata.ymid[i] - (double) range));
            fy = this.renderdata.realposy[i];
            for (int j = 8; !found && j > -9; --j) {
                block2:
                for (int m = -span; !found && m <= span; ++m) {
                    for (int n = -span; !found && n <= span; ++n) {
                        Block blk = this.level().getBlockState(new BlockPos((int) fx + m, (int) fy + j, (int) fz + n)).getBlock();
                        if (blk == Blocks.AIR || !this.level().getBlockState(new BlockPos((int) fx + m, (int) fy + j, (int) fz + n)).isSolid()) continue;
                        d1 = this.renderdata.realposx[i] - (fx + (float) m);
                        d2 = this.renderdata.realposy[i] - (fy + (float) j + 1.0f);
                        d3 = this.renderdata.realposz[i] - (fz + (float) n);
                        dd = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                        if ((dd *= 16.0f) > 144.0f) continue;
                        fy += (float) (j + 1);
                        fx += (float) m;
                        fz += (float) n;
                        found = true;
                        continue block2;
                    }
                }
            }
            if ((f -= 1.0f) >= 2.5f || range == 0.0f) continue;
            range = 0.0f;
            span = 3;
            f = oldf;
        }
        if (!found) {
            fx = deffx;
            fy = deffy;
            fz = deffz;
        }
        float sfx = this.renderdata.foot_xpos[i];
        float sfy = this.renderdata.foot_ypos[i];
        float sfz = this.renderdata.foot_zpos[i];
        this.renderdata.foot_xpos[i] = fx;
        this.renderdata.foot_ypos[i] = fy;
        this.renderdata.foot_zpos[i] = fz;
        if (this.renderdata.footup[i] == 0) {
            this.renderdata.footup[i] = 1;
            d1 = sfx - fx;
            d2 = sfy - fy;
            d3 = sfz - fz;
            dd = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            d1 = (sfy + fy) / 2.0f;
            if ((dd *= 16.0f) > 3.0f) {
                d1 += 0.3f;
            }
            if (dd > 24.0f) {
                d1 += 0.6f;
            }
            if (dd > 50.0f) {
                d1 += 0.6f;
            }
            this.renderdata.uppoint[i] = d1;
        }
    }

    @Override
    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public void addPassenger(Entity passenger) {
        if (passenger instanceof LivingEntity living) {
            this.setYRot(living.getYRot());
            this.yRotO = this.getYRot();
            this.yBodyRot = living.getYRot();
            this.yHeadRot = living.getYRot();
        }
        super.addPassenger(passenger);
        this.boatPosRotationIncrements = 0;
        if (passenger instanceof LivingEntity living) {
            living.setDeltaMovement(Vec3.ZERO);
            living.xxa = 0.0f;
            living.zza = 0.0f;
        }
        if (this.hasPassenger(passenger)) {
            this.positionRider(passenger, Entity::setPos);
        }
    }

    private boolean isNearGround(double gh) {
        int x = Mth.floor(this.getX());
        int z = Mth.floor(this.getZ());
        Block bid =
                this.level()
                        .getBlockState(
                                new BlockPos(x, Mth.floor((float) this.getY() - (float) gh + 1.0f), z))
                        .getBlock();
        if (bid == Blocks.AIR) {
            bid =
                    this.level()
                            .getBlockState(new BlockPos(x, Mth.floor((float) this.getY() - (float) gh), z))
                            .getBlock();
        }
        return bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.LAVA;
    }

    public int getTrackingRange() {
        return 128;
    }

    public int getUpdateFrequency() {
        return 10;
    }

    public boolean sendsVelocityUpdates() {
        return true;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public RenderSpiderRobotInfo getRenderSpiderRobotInfo() {
        return this.renderdata;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof LivingEntity living ? living : null;
    }

    @Override
    protected void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        this.xxa = player.xxa;
        this.zza = player.zza;
    }

    private Vec3 clampRiddenMotion(double mx, double my, double mz) {
        if (my > 0.8500000238418579) {
            my = 0.8500000238418579;
        }
        if (my < -0.8500000238418579) {
            my = -0.8500000238418579;
        }
        if (mx < -1.25) {
            mx = -1.25;
        }
        if (mx > 1.25) {
            mx = 1.25;
        }
        if (mz < -1.25) {
            mz = -1.25;
        }
        if (mz > 1.25) {
            mz = 1.25;
        }
        return new Vec3(mx, my, mz);
    }

    /** OreSpawn 1.7.10 ridden hover + terrain climb (server onLivingUpdate when ridden). */
    private double applyRiddenVerticalPhysics(double my, double horizontalVelocity) {
        double gh = 2.25;
        Block bid =
                this.level()
                        .getBlockState(
                                BlockPos.containing(
                                        this.getX(), this.getY() - gh, this.getZ()))
                        .getBlock();
        if (bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.LAVA) {
            my += 0.06;
            this.setPos(this.getX(), this.getY() + 0.03, this.getZ());
        } else {
            my -= 0.02;
        }
        double obstruction = 0.0;
        int scanDepth = 3 + (int) (Math.max(0.0, horizontalVelocity) * 6.0);
        if (scanDepth > 24) {
            scanDepth = 24;
        }
        for (int k = 1; k < scanDepth; ++k) {
            for (int i = 1; i < scanDepth * 2; ++i) {
                for (int j = -90; j <= 90; j += 30) {
                    double scanDx =
                            (double) i
                                    * Math.cos(
                                            Math.toRadians(this.getYRot() + 90.0f + (float) j));
                    double scanDz =
                            (double) i
                                    * Math.sin(
                                            Math.toRadians(this.getYRot() + 90.0f + (float) j));
                    bid =
                            this.level()
                                    .getBlockState(
                                            BlockPos.containing(
                                                    this.getX() + scanDx,
                                                    this.getY() - k,
                                                    this.getZ() + scanDz))
                                    .getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.WATER || bid == Blocks.LAVA) {
                        continue;
                    }
                    obstruction += 0.02;
                }
            }
        }
        my += obstruction * 0.05;
        if (obstruction != 0.0) {
            this.setPos(this.getX(), this.getY() + obstruction * 0.05, this.getZ());
        }
        return my;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (!this.isVehicle()) {
            super.travel(travelVector);
            return;
        }
        LivingEntity rider = this.getControllingPassenger();
        if (rider == null) {
            super.travel(travelVector);
            return;
        }
        float moveStrafe;
        float moveForward;
        if (this.level().isClientSide && rider instanceof LocalPlayer local) {
            moveStrafe = local.input.leftImpulse;
            moveForward = local.input.forwardImpulse;
        } else {
            moveStrafe = rider.xxa;
            moveForward = rider.zza;
        }
        this.xxa = moveStrafe;
        this.zza = moveForward;
        Vec3 dm = this.getDeltaMovement();
        double velocity = Math.sqrt(dm.x * dm.x + dm.z * dm.z);
        double max_speed = 0.3;
        double relative_g = 0.0;
        double d4 = rider.getYRot();
        d4 %= 360.0;
        while (d4 < 0.0) {
            d4 += 360.0;
        }
        double d5 = this.getYRot();
        d5 %= 360.0;
        while (d5 < 0.0) {
            d5 += 360.0;
        }
        for (relative_g = (d4 - d5) % 180.0; relative_g < 0.0; relative_g += 180.0) {
        }
        if (relative_g > 90.0) {
            relative_g -= 180.0;
        }
        float forwardInput = moveForward;
        float strafeInput = moveStrafe;
        boolean hasDriveInput =
                Math.abs(forwardInput) > 0.001f || Math.abs(strafeInput) > 0.001f;
        float yawDelta = Math.abs(Mth.wrapDegrees(rider.getYRot() - this.getYRot()));
        if (velocity <= 0.01 || yawDelta > 2.0f) {
            this.setYRot(rider.getYRot());
        } else {
            d4 = 1.85 - velocity;
            if ((d4 = Math.abs(d4)) < 0.01) {
                d4 = 0.01;
            }
            if (d4 > 0.9) {
                d4 = 0.9;
            }
            this.setYRot(rider.getYRot() + (float) (relative_g * d4));
        }
        this.setXRot(0.0f);
        this.setRot(this.getYRot(), this.getXRot());
        if (forwardInput < 0.0f) {
            max_speed = 0.25;
        }
        double steerYaw = hasDriveInput ? rider.getYRot() : this.getYRot();
        double yawRad = Math.toRadians(steerYaw);
        double sin = Math.sin(yawRad);
        double cos = Math.cos(yawRad);
        double vx = (-sin * (double) forwardInput + cos * (double) strafeInput * 0.5) * max_speed;
        double vz = (cos * (double) forwardInput + sin * (double) strafeInput * 0.5) * max_speed;
        double mx;
        double mz;
        if (hasDriveInput) {
            mx = vx;
            mz = vz;
        } else {
            mx = dm.x * 0.85;
            mz = dm.z * 0.85;
        }
        // Player-ridden tick() skips living bounce; apply 1.7.10 climb here on controlling side.
        double my = this.applyRiddenVerticalPhysics(dm.y, velocity);
        Vec3 motion = this.clampRiddenMotion(mx, my, mz);
        mx = motion.x;
        my = motion.y;
        mz = motion.z;
        boolean shouldApplyMovement =
                this.isControlledByLocalInstance()
                        || (!this.level().isClientSide && !(rider instanceof Player));
        if (shouldApplyMovement) {
            this.move(MoverType.SELF, new Vec3(mx, my, mz));
            this.setDeltaMovement(mx * 0.98, my * 0.98, mz * 0.98);
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    private boolean hasPlayerRider() {
        return this.getControllingPassenger() instanceof Player;
    }

    private float getSeatForwardOffset() {
        // 1.7 updateRiderPosition: f = -1.25 + cos bob
        return -1.25f + (float) (Math.cos((float) this.rideTicker * 0.33f) * 0.05);
    }

    @Override
    public double getPassengersRidingOffset() {
        // 1.7 getMountedYOffset
        return 0.55 + Math.cos((float) this.rideTicker * 0.19f) * 0.02;
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float f = this.getSeatForwardOffset();
            double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
            double y = ChaosMountHelper.riderSeatY(this, passenger, this.getPassengersRidingOffset());
            double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
            moveFunction.accept(passenger, x, y, z);
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.isVehicle()) {
            if (passenger instanceof Player player) {
                this.lastRiderId = player.getUUID();
            }
            this.dismountCooldown = ChaosMountHelper.GROUND_DISMOUNT_COOLDOWN_TICKS;
            this.fallDistance = 0.0f;
            this.boatPosRotationIncrements = 0;
            if (!this.level().isClientSide) {
                ChaosMountHelper.finishDismountLanding(this);
                this.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            } else {
                this.setDeltaMovement(Vec3.ZERO);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("inFire")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("onFire")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("magic")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("starve")) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
            MyUtils.faceEntity(this, e, 20.0f, 20.0f);
        }
        return super.hurt(par1DamageSource, par2);
    }

    @Override
    public boolean causeFallDamage(float distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int steps, boolean interpolate) {
        if (this.isControlledByLocalInstance()) {
            this.boatPosRotationIncrements = 0;
            return;
        }
        this.boatPosRotationIncrements = this.isVehicle() ? steps + 8 : steps;
        this.boatX = x;
        this.boatY = y;
        this.boatZ = z;
        this.boatYaw = yaw;
        this.boatPitch = pitch;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpMotion(double x, double y, double z) {
        if (!this.isControlledByLocalInstance()) {
            super.lerpMotion(x, y, z);
        }
    }

    @Override
    public void tick() {
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        super.tick();
        this.clearFire();
        if (this.level().getDifficulty() != Difficulty.PEACEFUL && !this.level().isClientSide && this.getControllingPassenger() != null && this.getRandom().nextInt(50) == 0) {
            this.feetFindSomethingToHit();
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL && !this.level().isClientSide && this.getControllingPassenger() != null && this.getRandom().nextInt(9) == 0) {
            LivingEntity e = this.findSomethingToAttack(1.0f, true);
            if (e != null) {
                if (this.distanceToSqr(e) < (double) ((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    this.attackEntityAsMob(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        float f = 4.0f;
        float dx = (float) ((double) f * Math.cos(Math.toRadians(this.getYRot() - 80.0f)));
        float dz = (float) ((double) f * Math.sin(Math.toRadians(this.getYRot() - 80.0f)));
        float dx2 = (float) ((double) f * Math.cos(Math.toRadians(this.getYRot() - 90.0f)));
        float dz2 = (float) ((double) f * Math.sin(Math.toRadians(this.getYRot() - 90.0f)));
        if (this.getRandom().nextInt(18) == 0) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 10.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }
        if (this.getRandom().nextInt(7) == 0) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 10.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }
        if (this.getRandom().nextInt(16) == 0) {
            this.level().addParticle(ParticleTypes.FIREWORK, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 5.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }
        dx = (float) ((double) f * Math.cos(Math.toRadians(this.getYRot() - 100.0f)));
        dz = (float) ((double) f * Math.sin(Math.toRadians(this.getYRot() - 100.0f)));
        if (this.getRandom().nextInt(18) == 0) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 10.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }
        if (this.getRandom().nextInt(7) == 0) {
            this.level().addParticle(ParticleTypes.SMOKE, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 10.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }
        if (this.getRandom().nextInt(16) == 0) {
            this.level().addParticle(ParticleTypes.FIREWORK, this.getX() + (double) dx, this.getY() + 0.5, this.getZ() + (double) dz, (double) (dx2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f), (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 5.0f), (double) (dz2 / f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) / 20.0f));
        }

        this.rideTicker += this.getRandom().nextInt(3);
        if (this.playing > 0) {
            --this.playing;
        }
        if (this.getControllingPassenger() != null && this.playing == 0 && this.getRandom().nextInt(80) == 1) {
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(), ChaosSounds.ROBOTSPIDER, SoundSource.NEUTRAL, 0.35f, 1.0f);
            this.playing = 125;
        }

        if (this.hasPlayerRider()) {
            if (this.level().isClientSide && !this.isControlledByLocalInstance() && this.boatPosRotationIncrements > 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double) this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double) this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double) this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                double d10 =
                        Mth.wrapDegrees(
                                (double) this.getControllingPassenger().getYRot()
                                        - (double) this.getYRot());
                this.setYRot(
                        (float)
                                ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            }
            if (this.level().isClientSide) {
                if (!this.isControlledByLocalInstance()) {
                    this.xo = this.getX();
                    this.yo = this.getY();
                    this.zo = this.getZ();
                }
                this.updateLegs();
            }
            return;
        }

        if (this.level().isClientSide && !this.isControlledByLocalInstance()) {
            this.xo = this.getX();
            this.yo = this.getY();
            this.zo = this.getZ();
            if (this.boatPosRotationIncrements > 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double) this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double) this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double) this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                double d10 = Mth.wrapDegrees(this.boatYaw - (double) this.getYRot());
                this.setYRot((float) ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            }
            this.updateLegs();
            return;
        }

        Vec3 motion = this.getDeltaMovement();
        double mx = motion.x;
        double my = motion.y;
        double mz = motion.z;
        double velocity = Math.sqrt(mx * mx + mz * mz);
        double obstruction_factor = 0.0;
        double gh = 1.75;
        if (this.isRemoved()) {
            return;
        }
        if (my > 0.8500000238418579) {
            my = 0.8500000238418579;
        }
        if (my < -0.8500000238418579) {
            my = -0.8500000238418579;
        }
        if (mx < -1.25) {
            mx = -1.25;
        }
        if (mx > 1.25) {
            mx = 1.25;
        }
        if (mz < -1.25) {
            mz = -1.25;
        }
        if (mz > 1.25) {
            mz = 1.25;
        }
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        if (this.level().isClientSide) {
            if (this.getControllingPassenger() == null) {
                if (this.isNearGround(gh)) {
                    my += 0.12;
                    this.setPos(this.getX(), this.getY() + 0.12, this.getZ());
                    this.boatY += 0.12;
                } else {
                    my -= 0.002;
                }
            }
            if (this.boatPosRotationIncrements > 0 && !this.isControlledByLocalInstance()) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double) this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double) this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double) this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.setXRot(
                        (float)
                                ((double) this.getXRot()
                                        + (this.boatPitch - (double) this.getXRot())
                                                / (double) this.boatPosRotationIncrements));
                double d10 = Mth.wrapDegrees(this.boatYaw - (double) this.getYRot());
                if (this.getControllingPassenger() != null) {
                    d10 = Mth.wrapDegrees(
                            (double) this.getControllingPassenger().getYRot() - (double) this.getYRot());
                }
                this.setYRot((float) ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            } else if (this.isVehicle() && this.isControlledByLocalInstance()) {
                this.move(MoverType.SELF, new Vec3(mx, my, mz));
                mx *= 0.99;
                my *= 0.95;
                mz *= 0.99;
            } else {
                double d4 = this.getX() + mx;
                double d5 = this.getY() + my;
                double d11 = this.getZ() + mz;
                this.setPos(d4, d5, d11);
                mx *= 0.99;
                my *= 0.95;
                mz *= 0.99;
            }
            this.updateLegs();
        } else {
            Block bid;
            if (this.getControllingPassenger() != null) {
                gh = 2.25;
                bid = this.level().getBlockState(new BlockPos((int) this.getX(), (int) ((float) this.getY() - (float) gh), (int) this.getZ())).getBlock();
                if (bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.LAVA) {
                    my += 0.06;
                } else {
                    my -= 0.02;
                }
            } else {
                if (this.isNearGround(gh)) {
                    my += 0.15;
                    this.setPos(this.getX(), this.getY() + 0.15, this.getZ());
                    this.boatY += 0.15;
                } else {
                    my -= 0.002;
                }
            }
            if (this.getControllingPassenger() != null && this.getControllingPassenger().isRemoved()) {
                this.ejectPassengers();
            }
            if (this.isVehicle()) {
                obstruction_factor = 0.0;
                int scanDepth = 3 + (int) (Math.max(0.0, velocity) * 6.0);
                if (scanDepth > 24) {
                    scanDepth = 24;
                }
                for (int k = 1; k < scanDepth; ++k) {
                    for (int i = 1; i < scanDepth * 2; ++i) {
                        for (int j = -90; j <= 90; j += 30) {
                            double scanDz = (double) i * Math.sin(Math.toRadians(this.getYRot() + 90.0f + (float) j));
                            double scanDx = (double) i * Math.cos(Math.toRadians(this.getYRot() + 90.0f + (float) j));
                            bid = this.level().getBlockState(new BlockPos((int) (this.getX() + scanDx), (int) this.getY() - k, (int) (this.getZ() + scanDz))).getBlock();
                            if (bid == Blocks.AIR || bid == Blocks.WATER || bid == Blocks.LAVA) continue;
                            obstruction_factor += 0.02;
                        }
                    }
                }
                my += obstruction_factor * 0.05;
                this.move(MoverType.SELF, new Vec3(mx, my, mz));
                mx *= 0.98;
                my *= 0.98;
                mz *= 0.98;
            } else {
                this.move(MoverType.SELF, new Vec3(mx, my, mz));
                mx *= 0.8;
                my *= 0.98;
                mz *= 0.8;
            }
        }

        this.setDeltaMovement(mx, my, mz);
        ChaosMountHelper.applyGroundGravityWhenIdle(this);
    }

    public void goThisWay(double mx, double mz) {
        Vec3 dm = this.getDeltaMovement();
        this.setDeltaMovement(mx, dm.y, mz);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("AntRobotOwned", this.getOwned());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.owned = tag.getInt("AntRobotOwned");
        this.entityData.set(OWNED, (byte) this.owned);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ChaosPersists.MyWrench) && this.getPassengers().isEmpty()) {
            if (ItemWrench.tryDismantle(player, this, stack, hand)) {
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (this.getOwned() == 0) {
                return InteractionResult.FAIL;
            }
        }
        if (this.getOwned() == 0) {
            return InteractionResult.PASS;
        }
        final double interactRangeSq = 36.0;
        if (!stack.isEmpty() && stack.is(Items.IRON_INGOT) && player.distanceToSqr(this) < interactRangeSq) {
            if (!this.level().isClientSide) {
                float healAmount = this.getMaxHealth() - this.getHealth();
                if (healAmount > 100.0f) {
                    healAmount = 100.0f;
                }
                if (healAmount > 0.0f) {
                    this.heal(healAmount);
                }
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.getControllingPassenger() instanceof Player rider && rider != player) {
            return InteractionResult.PASS;
        }
        if (stack.isEmpty()
                && player.distanceToSqr(this) < interactRangeSq
                && ChaosMountHelper.canPlayerMount(player, this, this.dismountCooldown)) {
            if (!this.level().isClientSide) {
                player.startRiding(this);
                ChaosMountHelper.onPlayerMounted(this);
                this.positionRider(player, Entity::setPos);
                this.lastRiderId = null;
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ChaosSounds.ROBOTSPIDERMOUNT,
                                SoundSource.NEUTRAL,
                                0.45f,
                                1.0f);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return InteractionResult.PASS;
    }

    private void feetFindSomethingToHit() {
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        List var5 = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 8.0, 10.0));
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity) var2.next();
            var4 = (LivingEntity) var3;
            if (!this.feetisSuitableTarget(var4, false)) continue;
            this.feetattackEntityAsMob(var4);
        }
    }

    private boolean feetisSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (par1EntityLiving instanceof AntRobot) {
            return false;
        }
        if (par1EntityLiving == this.getControllingPassenger()) {
            return false;
        }
        if (par1EntityLiving instanceof Player player
                && this.lastRiderId != null
                && player.getUUID().equals(this.lastRiderId)
                && this.dismountCooldown > 0) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        float d1 = (float) (par1EntityLiving.getX() - this.getX());
        float d2 = (float) (par1EntityLiving.getY() - this.getY());
        float d3 = (float) (par1EntityLiving.getZ() - this.getZ());
        float dd = (float) Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
        if (dd > 9.0f) {
            return false;
        }
        if (dd < 6.0f) {
            return false;
        }
        if (par1EntityLiving instanceof Player) {
            Player p = (Player) par1EntityLiving;
            if (p.getAbilities().instabuild) {
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean feetattackEntityAsMob(Entity par1Entity) {
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            double ks = 0.6;
            double inair = 0.1;
            float f3 = (float) Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            ret = par1Entity.hurt(this.damageSources().mobAttack(this), (float) ChaosPersists.AntRobot_stats.attack / 10.0f);
            if (par1Entity.isRemoved() || par1Entity instanceof Player) {
                inair *= 2.0;
            }
            if (ret) {
                ((LivingEntity) par1Entity).push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return ret;
    }

    private LivingEntity findSomethingToAttack(float distmul, boolean dircheck) {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0 * (double) distmul, 12.0, 12.0 * (double) distmul));
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity) var2.next();
            var4 = (LivingEntity) var3;
            if (!this.isSuitableTarget(var4, dircheck)) continue;
            return var4;
        }
        return null;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (par1EntityLiving instanceof AntRobot) {
            return false;
        }
        if (par1EntityLiving == this.getControllingPassenger()) {
            return false;
        }
        if (par1EntityLiving instanceof Player player
                && this.lastRiderId != null
                && player.getUUID().equals(this.lastRiderId)
                && this.dismountCooldown > 0) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par2) {
            double rr = Math.atan2(par1EntityLiving.getZ() - this.getZ(), par1EntityLiving.getX() - this.getX());
            double rhdir = Math.toRadians((this.getYRot() + 90.0f) % 360.0f);
            double pi = 3.1415926545;
            double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            rdd = Math.abs(rdd);
            if (this.distanceToSqr(par1EntityLiving) < 36.0) {
                return true;
            }
            if (rdd > 0.75) {
                return false;
            }
        }
        if (par1EntityLiving instanceof Player) {
            Player p = (Player) par1EntityLiving;
            if (p.getAbilities().instabuild) {
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean attackEntityAsMob(Entity par1Entity) {
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            double ks = 0.7;
            double inair = 0.1;
            float f3 = (float) Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            ret = par1Entity.hurt(this.damageSources().mobAttack(this), (float) ChaosPersists.AntRobot_stats.attack);
            if (par1Entity.isRemoved() || par1Entity instanceof Player) {
                inair *= 2.0;
            }
            if (ret) {
                ((LivingEntity) par1Entity).push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return ret;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, par1);
    }

    private ItemStack dropItemRand(net.minecraft.world.item.Item index, int par1) {
        ItemEntity var3;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level(), this.getX() + (double) ChaosPersists.ChaosRand.nextInt(2) - (double) ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(2) - (double) ChaosPersists.ChaosRand.nextInt(2), is);
        this.level().addFreshEntity(var3);
        return is;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (this.level().isClientSide) {
            return;
        }
        ItemStack is = null;
        int i = 7 + this.getRandom().nextInt(7);
        block13:
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(12);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(Items.REDSTONE, 1);
                    continue block13;
                }
                case 1: {
                    is = this.dropItemRand(Items.REPEATER, 1);
                    continue block13;
                }
                case 2: {
                    is = this.dropItemRand(Items.COMPARATOR, 1);
                    continue block13;
                }
                case 3: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block13;
                }
                case 4: {
                    is = this.dropItemRand(Blocks.DISPENSER.asItem(), 1);
                    continue block13;
                }
                case 5: {
                    is = this.dropItemRand(Blocks.STICKY_PISTON.asItem(), 1);
                    continue block13;
                }
                case 6: {
                    is = this.dropItemRand(Blocks.PISTON.asItem(), 1);
                    continue block13;
                }
                case 7: {
                    is = this.dropItemRand(Blocks.LEVER.asItem(), 1);
                    continue block13;
                }
                case 8: {
                    is = this.dropItemRand(Blocks.REDSTONE_BLOCK.asItem(), 1);
                    continue block13;
                }
                case 9: {
                    is = this.dropItemRand(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE.asItem(), 1);
                    continue block13;
                }
                case 10: {
                    is = this.dropItemRand(Items.IRON_INGOT, 1);
                    break;
                }
            }
        }
    }
}
