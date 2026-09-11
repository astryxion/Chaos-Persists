package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Mothra extends EntityButterfly {
    private BlockPos mothraFlightTarget = null;
    private int lastX = 0;
    private int lastZ = 0;
    private int lastY = 0;
    private int stuck_count = 0;
    private int wing_sound = 0;
    private int health_ticker = 100;
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.35f;

    public Mothra(EntityType<? extends Mothra> type, Level level) {
        super(type, level);
        this.targetSorter = new GenericTargetSorter(this);
        this.xpReward = 100;
        this.setNoGravity(true);
        this.fireImmune();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return EntityButterfly.createAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.Mothra_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.35f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.Mothra_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.Mothra_stats.defense);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    public int getMothraHealth() {
        return (int) this.getHealth();
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.sounds.SoundEvent getDeathSound() {
        return SoundEvents.GENERIC_EXPLODE;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.Mothra_stats.health;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        ++this.wing_sound;
        if (this.wing_sound > 30) {
            if (!this.level().isClientSide && ChaosSounds.MOTHRA_WINGS != null) {
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ChaosSounds.MOTHRA_WINGS,
                                SoundSource.NEUTRAL,
                                1.0f,
                                1.0f);
            }
            this.wing_sound = 0;
        }
        --this.health_ticker;
        if (this.health_ticker <= 0) {
            if (this.getHealth() < (float) this.mygetMaxHealth()) {
                this.heal(1.0f);
            }
            this.health_ticker = 200;
        }
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        HitResult hit =
                this.level()
                        .clip(
                                new ClipContext(
                                        new Vec3(this.getX(), this.getY() + 0.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        ClipContext.Block.COLLIDER,
                                        ClipContext.Fluid.NONE,
                                        this));
        return hit.getType() == HitResult.Type.MISS;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        int shoot = 3;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.lastX == (int) this.getX() && this.lastY == (int) this.getY() && this.lastZ == (int) this.getZ()) {
            ++this.stuck_count;
        } else {
            this.stuck_count = 0;
            this.lastX = (int) this.getX();
            this.lastY = (int) this.getY();
            this.lastZ = (int) this.getZ();
        }
        if (this.level().getDifficulty() == Difficulty.HARD) {
            shoot = 2;
        }
        if (this.mothraFlightTarget == null) {
            this.mothraFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.stuck_count > 50
                || this.getRandom().nextInt(300) == 0
                || this.mothraFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 9.0) {
            BlockState bid;
            int down = 0;
            int dist = 20;
            for (int i = -5; i <= 5; i += 5) {
                block1:
                for (int j = -5; j <= 5; j += 5) {
                    for (int k = 1; k < 20; ++k) {
                        bid = this.level()
                                .getBlockState(
                                        new BlockPos((int) this.getX() + j, (int) this.getY() - k, (int) this.getZ() + i));
                        if (bid.getBlock() == Blocks.AIR) {
                            continue;
                        }
                        if (k >= dist) {
                            continue block1;
                        }
                        dist = k;
                        continue block1;
                    }
                }
            }
            if (dist > 10) {
                down = dist - 10 + 1;
            }
            bid = Blocks.STONE.defaultBlockState();
            while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                xdir = 1;
                zdir = 1;
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -1;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -1;
                }
                int newz = this.getRandom().nextInt(20) + 8;
                int newx = this.getRandom().nextInt(20) + 8;
                this.mothraFlightTarget = new BlockPos(
                        (int) this.getX() + (newx * xdir),
                        (int) this.getY() + this.getRandom().nextInt(7) - 1 - down,
                        (int) this.getZ() + (newz * zdir));
                bid = this.level().getBlockState(this.mothraFlightTarget);
                if (bid.getBlock() == Blocks.AIR
                        && !this.canSeeTarget(
                                (double) this.mothraFlightTarget.getX(),
                                (double) this.mothraFlightTarget.getY(),
                                (double) this.mothraFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keep_trying;
            }
            this.stuck_count = 0;
        } else if (this.getRandom().nextInt(10) == 0
                && this.level().getDifficulty() != Difficulty.PEACEFUL
                && ChaosPersists.MothraPeaceful == 0) {
            Player target =
                    this.level()
                            .getNearestPlayer(
                                    this.getX(),
                                    this.getY(),
                                    this.getZ(),
                                    25.0,
                                    false);
            if (target != null) {
                if (!target.isCreative()) {
                    if (this.getSensing().hasLineOfSight(target)) {
                        this.mothraFlightTarget =
                                new BlockPos((int) target.getX(), (int) target.getY() + 4, (int) target.getZ());
                        if (this.getRandom().nextInt(shoot) == 0) {
                            this.attackWithSomething(target);
                        }
                    }
                } else {
                    target = null;
                }
            }
            if (target == null && this.getRandom().nextInt(3) == 0) {
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
                    this.mothraFlightTarget = new BlockPos((int) e.getX(), (int) e.getY() + 5, (int) e.getZ());
                    if (this.getRandom().nextInt(shoot) == 0) {
                        this.attackWithSomething(e);
                    }
                }
            }
        }
        double var1 = (double) this.mothraFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.mothraFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.mothraFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.5 - motion.x) * 0.30001,
                        (Math.signum(var3) * 0.7 - motion.y) * 0.20001,
                        (Math.signum(var5) * 0.5 - motion.z) * 0.30001));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 4.0f);
        MyUtils.applyChaosFlightMovement(this);
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if (e instanceof Mothra) {
            return false;
        }
        boolean ret = super.hurt(par1DamageSource, par2);
        if (e != null && this.mothraFlightTarget != null) {
            this.mothraFlightTarget = new BlockPos((int) e.getX(), (int) e.getY() + 2, (int) e.getZ());
        }
        return ret;
    }

    public static boolean checkMothraSpawnRules(
            EntityType<Mothra> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (MyUtils.getBlockStateForSpawnRules(level, pos).getBlock() != Blocks.AIR) {
            return false;
        }
        if (pos.getY() < 70) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        for (int k = -4; k < 4; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 1; i < 10; ++i) {
                    if (MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).getBlock() != Blocks.AIR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockState bid;
        int j;
        int i;
        int k;
        for (k = -2; k <= 2; ++k) {
            for (j = -2; j <= 2; ++j) {
                for (i = 1; i < 4; ++i) {
                    BlockPos checkPos = new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    bid = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (bid.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Mothra".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (this.getY() < 70.0) {
            return false;
        }
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        for (k = -4; k < 4; ++k) {
            for (j = -3; j < 3; ++j) {
                for (i = 1; i < 10; ++i) {
                    bid = MyUtils.getBlockStateForSpawnRules(level, new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k));
                    if (bid.getBlock() == Blocks.AIR) {
                        continue;
                    }
                    return false;
                }
            }
        }
        if (level instanceof Level world) {
            List<Mothra> nearby =
                    world.getEntitiesOfClass(
                            Mothra.class,
                            this.getBoundingBox().inflate(64.0, 32.0, 64.0),
                            e -> e != this);
            if (!nearby.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity item =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) ChaosPersists.ChaosRand.nextInt(8) - (double) ChaosPersists.ChaosRand.nextInt(8),
                        this.getY() + 1.0,
                        this.getZ() + (double) ChaosPersists.ChaosRand.nextInt(8) - (double) ChaosPersists.ChaosRand.nextInt(8),
                        new ItemStack(index, par1));
        this.level().addFreshEntity(item);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var4;
        this.dropItemRand(Items.ITEM_FRAME, 1);
        for (int i = 0; i < 20; ++i) {
            float var1 = (this.getRandom().nextFloat() - 0.5f) * 8.0f;
            float var2 = (this.getRandom().nextFloat() - 0.5f) * 4.0f;
            float var3 = (this.getRandom().nextFloat() - 0.5f) * 8.0f;
            this.level()
                    .addParticle(
                            ParticleTypes.EXPLOSION_EMITTER,
                            this.getX() + (double) var1,
                            this.getY() + 2.0 + (double) var2,
                            this.getZ() + (double) var3,
                            0.0,
                            0.0,
                            0.0);
        }
        for (var4 = 0; var4 < 53; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        Item mothScale =
                ForgeRegistries.ITEMS.getValue(new ResourceLocation("chaospersists", "mothscale"));
        if (mothScale != null) {
            for (var4 = 0; var4 < 25; ++var4) {
                this.dropItemRand(mothScale, 1);
            }
        }
        for (var4 = 0; var4 < 3; ++var4) {
            this.dropItemRand(Items.BLAZE_ROD, 1);
        }
        this.dropItemRand(Items.NETHER_STAR, 1);
        if (this.level() instanceof ServerLevel serverLevel) {
            for (var4 = 0; var4 < 20; ++var4) {
                spawnCreature(serverLevel, "moth", this.getX() + 0.5, this.getY() + 1.0, this.getZ() + 0.5);
            }
        }
    }

    public static Entity spawnCreature(ServerLevel level, String entityPath, double px, double py, double pz) {
        ResourceLocation id = new ResourceLocation("chaospersists", entityPath);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(id);
        if (type == null) {
            return null;
        }
        Entity entity = type.create(level);
        if (entity != null) {
            entity.moveTo(px, py, pz, level.getRandom().nextFloat() * 360.0f, 0.0f);
            level.addFreshEntity(entity);
            if (entity instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return entity;
    }

    private void attackWithSomething(LivingEntity par1) {
        double xzoff = 2.25;
        double yoff = 0.0;
        if (ChaosPersists.MothraPeaceful != 0) {
            return;
        }
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        if (this.level().getDifficulty() == Difficulty.EASY) {
            SmallFireball sf =
                    new SmallFireball(
                            this.level(),
                            this,
                            par1.getX() - cx,
                            par1.getY() + 0.55 - (this.getY() + yoff),
                            par1.getZ() - cz);
            sf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.HOSTILE,
                            0.75f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(sf);
        } else if (this.level().getDifficulty() == Difficulty.NORMAL) {
            if (this.getRandom().nextInt(2) == 0) {
                SmallFireball sf =
                        new SmallFireball(
                                this.level(),
                                this,
                                par1.getX() - cx,
                                par1.getY() + 0.55 - (this.getY() + yoff),
                                par1.getZ() - cz);
                sf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                SoundSource.HOSTILE,
                                0.75f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(sf);
            } else {
                BetterFireball bf =
                        new BetterFireball(
                                this.level(),
                                this,
                                par1.getX() - cx,
                                par1.getY() + 0.55 - (this.getY() + yoff),
                                par1.getZ() - cz);
                bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                bf.setNotMe();
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.TNT_PRIMED,
                                SoundSource.HOSTILE,
                                1.0f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(bf);
            }
        } else {
            BetterFireball bf =
                    new BetterFireball(
                            this.level(),
                            this,
                            par1.getX() - cx,
                            par1.getY() + 0.55 - (this.getY() + yoff),
                            par1.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
            bf.setNotMe();
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.TNT_PRIMED,
                            SoundSource.HOSTILE,
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(bf);
        }
        if (this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
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
        String n = par1EntityLiving.getClass().getSimpleName();
        if (n.equals("Mothra")
                || n.equals("Brutalfly")
                || n.equals("Vortex")
                || n.equals("VelocityRaptor")
                || n.equals("Cryolophosaurus")
                || n.equals("TerribleTerror")
                || n.equals("LurkingTerror")
                || n.equals("CloudShark")
                || n.equals("Rotator")
                || n.equals("Bee")
                || n.equals("Mantis")) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
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
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(15.0, 20.0, 15.0));
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
}
