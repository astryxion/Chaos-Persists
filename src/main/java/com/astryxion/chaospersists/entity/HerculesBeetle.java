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
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
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
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HerculesBeetle extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(HerculesBeetle.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private int hurt_timer = 0;
    private float moveSpeed = 0.25f;

    public HerculesBeetle(EntityType<? extends HerculesBeetle> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.setMaxUpStep(1.0F);
        this.xpReward = 200;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 4, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.HerculesBeetle_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.HerculesBeetle_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.HerculesBeetle_stats.defense);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.HerculesBeetle_stats.defense;
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
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
    }

    public int getHerculesBeetleHealth() {
        return (int) this.getHealth();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.HerculesBeetle_stats.health;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.HERCULES_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y + 0.25, motion.z);
        this.setPos(this.getX(), this.getY() + 0.5, this.getZ());
    }

    private ItemStack dropItemRand(Item index, int par1) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        is);
        this.level().addFreshEntity(entityItem);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MyBigHammer, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 4 + this.getRandom().nextInt(8);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        i = 1 + this.getRandom().nextInt(5);
        block16:
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(20);
            ItemStack is;
            switch (var3) {
                case 0: {
                    continue block16;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block16;
                }
                case 2: {
                    is = this.dropItemRand(Items.DIAMOND_BLOCK, 1);
                    continue block16;
                }
                case 3: {
                    is = this.dropItemRand(Items.DIAMOND_SWORD, 1);
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
                    continue block16;
                }
                case 4: {
                    is = this.dropItemRand(Items.DIAMOND_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block16;
                }
                case 5: {
                    is = this.dropItemRand(Items.DIAMOND_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    }
                    continue block16;
                }
                case 6: {
                    is = this.dropItemRand(Items.DIAMOND_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block16;
                }
                case 7: {
                    is = this.dropItemRand(Items.DIAMOND_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    continue block16;
                }
                case 8: {
                    is = this.dropItemRand(Items.DIAMOND_HELMET, 1);
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
                    continue block16;
                }
                case 9: {
                    is = this.dropItemRand(Items.DIAMOND_CHESTPLATE, 1);
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
                        continue block16;
                    }
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block16;
                }
                case 10: {
                    is = this.dropItemRand(Items.DIAMOND_LEGGINGS, 1);
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
                        continue block16;
                    }
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block16;
                }
                case 11: {
                    is = this.dropItemRand(Items.DIAMOND_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) {
                        continue block16;
                    }
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block16;
                }
                case 12: {
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
    public boolean doHurtTarget(Entity par1Entity) {
        double ks = 0.45;
        double inair = 1.25;
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity instanceof LivingEntity living) {
                float f3 = (float) Mth.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!par1Entity.isAlive() || par1Entity instanceof Player) {
                    inair *= 2.0;
                }
                living.push(
                        Math.cos(f3) * ks,
                        inair * (double) Math.abs(this.getRandom().nextFloat()),
                        Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.hurt_timer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.hurt_timer = 20;
        }
        Entity e = par1DamageSource.getEntity();
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.getRandom().nextInt(4) == 0) {
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
                float reach = 5.0f + e.getBbWidth() / 2.0f;
                if (this.distanceToSqr(e) < (double) (reach * reach)) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(3) == 0 || this.getRandom().nextInt(4) == 1) {
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
                                                1.4f,
                                                1.0f);
                            } else {
                                this.level()
                                        .playSound(
                                                null,
                                                e.getX(),
                                                e.getY(),
                                                e.getZ(),
                                                ChaosSounds.SCORPION_LIVING,
                                                SoundSource.HOSTILE,
                                                1.0f,
                                                1.0f);
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(150) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(2.0f);
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
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Creeper) {
            return false;
        }
        if (par1EntityLiving instanceof HerculesBeetle) {
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
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
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

    public static boolean checkHerculesBeetleSpawnRules(
            EntityType<HerculesBeetle> type,
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
                    if (id != null && "Hercules Beetle".equals(id.getPath())) {
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
        if (pos.getY() < 50) {
            return false;
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 2; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        List<HerculesBeetle> nearby =
                level.getLevel()
                        .getEntitiesOfClass(HerculesBeetle.class, new AABB(pos).inflate(16.0, 6.0, 16.0));
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
                    if (id != null && "Hercules Beetle".equals(id.getPath())) {
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
        if (this.getY() < 50.0) {
            return false;
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 2; i < 5; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        for (HerculesBeetle other :
                this.level()
                        .getEntitiesOfClass(
                                HerculesBeetle.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0))) {
            if (other != this) {
                return false;
            }
        }
        return true;
    }
}
