package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Firefly extends AmbientCreature {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/fireflytexture.png");
    int my_blink = 0;
    int blinker = 0;
    int myspace = 0;
    private BlockPos currentFlightTarget = null;

    public Firefly(EntityType<? extends Firefly> type, Level par1World) {
        super(type, par1World);
        this.my_blink = 20 + this.getRandom().nextInt(20);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    public ResourceLocation getTexture(Firefly a) {
        return TEXTURE;
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
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 1;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.600000023841, 1.0));
        ++this.blinker;
        if (this.blinker > this.my_blink) {
            this.blinker = 0;
        }
        if (this.isPersistenceRequired()) {
            return;
        }
        long t = this.level().getDayTime() % 24000L;
        if (t > 11000L) {
            return;
        }
        if (this.level().getRandom().nextInt(500) == 1) {
            this.discard();
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        int keep_trying = 25;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(40) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.0) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                this.currentFlightTarget = new BlockPos(
                        (int) this.getX() + this.getRandom().nextInt(4) - this.getRandom().nextInt(4),
                        (int) this.getY() + this.getRandom().nextInt(4) - 2,
                        (int) this.getZ() + this.getRandom().nextInt(4) - this.getRandom().nextInt(4));
                bid = this.level().getBlockState(this.currentFlightTarget);
                --keep_trying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add((Math.signum(var1) * 0.2 - motion.x) * 0.1, (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.1, (Math.signum(var5) * 0.2 - motion.z) * 0.1));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 4.0f);
        MyUtils.applyChaosFlightMovement(this);
}

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (!isDaytime(this.level())) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    private static boolean isDaytime(LevelAccessor level) {
        if (level instanceof Level world) {
            return world.isDay();
        }
        return false;
    }

    public static boolean checkFireflySpawnRules(
            EntityType<Firefly> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        BlockState bid = MyUtils.getBlockStateForSpawnRules(level, pos);
        if (bid.getBlock() != Blocks.AIR) {
            return false;
        }
        if (isDaytime(level)) {
            return false;
        }
        if (level.getEntitiesOfClass(Firefly.class, new AABB(pos).inflate(20.0, 8.0, 20.0)).size() > 10) {
            return false;
        }
        if (level.getLevel().dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        BlockState bid = MyUtils.getBlockStateForSpawnRules(level, pos);
        if (bid.getBlock() != Blocks.AIR) {
            return false;
        }
        if (isDaytime(level)) {
            return false;
        }
        if (this.findBuddies() > 10) {
            return false;
        }
        if (level instanceof Level world && world.dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    private int findBuddies() {
        List<Firefly> var5 =
                this.level().getEntitiesOfClass(Firefly.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        return var5.size();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        net.minecraft.world.level.block.Block block =
                ForgeRegistries.BLOCKS.getValue(new ResourceLocation("chaospersists", "extremetorch"));
        if (block != null) {
            this.spawnAtLocation(new ItemStack(block));
        }
    }
}
