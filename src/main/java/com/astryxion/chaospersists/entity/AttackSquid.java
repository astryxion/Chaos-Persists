package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.InkSack;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class AttackSquid extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(AttackSquid.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private LivingEntity buddy = null;
    private final float moveSpeed = 0.25f;
    private int wasshot = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public AttackSquid(EntityType<? extends AttackSquid> type, Level level) {
        super(type, level);
        this.xpReward = 15;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.AttackSquid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.25f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.AttackSquid_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.AttackSquid_stats.defense);
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

    public void setWasShot() {
        this.wasshot = 250;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.AttackSquid_stats.health;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SQUID_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.SQUID_DEATH;
    }

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_"));
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.COD;
    }

    private ItemStack dropItemRand(Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        ItemEntity itemEntity =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) this.getRandom().nextInt(2) - (double) this.getRandom().nextInt(2),
                        this.getY() + 1.0,
                        this.getZ() + (double) this.getRandom().nextInt(2) - (double) this.getRandom().nextInt(2),
                        stack);
        this.level().addFreshEntity(itemEntity);
        return stack;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.dropFewItems();
    }

private void dropFewItems() {
        ItemStack is = null;
        int var4 = this.getRandom().nextInt(50);
        switch (var4) {
            case 0: {
                is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                break;
            }
            case 1: {
                is = this.dropItemRand(Items.GOLD_INGOT, 1);
                break;
            }
            case 2: {
                is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                break;
            }
            case 3: {
                is = this.dropItemRand(Items.GOLDEN_SWORD, 1);
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
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 4: {
                is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 5: {
                is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 6: {
                is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 7: {
                is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 8: {
                is = this.dropItemRand(Items.GOLDEN_HELMET, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 1 + this.getRandom().nextInt(5));
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
                if (this.getRandom().nextInt(6) != 1) break;
                is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 9: {
                is = this.dropItemRand(Items.GOLDEN_CHESTPLATE, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) break;
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 10: {
                is = this.dropItemRand(Items.GOLDEN_LEGGINGS, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) break;
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 11: {
                is = this.dropItemRand(Items.GOLDEN_BOOTS, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) break;
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 12: {
                this.dropItemRand(Items.GOLDEN_APPLE, 1);
                break;
            }
            case 13: {
                this.dropItemRand(Items.GOLD_BLOCK, 1);
                break;
            }
            case 14: {
                this.dropItemRand(Items.ENCHANTED_GOLDEN_APPLE, 1);
                break;
            }
            case 15: 
            case 16: 
            case 17: {
                this.dropItemRand(Items.INK_SAC, 1);
                break;
            }
        }
        int i = 1 + this.getRandom().nextInt(3);
        for (var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.COD, 1);
        }
    }

    
    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        if (this.wasshot != 0) {
            return false;
        }
        return super.causeFallDamage(fallDistance, damageMultiplier, source);
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isDeadOrDying()) {
            return false;
        }
        Entity e = source.getEntity();
        if (e instanceof AttackSquid) {
            return false;
        }
        if (e instanceof WaterBall) {
            return false;
        }
        if (e instanceof WaterDragon) {
            return false;
        }
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (e instanceof AttackSquid) {
                return false;
            }
            if (e instanceof WaterDragon) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        boolean ret = super.hurt(source, amount);
        if ((this.getHealth() <= 0.0f || this.isDeadOrDying())
                && !this.level().dimension().equals(ChaosPersists.getDimensionKey(5))
                && !this.level().isClientSide
                && e instanceof Player
                && this.getRandom().nextInt(15) == 1
                && ChaosPersists.KrakenEnable != 0
                && this.wasshot == 0) {
            int j = 1 + this.getRandom().nextInt(3);
            for (int i = 0; i < j; ++i) {
                spawnCreature(
                        this.level(),
                        "chaospersists:the_kraken",
                        this.getX() + (double) this.getRandom().nextInt(4) - (double) this.getRandom().nextInt(4),
                        170.0,
                        this.getZ() + (double) this.getRandom().nextInt(4) - (double) this.getRandom().nextInt(4));
            }
        }
        return ret;
    }

    private static boolean isGirlfriend(LivingEntity e) {
        return e.getClass().getName().endsWith(".Girlfriend");
    }

    private static boolean isBoyfriend(LivingEntity e) {
        return e.getClass().getName().endsWith(".Boyfriend");
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                Block bid = state.getBlock();
                int d = dx * dx + j * j + i * i;
                if (bid == Blocks.WATER && d < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                bid = state.getBlock();
                if (bid == Blocks.WATER && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x - dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                Block bid = state.getBlock();
                int d = dy * dy + j * j + i * i;
                if (bid == Blocks.WATER && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                bid = state.getBlock();
                if (bid == Blocks.WATER && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y - dy;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                Block bid = state.getBlock();
                int d = dz * dz + j * j + i * i;
                if (bid == Blocks.WATER && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                bid = state.getBlock();
                if (bid == Blocks.WATER && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z - dz;
                    ++found;
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
        if (this.wasshot > 0) {
            --this.wasshot;
            if (this.wasshot == 0) {
                this.discard();
                return;
            }
        }
        if (!this.isInWater() && this.getRandom().nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 5) {
                    j = 5;
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
                if (this.getRandom().nextInt(25) == 1) {
                    this.hurt(this.damageSources().generic(), 1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.discard();
                    return;
                }
            }
        }
        if (this.getRandom().nextInt(10) == 1) {
            LivingEntity target = this.findSomethingToAttack();
            if (target != null) {
                if (this.distanceToSqr(target) < 9.0) {
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
                        this.doHurtTarget(target);
                    }
                } else {
                    this.getNavigation().moveTo(target, 1.2);
                    this.watercanon(target);
                }
            } else {
                if (this.buddy != null) {
                    this.getNavigation().moveTo(this.buddy, 1.0);
                }
                this.setAttacking(0);
            }
        }
    }

    private void watercanon(LivingEntity e) {
        double yoff = 1.0;
        double xzoff = 1.2;
        if (this.getRandom().nextInt(5) == 1) {
            if (this.getRandom().nextInt(3) == 1) {
                InkSack projectile =
                        new InkSack(
                                ChaosPersists.ENTITY_TYPE_INK_SACK.get(),
                                e.getX() - this.getX(),
                                e.getY() + 0.75 - (this.getY() + yoff),
                                e.getZ() - this.getZ(),
                                this.level());
                projectile.moveTo(
                        this.getX() - xzoff * Math.sin(Math.toRadians(this.getYHeadRot())),
                        this.getY() + yoff,
                        this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                        this.getYHeadRot(),
                        this.getXRot());
                double var3 = e.getX() - this.getX();
                double var5 = e.getY() + 0.25 - projectile.getY();
                double var7 = e.getZ() - this.getZ();
                float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                projectile.shoot(var3, var5 + (double) var9, var7, 1.4f, 5.0f);
                this.level()
                        .playSound(
                                null,
                                this.blockPosition(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                0.75f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(projectile);
            } else {
                WaterBall projectile =
                        new WaterBall(
                                ChaosPersists.ENTITY_TYPE_WATER_BALL.get(),
                                e.getX() - this.getX(),
                                e.getY() + 0.75 - (this.getY() + yoff),
                                e.getZ() - this.getZ(),
                                this.level());
                projectile.moveTo(
                        this.getX() - xzoff * Math.sin(Math.toRadians(this.getYHeadRot())),
                        this.getY() + yoff,
                        this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                        this.getYHeadRot(),
                        this.getXRot());
                double var3 = e.getX() - this.getX();
                double var5 = e.getY() + 0.25 - projectile.getY();
                double var7 = e.getZ() - this.getZ();
                float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                projectile.shoot(var3, var5 + (double) var9, var7, 1.4f, 5.0f);
                this.level()
                        .playSound(
                                null,
                                this.blockPosition(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                0.75f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(projectile);
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (isGirlfriend(par1EntityLiving)) {
            return true;
        }
        if (isBoyfriend(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof Zombie) {
            return true;
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        if (par1EntityLiving instanceof Spider) {
            return true;
        }
        if (par1EntityLiving instanceof CaveSpider) {
            return true;
        }
        if (par1EntityLiving instanceof Ghost) {
            return false;
        }
        if (par1EntityLiving instanceof GhostSkelly) {
            return false;
        }
        if (par1EntityLiving instanceof Lizard) {
            return true;
        }
        if (par1EntityLiving instanceof AttackSquid) {
            if (this.getRandom().nextInt(5) == 1) {
                this.buddy = par1EntityLiving;
            }
            return false;
        }
        if (this.wasshot != 0) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> entities =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 4.0, 10.0));
        Collections.sort(entities, this.targetSorter);
        Iterator<LivingEntity> var2 = entities.iterator();
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        while (var2.hasNext()) {
            LivingEntity candidate = var2.next();
            if (!this.isSuitableTarget(candidate, false)) {
                continue;
            }
            return candidate;
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("WasShot", this.wasshot);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.wasshot = tag.getInt("WasShot");
    }

    public static boolean checkAttackSquidSpawnRules(
            EntityType<AttackSquid> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return pos.getY() >= 50 && MyUtils.isDay(level);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world) {
            return this.getY() >= 50.0 && world.isDay();
        }
        return this.getY() >= 50.0;
    }
}
