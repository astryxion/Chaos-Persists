package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class WormLarge extends Monster {
    private int wormsSpawned = 0;

    public WormLarge(EntityType<? extends WormLarge> type, Level level) {
        super(type, level);
        this.xpReward = 2050;
        this.noPhysics = true;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(
                1, new MoveThroughVillageGoal((net.minecraft.world.entity.PathfinderMob) this, 1.0, false, 4, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.WormLarge_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.WormLarge_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.WormLarge_stats.defense);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.BIG_SPLAT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public void push(Entity entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.WormLarge_stats.health;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.WormLarge_stats.defense;
    }

    public void pointAtEntity(LivingEntity e) {
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float) Math.atan2(d2, d1);
        float f2 = (float) ((double) d * 180.0 / Math.PI) - 90.0f;
        this.setYRot(f2);
        this.setYHeadRot(f2);
    }

    @Override
    public void aiStep() {
        Block bid;
        Player target = null;
        WormMedium worms = null;
        super.aiStep();
        worms = this.findNearestWormMedium();
        if (worms == null) {
            target = this.findNearestPlayerInBox(8.0, 8.0, 8.0);
        }
        if (worms == null && target != null || ChaosPersists.PlayNicely != 0) {
            if (target != null) {
                this.pointAtEntity(target);
            }
            bid = this.level()
                    .getBlockState(BlockPos.containing(this.getX(), this.getY(), this.getZ()))
                    .getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                this.setDeltaMovement(
                        this.getDeltaMovement().x,
                        this.getDeltaMovement().y + 0.25,
                        this.getDeltaMovement().z);
                this.setPos(this.getX(), this.getY() + 0.10000000149011612, this.getZ());
            } else {
                this.noPhysics = false;
            }
        } else {
            this.noPhysics = true;
            bid = this.level()
                    .getBlockState(
                            BlockPos.containing(this.getX(), this.getY() + 3.5, this.getZ()))
                    .getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                this.setDeltaMovement(
                        this.getDeltaMovement().x,
                        this.getDeltaMovement().y + 0.10000000149011612,
                        this.getDeltaMovement().z);
                this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                    this.discard();
                }
            }
        }
        if (this.noPhysics) {
            this.setDeltaMovement(
                    0.0,
                    this.getDeltaMovement().y - 0.01,
                    0.0);
            this.setSpeed(0.0f);
        }
        if (this.level().isClientSide) {
            return;
        }
        if (this.wormsSpawned != 0) {
            return;
        }
        this.wormsSpawned = 1;
        for (int i = 0; i < 20; ++i) {
            WormLarge.spawnCreature(
                    this.level(),
                    "small_worm",
                    this.getX() + (double) this.getRandom().nextInt(6) - (double) this.getRandom().nextInt(6),
                    this.getY(),
                    this.getZ() + (double) this.getRandom().nextInt(6) - (double) this.getRandom().nextInt(6));
            WormLarge.spawnCreature(
                    this.level(),
                    "medium_worm",
                    this.getX() + (double) this.getRandom().nextInt(5) - (double) this.getRandom().nextInt(5),
                    this.getY(),
                    this.getZ() + (double) this.getRandom().nextInt(5) - (double) this.getRandom().nextInt(5));
        }
    }

    @Override
    public void tick() {
        if (this.isPersistenceRequired()) {
            this.noPhysics = false;
        }
        super.tick();
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                this.getDeltaMovement().y * 0.85,
                this.getDeltaMovement().z);
    }

    @Override
    protected void customServerAiStep() {
        int bid;
        Player target;
        WormMedium worms;
        if (this.isDeadOrDying()) {
            return;
        }
        if (!this.noPhysics) {
            super.customServerAiStep();
        }
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        worms = this.findNearestWormMedium();
        if (worms != null) {
            return;
        }
        target = this.findNearestPlayerInBox(8.0, 6.0, 8.0);
        if (target != null && target.isCreative()) {
            target = null;
        }
        if (target != null) {
            this.pointAtEntity(target);
            this.getNavigation().moveTo(target, 1.0);
            if (this.getRandom().nextInt(10) == 1 && this.distanceTo(target) < 3.0f) {
                ItemStack boots;
                ItemEntity dropped;
                this.doHurtTarget(target);
                if (this.getRandom().nextInt(4) == 1) {
                    boots = target.getItemBySlot(EquipmentSlot.FEET);
                    if (MyUtils.canMobStripItem(boots)) {
                        target.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                        bid = boots.getMaxDamage() - boots.getDamageValue();
                        bid = bid > 10 ? bid / 10 : 1;
                        boots.hurtAndBreak(bid, this, e -> e.broadcastBreakEvent(EquipmentSlot.FEET));
                        dropped =
                                new ItemEntity(
                                        this.level(),
                                        this.getX()
                                                + (double) ChaosPersists.ChaosRand.nextInt(5)
                                                - (double) ChaosPersists.ChaosRand.nextInt(5),
                                        this.getY() + 3.0,
                                        this.getZ()
                                                + (double) ChaosPersists.ChaosRand.nextInt(5)
                                                - (double) ChaosPersists.ChaosRand.nextInt(5),
                                        boots);
                        this.level().addFreshEntity(dropped);
                    } else {
                        boots = target.getItemBySlot(EquipmentSlot.LEGS);
                        if (MyUtils.canMobStripItem(boots)) {
                            target.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
                            bid = boots.getMaxDamage() - boots.getDamageValue();
                            bid = bid > 10 ? bid / 10 : 1;
                            boots.hurtAndBreak(bid, this, e -> e.broadcastBreakEvent(EquipmentSlot.LEGS));
                            dropped =
                                    new ItemEntity(
                                            this.level(),
                                            this.getX()
                                                    + (double) ChaosPersists.ChaosRand.nextInt(5)
                                                    - (double) ChaosPersists.ChaosRand.nextInt(5),
                                            this.getY() + 3.0,
                                            this.getZ()
                                                    + (double) ChaosPersists.ChaosRand.nextInt(5)
                                                    - (double) ChaosPersists.ChaosRand.nextInt(5),
                                            boots);
                            this.level().addFreshEntity(dropped);
                        }
                    }
                }
                if (this.getRandom().nextInt(4) == 1
                        && MyUtils.canMobStripItem(boots = target.getItemBySlot(EquipmentSlot.MAINHAND))) {
                    target.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                    bid = boots.getMaxDamage() - boots.getDamageValue();
                    bid = bid > 10 ? bid / 10 : 1;
                    boots.hurtAndBreak(bid, this, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                    dropped =
                            new ItemEntity(
                                    this.level(),
                                    this.getX()
                                            + (double) ChaosPersists.ChaosRand.nextInt(5)
                                            - (double) ChaosPersists.ChaosRand.nextInt(5),
                                    this.getY() + 3.0,
                                    this.getZ()
                                            + (double) ChaosPersists.ChaosRand.nextInt(5)
                                            - (double) ChaosPersists.ChaosRand.nextInt(5),
                                    boots);
                    this.level().addFreshEntity(dropped);
                }
            }
        }
    }

    @Nullable
    private WormMedium findNearestWormMedium() {
        List<WormMedium> list =
                this.level()
                        .getEntitiesOfClass(
                                WormMedium.class,
                                this.getBoundingBox().inflate(8.0, 8.0, 8.0),
                                e -> e.isAlive());
        WormMedium nearest = null;
        double best = Double.MAX_VALUE;
        for (WormMedium worm : list) {
            double d = this.distanceToSqr(worm);
            if (d < best) {
                best = d;
                nearest = worm;
            }
        }
        return nearest;
    }

    @Nullable
    private Player findNearestPlayerInBox(double expandX, double expandY, double expandZ) {
        List<Player> list =
                this.level()
                        .getEntitiesOfClass(
                                Player.class,
                                this.getBoundingBox().inflate(expandX, expandY, expandZ),
                                e -> e.isAlive());
        Player nearest = null;
        double best = Double.MAX_VALUE;
        for (Player player : list) {
            double d = this.distanceToSqr(player);
            if (d < best) {
                best = d;
                nearest = player;
            }
        }
        return nearest;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        if (!this.noPhysics) {
            return super.causeFallDamage(distance, damageMultiplier, source);
        }
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    private static boolean isLargeWormSpawnerId(ResourceLocation id) {
        if (id == null) {
            return false;
        }
        String path = id.getPath();
        if ("large_worm".equals(path) || "Large Worm".equals(path)) {
            return true;
        }
        ResourceLocation normalized = SpawnerFixHelper.normalizeSpawnerEntityId(id);
        if (normalized == null) {
            return false;
        }
        path = normalized.getPath();
        return "large_worm".equals(path) || "Large Worm".equals(path);
    }

    private static boolean checkWormLargeSpawnRulesInternal(
            LevelAccessor level, BlockPos origin, @Nullable WormLarge self) {
        Block bid;
        int j;
        int i;
        int k;
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (k = -3; k < 3; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 0; i < 5; ++i) {
                    checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                    bid = MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock();
                    if (bid != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (!isLargeWormSpawnerId(id)) {
                        continue;
                    }
                    if (self != null) {
                        self.wormsSpawned = 1;
                    }
                    return true;
                }
            }
        }
        if (level.getFluidState(origin).is(net.minecraft.tags.FluidTags.WATER)) {
            return false;
        }
        if (origin.getY() < 50) {
            return false;
        }
        AABB buddyBox = new AABB(origin).inflate(32.0, 8.0, 32.0);
        List<WormLarge> buddies =
                level.getEntitiesOfClass(
                        WormLarge.class,
                        buddyBox,
                        e -> self == null || e != self);
        if (!buddies.isEmpty()) {
            return false;
        }
        for (i = -6; i <= 6; ++i) {
            for (j = -6; j <= 6; ++j) {
                for (k = -2; k >= -8; --k) {
                    checkPos.set(origin.getX() + i, origin.getY() + k, origin.getZ() + j);
                    bid = MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock();
                    if (bid != Blocks.AIR) {
                        continue;
                    }
                    return false;
                }
            }
        }
        for (i = -6; i <= 6; ++i) {
            for (j = -6; j <= 6; ++j) {
                for (k = 2; k <= 8; ++k) {
                    checkPos.set(origin.getX() + i, origin.getY() + k, origin.getZ() + j);
                    bid = MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock();
                    if (bid == Blocks.AIR) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWormLargeSpawnRules(
            EntityType<WormLarge> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        return checkWormLargeSpawnRulesInternal(level, pos, null);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return checkWormLargeSpawnRulesInternal(level, this.blockPosition(), this);
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        if (damageSource.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurt(damageSource, amount);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("wormsSpawned", this.wormsSpawned);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.wormsSpawned = tag.getInt("wormsSpawned");
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

    private Item resolveModItem(String registryPath, Item fallback) {
        Item item =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", registryPath));
        return item != null ? item : fallback;
    }

    private void dropItemRand(Item index, int count) {
        if (index == null) {
            return;
        }
        ItemEntity itemEntity =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(4)
                                - (double) ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 2.5 + (double) this.level().getRandom().nextInt(4),
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(4)
                                - (double) ChaosPersists.ChaosRand.nextInt(4),
                        new ItemStack(index, count));
        this.level().addFreshEntity(itemEntity);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        int var4;
        this.dropItemRand(this.resolveModItem("wormtooth", ChaosPersists.WormTooth), 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.ROTTEN_FLESH, 1);
        }
        for (var4 = 0; var4 < 6; ++var4) {
            this.dropItemRand(Items.LEATHER, 1);
        }
        for (var4 = 0; var4 < 8; ++var4) {
            this.dropItemRand(Items.DIRT, 1);
        }
        for (var4 = 0; var4 < 16; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        for (var4 = 0; var4 < 5; ++var4) {
            this.dropItemRand(Items.DIAMOND, 1);
        }
        for (var4 = 0; var4 < 4; ++var4) {
            this.dropItemRand(
                    this.resolveModItem("uranium_nugget", ChaosPersists.UraniumNugget), 1);
        }
        for (var4 = 0; var4 < 4; ++var4) {
            this.dropItemRand(
                    this.resolveModItem("titanium_nugget", ChaosPersists.TitaniumNugget), 1);
        }
    }
}
