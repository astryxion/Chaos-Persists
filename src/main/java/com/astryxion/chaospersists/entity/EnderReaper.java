package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.UUID;

public class EnderReaper extends Monster {
    private static final EntityDataAccessor<Byte> SCREAMING =
            SynchedEntityData.defineId(EnderReaper.class, EntityDataSerializers.BYTE);
    private static final UUID ATTACKING_SPEED_BOOST_UUID =
            UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final AttributeModifier ATTACKING_SPEED_BOOST =
            new AttributeModifier(
                    ATTACKING_SPEED_BOOST_UUID,
                    "Attacking speed boost",
                    0.15000000596046448,
                    AttributeModifier.Operation.ADDITION);
    private int teleportDelay;
    private int stareTimer;
    private LivingEntity lastEntityToAttack;

    public EnderReaper(EntityType<? extends EnderReaper> type, Level level) {
        super(type, level);
        this.setMaxUpStep(1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.EnderReaper_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.37)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.EnderReaper_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.EnderReaper_stats.defense)
                .add(Attributes.FOLLOW_RANGE, 81.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SCREAMING, (byte) 0);
    }

    @Override
    public void tick() {
        if (this.isInWaterRainOrBubble()) {
            this.hurt(this.damageSources().drown(), 1.0f);
        }
        if (this.lastEntityToAttack != this.getTarget()) {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            attributeinstance.removeModifier(ATTACKING_SPEED_BOOST);
            if (this.getTarget() != null) {
                attributeinstance.addTransientModifier(ATTACKING_SPEED_BOOST);
            }
        }
        this.lastEntityToAttack = this.getTarget();
        if (this.level().isClientSide) {
            for (int i = 0; i < 2; ++i) {
                this.level()
                        .addParticle(
                                ParticleTypes.PORTAL,
                                this.getX() + (this.random.nextDouble() - 0.5) * (double) this.getBbWidth(),
                                this.getY()
                                        + this.random.nextDouble() * (double) this.getBbHeight()
                                        - 0.25,
                                this.getZ() + (this.random.nextDouble() - 0.5) * (double) this.getBbWidth(),
                                (this.random.nextDouble() - 0.5) * 2.0,
                                -this.random.nextDouble(),
                                (this.random.nextDouble() - 0.5) * 2.0);
            }
        }
        if (this.level().isDay()
                && !this.level().isClientSide
                && this.getLightLevelDependentMagicValue() > 0.5f
                && this.level().canSeeSky(this.blockPosition())
                && this.random.nextFloat() * 30.0f
                        < (this.getLightLevelDependentMagicValue() - 0.4f) * 2.0f) {
            this.setTarget(null);
            this.setScreaming(false);
            this.teleportRandomly();
        }
        if (this.isInWaterRainOrBubble() || this.isOnFire()) {
            this.setScreaming(false);
            this.teleportRandomly();
        }
        LivingEntity target = this.getTarget();
        if (!this.level().isClientSide && this.isAlive()) {
            this.updateEnderCombatMovement(target);
        }
        super.tick();
    }

    private void updateEnderCombatMovement(LivingEntity target) {
        if (target == null) {
            this.setScreaming(false);
            this.teleportDelay = 0;
            return;
        }
        boolean staredAt = target instanceof Player player && this.shouldAttackPlayer(player);
        boolean revenge = this.getLastHurtByMob() == target;
        if (staredAt && !revenge && this.distanceToSqr(target) < 16.0) {
            if (this.teleportDelay <= 0 && this.teleportRandomly()) {
                this.getNavigation().stop();
                this.teleportDelay = 40;
            } else if (this.teleportDelay > 0) {
                --this.teleportDelay;
            }
            return;
        }
        if (this.distanceToSqr(target) > 256.0
                && this.teleportDelay++ >= 30
                && this.teleportToEntity(target)) {
            this.getNavigation().stop();
            this.teleportDelay = 0;
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        LivingEntity current = this.getTarget();
        if (current != null
                && (!current.isAlive()
                        || !MyUtils.isValidAggroTarget(current)
                        || MyUtils.shouldSkipCombatTarget(this, current))) {
            this.setTarget(null);
            current = null;
        }
        if (current == null) {
            LivingEntity revenge = this.getLastHurtByMob();
            if (revenge != null
                    && revenge.isAlive()
                    && MyUtils.isValidAggroTarget(revenge)
                    && !MyUtils.shouldSkipCombatTarget(this, revenge)) {
                this.setTarget(revenge);
            } else {
                this.setTarget(this.findPlayerToAttack());
            }
        }
    }

    protected Player findPlayerToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        Player entityplayer = this.level().getNearestPlayer(this, 81.0);
        if (entityplayer != null) {
            if (this.shouldAttackPlayer(entityplayer)) {
                if (this.stareTimer == 0) {
                    this.level()
                            .playSound(
                                    null,
                                    entityplayer.getX(),
                                    entityplayer.getY(),
                                    entityplayer.getZ(),
                                    SoundEvents.ENDERMAN_STARE,
                                    SoundSource.HOSTILE,
                                    1.0f,
                                    1.0f);
                }
                if (this.stareTimer++ == 5) {
                    this.stareTimer = 0;
                }
                this.setScreaming(true);
                return entityplayer;
            }
            this.stareTimer = 0;
            this.setScreaming(false);
        }
        return null;
    }

    private boolean shouldAttackPlayer(Player par1EntityPlayer) {
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityPlayer)) {
            return false;
        }
        ItemStack itemstack = par1EntityPlayer.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
        if (!itemstack.isEmpty() && itemstack.is(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        }
        Vec3 look = par1EntityPlayer.getViewVector(1.0f).normalize();
        Vec3 vec31 =
                new Vec3(
                        this.getX() - par1EntityPlayer.getX(),
                        this.getBoundingBox().minY
                                + (double) (this.getBbHeight() / 2.0f)
                                - (par1EntityPlayer.getY() + (double) par1EntityPlayer.getEyeHeight()),
                        this.getZ() - par1EntityPlayer.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = look.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 && par1EntityPlayer.hasLineOfSight(this);
    }

    protected boolean teleportRandomly() {
        double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 64.0;
        double d1 = this.getY() + (double) (this.random.nextInt(64) - 32);
        double d2 = this.getZ() + (this.random.nextDouble() - 0.5) * 64.0;
        return this.tryEnderTeleportTo(d0, d1, d2);
    }

    protected boolean teleportToEntity(Entity par1Entity) {
        Vec3 vec3 =
                new Vec3(
                        this.getX() - par1Entity.getX(),
                        this.getBoundingBox().minY
                                + (double) (this.getBbHeight() / 2.0f)
                                - par1Entity.getY()
                                + (double) par1Entity.getEyeHeight(),
                        this.getZ() - par1Entity.getZ());
        vec3 = vec3.normalize();
        double d0 = 16.0;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5) * 8.0 - vec3.x * d0;
        double d2 = this.getY() + (double) (this.random.nextInt(16) - 8) - vec3.y * d0;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5) * 8.0 - vec3.z * d0;
        return this.tryEnderTeleportTo(d1, d2, d3);
    }

    public boolean tryEnderTeleportTo(double par1, double par3, double par5) {
        double d3 = this.getX();
        double d4 = this.getY();
        double d5 = this.getZ();
        this.setPos(par1, par3, par5);
        boolean flag = false;
        int i = Mth.floor(this.getX());
        int j = Mth.floor(this.getY());
        int k = Mth.floor(this.getZ());
        BlockPos blockPos = new BlockPos(i, j, k);
        if (this.level().hasChunkAt(blockPos)) {
            boolean flag1 = false;
            while (!flag1 && j > this.level().getMinBuildHeight()) {
                BlockPos below = new BlockPos(i, j - 1, k);
                BlockState state = this.level().getBlockState(below);
                if (!state.isAir() && state.isSolid()) {
                    flag1 = true;
                } else {
                    this.setPos(this.getX(), this.getY() - 1.0, this.getZ());
                    --j;
                }
            }
            if (flag1) {
                this.setPos(this.getX(), this.getY(), this.getZ());
                AABB box = this.getBoundingBox();
                if (this.level().noCollision(this, box) && !this.isInFluid(box)) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            this.setPos(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        if (this.level().isClientSide) {
            for (int lx = 0; lx < short1; ++lx) {
                double d6 = (double) lx / ((double) short1 - 1.0);
                float pf = (this.random.nextFloat() - 0.5f) * 0.2f;
                float pf1 = (this.random.nextFloat() - 0.5f) * 0.2f;
                float pf2 = (this.random.nextFloat() - 0.5f) * 0.2f;
                double d7 =
                        d3
                                + (this.getX() - d3) * d6
                                + (this.random.nextDouble() - 0.5) * (double) this.getBbWidth() * 2.0;
                double d8 = d4 + (this.getY() - d4) * d6 + this.random.nextDouble() * (double) this.getBbHeight();
                double d9 =
                        d5
                                + (this.getZ() - d5) * d6
                                + (this.random.nextDouble() - 0.5) * (double) this.getBbWidth() * 2.0;
                this.level().addParticle(ParticleTypes.PORTAL, d7, d8, d9, (double) pf, (double) pf1, (double) pf2);
            }
        } else {
            this.level()
                    .playSound(
                            null,
                            d3,
                            d4,
                            d5,
                            SoundEvents.ENDERMAN_TELEPORT,
                            SoundSource.HOSTILE,
                            1.0f,
                            1.0f);
            this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }
        return true;
    }

    private boolean isInFluid(AABB box) {
        int minX = Mth.floor(box.minX);
        int maxX = Mth.floor(box.maxX);
        int minY = Mth.floor(box.minY);
        int maxY = Mth.floor(box.maxY);
        int minZ = Mth.floor(box.minZ);
        int maxZ = Mth.floor(box.maxZ);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    pos.set(x, y, z);
                    FluidState fluid = this.level().getFluidState(pos);
                    if (fluid.is(FluidTags.WATER) || fluid.is(FluidTags.LAVA)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isScreaming() ? SoundEvents.ENDERMAN_SCREAM : SoundEvents.ENDERMAN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENDERMAN_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDERMAN_DEATH;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int k = this.random.nextInt(2 + looting);
        for (int l = 0; l < k; ++l) {
            this.spawnAtLocation(Items.ENDER_EYE);
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        this.setScreaming(true);
        if (par1DamageSource.isIndirect()) {
            for (int i = 0; i < 16; ++i) {
                if (this.teleportRandomly()) {
                    return true;
                }
            }
        }
        boolean hurt = super.hurt(par1DamageSource, par2);
        if (hurt) {
            Entity attacker = par1DamageSource.getEntity();
            if (attacker instanceof LivingEntity living
                    && living != this
                    && MyUtils.isValidAggroTarget(living)
                    && !MyUtils.shouldSkipCombatTarget(this, living)) {
                this.setTarget(living);
            }
        }
        return hurt;
    }

    public static boolean checkEnderReaperSpawnRules(
            EntityType<EnderReaper> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
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
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation reaperId =
                            new ResourceLocation("chaospersists", "ender_reaper");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, reaperId)
                            || "Ender Reaper".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (!Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random)) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (pos.getY() < 30) {
            return false;
        }
        List<EnderReaper> nearby =
                level.getLevel().getEntitiesOfClass(EnderReaper.class, new AABB(pos).inflate(16.0, 8.0, 16.0));
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
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation reaperId =
                            new ResourceLocation("chaospersists", "ender_reaper");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, reaperId)
                            || "Ender Reaper".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (this.level().isDay()) {
            return false;
        }
        if (this.getY() < 30.0) {
            return false;
        }
        return this.level()
                .getEntitiesOfClass(EnderReaper.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0))
                .isEmpty();
    }

    public boolean isScreaming() {
        return this.entityData.get(SCREAMING) > 0;
    }

    public void setScreaming(boolean par1) {
        this.entityData.set(SCREAMING, (byte) (par1 ? 1 : 0));
    }
}
