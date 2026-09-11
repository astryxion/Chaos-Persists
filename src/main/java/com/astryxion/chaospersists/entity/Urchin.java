package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Urchin extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Urchin.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private final float moveSpeed = 0.3f;
    private int was_spawnered = 0;

    public Urchin(EntityType<? extends Urchin> type, Level level) {
        super(type, level);
        this.xpReward = 20;
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
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Urchin_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Urchin_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Urchin_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.was_spawnered != 0) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.isPersistenceRequired()) {
            return;
        }
        if (this.was_spawnered != 0) {
            return;
        }
        if (!this.level().isClientSide) {
            long t = this.level().getDayTime() % 24000L;
            if (t < 12000L && this.getRandom().nextInt(400) == 1) {
                this.discard();
            }
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Urchin_stats.health;
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
    public void aiStep() {
        super.aiStep();
        if (this.getRandom().nextInt(3) == 1) {
            if (this.level().isClientSide) {
                this.level()
                        .addParticle(
                                ParticleTypes.FLAME,
                                this.getX(),
                                this.getY() + 0.75,
                                this.getZ(),
                                0.0,
                                (double) (this.getRandom().nextFloat() / 10.0f),
                                0.0);
            }
            if (this.isInWater() && this.getRandom().nextInt(5) == 1) {
                if (!this.level().isClientSide) {
                    this.doHurtTarget(this);
                }
                if (this.level().isClientSide) {
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 1.75,
                                    this.getZ(),
                                    0.0,
                                    (double) (this.getRandom().nextFloat() / 10.0f),
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.LARGE_SMOKE,
                                    this.getX(),
                                    this.getY() + 1.75,
                                    this.getZ(),
                                    0.0,
                                    (double) (this.getRandom().nextFloat() / 10.0f),
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.SMOKE,
                                    this.getX(),
                                    this.getY() + 2.0,
                                    this.getZ(),
                                    0.0,
                                    (double) (this.getRandom().nextFloat() / 10.0f),
                                    0.0);
                    this.level()
                            .addParticle(
                                    ParticleTypes.LARGE_SMOKE,
                                    this.getX(),
                                    this.getY() + 2.0,
                                    this.getZ(),
                                    0.0,
                                    (double) (this.getRandom().nextFloat() / 10.0f),
                                    0.0);
                }
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.KYUUBI_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.GLASSHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.GLASSDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 1.1f;
    }

    @Override
    public float getVoicePitch() {
        return 1.25f;
    }

    protected Item getDropItem() {
        int i = this.getRandom().nextInt(3);
        if (i == 1) {
            return ChaosPersists.MyCrystalPinkIngot;
        }
        if (i == 2) {
            return ChaosPersists.MyCrystalApple;
        }
        return null;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        Item drop = this.getDropItem();
        if (drop != null) {
            this.spawnAtLocation(drop);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof LivingEntity living) {
            living.setSecondsOnFire(5);
        }
        return super.doHurtTarget(target);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.getRandom().nextInt(8) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr(e) < 8.0) {
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(7) == 0 || this.getRandom().nextInt(8) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getMsgId().equals("cactus")) {
            return false;
        }
        return super.hurt(source, amount);
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
        if (par1EntityLiving instanceof Rotator) {
            return false;
        }
        if (par1EntityLiving instanceof Vortex) {
            return false;
        }
        if (par1EntityLiving instanceof CrystalCow) {
            return false;
        }
        if (par1EntityLiving instanceof Peacock) {
            return false;
        }
        if (par1EntityLiving instanceof Irukandji) {
            return false;
        }
        if (par1EntityLiving instanceof Skate) {
            return false;
        }
        if (par1EntityLiving instanceof Whale) {
            return false;
        }
        if (par1EntityLiving instanceof Flounder) {
            return false;
        }
        if (par1EntityLiving instanceof Urchin) {
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
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 3.0, 16.0));
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

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k <= 2; ++k) {
            for (int j = -2; j <= 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Crystal Urchin".equals(id.getPath())) {
                        this.was_spawnered = 1;
                        return true;
                    }
                }
            }
        }
        int sc = 0;
        for (int k = -1; k <= 1; ++k) {
            for (int j = -1; j <= 1; ++j) {
                checkPos.set((int) this.getX() + j, (int) this.getY() + 1, (int) this.getZ() + k);
                if (MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                    ++sc;
                }
            }
        }
        if (sc < 6) {
            return false;
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (!CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            long t = level.getLevelData().getDayTime() % 24000L;
            if (t < 13000L) {
                return false;
            }
        }
        return true;
    }

    protected boolean isValidLightLevel(LevelAccessor level) {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            return true;
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_URCHIN.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
