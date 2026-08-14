/*
 * Ported from Chaos Persists 1.12.2 — original decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.Cephadrome
 *  com.astryxion.chaospersists.GammaMetroid
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.Kraken
 *  com.astryxion.chaospersists.Leon
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.WaterDragon
 *  com.astryxion.chaospersists.compat.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.EnderDragon
 *  net.minecraft.entity.boss.EnderDragonPart
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.TamableAnimal
 *  net.minecraft.entity.player.Player
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  com.astryxion.chaospersists.compat.minecraft.init.Blocks
 *  com.astryxion.chaospersists.compat.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.SpawnerBlockEntity
 *  com.astryxion.chaospersists.compat.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.Explosion
 *  com.astryxion.chaospersists.compat.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.entity.GammaMetroid;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import com.astryxion.chaospersists.util.ChaosMountHelper;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;

public class Cephadrome extends PathfinderMob {

    private static final EntityDataAccessor<Byte> ATTACKING = SynchedEntityData.defineId(Cephadrome.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> STATE2 = SynchedEntityData.defineId(Cephadrome.class, EntityDataSerializers.BYTE);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private int damage_counter = 100;
    private int updateit = 1;
    private int color = 1;
    private int playing = 0;
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wasfed;
    private int shouldattack = 0;
    private int wing_sound = 0;
    private int hit_by_player = 0;
    private int badmood = 0;
    private float moveSpeed = 0.25f;
    private int dismountCooldown = 0;
    private int groundWanderTicks = 0;
    private int groundIdleTicks = 0;
    private float groundWanderYaw = 0.0f;

        public Cephadrome(EntityType<? extends Cephadrome> type, Level level) {
        super(type, level);
        this.xpReward = 200;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 9.0f));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
        this.moveControl = new ChaosChaseMoveControl(this);
        // 1.1 clears a full block via collide() step-up; 1.0 often fails on exact 1-block height.
        this.setMaxUpStep(1.1F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 70.0)
                .add(Attributes.ARMOR, 16.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
    }

    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public float getPickRadius() {
        return 2.5f;
    }

    @Override
    public boolean isControlledByLocalInstance() {
        Entity rider = this.getControllingPassenger();
        return rider instanceof Player player && player.isLocalPlayer();
    }

    private void resetClientInterpolation() {
        this.boatPosRotationIncrements = 0;
        this.boatX = this.getX();
        this.boatY = this.getY();
        this.boatZ = this.getZ();
        this.boatYaw = this.getYRot();
        this.boatPitch = this.getXRot();
        this.boatYawHead = this.getYRot();
    }

    @Override
    public void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        this.resetClientInterpolation();
        if (passenger != null && this.hasPassenger(passenger)) {
            this.setActivity(1);
            this.positionRider(passenger, Entity::moveTo);
        }
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity rider = this.getFirstPassenger();
        return rider instanceof LivingEntity living ? living : null;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 2.2;
    }

    @Override
    protected void positionRider(Entity passenger, net.minecraft.world.entity.Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float f = 0.75f;
            double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
            double y = this.getY() + this.getPassengersRidingOffset() + passenger.getMyRidingOffset();
            double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
            moveFunction.accept(passenger, x, y, z);
        } else {
            super.positionRider(passenger, moveFunction);
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        this.resetClientInterpolation();
        if (this.getPassengers().isEmpty()) {
            this.noPhysics = false;
            this.setNoGravity(false);
            MyUtils.clearChaosFlight(this);
            if (!this.level().isClientSide && this.getPassengers().isEmpty()) {
                this.setActivity(0);
                this.dismountCooldown = ChaosMountHelper.GROUND_DISMOUNT_COOLDOWN_TICKS;
                this.getNavigation().stop();
                this.setNoGravity(false);
                this.noPhysics = false;
                MyUtils.clearChaosFlight(this);
                if (this.lacksGroundSupport()) {
                    Vec3 motion = this.getDeltaMovement();
                    this.setDeltaMovement(motion.x * 0.5, -0.55, motion.z * 0.5);
                } else {
                    ChaosMountHelper.finishDismountLanding(this);
                }
            }
        }
    }

    /**
     * Same steering pipeline as {@link ThePrinceTeen}: vanilla calls {@code travel} with synced rider input
     * (via {@code CPacketInput} from client). Flight used to live only in {@code onLivingUpdate}, which never ran
     * through this path, so {@code moveForward} stayed wrong on the server.
     */
    @Override
    public void travel(Vec3 travelVector) {
        Vec3 dm = this.getDeltaMovement();
        double mx = dm.x;
        double my = dm.y;
        double mz = dm.z;
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player) {
            if (!this.level().isClientSide && this.getActivity() == 0) {
                this.setActivity(1);
            }
            Player pp = (Player)this.getControllingPassenger();
            if (pp.isDeadOrDying()) {
                this.ejectPassengers();
                this.setNoGravity(false);
                super.travel(travelVector);
                return;
            }
            this.setNoGravity(true);
            boolean riderJumping =
                    pp instanceof LocalPlayer lp && lp.input.jumping
                            || ChaosPersists.flyup_keystate != 0;
            boolean riderDescending =
                    pp instanceof LocalPlayer lp && lp.input.shiftKeyDown;
            double obstruction_factor = 0.0;
            double relative_g = 0.0;
            double max_speed = 1.15;
            double gh = 1.0;
            double rt = 0.0;
            double pi = 3.1415926545;
            double deltav = 0.0;
            double rdv;
            if (mx < -2.0) {
                mx = -2.0;
            }
            if (mx > 2.0) {
                mx = 2.0;
            }
            if (mz < -2.0) {
                mz = -2.0;
            }
            if (mz > 2.0) {
                mz = 2.0;
            }
            double velocity = Math.sqrt(mx * mx + mz * mz);
            gh = 1.55;
            BlockState ground =
                    this.level()
                            .getBlockState(
                                    BlockPos.containing(
                                            this.getX(), (float) this.getY() - (float) gh, this.getZ()));
            if (!ground.isAir()) {
                my += 0.07;
                this.setPos(this.getX(), this.getY() + 0.1, this.getZ());
            } else if (!riderJumping && ChaosPersists.flyup_keystate == 0) {
                my -= 0.018;
            }
            obstruction_factor = 0.0;
            // Scan radius from speed, fixed bound — do not mutate dist in the for-condition (was infinite loop).
            int scanDist = 2 + (int)(velocity * 6.0);
            if (scanDist < 2) {
                scanDist = 2;
            }
            if (scanDist > 32) {
                scanDist = 32;
            }
            for (int k = 1; k < scanDist; ++k) {
                for (int i = 1; i < scanDist * 2; ++i) {
                    double dz;
                    double dx = (double)i * Math.cos(Math.toRadians(this.getYRot() + 90.0f));
                    BlockState scan =
                            this.level()
                                    .getBlockState(
                                            BlockPos.containing(
                                                    this.getX() + dx,
                                                    this.getY() - k,
                                                    this.getZ()
                                                            + (dz =
                                                                    (double) i
                                                                            * Math.sin(
                                                                                    Math.toRadians(
                                                                                            this.getYRot()
                                                                                                    + 90.0f)))));
                    if (scan.isAir()) continue;
                    obstruction_factor += 0.04;
                }
            }
            my += obstruction_factor * 0.09;
            this.setPos(this.getX(), this.getY() + obstruction_factor * 0.09, this.getZ());
            if (my > 2.0) {
                my = 2.0;
            }
            double d4 = pp.getYRot();
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
            if (velocity > 0.01) {
                d4 = 1.85 - velocity;
                if ((d4 = Math.abs(d4)) < 0.01) {
                    d4 = 0.01;
                }
                if (d4 > 0.9) {
                    d4 = 0.9;
                }
                this.setYRot(pp.getYRot() + (float) (relative_g * d4));
            } else {
                this.setYRot(pp.getYRot());
            }
            relative_g = Math.abs(relative_g) * velocity;
            if (relative_g > 50.0) {
                relative_g = 0.0;
            }
            this.setXRot(2.0f * (float) velocity);
            this.setRot(this.getYRot(), this.getXRot());
            this.setYHeadRot(this.getYRot());
            double newvelocity = Math.sqrt(mx * mx + mz * mz);
            double rhm = Math.atan2(mz, mx);
            double rhdir = Math.toRadians((pp.getYRot() + 90.0f) % 360.0f);
            rt = 0.0;
            pi = 3.1415926545;
            deltav = 0.0;
            double im = pp.zza;
            if (this.level().isClientSide && pp instanceof LocalPlayer local) {
                im = local.input.forwardImpulse;
            }
            if (ChaosPersists.flyup_keystate != 0) {
                my += 0.04;
                my += velocity * 0.05;
            } else if (riderJumping) {
                my += 0.035;
                my += velocity * 0.038;
            }
            if (riderDescending) {
                my -= 0.06;
            }
            if ((rdv = Math.abs(rhm - rhdir) % (pi * 2.0)) > pi) {
                rdv -= pi * 2.0;
            }
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01) {
                rdv = 0.0;
            }
            if (rdv > 1.5) {
                newvelocity = -newvelocity;
            }
            if (Math.abs(im) > 0.0010000000474974513) {
                if (im > 0.0) {
                    deltav = 0.03;
                    if (max_speed > 0.85) {
                        deltav += 0.05;
                    }
                } else {
                    max_speed = 0.35;
                    deltav = -0.03;
                }
                if ((newvelocity += deltav) >= 0.0) {
                    if (newvelocity > max_speed) {
                        newvelocity = max_speed;
                    }
                    mx = Math.cos(Math.toRadians(this.getYRot() + 90.0f)) * newvelocity;
                    mz = Math.sin(Math.toRadians(this.getYRot() + 90.0f)) * newvelocity;
                } else {
                    if (newvelocity < -max_speed) {
                        newvelocity = -max_speed;
                    }
                    newvelocity = -newvelocity;
                    mx = Math.cos(Math.toRadians(this.getYRot() + 270.0f)) * newvelocity;
                    mz = Math.sin(Math.toRadians(this.getYRot() + 270.0f)) * newvelocity;
                }
            } else if (newvelocity >= 0.0) {
                mx = Math.cos(Math.toRadians(this.getYRot() + 90.0f)) * newvelocity;
                mz = Math.sin(Math.toRadians(this.getYRot() + 90.0f)) * newvelocity;
            } else {
                mx = Math.cos(Math.toRadians(this.getYRot() + 270.0f)) * (newvelocity * -1.0);
                mz = Math.sin(Math.toRadians(this.getYRot() + 270.0f)) * (newvelocity * -1.0);
            }
            this.setDeltaMovement(mx, my, mz);
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            dm = this.getDeltaMovement();
            mx = dm.x * 0.985;
            my = dm.y * 0.94;
            mz = dm.z * 0.985;
            this.setDeltaMovement(mx, my, mz);
            if (!this.level().isClientSide) {
                List<Entity> list =
                        this.level()
                                .getEntities(
                                        this,
                                        this.getBoundingBox().inflate(2.25, 2.0, 2.25),
                                        ent -> ent != pp && ent.isPushable() && ent.isAlive());
                for (Entity listEntity : list) {
                    listEntity.push(this);
                }
            }
            return;
        }
        this.setNoGravity(false);
        super.travel(travelVector);
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

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean causeFallDamage(float distance, float multiplier, DamageSource source) {
        return false;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(STATE2, (byte)0);
        this.setActivity(0);
        this.setAttacking(0);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
    }

    public int mygetMaxHealth() {
        return 300;
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
    }

    public int getArmorValue() {
        return 16;
    }

    @Override
    protected void jumpFromGround() {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.1, 0.0));
        super.jumpFromGround();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getActivity() != 1 && this.getRandom().nextInt(6) == 1) {
            return com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(5)
                                - (double) ChaosPersists.ChaosRand.nextInt(5),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(5)
                                - (double) ChaosPersists.ChaosRand.nextInt(5),
                        is);
        if (var3 != null) {
            this.level().addFreshEntity(var3);
        }
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        int var4;
        int i = 4 + this.getRandom().nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
        }
        i = 4 + this.getRandom().nextInt(6);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
        i = 1 + this.getRandom().nextInt(5);
        block17 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(20);
            ItemStack is;
            switch (var3) 
            {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
                    continue block17;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block17;
                }
                case 2: {
                    is = this.dropItemRand(ChaosPersists.MyThunderStaff, 1);
                    continue block17;
                }
                case 3: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyRubyShovel, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyRubyPickaxe, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyRubyAxe, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyRubyHoe, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 8: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyHelmet, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(2));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block17;
                    is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block17;
                }
                case 9: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBody, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block17;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block17;
                }
                case 10: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyLegs, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block17;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block17;
                }
                case 11: {
                    is = this.dropItemRand((Item)ChaosPersists.RubyBoots, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block17;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block17;
                }
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: {
                    is = this.dropItemRand(ChaosPersists.MyRuby, 1);
                    break;
                }
            }
        }
    }

    public int getCephadromeHealth() {
        return (int)this.getHealth();
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        double ks = 2.5;
        double inair = 0.35;
        float iskraken = 1.0f;
        boolean ret = false;
        if (par1Entity != null && par1Entity instanceof EnderDragon) {
            EnderDragon dr = (EnderDragon)par1Entity;
            DamageSource var21 = null;
            var21 = this.damageSources().explosion(null);
            if (this.getRandom().nextInt(6) == 1) {
                dr.hurt(var21, 70.0F);
            } else {
                dr.hurt(var21, 70.0F);
            }
            ret = true;
        } else if (par1Entity != null && par1Entity instanceof LivingEntity) {
            if (par1Entity instanceof Kraken) {
                iskraken = 1.5f;
            }
            ret = par1Entity.hurt(this.damageSources().mobAttack(this), iskraken * 70.0f);
            float f3 = (float) Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (!par1Entity.isAlive() || par1Entity instanceof Player) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return ret;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.hurt_timer > 0) {
            Entity attacker = par1DamageSource.getEntity();
            if (attacker != null) {
                double dx = attacker.getX() - this.getX();
                double dz = attacker.getZ() - this.getZ();
                if (dx * dx + dz * dz > 1.0E-4) {
                    this.knockback(0.35, dx, dz);
                }
            }
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        if (par1DamageSource.is(DamageTypes.CACTUS)) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.hurt_timer = 12;
        }
        Entity e = par1DamageSource.getEntity();
        if (!this.level().isClientSide && e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
        }
        if (e != null && e instanceof Player && this.getHealth() < this.getMaxHealth() * 9.0f / 10.0f) {
            this.hit_by_player = 1;
        }
        return ret;
    }

    public double getHorizontalDistanceSqToEntity(Entity par1Entity) {
        double d0 = this.getX() - par1Entity.getX();
        double d2 = this.getZ() - par1Entity.getZ();
        return d0 * d0 + d2 * d2;
    }

    /** Wide mobs fail 1-block pathfinding; steer via {@link #travel} instead. */
    private void planGroundWander() {
        if (!this.getPassengers().isEmpty() || this.getTarget() != null || this.getActivity() != 0) {
            return;
        }
        this.getNavigation().stop();

        if (this.groundWanderTicks > 0) {
            --this.groundWanderTicks;
            if (this.groundWanderTicks == 0) {
                this.groundIdleTicks = 50 + this.getRandom().nextInt(70);
                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(0.0, motion.y, 0.0);
            }
            return;
        }

        if (this.groundIdleTicks > 0) {
            --this.groundIdleTicks;
            return;
        }

        if (this.getRandom().nextInt(30) != 0) {
            return;
        }

        this.groundWanderTicks = 35 + this.getRandom().nextInt(55);
        float angle = this.getRandom().nextFloat() * ((float) Math.PI * 2.0f);
        this.groundWanderYaw =
                (float) Math.toDegrees(Math.atan2(-Math.cos(angle), Math.sin(angle)));
    }

    /** Wide ceph hitbox: bypass 1-block pathfinding and slide on the ground directly. */
    private void applyGroundWalkMovement() {
        if (this.level().isClientSide) {
            return;
        }
        if (!this.getPassengers().isEmpty() || this.getActivity() != 0 || this.getTarget() != null) {
            return;
        }
        if (this.groundWanderTicks <= 0 || this.lacksGroundSupport()) {
            return;
        }
        this.setYRot(Mth.rotateIfNecessary(this.getYRot(), this.groundWanderYaw, 12.0f));
        this.yBodyRot = this.getYRot();
        float yawRad = (float) Math.toRadians(this.getYRot());
        double speed = (double) this.moveSpeed * 1.35;
        double mx = -Math.sin(yawRad) * speed;
        double mz = Math.cos(yawRad) * speed;
        this.moveGroundForward(mx, mz);
    }

    private void applyChaseMovement(LivingEntity target) {
        if (this.level().isClientSide || this.lacksGroundSupport()) {
            return;
        }
        double dx = target.getX() - this.getX();
        double dz = target.getZ() - this.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist < 0.05) {
            return;
        }
        double speed = (double) this.moveSpeed * 1.7;
        double mx = (dx / dist) * speed;
        double mz = (dz / dist) * speed;
        this.setYRot((float) (Math.toDegrees(Math.atan2(dz, dx)) - 90.0));
        this.yBodyRot = this.getYRot();
        this.moveGroundForward(mx, mz);
    }

    /**
     * Slide on XZ like the original ground walk. If that hits a slab/block, retry with a
     * slight downward move so {@code Entity.collide} can step up in the same call.
     * Do not hop — airborne Y is slammed by {@link #tickUnmountedPhysics}.
     */
    private void moveGroundForward(double mx, double mz) {
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(mx, motion.y, mz);
        this.move(MoverType.SELF, new Vec3(mx, 0.0, mz));
        if (this.horizontalCollision && !this.lacksGroundSupport()) {
            this.move(MoverType.SELF, new Vec3(mx, -0.08, mz));
        }
    }

    private void tickCombat() {
        if (this.level().isClientSide
                || this.getActivity() != 0
                || this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        LivingEntity target = this.getTarget();
        if (target != null && !target.isAlive()) {
            this.setTarget(null);
            MyUtils.setChaseTarget(this, null);
            target = null;
        }
        if (target == null) {
            if (this.getAttacking() != 0) {
                this.setAttacking(0);
            }
            if (this.getRandom().nextInt(7) == 1) {
                target = this.findSomethingToAttack();
                if (target != null) {
                    this.setTarget(target);
                }
            }
            return;
        }
        this.getNavigation().stop();
        MyUtils.setChaseTarget(this, target);
        MyUtils.faceEntity(this, target, 10.0f, 10.0f);
        this.applyChaseMovement(target);
        this.setAttacking(1);
        double maxdist = 6.0;
        double reach = maxdist + (double) target.getBbWidth() / 2.0;
        if (this.distanceToSqr(target) < reach * reach) {
            this.doHurtTarget(target);
        } else if (target instanceof Kraken
                && this.getHorizontalDistanceSqToEntity(target) < reach * reach) {
            this.doHurtTarget(target);
        }
    }

    private boolean lacksGroundSupport() {
        if (this.isNoGravity()) {
            return true;
        }
        if (this.onGround()) {
            return false;
        }
        Level level = this.level();
        if (level == null) {
            return !this.onGround();
        }
        double minY = this.getBoundingBox().minY - 0.05;
        double half = this.getBbWidth() * 0.35;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (double xOff : new double[] {0.0, half, -half}) {
            for (double zOff : new double[] {0.0, half, -half}) {
                pos.set(this.getX() + xOff, minY, this.getZ() + zOff);
                if (!level.getBlockState(pos).getCollisionShape(level, pos).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.updateit > 0) {
            --this.updateit;
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.updateit <= 0 && !this.level().isClientSide) {
            this.updateit = 30;
            if (this.getFirstPassenger() != null) {
                this.setActivity(1);
            } else {
                this.setActivity(0);
            }
        }
        if (this.getRandom().nextInt(100) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        super.customServerAiStep();
        if (this.getActivity() == 0 && this.getTarget() == null) {
            this.planGroundWander();
            this.applyGroundWalkMovement();
        }
        this.tickCombat();
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Cephadrome) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof Mothra) {
            return true;
        }
        if (par1EntityLiving instanceof Leon) {
            TamableAnimal et = (TamableAnimal)par1EntityLiving;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof GammaMetroid) {
            TamableAnimal et = (TamableAnimal)par1EntityLiving;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof WaterDragon) {
            TamableAnimal et = (TamableAnimal)par1EntityLiving;
            if (et.isTame()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof EnderDragon) {
            return true;
        }
        if (par1EntityLiving instanceof Player) {
            Player p = (Player)par1EntityLiving;
            if (p.isCreative()) {
                return false;
            }
            if (this.hit_by_player != 0) {
                return true;
            }
            if (this.badmood != 0) {
                return true;
            }
            if (this.shouldattack > 0) {
                this.shouldattack = 0;
                return true;
            }
            return false;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 20.0, 16.0));
        Collections.sort(var5, this.targetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public static boolean checkCephadromeSpawnRules(EntityType<Cephadrome> type, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, net.minecraft.util.RandomSource random) {
        Cephadrome probe = type.create((Level) level);
        if (probe == null) return false;
        probe.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 0.0f, 0.0f);
        return probe.checkSpawnRules(level, spawnReason);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Cephadrome".equals(id.getPath())) {
                        this.badmood = 1;
                        return true;
                    }
                }
            }
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (!MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                        return false;
                    }
                }
            }
        }
        List<Cephadrome> nearby =
                this.level()
                        .getEntitiesOfClass(
                                Cephadrome.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0), ent -> ent != this);
        if (!nearby.isEmpty()) {
            return false;
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double par1, double par3, double par5, float par7, float par8, int par9, boolean p_19902_) {
        if (this.isControlledByLocalInstance()) {
            this.boatPosRotationIncrements = 0;
            return;
        }
        if (this.getPassengers().isEmpty()) {
            super.lerpTo(par1, par3, par5, par7, par8, par9, p_19902_);
            this.resetClientInterpolation();
            return;
        }
        this.boatPosRotationIncrements = par9;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.boatYawHead = par7;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double par1, double par3, double par5) {
        if (!this.isControlledByLocalInstance()) {
            super.lerpMotion(par1, par3, par5);
        }
    }

    @Override
    public void tick() {
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        if (this.getPassengers().isEmpty()) {
            this.noPhysics = false;
            this.setNoGravity(false);
            MyUtils.clearChaosFlight(this);
            if (!this.level().isClientSide) {
                this.setActivity(0);
            }
        } else {
            if (!this.level().isClientSide) {
                this.setActivity(1);
            }
            this.noPhysics = true;
        }
        super.tick();
        if (this.getActivity() == 1) {
            ++this.wing_sound;
            if (this.wing_sound > 22) {
                if (!this.level().isClientSide && ChaosSounds.MOTHRA_WINGS != null) {
                    this.level()
                            .playSound(
                                    null,
                                    this.getX(),
                                    this.getY(),
                                    this.getZ(),
                                    ChaosSounds.MOTHRA_WINGS,
                                    SoundSource.NEUTRAL,
                                    0.5f,
                                    1.0f);
                }
                this.wing_sound = 0;
            }
        }
        if (!this.level().isClientSide && this.getPassengers().isEmpty() && !this.lacksGroundSupport()) {
            MyUtils.enforceDragonMountGroundSafety(this);
        }
    }

    private void tickUnmountedPhysics() {
        if (!this.getPassengers().isEmpty()) {
            return;
        }
        this.noPhysics = false;
        if (this.isNoGravity()) {
            this.setNoGravity(false);
        }
        MyUtils.clearChaosFlight(this);
        if (this.lacksGroundSupport()) {
            Vec3 motion = this.getDeltaMovement();
            double vy = Math.min(motion.y - 0.08, -0.22);
            this.setDeltaMovement(motion.x * 0.98, vy, motion.z * 0.98);
            if (!this.level().isClientSide) {
                this.move(MoverType.SELF, new Vec3(0.0, vy, 0.0));
            }
        }
    }

    @Override
    protected void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        this.xxa = player.xxa;
        this.zza = player.zza;
        if (this.level().isClientSide && player instanceof LocalPlayer local) {
            this.xxa = local.input.leftImpulse;
            this.zza = local.input.forwardImpulse;
        }
        this.setRot(player.getYRot(), player.getXRot() * 0.5f);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        if (this.level().isClientSide && this.isVehicle() && !this.isControlledByLocalInstance()) {
            this.xo = this.getX();
            this.yo = this.getY();
            this.zo = this.getZ();
            if (this.boatPosRotationIncrements > 0) {
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
                    d10 =
                            Mth.wrapDegrees(
                                    (double) this.getControllingPassenger().getYRot() - (double) this.getYRot());
                }
                this.setYRot((float) ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            }
        }
        super.aiStep();
        this.tickUnmountedPhysics();
        if (this.level().isClientSide) {
            if (this.isVehicle() && this.boatPosRotationIncrements > 0 && this.isControlledByLocalInstance()) {
                --this.boatPosRotationIncrements;
            }
            if (this.getActivity() != 0 && this.getControllingPassenger() instanceof LocalPlayer pp) {
                pp.connection.send(new ServerboundMovePlayerPacket.Rot(pp.getYRot(), pp.getXRot(), pp.onGround()));
                pp.connection.send(
                        new ServerboundPlayerInputPacket(
                                pp.xxa, pp.zza, pp.input.jumping, pp.input.shiftKeyDown));
            }
        }
    }

    protected void playTameEffect(boolean par1) {
        String s = "heart";
        if (!par1) {
            s = "smoke";
        }
        for (int i = 0; i < 20; ++i) {
            double d0 = this.getRandom().nextGaussian() * 0.08;
            double d1 = this.getRandom().nextGaussian() * 0.08;
            double d2 = this.getRandom().nextGaussian() * 0.08;
            this.level()
                    .addParticle(
                            par1 ? ParticleTypes.HEART : ParticleTypes.SMOKE,
                            this.getX()
                                    + (double)
                                            ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.5f),
                            this.getY() + 0.5 + (double) this.getRandom().nextFloat() * 1.5,
                            this.getZ()
                                    + (double)
                                            ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.5f),
                            d0,
                            d1,
                            d2);
        }
    }

    @Override
    public InteractionResult mobInteract(Player par1Player, InteractionHand hand) {
        ItemStack var2 = par1Player.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1Player.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (var2.isEmpty()) {
            if (this.getFirstPassenger() instanceof Player rider && rider != par1Player) {
                return InteractionResult.SUCCESS;
            }
            if (ChaosMountHelper.canPlayerMount(par1Player, this, this.dismountCooldown)) {
                if (!this.level().isClientSide) {
                    if (this.wasfed == 0) {
                        this.setTarget(par1Player);
                        this.shouldattack = 1;
                        return InteractionResult.FAIL;
                    }
                    par1Player.startRiding(this);
                    ChaosMountHelper.onPlayerMounted(this);
                    this.wasfed = 0;
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            return InteractionResult.PASS;
        }
        if (!var2.isEmpty()
                && (var2.is(Items.BEEF) || var2.is(Items.CHICKEN) || var2.is(Items.PORKCHOP))
                && par1Player.distanceToSqr(this) < 25.0) {
            if (!this.level().isClientSide) {
                this.heal((float) this.mygetMaxHealth() - this.getHealth());
            }
            this.wasfed = 1;
            this.shouldattack = 0;
            this.playTameEffect(true);
            if (!par1Player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1Player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).byteValue() & 0xFF;
    }

    public void setAttacking(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public int getActivity() {
        return this.entityData.get(STATE2).byteValue() & 0xFF;
    }

    public void setActivity(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        this.entityData.set(STATE2, (byte)par1);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.getFirstPassenger() != null) {
            return false;
        }
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        par1NBTTagCompound.putInt("CephaWasFed", this.wasfed);
        par1NBTTagCompound.putInt("CephaAttacking", this.getAttacking());
        par1NBTTagCompound.putInt("CephaActivity", this.getActivity());
        par1NBTTagCompound.putInt("CephaHitByPlayer", this.hit_by_player);
        par1NBTTagCompound.putInt("CephaBadMood", this.badmood);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.wasfed = par1NBTTagCompound.getInt("CephaWasFed");
        this.hit_by_player = par1NBTTagCompound.getInt("CephaHitByPlayer");
        this.badmood = par1NBTTagCompound.getInt("CephaBadMood");
        this.setAttacking(par1NBTTagCompound.getInt("CephaAttacking"));
        this.setActivity(par1NBTTagCompound.getInt("CephaActivity"));
    }
}

