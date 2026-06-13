/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.AntRobot
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderSpiderRobotInfo
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.PistonBlock
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderSpiderRobotInfo;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CInputPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class AntRobot
extends CreatureEntity {
    private static final DataParameter<Integer> ATTACKING = EntityDataManager.defineId(AntRobot.class, DataSerializers.INT);
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

    private Entity getRiddenByEntity() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public AntRobot(EntityType<? extends AntRobot> type, World par1World) {
        super(type, par1World);
        // Model is long/tall; old 2.75?1.25 made a thin slab ? rays aimed at the body missed (mount/heal/wrench).
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 12.0f));
        this.goalSelector.addGoal(2, new LookRandomlyGoal(this));
        this.xpReward = ChaosPersists.AntRobot_stats.health / 2;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public AntRobot(World par1World, double par2, double par4, double par6) {
        this((EntityType<? extends AntRobot>)ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "robot_red_ant")), par1World);
        this.setPos(par2, par4 + (double)this.getMyRidingOffset(), par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

        public static AttributeModifierMap createAttributes() {
        return CreatureEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.AntRobot_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.AntRobot_stats.attack)
                .build();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    public void setOwned() {
        this.owned = 1;
    }

    public int getOwned() {
        return this.owned;
    }

    public int getArmorValue() {
        return ChaosPersists.AntRobot_stats.defense;
    }

    protected void customServerAiStep() {
        LivingEntity e = null;
        if (!this.isAlive()) {
            return;
        }
        if (this.getRiddenByEntity() != null) {
            return;
        }
        super.customServerAiStep();
        if (this.owned == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            if (this.level.random.nextInt(20) == 0) {
                this.feetFindSomethingToHit();
            }
            if (this.level.random.nextInt(150) == 0) {
                this.setTarget(null);
            }
            if ((e = this.getTarget()) != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack(2.0f);
            }
            if (e != null) {
                this.lookAt((Entity)e, 10.0f, 10.0f);
                if (this.distanceToSqr((Entity)e) > 16.0) {
                    double d1 = e.getZ() - this.getZ();
                    double d2 = e.getX() - this.getX();
                    double dd = Math.atan2(d1, d2);
                    this.goThisWay(0.2 * Math.cos(dd), 0.2 * Math.sin(dd));
                }
            } else {
                this.setAttacking(0);
            }
            if (e != null && this.level.random.nextInt(15) == 0) {
                e = this.getTarget();
                if (e == null) {
                    e = this.findSomethingToAttack(2.0f, true);
                }
                if (e != null) {
                    if (this.distanceToSqr((Entity)e) < (double)((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f))) {
                        this.setAttacking(1);
                        this.doHurtTarget((LivingEntity)e);
                    } else {
                        this.setAttacking(0);
                    }
                } else {
                    this.setAttacking(0);
                }
            }
        }
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
            this.renderdata.foot_xpos[i] = (float)this.getX();
            this.renderdata.foot_ypos[i] = (float)this.getY();
            this.renderdata.foot_zpos[i] = (float)this.getZ();
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
            if ((double)diff < 0.008726646259971648 * (double)tv) {
                curval = 0.0f;
            } else {
                curval = (float)((double)curval + 0.004363323129985824 * (double)tv);
                if ((double)diff < 0.06981317007977318 * (double)tv) {
                    curval = (float)(0.017453292519943295 * (double)tv);
                }
                if ((double)diff < 0.03490658503988659 * (double)tv) {
                    curval = (float)(0.008726646259971648 * (double)tv);
                }
                if ((double)curval > 0.06981317007977318 * (double)tv) {
                    curval = (float)(0.06981317007977318 * (double)tv);
                }
            }
        } else if ((double)diff > -0.008726646259971648 * (double)tv) {
            curval = 0.0f;
        } else {
            curval = (float)((double)curval - 0.004363323129985824 * (double)tv);
            if ((double)diff > -0.06981317007977318 * (double)tv) {
                curval = - (float)(0.017453292519943295 * (double)tv);
            }
            if ((double)diff > -0.03490658503988659 * (double)tv) {
                curval = - (float)(0.008726646259971648 * (double)tv);
            }
            if ((double)curval < -0.06981317007977318 * (double)tv) {
                curval = - (float)(0.06981317007977318 * (double)tv);
            }
        }
        return curval;
    }

    public void updateLegs() {
        if (!this.level.isClientSide) {
            return;
        }
        this.yRot %= 360.0f;
        while (this.yRot < 0.0f) {
            this.yRot += 360.0f;
        }
        ++this.renderdata.gpcounter;
        if (this.didonce == 0) {
            this.didonce = 1;
            this.initLegData();
        }
        float d1 = (float)(this.xo - this.getX());
        float d2 = (float)(this.yo - this.getY());
        float d3 = (float)(this.zo - this.getZ());
        float realv = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
        int i = 0;
        for (i = 0; i < 6; ++i) {
            double rdv;
            int fcount = 0;
            int[] arrn = this.renderdata.footingticker;
            int n = i;
            arrn[n] = arrn[n] + 1;
            this.renderdata.realposx[i] = (float)(this.getX() - (double)this.renderdata.legoff[i] * Math.sin(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i]));
            this.renderdata.realposz[i] = (float)(this.getZ() + (double)this.renderdata.legoff[i] * Math.cos(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i]));
            this.renderdata.realposy[i] = (float)this.getY() + this.renderdata.yoff[i];
            int it = this.renderdata.footingticker[i] + this.renderdata.footingticker[this.renderdata.pairedwith[i]];
            if (it > 50 && this.renderdata.footingticker[i] > this.renderdata.footingticker[this.renderdata.pairedwith[i]]) {
                this.renderdata.footingticker[i] = 0;
            }
            d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
            d2 = this.renderdata.realposy[i] - this.renderdata.foot_ypos[i];
            d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
            float dd = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
            dd *= 16.0f;
            float da = (float)(Math.abs((double)this.renderdata.ycurrentangle[i] - (Math.toRadians(MathHelper.wrapDegrees((double)this.yRot)) + (double)this.renderdata.ymid[i])) % 6.283185307179586);
            if ((double)da > 3.141592653589793) {
                da = (float)((double)da - 6.283185307179586);
            }
            if ((double)da < -3.141592653589793) {
                da = (float)((double)da + 6.283185307179586);
            }
            da = Math.abs(da);
            if (dd > 144.0f || dd < 22.0f || da > Math.abs(this.renderdata.yrange[i]) * 8.0f / 6.0f || (double)Math.abs(this.renderdata.udcurrentangle[i]) > 1.25 || this.renderdata.footingticker[i] == 0) {
                this.findNewFooting(i);
                d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
                d2 = this.renderdata.realposy[i] - this.renderdata.foot_ypos[i];
                d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
                dd = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                dd *= 16.0f;
            }
            float c1 = (float)(49.0 * Math.cos(this.renderdata.p2xangle[i] - this.renderdata.p1xangle[i]));
            float c2 = 49.0f;
            float c3 = (float)(49.0 * Math.cos(this.renderdata.p2xangle[i] - this.renderdata.p3xangle[i]));
            float cc = c1 + c2 + c3;
            float diff = cc - dd;
            this.renderdata.pxvelocity[i] = this.getNewVelocity(realv, (float)((double)diff * 3.141592653589793 / 360.0), this.renderdata.pxvelocity[i]);
            if (this.renderdata.pxvelocity[i] == 0.0f || Math.abs(diff) < 8.0f) {
                ++fcount;
            }
            double[] arrd = this.renderdata.p1xangle;
            int n2 = i;
            arrd[n2] = arrd[n2] + (double)this.renderdata.pxvelocity[i];
            this.renderdata.p2xangle[i] = 0.0;
            this.renderdata.p3xangle[i] = - this.renderdata.p1xangle[i];
            dd = this.renderdata.uppoint[i] != 0.0f ? (float)Math.atan2(dd, (double)(this.renderdata.realposy[i] - this.renderdata.uppoint[i]) * 16.0) : (float)Math.atan2(dd, (double)(this.renderdata.realposy[i] - this.renderdata.foot_ypos[i]) * 16.0);
            this.renderdata.udwantedangle[i] = (float)((double)dd - 1.5707963267948966);
            while ((double)this.renderdata.udwantedangle[i] > 3.141592653589793) {
                float[] arrf = this.renderdata.udwantedangle;
                int n3 = i;
                arrf[n3] = (float)((double)arrf[n3] - 6.283185307179586);
            }
            while ((double)this.renderdata.udwantedangle[i] < -3.141592653589793) {
                float[] arrf = this.renderdata.udwantedangle;
                int n4 = i;
                arrf[n4] = (float)((double)arrf[n4] + 6.283185307179586);
            }
            double rhm = this.renderdata.udwantedangle[i];
            double rhdir = this.renderdata.udcurrentangle[i];
            for (rdv = (rhm - rhdir) % 6.283185307179586; rdv > 3.141592653589793; rdv -= 6.283185307179586) {
            }
            while (rdv < -3.141592653589793) {
                rdv += 6.283185307179586;
            }
            diff = (float)rdv;
            this.renderdata.udvelocity[i] = this.getNewVelocity(realv * 2.0f, diff, this.renderdata.udvelocity[i]);
            if (this.renderdata.udvelocity[i] == 0.0f || (double)Math.abs(diff) < 0.03490658503988659) {
                this.renderdata.uppoint[i] = 0.0f;
                ++fcount;
            }
            rhdir += (double)this.renderdata.udvelocity[i];
            while (rhdir > 3.141592653589793) {
                rhdir -= 6.283185307179586;
            }
            while (rhdir < -3.141592653589793) {
                rhdir += 6.283185307179586;
            }
            this.renderdata.uddisplayangle[i] = dd = (this.renderdata.udcurrentangle[i] = (float)rhdir);
            d3 = this.renderdata.realposz[i] - this.renderdata.foot_zpos[i];
            d1 = this.renderdata.realposx[i] - this.renderdata.foot_xpos[i];
            dd = (float)Math.atan2(d3, d1);
            this.renderdata.ywantedangle[i] = dd;
            rhm = this.renderdata.ywantedangle[i];
            rdv = (rhm - (rhdir = (double)this.renderdata.ycurrentangle[i])) % 6.283185307179586;
            if (rdv > 3.141592653589793) {
                rdv -= 6.283185307179586;
            }
            if (rdv < -3.141592653589793) {
                rdv += 6.283185307179586;
            }
            diff = (float)rdv;
            this.renderdata.yvelocity[i] = this.getNewVelocity(realv, diff, this.renderdata.yvelocity[i]);
            if (this.renderdata.yvelocity[i] == 0.0f || (double)Math.abs(diff) < 0.03490658503988659) {
                ++fcount;
            }
            float[] arrf = this.renderdata.ycurrentangle;
            int n5 = i;
            arrf[n5] = arrf[n5] + this.renderdata.yvelocity[i];
            while ((double)this.renderdata.ycurrentangle[i] > 3.141592653589793) {
                float[] arrf2 = this.renderdata.ycurrentangle;
                int n6 = i;
                arrf2[n6] = (float)((double)arrf2[n6] - 6.283185307179586);
            }
            while ((double)this.renderdata.ycurrentangle[i] < -3.141592653589793) {
                float[] arrf3 = this.renderdata.ycurrentangle;
                int n7 = i;
                arrf3[n7] = (float)((double)arrf3[n7] + 6.283185307179586);
            }
            dd = (float)((double)this.renderdata.ycurrentangle[i] - Math.toRadians(MathHelper.wrapDegrees((double)this.yRot)) - 1.5707963267948966);
            while ((double)dd > 3.141592653589793) {
                dd = (float)((double)dd - 6.283185307179586);
            }
            while ((double)dd < -3.141592653589793) {
                dd = (float)((double)dd + 6.283185307179586);
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
        double rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f);
        double pi = 3.1415926545;
        this.renderdata.footingticker[i] = 0;
        float d1 = (float)(this.getX() - this.xo);
        float d3 = (float)(this.getZ() - this.zo);
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
        if (Math.abs((this.yRotO - this.yRot) % 360.0f) > 0.75f) {
            range = 0.0f;
        }
        if (i >= 4) {
            f = 4.0f;
        }
        if (rdv > 1.5) {
            range = - range;
            f = 4.0f;
            if (i >= 4) {
                f = 9.0f;
            }
        }
        if (i == 0 || i == 1) {
            f = 6.0f;
        }
        float deffx = fx = (float)((double)this.renderdata.realposx[i] - (double)(f / 2.0f) * Math.sin(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i]));
        float deffz = fz = (float)((double)this.renderdata.realposz[i] + (double)(f / 2.0f) * Math.cos(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i]));
        float deffy = fy = this.renderdata.realposy[i] - 1.0f;
        float oldf = f;
        int span = 1;
        while (!found && f > 2.5f) {
            fx = (float)((double)this.renderdata.realposx[i] - (double)f * Math.sin(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i] - (double)range));
            fz = (float)((double)this.renderdata.realposz[i] + (double)f * Math.cos(Math.toRadians(MathHelper.wrapDegrees((double)(this.yRot + 90.0f))) + (double)this.renderdata.ymid[i] - (double)range));
            fy = this.renderdata.realposy[i];
            for (int j = 8; !found && j > -9; --j) {
                block2 : for (int m = - span; !found && m <= span; ++m) {
                    for (int n = - span; !found && n <= span; ++n) {
                        Block blk = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)fx + m, (int)fy + j, (int)fz + n)).getBlock();
                        net.minecraft.block.BlockState stateHere = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)fx + m, (int)fy + j, (int)fz + n));
                        if (blk == Blocks.AIR || !stateHere.isSolidRender(this.level, new net.minecraft.util.math.BlockPos((int)fx + m, (int)fy + j, (int)fz + n))) continue;
                        d1 = this.renderdata.realposx[i] - (fx + (float)m);
                        d2 = this.renderdata.realposy[i] - (fy + (float)j + 1.0f);
                        d3 = this.renderdata.realposz[i] - (fz + (float)n);
                        dd = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                        if ((dd *= 16.0f) > 144.0f) continue;
                        fy += (float)(j + 1);
                        fx += (float)m;
                        fz += (float)n;
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
            dd = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
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

    public boolean shouldRiderSit() {
        return true;
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

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.setPersistenceRequired();
        this.initLegData();
        int i = 0;
        this.entityData.define(ATTACKING, i);
    }

    public RenderSpiderRobotInfo getRenderSpiderRobotInfo() {
        return this.renderdata;
    }

    public boolean canBePushed() {
        return false;
    }

    public boolean canBeSteered() {
        return true;
    }

    public boolean canPassengerSteer() {
        return true;
    }

    public boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    @Override
    public Entity getControllingPassenger() {
        if (this.getPassengers().isEmpty()) {
            return null;
        }
        return this.getPassengers().get(0);
    }

    private PlayerEntity getRiderPlayerEntity() {
        Entity rider = this.getControllingPassenger();
        if (rider instanceof PlayerEntity) {
            return (PlayerEntity) rider;
        }
        if (rider != null && !rider.getPassengers().isEmpty() && rider.getPassengers().get(0) instanceof PlayerEntity) {
            return (PlayerEntity) rider.getPassengers().get(0);
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    private ClientPlayerEntity getRiderPlayerEntityClient() {
        PlayerEntity rider = this.getRiderPlayerEntity();
        return rider instanceof ClientPlayerEntity ? (ClientPlayerEntity) rider : null;
    }

    @Override
    public void travel(Vector3d travelVector) {
        if (this.getControllingPassenger() == null) {
            super.travel(travelVector);
            return;
        }
        Entity controller = this.getControllingPassenger();
        if (!(controller instanceof LivingEntity)) {
            super.travel(travelVector);
            return;
        }
        LivingEntity rider = (LivingEntity) controller;
        float moveStrafe = rider.xxa;
        float moveForward = rider.yya;
        this.xxa = moveStrafe;
        this.yya = moveForward;

        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double max_speed = 0.3;
        double relative_g = 0.0;
        double d4 = (double) rider.yRot;
        d4 %= 360.0;
        while (d4 < 0.0) {
            d4 += 360.0;
        }
        double d5 = this.yRot;
        d5 %= 360.0;
        while (d5 < 0.0) {
            d5 += 360.0;
        }
        for (relative_g = (d4 - d5) % 180.0; relative_g < 0.0; relative_g += 180.0) {
        }
        if (relative_g > 90.0) {
            relative_g -= 180.0;
        }
        if (velocity > 0.01) {
            d4 = 1.85 - velocity;
            if ((d4 = Math.abs(d4)) < 0.01) {
                d4 = 0.01;
            }
            if (d4 > 0.9) {
                d4 = 0.9;
            }
            this.yRot = rider.yRot + (float) (relative_g * d4);
        } else {
            this.yRot = rider.yRot;
        }
        relative_g = Math.abs(relative_g) * velocity;
        if (relative_g > 50.0) {
            relative_g = 0.0;
        }
        this.xRot = 0.0f;

        float forwardInput = moveForward;
        float strafeInput = moveStrafe;
        if (forwardInput < 0.0f) {
            max_speed = 0.25;
        }
        double yawRad = Math.toRadians(this.yRot);
        double sin = Math.sin(yawRad);
        double cos = Math.cos(yawRad);
        double vx = (-sin * (double) forwardInput + cos * (double) strafeInput * 0.5) * max_speed;
        double vz = (cos * (double) forwardInput + sin * (double) strafeInput * 0.5) * max_speed;
        if (Math.abs(forwardInput) > 0.001f || Math.abs(strafeInput) > 0.001f) {
            this.setDeltaMovement(vx, this.getDeltaMovement().y, this.getDeltaMovement().z);
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, vz);
        } else {
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.85, 1.0, 1.0);
            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 1.0, 0.85);
        }
    }

    public double getMountedYOffset() {
        return 0.55 + Math.cos((float)this.rideTicker * 0.19f) * 0.02;
    }

    public void updateRiderPosition() {
        if (this.getRiddenByEntity() != null) {
            float f = -1.25f;
            f = (float)((double)f + Math.cos((float)this.rideTicker * 0.33f) * 0.05);
            this.getRiddenByEntity().setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset() + this.getRiddenByEntity().getMyRidingOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
    }

    @Override
    public void positionRider(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            this.updateRiderPosition();
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
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
        if (e != null && e instanceof MobEntity) {
            this.setTarget((LivingEntity)((MobEntity)e));
            this.lookAt(e, 20.0f, 20.0f);
        }
        return super.hurt(par1DamageSource, par2);
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    public float getCollisionBorderSize() {
        return 0.35f;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.boatPosRotationIncrements = this.getRiddenByEntity() != null ? posRotationIncrements + 8 : posRotationIncrements + 6;
        this.boatX = x;
        this.boatY = y;
        this.boatZ = z;
        this.boatYaw = yaw;
        this.boatPitch = pitch;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.boatPosRotationIncrements = this.getRiddenByEntity() != null ? par9 + 8 : par9 + 6;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        if (this.getRiddenByEntity() == null) {
            this.setDeltaMovement(par1, par3, par5);
        }
    }

    public void tick() {
        super.tick();
        this.setSecondsOnFire(0);
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && !this.level.isClientSide && this.getRiddenByEntity() != null && this.level.random.nextInt(50) == 0) {
            this.feetFindSomethingToHit();
        }
        if (this.level.getDifficulty() != Difficulty.PEACEFUL && !this.level.isClientSide && this.getRiddenByEntity() != null && this.level.random.nextInt(9) == 0) {
            LivingEntity e = null;
            e = this.findSomethingToAttack(1.0f, true);
            if (e != null) {
                if (this.distanceToSqr((Entity)e) < (double)((6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    this.doHurtTarget((LivingEntity)e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        float f = 4.0f;
        float dx = (float)((double)f * Math.cos(Math.toRadians(this.yRot - 80.0f)));
        float dz = (float)((double)f * Math.sin(Math.toRadians(this.yRot - 80.0f)));
        float dx2 = (float)((double)f * Math.cos(Math.toRadians(this.yRot - 90.0f)));
        float dz2 = (float)((double)f * Math.sin(Math.toRadians(this.yRot - 90.0f)));
        if (this.level.random.nextInt(18) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FLAME, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 10.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
        if (this.level.random.nextInt(7) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 10.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
        if (this.level.random.nextInt(16) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 5.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
        dx = (float)((double)f * Math.cos(Math.toRadians(this.yRot - 100.0f)));
        dz = (float)((double)f * Math.sin(Math.toRadians(this.yRot - 100.0f)));
        if (this.level.random.nextInt(18) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FLAME, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 10.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
        if (this.level.random.nextInt(7) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.SMOKE, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 10.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
        if (this.level.random.nextInt(16) == 0) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX() + (double)dx, this.getY() + 0.5, this.getZ() + (double)dz, (double)(dx2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f), (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) / 5.0f), (double)(dz2 / f + (this.level.random.nextFloat() - this.level.random.nextFloat()) / 20.0f));
        }
    }

    public void aiStep() {
        Object list = null;
        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double d6 = this.random.nextFloat() * 2.0f - 1.0f;
        double d7 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
        double obstruction_factor = 0.0;
        double relative_g = 0.0;
        double max_speed = 0.3;
        double gh = 1.75;
        int dist = 2;
        if (!this.isAlive()) {
            return;
        }
        super.aiStep();
        if (this.getDeltaMovement().y > 0.8500000238418579) {
            this.setDeltaMovement(this.getDeltaMovement().x, 0.8500000238418579, this.getDeltaMovement().z);
        }
        if (this.getDeltaMovement().y < -0.8500000238418579) {
            this.setDeltaMovement(this.getDeltaMovement().x, -0.8500000238418579, this.getDeltaMovement().z);
        }
        if (this.getDeltaMovement().x < -1.25) {
            this.setDeltaMovement(-1.25, this.getDeltaMovement().y, this.getDeltaMovement().z);
        }
        if (this.getDeltaMovement().x > 1.25) {
            this.setDeltaMovement(1.25, this.getDeltaMovement().y, this.getDeltaMovement().z);
        }
        if (this.getDeltaMovement().z < -1.25) {
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, -1.25);
        }
        if (this.getDeltaMovement().z > 1.25) {
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 1.25);
        }
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        this.rideTicker += this.level.random.nextInt(3);
        if (this.playing > 0) {
            --this.playing;
        }
        if (this.getRiddenByEntity() != null && this.playing == 0 && this.level.random.nextInt(80) == 1) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.ROBOTSPIDER, net.minecraft.util.SoundCategory.NEUTRAL, 0.35f, 1.0f);
            this.playing = 125;
        }
        if (this.level.isClientSide) {
            if (this.getRiddenByEntity() == null) {
                Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh + 1.0f), (int)this.getZ())).getBlock();
                if (bid == Blocks.AIR) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
                }
                if (bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.WATER && bid != Blocks.LAVA && bid != Blocks.LAVA) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.12, 0.0);
                    com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.12);
                    this.boatY += 0.12;
                } else {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.002), 0.0);
                }
            }
            {
                ClientPlayerEntity pp = this.getRiderPlayerEntityClient();
                if (pp != null) {
                pp.connection.send(new CPlayerPacket.RotationPacket(pp.yRot, pp.xRot, pp.isOnGround()));
                pp.connection.send(new CInputPacket(pp.xxa, pp.yya, pp.input.jumping, pp.isShiftKeyDown()));
                }
            }
            if (this.boatPosRotationIncrements > 0) {
                double d4 = this.getX() + (this.boatX - this.getX()) / (double)this.boatPosRotationIncrements;
                double d5 = this.getY() + (this.boatY - this.getY()) / (double)this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double)this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.xRot = (float)((double)this.xRot + (this.boatPitch - (double)this.xRot) / (double)this.boatPosRotationIncrements);
                double d10 = MathHelper.wrapDegrees((double)(this.boatYaw - (double)this.yRot));
                if (this.getRiddenByEntity() != null) {
                    d10 = MathHelper.wrapDegrees((double)((double)this.getRiddenByEntity().yRot - (double)this.yRot));
                }
                this.yRot = (float)((double)this.yRot + d10 / (double)this.boatPosRotationIncrements);
                --this.boatPosRotationIncrements;
            } else {
                double d4 = this.getX() + this.getDeltaMovement().x;
                double d5 = this.getY() + this.getDeltaMovement().y;
                double d11 = this.getZ() + this.getDeltaMovement().z;
                this.setPos(d4, d5, d11);
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.99, 0.95, 0.99);
            }
            this.updateLegs();
        } else {
            Block bid;
            if (this.getRiddenByEntity() != null) {
                gh = 2.25;
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
                if (bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.WATER && bid != Blocks.LAVA && bid != Blocks.LAVA) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.06, 0.0);
                    com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.03);
                } else {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.02), 0.0);
                }
            } else {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh + 1.0f), (int)this.getZ())).getBlock();
                if (bid == Blocks.AIR) {
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
                }
                if (bid != Blocks.AIR && bid != Blocks.WATER && bid != Blocks.WATER && bid != Blocks.LAVA && bid != Blocks.LAVA) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.15, 0.0);
                    com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.15);
                    this.boatY += 0.15;
                } else {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.002), 0.0);
                }
            }
            PlayerEntity pp = this.getRiderPlayerEntity();
            if (pp != null && pp.isCrouching()) {
                pp.stopRiding();
                return;
            }
            if (!this.getPassengers().isEmpty()) {
                obstruction_factor = 0.0;
                int scanDepth = 3 + (int)(Math.max(0.0, velocity) * 6.0);
                if (scanDepth > 24) {
                    scanDepth = 24;
                }
                for (int k = 1; k < scanDepth; ++k) {
                    for (int i = 1; i < scanDepth * 2; ++i) {
                        for (int j = -90; j <= 90; j += 30) {
                            double dz;
                            double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f + (float)j));
                            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f + (float)j)))))).getBlock();
                            if (bid == Blocks.AIR || bid == Blocks.WATER || bid == Blocks.WATER || bid == Blocks.LAVA || bid == Blocks.LAVA) continue;
                            obstruction_factor += 0.02;
                        }
                    }
                }
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.05, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.05);
                this.move(net.minecraft.entity.MoverType.SELF, this.getDeltaMovement());
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.98, 0.98, 0.98);
            } else {
                this.move(net.minecraft.entity.MoverType.SELF, this.getDeltaMovement());
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.8, 0.98, 0.8);
            }
            if (this.getRiddenByEntity() != null && this.getRiddenByEntity().removed) {
                this.ejectPassengers();
            }
        }
    }

    public void goThisWay(double mx, double mz) {
        this.setDeltaMovement(mx, this.getDeltaMovement().y, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, mz);
    }

    public boolean isAIEnabled() {
        if (this.getRiddenByEntity() != null) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("AntRobotOwned", this.owned);
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.owned = par1CompoundNBT.getInt("AntRobotOwned");
    }

    public float getShadowSize() {
        return 0.95f;
    }

    /**
     * 1.7.10 parity: {@code owned == 0} (wild / egg-spawned) returns true immediately ? no wheat-style taming here.
     * Taming is done by placing a {@link com.astryxion.chaospersists.item.ItemSpiderRobotKit Red Ant Robot Kit} on a block,
     * which calls {@link #setOwned()}. Owned robots: iron ingot heals, empty hand nearby mounts (within 6 blocks).
     */
    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (this.owned == 0) {
            return ActionResultType.SUCCESS;
        }
        final double interactRangeSq = 36.0;
        if (!stack.isEmpty() && stack.getItem() == Items.IRON_INGOT && player.distanceToSqr((Entity) this) < interactRangeSq) {
            if (!this.level.isClientSide) {
                float healAmount = this.getMaxHealth() - this.getHealth();
                if (healAmount > 100.0f) {
                    healAmount = 100.0f;
                }
                if (healAmount > 0.0f) {
                    this.heal(healAmount);
                }
            }
            if (!player.isCreative()) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return ActionResultType.SUCCESS;
        }
        Entity rider = getRiddenByEntity();
        if (rider instanceof PlayerEntity && rider != player) {
            return ActionResultType.SUCCESS;
        }
        if (!this.level.isClientSide && rider == null && player.distanceToSqr((Entity) this) < interactRangeSq) {
            player.startRiding(this);
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.ROBOTSPIDERMOUNT, net.minecraft.util.SoundCategory.NEUTRAL, 0.45f, 1.0f);
        }
        return ActionResultType.SUCCESS;
    }

    private void feetFindSomethingToHit() {
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 8.0, 10.0));
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.feetisSuitableTarget(var4, false)) continue;
            this.feetdoHurtTarget((LivingEntity)var4);
        }
    }

    private boolean feetisSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (par1Mob instanceof AntRobot) {
            return false;
        }
        if (par1Mob == this.getRiddenByEntity()) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        float d1 = (float)(par1Mob.getX() - this.getX());
        float d2 = (float)(par1Mob.getY() - this.getY());
        float d3 = (float)(par1Mob.getZ() - this.getZ());
        float dd = (float)Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
        if (dd > 9.0f) {
            return false;
        }
        if (dd < 6.0f) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean feetdoHurtTarget(LivingEntity par1Entity) {
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            double ks = 0.6;
            double inair = 0.1;
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            ret = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), (float)ChaosPersists.AntRobot_stats.attack / 10.0f);
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            if (ret) {
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return ret;
    }

    private LivingEntity findSomethingToAttack(float distmul) {
        return this.findSomethingToAttack(distmul, false);
    }

    private LivingEntity findSomethingToAttack(float distmul, boolean dircheck) {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0 * (double)distmul, 12.0, 12.0 * (double)distmul));
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, dircheck)) continue;
            return var4;
        }
        return null;
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (par1Mob instanceof AntRobot) {
            return false;
        }
        if (par1Mob == this.getRiddenByEntity()) {
            return false;
        }
        if (MyUtils.isIgnoreable((LivingEntity)par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par2) {
            double rr = Math.atan2(par1Mob.getZ() - this.getZ(), par1Mob.getX() - this.getX());
            double rhdir = Math.toRadians((this.yRot + 90.0f) % 360.0f);
            double pi = 3.1415926545;
            double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            rdd = Math.abs(rdd);
            if (this.distanceToSqr((Entity)par1Mob) < 36.0) {
                return true;
            }
            if (rdd > 0.75) {
                return false;
            }
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            double ks = 0.7;
            double inair = 0.1;
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            ret = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), (float)ChaosPersists.AntRobot_stats.attack);
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            if (ret) {
                par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return ret;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, par1);
    }

    protected Item getDropItem() {
        return null;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        ItemStack is = null;
        int i = 7 + this.level.random.nextInt(7);
        block13 : for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.level.random.nextInt(12);
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
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.REDSTONE_BLOCK), 1);
                    continue block13;
                }
                case 4: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.DISPENSER), 1);
                    continue block13;
                }
                case 5: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.STICKY_PISTON), 1);
                    continue block13;
                }
                case 6: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.PISTON), 1);
                    continue block13;
                }
                case 7: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.LEVER), 1);
                    continue block13;
                }
                case 8: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.REDSTONE_BLOCK), 1);
                    continue block13;
                }
                case 9: {
                    is = this.dropItemRand(Item.byBlock((Block)Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE), 1);
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

