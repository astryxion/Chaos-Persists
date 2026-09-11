package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
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
import net.minecraft.tags.BlockTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class Molenoid extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Molenoid.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.35f;

    public Molenoid(EntityType<? extends Molenoid> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 40;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 4, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Molenoid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Molenoid_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Molenoid_stats.defense);
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

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Molenoid_stats.health;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(3) == 0) {
            return ChaosSounds.MOLENOID_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.MOLENOID_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.MOLENOID_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.1f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MolenoidNose, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (int var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        for (int var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
    }

    private void dropItemRand(Item index, int count) {
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        new ItemStack(index, count));
        this.level().addFreshEntity(entityItem);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getMsgId().equals("inWall")) {
            return false;
        }
        return super.hurt(source, amount);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                double ks = 0.8;
                double inair = 0.1;
                float f3 = (float) Math.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
                if (!living.isAlive() || target instanceof Player) {
                    inair *= 2.0;
                }
                living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        LivingEntity e = null;
        if (this.getRandom().nextInt(4) == 0) {
            e = this.findSomethingToAttack();
            if (e != null) {
                float reach = 6.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.distanceToSqr(e) < 16.0
                            && (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1)) {
                        this.doHurtTarget(e);
                    } else if (ChaosPersists.PlayNicely == 0) {
                        int j = 1 + this.getRandom().nextInt(4);
                        block0:
                        for (int k = 0; k < j; ++k) {
                            double dx = e.getX();
                            double dz = e.getZ();
                            dx += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0;
                            dz += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0;
                            for (int i = 4; i > -3; --i) {
                                BlockPos placePos =
                                        BlockPos.containing(dx, e.getY() + i + 1, dz);
                                BlockPos belowPos = BlockPos.containing(dx, e.getY() + i, dz);
                                if (this.level().getBlockState(placePos).isAir()
                                        && !this.level().getBlockState(belowPos).isAir()) {
                                    this.level()
                                            .setBlock(
                                                    placePos,
                                                    ChaosPersists.MyMoleDirtBlock.defaultBlockState(),
                                                    3);
                                    break block0;
                                }
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.25);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(2) == 0) {
            int odds;
            Vec3 motion = this.getDeltaMovement();
            double spd = motion.x * motion.x + motion.z * motion.z;
            spd = Math.sqrt(spd);
            if (spd > (double) this.moveSpeed) {
                spd = this.moveSpeed;
            }
            if ((odds = (int) (100.0 * spd / (double) this.moveSpeed)) > 0
                    && this.getRandom().nextInt(100) < odds
                    && ChaosPersists.PlayNicely == 0) {
                double dx = this.getX() + 6.0 * Math.sin(Math.toRadians(this.getYHeadRot()));
                double dz = this.getZ() - 6.0 * Math.cos(Math.toRadians(this.getYHeadRot()));
                dx += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0;
                dz += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0;
                for (int i = 4; i > -4; --i) {
                    BlockPos placePos = BlockPos.containing(dx, this.getY() + i + 1, dz);
                    BlockPos belowPos = BlockPos.containing(dx, this.getY() + i, dz);
                    if (this.level().getBlockState(placePos).isAir()
                            && !this.level().getBlockState(belowPos).isAir()) {
                        this.level()
                                .setBlock(
                                        placePos,
                                        ChaosPersists.MyMoleDirtBlock.defaultBlockState(),
                                        3);
                        break;
                    }
                }
            }
        }
        double dx = this.getX() - 3.0 * Math.sin(Math.toRadians(this.getYHeadRot()));
        double dz = this.getZ() + 3.0 * Math.cos(Math.toRadians(this.getYHeadRot()));
        dx += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0;
        dz += (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 3.0;
        int dir = 1;
        if (e != null) {
            if ((int) e.getY() > (int) this.getY()) {
                dir = 2;
            }
            if ((int) e.getY() < (int) this.getY()) {
                dir = 0;
            }
        }
        if (ChaosPersists.PlayNicely == 0) {
            for (int i = dir; i < dir + 3; ++i) {
                BlockPos digPos = BlockPos.containing(dx, this.getY() + i, dz);
                BlockState state = this.level().getBlockState(digPos);
                Block bid = state.getBlock();
                if ((bid == Blocks.DIRT
                                || bid == Blocks.GRASS_BLOCK
                                || bid == Blocks.GRAVEL
                                || bid == Blocks.SAND
                                || state.is(BlockTags.LEAVES))
                        && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                    this.level().setBlock(digPos, Blocks.AIR.defaultBlockState(), 3);
                }
                if (bid == ChaosPersists.MyMoleDirtBlock) {
                    this.level().setBlock(digPos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
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
        if (!this.myCanSee(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof Molenoid) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (MyUtils.isAttackableNonMob(par1EntityLiving)) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 6.0, 12.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public boolean myCanSee(LivingEntity e) {
        double xzoff = 2.0;
        int nblks = 10;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        float startx = (float) cx;
        float starty = (float) (this.getY() + 1.0);
        float startz = (float) cz;
        float dx = (float) ((e.getX() - (double) startx) / 10.0);
        float dy = (float) ((e.getY() + (double) (e.getBbHeight() / 2.0f) - (double) starty) / 10.0);
        float dz = (float) ((e.getZ() - (double) startz) / 10.0);
        if ((double) Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int) ((float) nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double) Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int) ((float) nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double) Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int) ((float) nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; ++i) {
            BlockPos pos = BlockPos.containing(startx += dx, starty += dy, startz += dz);
            BlockState state = this.level().getBlockState(pos);
            Block bid = state.getBlock();
            if (bid == Blocks.AIR
                    || bid == ChaosPersists.MyMoleDirtBlock
                    || bid == Blocks.DIRT
                    || bid == Blocks.GRASS_BLOCK
                    || state.is(Blocks.TALL_GRASS)
                    || bid == Blocks.SAND
                    || bid == Blocks.GRAVEL) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean checkMolenoidSpawnRules(
            EntityType<Molenoid> type,
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
                    if (id != null && "Molenoid".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (!Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random)) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (!MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                        return false;
                    }
                }
            }
        }
        List<Molenoid> nearby =
                level.getLevel().getEntitiesOfClass(Molenoid.class, new AABB(pos).inflate(16.0, 8.0, 16.0));
        return nearby.isEmpty();
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
                    if (id != null && "Molenoid".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (!MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                        return false;
                    }
                }
            }
        }
        return this.level()
                .getEntitiesOfClass(Molenoid.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0))
                .isEmpty();
    }

    protected boolean isValidLightLevel(LevelAccessor level) {
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_MOLENOID.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
