package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerInputPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import com.astryxion.chaospersists.util.ChaosMountHelper;
import com.astryxion.chaospersists.util.RoyalPetFollowHelper;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.client.player.LocalPlayer;

public class Dragon extends TamableAnimal {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Dragon.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> STATE2 =
            SynchedEntityData.defineId(Dragon.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> INT22 =
            SynchedEntityData.defineId(Dragon.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> INT24 =
            SynchedEntityData.defineId(Dragon.class, EntityDataSerializers.INT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private int updateit = 1;
    private int color = 1;
    private int playing = 0;
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wing_sound = 0;
    private BlockPos currentFlightTarget = null;
    private boolean target_in_sight = false;
    private int owner_flying = 0;
    private int flyaway = 0;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private int unstick_timer = 0;
    private int fireballticker = 0;
    private float moveSpeed = 0.32f;
    private float deltasmooth = 0.0f;
    private int dragontype = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;
    private int dismountCooldown = 0;

    public Dragon(EntityType<? extends Dragon> type, Level level) {
        super(type, level);
        this.xpReward = 100;
        this.fireImmune();
        this.moveSpeed = 0.32f;
        this.setOrderedToSit(false);
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner(this, 1.1f, 12.0f, 2.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 9.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, true));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(
                    1,
                    new NearestAttackableTargetGoal<>(
                            this, Mob.class, 10, true, false, PetCombatHelper::isNearestHostileGoalTarget) {
                        @Override
                        public boolean canUse() {
                            return PetCombatHelper.canUseNearestHostileGoal(Dragon.this)
                                    && super.canUse();
                        }
                    });
        }
        this.targetSelector.addGoal(2, new ChaosHurtByTargetGoal(this));
        this.refreshDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 35.0)
                .add(Attributes.ARMOR, 14.0);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.fixed(1.5f, 1.25f);
    }

