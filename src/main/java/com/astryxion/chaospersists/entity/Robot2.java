package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Robot2 extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Robot2.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int just_for_fun = 0;
    private float moveSpeed = 0.3f;

    public Robot2(EntityType<? extends Robot2> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 100;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(
                2, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 4, () -> false));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Robot2_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Robot2_stats.attack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return !this.isPersistenceRequired();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Robot2_stats.health;
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
    public int getArmorValue() {
        return ChaosPersists.Robot2_stats.defense;
    }

    @Override
    protected void jumpFromGround() {
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 0.25, this.getDeltaMovement().z);
        super.jumpFromGround();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(4) == 0) {
            return ChaosSounds.ROBOT_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ROBOT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ROBOT_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var5 = 2 + this.getRandom().nextInt(8);
        for (int var4 = 0; var4 < var5; ++var4) {
            this.spawnAtLocation(new ItemStack(Blocks.IRON_BLOCK));
        }
        var5 = 5 + this.getRandom().nextInt(6);
        for (int var4 = 0; var4 < var5; ++var4) {
            this.spawnAtLocation(new ItemStack(Items.IRON_INGOT, 1));
        }
        int i = 5 + this.getRandom().nextInt(10);
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(15);
            switch (var3) {
                case 0:
                    this.spawnAtLocation(Items.REDSTONE);
                    break;
                case 1:
                    this.spawnAtLocation(Items.REPEATER);
                    break;
                case 2:
                    this.spawnAtLocation(Items.COMPARATOR);
                    break;
                case 3:
                    this.spawnAtLocation(Blocks.REDSTONE_BLOCK);
                    break;
                case 4:
                    this.spawnAtLocation(Blocks.DISPENSER);
                    break;
                case 5:
                    this.spawnAtLocation(Blocks.STICKY_PISTON);
                    break;
                case 6:
                    this.spawnAtLocation(Blocks.PISTON);
                    break;
                case 7:
                    this.spawnAtLocation(Blocks.LEVER);
                    break;
                case 8:
                    this.spawnAtLocation(Blocks.REDSTONE_BLOCK);
                    break;
                case 9:
                    this.spawnAtLocation(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE);
                    break;
                default:
                    break;
            }
        }
    }

    protected void destroyBlock(LivingEntity e) {
        double x = e.getX() + (double) this.getRandom().nextFloat() - (double) this.getRandom().nextFloat();
        double y = e.getY() - 1.0;
        double z = e.getZ() + (double) this.getRandom().nextFloat() - (double) this.getRandom().nextFloat();
        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = this.level().getBlockState(pos);
        if (state.is(Blocks.OBSIDIAN)) {
            return;
        }
        if (state.is(Blocks.BEDROCK)) {
            return;
        }
        if (state.is(Blocks.QUARTZ_BLOCK)) {
            return;
        }
        if (state.is(Blocks.SPAWNER)) {
            return;
        }
        if (state.is(Blocks.REDSTONE_BLOCK)) {
            return;
        }
        if (state.is(Blocks.IRON_BLOCK)) {
            return;
        }
        if (state.is(Blocks.CHEST)) {
            return;
        }
        if (!state.isAir() && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            this.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    protected void destroyNearbyBlocks() {
        for (int i = 0; i < 50; ++i) {
            double x = this.getX() + (double) this.getRandom().nextFloat() * 6.5 - (double) this.getRandom().nextFloat() * 6.5;
            double y = this.getY() + 0.1 + (double) this.getRandom().nextFloat() * 8.5;
            double z = this.getZ() + (double) this.getRandom().nextFloat() * 6.5 - (double) this.getRandom().nextFloat() * 6.5;
            BlockPos pos = BlockPos.containing(x, y, z);
            BlockState state = this.level().getBlockState(pos);
            if (state.is(Blocks.OBSIDIAN)
                    || state.is(Blocks.BEDROCK)
                    || state.is(Blocks.QUARTZ_BLOCK)
                    || state.is(Blocks.SPAWNER)
                    || state.is(Blocks.REDSTONE_BLOCK)
                    || state.is(Blocks.IRON_BLOCK)
                    || state.is(Blocks.CHEST)
                    || state.isAir()
                    || !this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                continue;
            }
            this.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.getRandom().nextInt(6) == 1 && ChaosPersists.PlayNicely == 0) {
            LivingEntity e = this.getTarget();
            if (this.getRandom().nextInt(50) == 1) {
                this.setTarget(null);
            }
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                double rhdir = Math.toRadians((this.getYRot() + 90.0f) % 360.0f);
                double pi = 3.1415926545;
                double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                if (rdd > pi) {
                    rdd -= pi * 2.0;
                }
                rdd = Math.abs(rdd);
                MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                if (rdd < 1.25) {
                    float reach = 5.0f + e.getBbWidth() / 2.0f;
                    if (this.distanceToSqr(e) < (double) (reach * reach)) {
                        this.setAttacking(1);
                        if (this.getRandom().nextInt(5) == 0 || this.getRandom().nextInt(6) == 1) {
                            this.doHurtTarget(e);
                            for (int i = 0; i < 6; ++i) {
                                this.destroyBlock(e);
                            }
                        }
                        this.destroyNearbyBlocks();
                    }
                } else {
                    this.setAttacking(0);
                }
                this.getNavigation().moveTo(e, 1.0);
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getAttacking() == 0 && ChaosPersists.PlayNicely == 0) {
            if (this.getRandom().nextInt(450) == 1) {
                this.just_for_fun = 50;
            }
            if (this.just_for_fun > 0) {
                --this.just_for_fun;
            }
            if (this.just_for_fun > 0) {
                this.setAttacking(1);
                if (this.getRandom().nextInt(3) == 1) {
                    this.destroyNearbyBlocks();
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if ("cactus".equals(par1DamageSource.getMsgId())) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity src = par1DamageSource.getEntity();
        if (src instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        return ret;
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Monster) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(14.0, 3.0, 14.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (this.isSuitableTarget(var4, false)) {
                return var4;
            }
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Robo-Pounder".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                for (int i = 1; i < 6; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.isAir() || state.is(Blocks.TALL_GRASS)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_ROBOT2.get(),
                    serverLevel,
                    spawnReason,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
