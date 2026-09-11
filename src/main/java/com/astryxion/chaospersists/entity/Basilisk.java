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
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.AABB;

public class Basilisk extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Basilisk.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private int hurtTimer = 0;
    private float moveSpeed = 0.4f;

    public Basilisk(EntityType<? extends Basilisk> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 150;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 4, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 20, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Basilisk_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Basilisk_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Basilisk_stats.defense);
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
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (!this.isDeadOrDying() && this.getRandom().nextInt(200) == 0) {
            this.heal(1.0f);
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Basilisk_stats.health;
    }

    public int getBasiliskHealth() {
        return (int) this.getHealth();
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                this.getDeltaMovement().y + 0.25,
                this.getDeltaMovement().z);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(2) == 0) {
            return ChaosSounds.BASILISK_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.EMPERORSCORPION_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    private static Item chaosItem(String path) {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation("chaospersists", path));
    }

    private ItemStack dropItemRand(Item index, int par1) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        is);
        this.level().addFreshEntity(entityItem);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MyBasiliskScale, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 12 + this.getRandom().nextInt(6);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.EMERALD, 1);
        }
        i = 8 + this.getRandom().nextInt(5);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.CHICKEN, 1);
        }
        i = 3 + this.getRandom().nextInt(5);
        block15:
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(15);
            ItemStack is;
            switch (var3) {
                case 1: {
                    is = this.dropItemRand(Items.EMERALD, 1);
                    continue block15;
                }
                case 2: {
                    is = this.dropItemRand(Items.EMERALD_BLOCK, 1);
                    continue block15;
                }
                case 3: {
                    is = this.dropItemRand(ChaosPersists.MyEmeraldSword, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block15;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyEmeraldShovel, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block15;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyEmeraldPickaxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    }
                    continue block15;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyEmeraldAxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block15;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyEmeraldHoe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block15;
                }
                case 8: {
                    is = this.dropItemRand(chaosItem("emerald_helmet"), 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(2));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    continue block15;
                }
                case 9: {
                    is = this.dropItemRand(chaosItem("emerald_chest"), 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    continue block15;
                }
                case 10: {
                    is = this.dropItemRand(chaosItem("emerald_leggings"), 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    continue block15;
                }
                case 11: {
                    is = this.dropItemRand(chaosItem("emerald_boots"), 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block15;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    break;
                }
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                int var2 = 8;
                if (this.level().getDifficulty() == Difficulty.EASY) {
                    var2 = 10;
                }
                if (this.level().getDifficulty() == Difficulty.NORMAL) {
                    var2 = 12;
                } else if (this.level().getDifficulty() == Difficulty.HARD) {
                    var2 = 14;
                }
                if (this.getRandom().nextInt(3) == 0) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, var2 * 20, 0));
                }
                double ks = 1.5;
                double inair = 0.15;
                float f3 = (float) Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
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
    public boolean hurt(DamageSource source, float amount) {
        if (this.hurtTimer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        boolean ret = super.hurt(source, amount);
        if (ret) {
            this.hurtTimer = 30;
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurtTimer > 0) {
            --this.hurtTimer;
        }
        if (this.getRandom().nextInt(5) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                float reach = 6.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(3) == 0 || this.getRandom().nextInt(4) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.25);
                }
                e.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(75) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
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
        if (par1EntityLiving instanceof Basilisk) {
            return false;
        }
        if ("LeafMonster".equals(par1EntityLiving.getClass().getSimpleName())) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(24.0, 7.0, 24.0));
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

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public static boolean checkBasiliskSpawnRules(
            EntityType<Basilisk> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        ResourceLocation basiliskId = new ResourceLocation("chaospersists", "basilisk");
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
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, basiliskId)) {
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
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        List<Basilisk> nearby =
                level.getLevel().getEntitiesOfClass(Basilisk.class, new AABB(pos).inflate(20.0, 6.0, 20.0));
        return nearby.isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        ResourceLocation basiliskId = new ResourceLocation("chaospersists", "basilisk");
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
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, basiliskId)) {
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
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        for (Basilisk other :
                this.level().getEntitiesOfClass(Basilisk.class, this.getBoundingBox().inflate(20.0, 6.0, 20.0))) {
            if (other != this) {
                return false;
            }
        }
        return true;
    }
}
