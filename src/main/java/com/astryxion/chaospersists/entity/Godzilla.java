package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class Godzilla extends Monster {
    private static final net.minecraft.network.syncher.EntityDataAccessor<Byte> ATTACKING =
            net.minecraft.network.syncher.SynchedEntityData.defineId(
                    Godzilla.class, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> PLAY_NICELY =
            net.minecraft.network.syncher.SynchedEntityData.defineId(
                    Godzilla.class, net.minecraft.network.syncher.EntityDataSerializers.INT);

    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.75f;
    private int hurt_timer = 0;
    private int jumped = 0;
    private int jump_timer = 0;
    private int ticker = 0;
    private RenderInfo renderdata = new RenderInfo();
    private int stream_count = 8;
    private MyEntityAIWanderALot wander = null;
    private int head_found = 0;
    private int headEntityId = -1;
    private int large_unknown_detected = 0;
    private int lastPlayNicely = -1;

    public Godzilla(EntityType<? extends Godzilla> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.setMaxUpStep(1.0F);
        this.xpReward = 10000;
        this.noPhysics = false;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 1.0, false, 4, () -> false));
        this.wander = new MyEntityAIWanderALot(this, 15, 1.0);
        this.goalSelector.addGoal(2, this.wander);
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, LivingEntity.class, 50.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
        this.applyGodzillaDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Godzilla_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.75f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Godzilla_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Godzilla_stats.defense);
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

    private void applyGodzillaDimensions() {
        this.refreshDimensions();
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.applyGodzillaDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (ChaosPersists.PlayNicely == 0) {
            return EntityDimensions.scalable(9.9f, 25.0f);
        }
        return EntityDimensions.scalable(2.475f, 6.25f);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY);
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
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (ChaosPersists.PlayNicely != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Godzilla_stats.health;
    }

    @Override
    public int getArmorValue() {
        if (this.large_unknown_detected != 0) {
            return 25;
        }
        return ChaosPersists.Godzilla_stats.defense;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        int pn = this.getPlayNicely();
        if (pn != this.lastPlayNicely) {
            this.lastPlayNicely = pn;
            this.applyGodzillaDimensions();
        }
        if (!this.onGround() && this.jump_timer > 0) {
            this.getNavigation().stop();
        }
    }

    private void discardAttachedHeads() {
        if (this.headEntityId >= 0) {
            Entity head = this.level().getEntity(this.headEntityId);
            if (head != null) {
                head.discard();
            }
            this.headEntityId = -1;
        }
        AABB box = this.getBoundingBox().inflate(64.0, 64.0, 64.0);
        for (GodzillaHead head : this.level().getEntitiesOfClass(GodzillaHead.class, box)) {
            head.discard();
        }
        this.head_found = 0;
    }

    @Override
    public void die(DamageSource source) {
        this.discardAttachedHeads();
        super.die(source);
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        this.discardAttachedHeads();
        super.remove(reason);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    protected float getDamageAfterArmorAbsorb(DamageSource damageSource, float damageAmount) {
        return Math.min(super.getDamageAfterArmorAbsorb(damageSource, damageAmount), 120.0f);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(5) == 0) {
            return ChaosSounds.GODZILLA_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource ds) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.GODZILLA_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.65f;
    }

    @Override
    public float getVoicePitch() {
        return 1.1f;
    }

    @Override
    protected void jumpFromGround() {
        while (this.getYRot() < 0.0f) {
            this.setYRot(this.getYRot() + 360.0f);
        }
        while (this.getYHeadRot() < 0.0f) {
            this.setYHeadRot(this.getYHeadRot() + 360.0f);
        }
        while (this.getYRot() > 360.0f) {
            this.setYRot(this.getYRot() - 360.0f);
        }
        while (this.getYHeadRot() > 360.0f) {
            this.setYHeadRot(this.getYHeadRot() - 360.0f);
        }
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y + 0.44999998807907104, motion.z);
        this.setPos(this.getX(), this.getY() + 0.5, this.getZ());
        float f = 0.2f + Math.abs(this.getRandom().nextFloat() * 0.45f);
        this.setDeltaMovement(
                this.getDeltaMovement().x
                        + (double) f * Math.cos(Math.toRadians(this.getYHeadRot() + 90.0f)),
                this.getDeltaMovement().y,
                this.getDeltaMovement().z
                        + (double) f * Math.sin(Math.toRadians(this.getYHeadRot() + 90.0f)));
        this.setOnGround(false);
        this.jump_timer = Math.max(this.jump_timer, 10);
        this.getNavigation().stop();
    }

    protected void jumpAtEntity(LivingEntity e) {
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                this.getDeltaMovement().y + 1.25,
                this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 1.5499999523162842, this.getZ());
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float) Math.atan2(d2, d1);
        this.setYRot((float) ((double) d * 180.0 / 3.141592653589793) - 90.0f);
        d1 = Math.sqrt(d1 * d1 + d2 * d2);
        this.setDeltaMovement(
                this.getDeltaMovement().x + d1 * 0.05 * Math.cos(d),
                this.getDeltaMovement().y,
                this.getDeltaMovement().z + d1 * 0.05 * Math.sin(d));
        this.setOnGround(false);
        this.jump_timer = Math.max(this.jump_timer, 20);
        this.getNavigation().stop();
    }

    private double getHorizontalDistanceSqToEntity(Entity e) {
        double d1 = e.getZ() - this.getZ();
        double d2 = e.getX() - this.getX();
        return d1 * d1 + d2 * d2;
    }

    public double MygetDistanceSqToEntity(Entity par1Entity) {
        double d0 = this.getX() - par1Entity.getX();
        double d1 = par1Entity.getY() - this.getY();
        double d2 = this.getZ() - par1Entity.getZ();
        if (d1 > 0.0 && d1 < 20.0) {
            d1 = 0.0;
        }
        if (d1 > 20.0) {
            d1 -= 10.0;
        }
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    @Override
    protected void customServerAiStep() {
        int j;
        int i;
        Block bid;
        LivingEntity e = null;
        int xzrange = 9;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        ++this.ticker;
        if (this.ticker > 30000) {
            this.ticker = 0;
        }
        if (this.ticker % 100 == 0) {
            this.stream_count = 8;
        }
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.jump_timer > 0) {
            --this.jump_timer;
        }
        ChaosPersists.godzilla_has_spawned = 1;
        if (this.getRandom().nextInt(200) == 0) {
            this.setTarget(null);
        }
        if (ChaosPersists.PlayNicely == 0) {
            if (this.getDeltaMovement().y < -0.95) {
                this.jumped = 1;
            }
            if (this.getDeltaMovement().y < -1.5) {
                this.jumped = 2;
            }
            if (this.jumped != 0 && this.getDeltaMovement().y > -0.1) {
                double df = 1.0;
                if (this.jumped == 2) {
                    df = 1.5;
                }
                this.doJumpDamage(
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        10.0,
                        (double) ChaosPersists.Godzilla_stats.attack * df,
                        0);
                this.doJumpDamage(
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        15.0,
                        (double) (ChaosPersists.Godzilla_stats.attack / 2) * df,
                        0);
                this.doJumpDamage(
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        25.0,
                        (double) (ChaosPersists.Godzilla_stats.attack / 4) * df,
                        0);
                this.jumped = 0;
            }
        }
        xzrange = 12;
        if (this.getAttacking() != 0) {
            xzrange = 16;
        }
        int k = -3 + this.ticker % 30;
        BlockPos.MutableBlockPos crushPos = new BlockPos.MutableBlockPos();
        if (ChaosPersists.PlayNicely == 0) {
            for (i = -xzrange; i <= xzrange; ++i) {
                for (j = -xzrange; j <= xzrange; ++j) {
                    crushPos.set((int) this.getX() + i, (int) this.getY() + k, (int) this.getZ() + j);
                    bid = this.level().getBlockState(crushPos).getBlock();
                    if (this.isCrushable(bid)) {
                        this.level().setBlock(crushPos, Blocks.AIR.defaultBlockState(), 3);
                        continue;
                    }
                    if (bid == Blocks.GRASS_BLOCK
                            && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level().setBlock(crushPos, Blocks.DIRT.defaultBlockState(), 3);
                    }
                    if (bid != Blocks.FARMLAND
                            || !this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        continue;
                    }
                    this.level().setBlock(crushPos, Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }
        double dx = this.getX() + 16.0 * Math.sin(Math.toRadians(this.getYHeadRot()));
        double dz = this.getZ() - 16.0 * Math.cos(Math.toRadians(this.getYHeadRot()));
        k = -3 + this.ticker % 12;
        if (ChaosPersists.PlayNicely == 0) {
            for (i = -xzrange; i <= xzrange; ++i) {
                for (j = -xzrange; j <= xzrange; ++j) {
                    crushPos.set((int) dx + i, (int) this.getY() + k, (int) dz + j);
                    bid = this.level().getBlockState(crushPos).getBlock();
                    if (this.isCrushable(bid)) {
                        this.level().setBlock(crushPos, Blocks.AIR.defaultBlockState(), 3);
                        continue;
                    }
                    if (bid == Blocks.GRASS_BLOCK
                            && this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level().setBlock(crushPos, Blocks.DIRT.defaultBlockState(), 3);
                    }
                    if (bid != Blocks.FARMLAND
                            || !this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        continue;
                    }
                    this.level().setBlock(crushPos, Blocks.DIRT.defaultBlockState(), 3);
                }
            }
        }
        if (ChaosPersists.PlayNicely == 0 && k == 0) {
            this.doJumpDamage(
                    dx,
                    this.getY(),
                    dz,
                    15.0,
                    (double) (ChaosPersists.Godzilla_stats.attack / 2),
                    1);
        }
        if (this.getRandom().nextInt(5 - this.large_unknown_detected) == 1) {
            e = this.getTarget();
            if (ChaosPersists.PlayNicely != 0) {
                e = null;
            }
            if (e != null) {
                if (!e.isAlive()) {
                    this.setTarget(null);
                    e = null;
                } else if (e instanceof Godzilla || e instanceof GodzillaHead) {
                    this.setTarget(null);
                    e = null;
                }
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
                if (this.head_found == 0) {
                    Entity spawned = spawnCreature(
                            this.level(),
                            "chaospersists:mobzilla_head",
                            this.getX(),
                            this.getY() + 20.0,
                            this.getZ());
                    if (spawned != null) {
                        this.head_found = 1;
                        this.headEntityId = spawned.getId();
                    }
                }
            }
            if (e != null) {
                this.wander.setBusy(1);
                if (this.getRandom().nextInt(65) == 1 && this.MygetDistanceSqToEntity(e) > 300.0) {
                    this.doLightningAttack(e);
                } else if (this.getRandom().nextInt(20 - this.large_unknown_detected * 5) == 1
                        && this.jump_timer == 0) {
                    this.jumpAtEntity(e);
                    this.jump_timer = 30;
                } else if (this.MygetDistanceSqToEntity(e)
                        < (double) (300.0f + e.getBbWidth() / 2.0f * (e.getBbWidth() / 2.0f))) {
                    this.setAttacking(1);
                    this.getNavigation().moveTo(e, 1.0);
                    if (this.getRandom().nextInt(4 - this.large_unknown_detected) == 0
                            || this.getRandom().nextInt(3 - this.large_unknown_detected) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.0);
                    if (this.getHorizontalDistanceSqToEntity(e) > 625.0) {
                        if (this.stream_count > 0) {
                            this.setAttacking(1);
                            double rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                            double rhdir = Math.toRadians((this.getYHeadRot() + 90.0f) % 360.0f);
                            double pi = 3.1415926545;
                            double rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                            if (rdd > pi) {
                                rdd -= pi * 2.0;
                            }
                            rdd = Math.abs(rdd);
                            if (rdd < 0.5) {
                                this.firecanon(e);
                            }
                        } else {
                            this.setAttacking(0);
                        }
                    } else {
                        this.setAttacking(0);
                    }
                }
            } else {
                this.setAttacking(0);
                this.wander.setBusy(0);
                this.stream_count = 8;
            }
        }
        if (this.getRandom().nextInt(35) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(5.0f);
        }
    }

    public static Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        ResourceLocation res = resolveSpawnId(par1);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    private static ResourceLocation resolveSpawnId(String par1) {
        if (par1.contains(":")) {
            return new ResourceLocation(par1);
        }
        return switch (par1) {
            case "GodzillaHead" ->
                    new ResourceLocation("chaospersists", "mobzilla_head");
            default ->
                    new ResourceLocation(
                            "chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
        };
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
        if (par1EntityLiving instanceof Godzilla) {
            return false;
        }
        if (par1EntityLiving instanceof GodzillaHead) {
            return false;
        }
        if (par1EntityLiving instanceof Creeper) {
            return false;
        }
        if (par1EntityLiving instanceof Zombie) {
            return false;
        }
        if (par1EntityLiving instanceof Spider) {
            return false;
        }
        if (par1EntityLiving instanceof Skeleton) {
            return false;
        }
        if (par1EntityLiving instanceof Ghost) {
            return false;
        }
        if (par1EntityLiving instanceof GhostSkelly) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.getAbilities().instabuild;
        }
        return true;
    }

    private boolean isVillagerTarget(LivingEntity par1EntityLiving, boolean par2) {
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        return par1EntityLiving instanceof Villager;
    }

    private LivingEntity doJumpDamage(
            double X, double Y, double Z, double dist, double damage, int knock) {
        AABB bb = new AABB(X - dist, Y - 10.0, Z - dist, X + dist, Y + 10.0, Z + dist);
        List<LivingEntity> var5 = this.level().getEntitiesOfClass(LivingEntity.class, bb);
        Collections.sort(var5, this.targetSorter);
        for (LivingEntity var4 : var5) {
            if (var4 == null
                    || var4 == this
                    || !var4.isAlive()
                    || var4 instanceof Godzilla
                    || var4 instanceof GodzillaHead
                    || var4 instanceof Ghost
                    || var4 instanceof GhostSkelly) {
                continue;
            }
            var4.hurt(this.damageSources().explosion(null), (float) damage / 2.0f);
            var4.hurt(this.damageSources().fall(), (float) damage / 2.0f);
            this.level()
                    .playSound(
                            null,
                            var4.getX(),
                            var4.getY(),
                            var4.getZ(),
                            SoundEvents.GENERIC_EXPLODE,
                            this.getSoundSource(),
                            0.85f,
                            1.0f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.5f);
            if (knock == 0) {
                continue;
            }
            double ks = 3.5;
            double inair = 0.75;
            float f3 = (float) Math.atan2(var4.getZ() - this.getZ(), var4.getX() - this.getX());
            var4.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return null;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            this.head_found = 1;
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(64.0, 40.0, 64.0));
        if (var5 == null) {
            return null;
        }
        Collections.sort(var5, this.targetSorter);
        LivingEntity ret = null;
        boolean vf = false;
        this.head_found = 0;
        for (LivingEntity var4 : var5) {
            if (var4 instanceof GodzillaHead) {
                this.head_found = 1;
            }
            if (!vf && this.isVillagerTarget(var4, false)) {
                ret = var4;
                vf = true;
            }
            if (ret != null || vf || !this.isSuitableTarget(var4, false)) {
                continue;
            }
            ret = var4;
        }
        return ret;
    }

    protected boolean isValidLightLevel(LevelAccessor level, BlockPos pos) {
        return level.getMaxLocalRawBrightness(pos) < 8;
    }

    public static boolean checkGodzillaSpawnRules(
            EntityType<Godzilla> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (level.getMaxLocalRawBrightness(pos) >= 8) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (ChaosPersists.godzilla_has_spawned != 0) {
            return false;
        }
        if (random.nextInt(40) != 1) {
            return false;
        }
        BlockPos.MutableBlockPos check = new BlockPos.MutableBlockPos();
        for (int k = -8; k <= 8; ++k) {
            for (int j = -8; j <= 8; ++j) {
                for (int i = 5; i < 15; ++i) {
                    check.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, check).isAir()) {
                        continue;
                    }
                    return false;
                }
            }
        }
        AABB box =
                new AABB(pos)
                        .inflate(64.0, 16.0, 64.0);
        if (!level.getEntitiesOfClass(Godzilla.class, box).isEmpty()) {
            return false;
        }
        if (!level.isClientSide()) {
            ChaosPersists.godzilla_has_spawned = 1;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (spawnReason == MobSpawnType.SPAWN_EGG || spawnReason == MobSpawnType.COMMAND) {
            return true;
        }
        if (!(level instanceof ServerLevelAccessor serverLevel)) {
            return false;
        }
        return checkGodzillaSpawnRules(
                ChaosPersists.ENTITY_TYPE_MOBZILLA.get(), serverLevel, spawnReason, this.blockPosition(), this.getRandom());
    }

    public final int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    private ItemStack dropItemRandMod(String path, int par1) {
        Item item =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", path));
        if (item == null) {
            return null;
        }
        return this.dropItemRand(item, par1);
    }

    private ItemStack dropItemRand(Item index, int par1) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(10)
                                - (double) ChaosPersists.ChaosRand.nextInt(10),
                        this.getY() + 4.0 + (double) this.getRandom().nextInt(10),
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(10)
                                - (double) ChaosPersists.ChaosRand.nextInt(10),
                        is);
        this.level().addFreshEntity(var3);
        return is;
    }

    private ItemStack dropItemRandAt(Item index, int par1, double dx, double dz) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        dx + (double) ChaosPersists.ChaosRand.nextInt(10)
                                - (double) ChaosPersists.ChaosRand.nextInt(10),
                        this.getY() + 4.0 + (double) this.getRandom().nextInt(6),
                        dz + (double) ChaosPersists.ChaosRand.nextInt(10)
                                - (double) ChaosPersists.ChaosRand.nextInt(10),
                        is);
        this.level().addFreshEntity(var3);
        return is;
    }

    private boolean isCrushable(Block bid) {
        if (bid == null) {
            return false;
        }
        if (!this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
            return false;
        }
        if (bid == Blocks.GRASS_BLOCK) {
            return false;
        }
        if (bid == Blocks.DIRT) {
            return false;
        }
        if (bid == Blocks.STONE) {
            return false;
        }
        if (bid == Blocks.FARMLAND) {
            return false;
        }
        if (bid == Blocks.WATER) {
            return false;
        }
        if (bid == Blocks.LAVA) {
            return false;
        }
        if (bid == Blocks.BEDROCK) {
            return false;
        }
        if (bid == Blocks.OBSIDIAN) {
            return false;
        }
        if (bid == Blocks.SAND) {
            return false;
        }
        if (bid == Blocks.GRAVEL) {
            return false;
        }
        if (bid == Blocks.IRON_BLOCK) {
            return false;
        }
        if (bid == Blocks.DIAMOND_BLOCK) {
            return false;
        }
        if (bid == Blocks.EMERALD_BLOCK) {
            return false;
        }
        if (bid == Blocks.GOLD_BLOCK) {
            return false;
        }
        if (bid == Blocks.NETHERRACK) {
            return false;
        }
        if (bid == Blocks.END_STONE) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockAmethystBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockRubyBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockUraniumBlock) {
            return false;
        }
        if (bid == ChaosPersists.MyBlockTitaniumBlock) {
            return false;
        }
        if (bid == ChaosPersists.CrystalStone) {
            return false;
        }
        if (bid == ChaosPersists.CrystalGrass) {
            return false;
        }
        return true;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 19.0;
        double xzoff = 22.0;
        BetterFireball bf;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        if (this.stream_count > 0) {
            bf =
                    new BetterFireball(
                            this.level(),
                            this,
                            e.getX() - cx,
                            e.getY() + (double) (e.getBbHeight() / 2.0f) - (this.getY() + yoff),
                            e.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
            bf.setBig();
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.TNT_PRIMED,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(bf);
            for (int i = 0; i < 5; ++i) {
                float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                bf =
                        new BetterFireball(
                                this.level(),
                                this,
                                e.getX() - cx + (double) r1,
                                e.getY()
                                                + (double) (e.getBbHeight() / 2.0f)
                                                - (this.getY() + yoff)
                                        + (double) r2,
                                e.getZ() - cz + (double) r3);
                bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                if (this.getRandom().nextInt(2) == 1) {
                    bf.setSmall();
                }
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                1.0f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(bf);
            }
            --this.stream_count;
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        float s;
        if (!(par1Entity == null
                || !(par1Entity instanceof LivingEntity)
                || (s = par1Entity.getBbHeight() * par1Entity.getBbWidth()) <= 30.0f
                || MyUtils.isRoyalty(par1Entity)
                || par1Entity instanceof Godzilla
                || par1Entity instanceof GodzillaHead
                || par1Entity instanceof PitchBlack
                || par1Entity instanceof Kraken)) {
            LivingEntity living = (LivingEntity) par1Entity;
            living.setHealth(living.getHealth() / 2.0f);
            living.hurt(
                    this.damageSources().mobAttack(this),
                    (float) ChaosPersists.Godzilla_stats.attack * 10.0f);
            this.large_unknown_detected = 1;
        }
        if (par1Entity instanceof EnderDragon dr) {
            DamageSource var21 = this.damageSources().explosion(null);
            if (this.getRandom().nextInt(6) == 1) {
                dr.hurt(dr.head, var21, (float) ChaosPersists.Godzilla_stats.attack / 2.0f);
            } else {
                dr.hurt(var21, (float) ChaosPersists.Godzilla_stats.attack / 2.0f);
            }
        }
        if (super.doHurtTarget(par1Entity)) {
            if (par1Entity instanceof LivingEntity living) {
                double ks = 3.2;
                double inair = 0.3;
                float f3 =
                        (float) Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!living.isAlive() || par1Entity instanceof Player) {
                    inair *= 2.0;
                }
                living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e;
        boolean ret = false;
        float dm = par2;
        float s = 0.0f;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        if (dm > 750.0f) {
            dm = 750.0f;
        }
        if (dm > 120.0f && par1DamageSource.is(DamageTypeTags.BYPASSES_ARMOR)) {
            dm = 120.0f;
        }
        if ((e = par1DamageSource.getEntity()) instanceof LivingEntity enl) {
            s = enl.getBbHeight() * enl.getBbWidth();
            if (!(s <= 30.0f
                    || MyUtils.isRoyalty(enl)
                    || enl instanceof Godzilla
                    || enl instanceof GodzillaHead
                    || enl instanceof PitchBlack
                    || enl instanceof Kraken)) {
                dm /= 10.0f;
                this.large_unknown_detected = 1;
            }
        }
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            ret = super.hurt(par1DamageSource, dm);
            if (ret) {
                this.hurt_timer = 20;
            }
            e = par1DamageSource.getEntity();
            if (e instanceof LivingEntity living
                    && !(e instanceof GodzillaHead)
                    && !(e instanceof Godzilla)
                    && MyUtils.isValidAggroTarget(living)) {
                this.setTarget(living);
                this.getNavigation().moveTo(living, 1.2);
            }
        }
        return ret;
    }

    @Override
    public void thunderHit(ServerLevel level, LightningBolt lightning) {}

    private void doLightningAttack(LivingEntity e) {
        if (e == null) {
            return;
        }
        float var2 = 100.0f;
        e.hurt(this.damageSources().mobAttack(this), var2);
        e.setSecondsOnFire(5);
        for (int var3 = 0; var3 < 20; ++var3) {
            this.level()
                    .addParticle(
                            ParticleTypes.SMOKE,
                            e.getX() + (double) this.getRandom().nextFloat()
                                    - (double) this.getRandom().nextFloat(),
                            e.getY() + (double) this.getRandom().nextFloat()
                                    - (double) this.getRandom().nextFloat(),
                            e.getZ() + (double) this.getRandom().nextFloat(),
                            0.0,
                            0.0,
                            0.0);
            this.level()
                    .addParticle(
                            ParticleTypes.LARGE_SMOKE,
                            e.getX() + (double) this.getRandom().nextFloat()
                                    - (double) this.getRandom().nextFloat(),
                            e.getY() + (double) this.getRandom().nextFloat()
                                    - (double) this.getRandom().nextFloat(),
                            e.getZ() + (double) this.getRandom().nextFloat()
                                    - (double) this.getRandom().nextFloat(),
                            0.0,
                            0.0,
                            0.0);
            this.level()
                    .addParticle(
                            ParticleTypes.FIREWORK,
                            e.getX(),
                            e.getY(),
                            e.getZ(),
                            this.getRandom().nextGaussian(),
                            this.getRandom().nextGaussian(),
                            this.getRandom().nextGaussian());
        }
        this.level()
                .playSound(
                        null,
                        e.getX(),
                        e.getY(),
                        e.getZ(),
                        SoundEvents.GENERIC_EXPLODE,
                        SoundSource.HOSTILE,
                        0.5f,
                        1.0f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.5f);
        if (!this.level().isClientSide) {
            this.level()
                    .explode(
                            this,
                            e.getX(),
                            e.getY(),
                            e.getZ(),
                            3.0f,
                            this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING),
                            Level.ExplosionInteraction.MOB);
        }
        if (this.level() instanceof ServerLevel serverLevel) {
            LightningBolt bolt1 = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if (bolt1 != null) {
                bolt1.moveTo(e.getX(), e.getY() + 1.0, e.getZ());
                serverLevel.addFreshEntity(bolt1);
            }
            LightningBolt bolt2 = EntityType.LIGHTNING_BOLT.create(serverLevel);
            if (bolt2 != null) {
                bolt2.moveTo(this.getX(), this.getY() + 15.0, this.getZ());
                serverLevel.addFreshEntity(bolt2);
            }
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.dropFewItems();
    }

        private void dropFewItems() {
        int var4;
        ItemStack is = null;
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 50 + this.getRandom().nextInt(30);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(ChaosPersists.MyGodzillaScale, 1);
        }
        var5 = 100 + this.getRandom().nextInt(160);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
        var5 = 50 + this.getRandom().nextInt(60);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.BONE, 1);
        }
        int i = 25 + this.getRandom().nextInt(15);
        block80 : for (var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(80);
            switch (var3) {
                case 0: {
                    is = this.dropItemRandMod("ultimatesword", 1);
                    continue block80;
                }
                case 1: {
                    is = this.dropItemRand(Items.DIAMOND, 1);
                    continue block80;
                }
                case 2: {
                    is = this.dropItemRand(Items.DIAMOND_BLOCK, 1);
                    continue block80;
                }
                case 3: {
                    is = this.dropItemRand(Items.DIAMOND_SWORD, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 4: {
                    is = this.dropItemRand(Items.DIAMOND_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 5: {
                    is = this.dropItemRand(Items.DIAMOND_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 6: {
                    is = this.dropItemRand(Items.DIAMOND_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 7: {
                    is = this.dropItemRand(Items.DIAMOND_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 8: {
                    is = this.dropItemRand(Items.DIAMOND_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(2));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 9: {
                    is = this.dropItemRand(Items.DIAMOND_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 10: {
                    is = this.dropItemRand(Items.DIAMOND_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 11: {
                    is = this.dropItemRand(Items.DIAMOND_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 12: {
                    is = this.dropItemRandMod("ultimatebow", 1);
                    continue block80;
                }
                case 13: {
                    is = this.dropItemRandMod("ultimateaxe", 1);
                    continue block80;
                }
                case 14: {
                    is = this.dropItemRand(Items.IRON_INGOT, 1);
                    continue block80;
                }
                case 15: {
                    is = this.dropItemRandMod("ultimatepickaxe", 1);
                    continue block80;
                }
                case 16: {
                    is = this.dropItemRand(Items.IRON_SWORD, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 17: {
                    is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 18: {
                    is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 19: {
                    is = this.dropItemRand(Items.IRON_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 20: {
                    is = this.dropItemRand(Items.IRON_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 21: {
                    is = this.dropItemRand(Items.IRON_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 22: {
                    is = this.dropItemRand(Items.IRON_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 23: {
                    is = this.dropItemRand(Items.IRON_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 24: {
                    is = this.dropItemRand(Items.IRON_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 25: {
                    is = this.dropItemRandMod("ultimateshovel", 1);
                    continue block80;
                }
                case 26: {
                    this.dropItemRand(Items.IRON_BLOCK, 1);
                    continue block80;
                }
                case 27: {
                    is = this.dropItemRand(Items.GOLD_NUGGET, 1);
                    continue block80;
                }
                case 28: {
                    is = this.dropItemRand(Items.GOLD_INGOT, 1);
                    continue block80;
                }
                case 29: {
                    is = this.dropItemRand(Items.GOLDEN_CARROT, 1);
                    continue block80;
                }
                case 30: {
                    is = this.dropItemRand(Items.GOLDEN_SWORD, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 31: {
                    is = this.dropItemRand(Items.GOLDEN_SHOVEL, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 32: {
                    is = this.dropItemRand(Items.GOLDEN_PICKAXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 33: {
                    is = this.dropItemRand(Items.GOLDEN_AXE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 34: {
                    is = this.dropItemRand(Items.GOLDEN_HOE, 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 35: {
                    is = this.dropItemRand(Items.GOLDEN_HELMET, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 36: {
                    is = this.dropItemRand(Items.GOLDEN_CHESTPLATE, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 37: {
                    is = this.dropItemRand(Items.GOLDEN_LEGGINGS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 38: {
                    is = this.dropItemRand(Items.GOLDEN_BOOTS, 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 39: {
                    this.dropItemRand(Items.GOLDEN_APPLE, 1);
                    continue block80;
                }
                case 40: {
                    this.dropItemRand(Items.GOLD_BLOCK, 1);
                    continue block80;
                }
                case 41: {
                    this.dropItemRand(Items.ENCHANTED_GOLDEN_APPLE, 1);
                    continue block80;
                }
                case 42: {
                    is = this.dropItemRandMod("experiencesword", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 43: {
                    is = this.dropItemRandMod("experience_helmet", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 44: {
                    is = this.dropItemRandMod("experience_chest", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 45: {
                    is = this.dropItemRandMod("experience_leggings", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 46: {
                    is = this.dropItemRandMod("experience_boots", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 47: {
                    is = this.dropItemRandMod("amethystsword", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 48: {
                    is = this.dropItemRandMod("amethystshovel", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 49: {
                    is = this.dropItemRandMod("amethystpickaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 50: {
                    is = this.dropItemRandMod("amethystaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 51: {
                    is = this.dropItemRandMod("amethysthoe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 52: {
                    is = this.dropItemRand(ChaosPersists.MyBlockAmethystBlock.asItem(), 1);
                    continue block80;
                }
                case 53: {
                    is = this.dropItemRandMod("amethyst_helmet", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 54: {
                    is = this.dropItemRandMod("amethyst_chest", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 55: {
                    is = this.dropItemRandMod("amethyst_leggings", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 56: {
                    is = this.dropItemRandMod("amethyst_boots", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 57: {
                    is = this.dropItemRandMod("ruby_helmet", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 58: {
                    is = this.dropItemRandMod("ruby_chest", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 59: {
                    is = this.dropItemRandMod("ruby_leggings", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 60: {
                    is = this.dropItemRandMod("ruby_boots", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 61: {
                    is = this.dropItemRandMod("rubysword", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 62: {
                    is = this.dropItemRandMod("rubyshovel", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 63: {
                    is = this.dropItemRandMod("rubypickaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 64: {
                    is = this.dropItemRandMod("rubyaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 65: {
                    is = this.dropItemRandMod("rubyhoe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 66: {
                    is = this.dropItemRand(ChaosPersists.MyBlockRubyBlock.asItem(), 1);
                    continue block80;
                }
                case 67: {
                    is = this.dropItemRandMod("ultimate_helmet", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 68: {
                    is = this.dropItemRandMod("ultimate_chest", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 69: {
                    is = this.dropItemRandMod("ultimate_leggings", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 70: {
                    is = this.dropItemRandMod("ultimate_boots", 1);
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(2) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    continue block80;
                }
                case 71: {
                    is = this.dropItemRandMod("ultimateshovel", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 73: {
                    is = this.dropItemRandMod("ultimatepickaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 74: {
                    is = this.dropItemRandMod("ultimateaxe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(net.minecraft.world.item.enchantment.Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(net.minecraft.world.item.enchantment.Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    continue block80;
                }
                case 75: {
                    is = this.dropItemRandMod("ultimatehoe", 1);
                    if (this.getRandom().nextInt(2) == 1) {
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    if (this.getRandom().nextInt(6) != 1) continue block80;
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                    break;
                }
            }
        }
    }
}
