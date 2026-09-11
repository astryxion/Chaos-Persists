package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class Rat extends Monster {
    private static final net.minecraft.network.syncher.EntityDataAccessor<Byte> ATTACKING =
            net.minecraft.network.syncher.SynchedEntityData.defineId(Rat.class, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;
    private String myowner = null;

    public Rat(EntityType<? extends Rat> type, Level par1World) {
        super(type, par1World);
        this.setMaxUpStep(0.5f);
        this.xpReward = 5;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.goalSelector.addGoal(3, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Rat_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.25f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Rat_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Rat_stats.defense);
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
        if (this.myowner != null) {
            return false;
        }
        return true;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Rat_stats.health;
    }

    @Override
    public void aiStep() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.aiStep();
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.25, 0.0));
        this.setPos(this.getX(), this.getY() + 0.25, this.getZ());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.RATLIVE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.RATHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.RATDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.spawnAtLocation(Items.ROTTEN_FLESH);
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.level().getRandom().nextInt(200) == 1) {
            this.setTarget(null);
        }
        if (this.level().getRandom().nextInt(5) == 1) {
            LivingEntity e = this.getTarget();
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
                this.setAttacking(1);
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr(e) < 4.0
                        && (this.getRandom().nextInt(8) == 0 || this.getRandom().nextInt(7) == 1)) {
                    this.doHurtTarget(e);
                }
            } else {
                Player p = null;
                this.setAttacking(0);
                if (this.myowner != null) {
                    try {
                        p = this.level().getPlayerByUUID(UUID.fromString(this.myowner));
                    } catch (IllegalArgumentException ignored) {
                        p = null;
                    }
                }
                if (this.myowner != null && p != null) {
                    if (this.distanceToSqr(p) > 64.0) {
                        this.getNavigation().moveTo(p, 1.75);
                    }
                    if (this.distanceToSqr(p) > 256.0) {
                        this.setPos(
                                p.getX() + (double) this.level().getRandom().nextFloat()
                                        - (double) this.level().getRandom().nextFloat(),
                                p.getY(),
                                p.getZ() + (double) this.level().getRandom().nextFloat()
                                        - (double) this.level().getRandom().nextFloat());
                    }
                }
            }
        }
        if (this.level().getRandom().nextInt(250) == 1) {
            this.heal(1.0f);
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (isRatExcludedTarget(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.isCreative()) {
                return false;
            }
            if (this.myowner != null) {
                if (this.myowner.equals(p.getUUID().toString())) {
                    return false;
                }
                if (ChaosPersists.RatPlayerFriendly != 0) {
                    return false;
                }
            }
        }
        if (this.myowner != null && par1EntityLiving instanceof TamableAnimal e) {
            if (ChaosPersists.RatPetFriendly != 0 && e.isTame()) {
                return false;
            }
            if (e.getOwnerUUID() != null && this.myowner.equals(e.getOwnerUUID().toString())) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 = this.level().getEntitiesOfClass(
                LivingEntity.class, this.getBoundingBox().inflate(9.0, 2.0, 9.0));
        Collections.sort(var5, this.TargetSorter);
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

    public void setOwner(LivingEntity e) {
        if (e instanceof Player p) {
            this.myowner = p.getUUID().toString();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        if (this.myowner == null) {
            this.myowner = "null";
        }
        par1NBTTagCompound.putString("MyOwner", this.myowner);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.myowner = par1NBTTagCompound.getString("MyOwner");
        if (this.myowner != null && this.myowner.equals("null")) {
            this.myowner = null;
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    public static boolean checkRatSpawnRules(
            EntityType<Rat> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            return true;
        }
        return Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        int k;
        int j;
        for (k = -2; k < 2; ++k) {
            for (j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "rat".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (this.findBuddies() > 8) {
            return false;
        }
        return true;
    }

    protected boolean isValidLightLevel(LevelAccessor level) {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(level)) {
            return true;
        }
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_RAT.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }

    private int findBuddies() {
        List<Rat> var5 = this.level().getEntitiesOfClass(Rat.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }

    /** Same exclusions as 1.12.2 instanceof checks; class names until all mobs extend LivingEntity. */
    private static boolean isRatExcludedTarget(LivingEntity entity) {
        String n = entity.getClass().getSimpleName();
        return n.equals("Irukandji")
                || n.equals("Skate")
                || n.equals("Whale")
                || n.equals("Flounder")
                || n.equals("Rat")
                || n.equals("Ghost")
                || n.equals("GhostSkelly")
                || n.equals("DungeonBeast");
    }
}
