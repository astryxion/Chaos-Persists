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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import com.astryxion.chaospersists.util.SurfaceWaterFloat;
import net.minecraft.world.entity.item.ItemEntity;
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
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.tags.TagKey;
import net.minecraftforge.common.ForgeMod;

public class SeaViper extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(SeaViper.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int stream_count = 0;
    private int hurt_timer = 0;
    private float moveSpeed = 0.35f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public SeaViper(EntityType<? extends SeaViper> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 120;
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
        this.getNavigation().setCanFloat(true);
        // Surface skim via SurfaceWaterFloat — FloatGoal hop-jumps with swim speed.
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.SeaViper_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.35f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.SeaViper_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.SeaViper_stats.defense)
                // 1.13+ water travel ignores MOVEMENT_SPEED; Forge swim speed is the only multiplier.
                .add(ForgeMod.SWIM_SPEED.get(), 4.0D);
    }

    /**
     * Forge multiplies liquid jumps by {@link ForgeMod#SWIM_SPEED}; keep vanilla 0.04 upward
     * so any jump (pathing, etc.) does not become a surface hop.
     */
    @Override
    protected void jumpInLiquid(TagKey<Fluid> fluidTag) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.04, 0.0));
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
        this.moveSpeed = this.isInWater() ? 0.75f : 0.25f;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        SurfaceWaterFloat.keepOnSurface(this);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.SeaViper_stats.health;
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

    public int getSeaViperHealth() {
        return (int) this.getHealth();
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(2) == 0) {
            return ChaosSounds.SEAVIPER_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SEAVIPER_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.SEAVIPER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        is);
        this.level().addFreshEntity(var3);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        ItemStack is = null;
        this.dropItemRand(ChaosPersists.SeaViperTongue, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 9 + this.level().getRandom().nextInt(6);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.COD, 1);
            this.dropItemRand(Items.CHICKEN, 1);
        }
        var4 = this.level().getRandom().nextInt(20);
        switch (var4) {
            case 1: {
                is = this.dropItemRand(Items.IRON_INGOT, 1);
                break;
            }
            case 3: {
                is = this.dropItemRand(Items.IRON_SWORD, 1);
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.SHARPNESS, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BANE_OF_ARTHROPODS, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.KNOCKBACK, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.MOB_LOOTING, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_ASPECT, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.SHARPNESS, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 4: {
                is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 5: {
                is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 6: {
                is = this.dropItemRand(Items.IRON_AXE, 1);
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 7: {
                is = this.dropItemRand(Items.IRON_HOE, 1);
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 8: {
                is = this.dropItemRand(Items.IRON_HELMET, 1);
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.RESPIRATION, 1 + this.level().getRandom().nextInt(2));
                }
                if (this.level().getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.level().getRandom().nextInt(5));
                break;
            }
            case 9: {
                is = this.dropItemRand(Items.IRON_CHESTPLATE, 1);
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                break;
            }
            case 10: {
                is = this.dropItemRand(Items.IRON_LEGGINGS, 1);
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                break;
            }
            case 11: {
                is = this.dropItemRand(Items.IRON_BOOTS, 1);
                if (this.level().getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 5 + this.level().getRandom().nextInt(5));
                }
                if (this.level().getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.level().getRandom().nextInt(4));
                break;
            }
            case 13: {
                this.dropItemRand(Items.IRON_BLOCK, 1);
                break;
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity instanceof LivingEntity living) {
                double ks = 0.8;
                double inair = 0.14;
                float f3 = (float) Mth.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!living.isAlive() || par1Entity instanceof Player) {
                    inair *= 2.0;
                }
                living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
                int var2 = 6;
                if (this.level().getDifficulty() == Difficulty.EASY) {
                    var2 = 8;
                    if (this.level().getDifficulty() == Difficulty.NORMAL) {
                        var2 = 10;
                    } else if (this.level().getDifficulty() == Difficulty.HARD) {
                        var2 = 12;
                    }
                }
                if (this.level().getRandom().nextInt(2) == 1) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, var2 * 20, 0));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (this.hurt_timer <= 0 && !this.isInvulnerableTo(par1DamageSource)) {
            ret = super.hurt(par1DamageSource, par2);
            if (ret) {
                this.hurt_timer = 5;
            }
        }
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (e instanceof SeaViper) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        return ret;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        BlockState bid;
        int d;
        int j;
        int found = 0;
        for (i = -dy; i <= dy; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                if (bid.is(Blocks.WATER) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                bid = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                if (!bid.is(Blocks.WATER) || (d = dx * dx + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                if (bid.is(Blocks.WATER) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                if (!bid.is(Blocks.WATER) || (d = dy * dy + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dy; j <= dy; ++j) {
                bid = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                if (bid.is(Blocks.WATER) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                if (!bid.is(Blocks.WATER) || (d = dz * dz + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
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
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
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
                if (this.getRandom().nextInt(150) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.discard();
                    return;
                }
            }
        }
        if (this.getRandom().nextInt(5) == 1) {
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
                if (this.distanceToSqr(e)
                        < (double)
                                ((4.5f + e.getBbWidth() / 2.0f)
                                        * (4.5f + e.getBbWidth() / 2.0f))) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(2) == 0 || this.getRandom().nextInt(4) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.5);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(100) == 1
                && this.isInWater()
                && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.playSound(
                    SoundEvents.GENERIC_SPLASH,
                    1.5f,
                    this.getRandom().nextFloat() * 0.2f + 0.9f);
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof SeaViper) {
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
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(18.0, 4.0, 18.0));
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

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    private static boolean isSeaViperSpawnerNear(LevelAccessor level, BlockPos origin) {
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
                    if (id != null && "Sea Viper".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkSeaViperSpawnRules(
            EntityType<SeaViper> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (isSeaViperSpawnerNear(level, pos)) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        List<SeaViper> nearby =
                level.getLevel()
                        .getEntitiesOfClass(SeaViper.class, new AABB(pos).inflate(16.0, 5.0, 16.0));
        if (!nearby.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (isSeaViperSpawnerNear(level, this.blockPosition())) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        List<SeaViper> nearby =
                this.level()
                        .getEntitiesOfClass(
                                SeaViper.class, this.getBoundingBox().inflate(16.0, 5.0, 16.0));
        for (SeaViper sv : nearby) {
            if (sv != this) {
                return false;
            }
        }
        return true;
    }
}
