package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.RoyalPetFollowHelper;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.core.BlockPos;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.Difficulty;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.client.player.LocalPlayer;

public class ThePrinceAdult extends TamableAnimal {
    private static final EntityDataAccessor<Integer> ATTACKING =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ACTIVITY =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAD1_EXT =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAD2_EXT =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> THE_PRINCE_ADULT_FIRE =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HEAD3_EXT =
            SynchedEntityData.defineId(ThePrinceAdult.class, EntityDataSerializers.INT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wing_sound = 0;
    private BlockPos currentFlightTarget = null;
    private boolean target_in_sight = false;
    private int owner_flying = 0;
    private int flyaway = 0;
    private float moveSpeed = 0.36f;
    private float deltasmooth = 0.0f;
    private int which_attack = 0;
    private int fireballticker = 0;
    private int head1ext = 0;
    private int head2ext = 0;
    private int head3ext = 0;
    private int head1dir = 1;
    private int head2dir = 1;
    private int head3dir = 1;
    private int growcounter = 0;
    private int dismountCooldown = 0;

    public ThePrinceAdult(EntityType<? extends ThePrinceAdult> type, Level level) {
        super(type, level);
        this.fireImmune();
        this.xpReward = 3000;
        this.moveSpeed = 0.36f;
        this.setOrderedToSit(false);
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner(this, 1.15f, 12.0f, 2.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, LivingEntity.class, 20.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true, false));
        }
        this.targetSelector.addGoal(2, new ChaosHurtByTargetGoal(this));
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3000.0)
                .add(Attributes.MOVEMENT_SPEED, 0.36)
                .add(Attributes.ATTACK_DAMAGE, 100.0);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(6.25f, 10.25f);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.refreshDimensions();
    }

    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (!this.level().isClientSide) {
            if (orderedToSit) {
                this.setActivity(0);
                this.setAttacking(0);
                this.setTarget(null);
                this.setLastHurtByMob(null);
                MyUtils.clearChaosFlight(this);
                this.setNoGravity(false);
                this.noPhysics = false;
                if (this.getNavigation() != null) {
                    this.getNavigation().stop();
                }
                this.setDeltaMovement(Vec3.ZERO);
            }
        }
    }

    public int getTrackingRange() {
        return 64;
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
        return 9.05;
    }

    public double getMountedYOffset() {
        return 9.05;
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float f = 4.65f;
            double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
            double y = this.getY() + this.getPassengersRidingOffset() + passenger.getMyRidingOffset();
            double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
            moveFunction.accept(passenger, x, y, z);
        } else {
            super.positionRider(passenger, moveFunction);
        }
    }

    public boolean canBeSteered() {
        return true;
    }

    public boolean canPassengerSteer() {
        return true;
    }

    /**
     * Same pipeline as {@link ThePrinceAdult} / {@link Cephadrome}: vanilla calls {@code travel} with rider
     * input synced from the client via {@code ServerboundPlayerInputPacket}. Flight must run here, not only in
     * {@code aiStep}, or {@code zza} stays zero on the server and the mount cannot fly.
     */
    @Override
    public void travel(Vec3 travelVector) {
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
            double max_speed = 1.05;
            double gh = 1.25;
            double deltav;

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

            this.setYRot(pp.getYRot());
            this.setXRot(2.0f * (float) velocity);
            this.setRot(this.getYRot(), this.getXRot());
            this.setYHeadRot(this.getYRot());

            double newvelocity = Math.sqrt(mx * mx + mz * mz);
            double rhm = Math.atan2(mz, mx);
            double rhdir = Math.toRadians((pp.getYRot() + 90.0f) % 360.0f);
            double rdv;
            if ((rdv = Math.abs(rhm - rhdir) % (Math.PI * 2.0)) > Math.PI) {
                rdv -= Math.PI * 2.0;
            }
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01) {
                rdv = 0.0;
            }
            if (rdv > 1.5) {
                newvelocity = -newvelocity;
            }
            double im = pp.zza;

            boolean riderJumping =
                    pp instanceof LocalPlayer lp && lp.input.jumping || ChaosPersists.flyup_keystate != 0;
            if (ChaosPersists.flyup_keystate != 0) {
                my += 0.035;
                my += velocity * 0.046;
            } else if (riderJumping) {
                my += 0.06;
            } else if (pp.getXRot() > 45.0f && pp.zza > 0.0f) {
                my -= 0.05;
            } else {
                my *= 0.91;
                if (Math.abs(my) < 0.05) {
                    my = 0.0;
                }
            }

            if (Math.abs(im) > 0.001) {
                if (im > 0.0) {
                    deltav = 0.028;
                    if (max_speed > 1.0) {
                        deltav += 0.06;
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

            if (!this.level().isClientSide
                    && this.fireballticker == 0
                    && (pp.xxa < -0.001f || pp.xxa > 0.001f)) {
                this.princeAdultRiderStrafeAttack(pp, mx, my, mz);
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
                                        this.getBoundingBox().inflate(6.25, 10.0, 6.25),
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
        if (!this.level().isClientSide && this.getPassengers().isEmpty()) {
            this.finishDismountLanding();
        }
    }

    private void finishDismountLanding() {
        this.dismountCooldown = 80;
        this.setActivity(0);
        this.owner_flying = 0;
        this.setNoGravity(false);
        this.noPhysics = false;
        Vec3 dm = this.getDeltaMovement();
        this.setDeltaMovement(dm.x, Math.min(dm.y, -0.25), dm.z);
        MyUtils.enforceDragonMountGroundSafety(this);
    }

    private void princeAdultRiderStrafeAttack(Player pp, double mx, double my, double mz) {
        double cx;
        double cz;
        double yoff = 9.5;
        double xzoff = 14.5;
        ++this.which_attack;
        if (this.which_attack > 2) {
            this.which_attack = 0;
        }
        if (this.which_attack == 0) {
            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot() - 10.0f));
            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot() - 10.0f));
            BetterFireball bf = new BetterFireball(this.level(), this, 0.0, 0.0, 0.0);
            bf.setNotMe();
            bf.setBig();
            yoff -= (double) ((float) this.getHead1Ext() * 0.08f);
            bf.moveTo(cx, this.getY() + yoff, cz);
            cx = Math.cos(Math.toRadians(pp.getYHeadRot() + 90.0f));
            cz = Math.sin(Math.toRadians(pp.getYHeadRot() + 90.0f));
            double cy = -Math.sin(Math.toRadians(pp.getXRot()));
            double d3 = Math.sqrt(cx * cx + cy * cy + cz * cz);
            if (d3 > 0.0) {
                bf.accelerationX = cx / d3 * 0.1;
                bf.accelerationY = cy / d3 * 0.1;
                bf.accelerationZ = cz / d3 * 0.1;
            }
            bf.setDeltaMovement(mx, my, mz);
            bf.setPos(bf.getX() - mx * 3.0, bf.getY() - my * 3.0, bf.getZ() - mz * 3.0);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.TNT_PRIMED,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(bf);
        }
        if (this.which_attack == 1) {
            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot() + 10.0f));
            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot() + 10.0f));
            yoff -= (double) ((float) this.getHead3Ext() * 0.08f);
            IceBall var2 =
                    new IceBall(
                            ChaosPersists.ENTITY_TYPE_ICE_BALL.get(), cx, this.getY() + yoff, cz, this.level());
            var2.moveTo(cx, this.getY() + yoff, cz, pp.getYRot() + 90.0f, pp.getXRot());
            var2.setIceMaker(1);
            double var3 = Math.cos(Math.toRadians(pp.getYRot() + 90.0f));
            double var5 = -Math.sin(Math.toRadians(pp.getXRot()));
            double var77 = Math.sin(Math.toRadians(pp.getYRot() + 90.0f));
            float var9 = Mth.sqrt((float) (var3 * var3 + var77 * var77)) * 0.2f;
            var2.shoot(var3, var5 + (double) var9, var77, 1.4f, 5.0f);
            var2.setPos(var2.getX() - mx * 3.0, var2.getY() - my * 3.0, var2.getZ() - mz * 3.0);
            Vec3 ibdm = var2.getDeltaMovement();
            var2.setDeltaMovement(ibdm.x * 2.0, ibdm.y * 2.0, ibdm.z * 2.0);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.FIREWORK_ROCKET_LAUNCH,
                            this.getSoundSource(),
                            0.75f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(var2);
        }
        if (this.which_attack == 2) {
            cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
            cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
            yoff -= (double) ((float) this.getHead2Ext() * 0.08f);
            ThunderBolt lb =
                    new ThunderBolt(
                            ChaosPersists.ENTITY_TYPE_THUNDER_BOLT.get(), pp, this.level());
            lb.moveTo(cx, this.getY() + yoff, cz, pp.getYRot() + 90.0f, pp.getXRot());
            Vec3 lbdm = lb.getDeltaMovement();
            lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            this.getSoundSource(),
                            0.75f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(lb);
        }
        this.fireballticker = 8;
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
        this.entityData.define(ATTACKING, 0);
        this.entityData.define(ACTIVITY, 0);
        this.entityData.define(THE_PRINCE_ADULT_FIRE, 1);
        this.entityData.define(HEAD1_EXT, 0);
        this.entityData.define(HEAD2_EXT, 0);
        this.entityData.define(HEAD3_EXT, 0);
        this.setActivity(0);
        this.setAttacking(0);
        this.setThePrinceAdultFire(1);
        this.setTame(false);
        this.noPhysics = false;
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
        return 3000;
    }

    public int getThePrinceAdultHealth() {
        return (int) this.getHealth();
    }

    public int getHead1Ext() {
        return this.entityData.get(HEAD1_EXT);
    }

    public int getHead2Ext() {
        return this.entityData.get(HEAD2_EXT);
    }

    public int getHead3Ext() {
        return this.entityData.get(HEAD3_EXT);
    }

    public void setHead1Ext(int par1) {
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(HEAD1_EXT, par1);
    }

    public void setHead2Ext(int par1) {
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(HEAD2_EXT, par1);
    }

    public void setHead3Ext(int par1) {
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(HEAD3_EXT, par1);
    }

    public int getThePrinceAdultFire() {
        return this.entityData.get(THE_PRINCE_ADULT_FIRE);
    }

    public void setThePrinceAdultFire(int par1) {
        if (this.level().isClientSide) {
            return;
        }
        this.entityData.set(THE_PRINCE_ADULT_FIRE, par1);
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
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.35, 0.0));
        super.jumpFromGround();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (RoyalPetFollowHelper.isStayingPut(this)) {
            return null;
        }
        if (this.getActivity() == 1 && !this.onGround() && this.getPassengers().isEmpty()) {
            return ChaosSounds.KING_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.KING_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.TREX_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.85f;
    }

    @Override
    public float getVoicePitch() {
        return 1.1f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public int getArmorValue() {
        return 20;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        is);
        this.level().addFreshEntity(var3);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        if (ChaosPersists.ThePrinceEgg != null) {
            this.dropItemRand(ChaosPersists.ThePrinceEgg, 1);
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        double ks = 2.0;
        double inair = 0.2;
        float iskraken = 1.0f;
        if (par1Entity instanceof LivingEntity living) {
            if (par1Entity instanceof Kraken) {
                iskraken = 2.0f;
            }
            living.hurt(this.damageSources().mobAttack(this), iskraken * 100.0f);
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
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
                this.setActivity(1);
            }
            return ret;
        }
        e = par1DamageSource.getEntity();
        if (e instanceof BetterFireball bf && bf.shootingEntity == this) {
            return false;
        }
        if (par1DamageSource.getDirectEntity() instanceof BetterFireball bf2 && bf2.shootingEntity == this) {
            return false;
        }
        if (!this.level().isClientSide) {
            this.setOrderedToSit(false);
            this.setActivity(1);
        }
        e = par1DamageSource.getEntity();
        if (e instanceof BetterFireball) {
            e.discard();
            return ret;
        }
        if (e instanceof SmallFireball) {
            e.discard();
            return ret;
        }
        if (e instanceof ThePrinceAdult) {
            return false;
        }
        if (e instanceof Spyro) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        this.hurt_timer = 20;
        if (e instanceof LivingEntity living && !this.level().isClientSide && MyUtils.isValidAggroTarget(living)) {
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
        if (this.isTame() && !RoyalPetFollowHelper.isStayingPut(this)) {
            RoyalPetFollowHelper.syncDimensionOnly(this);
        }
        LivingEntity e;
        if (this.getActivity() == 0 && this.getPassengers().isEmpty()) {
            super.customServerAiStep();
        }
        if (!RoyalPetFollowHelper.isStayingPut(this)
                && this.dismountCooldown == 0
                && this.getActivity() == 0
                && this.getPassengers().isEmpty()
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(10) == 1) {
            e = this.findSomethingToAttack();
            if (e != null) {
                LivingEntity owner = this.getOwner();
                if (!this.isTame()
                        || owner == null
                        || this.distanceToSqr(owner) > 256.0) {
                    this.setActivity(1);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getActivity() == 0
                && this.getPassengers().isEmpty()
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.isTame()
                && ChaosPersists.FullPowerKingEnable != 0) {
            ++this.growcounter;
            if (this.growcounter > 288000) {
                Entity ent =
                        spawnCreature(
                                this.level(),
                                "The King",
                                this.getX(),
                                this.getY(),
                                this.getZ());
                if (ent != null) {
                    callKingSetFree(ent);
                    this.discard();
                }
            }
        }
    }

    public void always_do() {
        if (this.getRandom().nextInt(250) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(5.0f);
        }
        if (this.getRandom().nextInt(250) == 0) {
            this.setTarget(null);
        }
        if (RoyalPetFollowHelper.isStayingPut(this)) {
            if (!this.level().isClientSide) {
                this.setNoGravity(false);
                this.noPhysics = false;
                if (this.getNavigation() != null) {
                    this.getNavigation().stop();
                }
            }
            return;
        }
        this.owner_flying = 0;
        if (this.isTame()
                && this.getOwner() != null
                && this.getPassengers().isEmpty()
                && !RoyalPetFollowHelper.isStayingPut(this)
                && this.getOwner() instanceof Player owner
                && owner.getAbilities().flying) {
            this.owner_flying = 1;
            this.setActivity(1);
        }
        if (this.getRandom().nextInt(50) == 1
                && !RoyalPetFollowHelper.isStayingPut(this)
                && !this.target_in_sight
                && this.getPassengers().isEmpty()) {
            if (MyUtils.isPrinceAirborne(this)) {
                this.setActivity(1);
            } else if (this.getRandom().nextInt(15) == 1) {
                this.setActivity(1);
            } else {
                this.setActivity(0);
            }
        }
    }

    public void fly_with_rider() {
        LivingEntity e;
        if (this.isDeadOrDying()) {
            return;
        }
        if (RoyalPetFollowHelper.isStayingPut(this)) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.getRandom().nextInt(5) == 1 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            e = this.findSomethingToAttack();
            if (e != null) {
                this.setAttacking(1);
                if (this.distanceToSqr(e)
                        < (double) ((10.0f + e.getBbWidth() / 2.0f) * (10.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget(e);
                } else if (this.distanceToSqr(e) < 625.0
                        && !this.isInWater()
                        && this.getThePrinceAdultFire() != 0) {
                    this.shoot_something(e.getX(), e.getY(), e.getZ());
                }
            } else {
                this.setAttacking(0);
            }
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
        if (MyUtils.isRoyalty(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof Mothra) {
            return true;
        }
        if (par1EntityLiving instanceof Kraken) {
            return true;
        }
        if (par1EntityLiving instanceof Leon l) {
            if (l.isTame()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof WaterDragon l) {
            if (l.isTame()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof GammaMetroid l) {
            if (l.isTame()) {
                return false;
            }
            return true;
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
                                LivingEntity.class, this.getBoundingBox().inflate(32.0, 20.0, 32.0));
        Collections.sort(var5, this.targetSorter);
        for (LivingEntity var4 : var5) {
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return false;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
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
        super.lerpMotion(par1, par3, par5);
    }

    @Override
    public void tick() {
        LivingEntity e;
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        if (!this.level().isClientSide && this.getRemainingFireTicks() > 0) {
            this.clearFire();
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        if (!this.level().isClientSide
                && this.getPassengers().isEmpty()
                && !RoyalPetFollowHelper.isStayingPut(this)
                && this.getActivity() == 0
                && MyUtils.isPrinceAirborne(this)) {
            this.setActivity(1);
        }
        if (!this.level().isClientSide
                && this.getPassengers().isEmpty()
                && this.getActivity() == 0
                && !RoyalPetFollowHelper.isStayingPut(this)) {
            this.setNoGravity(false);
            this.noPhysics = false;
            if (!this.onGround()) {
                Vec3 dm = this.getDeltaMovement();
                this.setDeltaMovement(dm.x, Math.min(dm.y - 0.04, -0.08), dm.z);
            }
        }
        super.tick();
        if (RoyalPetFollowHelper.isStayingPut(this) && this.getPassengers().isEmpty()) {
            this.noPhysics = false;
            if (!this.level().isClientSide) {
                this.setNoGravity(false);
            }
        } else {
            this.noPhysics = this.getActivity() != 0;
            if (!this.level().isClientSide && this.getActivity() != 0 && this.getPassengers().isEmpty()) {
                this.setNoGravity(true);
            }
        }
        if (!this.level().isClientSide) {
            int i;
            if (this.getRandom().nextInt(10) == 1) {
                i = this.getRandom().nextInt(3);
                if (i == 0) {
                    this.head1dir = 2;
                }
                if (i == 1) {
                    this.head1dir = -2;
                }
                if (i == 2) {
                    this.head1dir = 0;
                }
            }
            if (this.getRandom().nextInt(10) == 1) {
                i = this.getRandom().nextInt(3);
                if (i == 0) {
                    this.head2dir = 2;
                }
                if (i == 1) {
                    this.head2dir = -2;
                }
                if (i == 2) {
                    this.head2dir = 0;
                }
            }
            if (this.getRandom().nextInt(10) == 1) {
                i = this.getRandom().nextInt(3);
                if (i == 0) {
                    this.head3dir = 2;
                }
                if (i == 1) {
                    this.head3dir = -2;
                }
                if (i == 2) {
                    this.head3dir = 0;
                }
            }
            this.head1ext += this.head1dir;
            if (this.head1ext < 0) {
                this.head1ext = 0;
            }
            if (this.head1ext > 60) {
                this.head1ext = 60;
            }
            this.head2ext += this.head2dir;
            if (this.head2ext < 0) {
                this.head2ext = 0;
            }
            if (this.head2ext > 60) {
                this.head2ext = 60;
            }
            this.head3ext += this.head3dir;
            if (this.head3ext < 0) {
                this.head3ext = 0;
            }
            if (this.head3ext > 60) {
                this.head3ext = 60;
            }
            this.setHead1Ext(this.head1ext);
            this.setHead2Ext(this.head2ext);
            this.setHead3Ext(this.head3ext);
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.getActivity() != 0
                && (this.owner_flying != 0
                        || !this.onGround()
                        || this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-4)) {
            ++this.wing_sound;
            if (this.wing_sound > 30) {
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
        if (!this.level().isClientSide
                && this.getActivity() == 0
                && this.isTame()
                && this.getOwner() != null
                && !RoyalPetFollowHelper.isStayingPut(this)
                && this.getPassengers().isEmpty()
                && this.distanceToSqr((e = this.getOwner())) > 900.0) {
            this.setActivity(1);
        }
        MyUtils.enforceDragonMountGroundSafety(this);
    }

    private void fly_without_rider() {
        if (this.getNavigation() != null) {
            this.getNavigation().stop();
        }
        Vec3 dm = this.getDeltaMovement();
        double mx = dm.x;
        double my = dm.y;
        double mz = dm.z;
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 10;
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
        double obstruction_factor = 0.0;
        boolean toofar = false;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (!this.getPassengers().isEmpty()) {
            return;
        }
        if (RoyalPetFollowHelper.isStayingPut(this)) {
            return;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.owner_flying != 0) {
                this.currentFlightTarget = BlockPos.containing(ox, oy + 2.0, oz);
                do_new = false;
            }
            if (this.distanceToSqr(e) > 400.0 && !RoyalPetFollowHelper.isStayingPut(this)) {
                toofar = true;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.setOrderedToSit(false);
                this.flyaway = 0;
                do_new = true;
            }
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
        if (this.flyaway > 0) {
            --this.flyaway;
        }
        if (!toofar
                && this.flyaway == 0
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(6) == 1) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                if (this.isTame() && this.getHealth() / (float) this.mygetMaxHealth() < 0.25f) {
                    this.setActivity(1);
                    this.setAttacking(0);
                    this.target_in_sight = false;
                    do_new = false;
                    this.currentFlightTarget =
                            new BlockPos(
                                    (int) (this.getX() + (this.getX() - e.getX())),
                                    (int) (this.getY() + 1.0),
                                    (int) (this.getZ() + (this.getZ() - e.getZ())));
                } else {
                    this.setActivity(1);
                    this.setAttacking(1);
                    this.target_in_sight = true;
                    this.currentFlightTarget =
                            new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                    do_new = false;
                    if (this.distanceToSqr(e)
                            < (double) ((10.0f + e.getBbWidth() / 2.0f) * (10.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget(e);
                        this.flyaway = 5 + this.getRandom().nextInt(15);
                        do_new = true;
                    } else if (this.distanceToSqr(e) < 600.0
                            && !this.isInWater()
                            && this.getThePrinceAdultFire() != 0
                            && this.getRandom().nextInt(2) == 1) {
                        this.shoot_something(e.getX(), e.getY(), e.getZ());
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
                if (has_owner) {
                    gox = (int) ox;
                    goy = (int) oy;
                    goz = (int) oz;
                    if (this.owner_flying == 0) {
                        zdir = this.getRandom().nextInt(16) + 8;
                        xdir = this.getRandom().nextInt(16) + 8;
                    } else {
                        zdir = this.getRandom().nextInt(12);
                        xdir = this.getRandom().nextInt(12);
                    }
                } else {
                    zdir = this.getRandom().nextInt(15) + 20;
                    xdir = this.getRandom().nextInt(15) + 20;
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
        // Match ThePrinceAdult: horizontal speed for kMax after targeting/damping, not at method entry.
        double velocity = Math.sqrt(mx * mx + mz * mz);
        // Match ThePrinceAdult: small inner loop only — large scanDist*2 loops stacked huge Y boosts (random "rocket up").
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
        } else if (has_owner && e != null && this.distanceToSqr(e) < 64.0) {
            speed_factor = 0.35;
        }
        mx += (Math.signum(var1) - mx) * 0.15 * speed_factor;
        my += (Math.signum(var3) - my) * 0.21 * speed_factor;
        mz += (Math.signum(var5) - mz) * 0.15 * speed_factor;
        float var7 = (float)(Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = Mth.wrapDegrees((float)(var7 - this.getYRot()));
        this.zza = (float) (0.75 * speed_factor);
        this.setYRot(this.getYRot() + var8 / 4.0f);
        float horizSpeed = (float) Math.sqrt(mx * mx + mz * mz);
        this.zza = Mth.clamp(horizSpeed * 1.5f, 0.15f, 1.0f);
        if (mx * mx + my * my + mz * mz < 0.0025) {
            mx = (this.getRandom().nextDouble() - 0.5) * 0.35;
            my = -0.12;
            mz = (this.getRandom().nextDouble() - 0.5) * 0.35;
        }
        this.setDeltaMovement(mx, my, mz);
        this.move(net.minecraft.world.entity.MoverType.SELF, this.getDeltaMovement());
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        super.aiStep();
        if (this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.07, 0.0));
        }
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
            if (this.boatPosRotationIncrements > 0 && this.getActivity() != 0) {
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
                    Entity rider = this.getPassengers().get(0);
                    this.setYRot(rider.getYRot());
                    this.setYHeadRot(rider.getYRot());
                } else {
                    this.setYRot((float) ((double) this.getYRot() + d10 / (double) this.boatPosRotationIncrements));
                }
                this.setRot(this.getYRot(), this.getXRot());
                --this.boatPosRotationIncrements;
            }
        } else {
            if (this.getActivity() != 0) {
                if (!this.getPassengers().isEmpty()) {
                    this.fly_with_rider();
                    Entity rider = this.getFirstPassenger();
                    if (rider != null && !rider.isAlive()) {
                        this.ejectPassengers();
                    }
                } else if (!RoyalPetFollowHelper.isStayingPut(this)) {
                    this.fly_without_rider();
                }
            }
            this.always_do();
        }
    }

    private boolean isPlayerWithinPrinceReach(Player player, double maxCenterDistSq, double inflate) {
        if (player.distanceToSqr(this) <= maxCenterDistSq) {
            return true;
        }
        return this.getBoundingBox().inflate(inflate, inflate, inflate).contains(player.position());
    }

    private InteractionResult toggleSitStay(Player player) {
        if (!this.level().isClientSide) {
            this.setOrderedToSit(!this.isOrderedToSit());
        }
        return InteractionResult.sidedSuccess(this.level().isClientSide);
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (var2.isEmpty()) {
            var2 = ItemStack.EMPTY;
        } else if (var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()
                && var2.is(Blocks.DIAMOND_BLOCK.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 36.0) {
            if (!this.level().isClientSide) {
                this.heal((float) this.mygetMaxHealth() - this.getHealth());
                this.growcounter = 288000;
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()) {
            if (!this.isOwnedBy(par1EntityPlayer)) {
                return super.mobInteract(par1EntityPlayer, hand);
            }
            if (var2.isEmpty()) {
                if (this.isPlayerWithinPrinceReach(par1EntityPlayer, 36.0, 2.0)) {
                    if (!this.level().isClientSide) {
                        par1EntityPlayer.startRiding(this);
                        this.setActivity(1);
                        this.setOrderedToSit(false);
                    }
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }
            if (!var2.isEmpty()
                    && var2.is(Items.STICK)
                    && this.isPlayerWithinPrinceReach(par1EntityPlayer, 64.0, 4.0)) {
                return this.toggleSitStay(par1EntityPlayer);
            }
            if (!var2.isEmpty() && var2.is(Items.BEEF) && par1EntityPlayer.distanceToSqr(this) < 36.0) {
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
            if (!var2.isEmpty() && par1EntityPlayer.distanceToSqr(this) < 36.0) {
                FoodProperties var3 = var2.getFoodProperties(this);
                if (var3 != null) {
                    if (!this.level().isClientSide) {
                        if ((float) this.mygetMaxHealth() > this.getHealth()) {
                            this.heal((float) (var3.getNutrition() * 10));
                        }
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                    }
                    if (!par1EntityPlayer.getAbilities().instabuild) {
                        var2.shrink(1);
                        if (var2.isEmpty()) {
                            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
            if (!var2.isEmpty()
                    && var2.is(Blocks.ICE.asItem())
                    && par1EntityPlayer.distanceToSqr(this) < 36.0) {
                if (!this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                    this.setThePrinceAdultFire(0);
                    par1EntityPlayer.displayClientMessage(
                            Component.literal("Fireballs extinguished."), true);
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
                    && par1EntityPlayer.distanceToSqr(this) < 36.0) {
                if (!this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 6);
                    this.setThePrinceAdultFire(1);
                    par1EntityPlayer.displayClientMessage(
                            Component.literal("Fireballs lit!"), true);
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
                    && var2.is(Items.DIAMOND)
                    && par1EntityPlayer.distanceToSqr(this) < 36.0
                    && !this.level().isClientSide) {
                Entity ent =
                        spawnCreature(
                                this.level(),
                                "The Young Prince",
                                this.getX(),
                                this.getY(),
                                this.getZ());
                if (ent != null) {
                    transferTameToTeen(ent, par1EntityPlayer.getUUID(), this.isTame());
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
                    && par1EntityPlayer.distanceToSqr(this) < 36.0
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
            if (!var2.isEmpty() && this.isPlayerWithinPrinceReach(par1EntityPlayer, 64.0, 4.0)) {
                return this.toggleSitStay(par1EntityPlayer);
            }
        }
        return super.mobInteract(par1EntityPlayer, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.BEEF);
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        this.entityData.set(ATTACKING, par1);
    }

    public int getActivity() {
        return this.entityData.get(ACTIVITY);
    }

    public void setActivity(int par1) {
        if (this.level() != null && this.level().isClientSide) {
            return;
        }
        if (par1 == 0
                && !RoyalPetFollowHelper.isStayingPut(this)
                && this.getNavigation() != null
                && MyUtils.isPrinceAirborne(this)) {
            par1 = 1;
        }
        if (par1 != 0 && this.getNavigation() != null) {
            this.getNavigation().stop();
        }
        this.entityData.set(ACTIVITY, par1);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
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
        tag.putInt("ThePrinceAdultAttacking", this.getAttacking());
        tag.putInt("ThePrinceAdultActivity", this.getActivity());
        tag.putInt("ThePrinceAdultFire", this.getThePrinceAdultFire());
        tag.putInt("ThePrinceAdultGrow", this.growcounter);
    }

    @Override
    public void readAdditionalSaveData(net.minecraft.nbt.CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setAttacking(tag.getInt("ThePrinceAdultAttacking"));
        this.setActivity(tag.getInt("ThePrinceAdultActivity"));
        this.setThePrinceAdultFire(tag.getInt("ThePrinceAdultFire"));
        this.growcounter = tag.getInt("ThePrinceAdultGrow");
        this.refreshDimensions();
    }

    public static Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        ResourceLocation res =
                par1.contains(":")
                        ? ResourceLocation.parse(par1)
                        : ResourceLocation.fromNamespaceAndPath(
                                "chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
        if (!serverLevel.addFreshEntity(entity)) {
            return null;
        }
        return entity;
    }

    private static void transferTameToTeen(Entity ent, UUID ownerUuid, boolean fromAdultTamed) {
        if (ent instanceof ThePrinceTeen teen) {
            if (fromAdultTamed) {
                teen.setTame(true);
                if (ownerUuid != null) {
                    teen.setOwnerUUID(ownerUuid);
                }
            }
            return;
        }
        if ("ThePrinceTeen".equals(ent.getClass().getSimpleName())) {
            try {
                if (fromAdultTamed) {
                    ent.getClass().getMethod("setTame", boolean.class).invoke(ent, Boolean.TRUE);
                    if (ownerUuid != null) {
                        ent.getClass().getMethod("setOwnerUUID", UUID.class).invoke(ent, ownerUuid);
                    }
                }
            } catch (ReflectiveOperationException ignored) {
            }
        }
    }

    private static void callKingSetFree(Entity ent) {
        if (ent instanceof TheKing king) {
            king.setFree();
            return;
        }
        if ("TheKing".equals(ent.getClass().getSimpleName())) {
            try {
                ent.getClass().getMethod("setFree").invoke(ent);
            } catch (ReflectiveOperationException ignored) {
            }
        }
    }

    private void shoot_something(double x, double y, double z) {
        double rr;
        double rhdir;
        double rdd;
        double pi = 3.1415926545;
        int which = this.getRandom().nextInt(3);
        if (which == 0) {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd =
                    Math.abs(
                                    rr
                                            - (rhdir =
                                                    Math.toRadians((this.getYRot() + 90.0f) % 360.0f)))
                            % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanon(x, y, z);
            }
        } else if (which == 1) {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd =
                    Math.abs(
                                    rr
                                            - (rhdir =
                                                    Math.toRadians((this.getYRot() + 90.0f) % 360.0f)))
                            % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanonl(x, y, z);
            }
        } else {
            rr = Math.atan2(z - this.getZ(), x - this.getX());
            rdd =
                    Math.abs(
                                    rr
                                            - (rhdir =
                                                    Math.toRadians((this.getYRot() + 90.0f) % 360.0f)))
                            % (pi * 2.0);
            if (rdd > pi) {
                rdd -= pi * 2.0;
            }
            if ((rdd = Math.abs(rdd)) < 0.5) {
                this.firecanoni(x, y, z);
            }
        }
    }

    private void firecanon(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
        BetterFireball bf =
                new BetterFireball(
                        this.level(),
                        this,
                        x - cx + (double) r1,
                        y + 0.25 - (this.getY() + yoff) + (double) r2,
                        z - cz + (double) r3);
        bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
        bf.setBig();
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        this.level().addFreshEntity(bf);
    }

    private void firecanonl(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        ThunderBolt lb =
                new ThunderBolt(
                        ChaosPersists.ENTITY_TYPE_THUNDER_BOLT.get(), cx, this.getY() + yoff, cz, this.level());
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
        double var3 = x - lb.getX();
        double var5 = y + 0.25 - lb.getY();
        double var7 = z - lb.getZ();
        float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
        Vec3 lbdm = lb.getDeltaMovement();
        lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
        this.level().addFreshEntity(lb);
    }

    private void firecanoni(double x, double y, double z) {
        double yoff = 3.5;
        double xzoff = 6.0;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        this.level()
                .playSound(
                        null,
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundEvents.ARROW_SHOOT,
                        this.getSoundSource(),
                        1.0f,
                        1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
        IceBall lb =
                new IceBall(
                        ChaosPersists.ENTITY_TYPE_ICE_BALL.get(), cx, this.getY() + yoff, cz, this.level());
        lb.setIceMaker(1);
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
        double var3 = x - lb.getX();
        double var5 = y + 0.25 - lb.getY();
        double var7 = z - lb.getZ();
        float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
        lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
        Vec3 lbdm = lb.getDeltaMovement();
        lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
        this.level().addFreshEntity(lb);
    }
}

