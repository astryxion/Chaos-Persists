package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
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
import net.minecraft.block.BlockState;

public class WormMedium extends MonsterEntity {
    public int upcount = 0;
    public int downcount = 0;

    public WormMedium(EntityType<? extends WormMedium> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.5f, height=2.0f
        this.xpReward = 0;
        this.noPhysics = true;
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.WormMedium_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.WormMedium_stats.attack)
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
        return 1.5f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LITTLE_SPLAT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.BIG_SPLAT;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.WormMedium_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.WormMedium_stats.defense;
    }

    @Override
    public void aiStep() {
        Block bid;
        PlayerEntity target = null;
        WormSmall worms = null;
        super.aiStep();
        if (this.level.isClientSide) {
            return;
        }
        List<WormSmall> wormList = this.level.getEntitiesOfClass(WormSmall.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0), (e) -> e.isAlive());
        if (!wormList.isEmpty()) {
            worms = wormList.get(0);
        }
        if (worms == null) {
            target = this.level.getNearestPlayer(this, 8.0);
        }
        if (worms == null && target != null || ChaosPersists.PlayNicely != 0) {
            if (this.upcount > 0) {
                --this.upcount;
                if (this.upcount == 0) {
                    this.downcount = 100 + this.level.random.nextInt(150);
                }
                if (target != null) {
                    this.pointAtEntity(target);
                }
                bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) (this.getY() + 0.25), (int) this.getZ())).getBlock();
                if (bid == Blocks.TALL_GRASS) {
                    bid = Blocks.AIR;
                }
                if (bid != Blocks.AIR) {
                    if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                        this.remove();
                    }
                    this.push(0.0, 0.20000000298023224, 0.0);
                    this.setPos(this.getX(), this.getY() + 0.10000000149011612, this.getZ());
                }
            } else {
                if (this.downcount > 0) {
                    --this.downcount;
                } else {
                    this.upcount = 25 + this.level.random.nextInt(75);
                }
                bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + 3, (int) this.getZ())).getBlock();
                if (bid == Blocks.TALL_GRASS) {
                    bid = Blocks.AIR;
                }
                if (bid != Blocks.AIR) {
                    if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                        this.remove();
                    }
                    this.push(0.0, 0.10000000149011612, 0.0);
                    this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
                }
            }
        } else {
            this.upcount = this.level.random.nextInt(50);
            this.downcount = 0;
            bid = this.level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + 3, (int) this.getZ())).getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                    this.remove();
                }
                this.push(0.0, 0.10000000149011612, 0.0);
                this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
            }
        }
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y - 0.01, this.getDeltaMovement().z);
        this.setDeltaMovement(0.0, this.getDeltaMovement().y, 0.0);
        this.xxa = 0.0f;
    }

    @Override
    public void tick() {
        if (this.isPersistenceRequired()) {
            this.noPhysics = false;
        }
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.65, this.getDeltaMovement().z);
    }

    public void pointAtEntity(LivingEntity e) {
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float) Math.atan2(d2, d1);
        float f2 = (float) ((double) d * 180.0 / 3.141592653589793) - 90.0f;
        this.yRot = this.yHeadRot = f2;
    }

    @Override
    protected void customServerAiStep() {
        int bid = 0;
        PlayerEntity target = null;
        WormSmall worms = null;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        List<WormSmall> wormList = this.level.getEntitiesOfClass(WormSmall.class, this.getBoundingBox().inflate(8.0, 8.0, 8.0), (e) -> e.isAlive());
        if (!wormList.isEmpty()) {
            worms = wormList.get(0);
        }
        if (worms != null) {
            return;
        }
        List<PlayerEntity> players = this.level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(2.25, 8.0, 2.25), (e) -> e.isAlive());
        if (!players.isEmpty()) {
            target = players.get(0);
        }
        if (target != null && target.isCreative()) {
            target = null;
        }
        if (target != null) {
            this.pointAtEntity(target);
            if (this.upcount > 0 && this.level.random.nextInt(15) == 1 && !target.isCreative()) {
                ItemStack boots;
                this.doHurtTarget(target);
                if (this.level.random.nextInt(6) == 1 && !(boots = target.getItemBySlot(EquipmentSlotType.FEET)).isEmpty()) {
                    target.setItemSlot(EquipmentSlotType.FEET, ItemStack.EMPTY);
                    bid = boots.getMaxDamage() - boots.getDamageValue();
                    bid = bid > 15 ? (bid /= 15) : 1;
                    boots.hurtAndBreak(bid, this, (e) -> e.broadcastBreakEvent(EquipmentSlotType.FEET));
                    ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), this.getY() + 3.0, this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(5) - (double) ChaosPersists.ChaosRand.nextInt(5), boots);
                    this.level.addFreshEntity(var3);
                } else if (this.level.random.nextInt(6) == 1 && !(boots = target.getItemBySlot(EquipmentSlotType.LEGS)).isEmpty()) {
                    target.setItemSlot(EquipmentSlotType.LEGS, ItemStack.EMPTY);
                    bid = boots.getMaxDamage() - boots.getDamageValue();
                    bid = bid > 15 ? (bid /= 15) : 1;
                    boots.hurtAndBreak(bid, this, (e) -> e.broadcastBreakEvent(EquipmentSlotType.LEGS));
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
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        if (level instanceof net.minecraft.world.World && ((net.minecraft.world.World)level).isDay()) {
            return false;
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

    protected Item getDropItem() {
        return Items.ROTTEN_FLESH;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double) ChaosPersists.ChaosRand.nextInt(3) - (double) ChaosPersists.ChaosRand.nextInt(3), this.getY() + 2.5 + (double) this.level.random.nextInt(3), this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(3) - (double) ChaosPersists.ChaosRand.nextInt(3), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        for (var4 = 0; var4 < 2; ++var4) {
            this.dropItemRand(Items.ROTTEN_FLESH, 1);
        }
        for (var4 = 0; var4 < 2; ++var4) {
            this.dropItemRand(Items.LEATHER, 1);
        }
    }
}
