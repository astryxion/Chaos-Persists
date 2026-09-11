package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class LeafMonster extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(LeafMonster.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private float moveSpeed = 0.25f;

    public LeafMonster(EntityType<? extends LeafMonster> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.LeafMonster_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.LeafMonster_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.LeafMonster_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.LeafMonster_stats.health;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.getAttacking() == 0) {
            this.snapToBlockCenter();
            this.setXRot(0.0f);
            int head = Mth.floor(this.getYRot());
            head = head / 90 * 90;
            this.setYRot(head);
            this.yHeadRot = head;
            this.getNavigation().stop();
            Vec3 motion = this.getDeltaMovement();
            this.setDeltaMovement(0.0, motion.y, 0.0);
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.getAttacking() == 0) {
            super.travel(Vec3.ZERO);
            return;
        }
        super.travel(travelVector);
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        if (this.getAttacking() != 0) {
            super.playStepSound(pos, state);
        }
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        float i = distance - 3.0f;
        if (i > 0.0f) {
            if (i > 2.0f) {
                this.playSound(SoundEvents.GENERIC_BIG_FALL, 1.0f, 1.0f);
                i = 2.0f;
            } else {
                this.playSound(SoundEvents.GENERIC_SMALL_FALL, 1.0f, 1.0f);
            }
            this.hurt(this.damageSources().fall(), i);
        }
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LEAVES_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.LEAVES_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.65f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = this.getRandom().nextInt(3);
        Item drop = null;
        if (i == 0) {
            drop = Items.OAK_LOG;
        } else if (i == 1) {
            drop = Items.OAK_LEAVES.asItem();
        } else if (i == 2) {
            drop = Items.ROTTEN_FLESH;
        }
        if (drop != null) {
            this.spawnAtLocation(drop);
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(100) == 1) {
            this.setLastHurtByMob(null);
        }
        LivingEntity target = this.findSomethingToAttack();
        LivingEntity revenge = this.getLastHurtByMob();
        if (revenge != null && this.isSuitableTarget(revenge, false)) {
            target = revenge;
        }
        if (target != null) {
            MyUtils.faceEntity(this, target, 10.0f, 10.0f);
            this.setAttacking(1);
            this.moveAlongGridToward(target);
            if (this.distanceToSqr(target) < 25.0
                    && (this.getRandom().nextInt(8) == 0 || this.getRandom().nextInt(10) == 1)) {
                this.doHurtTarget(target);
            }
        } else {
            this.setAttacking(0);
            this.getNavigation().stop();
        }
    }

    private void moveAlongGridToward(LivingEntity target) {
        int targetX = Mth.floor(target.getX());
        int targetZ = Mth.floor(target.getZ());
        int currentX = Mth.floor(this.getX());
        int currentZ = Mth.floor(this.getZ());
        if (currentX == targetX && currentZ == targetZ) {
            this.snapToBlockCenter();
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }
        int nextX = currentX;
        int nextZ = currentZ;
        double deltaX = target.getX() - this.getX();
        double deltaZ = target.getZ() - this.getZ();
        if (Math.abs(deltaX) >= Math.abs(deltaZ)) {
            nextX += Integer.compare(targetX, currentX);
            this.setCardinalFacing(nextX - currentX, 0);
        } else {
            nextZ += Integer.compare(targetZ, currentZ);
            this.setCardinalFacing(0, nextZ - currentZ);
        }
        double destX = nextX + 0.5;
        double destZ = nextZ + 0.5;
        Vec3 step = new Vec3(destX - this.getX(), 0.0, destZ - this.getZ());
        if (step.lengthSqr() < 0.0025) {
            this.snapToBlockCenter();
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }
        Vec3 horiz = step.normalize().scale(this.moveSpeed);
        this.setDeltaMovement(horiz.x, this.getDeltaMovement().y, horiz.z);
        this.setXRot(0.0f);
        this.yHeadRot = this.getYRot();
    }

    private void snapToBlockCenter() {
        int px = Mth.floor(this.getX());
        int py = Mth.floor(this.getY());
        int pz = Mth.floor(this.getZ());
        this.setPos(px + 0.5, py, pz + 0.5);
    }

    private void setCardinalFacing(int stepX, int stepZ) {
        if (stepX > 0) {
            this.setYRot(270.0f);
        } else if (stepX < 0) {
            this.setYRot(90.0f);
        } else if (stepZ > 0) {
            this.setYRot(0.0f);
        } else if (stepZ < 0) {
            this.setYRot(180.0f);
        }
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof EntityAnt) {
            return true;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof EntityLunaMoth) {
            return true;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(4.0, 6.0, 4.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public static boolean checkLeafMonsterSpawnRules(
            EntityType<LeafMonster> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation leafId =
                            new ResourceLocation("chaospersists", "leaf_monster");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, leafId)
                            || "Leaf Monster".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(4))) {
            if (pos.getY() > 20) {
                return false;
            }
        } else if (pos.getY() < 50) {
            return false;
        }
        List<LeafMonster> buddies =
                level.getLevel().getEntitiesOfClass(LeafMonster.class, new AABB(pos).inflate(20.0, 10.0, 20.0));
        return buddies.size() <= 4;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation leafId =
                            new ResourceLocation("chaospersists", "leaf_monster");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, leafId)
                            || "Leaf Monster".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (this.level().isDay()) {
            return false;
        }
        if (this.level().dimension().equals(ChaosPersists.getDimensionKey(4))) {
            if (this.getY() > 20.0) {
                return false;
            }
        } else if (this.getY() < 50.0) {
            return false;
        }
        return this.level()
                        .getEntitiesOfClass(LeafMonster.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0))
                        .size()
                <= 4;
    }
}
