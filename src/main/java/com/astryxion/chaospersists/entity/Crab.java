package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class Crab extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Crab.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> CRAB_SCALE =
            SynchedEntityData.defineId(Crab.class, EntityDataSerializers.INT);
    private final GenericTargetSorter targetSorter;
    private int hurt_timer = 0;
    private float moveSpeed = 0.55f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Crab(EntityType<? extends Crab> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 150;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.PitchBlack_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.55)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Crab_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Crab_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(CRAB_SCALE, 25);
    }

    public float getCrabScale() {
        return this.entityData.get(CRAB_SCALE) / 100.0f;
    }

    public void setCrabScale(float par1) {
        this.entityData.set(CRAB_SCALE, (int) (par1 * 100.0f));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putFloat("Fscale", this.getCrabScale());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("Fscale")) {
            this.setCrabScale(tag.getFloat("Fscale"));
            this.xpReward = (int) (400.0f * this.getCrabScale());
        }
    }

    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            net.minecraft.world.DifficultyInstance difficulty,
            MobSpawnType reason,
            @Nullable SpawnGroupData spawnData,
            @Nullable CompoundTag dataTag) {
        spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
        if (dataTag == null || !dataTag.contains("Fscale")) {
            if (isCrabSpawnerNear(level, this.blockPosition())) {
                this.setCrabScale(0.35f);
            } else {
                float t = 0.25f;
                if (this.getRandom().nextInt(4) == 1) {
                    t = 0.5f;
                }
                if (this.getRandom().nextInt(8) == 2) {
                    t = 1.0f;
                }
                this.setCrabScale(t);
            }
            this.xpReward = (int) (400.0f * this.getCrabScale());
        }
        this.refreshAttributesForScale();
        this.refreshDimensions();
        return spawnData;
    }

    private void refreshAttributesForScale() {
        float scale = this.getCrabScale();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) ((float) ChaosPersists.PitchBlack_stats.health * scale));
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) (this.moveSpeed * scale));
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double) (ChaosPersists.Crab_stats.attack * scale));
        this.getAttribute(Attributes.ARMOR).setBaseValue((double) (ChaosPersists.Crab_stats.defense + 2.0f * scale));
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        float scale = this.getCrabScale();
        return EntityDimensions.scalable(2.5f * scale, 3.5f * scale);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void tick() {
        this.moveSpeed = this.isInWater() ? 0.95f : 0.55f;
        this.refreshAttributesForScale();
        super.tick();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        this.refreshDimensions();
    }

    public int mygetMaxHealth() {
        return (int) ((float) ChaosPersists.PitchBlack_stats.health * this.getCrabScale());
    }

    public int getCrabHealth() {
        return (int) this.getHealth();
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
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    public float getVoicePitch() {
        return 2.0f - 0.3f * (1.0f / this.getCrabScale());
    }

    private ItemStack dropItemRand(Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        ItemEntity itemEntity =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        stack);
        this.level().addFreshEntity(itemEntity);
        return stack;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        Item meat = ChaosPersists.MyRawCrabMeat;
        if (meat == null) {
            meat =
                    ForgeRegistries.ITEMS.getValue(
                            new ResourceLocation("chaospersists", "crabmeat"));
        }
        if (meat == null) {
            return;
        }
        int var5 = 4 + this.getRandom().nextInt(8);
        var5 = (int) ((float) var5 * this.getCrabScale());
        if (var5 < 1) {
            var5 = 1;
        }
        for (int var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(meat, 1);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        float damage = (float) ChaosPersists.Crab_stats.attack * this.getCrabScale();
        boolean hit = target.hurt(this.damageSources().mobAttack(this), damage);
        if (hit && target instanceof LivingEntity living) {
            double ks = 1.15 * (double) this.getCrabScale();
            double inair = 0.48 * (double) this.getCrabScale();
            float f3 = (float) Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
            if (!target.isAlive() || target instanceof Player) {
                inair *= 2.0;
            }
            living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return hit;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getMsgId().equals("cactus")) {
            return false;
        }
        Entity e = source.getEntity();
        boolean ret = false;
        if (this.hurt_timer <= 0 && !this.isInvulnerableTo(source)) {
            ret = super.hurt(source, amount);
            if (ret) {
                this.hurt_timer = 8;
            }
        }
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (e instanceof Crab) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        return ret;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x - dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y - dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                if (state.is(Blocks.WATER)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z + dz;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                if (state.is(Blocks.WATER)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z - dz;
                        ++found;
                    }
                }
            }
        }
        return found != 0;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (!this.isInWater() && this.getRandom().nextInt(25) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 10) {
                    j = 10;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() - 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 5) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double) this.tx, (double) (this.ty - 1), (double) this.tz, 1.33);
            } else {
                if (this.getRandom().nextInt(100) == 1) {
                    this.heal(-1.0f * this.getCrabScale());
                }
                if (this.getHealth() <= 0.0f) {
                    this.kill();
                    return;
                }
            }
        }
        if (this.getRandom().nextInt(5) == 1) {
            LivingEntity e = null;
            if (this.getRandom().nextInt(100) == 1) {
                this.setTarget(null);
            }
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.setTarget(e);
                float reach = (6.0f + e.getBbWidth() / 2.0f) * (6.0f + e.getBbWidth() / 2.0f) * this.getCrabScale();
                if (this.distanceToSqr(e) < (double) reach) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
                        this.doHurtTarget(e);
                        if (!this.level().isClientSide) {
                            if (this.getRandom().nextInt(3) == 1) {
                                this.level()
                                        .playSound(
                                                null,
                                                e.getX(),
                                                e.getY(),
                                                e.getZ(),
                                                ChaosSounds.SCORPION_ATTACK,
                                                SoundSource.HOSTILE,
                                                0.75f,
                                                1.5f);
                            } else {
                                this.level()
                                        .playSound(
                                                null,
                                                e.getX(),
                                                e.getY(),
                                                e.getZ(),
                                                ChaosSounds.SCORPION_LIVING,
                                                SoundSource.HOSTILE,
                                                0.75f,
                                                1.5f);
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.0);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(120) == 1
                && this.isInWater()
                && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.playSound(SoundEvents.GENERIC_SPLASH, 1.5f, this.getRandom().nextFloat() * 0.2f + 0.9f);
            this.heal(4.0f * this.getCrabScale());
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
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof Crab) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof Lizard) {
            return true;
        }
        if (par1EntityLiving instanceof RubberDucky) {
            return true;
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        if (par1EntityLiving instanceof Girlfriend) {
            return true;
        }
        if (par1EntityLiving instanceof Boyfriend) {
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
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    private int findBuddies() {
        List<Crab> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Crab.class, this.getBoundingBox().inflate(24.0, 8.0, 24.0));
        return var5.size();
    }

    private boolean feetInWater() {
        BlockPos base = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        for (int k = 0; k <= 2; k++) {
            if (this.level().getBlockState(base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    private static boolean feetInWaterAt(ServerLevelAccessor level, BlockPos base) {
        for (int k = 0; k <= 2; k++) {
            if (MyUtils.getBlockStateForSpawnRules(level, base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCrabSpawnerNear(LevelAccessor level, BlockPos origin) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Crab".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkCrabSpawnRules(
            EntityType<Crab> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (isCrabSpawnerNear(level, pos)) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            if (!feetInWaterAt(level, pos)) {
                return false;
            }
            List<Crab> buddies =
                    level.getLevel()
                            .getEntitiesOfClass(Crab.class, new AABB(pos).inflate(24.0, 8.0, 24.0));
            if (buddies.size() > 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (isCrabSpawnerNear(level, this.blockPosition())) {
            this.setCrabScale(0.35f);
            this.xpReward = (int) (400.0f * this.getCrabScale());
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            if (!this.feetInWater()) {
                return false;
            }
            if (this.findBuddies() > 2) {
                return false;
            }
        }
        return true;
    }
}
