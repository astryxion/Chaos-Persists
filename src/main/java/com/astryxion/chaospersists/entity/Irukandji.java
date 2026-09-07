package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class Irukandji extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(Irukandji.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private LivingEntity buddy = null;
    private final float moveSpeed = 0.15f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Irukandji(EntityType<? extends Irukandji> type, Level level) {
        super(type, level);
        this.xpReward = 50;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Irukandji_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.15f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Irukandji_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Irukandji_stats.defense);
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
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Irukandji_stats.health;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LITTLE_SPLAT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.RATDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.25f;
    }

    @Override
    public float getVoicePitch() {
        return 2.0f;
    }

    public int getAttackStrength(Entity par1Entity) {
        return 2;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        Item drop = ChaosPersists.MyIrukandji;
        if (drop == null) {
            drop =
                    ForgeRegistries.ITEMS.getValue(
                            new ResourceLocation("chaospersists", "deadirukandji"));
        }
        if (drop != null) {
            this.spawnAtLocation(new ItemStack(drop));
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player.getItemInHand(hand).isEmpty()) {
            player.hurt(this.damageSources().mobAttack(this), 200.0f);
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isDeadOrDying()) {
            return false;
        }
        Entity e = source.getEntity();
        if (e instanceof Player player && player.getMainHandItem().isEmpty()) {
            player.hurt(this.damageSources().mobAttack(this), 200.0f);
            return false;
        }
        boolean ret = false;
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (e instanceof Irukandji) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
            ret = true;
        }
        ret = super.hurt(source, amount) || ret;
        return ret;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x - dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                if (state.is(Blocks.WATER)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y - dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                if (state.is(Blocks.WATER)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z + dz;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                if (state.is(Blocks.WATER)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z - dz;
                        ++found;
                    }
                }
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
        if (!this.isInWater() && this.getRandom().nextInt(10) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 5) {
                    j = 5;
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
                if (this.getRandom().nextInt(25) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.kill();
                    return;
                }
            }
        }
        if (this.getRandom().nextInt(8) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr(e) < 3.0) {
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
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
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
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
                                LivingEntity.class, this.getBoundingBox().inflate(6.0, 4.0, 6.0));
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

    public final int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    private int findBuddies() {
        List<Irukandji> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Irukandji.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0));
        return var5.size();
    }

    public static boolean checkIrukandjiSpawnRules(
            EntityType<Irukandji> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (random.nextInt(60) != 1) {
            return false;
        }
        List<Irukandji> buddies =
                level.getLevel()
                        .getEntitiesOfClass(Irukandji.class, new AABB(pos).inflate(16.0, 8.0, 16.0));
        if (buddies.size() > 2) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.getRandom().nextInt(60) != 1) {
            return false;
        }
        if (this.findBuddies() > 2) {
            return false;
        }
        return true;
    }
}
