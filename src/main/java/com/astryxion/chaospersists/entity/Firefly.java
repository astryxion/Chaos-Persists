package com.astryxion.chaospersists.entity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;

import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.MathHelper;

public class Firefly extends FlyingEntity {
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/fireflytexture.png");
    int my_blink = 0;
    int blinker = 0;
    int myspace = 0;
    private BlockPos currentFlightTarget = null;

    public Firefly(EntityType<? extends Firefly> type, World par1World) {
        super(type, par1World);
        this.my_blink = 20 + this.random.nextInt(20);
        // EntityType registration: width=0.4f, height=0.8f
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0).build();
    }

    public ResourceLocation getTexture(Firefly a) {
        return texture1;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public float getBlink() {
        if (this.blinker < this.my_blink / 2) {
            return 240.0f;
        }
        return 0.0f;
    }

    @Override
    protected float getSoundVolume() {
        return 0.0f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource ds) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 1;
    }

    protected Item getDropItem() {
        return ChaosPersists.ExtremeTorch.asItem();
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.600000023841, this.getDeltaMovement().z);
        ++this.blinker;
        if (this.blinker > this.my_blink) {
            this.blinker = 0;
        }
        if (this.isPersistenceRequired()) {
            return;
        }
        long t = this.level.getDayTime();
        if ((t %= 24000L) > 11000L) {
            return;
        }
        if (this.level.random.nextInt(500) == 1) {
            this.remove();
        }
    }

    @Override
    protected void customServerAiStep() {
        int keep_trying = 25;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(40) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.0) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(4) - this.random.nextInt(4), (int) this.getY() + this.random.nextInt(4) - 2, (int) this.getZ() + this.random.nextInt(4) - this.random.nextInt(4));
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                --keep_trying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.2 - this.getDeltaMovement().x) * 0.1;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.1;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.2 - this.getDeltaMovement().z) * 0.1;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.2f;
        this.yRot += var8 / 4.0f;
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        Block bid = level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())).getBlock();
        if (bid != Blocks.AIR) {
            return false;
        }
        if (level instanceof net.minecraft.world.World && ((net.minecraft.world.World)level).isDay()) {
            return false;
        }
        if (this.findBuddies() > 10) {
            return false;
        }
        if (level instanceof ServerWorld) {
            ServerWorld dim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(4));
            if (dim != null && level == dim) {
                return true;
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    private int findBuddies() {
        List<Firefly> var5 = this.level.getEntitiesOfClass(Firefly.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        return var5.size();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.level.isNight()) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }
}
