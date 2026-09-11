package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
public class Triffid extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Triffid.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> STATE2 =
            SynchedEntityData.defineId(Triffid.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private float moveSpeed = 0.13f;

    public Triffid(EntityType<? extends Triffid> type, Level level) {
        super(type, level);
        this.xpReward = 50;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Triffid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.13)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Triffid_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Triffid_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        this.entityData.define(STATE2, (byte) 0);
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

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.random.nextInt(100) == 1) {
            int ix = Mth.floor(this.getX());
            int iz = Mth.floor(this.getZ());
            for (int k = -5; k <= 5; ++k) {
                BlockState bid =
                        this.level()
                                .getBlockState(
                                        new BlockPos(
                                                Mth.floor(this.getX()),
                                                Mth.floor(this.getY()) - 1,
                                                Mth.floor(this.getZ()) + k));
                if (bid.isAir()) {
                    continue;
                }
                if (k < 0) {
                    --iz;
                }
                if (k > 0) {
                    ++iz;
                }
            }
            for (int k = -5; k <= 5; ++k) {
                BlockState bid =
                        this.level()
                                .getBlockState(
                                        new BlockPos(
                                                Mth.floor(this.getX()) + k,
                                                Mth.floor(this.getY()) - 1,
                                                Mth.floor(this.getZ())));
                if (bid.isAir()) {
                    continue;
                }
                if (k < 0) {
                    --ix;
                }
                if (k > 0) {
                    ++ix;
                }
            }
            this.getNavigation().moveTo(ix, this.getY(), iz, 1.0);
        }
        if (this.hurt_timer <= 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                float yaw = (float) Math.toDegrees(Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX())) - 90.0f;
                while (yaw < 0.0f) {
                    yaw += 360.0f;
                }
                this.setYRot(yaw);
                this.yHeadRot = yaw;
            }
        }
        if (!this.level().isClientSide && this.hurt_timer > 0) {
            this.setDeltaMovement(0.0, this.getDeltaMovement().y, 0.0);
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Triffid_stats.health;
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

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.TRIFFID_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.TRIFFID_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.TRIFFID_DEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 4 + this.random.nextInt(6);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.GreenGoo, 1);
        }
        this.dropItemRand(Items.ITEM_FRAME, 1);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        return super.doHurtTarget(par1Entity);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        // /kill and other absolute damage pierce closed shell / i-frames
        if (par1DamageSource.is(net.minecraft.tags.DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return super.hurt(par1DamageSource, par2);
        }
        if (this.hurt_timer > 0 || this.getOpenClosed() == 0) {
            this.hurt_timer = 300;
            this.setAttacking(0);
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (ret) {
            this.hurt_timer = 300;
            this.setOpenClosed(0);
            this.setAttacking(0);
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
            this.setRemainingFireTicks(0);
            this.setOpenClosed(0);
        }
        if (this.random.nextInt(250) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
        if (this.random.nextInt(80) == 2 && this.hurt_timer <= 0) {
            if (this.random.nextInt(8) == 1) {
                this.setOpenClosed(1);
            } else {
                this.setOpenClosed(0);
            }
        }
        if (this.random.nextInt(10) == 1 && this.hurt_timer <= 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.setOpenClosed(1);
                if (this.distanceToSqr(e) < 25.0) {
                    float yaw =
                            (float) Math.toDegrees(Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX())) - 90.0f;
                    while (yaw < 0.0f) {
                        yaw += 360.0f;
                    }
                    this.setYRot(yaw);
                    this.yHeadRot = yaw;
                    this.setAttacking(1);
                    this.doHurtTarget(e);
                } else {
                    this.setAttacking(0);
                }
            } else {
                this.setAttacking(0);
            }
        }
    }

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(3)
                                - (double) ChaosPersists.ChaosRand.nextInt(3),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(3)
                                - (double) ChaosPersists.ChaosRand.nextInt(3),
                        is);
        this.level().addFreshEntity(entityItem);
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
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Creeper) {
            return false;
        }
        if (par1EntityLiving instanceof EnderReaper) {
            return false;
        }
        if (par1EntityLiving instanceof EnderKnight) {
            return false;
        }
        if (par1EntityLiving instanceof Triffid) {
            return false;
        }
        if (par1EntityLiving instanceof TerribleTerror) {
            return false;
        }
        if (par1EntityLiving instanceof LurkingTerror) {
            return false;
        }
        if (par1EntityLiving instanceof PitchBlack) {
            return false;
        }
        if (par1EntityLiving instanceof Dragon) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.isCreative();
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10.0, 8.0, 10.0));
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

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public int getOpenClosed() {
        return this.entityData.get(STATE2);
    }

    public void setOpenClosed(int par1) {
        this.entityData.set(STATE2, (byte) par1);
    }

    public int getTriffidHealth() {
        return (int) this.getHealth();
    }

    public static boolean checkTriffidSpawnRules(
            EntityType<Triffid> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return true;
    }
}
