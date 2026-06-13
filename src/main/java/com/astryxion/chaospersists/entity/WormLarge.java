package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class WormLarge extends MonsterEntity {
    private int wormsSpawned = 0;

    public WormLarge(EntityType<? extends WormLarge> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=1.55f, height=2.5f
        this.xpReward = 2050;
        this.noPhysics = true;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 512, () -> true));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.WormLarge_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.WormLarge_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.BIG_SPLAT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.WormLarge_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.WormLarge_stats.defense;
    }

    public void pointAtEntity(LivingEntity e) {
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float) Math.atan2(d2, d1);
        float f2 = (float) ((double) d * 180.0 / 3.141592653589793) - 90.0f;
        this.yRot = this.yHeadRot = f2;
    }

    @Override
    public void aiStep() {
        Block bid;
        PlayerEntity target = null;
        WormMedium worms = null;
        super.aiStep();
        List<WormMedium> wormList = this.level.getEntitiesOfClass(WormMedium.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0), (e) -> e.isAlive());
        if (!wormList.isEmpty()) {
            worms = wormList.get(0);
        }
        if (worms == null) {
            target = this.level.getNearestPlayer(this, 8.0);
        }
        if (worms == null && target != null || ChaosPersists.PlayNicely != 0) {
            if (target != null) {
                this.pointAtEntity(target);
            }
            bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())).getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                this.push(0.0, 0.25, 0.0);
                this.setPos(this.getX(), this.getY() + 0.10000000149011612, this.getZ());
            } else {
                this.noPhysics = false;
            }
        } else {
            this.noPhysics = true;
            bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) (this.getY() + 3.5), (int) this.getZ())).getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                this.push(0.0, 0.10000000149011612, 0.0);
                this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                    this.remove();
                }
            }
        }
        if (this.noPhysics) {
            this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y - 0.01, this.getDeltaMovement().z);
            this.setDeltaMovement(0.0, this.getDeltaMovement().y, 0.0);
            this.xxa = 0.0f;
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.wormsSpawned != 0) {
            return;
        }
        this.wormsSpawned = 1;
        for (int i = 0; i < 20; ++i) {
            WormLarge.spawnCreature(this.level, "Small Worm", this.getX() + (double) this.level.random.nextInt(6) - (double) this.level.random.nextInt(6), this.getY(), this.getZ() + (double) this.level.random.nextInt(6) - (double) this.level.random.nextInt(6));
            WormLarge.spawnCreature(this.level, "Medium Worm", this.getX() + (double) this.level.random.nextInt(5) - (double) this.level.random.nextInt(5), this.getY(), this.getZ() + (double) this.level.random.nextInt(5) - (double) this.level.random.nextInt(5));
        }
    }

    @Override
    public void tick() {
        if (this.isPersistenceRequired()) {
            this.noPhysics = false;
        }
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.85, this.getDeltaMovement().z);
    }

    @Override
    protected void customServerAiStep() {
        int bid = 0;
        PlayerEntity target = null;
        WormMedium worms = null;
        if (this.removed) {
            return;
        }
        if (!this.noPhysics) {
            super.customServerAiStep();
        }
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        List<WormMedium> wormList = this.level.getEntitiesOfClass(WormMedium.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0), (e) -> e.isAlive());
        if (!wormList.isEmpty()) {
            worms = wormList.get(0);
        }
        if (worms != null) {
            return;
        }
        target = this.level.getNearestPlayer(this, 8.0);
        if (target != null && target.isCreative()) {
            target = null;
        }
        if (target != null) {
            this.pointAtEntity(target);
            this.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
            if (this.level.random.nextInt(10) == 1 && this.distanceTo(target) < 3.0) {
                ItemStack boots;
                this.doHurtTarget(target);
                if (this.level.random.nextInt(4) == 1) {
                    if (!(boots = target.getItemBySlot(EquipmentSlotType.FEET)).isEmpty()) {
                        target.setItemSlot(EquipmentSlotType.FEET, ItemStack.EMPTY);
                        bid = boots.getMaxDamage() - boots.getDamageValue();
                        bid = bid > 10 ? (bid /= 10) : 1;
                        boots.hurtAndBreak(bid, this, (e) -> e.broadcastBreakEvent(EquipmentSlotType.FEET));
                        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), this.getY() + 3.0, this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), boots);
                        this.level.addFreshEntity(var3);
                    } else if (!(boots = target.getItemBySlot(EquipmentSlotType.LEGS)).isEmpty()) {
                        target.setItemSlot(EquipmentSlotType.LEGS, ItemStack.EMPTY);
                        bid = boots.getMaxDamage() - boots.getDamageValue();
                        bid = bid > 10 ? (bid /= 10) : 1;
                        boots.hurtAndBreak(bid, this, (e) -> e.broadcastBreakEvent(EquipmentSlotType.LEGS));
                        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), this.getY() + 3.0, this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), boots);
                        this.level.addFreshEntity(var3);
                    }
                }
                if (this.level.random.nextInt(4) == 1 && !(boots = target.getItemBySlot(EquipmentSlotType.MAINHAND)).isEmpty()) {
                    target.setItemSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                    bid = boots.getMaxDamage() - boots.getDamageValue();
                    bid = bid > 10 ? (bid /= 10) : 1;
                    boots.hurtAndBreak(bid, this, (e) -> e.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                    ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), this.getY() + 3.0, this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), boots);
                    this.level.addFreshEntity(var3);
                }
            }
        }
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float par1, float par2) {
        if (!this.noPhysics) {
            return super.causeFallDamage(par1, par2);
        }
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        Block bid;
        int j;
        int i;
        int k;
        for (k = -3; k < 3; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 0; i < 5; ++i) {
                    bid = level.getBlockState(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)) instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity) level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k));
                    String s = null;
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) {
                        s = id.getPath();
                    }
                    if (s == null || !s.equals("Large Worm")) {
                        continue;
                    }
                    this.wormsSpawned = 1;
                    return true;
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        List<WormLarge> targets = this.level.getEntitiesOfClass(WormLarge.class, this.getBoundingBox().inflate(32.0, 8.0, 32.0), (e) -> e.isAlive());
        if (!targets.isEmpty()) {
            return false;
        }
        for (i = -6; i <= 6; ++i) {
            for (j = -6; j <= 6; ++j) {
                for (k = -2; k >= -8; --k) {
                    bid = level.getBlockState(new BlockPos((int) this.getX() + i, (int) this.getY() + k, (int) this.getZ() + j)).getBlock();
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
                    bid = level.getBlockState(new BlockPos((int) this.getX() + i, (int) this.getY() + k, (int) this.getZ() + j)).getBlock();
                    if (bid == Blocks.AIR) {
                        continue;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void initCreature() {
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("wormsSpawned", this.wormsSpawned);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.wormsSpawned = par1CompoundNBT.getInt("wormsSpawned");
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_")));
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
        }
        return var8;
    }

    protected Item getDropItem() {
        return Items.ROTTEN_FLESH;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(4) - (double) ChaosPersists.ChaosRand.nextInt(4), this.getY() + 2.5 + (double) this.level.random.nextInt(4), this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(4) - (double) ChaosPersists.ChaosRand.nextInt(4), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        this.dropItemRand(ChaosPersists.WormTooth, 1);
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
            this.dropItemRand(ChaosPersists.UraniumNugget, 1);
        }
        for (var4 = 0; var4 < 4; ++var4) {
            this.dropItemRand(ChaosPersists.TitaniumNugget, 1);
        }
    }
}
