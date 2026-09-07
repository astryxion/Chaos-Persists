package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import java.util.List;
import org.joml.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import java.util.Collections;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Elevator extends Mob {
    private static final EntityDataAccessor<Integer> I20 =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> I21 =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> I22 =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> I23 =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> F24 =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> DATA_FORWARD =
            SynchedEntityData.defineId(Elevator.class, EntityDataSerializers.BOOLEAN);

    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/elevator1.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/elevator2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/elevator3.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/elevator4.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/elevator5.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/entity/elevator6.png");
    private static final ResourceLocation TEXTURE7 =
            new ResourceLocation("chaospersists", "textures/entity/elevator7.png");
    private static final ResourceLocation TEXTURE8 =
            new ResourceLocation("chaospersists", "textures/entity/elevator8.png");
    private static final ResourceLocation TEXTURE9 =
            new ResourceLocation("chaospersists", "textures/entity/elevator9.png");
    private static final ResourceLocation TEXTURE10 =
            new ResourceLocation("chaospersists", "textures/entity/elevator10.png");

    private int exploding = 0;
    private int color = 1;
    private int playing = 0;
    private double hoverMoveX;
    private double hoverMoveZ;

    public Elevator(EntityType<? extends Elevator> type, Level level) {
        super(type, level);
        this.setNoGravity(true);
        this.setNoAi(false);
        this.setPersistenceRequired();
    }

    public Elevator(Level level, double x, double y, double z) {
        this(ChaosPersists.ENTITY_TYPE_ELEVATOR.get(), level);
        this.moveTo(x, y + this.getMyRidingOffset(), z, 0.0f, 0.0f);
        this.setDeltaMovement(Vec3.ZERO);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    public static AttributeSupplier.Builder createAttributes() {
        // Mob.createNavigation() requires FOLLOW_RANGE during construction.
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 1.3300000429153442)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public HumanoidArm getMainArm() {
        return HumanoidArm.RIGHT;
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {}

    public ResourceLocation getTexture() {
        return switch (this.getColor()) {
            case 2 -> TEXTURE2;
            case 3 -> TEXTURE3;
            case 4 -> TEXTURE4;
            case 5 -> TEXTURE5;
            case 6 -> TEXTURE6;
            case 7 -> TEXTURE7;
            case 8 -> TEXTURE8;
            case 9 -> TEXTURE9;
            case 10 -> TEXTURE10;
            default -> TEXTURE1;
        };
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(I22, 0);
        this.entityData.define(I23, 1);
        this.entityData.define(F24, 0.0f);
        this.entityData.define(I20, 0);
        this.entityData.define(I21, 1);
        this.entityData.define(DATA_FORWARD, false);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean shouldRiderSit() {
        return false;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.5;
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

    private Player getRiderPlayer() {
        Entity rider = this.getControllingPassenger();
        if (rider instanceof Player player) {
            return player;
        }
        if (rider != null
                && !rider.getPassengers().isEmpty()
                && rider.getPassengers().get(0) instanceof Player player) {
            return player;
        }
        return null;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int steps, boolean interpolate) {
        if (this.isControlledByLocalInstance()) {
            return;
        }
        super.lerpTo(x, y, z, yaw, pitch, steps, interpolate);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void lerpMotion(double x, double y, double z) {
        if (this.isControlledByLocalInstance()) {
            return;
        }
        super.lerpMotion(x, y, z);
    }

    private boolean isRiderMovingForward(Player rider) {
        if (this.level().isClientSide() && rider instanceof LocalPlayer local) {
            if (local.input.forwardImpulse > 0.001f) {
                return true;
            }
            return Minecraft.getInstance().options.keyUp.isDown();
        }
        return rider.zza > 0.001f;
    }

    private void updateHoverMoveDirection(Player rider) {
        double speed = 0.8;
        if (ChaosPersists.flyup_keystate != 0) {
            speed += 1.0;
        }
        double yawRad = Math.toRadians(rider.getYRot() + 90.0f);
        this.hoverMoveX = speed * Math.cos(yawRad);
        this.hoverMoveZ = speed * Math.sin(yawRad);
    }

    @Override
    public void travel(Vec3 travelVector) {
        LivingEntity passenger = this.getControllingPassenger();
        if (this.isVehicle() && passenger != null) {
            this.setYRot(passenger.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(passenger.getXRot() * 0.5f);
            this.yBodyRot = passenger.getYRot();
            this.yHeadRot = passenger.getYRot();
            this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
            super.travel(new Vec3(passenger.xxa, 0.0f, 0.0f));
            return;
        }
        super.travel(travelVector);
    }

    private void updateHoverboardMovement() {
        this.setNoGravity(true);
        double distanceloc = 0.0;
        boolean nearGround = false;
        Player rider = this.getRiderPlayer();

        if (rider != null) {
            if (!rider.isAlive()) {
                this.ejectPassengers();
                return;
            }
            this.updateHoverMoveDirection(rider);
            boolean movingForward = this.level().isClientSide()
                    ? this.isRiderMovingForward(rider)
                    : rider.zza > 0.001f;
            this.entityData.set(DATA_FORWARD, movingForward);

            for (int index = 0; index < 15; ++index) {
                BlockPos pos = BlockPos.containing(this.getX(), this.getY() - distanceloc, this.getZ());
                if (!this.level().getBlockState(pos).isAir()) {
                    nearGround = true;
                } else if (!nearGround) {
                    distanceloc += 1.0;
                }
            }

            if (this.tickCount % 2 != 0) {
                return;
            }

            if (this.entityData.get(DATA_FORWARD)) {
                if (nearGround) {
                    this.addDeltaMovement(
                            new Vec3(this.hoverMoveX / 3.0, 0.02, this.hoverMoveZ / 3.0));
                } else {
                    this.addDeltaMovement(
                            new Vec3(this.hoverMoveX / 5.0, -0.025, this.hoverMoveZ / 5.0));
                }
            } else if (nearGround) {
                this.addDeltaMovement(new Vec3(0.0, 0.02, 0.0));
            } else {
                this.addDeltaMovement(new Vec3(0.0, -0.025, 0.0));
            }
        } else {
            this.entityData.set(DATA_FORWARD, false);
            distanceloc = 0.0;
            nearGround = false;
            for (int index = 0; index < 2; ++index) {
                BlockPos pos = BlockPos.containing(this.getX(), this.getY() - distanceloc, this.getZ());
                if (!this.level().getBlockState(pos).isAir()) {
                    nearGround = true;
                } else if (!nearGround) {
                    distanceloc += 1.0;
                }
            }

            if (this.tickCount % 2 != 0) {
                return;
            }

            if (nearGround) {
                this.addDeltaMovement(new Vec3(0.0, 0.03, 0.0));
            } else {
                this.addDeltaMovement(new Vec3(0.0, -0.03, 0.0));
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean p = source.getEntity() instanceof Player;
        if (this.getControllingPassenger() != null && !p) {
            return false;
        }
        if (source.getMsgId().equals("inWall")) {
            return false;
        }
        if (!this.level().isClientSide && !this.isRemoved()) {
            this.setForwardDirection(-this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + amount * 10.0f);
            boolean creative =
                    source.getEntity() instanceof Player player && player.getAbilities().instabuild;
            if (creative || this.getDamageTaken() > 40.0f) {
                if (this.getControllingPassenger() != null) {
                    this.ejectPassengers();
                }
                if (!creative && ChaosPersists.MyElevator != null) {
                    this.spawnAtLocation(ChaosPersists.MyElevator);
                }
                this.discard();
            }
            return true;
        }
        return true;
    }

    @Override
    public void animateHurt(float yaw) {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0f);
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {}

    @Override
    public void tick() {
        if (this.isRemoved()) {
            return;
        }
        super.tick();
        this.updateHoverboardMovement();
        int k;
        double d5;
        Block bid;
        int i;
        double d4;
        double d6;
        double d7;
        Vec3 motion = this.getDeltaMovement();
        double mx = motion.x;
        double my = motion.y;
        double mz = motion.z;
        double velocity = Math.sqrt(mx * mx + mz * mz);
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.getDamageTaken() > 0.0f) {
            this.setDamageTaken(this.getDamageTaken() - 1.0f);
        }
        if (this.playing > 0) {
            --this.playing;
        }
        if (this.getControllingPassenger() != null
                && this.playing == 0
                && this.getRandom().nextInt(80) == 1) {
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            ChaosSounds.HOVER,
                            SoundSource.NEUTRAL,
                            0.45f,
                            1.0f);
            this.playing = 55;
        }
        if (!this.level().isClientSide) {
            if (this.exploding > 0) {
                --this.exploding;
            }
            if (this.exploding == 0 && velocity > 0.65 && this.getRandom().nextInt(20000) == 1) {
                this.exploding = 45;
                this.playing = 50;
            }
            this.setExploding(this.exploding);
        } else {
            this.exploding = this.getExploding();
        }
        if (this.getExploding() > 0 && this.getControllingPassenger() != null) {
            if (this.getRandom().nextInt(10) == 1) {
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.GENERIC_EXPLODE,
                                SoundSource.NEUTRAL,
                                0.55f,
                                0.75f + this.getRandom().nextFloat());
            }
            for (i = 0; i < 15; ++i) {
                this.level()
                        .addParticle(
                                ParticleTypes.EXPLOSION,
                                (int) (this.getX()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 4.0f)),
                                (int) (this.getY()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 4.0f)),
                                (int) (this.getZ()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 4.0f)),
                                mx,
                                0.0,
                                mz);
                this.level()
                        .addParticle(
                                ParticleTypes.EXPLOSION_EMITTER,
                                (int) (this.getX()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0f)),
                                (int) (this.getY()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0f)),
                                (int) (this.getZ()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0f)),
                                mx,
                                0.0,
                                mz);
                this.level()
                        .addParticle(
                                ParticleTypes.SMOKE,
                                (int) (this.getX()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 5.0f)),
                                (int) (this.getY()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 5.0f)),
                                (int) (this.getZ()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 5.0f)),
                                mx,
                                0.0,
                                mz);
                this.level()
                        .addParticle(
                                ParticleTypes.LARGE_SMOKE,
                                (int) (this.getX()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0f)),
                                (int) (this.getY()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0f)),
                                (int) (this.getZ()
                                        + (double) ((this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0f)),
                                mx,
                                0.0,
                                mz);
            }
        }
        if (!this.level().isClientSide()) {
            Player rider = this.getRiderPlayer();
            if (rider == null) {
                List<Entity> nearby =
                        this.level().getEntities(this, this.getBoundingBox().inflate(0.25, 0.0, 0.25));
                for (Entity entity : nearby) {
                    if (!entity.isPushable()) {
                        continue;
                    }
                    String simpleName = entity.getClass().getSimpleName();
                    if ("Girlfriend".equals(simpleName) || "Boyfriend".equals(simpleName)) {
                        continue;
                    }
                    entity.push(this);
                }
                if (this.horizontalCollision && motion.horizontalDistance() > 0.75) {
                    this.discard();
                    int p = this.getRandom().nextInt(10);
                    for (int drop = 0; drop < 6 + p; ++drop) {
                        this.spawnAtLocation(new ItemStack(net.minecraft.world.item.Items.STICK));
                    }
                    for (int drop = 0; drop < 2; ++drop) {
                        this.spawnAtLocation(new ItemStack(net.minecraft.world.item.Items.DIAMOND));
                    }
                    return;
                }
            }
        }
        double horizSpeed =
                Math.sqrt(
                        (this.getX() - this.xo) * (this.getX() - this.xo)
                                + (this.getZ() - this.zo) * (this.getZ() - this.zo));
        if (horizSpeed > 0.15 && this.getControllingPassenger() != null) {
            d4 = Math.cos(Math.toRadians(this.getYRot() + 270.0f));
            d5 = Math.sin(Math.toRadians(this.getYRot() + 270.0f));
            bid = Blocks.AIR;
            for (i = 1; i < 10
                    && (bid = this.level()
                                    .getBlockState(new BlockPos((int) this.getX(), (int) this.getY() - i, (int) this.getZ()))
                                    .getBlock())
                            == Blocks.AIR;
                    ++i) {
            }
            int j = 0;
            DustParticleOptions dust = new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);
            while ((double) j < 1.0 + horizSpeed * 10.0) {
                double d9;
                double d8;
                d6 = this.getRandom().nextFloat() * 2.0f - 1.0f;
                d7 = (double) (this.getRandom().nextInt(2) * 2 - 1) * 0.7;
                if (this.getRandom().nextBoolean()) {
                    d8 = this.getX() - d4 * d6 * 0.8 + d5 * d7;
                    d9 = this.getZ() - d5 * d6 * 0.8 - d4 * d7;
                    if (this.getRandom().nextBoolean()) {
                        this.level().addParticle(ParticleTypes.SMOKE, d8, this.getY() - 0.25, d9, mx, my, mz);
                    } else {
                        this.level().addParticle(dust, d8, this.getY() - 0.25, d9, mx, my, mz);
                    }
                } else {
                    d8 = this.getX() + d4 + d5 * d6 * 0.7;
                    d9 = this.getZ() + d5 - d4 * d6 * 0.7;
                    if (this.getRandom().nextBoolean()) {
                        this.level().addParticle(ParticleTypes.SMOKE, d8, this.getY() - 0.225, d9, mx, my, mz);
                    } else {
                        this.level().addParticle(dust, d8, this.getY() - 0.225, d9, mx, my, mz);
                    }
                }
                if (bid == Blocks.WATER) {
                    for (k = 0; k < 5; ++k) {
                        this.level()
                                .addParticle(
                                        ParticleTypes.SPLASH,
                                        this.getX() + (double) this.getRandom().nextFloat(),
                                        this.getY() - (double) i + 1.25,
                                        this.getZ() + (double) this.getRandom().nextFloat(),
                                        mx / 2.0,
                                        my + horizSpeed,
                                        mz / 2.0);
                    }
                }
                ++j;
            }
        }
        this.clearFire();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("HoverColor", this.getColor());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.color = tag.getInt("HoverColor");
        if (this.color < 1) {
            this.color = 1;
        }
        if (this.color > 10) {
            this.color = 10;
        }
        this.setColor(this.color);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && stack.getCount() <= 0) {
            player.setItemInHand(hand, ItemStack.EMPTY);
            stack = ItemStack.EMPTY;
        }
        if (!stack.isEmpty()
                && stack.getItem() == ChaosPersists.MyUltimateSword
                && player.distanceToSqr(this) < 16.0) {
            if (!this.level().isClientSide) {
                int c = this.getColor() + 1;
                if (c > 10) {
                    c = 1;
                }
                this.setColor(c);
            }
            return InteractionResult.SUCCESS;
        }
        if (this.getControllingPassenger() instanceof Player rider
                && rider != player) {
            return InteractionResult.SUCCESS;
        }
        if (!this.level().isClientSide) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }

    public void setDamageTaken(float f) {
        this.entityData.set(F24, f);
    }

    public float getDamageTaken() {
        return this.entityData.get(F24);
    }

    public void setTimeSinceHit(int par1) {
        this.entityData.set(I22, par1);
    }

    public int getTimeSinceHit() {
        return this.entityData.get(I22);
    }

    public void setForwardDirection(int par1) {
        this.entityData.set(I23, par1);
    }

    public int getForwardDirection() {
        return this.entityData.get(I23);
    }

    public void setExploding(int par1) {
        this.entityData.set(I20, par1);
    }

    public int getExploding() {
        return this.entityData.get(I20);
    }

    public void setColor(int par1) {
        this.entityData.set(I21, par1);
    }

    public int getColor() {
        return this.entityData.get(I21);
    }
}
