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
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class Frog extends Animal {
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> SINGING =
            net.minecraft.network.syncher.SynchedEntityData.defineId(Frog.class, net.minecraft.network.syncher.EntityDataSerializers.INT);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> JUMPING =
            net.minecraft.network.syncher.SynchedEntityData.defineId(Frog.class, net.minecraft.network.syncher.EntityDataSerializers.INT);
    private final GenericTargetSorter targetSorter;
    public double moveSpeed = 0.10000000149011612;
    private int singing = 0;
    private int jumpcount = 0;

    public Frog(EntityType<? extends Frog> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(2, new MyEntityAIWander(this, 1.0f));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SINGING, 0);
        this.entityData.define(JUMPING, 0);
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

    public int getSinging() {
        return this.entityData.get(SINGING);
    }

    public void setSinging(int par1) {
        this.entityData.set(SINGING, par1);
    }

    public int getJumping() {
        return this.entityData.get(JUMPING);
    }

    public void setJumping(int par1) {
        this.entityData.set(JUMPING, par1);
    }

    private void jumpAround() {
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.x - (0.7f + Math.abs(this.getRandom().nextFloat() * 0.75f)) * Math.sin(Math.toRadians(this.getYRot())),
                motion.y + (0.75f + Math.abs(this.getRandom().nextFloat() * 0.55f)),
                motion.z + (0.7f + Math.abs(this.getRandom().nextFloat() * 0.75f)) * Math.cos(Math.toRadians(this.getYRot())));
        this.setPos(this.getX(), this.getY() + 0.3499999940395355, this.getZ());
        this.setOnGround(false);
        this.setJumping(30);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
        if (!this.level().isClientSide) {
            if (this.singing != 0) {
                --this.singing;
                if (this.singing <= 0) {
                    this.setSinging(0);
                }
            }
            if (this.jumpcount > 0) {
                --this.jumpcount;
            }
            if (this.jumpcount == 0 && this.getRandom().nextInt(70) == 1) {
                this.jumpAround();
                this.jumpcount = 50;
            }
            int jumping = this.getJumping();
            if (jumping > 0) {
                if (this.onGround()) {
                    this.setJumping(0);
                } else {
                    this.setJumping(jumping - 1);
                }
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player == null || !player.isShiftKeyDown() || !player.getItemInHand(hand).isEmpty()) {
            return InteractionResult.PASS;
        }
        Level world = player.level();
        if (!world.isClientSide) {
            this.discard();
            world.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.GENERIC_EXPLODE,
                    SoundSource.PLAYERS,
                    1.0f,
                    world.getRandom().nextFloat() * 0.2f + 0.9f);
            if (world.getRandom().nextInt(2) == 0) {
                Boyfriend ent = (Boyfriend) spawnCreature(world, "boyfriend", this.getX(), this.getY() + 0.01, this.getZ());
                if (ent != null) {
                    ent.setPrince(1 + world.getRandom().nextInt(2));
                }
            } else {
                Girlfriend ent =
                        (Girlfriend) spawnCreature(world, "girlfriend", this.getX(), this.getY() + 0.01, this.getZ());
                if (ent != null) {
                    ent.setPrincess(1 + world.getRandom().nextInt(2));
                }
            }
        } else {
            RandomSource rand = world.getRandom();
            for (int var3 = 0; var3 < 16; ++var3) {
                world.addParticle(
                        ParticleTypes.SMOKE,
                        (float) this.getX() + rand.nextFloat() - rand.nextFloat(),
                        (float) this.getY() + rand.nextFloat(),
                        (float) this.getZ() + rand.nextFloat() - rand.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
                world.addParticle(
                        ParticleTypes.EXPLOSION,
                        (float) this.getX() + rand.nextFloat() - rand.nextFloat(),
                        (float) this.getY() + rand.nextFloat(),
                        (float) this.getZ() + rand.nextFloat() - rand.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
                world.addParticle(
                        new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f),
                        (float) this.getX() + rand.nextFloat() - rand.nextFloat(),
                        (float) this.getY() + rand.nextFloat(),
                        (float) this.getZ() + rand.nextFloat() - rand.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
            }
        }
        return InteractionResult.PASS;
    }

    public int mygetMaxHealth() {
        return 8;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().isClientSide) {
            return null;
        }
        if (this.getRandom().nextInt(2) == 0) {
            return null;
        }
        this.singing = 35;
        this.setSinging(this.singing);
        return ChaosSounds.FROG;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SCORPION_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.BIG_SPLAT;
    }

    @Override
    protected float getSoundVolume() {
        return 0.7f;
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
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        for (int i = 0; i < 4; ++i) {
            this.spawnAtLocation(new ItemStack(Items.SLIME_BALL, 1));
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        boolean hit = target.hurt(this.damageSources().mobAttack(this), 3.0f);
        if (hit && !target.isAlive()) {
            this.heal(1.0f);
        }
        return hit;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        boolean ret = super.hurt(source, amount);
        if (!this.level().isClientSide && this.jumpcount <= 0) {
            this.jumpAround();
            this.jumpcount = 25;
        }
        return ret;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.getRandom().nextInt(12) == 0 && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr(e) < 6.0) {
                    this.doHurtTarget(e);
                }
            }
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
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof EntityAnt) {
            return true;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return true;
        }
        if (par1EntityLiving instanceof Cricket) {
            return true;
        }
        if (par1EntityLiving instanceof EntityMosquito) {
            return true;
        }
        if (par1EntityLiving instanceof Firefly) {
            return true;
        }
        if (par1EntityLiving instanceof WormSmall) {
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
                                LivingEntity.class, this.getBoundingBox().inflate(8.0, 3.0, 8.0));
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

    private int findBuddies() {
        List<Frog> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Frog.class, this.getBoundingBox().inflate(20.0, 8.0, 20.0));
        return var5.size();
    }

    private boolean chaosUtopiaOrVillageDimension() {
        if (this.level() == null) {
            return false;
        }
        ResourceKey<Level> dim = this.level().dimension();
        return dim.equals(ChaosPersists.getDimensionKey(1))
                || dim.equals(ChaosPersists.getDimensionKey(3));
    }

    private boolean feetInWater() {
        BlockPos base = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        for (int k = 0; k <= 2; k++) {
            if (this.level().getBlockState(base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    private static boolean chaosUtopiaOrVillageDimension(ServerLevelAccessor level) {
        ResourceKey<Level> dim = level.getLevel().dimension();
        return dim.equals(ChaosPersists.getDimensionKey(1))
                || dim.equals(ChaosPersists.getDimensionKey(3));
    }

    private static boolean feetInWaterAt(ServerLevelAccessor level, BlockPos base) {
        for (int k = 0; k <= 2; k++) {
            if (MyUtils.getBlockStateForSpawnRules(level, base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFrogSpawnRules(
            EntityType<Frog> type,
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
        if (chaosUtopiaOrVillageDimension(level)) {
            if (!feetInWaterAt(level, pos)) {
                return false;
            }
            if (level.getLevel().getEntitiesOfClass(Frog.class, new AABB(pos).inflate(20.0, 8.0, 20.0)).size() > 2) {
                return false;
            }
        } else if (level.getLevel().getEntitiesOfClass(Frog.class, new AABB(pos).inflate(20.0, 8.0, 20.0)).size() > 5) {
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
        if (this.chaosUtopiaOrVillageDimension()) {
            if (!this.feetInWater()) {
                return false;
            }
            if (this.findBuddies() > 2) {
                return false;
            }
        } else if (this.findBuddies() > 5) {
            return false;
        }
        return true;
    }

    public static Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation("chaospersists", par1.toLowerCase());
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
        if (entity instanceof LivingEntity living) {
            MyUtils.playAmbientSound(living);
        }
        return entity;
    }
}
