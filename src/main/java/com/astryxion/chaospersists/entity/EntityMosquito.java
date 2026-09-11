package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import com.astryxion.chaospersists.util.MyUtils;

public class EntityMosquito extends AmbientCreature {
    private BlockPos currentFlightTarget = null;

    public EntityMosquito(EntityType<? extends EntityMosquito> type, Level level) {
        super(type, level);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    public float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.MOSQUITO;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
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
        int keep_trying = 50;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(20) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 3.0) {
            Player target = null;
            if (ChaosPersists.ChaosRand.nextInt(4) == 0) {
                AABB search = this.getBoundingBox().inflate(10.0, 6.0, 10.0);
                List<Player> players = this.level().getEntitiesOfClass(Player.class, search);
                double best = Double.MAX_VALUE;
                for (Player player : players) {
                    double dist = this.distanceToSqr(player);
                    if (dist < best) {
                        best = dist;
                        target = player;
                    }
                }
                if (target != null) {
                    this.currentFlightTarget =
                            new BlockPos((int) target.getX(), (int) target.getY() + 2, (int) target.getZ());
                } else {
                    BlockState bid = Blocks.STONE.defaultBlockState();
                    while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                        this.currentFlightTarget = new BlockPos(
                                (int) this.getX() + this.getRandom().nextInt(6) - this.getRandom().nextInt(6),
                                (int) this.getY() + this.getRandom().nextInt(6) - 2,
                                (int) this.getZ() + this.getRandom().nextInt(6) - this.getRandom().nextInt(6));
                        bid = this.level().getBlockState(this.currentFlightTarget);
                        --keep_trying;
                    }
                }
            } else {
                BlockState bid = Blocks.STONE.defaultBlockState();
                while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                    this.currentFlightTarget = new BlockPos(
                            (int) this.getX() + this.getRandom().nextInt(6) - this.getRandom().nextInt(6),
                            (int) this.getY() + this.getRandom().nextInt(6) - 2,
                            (int) this.getZ() + this.getRandom().nextInt(6) - this.getRandom().nextInt(6));
                    bid = this.level().getBlockState(this.currentFlightTarget);
                    --keep_trying;
                }
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.5 - motion.x) * 0.10000000149011612,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.10000000149011612,
                        (Math.signum(var5) * 0.5 - motion.z) * 0.10000000149011612));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8);
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

    public static boolean checkMosquitoSpawnRules(
            EntityType<EntityMosquito> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return true;
    }
}
