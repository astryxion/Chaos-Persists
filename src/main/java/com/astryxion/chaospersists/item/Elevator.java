/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Elevator
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.client.entity.player.player.EntityClientServerPlayerEntity
 *  net.minecraft.client.network.ClientPlayNetHandler
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerInventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.network.IPacket
 *  net.minecraft.network.play.client.C03PacketPlayerEntity
 *  net.minecraft.network.play.client.C03PacketPlayerEntity$C05PacketPlayerEntityLook
 *  net.minecraft.network.play.client.C0CPacketInput
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.MovementInput
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.util.SoundEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.client.CInputPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ActionResultType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class Elevator
extends MobEntity {
    private static final DataParameter<Integer> I20 = EntityDataManager.defineId(Elevator.class, DataSerializers.INT);
    private static final DataParameter<Integer> I21 = EntityDataManager.defineId(Elevator.class, DataSerializers.INT);
    private static final DataParameter<Integer> I22 = EntityDataManager.defineId(Elevator.class, DataSerializers.INT);
    private static final DataParameter<Integer> I23 = EntityDataManager.defineId(Elevator.class, DataSerializers.INT);
    private static final DataParameter<Float> F24 = EntityDataManager.defineId(Elevator.class, DataSerializers.FLOAT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
    private int damage_counter = 100;
    private int exploding = 0;
    private int color = 1;
    private int playing = 0;
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/elevator1.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/elevator2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/elevator3.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/elevator4.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/elevator5.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/elevator6.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/entity/elevator7.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/entity/elevator8.png");
    private static final ResourceLocation texture9 = new ResourceLocation("chaospersists", "textures/entity/elevator9.png");
    private static final ResourceLocation texture10 = new ResourceLocation("chaospersists", "textures/entity/elevator10.png");

    public Elevator(EntityType<? extends Elevator> type, World par1World) {
        super(type, par1World);
        this.noPhysics = true;
        this.setNoGravity(true);
        this.setNoAi(true);
    }

    public Elevator(World par1World) {
        this(resolveEntityType(), par1World);
    }

    public Elevator(World par1World, double par2, double par4, double par6) {
        this(par1World);
        this.setPos(par2, par4 + (double)this.getMyRidingOffset(), par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends Elevator> resolveEntityType() {
        EntityType<?> type = net.minecraftforge.registries.ForgeRegistries.ENTITIES
            .getValue(new ResourceLocation("chaospersists", "hoverboard"));
        return type != null ? (EntityType<? extends Elevator>) type : (EntityType<? extends Elevator>) (EntityType<?>) EntityType.PIG;
    }

    public ResourceLocation getTexture() {
        switch (this.getColor()) {
            case 1: {
                return texture1;
            }
            case 2: {
                return texture2;
            }
            case 3: {
                return texture3;
            }
            case 4: {
                return texture4;
            }
            case 5: {
                return texture5;
            }
            case 6: {
                return texture6;
            }
            case 7: {
                return texture7;
            }
            case 8: {
                return texture8;
            }
            case 9: {
                return texture9;
            }
            case 10: {
                return texture10;
            }
        }
        return texture1;
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 60.0)
                .add(Attributes.MOVEMENT_SPEED, 1.3300000429153442)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    protected boolean canDespawn() {
        return false;
    }

    public boolean shouldRiderSit() {
        return false;
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

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(I22, 0);
        this.entityData.define(I23, 1);
        this.entityData.define(F24, Float.valueOf(0.0f));
        this.entityData.define(I20, 0);
        this.entityData.define(I21, 0);
        this.setPersistenceRequired();
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    public double getMountedYOffset() {
        return (double)this.getPassengersRidingOffset();
    }

    public double getPassengersRidingOffset() {
        return 0.5D;
    }

    public boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().isEmpty();
    }

    public boolean canPassengerSteer() {
        return true;
    }

    /**
     * Vanilla requires this for player movement input ({@code yya} / {@code xxa}) to apply while riding
     * a living entity; otherwise the client/server treat the mount like a non-steerable mob.
     */
    public boolean canBeSteered() {
        return true;
    }

    @Override
    public Entity getControllingPassenger() {
        if (this.getPassengers().isEmpty()) {
            return null;
        }
        Entity passenger = this.getPassengers().get(0);
        return passenger instanceof LivingEntity ? passenger : null;
    }

    @Override
    public void positionRider(Entity passenger) {
        if (this.hasPassenger(passenger)) {
            passenger.setPos(this.getX(), this.getY() + (double)this.getPassengersRidingOffset() + passenger.getMyRidingOffset(), this.getZ());
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean p = par1DamageSource.getEntity() instanceof PlayerEntity;
        if (this.getControllingPassenger() != null && !p) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        if (!this.level.isClientSide && !this.removed) {
            boolean flag;
            this.setForwardDirection(- this.getForwardDirection());
            this.setTimeSinceHit(10);
            this.setDamageTaken(this.getDamageTaken() + par2 * 10.0f);
            boolean bl = flag = par1DamageSource.getEntity() instanceof PlayerEntity && ((PlayerEntity)par1DamageSource.getEntity()).isCreative();
            if (flag || this.getDamageTaken() > 40.0f) {
                if (this.getControllingPassenger() != null) {
                    this.ejectPassengers();
                }
                if (!flag) {
                    this.spawnAtLocation(new ItemStack(ChaosPersists.MyElevator));
                }
                this.remove();
            }
            return true;
        }
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    public void performHurtAnimation() {
        this.setForwardDirection(- this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0f);
    }

    public boolean canBeCollidedWith() {
        return !this.removed;
    }

    /**
     * 1.12.2 uses {@link Entity#setPositionAndRotationDirect} for server→client motion sync; 1.7's
     * {@code setPositionAndRotation2} is not invoked by the network layer anymore.
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) {
        this.boatPosRotationIncrements = this.getControllingPassenger() != null ? posRotationIncrements + 8 : 6;
        this.boatX = x;
        this.boatY = y;
        this.boatZ = z;
        this.boatYaw = yaw;
        this.boatPitch = pitch;
        this.setDeltaMovement(this.velocityX, this.getDeltaMovement().y, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.velocityY, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, this.velocityZ);
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.velocityX = par1;
        this.velocityY = par3;
        this.velocityZ = par5;
        this.setDeltaMovement(par1, par3, par5);
    }

    /**
     * Vanilla copies the rider's {@code moveForward} / {@code moveStrafing} onto the mount inside
     * {@link LivingEntity#onLivingUpdate()} before {@link #travel(float, float, float)}. Skipping
     * {@code super} while ridden (old 1.7 port) left those fields at 0 on the server, so WASD did nothing.
     * We suppress default {@code travel} motion and still apply hover physics below.
     */
    @Override
    public void travel(Vector3d travelVector) {
        if (this.getControllingPassenger() != null) {
            return;
        }
        super.travel(travelVector);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isAlive()) {
            return;
        }
        int k;
        double d5;
        Block bid;
        int i;
        double d4;
        List list = null;
        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        double d6 = this.random.nextFloat() * 2.0f - 1.0f;
        double d7 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
        double obstruction_factor = 0.0;
        double relative_g = 0.0;
        double max_speed = 0.85;
        double gh = 0.75;
        int dist = 2;
        if (this.getTimeSinceHit() > 0) {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }
        if (this.getDamageTaken() > 0.0f) {
            this.setDamageTaken(this.getDamageTaken() - 1.0f);
        }
        this.xo = this.getX();
        this.yo = this.getY();
        this.zo = this.getZ();
        if (this.playing > 0) {
            --this.playing;
        }
        if (this.getControllingPassenger() != null && this.playing == 0 && this.level.random.nextInt(80) == 1) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.HOVER, net.minecraft.util.SoundCategory.NEUTRAL, 0.45f, 1.0f);
            this.playing = 55;
        }
        if (!this.level.isClientSide) {
            if (this.exploding > 0) {
                --this.exploding;
            }
            if (this.exploding == 0 && velocity > 0.65 && this.level.random.nextInt(20000) == 1) {
                this.exploding = 45;
                this.playing = 50;
            }
            this.setExploding(this.exploding);
        } else {
            this.exploding = this.getExploding();
        }
        if (this.getExploding() > 0 && this.getControllingPassenger() != null) {
            if (this.level.random.nextInt(10) == 1) {
                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_EXPLODE, net.minecraft.util.SoundCategory.NEUTRAL, 0.55f, 0.75f + this.level.random.nextFloat());
            }
            for (i = 0; i < 15; ++i) {
                this.level.addParticle(ParticleTypes.EXPLOSION, (double)((int)(this.getX() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 4.0f))), (double)((int)(this.getY() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 4.0f))), (double)((int)(this.getZ() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 4.0f))), this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
                this.level.addParticle(ParticleTypes.EXPLOSION_EMITTER, (double)((int)(this.getX() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0f))), (double)((int)(this.getY() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0f))), (double)((int)(this.getZ() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 2.0f))), this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
                this.level.addParticle(ParticleTypes.SMOKE, (double)((int)(this.getX() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 5.0f))), (double)((int)(this.getY() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 5.0f))), (double)((int)(this.getZ() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 5.0f))), this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
                this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (double)((int)(this.getX() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0f))), (double)((int)(this.getY() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0f))), (double)((int)(this.getZ() + (double)((this.level.random.nextFloat() - this.level.random.nextFloat()) * 3.0f))), this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
            }
        }
        if (this.level.isClientSide) {
            if (this.getControllingPassenger() == null) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
                if (bid != Blocks.AIR) {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.06, 0.0);
                    com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.07);
                    this.boatY += 0.07;
                } else {
                    com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.003), 0.0);
                }
            }

            // Mirror 1.7.10-style input sync: ensure the server sees WASD while riding.
            if (this.getControllingPassenger() instanceof ClientPlayerEntity) {
                ClientPlayerEntity pp = (ClientPlayerEntity)this.getControllingPassenger();
                pp.connection.send(new CPlayerPacket.RotationPacket(pp.yRot, pp.xRot, pp.isOnGround()));
                pp.connection.send(new CInputPacket(pp.xxa, pp.yya, pp.input.jumping, pp.isShiftKeyDown()));
            }
            if (this.boatPosRotationIncrements > 0) {
                d4 = this.getX() + (this.boatX - this.getX()) / (double)this.boatPosRotationIncrements;
                d5 = this.getY() + (this.boatY - this.getY()) / (double)this.boatPosRotationIncrements;
                double d11 = this.getZ() + (this.boatZ - this.getZ()) / (double)this.boatPosRotationIncrements;
                this.setPos(d4, d5, d11);
                this.xRot = (float)((double)this.xRot + (this.boatPitch - (double)this.xRot) / (double)this.boatPosRotationIncrements);
                double d10 = MathHelper.wrapDegrees((double)(this.boatYaw - (double)this.yRot));
                if (this.getControllingPassenger() != null) {
                    d10 = MathHelper.wrapDegrees((double)((double)this.getControllingPassenger().yRot - (double)this.yRot));
                }
                this.yRot = (float)((double)this.yRot + d10 / (double)this.boatPosRotationIncrements);
                this.yRot = this.yRot;
                this.xRot = this.xRot;
                --this.boatPosRotationIncrements;
            } else {
                d4 = this.getX() + this.getDeltaMovement().x;
                d5 = this.getY() + this.getDeltaMovement().y;
                double d11 = this.getZ() + this.getDeltaMovement().z;
                this.setPos(d4, d5, d11);
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.99, 0.95, 0.99);
            }
        } else {
            if (this.getControllingPassenger() != null) {
                gh = 1.25;
            }
            if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock()) != Blocks.AIR) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.06, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.1);
                if (bid == Blocks.GRASS_BLOCK && this.getControllingPassenger() != null && this.level.random.nextInt(200) == 1 && this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)(this.getY() - gh), (int)this.getZ()), Blocks.AIR.defaultBlockState(), 3);
                }
                if (bid == Blocks.GRASS_BLOCK && this.getControllingPassenger() != null && this.level.random.nextInt(200) == 1 && this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    this.level.setBlock(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)(this.getY() - gh), (int)this.getZ()), Blocks.DIRT.defaultBlockState(), 3);
                }
            } else {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.01), 0.0);
            }
            if (this.getControllingPassenger() != null) {
                double rdv;
                PlayerEntity pp = (PlayerEntity)this.getControllingPassenger();
                // Let Shift always dismount immediately while riding.
                if (pp.isCrouching()) {
                    pp.stopRiding();
                    return;
                }
                obstruction_factor = 0.0;
                // IMPORTANT: do not mutate loop bounds inside the condition.
                // Old logic used: k < (dist += velocity*8), which can grow faster than k and stall the game.
                int scanDepth = 3 + (int)(Math.max(0.0, velocity) * 8.0);
                if (scanDepth > 24) {
                    scanDepth = 24;
                }
                for (k = 1; k < scanDepth; ++k) {
                    for (i = 1; i < scanDepth * 2; ++i) {
                        double dz;
                        double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                        bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f)))))).getBlock();
                        if (bid == Blocks.AIR) continue;
                        obstruction_factor += 0.05;
                    }
                }
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.11, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.11);
                d4 = this.getControllingPassenger().yRot;
                d4 %= 360.0;
                while (d4 < 0.0) {
                    d4 += 360.0;
                }
                d5 = this.yRot;
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
                    this.yRot = this.getControllingPassenger().yRot + (float)(relative_g * d4);
                } else {
                    this.yRot = this.getControllingPassenger().yRot;
                }
                relative_g = Math.abs(relative_g) * velocity;
                if (relative_g > 50.0) {
                    relative_g = 0.0;
                }
                this.xRot = 10.0f * (float)velocity;
                this.yRot = this.yRot;
                this.xRot = this.xRot;
                double newvelocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
                if (this.exploding != 0 && (newvelocity -= 0.05) < 0.0) {
                    newvelocity = 0.0;
                }
                double rr = Math.atan2(this.getControllingPassenger().getDeltaMovement().z, this.getControllingPassenger().getDeltaMovement().x);
                double rhm = Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x);
                double rhdir = Math.toRadians((this.getControllingPassenger().yRot + 90.0f) % 360.0f);
                double rt = 0.0;
                double pi = 3.1415926545;
                double deltav = 0.0;
                // Prefer mounted input copied onto this entity by vanilla riding flow.
                // Fallback to player input field for compatibility with prior IPacket sync behavior.
                float im = Math.abs(this.yya) > 0.001f ? this.yya : pp.yya;
                if (ChaosPersists.flyup_keystate != 0) {
                    max_speed += 1.0;
                }
                if ((rdv = Math.abs(rhm - rhdir) % (pi * 2.0)) > pi) {
                    rdv -= pi * 2.0;
                }
                rdv = Math.abs(rdv);
                if (Math.abs(newvelocity) < 0.01) {
                    rdv = 0.0;
                }
                if (rdv > 1.5) {
                    newvelocity = - newvelocity;
                }
                if (Math.abs(im) > 0.001f) {
                    if (im > 0.0f) {
                        deltav = 0.025;
                        if (max_speed > 1.0) {
                            deltav += 0.15;
                        }
                    } else {
                        max_speed = 0.35;
                        deltav = -0.02;
                    }
                    if ((newvelocity += deltav) >= 0.0) {
                        if (newvelocity > max_speed) {
                            newvelocity = max_speed;
                        }
                        this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
                    } else {
                        if (newvelocity < - max_speed) {
                            newvelocity = - max_speed;
                        }
                        newvelocity = - newvelocity;
                        this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 270.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 270.0f)) * newvelocity);
                    }
                } else if (newvelocity >= 0.0) {
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 90.0f)) * newvelocity, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 90.0f)) * newvelocity);
                } else {
                    this.setDeltaMovement(Math.cos(Math.toRadians(this.yRot + 270.0f)) * (newvelocity * -1.0), this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, Math.sin(Math.toRadians(this.yRot + 270.0f)) * (newvelocity * -1.0));
                }
            } else if (this.getControllingPassenger() == null) {
                this.setDeltaMovement(0.0, this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 0.0);
            }
            this.move(net.minecraft.entity.MoverType.SELF, this.getDeltaMovement());
            if (this.horizontalCollision && velocity > 0.75) {
                this.remove();
                int p = this.level.random.nextInt(10);
                for (k = 0; k < 6 + p; ++k) {
                    this.spawnAtLocation(new ItemStack(Items.STICK));
                }
                for (k = 0; k < 2; ++k) {
                    this.spawnAtLocation(new ItemStack(Items.DIAMOND));
                }
            } else {
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.98, 0.94, 0.98);
            }
            list = this.level.getEntities((Entity)this, this.getBoundingBox().inflate(0.25, 0.0, 0.25));
            if (list != null && !list.isEmpty()) {
                for (int l = 0; l < list.size(); ++l) {
                    Entity entity = (Entity)list.get(l);
                    if (entity == this.getControllingPassenger() || !entity.isPushable() || entity instanceof Girlfriend || entity instanceof Boyfriend) continue;
                    entity.push((Entity)this);
                }
            }
            if (this.getControllingPassenger() != null && this.getControllingPassenger().removed) {
                this.ejectPassengers();
            }
        }
        // Motion is often ~0 on the client while position still updates (interpolation / packets). Use displacement for trail FX.
        double horizSpeed = Math.sqrt(
                (this.getX() - this.xo) * (this.getX() - this.xo)
                + (this.getZ() - this.zo) * (this.getZ() - this.zo));
        if (horizSpeed > 0.15 && this.getControllingPassenger() != null) {
            d4 = Math.cos(Math.toRadians(this.yRot + 270.0f));
            d5 = Math.sin(Math.toRadians(this.yRot + 270.0f));
            bid = Blocks.AIR;
            for (i = 1; i < 10 && (bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY() - i, (int)this.getZ())).getBlock()) == Blocks.AIR; ++i) {
            }
            int j = 0;
            while ((double)j < 1.0 + horizSpeed * 10.0) {
                double d9;
                double d8;
                d6 = this.random.nextFloat() * 2.0f - 1.0f;
                d7 = (double)(this.random.nextInt(2) * 2 - 1) * 0.7;
                if (this.random.nextBoolean()) {
                    d8 = this.getX() - d4 * d6 * 0.8 + d5 * d7;
                    d9 = this.getZ() - d5 * d6 * 0.8 - d4 * d7;
                    if (this.random.nextBoolean()) {
                        this.level.addParticle(ParticleTypes.SMOKE, d8, this.getY() - 0.25, d9, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    } else {
                        this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), d8, this.getY() - 0.25, d9, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    }
                } else {
                    d8 = this.getX() + d4 + d5 * d6 * 0.7;
                    d9 = this.getZ() + d5 - d4 * d6 * 0.7;
                    if (this.random.nextBoolean()) {
                        this.level.addParticle(ParticleTypes.SMOKE, d8, this.getY() - 0.225, d9, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    } else {
                        this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), d8, this.getY() - 0.225, d9, this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                    }
                }
                if (bid == Blocks.WATER) {
                    for (k = 0; k < 5; ++k) {
                        this.level.addParticle(ParticleTypes.SPLASH, this.getX() + (double)this.random.nextFloat(), this.getY() - (double)i + 1.25, this.getZ() + (double)this.random.nextFloat(), this.getDeltaMovement().x / 2.0, this.getDeltaMovement().y + horizSpeed, this.getDeltaMovement().z / 2.0);
                    }
                }
                ++j;
            }
        }
        this.setSecondsOnFire(0);
    }


    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        par1CompoundNBT.putInt("HoverColor", this.getColor());
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        this.color = par1CompoundNBT.getInt("HoverColor");
        if (this.color < 1) {
            this.color = 1;
        }
        if (this.color > 10) {
            this.color = 10;
        }
        this.setColor(this.color);
    }

    public float getShadowSize() {
        return 0.25f;
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && stack.getCount() <= 0) {
            player.setItemInHand(hand, ItemStack.EMPTY);
            stack = ItemStack.EMPTY;
        }
        if (!stack.isEmpty() && stack.getItem() == ChaosPersists.MyUltimateSword && player.distanceToSqr(this) < 16.0) {
            if (!this.level.isClientSide) {
                int c = this.getColor() + 1;
                if (c > 10) {
                    c = 1;
                }
                this.setColor(c);
            }
            return ActionResultType.SUCCESS;
        }
        if (this.getControllingPassenger() != null && this.getControllingPassenger() instanceof PlayerEntity && this.getControllingPassenger() != player) {
            return ActionResultType.SUCCESS;
        }
        if (!this.level.isClientSide) {
            player.startRiding(this);
        }
        return ActionResultType.SUCCESS;
    }

    public void setDamageTaken(float f) {
        this.entityData.set(F24, Float.valueOf(f));
    }

    public float getDamageTaken() {
        return this.entityData.get(F24).floatValue();
    }

    public void setTimeSinceHit(int par1) {
        this.entityData.set(I22, par1);
    }

    public int getTimeSinceHit() {
        return this.entityData.get(I22).intValue();
    }

    public void setForwardDirection(int par1) {
        this.entityData.set(I23, par1);
    }

    public int getForwardDirection() {
        return this.entityData.get(I23).intValue();
    }

    public void setExploding(int par1) {
        this.entityData.set(I20, par1);
    }

    public int getExploding() {
        return this.entityData.get(I20).intValue();
    }

    public void setColor(int par1) {
        this.entityData.set(I21, par1);
    }

    public int getColor() {
        return this.entityData.get(I21).intValue();
    }
}

