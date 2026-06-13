package com.astryxion.chaospersists.entity;

/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.DungeonBeast
 *  com.astryxion.chaospersists.Flounder
 *  com.astryxion.chaospersists.GenericTargetSorter
 *  com.astryxion.chaospersists.Irukandji
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.MyEntityAIWanderALot
 *  com.astryxion.chaospersists.MyUtils
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Peacock
 *  com.astryxion.chaospersists.Rat
 *  com.astryxion.chaospersists.RenderInfo
 *  com.astryxion.chaospersists.Rotator
 *  com.astryxion.chaospersists.Skate
 *  com.astryxion.chaospersists.Whale
 *  net.minecraft.block.Block
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.HurtByTargetGoal
 *  net.minecraft.entity.ai.LookRandomlyGoal
 *  net.minecraft.entity.ai.SwimGoal
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.LookAtGoal
 *  net.minecraft.entity.ai.EntitySenses
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraft.world.Dimension
 */
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.entity.Flounder;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.CrystalDimensionSpawnHelper;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Peacock;
import com.astryxion.chaospersists.entity.Rat;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Rotator;
import com.astryxion.chaospersists.entity.Skate;
import com.astryxion.chaospersists.entity.Whale;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;

public class DungeonBeast
extends MonsterEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(DungeonBeast.class, DataSerializers.BYTE);
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private float moveSpeed = 0.29f;

    public DungeonBeast(EntityType<? extends DungeonBeast> type, World par1World) {
        super(type, par1World);
                this.xpReward = 60;
                
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.DungeonBeast_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.29)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.DungeonBeast_stats.attack)
                .build();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
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

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.DungeonBeast_stats.health;
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

    public int getArmorValue() {
        return ChaosPersists.DungeonBeast_stats.defense;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DBHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.DBDEAD;
    }

    protected float getSoundVolume() {
        return 0.8f;
    }

    protected float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(4);
        if (i == 1) {
            return ChaosPersists.MyCrystalPinkIngot;
        }
        if (i == 2) {
            return ChaosPersists.MyCrystalApple;
        }
        if (i == 3) {
            return Item.byBlock((Block)Blocks.OAK_LOG);
        }
        return null;
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        return false;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        return super.doHurtTarget(par1Entity);
    }

    @Override
    protected void customServerAiStep() {
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.level.random.nextInt(8) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr(e) < 8.0) {
                    this.setAttacking(1);
                    if (this.level.random.nextInt(7) == 0 || this.level.random.nextInt(8) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1Mob)) {
            return false;
        }
        if (!this.getSensing().canSee(par1Mob)) {
            return false;
        }
        if (par1Mob instanceof Rat) {
            return false;
        }
        if (par1Mob instanceof DungeonBeast) {
            return false;
        }
        if (par1Mob instanceof Rotator) {
            return false;
        }
        if (par1Mob instanceof Peacock) {
            return false;
        }
        if (par1Mob instanceof Irukandji) {
            return false;
        }
        if (par1Mob instanceof Skate) {
            return false;
        }
        if (par1Mob instanceof Whale) {
            return false;
        }
        if (par1Mob instanceof Flounder) {
            return false;
        }
        if (par1Mob instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)par1Mob;
            if (p.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(16.0, 3.0, 16.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte)par1);
    }

    protected boolean isValidLightLevel() {
        if (CrystalDimensionSpawnHelper.isCrystalDimension(this.level)) {
            return true;
        }
        return MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random);
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
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
                    ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) {
                        s = id.getPath();
                    }
                    if (s != null && s.equals("Dungeon Beast")) {
                        return true;
                    }
                }
            }
        }
        if (!this.isValidLightLevel()) {
            return false;
        }
        return true;
    }
}

