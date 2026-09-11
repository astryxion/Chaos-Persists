package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.AABB;

public class BandP extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(BandP.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.32f;
    private int whatset = 0;
    private int whatami = 0;
    public ItemStack[] MymainInventory = new ItemStack[100];
    int got_stuff = 0;

    public BandP(EntityType<? extends BandP> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 1000;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new MoveThroughVillageGoal(this, 0.5, false, 4, () -> false));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 16, 0.5));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(4, new OpenDoorGoal(this, true));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        int health = ChaosPersists.BandP_stats != null ? ChaosPersists.BandP_stats.health : 100;
        double attack = ChaosPersists.BandP_stats != null ? ChaosPersists.BandP_stats.attack : 1.0;
        double defense = ChaosPersists.BandP_stats != null ? ChaosPersists.BandP_stats.defense : 18.0;
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) health)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, attack)
                .add(Attributes.ARMOR, defense);
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
        if (this.got_stuff != 0) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (!this.level().isClientSide && this.whatset == 0) {
            this.whatset = 1;
            this.whatami = this.getRandom().nextInt(2);
            this.setWhat(this.whatami);
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.BandP_stats != null ? ChaosPersists.BandP_stats.health : 100;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.BandP_stats != null ? ChaosPersists.BandP_stats.defense : 18;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    private ItemStack dropItemRand(Item index, int count) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, count);
        ItemEntity drop =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        this.getY() + 1.0,
                        this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        is);
        this.level().addFreshEntity(drop);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4 = 10 + this.random.nextInt(5);
        int i;
        for (i = 0; i < var4; ++i) {
            this.dropItemRand(Items.EMERALD, 1);
        }
        if (this.getWhat() == 0) {
            var4 = 2 + this.random.nextInt(3);
            for (i = 0; i < var4; ++i) {
                this.dropItemRand(ChaosPersists.UraniumNugget, 1);
                this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
            }
        }
        for (i = 0; i < this.MymainInventory.length; ++i) {
            if (this.MymainInventory[i] == null || this.MymainInventory[i].isEmpty()) {
                continue;
            }
            ItemStack dropped =
                    this.dropItemRand(this.MymainInventory[i].getItem(), this.MymainInventory[i].getCount());
            if (this.MymainInventory[i].getCount() == 1 && dropped != null) {
                dropped.setDamageValue(this.MymainInventory[i].getDamageValue());
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.getRandom().nextInt(12) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            LivingEntity revenge = this.getLastHurtByMob();
            if (revenge != null && this.isSuitableTarget(revenge)) {
                e = revenge;
            }
            if (e != null) {
                this.setTarget(e);
                double reach = 3.0 + (double) (this.getBbWidth() + e.getBbWidth()) * 0.5;
                if (this.distanceToSqr(e) < reach * reach) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.getNavigation().stop();
                    this.doHurtTarget(e);
                    if (e instanceof Player p) {
                        int k = -1;
                        int kp = -1;
                        for (int i = 0; i < this.MymainInventory.length; ++i) {
                            if (this.MymainInventory[i] == null || this.MymainInventory[i].isEmpty()) {
                                k = i;
                                break;
                            }
                        }
                        if (k >= 0) {
                            for (int i = p.getInventory().armor.size() - 1; i >= 0; --i) {
                                if (MyUtils.canMobStripItem(p.getInventory().armor.get(i))) {
                                    kp = i;
                                    break;
                                }
                            }
                            if (kp >= 0) {
                                this.MymainInventory[k] = p.getInventory().armor.get(kp).copy();
                                p.getInventory().armor.set(kp, ItemStack.EMPTY);
                                ++this.got_stuff;
                            }
                            if (kp < 0) {
                                for (int i = p.getInventory().items.size() - 1; i >= 0; --i) {
                                    if (!p.getInventory().items.get(i).isEmpty()) {
                                        kp = i;
                                        break;
                                    }
                                }
                                if (kp >= 0) {
                                    this.MymainInventory[k] = p.getInventory().items.get(kp).copy();
                                    p.getInventory().items.set(kp, ItemStack.EMPTY);
                                    ++this.got_stuff;
                                }
                            }
                        }
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.25);
                }
            }
        }
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving) {
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
        // Point-blank: skip LOS so criminals can still hit when pressed against the player
        if (this.distanceToSqr(par1EntityLiving) > 4.0
                && !this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        if (MyUtils.isVillageCombatTarget(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof Girlfriend) {
            return true;
        }
        if (par1EntityLiving instanceof Boyfriend) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                this.getBoundingBox().inflate(20.0, 6.0, 20.0),
                                e -> e != this && this.isSuitableTarget(e));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (this.isSuitableTarget(var4)) {
                return var4;
            }
        }
        return null;
    }

    public int getWhat() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setWhat(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    private static boolean isCriminalSpawnerNear(LevelAccessor level, BlockPos origin) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                    if (level.getBlockState(checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(level.getBlockEntity(checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "criminal".equals(id.getPath().toLowerCase(Locale.ROOT))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkBandPSpawnRules(
            EntityType<BandP> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (isCriminalSpawnerNear(level, pos)) {
            return true;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (pos.getY() < 100) {
            return false;
        }
        AABB bandpBox = new AABB(pos).inflate(32.0, 12.0, 32.0);
        if (!level.getEntitiesOfClass(BandP.class, bandpBox, Entity::isAlive).isEmpty()) {
            return false;
        }
        AABB villagerBox = new AABB(pos).inflate(36.0, 12.0, 36.0);
        return !level.getEntitiesOfClass(Villager.class, villagerBox, Entity::isAlive).isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (!(level instanceof ServerLevelAccessor serverLevel)) {
            return false;
        }
        return checkBandPSpawnRules(
                (EntityType<BandP>) this.getType(),
                serverLevel,
                spawnReason,
                this.blockPosition(),
                this.getRandom());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.got_stuff != 0) {
            tag.put("Inventory", this.writeInventoryToNbt());
        }
        tag.putInt("GotStuff", this.got_stuff);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.got_stuff = tag.getInt("GotStuff");
        if (this.got_stuff != 0 && tag.contains("Inventory", Tag.TAG_LIST)) {
            this.readInventoryFromNbt(tag.getList("Inventory", Tag.TAG_COMPOUND));
        }
    }

    private ListTag writeInventoryToNbt() {
        ListTag list = new ListTag();
        for (int i = 0; i < this.MymainInventory.length; ++i) {
            if (this.MymainInventory[i] == null || this.MymainInventory[i].isEmpty()) {
                continue;
            }
            CompoundTag slotTag = new CompoundTag();
            slotTag.putByte("Slot", (byte) i);
            this.MymainInventory[i].save(slotTag);
            list.add(slotTag);
        }
        return list;
    }

    private void readInventoryFromNbt(ListTag list) {
        this.MymainInventory = new ItemStack[100];
        for (int i = 0; i < list.size(); ++i) {
            CompoundTag slotTag = list.getCompound(i);
            int j = slotTag.getByte("Slot") & 255;
            ItemStack stack = ItemStack.of(slotTag);
            if (!stack.isEmpty() && j >= 0 && j < this.MymainInventory.length) {
                this.MymainInventory[j] = stack;
            }
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.75f, 1.75f);
    }
}
