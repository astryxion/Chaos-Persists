package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;

public class Rat extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Rat.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private float moveSpeed = 0.25f;
    private String myowner = null;

    public Rat(EntityType<? extends Rat> type, World par1World) {
        super(type, par1World);
        this.xpReward = 5;
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.350000023841858));
        this.goalSelector.addGoal(2, new MoveThroughVillageGoal(this, 1.0, false, 512, () -> true));
        this.goalSelector.addGoal(3, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter(this);
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.Rat_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.Rat_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.hasOwner()) {
            return false;
        }
        return true;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Rat_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.Rat_stats.defense;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
    }

    @Override
    protected void jumpFromGround() {
        super.jumpFromGround();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y + 0.25, this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 0.25, this.getZ());
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.RATLIVE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.RATHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.RATDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.ROTTEN_FLESH;
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    @Override
    protected void customServerAiStep() {
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.level.random.nextInt(5) == 1) {
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
                this.setAttacking(1);
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr(e) < 4.0 && (this.random.nextInt(8) == 0 || this.random.nextInt(7) == 1)) {
                    this.doHurtTarget(e);
                }
            } else {
                this.setAttacking(0);
                PlayerEntity p = this.getOwnerPlayer();
                if (p != null) {
                    if (this.distanceToSqr(p) > 64.0) {
                        this.getNavigation().moveTo(p, 1.75);
                    }
                    if (this.distanceToSqr(p) > 256.0) {
                        this.setPos(p.getX() + (double) this.level.random.nextFloat() - (double) this.level.random.nextFloat(), p.getY(), p.getZ() + (double) this.level.random.nextFloat() - (double) this.level.random.nextFloat());
                    }
                }
            }
        }
        if (this.level.random.nextInt(250) == 1) {
            this.heal(1.0f);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1LivingEntity, boolean par2) {
        if (par1LivingEntity == null) {
            return false;
        }
        if (par1LivingEntity == this) {
            return false;
        }
        if (!par1LivingEntity.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1LivingEntity)) {
            return false;
        }
        if (!this.getSensing().canSee(par1LivingEntity)) {
            return false;
        }
        if (par1LivingEntity instanceof Irukandji) {
            return false;
        }
        if (par1LivingEntity instanceof Skate) {
            return false;
        }
        if (par1LivingEntity instanceof Whale) {
            return false;
        }
        if (par1LivingEntity instanceof Flounder) {
            return false;
        }
        if (par1LivingEntity instanceof Rat) {
            return false;
        }
        if (par1LivingEntity instanceof Ghost) {
            return false;
        }
        if (par1LivingEntity instanceof GhostSkelly) {
            return false;
        }
        if (par1LivingEntity instanceof DungeonBeast) {
            return false;
        }
        if (par1LivingEntity instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) par1LivingEntity;
            if (p.isCreative()) {
                return false;
            }
            if (this.hasOwner()) {
                if (this.myowner.equals(p.getUUID().toString())) {
                    return false;
                }
                if (ChaosPersists.RatPlayerFriendly != 0) {
                    return false;
                }
            }
        }
        if (this.hasOwner() && par1LivingEntity instanceof TameableEntity) {
            TameableEntity e = (TameableEntity) par1LivingEntity;
            if (ChaosPersists.RatPetFriendly != 0 && e.isTame()) {
                return false;
            }
            if (e.getOwnerUUID() != null && this.myowner.equals(e.getOwnerUUID().toString())) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(9.0, 2.0, 9.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (this.isSuitableTarget(var4, false)) {
                return var4;
            }
        }
        return null;
    }

    public void setOwner(LivingEntity e) {
        if (e instanceof PlayerEntity) {
            this.myowner = ((PlayerEntity) e).getUUID().toString();
        }
    }

    private PlayerEntity getOwnerPlayer() {
        UUID ownerId = this.getOwnerUuid();
        if (ownerId == null || !(this.level instanceof net.minecraft.world.server.ServerWorld)) {
            return null;
        }
        return ((net.minecraft.world.server.ServerWorld) this.level).getServer().getPlayerList().getPlayer(ownerId);
    }

    private UUID getOwnerUuid() {
        if (this.myowner == null || this.myowner.isEmpty() || "null".equals(this.myowner)) {
            return null;
        }
        try {
            return UUID.fromString(this.myowner);
        } catch (IllegalArgumentException ignored) {
            this.myowner = null;
            return null;
        }
    }

    private boolean hasOwner() {
        return this.getOwnerUuid() != null;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        if (this.myowner != null && !this.myowner.isEmpty() && !"null".equals(this.myowner)) {
            par1CompoundNBT.putString("MyOwner", this.myowner);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        if (par1CompoundNBT.contains("MyOwner", 8)) {
            this.myowner = par1CompoundNBT.getString("MyOwner");
            if (this.myowner == null || this.myowner.isEmpty() || "null".equals(this.myowner)) {
                this.myowner = null;
            }
        } else {
            this.myowner = null;
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    protected boolean isValidLightLevel() {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            return true;
        }
        return MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random);
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    BlockPos pos = new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (level.getBlockState(pos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(level.getBlockEntity(pos) instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity) level.getBlockEntity(pos);
                    String s = null;
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) {
                        s = id.getPath();
                    }
                    if (s != null && s.equals("Rat")) {
                        return true;
                    }
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        return this.findBuddies() <= 8;
    }

    private int findBuddies() {
        List<Rat> var5 = this.level.getEntitiesOfClass(Rat.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }
}
