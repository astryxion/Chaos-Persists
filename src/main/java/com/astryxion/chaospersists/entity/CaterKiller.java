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
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
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
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class CaterKiller extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(CaterKiller.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> PLAY_NICELY =
            SynchedEntityData.defineId(CaterKiller.class, EntityDataSerializers.INT);
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.35f;
    int foundmob = 0;
    int ticker = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;
    private int lastPlayNicely = -1;

    public CaterKiller(EntityType<? extends CaterKiller> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 200;
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
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.CaterKiller_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.35f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.CaterKiller_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.CaterKiller_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (this.getPlayNicely() != 0) {
            return EntityDimensions.scalable(1.45f, 2.3f);
        }
        return EntityDimensions.scalable(2.9f, 4.6f);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
        }
        return ret;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        int pn = this.getPlayNicely();
        if (pn != this.lastPlayNicely) {
            this.lastPlayNicely = pn;
            this.refreshDimensions();
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.CaterKiller_stats.health;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(3) == 0) {
            return ChaosSounds.CATERKILLER_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ChaosSounds.CATERKILLER_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.CATERKILLER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5),
                        this.getY() + 1.0,
                        this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5),
                        is);
        this.level().addFreshEntity(var3);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        ItemStack is = null;
        this.dropItemRand(ChaosPersists.CaterKillerJaw, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (var4 = 0; var4 < 10; ++var4) {
            this.dropItemRand(Items.LEATHER, 1);
        }
        for (var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        int i = 1 + this.getRandom().nextInt(5);
        block17:
        for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(20);
            switch (var3) {
                case 0: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateSword, 1);
                    continue block17;
                }
                case 1: {
                    is = this.dropItemRand(ChaosPersists.MyRuby, 1);
                    continue block17;
                }
                case 2: {
                    is = this.dropItemRand(Items.DIAMOND_BLOCK, 1);
                    continue block17;
                }
                case 3: {
                    is = this.dropItemRand(ChaosPersists.MyRubySword, 1);
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
                    continue block17;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyRubyShovel, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block17;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyRubyPickaxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block17;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyRubyAxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block17;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyRubyHoe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block17;
                }
                case 8: {
                    is = this.dropItemRand(ChaosPersists.RubyHelmet, 1);
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
                    }
                    continue block17;
                }
                case 9: {
                    is = this.dropItemRand(ChaosPersists.RubyBody, 1);
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
                    }
                    continue block17;
                }
                case 10: {
                    is = this.dropItemRand(ChaosPersists.RubyLegs, 1);
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
                    }
                    continue block17;
                }
                case 11: {
                    is = this.dropItemRand(ChaosPersists.RubyBoots, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                    }
                    continue block17;
                }
                case 12: {
                    is = this.dropItemRand(ChaosPersists.MyUltimateBow, 1);
                    break;
                }
            }
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            for (var4 = 0; var4 < 25; ++var4) {
                spawnCreature(serverLevel, "Butterfly", this.getX(), this.getY() + 1.0, this.getZ());
            }
        }
    }

    public static Entity spawnCreature(ServerLevel level, String par1, double par2, double par4, double par6) {
        if (level == null) {
            return null;
        }
        ResourceLocation key = resolveSpawnId(par1);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(key);
        if (type == null) {
            return null;
        }
        Entity var8 = type.create(level);
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
            level.addFreshEntity(var8);
            if (var8 instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return var8;
    }

    private static ResourceLocation resolveSpawnId(String par1) {
        if (par1.contains(":")) {
            return new ResourceLocation(par1);
        }
        return switch (par1) {
            case "Butterfly" -> new ResourceLocation("chaospersists", "butterfly");
            case "Brutalfly" -> new ResourceLocation("chaospersists", "brutalfly");
            default ->
                    new ResourceLocation(
                            "chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
        };
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity instanceof LivingEntity living) {
                double ks = 1.2;
                double inair = 0.1;
                float f3 = (float) Mth.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!living.isAlive() || par1Entity instanceof Player) {
                    inair *= 2.0;
                }
                living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    private boolean isEdibleTreeBlock(Block bid) {
        if (bid == Blocks.VINE || bid == ChaosPersists.MyDT) {
            return true;
        }
        if (bid == ChaosPersists.MyAppleLeaves
                || bid == ChaosPersists.MyExperienceLeaves
                || bid == ChaosPersists.MyScaryLeaves
                || bid == ChaosPersists.MyPeachLeaves
                || bid == ChaosPersists.MyCherryLeaves) {
            return true;
        }
        BlockState state = bid.defaultBlockState();
        return state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS);
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (i = -dy; i <= dy; ++i) {
            for (j = -dz; j <= dz; ++j) {
                pos.set(x + dx, y + i, z + j);
                bid = this.level().getBlockState(pos).getBlock();
                if (this.isEdibleTreeBlock(bid) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                pos.set(x - dx, y + i, z + j);
                bid = this.level().getBlockState(pos).getBlock();
                if (!this.isEdibleTreeBlock(bid) || (d = dx * dx + j * j + i * i) >= this.closest) {
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
                pos.set(x + i, y + dy, z + j);
                bid = this.level().getBlockState(pos).getBlock();
                if (this.isEdibleTreeBlock(bid) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                pos.set(x + i, y - dy, z + j);
                bid = this.level().getBlockState(pos).getBlock();
                if (!this.isEdibleTreeBlock(bid) || (d = dy * dy + j * j + i * i) >= this.closest) {
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
                pos.set(x + i, y + j, z + dz);
                bid = this.level().getBlockState(pos).getBlock();
                if (this.isEdibleTreeBlock(bid) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                pos.set(x + i, y + j, z - dz);
                bid = this.level().getBlockState(pos).getBlock();
                if (!this.isEdibleTreeBlock(bid) || (d = dz * dz + j * j + i * i) >= this.closest) {
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
        int i;
        int j;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.getHealth() + 1.0f < this.getMaxHealth()) {
            ++this.ticker;
            if (this.ticker > 2400) {
                if (this.level() instanceof ServerLevel serverLevel) {
                    spawnCreature(serverLevel, "Brutalfly", this.getX(), this.getY() + 4.0, this.getZ());
                    for (int i2 = 0; i2 < 10; ++i2) {
                        spawnCreature(
                                serverLevel,
                                "Butterfly",
                                this.getX(),
                                this.getY() + 1.0 + (double) this.getRandom().nextInt(4),
                                this.getZ());
                    }
                }
                this.playSound(
                        SoundEvents.GENERIC_EXPLODE,
                        1.0f,
                        this.getRandom().nextFloat() * 0.2f + 0.9f);
                this.discard();
                return;
            }
        }
        boolean inWeb = false;
        BlockPos.MutableBlockPos webPos = new BlockPos.MutableBlockPos();
        for (i = -2; i <= 2; ++i) {
            for (j = -1; j < 5; ++j) {
                for (int k = -2; k <= 2; ++k) {
                    webPos.set((int) this.getX() + i, (int) this.getY() + j, (int) this.getZ() + k);
                    if (this.level().getBlockState(webPos).is(Blocks.COBWEB)) {
                        inWeb = true;
                        break;
                    }
                }
                if (inWeb) {
                    break;
                }
            }
            if (inWeb) {
                break;
            }
        }
        if (inWeb) {
            for (i = -2; i <= 2; ++i) {
                for (j = -1; j < 5; ++j) {
                    for (int k = -2; k <= 2; ++k) {
                        webPos.set((int) this.getX() + i, (int) this.getY() + j, (int) this.getZ() + k);
                        if (!this.level().getBlockState(webPos).is(Blocks.COBWEB)) {
                            continue;
                        }
                        this.level().setBlock(webPos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
        if (this.getRandom().nextInt(4) == 0) {
            LivingEntity e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (this.getRandom().nextInt(200) == 0) {
                this.setTarget(null);
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.setTarget(e);
                this.foundmob = 1;
                if (this.distanceToSqr(e)
                        < (double) ((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(3) == 0 || this.getRandom().nextInt(4) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.setAttacking(0);
                    this.getNavigation().moveTo(e, 1.25);
                    if (this.getRandom().nextInt(4) == 0) {
                        double dx = e.getX();
                        double dz = e.getZ();
                        dx += (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0;
                        dz += (double) (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 2.0;
                        for (i = 2; i > -2; --i) {
                            webPos.set((int) dx, (int) e.getY() + i + 1, (int) dz);
                            if (!this.level().getBlockState(webPos).isAir()) {
                                continue;
                            }
                            webPos.set((int) dx, (int) e.getY() + i, (int) dz);
                            if (this.level().getBlockState(webPos).isAir()) {
                                continue;
                            }
                            webPos.set((int) dx, (int) e.getY() + i + 1, (int) dz);
                            this.level().setBlock(webPos, Blocks.COBWEB.defaultBlockState(), 3);
                            break;
                        }
                    }
                }
            } else {
                this.setAttacking(0);
                this.foundmob = 0;
            }
        }
        if ((this.getRandom().nextInt(8) == 0 && this.getHealth() < (float) this.mygetMaxHealth()
                        || this.getRandom().nextInt(30) == 0)
                && ChaosPersists.PlayNicely == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (i = 1; i < 13; ++i) {
                j = i;
                if (j > 9) {
                    j = 9;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 9) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                if (this.foundmob == 0) {
                    this.getNavigation().moveTo((double) this.tx, (double) this.ty, (double) this.tz, 1.0);
                }
                if (this.closest < 81) {
                    if (this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level().setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                    }
                    this.heal(2.0f);
                    if (this.getRandom().nextInt(20) == 1) {
                        this.playSound(
                                SoundEvents.PLAYER_BURP,
                                1.0f,
                                this.getRandom().nextFloat() * 0.2f + 0.9f);
                    }
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
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.MyCanSee(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof CaterKiller) {
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
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity var4;
        while (var2.hasNext()) {
            var4 = var2.next();
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

    public static boolean checkCaterKillerSpawnRules(
            EntityType<CaterKiller> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
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
                    if (id != null && "CaterKiller".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (random.nextInt(10) != 0) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.isAir() || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        List<CaterKiller> nearby =
                level.getLevel().getEntitiesOfClass(CaterKiller.class, new AABB(pos).inflate(48.0, 16.0, 48.0));
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
                    if (id != null && "CaterKiller".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.getRandom().nextInt(10) != 0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.isAir() || state.is(BlockTags.LEAVES) || state.is(BlockTags.LOGS)) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return this.level()
                .getEntitiesOfClass(CaterKiller.class, this.getBoundingBox().inflate(48.0, 16.0, 48.0))
                .stream()
                .filter(other -> other != this)
                .findAny()
                .isEmpty();
    }

    public boolean MyCanSee(LivingEntity e) {
        double xzoff = 2.5;
        int nblks = 10;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        float startx = (float) cx;
        float starty = (float) (this.getY() + 3.0);
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
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < nblks; ++i) {
            pos.set((int) (startx += dx), (int) (starty += dy), (int) (startz += dz));
            BlockState state = this.level().getBlockState(pos);
            if (state.isAir()
                    || state.is(Blocks.COBWEB)
                    || state.is(Blocks.GRASS)
                    || state.is(Blocks.TALL_GRASS)
                    || state.is(BlockTags.LEAVES)) {
                continue;
            }
            return false;
        }
        return true;
    }
}