    @Override
    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (!this.level().isClientSide && orderedToSit) {
            this.setActivity(0);
            this.setAttacking(0);
            PetCombatHelper.onPetSit(this);
            this.owner_flying = 0;
            MyUtils.clearChaosFlight(this);
            this.setNoGravity(false);
            this.noPhysics = false;
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            Vec3 dm = this.getDeltaMovement();
            this.setDeltaMovement(0.0, Math.min(dm.y, 0.0), 0.0);
        }
    }

    private boolean isStayingPut() {
        return RoyalPetFollowHelper.isStayingPut(this);
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
    public LivingEntity getControllingPassenger() {
        Entity rider = this.getFirstPassenger();
        return rider instanceof LivingEntity living ? living : null;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.75;
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float f = 0.65f;
            double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
            double y = ChaosMountHelper.riderSeatY(this, passenger, this.getPassengersRidingOffset());
            double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
            moveFunction.accept(passenger, x, y, z);
        } else {
            super.positionRider(passenger, moveFunction);
        }
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        float f = 0.65f;
        double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
        double y = ChaosMountHelper.riderSeatY(this, passenger, this.getPassengersRidingOffset());
        double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
        return new Vec3(x, y, z);
    }

    /** True when no solid block is under the hitbox. Do not trust onGround() after chaos flight. */
    private boolean lacksGroundSupport() {
        if (this.level() == null) {
            return !this.onGround();
        }
        double minY = this.getBoundingBox().minY - 0.05;
        double half = this.getBbWidth() * 0.35;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (double xOff : new double[] {0.0, half, -half}) {
            for (double zOff : new double[] {0.0, half, -half}) {
                pos.set(this.getX() + xOff, minY, this.getZ() + zOff);
                if (!this.level().getBlockState(pos).getCollisionShape(this.level(), pos).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Land / stay must actually drop. Do not call enforceDragonMountGroundSafety here:
     * isInWall() while touching the ground shoved princes (and this dragon) back up ~2 blocks.
     */
    private void applyLandingFallIfNeeded() {
        if (this.level().isClientSide || !this.getPassengers().isEmpty()) {
            return;
        }
        if (this.getActivity() != 0 && !this.isStayingPut()) {
            return;
        }
        this.setNoGravity(false);
        this.noPhysics = false;
        MyUtils.clearChaosFlight(this);
        if (!this.lacksGroundSupport()) {
            return;
        }
        this.setOnGround(false);
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x * 0.98, Math.min(motion.y - 0.08, -0.22), motion.z * 0.98);
        this.move(MoverType.SELF, this.getDeltaMovement());
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

    public boolean canBeSteered() {
        return true;
    }

    public boolean canPassengerSteer() {
        return true;
    }

    /**
     * Same pipeline as {@link ThePrinceTeen} / {@link Cephadrome}: vanilla calls {@code travel} with rider
     * input synced from the client via {@code ServerboundPlayerInputPacket}. Flight must run here, not only in
     * {@code aiStep}, or {@code zza} stays zero on the server and the mount cannot fly.
     */
    @Override
    public void travel(Vec3 travelVector) {
        if (this.isStayingPut() || this.getActivity() == 0) {
            if (this.lacksGroundSupport()) {
                this.setOnGround(false);
            }
            this.setNoGravity(false);
            super.travel(travelVector);
            return;
        }
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        Vec3 dm = this.getDeltaMovement();
        double mx = dm.x;
        double my = dm.y;
        double mz = dm.z;
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player pp && this.getActivity() != 0) {
            if (pp.isDeadOrDying()) {
                this.ejectPassengers();
                this.setNoGravity(false);
                super.travel(travelVector);
                return;
            }
            this.setNoGravity(true);
            double obstruction_factor;
            double relative_g;
            double max_speed = 0.95;
            double gh;
            double pi = 3.1415926545;
            double deltav;
            BetterFireball bf;

            if (this.fireballticker > 0) {
                --this.fireballticker;
            }

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

            gh = 1.25;
            BlockState ground =
                    this.level()
                            .getBlockState(
                                    BlockPos.containing(
                                            this.getX(), (float) this.getY() - (float) gh, this.getZ()));
            if (!ground.isAir()) {
                my += 0.03;
                this.setPos(this.getX(), this.getY() + 0.1, this.getZ());
            } else {
                my -= 0.018;
            }

            obstruction_factor = 0.0;
            int distLimit = 3 + (int) (velocity * 7.0);
            if (distLimit < 3) {
                distLimit = 3;
            }
            if (distLimit > 24) {
                distLimit = 24;
            }
            int iMax = Math.min(distLimit * 2, 48);
            for (int k = 1; k < distLimit; k++) {
                for (int i = 1; i < iMax; i++) {
                    double dx = i * Math.cos(Math.toRadians(this.getYRot() + 90.0f));
                    double dz = i * Math.sin(Math.toRadians(this.getYRot() + 90.0f));
                    BlockState scan =
                            this.level()
                                    .getBlockState(
                                            BlockPos.containing(
                                                    this.getX() + dx, this.getY() - k, this.getZ() + dz));
                    if (!scan.isAir()) {
                        obstruction_factor += 0.05;
                    }
                }
            }

            my += obstruction_factor * 0.07000000000000001;
            this.setPos(this.getX(), this.getY() + obstruction_factor * 0.07000000000000001, this.getZ());
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
            relative_g = (d4 - d5) % 180.0;
            while (relative_g < 0.0) {
                relative_g += 180.0;
            }
            if (relative_g > 90.0) {
                relative_g -= 180.0;
            }

            if (velocity > 0.01) {
                d4 = 1.85 - velocity;
                d4 = Math.abs(d4);
                if (d4 < 0.01) {
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

            double newvelocity = Math.sqrt(mx * mx + mz * mz);
            double rhm = Math.atan2(mz, mx);
            double rhdir = Math.toRadians((pp.getYRot() + 90.0f) % 360.0f);
            double im = pp.zza;

            boolean riderJumping =
                    pp instanceof LocalPlayer lp && lp.input.jumping || ChaosPersists.flyup_keystate != 0;
            if (riderJumping) {
                my += 0.03;
                my += velocity * 0.036;
            }

            double rdv = Math.abs(rhm - rhdir) % (pi * 2.0);
            if (rdv > pi) {
                rdv -= pi * 2.0;
            }
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01) {
                rdv = 0.0;
            }

            if (rdv > 1.5) {
                newvelocity = -newvelocity;
            }

            if (Math.abs(im) > 0.001) {
                if (im > 0.0) {
                    deltav = 0.025;
                    if (max_speed > 1.0) {
                        deltav += 0.05;
                    }
                    if (this.deltasmooth < 0.0f) {
                        this.deltasmooth = 0.0f;
                    }
                    this.deltasmooth = (float) (this.deltasmooth + deltav / 10.0);
                    if (this.deltasmooth > deltav) {
                        this.deltasmooth = (float) deltav;
                    }
                } else {
                    max_speed = 0.35;
                    deltav = -0.02;
                    if (this.deltasmooth > 0.0f) {
                        this.deltasmooth = 0.0f;
                    }
                    this.deltasmooth = (float) (this.deltasmooth + deltav / 10.0);
                    if (this.deltasmooth < deltav) {
                        this.deltasmooth = (float) deltav;
                    }
                }

                newvelocity += this.deltasmooth;
                if (newvelocity >= 0.0) {
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

            if (this.fireballticker == 0) {
                double xzoff = 4.0;
                double yoff = -0.25;

                if (this.getDragonType() == 0) {
                    if (pp.xxa > 0.001f) {
                        bf = new BetterFireball(this.level(), this, 0.0, 0.0, 0.0);
                        bf.setNotMe();
                        bf.setSmall();
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
                        bf.moveTo(cx, this.getY() + yoff, cz);
                        cx = Math.cos(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        cz = Math.sin(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        double cy = -Math.sin(Math.toRadians(pp.getXRot()));
                        double d3 = Math.sqrt(cx * cx + cy * cy + cz * cz);
                        bf.accelerationX = cx / d3 * 0.15;
                        bf.accelerationY = cy / d3 * 0.15;
                        bf.accelerationZ = cz / d3 * 0.15;
                        bf.setDeltaMovement(mx, my, mz);
                        bf.setPos(
                                bf.getX() - mx * 9.0,
                                bf.getY() - my * 9.0,
                                bf.getZ() - mz * 9.0);
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        SoundEvents.ARROW_SHOOT,
                                        SoundSource.NEUTRAL,
                                        0.75f,
                                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                        this.level().addFreshEntity(bf);
                        this.fireballticker = 10;
                    }
                    if (pp.xxa < -0.001f) {
                        bf = new BetterFireball(this.level(), this, 0.0, 0.0, 0.0);
                        bf.setNotMe();
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
                        bf.moveTo(cx, this.getY() + yoff, cz);
                        cx = Math.cos(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        cz = Math.sin(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        double cy = -Math.sin(Math.toRadians(pp.getXRot()));
                        double d3 = Math.sqrt(cx * cx + cy * cy + cz * cz);
                        bf.accelerationX = cx / d3 * 0.1;
                        bf.accelerationY = cy / d3 * 0.1;
                        bf.accelerationZ = cz / d3 * 0.1;
                        bf.setDeltaMovement(mx, my, mz);
                        bf.setPos(
                                bf.getX() - mx * 9.0,
                                bf.getY() - my * 9.0,
                                bf.getZ() - mz * 9.0);
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        SoundEvents.TNT_PRIMED,
                                        SoundSource.NEUTRAL,
                                        1.0f,
                                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                        this.level().addFreshEntity(bf);
                        this.fireballticker = 20;
                    }
                } else {
                    if (pp.xxa > 0.001f) {
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
                        WaterBall wb = new WaterBall(this.level(), cx, this.getY() + yoff, cz);
                        wb.moveTo(cx, this.getY() + yoff, cz, pp.getYRot() + 90.0f, pp.getXRot());
                        double var3 = Math.cos(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        double var5 = -Math.sin(Math.toRadians(pp.getXRot()));
                        double var77 = Math.sin(Math.toRadians(pp.getYHeadRot() + 90.0f));
                        float var9 = Mth.sqrt((float) (var3 * var3 + var77 * var77)) * 0.2f;
                        wb.shoot(var3, var5 + var9, var77, 1.4f, 5.0f);
                        wb.setPos(wb.getX() - mx * 7.0, wb.getY() - my * 7.0, wb.getZ() - mz * 7.0);
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        SoundEvents.ARROW_SHOOT,
                                        SoundSource.NEUTRAL,
                                        0.75f,
                                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                        this.level().addFreshEntity(wb);
                        this.fireballticker = 5;
                    }
                    if (pp.xxa < -0.001f) {
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
                        IceBall ib =
                                new IceBall(
                                        ChaosPersists.ENTITY_TYPE_ICE_BALL.get(),
                                        cx,
                                        this.getY() + yoff,
                                        cz,
                                        this.level());
                        ib.moveTo(cx, this.getY() + yoff, cz, pp.getYRot() + 90.0f, pp.getXRot());
                        ib.setSpecial();
                        ib.setIceBall();
                        double var3 = Math.cos(Math.toRadians(pp.getYRot() + 90.0f));
                        double var5 = -Math.sin(Math.toRadians(pp.getXRot()));
                        double var77 = Math.sin(Math.toRadians(pp.getYRot() + 90.0f));
                        float var9 = Mth.sqrt((float) (var3 * var3 + var77 * var77)) * 0.2f;
                        ib.shoot(var3, var5 + var9, var77, 1.4f, 5.0f);
                        ib.setPos(ib.getX() - mx * 7.0, ib.getY() - my * 7.0, ib.getZ() - mz * 7.0);
                        Vec3 ibdm = ib.getDeltaMovement();
                        ib.setDeltaMovement(ibdm.x * 2.0, ibdm.y * 2.0, ibdm.z * 2.0);
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        SoundEvents.FIREWORK_ROCKET_LAUNCH,
                                        SoundSource.NEUTRAL,
                                        0.75f,
                                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                        this.level().addFreshEntity(ib);
                        this.fireballticker = 15;
                    }
                }
            }

            this.setDeltaMovement(mx, my, mz);
            this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
            dm = this.getDeltaMovement();
            this.setDeltaMovement(dm.x * 0.985, dm.y * 0.94, dm.z * 0.985);

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

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (this.getPassengers().isEmpty()) {
            this.boatPosRotationIncrements = 0;
            this.noPhysics = false;
            this.setNoGravity(false);
            MyUtils.clearChaosFlight(this);
            if (!this.level().isClientSide) {
                this.owner_flying = 0;
                this.dismountCooldown = ChaosMountHelper.GROUND_DISMOUNT_COOLDOWN_TICKS;
                this.getNavigation().stop();
                if (this.lacksGroundSupport()) {
                    // 1.12: keep ambient flight after a mid-air dismount.
                    this.setActivity(1);
                } else {
                    this.setActivity(0);
                    ChaosMountHelper.finishDismountLanding(this);
                }
            }
        }
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

    @Override
    public boolean causeFallDamage(float distance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(STATE2, (byte) 0);
        this.entityData.define(INT22, 0);
        this.entityData.define(INT24, 1);
        this.setActivity(0);
        this.setAttacking(0);
        this.setTame(false);
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
        return 200;
    }

    public int getDragonHealth() {
        return (int)this.getHealth();
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

    @Override
    protected void jumpFromGround() {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.25, 0.0));
        super.jumpFromGround();
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = - dy; i <= dy; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (bid == Blocks.LAVA && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.LAVA
                        || (d = dx * dx + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j)).getBlock();
                if ((bid == Blocks.LAVA) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.LAVA
                        || (d = dy * dy + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dy; j <= dy; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz)).getBlock();
                if ((bid == Blocks.LAVA) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.LAVA
                        || (d = dz * dz + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        if (found != 0) {
            return true;
        }
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInSittingPose()) {
            return null;
        }
        if (this.getAttacking() == 1 && this.getPassengers().isEmpty()) {
            return ChaosSounds.ROAR;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.6f;
    }

    @Override
    public float getVoicePitch() {
        return 0.75f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public int getArmorValue() {
        return 14;
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
        this.level().addFreshEntity(var3);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 1 + this.getRandom().nextInt(6);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        double ks = 1.75;
        double inair = 0.1;
        float iskraken = 1.0f;
        if (par1Entity instanceof LivingEntity living) {
            if (par1Entity instanceof Kraken) {
                iskraken = 2.0f;
            }
            living.hurt(this.damageSources().mobAttack(this), iskraken * 35.0f);
            float f3 = (float) Math.atan2(living.getZ() - this.getZ(), living.getX() - this.getX());
            if (!living.isAlive() || par1Entity instanceof Player) {
                inair *= 2.0;
            }
            living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (par1DamageSource.is(DamageTypes.CACTUS)) {
            return ret;
        }
        if (par1DamageSource.is(DamageTypes.IN_FIRE)) {
            return ret;
        }
        if (par1DamageSource.is(DamageTypes.ON_FIRE)) {
            return ret;
        }
        if (par1DamageSource.is(DamageTypes.LAVA)) {
            return ret;
        }
        if (par1DamageSource.is(DamageTypes.IN_WALL)) {
            return ret;
        }
        this.setOrderedToSit(false);
        this.setActivity(1);
        e = par1DamageSource.getEntity();
        if (e instanceof BetterFireball && this.dragontype == 0) {
            e.discard();
            return ret;
        }
        if (e instanceof IceBall && this.dragontype != 0) {
            e.discard();
            return ret;
        }
        if (e instanceof WaterBall && this.dragontype != 0) {
            e.discard();
            return ret;
        }
        if (e instanceof SmallFireball && this.dragontype == 0) {
            e.discard();
            return ret;
        }
        if (e instanceof Dragon) {
            return false;
        }
        if (e instanceof Spyro) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.hurt_timer = 20;
        }
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (this.isTame() && e instanceof Player) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
            ret = true;
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        PetCombatHelper.tickPetCombat(this);
        if (!this.getPassengers().isEmpty()) {
            return;
        }
        // 1.12: skip wander/follow while ambient-flying or airborne so follow-owner teleport
        // does not snap flyers back to the owner every few seconds.
        if (this.getActivity() != 0 || this.lacksGroundSupport()) {
            return;
        }
        super.customServerAiStep();
        LivingEntity e;
        if (!this.isStayingPut()
                && this.getActivity() == 0
                && this.getPassengers().isEmpty()
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(10) == 1
                && (e =
                        PetCombatHelper.resolveCombatTarget(
                                this, this.getTarget(), this::findSomethingToAttack))
                        != null) {
            if (e != this.getTarget()) {
                this.setTarget(e);
            }
            this.setActivity(1);
        }
    }

    public void always_do() {
        Player p = null;
        if (this.dismountCooldown > 0 && this.getPassengers().isEmpty()) {
            this.owner_flying = 0;
            MyUtils.clearChaosFlight(this);
            if (this.getActivity() == 0) {
                this.setNoGravity(false);
                this.noPhysics = false;
                ChaosMountHelper.applyGroundGravityWhenIdle(this);
            }
            return;
        }
        if (this.getRandom().nextInt(250) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        if (this.isStayingPut()) {
            return;
        }
        this.owner_flying = 0;
        if (this.isTame()
                && this.getOwner() != null
                && this.getPassengers().isEmpty()
                && !this.isStayingPut()) {
            p = (Player) this.getOwner();
            if (p.getAbilities().flying) {
                this.owner_flying = 1;
                this.setActivity(1);
            }
        }
        if (this.isTame()
                && this.getOwner() != null
                && !this.isStayingPut()
                && this.distanceToSqr((p = (Player) this.getOwner())) > 400.0) {
            this.setActivity(1);
        }
        if (this.getRandom().nextInt(50) == 1
                && !this.isStayingPut()
                && !this.target_in_sight
                && this.getPassengers().isEmpty()) {
            if (this.getRandom().nextInt(15) == 1) {
                this.setActivity(1);
            } else {
                this.setActivity(0);
            }
        }
        if (this.getRandom().nextInt(25) == 0 && !this.target_in_sight && this.getPassengers().isEmpty()) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 11; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() - 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 6) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.setActivity(0);
                this.getNavigation().moveTo(this.tx, this.ty - 1, this.tz, 1.0);
                if (this.isInLava()) {
                    this.heal(1.0f);
                    this.playSound(
                            SoundEvents.GENERIC_SPLASH,
                            1.0f,
                            this.getRandom().nextFloat() * 0.2f + 0.9f);
                }
            }
        }
    }

    public void fly_with_rider() {
        LivingEntity e;
        int freq = 7;
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.isStayingPut()) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.getRandom().nextInt(freq) == 1 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            if (this.getRandom().nextInt(250) == 0) {
                this.setTarget(null);
            }
            LivingEntity prior = this.getTarget();
            e = PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
            if (e != prior) {
                this.setTarget(e);
            }
            if (e != null) {
                this.setAttacking(1);
                if (this.distanceToSqr(e)
                        < (double)
                                ((7.0f + e.getBbWidth() / 2.0f) * (7.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget(e);
                }
                return;
            }
            this.setAttacking(0);
        }
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
        String cn = par1EntityLiving.getClass().getSimpleName();
        if (cn.equals("LurkingTerror")
                || cn.equals("EnderReaper")
                || cn.equals("TerribleTerror")
                || cn.equals("LeafMonster")
                || cn.equals("CreepingHorror")
                || cn.equals("Triffid")) {
            return false;
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof Kraken) {
            return true;
        }
        if (par1EntityLiving instanceof Player) {
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
                                LivingEntity.class, this.getBoundingBox().inflate(20.0, 20.0, 20.0));
        Collections.sort(var5, this.targetSorter);
        for (LivingEntity var4 : var5) {
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        List<Dragon> nearby =
                this.level()
                        .getEntitiesOfClass(
                                Dragon.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0), ent -> ent != this);
        if (!nearby.isEmpty()) {
            return false;
        }
        if (level instanceof Level world && world.dimension().equals(ChaosPersists.getDimensionKey(4))) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    public static boolean checkDragonSpawnRules(
            EntityType<Dragon> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(4))) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        List<Dragon> nearby =
                level.getLevel()
                        .getEntitiesOfClass(
                                Dragon.class,
                                new AABB(pos).inflate(16.0, 6.0, 16.0));
        return nearby.isEmpty();
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(
            double par1, double par3, double par5, float par7, float par8, int par9, boolean interpolate) {
        if (this.isControlledByLocalInstance()) {
            this.boatPosRotationIncrements = 0;
            return;
        }
        if (this.getPassengers().isEmpty()) {
            super.lerpTo(par1, par3, par5, par7, par8, par9, interpolate);
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpMotion(double par1, double par3, double par5) {
        if (!this.isControlledByLocalInstance()) {
            super.lerpMotion(par1, par3, par5);
        }
    }

    @Override
    public void tick() {
        LivingEntity e;
        if (this.isDeadOrDying()) {
            this.noPhysics = false;
            if (!this.level().isClientSide) {
                this.setNoGravity(false);
            }
            MyUtils.clearChaosFlight(this);
            super.tick();
            return;
        }
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        if (this.isStayingPut() && this.getPassengers().isEmpty()) {
            if (this.getActivity() != 0) {
                this.setActivity(0);
            }
            this.owner_flying = 0;
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
        }
        if (this.getPassengers().isEmpty()) {
            if (this.getActivity() == 0) {
                this.noPhysics = false;
                this.setNoGravity(false);
                MyUtils.clearChaosFlight(this);
            }
        } else if (!this.level().isClientSide) {
            this.setActivity(1);
        }
        if (!this.level().isClientSide
                && this.getActivity() != 0
                && this.getPassengers().isEmpty()
                && !this.isStayingPut()) {
            this.fly_without_rider();
        }
        super.tick();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.getActivity() == 1) {
            ++this.wing_sound;
            if (this.wing_sound > 20) {
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
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.07, 0.0));
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.getActivity() == 0
                && this.isTame()
                && this.getOwner() != null
                && !this.isStayingPut()
                && this.dismountCooldown <= 0
                && this.distanceToSqr((e = this.getOwner())) > 144.0) {
            this.setActivity(1);
        }
        this.applyLandingFallIfNeeded();
    }

    private void fly_without_rider() {
        Vec3 dm = this.getDeltaMovement();
        double mx = dm.x;
        double my = dm.y;
        double mz = dm.z;
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        LivingEntity e = null;
        double speed_factor = 0.5;
        double var1 = 0.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double yoff = 1.25;
        double xzoff = 2.25;
        double gh = 1.25;
        double obstruction_factor = 0.0;
        SmallFireball sf = null;
        BetterFireball bf = null;
        IceBall ib = null;
        WaterBall wb = null;
        boolean toofar = false;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.isStayingPut()) {
            return;
        }
        if ((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) != null) {
            return;
        }
        if (this.unstick_timer > 0) {
            --this.unstick_timer;
        }
        if (this.lastX == (int)this.getX() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
            if (this.stuck_count > 50) {
                this.stuck_count = 0;
                this.unstick_timer = 100;
                this.target_in_sight = false;
                this.setAttacking(0);
                do_new = true;
            }
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastZ = (int)this.getZ();
        }
        if (this.getY() < (double) this.currentFlightTarget.getY() + 2.0) {
            my *= 0.7;
        } else if (this.getY() > (double) this.currentFlightTarget.getY() - 2.0) {
            my *= 0.5;
        } else {
            my *= 0.61;
        }
        if (this.getRandom().nextInt(300) == 1) {
            do_new = true;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr(e) > 144.0) {
                toofar = true;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.flyaway = 0;
                do_new = true;
            }
        }
        if (this.flyaway > 0) {
            --this.flyaway;
        }
        if (!toofar && this.unstick_timer == 0 && this.flyaway == 0 && this.level().getDifficulty() != Difficulty.PEACEFUL && this.getRandom().nextInt(9) == 1) {
            LivingEntity prior = this.getTarget();
            e = PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
            if (e != prior) {
                this.setTarget(e);
            }
            if (e != null) {
                if (this.isTame() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25f) {
                    this.setActivity(1);
                    this.setAttacking(0);
                    this.target_in_sight = false;
                    do_new = false;
                    this.currentFlightTarget = new BlockPos((int)(this.getX() + (this.getX() - e.getX())), (int)(this.getY() + 1.0), (int)(this.getZ() + (this.getZ() - e.getZ())));
                } else {
                    this.setActivity(1);
                    this.setAttacking(1);
                    this.target_in_sight = true;
                    this.currentFlightTarget = new BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                    do_new = false;
                    if (this.distanceToSqr(e) < (double)((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget((Entity)e);
                        this.flyaway = 5 + this.getRandom().nextInt(10);
                        do_new = true;
                    } else if (this.distanceToSqr(e) < 256.0 && !this.isInWater() && this.getDragonFire() >= 1) {
                        double var7;
                        float var9;
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
                        if (this.dragontype == 0) {
                            if (this.getDragonFire() == 1) {
                                sf =
                                        new SmallFireball(
                                                this.level(),
                                                this,
                                                e.getX() - cx,
                                                e.getY() + (double) (e.getBbHeight() / 2.0f)
                                                        - (this.getY() + yoff),
                                                e.getZ() - cz);
                                sf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                                this.level()
                                        .playSound(
                                                null,
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                SoundEvents.ARROW_SHOOT,
                                                SoundSource.NEUTRAL,
                                                0.75f,
                                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                                this.level().addFreshEntity(sf);
                            } else {
                                bf =
                                        new BetterFireball(
                                                this.level(),
                                                this,
                                                e.getX() - cx,
                                                e.getY() + (double) (e.getBbHeight() / 2.0f)
                                                        - (this.getY() + yoff),
                                                e.getZ() - cz);
                                bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                                this.level()
                                        .playSound(
                                                null,
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                SoundEvents.TNT_PRIMED,
                                                SoundSource.NEUTRAL,
                                                1.0f,
                                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                                this.level().addFreshEntity(bf);
                            }
                        } else if (this.getDragonFire() == 1) {
                            wb =
                                    new WaterBall(
                                            this.level(),
                                            e.getX() - this.getX(),
                                            e.getY() + (double) (e.getBbHeight() / 2.0f)
                                                    - (this.getY() + yoff),
                                            e.getZ() - this.getZ());
                            wb.moveTo(
                                    this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot())),
                                    this.getY() + yoff,
                                    this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                                    this.getYHeadRot(),
                                    this.getXRot());
                            var3 = e.getX() - wb.getX();
                            var5 = e.getY() + 0.25 - wb.getY();
                            var7 = e.getZ() - wb.getZ();
                            var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                            wb.shoot(var3, var5 + (double) var9, var7, 1.4f, 5.0f);
                            this.level()
                                    .playSound(
                                            null,
                                            this.getX(),
                                            this.getY(),
                                            this.getZ(),
                                            SoundEvents.ARROW_SHOOT,
                                            SoundSource.NEUTRAL,
                                            0.75f,
                                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                            this.level().addFreshEntity(wb);
                        } else {
                            ib =
                                    new IceBall(
                                            ChaosPersists.ENTITY_TYPE_ICE_BALL.get(),
                                            e.getX() - this.getX(),
                                            e.getY() + (double) (e.getBbHeight() / 2.0f)
                                                    - (this.getY() + yoff),
                                            e.getZ() - this.getZ(),
                                            this.level());
                            ib.moveTo(
                                    this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot())),
                                    this.getY() + yoff,
                                    this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                                    this.getYHeadRot(),
                                    this.getXRot());
                            ib.setSpecial();
                            ib.setIceBall();
                            var3 = e.getX() - ib.getX();
                            var5 = e.getY() + 0.25 - ib.getY();
                            var7 = e.getZ() - ib.getZ();
                            var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                            ib.shoot(var3, var5 + (double) var9, var7, 1.4f, 5.0f);
                            this.level()
                                    .playSound(
                                            null,
                                            this.getX(),
                                            this.getY(),
                                            this.getZ(),
                                            SoundEvents.TNT_PRIMED,
                                            SoundSource.NEUTRAL,
                                            1.0f,
                                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                            this.level().addFreshEntity(ib);
                        }
                    }
                }
            } else {
                this.target_in_sight = false;
                this.flyaway = 0;
                this.setAttacking(0);
            }
        }
        if (this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1) {
            do_new = true;
        }
        if (do_new && !this.target_in_sight || do_new && this.flyaway != 0) {
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int)this.getX();
                int goy = (int)this.getY();
                int goz = (int)this.getZ();
                if (has_owner && this.unstick_timer == 0) {
                    gox = (int)ox;
                    goy = (int)oy;
                    goz = (int)oz;
                    if (this.owner_flying == 0) {
                        zdir = this.getRandom().nextInt(10) + 4;
                        xdir = this.getRandom().nextInt(10) + 4;
                    } else {
                        zdir = this.getRandom().nextInt(6);
                        xdir = this.getRandom().nextInt(6);
                    }
                } else {
                    zdir = this.getRandom().nextInt(10) + 16;
                    xdir = this.getRandom().nextInt(10) + 16;
                }
                if (this.getRandom().nextInt(2) == 1) {
                    zdir = - zdir;
                }
                if (this.getRandom().nextInt(2) == 1) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new BlockPos(gox + xdir, goy + this.getRandom().nextInt(9 + this.owner_flying * 2) - 4, goz + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        obstruction_factor = 0.0;
        // Match ThePrinceTeen: horizontal speed for kMax after targeting/damping, not at method entry.
        double velocity = Math.sqrt(mx * mx + mz * mz);
        // Match ThePrinceTeen: small inner loop only — large scanDist*2 loops stacked huge Y boosts (random "rocket up").
        int kMax = 2 + (int)(Math.max(0.0, velocity) * 4.0);
        if (kMax < 2) {
            kMax = 2;
        }
        if (kMax > 24) {
            kMax = 24;
        }
        for (int k = 1; k < kMax; ++k) {
            for (int i = 1; i < 4; ++i) {
                double dz;
                double dx = (double)i * Math.cos(Math.toRadians(this.getYRot() + 90.0f));
                bid = this.level().getBlockState(new BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.getYRot() + 90.0f)))))).getBlock();
                if (bid == Blocks.AIR) continue;
                obstruction_factor += 0.05;
            }
        }
        my += obstruction_factor * 0.05;
        this.setPos(this.getX(), this.getY() + obstruction_factor * 0.05, this.getZ());
        speed_factor = 0.5;
        var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        if (this.owner_flying != 0) {
            speed_factor = 1.75;
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr((e = this.getOwner())) > 49.0) {
                speed_factor = 3.5;
            }
        }
        mx += (Math.signum(var1) - mx) * 0.15 * speed_factor;
        my += (Math.signum(var3) - my) * 0.21 * speed_factor;
        mz += (Math.signum(var5) - mz) * 0.15 * speed_factor;
        float var7 = (float)(Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = Mth.wrapDegrees((float)(var7 - this.getYRot()));
        this.zza = (float) (0.75 * speed_factor);
        this.setYRot(this.getYRot() + var8 / 4.0f);
        this.setDeltaMovement(mx, my, mz);
        MyUtils.applyChaosFlightMovement(this);
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        super.aiStep();
        if (this.level().isClientSide) {
            if (this.getActivity() != 0 && !this.getPassengers().isEmpty()) {
                Entity rider = this.getPassengers().get(0);
                if (rider instanceof LocalPlayer pp) {
                    pp.connection.send(
                            new ServerboundMovePlayerPacket.Rot(pp.getYRot(), pp.getXRot(), pp.onGround()));
                    pp.connection.send(
                            new ServerboundPlayerInputPacket(
                                    pp.xxa, pp.zza, pp.input.jumping, pp.input.shiftKeyDown));
                }
            }
            if (this.boatPosRotationIncrements > 0
                    && this.getActivity() != 0
                    && !this.isControlledByLocalInstance()) {
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
                if (!this.getPassengers().isEmpty()) {
                    d10 =
                            Mth.wrapDegrees(
                                    (double) this.getPassengers().get(0).getYRot() - (double) this.getYRot());
                }
                this.setYRot((float) ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            }
        } else {
            if (this.getActivity() != 0 && !this.getPassengers().isEmpty()) {
                this.fly_with_rider();
            }
            this.always_do();
        }
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!this.isTame()) {
            if (!var2.isEmpty() && var2.is(Items.BEEF) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(5) == 1) {
                        this.setTame(true);
                        this.setOwnerUUID(par1EntityPlayer.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        } else {
            if (!this.isOwnedBy(par1EntityPlayer)) {
                return super.mobInteract(par1EntityPlayer, hand);
            }
            if (var2.isEmpty()
                    && ChaosMountHelper.canPlayerMount(par1EntityPlayer, this, this.dismountCooldown)) {
                if (!this.level().isClientSide) {
                    par1EntityPlayer.startRiding(this);
                    this.setActivity(1);
                    ChaosMountHelper.onPlayerMounted(this);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (!var2.isEmpty()
                    && var2.is(Items.STICK)
                    && (par1EntityPlayer.distanceToSqr(this) < 64.0
                            || this.getBoundingBox().inflate(3.0).contains(par1EntityPlayer.position()))) {
                if (!this.level().isClientSide) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (!var2.isEmpty() && var2.is(Items.BEEF) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty()
                    && var2.is(Blocks.DEAD_BUSH.asItem())
                    && par1EntityPlayer.distanceToSqr(this) < 25.0) {
                if (!this.level().isClientSide) {
                    this.setTame(false);
                    this.setOwnerUUID(null);
                    spawnTamingParticles(false);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty()
                    && var2.is(Blocks.ICE.asItem())
                    && par1EntityPlayer.distanceToSqr(this) < 25.0
                    && this.isOwnedBy(par1EntityPlayer)) {
                if (!this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                    this.setDragonFire(0);
                    par1EntityPlayer.displayClientMessage(
                            Component.literal("Dragon fireballs extinguished."), true);
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty()
                    && var2.is(Items.FLINT_AND_STEEL)
                    && par1EntityPlayer.distanceToSqr(this) < 25.0
                    && this.isOwnedBy(par1EntityPlayer)) {
                if (!this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                    this.setDragonFire(1);
                    par1EntityPlayer.displayClientMessage(
                            Component.literal("Dragon fireballs lit!"), true);
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty()
                    && var2.is(Items.GUNPOWDER)
                    && par1EntityPlayer.distanceToSqr(this) < 25.0
                    && this.isOwnedBy(par1EntityPlayer)
                    && this.getDragonFire() > 0) {
                if (!this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                    this.setDragonFire(2);
                    par1EntityPlayer.displayClientMessage(
                            Component.literal("Dragon fireballs supercharged!"), true);
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty() && var2.is(Items.SNOWBALL) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                this.dragontype = 1;
                this.setDragonType(this.dragontype);
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty() && var2.is(Items.COAL) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                this.dragontype = 0;
                this.setDragonType(this.dragontype);
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (!var2.isEmpty()
                    && var2.is(Items.DIAMOND)
                    && par1EntityPlayer.distanceToSqr(this) < 25.0
                    && this.isOwnedBy(par1EntityPlayer)
                    && !this.level().isClientSide) {
                Entity ent =
                        spawnCreature(
                                this.level(),
                                "baby_dragon",
                                this.getX(),
                                this.getY(),
                                this.getZ());
                if (ent instanceof Spyro d) {
                    if (this.isTame()) {
                        d.setTame(true);
                        d.setOwnerUUID(par1EntityPlayer.getUUID());
                    }
                    this.discard();
                }
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
            if (this.isTame()
                    && !var2.isEmpty()
                    && var2.is(Items.NAME_TAG)
                    && par1EntityPlayer.distanceToSqr(this) < 25.0
                    && this.isOwnedBy(par1EntityPlayer)) {
                this.setCustomName(var2.getHoverName());
                if (!par1EntityPlayer.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(par1EntityPlayer, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.BEEF);
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int getActivity() {
        return this.entityData.get(STATE2).byteValue();
    }

    public void setActivity(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        this.entityData.set(STATE2, (byte) par1);
        if (par1 == 0) {
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
        }
    }

    public int getDragonFire() {
        return this.entityData.get(INT24);
    }

    public void setDragonFire(int par1) {
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(INT24, par1);
    }

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation(
                                "chaospersists", par1.toLowerCase().replace(" ", "_"));
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    public void setDragonType(int par1) {
        this.entityData.set(INT22, par1);
    }

    public int getDragonType() {
        return this.entityData.get(INT22);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.getPassengers().isEmpty()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    @Override
    public void addAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("DragonAttacking", this.getAttacking());
        tag.putInt("DragonActivity", this.getActivity());
        tag.putInt("DragonFire", this.getDragonFire());
        tag.putInt("DragonType", this.getDragonType());
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setAttacking(tag.getInt("DragonAttacking"));
        this.setActivity(tag.getInt("DragonActivity"));
        this.setDragonFire(tag.getInt("DragonFire"));
        this.dragontype = tag.getInt("DragonType");
        this.setDragonType(this.dragontype);
    }
}

