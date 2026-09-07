package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import java.lang.reflect.Method;
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
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Kraken extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Kraken.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> PLAY_NICELY =
            SynchedEntityData.defineId(Kraken.class, EntityDataSerializers.INT);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private BlockPos currentFlightTarget = null;
    private LivingEntity caught = null;
    private int newtarget = 0;
    private int release = 0;
    private int weather_set = 10;
    private int long_enough = 3600;
    private int call_reinforcements = 0;
    private boolean hit_by_player = false;
    private int straight_down = 1;
    private int hurt_timer = 0;
    private int lastPlayNicely = -1;

    public Kraken(EntityType<? extends Kraken> type, Level level) {
        super(type, level);
        this.xpReward = 500;
        this.fireImmune();
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
        this.setNoGravity(true);
        this.applyKrakenDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Kraken_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.3700000047683716)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Kraken_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Kraken_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
    }

    private void applyKrakenDimensions() {
        this.refreshDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (this.getPlayNicely() == 0) {
            return EntityDimensions.scalable(4.0f, 15.0f);
        }
        return EntityDimensions.scalable(1.3333334f, 5.0f);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Kraken_stats.health;
    }

    public int getKrakenHealth() {
        return (int) this.getHealth();
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

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation(
                                "chaospersists", par1.toLowerCase().replace(" ", "_"));
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
    public void tick() {
        super.tick();
        if (this.isDeadOrDying()) {
            return;
        }
        int pn = this.getPlayNicely();
        if (pn != this.lastPlayNicely) {
            this.lastPlayNicely = pn;
            this.applyKrakenDimensions();
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget =
                    new BlockPos((int) this.getX(), (int) (this.getY() - 10.0), (int) this.getZ());
        } else {
            Vec3 motion = this.getDeltaMovement();
            double newY =
                    this.getY() < (double) this.currentFlightTarget.getY()
                            ? motion.y * 0.72
                            : motion.y * 0.5;
            this.setDeltaMovement(motion.x, newY, motion.z);
        }
        if (this.weather_set > 0 && ChaosPersists.PlayNicely == 0) {
            --this.weather_set;
            if (this.weather_set == 0 && !this.level().isClientSide && this.level() instanceof ServerLevel serverLevel) {
                if (!serverLevel.isRaining()) {
                    serverLevel.setWeatherParameters(0, 300, true, true);
                } else {
                    serverLevel.setWeatherParameters(0, 300, true, true);
                }
                this.weather_set = 100;
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("LongEnough", this.long_enough);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.long_enough = tag.getInt("LongEnough");
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(5) == 0) {
            return ChaosSounds.KRAKEN_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 2.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.QUARTZ;
    }

    private ItemStack dropItemRand(Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        ItemEntity itemEntity =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(8)
                                - (double) ChaosPersists.ChaosRand.nextInt(8),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(8)
                                - (double) ChaosPersists.ChaosRand.nextInt(8),
                        stack);
        this.level().addFreshEntity(itemEntity);
        return stack;
    }

    private ItemStack dropItemRandMod(String path, int count) {
        Item item =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", path));
        if (item == null) {
            return null;
        }
        return this.dropItemRand(item, count);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.dropFewItems();
    }

private void dropFewItems() {
        int var4;
        ItemStack is = null;
        this.dropItemRandMod("krakentooth", 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 120 + this.getRandom().nextInt(160);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.INK_SAC, 1);
        }
        int i = 5 + this.getRandom().nextInt(10);
        block56 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(53);
            switch (var3) {
                case 0: {
                    is = this.dropItemRandMod("ultimatesword", 1);
                    continue block56;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block56;
                }
                case 2: {
                    is = this.dropItemRand(Items.DIAMOND_BLOCK, 1);
                    continue block56;
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 4: {
                    is = this.dropItemRand(Items.DIAMOND_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 5: {
                    is = this.dropItemRand(Items.DIAMOND_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 6: {
                    is = this.dropItemRand(Items.DIAMOND_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 7: {
                    is = this.dropItemRand(Items.DIAMOND_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 8: {
                    is = this.dropItemRand(Items.DIAMOND_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 9: {
                    is = this.dropItemRand(Items.DIAMOND_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 10: {
                    is = this.dropItemRand(Items.DIAMOND_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 11: {
                    is = this.dropItemRand(Items.DIAMOND_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 12: {
                    is = this.dropItemRandMod("ultimatebow", 1);
                    continue block56;
                }
                case 13: {
                    is = this.dropItemRandMod("ultimateaxe", 1);
                    continue block56;
                }
                case 14: {
                    is = this.dropItemRand(Items.IRON_INGOT, 1);
                    continue block56;
                }
                case 15: {
                    is = this.dropItemRandMod("ultimatepickaxe", 1);
                    continue block56;
                }
                case 16: {
                    is = this.dropItemRand(Items.IRON_SWORD, 1);
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 17: {
                    is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 18: {
                    is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 19: {
                    is = this.dropItemRand(Items.IRON_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 20: {
                    is = this.dropItemRand(Items.IRON_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 21: {
                    is = this.dropItemRand(Items.IRON_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
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
                        is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 22: {
                    is = this.dropItemRand(Items.IRON_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 23: {
                    is = this.dropItemRand(Items.IRON_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 24: {
                    is = this.dropItemRand(Items.IRON_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 25: {
                    is = this.dropItemRandMod("ultimateshovel", 1);
                    continue block56;
                }
                case 26: {
                    this.dropItemRand(Items.IRON_BLOCK, 1);
                    continue block56;
                }
                case 27: {
                    is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                    continue block56;
                }
                case 28: {
                    is = this.dropItemRand(Items.GOLD_INGOT, 1);
                    continue block56;
                }
                case 29: {
                    is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                    continue block56;
                }
                case 30: {
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 31: {
                    is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 32: {
                    is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 33: {
                    is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 34: {
                    is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 35: {
                    is = this.dropItemRand(Items.GOLDEN_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
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
                        is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 36: {
                    is = this.dropItemRand(Items.GOLDEN_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 37: {
                    is = this.dropItemRand(Items.GOLDEN_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 38: {
                    is = this.dropItemRand(Items.GOLDEN_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 39: {
                    this.dropItemRand(Items.GOLDEN_APPLE, 1);
                    continue block56;
                }
                case 40: {
                    this.dropItemRand(Items.GOLD_BLOCK, 1);
                    continue block56;
                }
                case 41: {
                    this.dropItemRand(Items.ENCHANTED_GOLDEN_APPLE, 1);
                    continue block56;
                }
                case 42: {
                    is = this.dropItemRandMod("experiencesword", 1);
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 43: {
                    is = this.dropItemRandMod("experience_helmet", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
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
                        is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 44: {
                    is = this.dropItemRandMod("experience_chest", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 45: {
                    is = this.dropItemRandMod("experience_leggings", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.THORNS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 46: {
                    is = this.dropItemRandMod("experience_boots", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block56;
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block56;
                }
                case 47: {
                    is = this.dropItemRandMod("amethystsword", 1);
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
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 48: {
                    is = this.dropItemRandMod("amethystshovel", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 49: {
                    is = this.dropItemRandMod("amethystpickaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 50: {
                    is = this.dropItemRandMod("amethystaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 51: {
                    is = this.dropItemRandMod("amethysthoe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block56;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block56;
                }
                case 52: {
                    is = this.dropItemRandMod("blockamethyst", 1);
                    break;
                }
            }
        }
    }

    
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.long_enough <= 0) {
            return true;
        }
        if (this.getY() > 150.0 && this.getHealth() < (float) (this.mygetMaxHealth() / 2)) {
            return true;
        }
        if (this.getY() > 180.0 && this.long_enough <= 0) {
            this.discard();
            return true;
        }
        return false;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        int i;
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.long_enough > 0) {
            --this.long_enough;
        }
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.getRandom().nextInt(400) == 1 && ChaosPersists.PlayNicely == 0) {
            if (this.level() instanceof ServerLevel serverLevel) {
                LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(serverLevel);
                if (lightning != null) {
                    lightning.moveTo(this.getX(), this.getY() - 16.0, this.getZ());
                    serverLevel.addFreshEntity(lightning);
                }
            }
        }
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.newtarget != 0
                || this.getRandom().nextInt(250) == 1
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 9.1) {
            int ground_dist;
            this.newtarget = 0;
            for (ground_dist = 0; ground_dist < 31; ++ground_dist) {
                bid =
                        this.level()
                                .getBlockState(
                                        new BlockPos(
                                                (int) this.getX(),
                                                (int) this.getY() - ground_dist,
                                                (int) this.getZ()))
                                .getBlock();
                if (bid == Blocks.AIR) {
                    continue;
                }
                this.straight_down = 0;
                break;
            }
            ground_dist = 20 - ground_dist;
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.getRandom().nextInt(6) + 12;
                xdir = this.getRandom().nextInt(6) + 12;
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                if (this.straight_down != 0) {
                    xdir = 0;
                    zdir = 0;
                }
                this.currentFlightTarget =
                        new BlockPos(
                                (int) this.getX() + xdir,
                                (int) this.getY() + ground_dist + this.getRandom().nextInt(9) - 6,
                                (int) this.getZ() + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR
                        && !this.canSeeTarget(
                                (double) this.currentFlightTarget.getX(),
                                (double) this.currentFlightTarget.getY(),
                                (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
            if (this.long_enough <= 0
                    || this.getY() < 200.0 && this.getHealth() < (float) (this.mygetMaxHealth() / 4)) {
                this.currentFlightTarget =
                        new BlockPos(
                                this.currentFlightTarget.getX(),
                                this.currentFlightTarget.getY() + 30,
                                this.currentFlightTarget.getZ());
                if (this.hit_by_player
                        && this.call_reinforcements == 0
                        && this.getHealth() < (float) (this.mygetMaxHealth() / 8)
                        && this.getY() > 130.0) {
                    this.call_reinforcements = 1;
                    for (i = 0; i < 10; ++i) {
                        spawnCreature(
                                this.level(),
                                "the_kraken",
                                this.getX()
                                        + (double) this.getRandom().nextInt(10)
                                        - (double) this.getRandom().nextInt(10),
                                170.0,
                                this.getZ()
                                        + (double) this.getRandom().nextInt(10)
                                        - (double) this.getRandom().nextInt(10));
                    }
                }
            }
        } else if (this.caught == null
                && this.getRandom().nextInt(8) == 1
                && ChaosPersists.PlayNicely == 0) {
            Player target = this.level().getNearestPlayer(this, 40.0);
            if (target != null) {
                if (!target.isCreative()) {
                    if (this.getSensing().hasLineOfSight(target)) {
                        this.currentFlightTarget =
                                new BlockPos(
                                        (int) target.getX(),
                                        (int) target.getY() + 15,
                                        (int) target.getZ());
                        this.attackWithSomething(target);
                    }
                } else {
                    target = null;
                }
            }
            if (target == null && this.getRandom().nextInt(2) == 0) {
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
                    this.currentFlightTarget =
                            new BlockPos((int) e.getX(), (int) e.getY() + 15, (int) e.getZ());
                    this.attackWithSomething(e);
                }
            }
        }
        if (this.caught != null) {
            if (!this.caught.isDeadOrDying()) {
                this.currentFlightTarget =
                        new BlockPos((int) this.getX(), 200, (int) this.getZ());
                if (this.getY() > 190.0) {
                    this.release = 1;
                }
                Vec3 krakenMotion = this.getDeltaMovement();
                this.caught.setDeltaMovement(krakenMotion);
                this.caught.setPos(this.getX(), this.caught.getY(), this.getZ());
                if (this.getY() - this.caught.getY() > 16.0) {
                    this.caught.setDeltaMovement(
                            this.caught.getDeltaMovement().add(0.0, 0.25, 0.0));
                }
                this.caught.setPos(this.getX(), this.getY() - 15.0, this.getZ());
                this.caught.setYRot(this.getYRot());
                if (this.getRandom().nextInt(50) == 1) {
                    this.doHurtTarget(this.caught);
                }
                if (this.release != 0 || this.getRandom().nextInt(250) == 1) {
                    this.caught = null;
                    this.newtarget = 1;
                    this.release = 0;
                    this.setAttacking(0);
                }
            } else {
                this.caught = null;
                this.newtarget = 1;
                this.release = 0;
                this.setAttacking(0);
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.3 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.3 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        double mx = motion.x + (Math.signum(var1) * 0.45 - motion.x) * 0.15;
        double my = motion.y + (Math.signum(var3) * 0.70999 - motion.y) * 0.202;
        double mz = motion.z + (Math.signum(var5) * 0.45 - motion.z) * 0.15;
        this.setDeltaMovement(mx, my, mz);
        float var7 =
                (float) (Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / Math.PI)
                        - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.zza = 0.4f;
        if (Math.abs(this.getDeltaMovement().x) + Math.abs(this.getDeltaMovement().z) < 0.15) {
            var8 = 0.0f;
        }
        this.setYRot(this.getYRot() + var8 / 5.0f);
        double obstruction_factor = 0.0;
        double dx = 0.0;
        double dz = 0.0;
        int dist = 10;
        for (int k = -20; k < 18; k += 2) {
            for (i = 1; i < dist; i += 2) {
                dx = (double) i * Math.cos(Math.toRadians(this.getYRot() + 90.0f));
                bid =
                        this.level()
                                .getBlockState(
                                        new BlockPos(
                                                (int) (this.getX() + dx),
                                                (int) this.getY() + k,
                                                (int)
                                                        (this.getZ()
                                                                + (dz =
                                                                        (double) i
                                                                                * Math.sin(
                                                                                        Math.toRadians(
                                                                                                this.getYRot()
                                                                                                        + 90.0f))))))
                                .getBlock();
                if (bid == Blocks.AIR) {
                    continue;
                }
                obstruction_factor += 0.1;
            }
        }
        motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y + obstruction_factor * 0.08, motion.z);
        this.setPos(this.getX(), this.getY() + obstruction_factor * 0.08, this.getZ());
        if (this.getY() > 256.0 && !this.isPersistenceRequired()) {
            this.discard();
        }
        MyUtils.applyChaosFlightMovement(this);
}

    private void attackWithSomething(LivingEntity par1) {
        if (this.caught != null) {
            return;
        }
        double dist = (this.getX() - par1.getX()) * (this.getX() - par1.getX());
        dist += (this.getZ() - par1.getZ()) * (this.getZ() - par1.getZ());
        if ((dist += (this.getY() - par1.getY() - 15.0) * (this.getY() - par1.getY() - 15.0)) < 30.0) {
            this.caught = par1;
            this.release = 0;
            this.setAttacking(1);
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            if (player.getAbilities().flying) {
                return false;
            }
            return true;
        }
        if (!par1EntityLiving.onGround() && !par1EntityLiving.isInWater()) {
            return false;
        }
        if (par1EntityLiving instanceof Squid) {
            return false;
        }
        if (par1EntityLiving instanceof AttackSquid) {
            return false;
        }
        if (par1EntityLiving instanceof Kraken) {
            return false;
        }
        if (isUnportedSimpleName(par1EntityLiving, "Spyro")) {
            return false;
        }
        if (isUnportedRidableTarget(par1EntityLiving, "Dragon")) {
            return true;
        }
        if (isUnportedRidableTarget(par1EntityLiving, "Cephadrome")) {
            return true;
        }
        if (isUnportedRidableTarget(par1EntityLiving, "Leon")) {
            return true;
        }
        if (isUnportedRidableTarget(par1EntityLiving, "ThePrinceTeen")) {
            return true;
        }
        if (isUnportedRidableTarget(par1EntityLiving, "ThePrinceAdult")) {
            return true;
        }
        if (par1EntityLiving instanceof Chicken) {
            return false;
        }
        if (par1EntityLiving instanceof Chipmunk) {
            return false;
        }
        if (isUnportedSimpleName(par1EntityLiving, "StinkBug")) {
            return false;
        }
        if (par1EntityLiving instanceof Mothra) {
            return false;
        }
        return true;
    }

    private static boolean isUnportedSimpleName(LivingEntity entity, String simpleName) {
        return entity.getClass().getSimpleName().equals(simpleName);
    }

    /** Unported rideables: match simple class name, reject when getControllingPassenger() is non-null (reflection). */
    private static boolean isUnportedRidableTarget(LivingEntity entity, String simpleName) {
        if (!entity.getClass().getSimpleName().equals(simpleName)) {
            return false;
        }
        return getControllingPassengerReflect(entity) == null;
    }

    private static Entity getControllingPassengerReflect(LivingEntity entity) {
        try {
            Method m = entity.getClass().getMethod("getControllingPassenger");
            return (Entity) m.invoke(entity);
        } catch (ReflectiveOperationException ex) {
            return null;
        }
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(20.0, 40.0, 20.0));
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

    @Override
    public void thunderHit(ServerLevel level, LightningBolt lightning) {
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        boolean ret = false;
        if (this.currentFlightTarget != null
                && e != null
                && e instanceof Player
                && this.getHealth() > (float) (this.mygetMaxHealth() / 4)) {
            this.hit_by_player = true;
            this.currentFlightTarget =
                    new BlockPos((int) e.getX(), (int) e.getY() + 15, (int) e.getZ());
        }
        if (this.hurt_timer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.hurt_timer = 30;
        }
        if (this.getRandom().nextInt(2) == 1) {
            this.release = 1;
        }
        return ret;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public static boolean checkKrakenSpawnRules(
            EntityType<Kraken> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 6; ++i) {
                    Block bid =
                            MyUtils.getBlockStateForSpawnRules(level, new BlockPos(pos.getX() + j, pos.getY() + i, pos.getZ() + k))
                                    .getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.TALL_GRASS) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        for (int k = -1; k < 2; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 6; ++i) {
                    Block bid =
                            MyUtils.getBlockStateForSpawnRules(level, 
                                            new BlockPos(
                                                    (int) this.getX() + j,
                                                    (int) this.getY() + i,
                                                    (int) this.getZ() + k))
                                    .getBlock();
                    if (bid == Blocks.AIR || bid == Blocks.TALL_GRASS) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
