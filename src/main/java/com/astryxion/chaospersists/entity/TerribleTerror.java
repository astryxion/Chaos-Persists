package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import com.astryxion.chaospersists.util.RoyalPetFollowHelper;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TerribleTerror extends TamableAnimal {
    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;

    public TerribleTerror(EntityType<? extends TerribleTerror> type, Level level) {
        super(type, level);
        this.xpReward = 10;
        this.setOrderedToSit(false);
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.TerribleTerror_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.TerribleTerror_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.TerribleTerror_stats.defense);
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
    public boolean isBaby() {
        return false;
    }

    @Override
    public void setAge(int age) {
        super.setAge(Math.max(0, age));
    }

    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType reason,
            SpawnGroupData spawnData,
            CompoundTag dataTag) {
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
        this.setAge(0);
        return data;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.setOrderedToSit(false);
        this.setTame(false);
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (orderedToSit) {
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            if (!this.level().isClientSide) {
                PetCombatHelper.onPetSit(this);
            }
            this.setNoGravity(false);
            this.noPhysics = false;
            MyUtils.clearChaosFlight(this);
            this.currentFlightTarget = null;
            this.setDeltaMovement(0.0, Math.min(this.getDeltaMovement().y, 0.0), 0.0);
            this.xxa = 0.0f;
            this.zza = 0.0f;
            this.yya = 0.0f;
        }
    }

    private boolean isSittingNow() {
        return this.isOrderedToSit() || this.isInSittingPose();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && stack.getCount() <= 0) {
            player.setItemInHand(hand, ItemStack.EMPTY);
            stack = ItemStack.EMPTY;
        }
        if (!stack.isEmpty() && stack.is(Items.CHICKEN) && player.distanceToSqr(this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(2) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(player.getUUID());
                        this.setPersistenceRequired();
                        this.setOrderedToSit(false);
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(player)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !stack.isEmpty()
                && stack.is(Blocks.DEAD_BUSH.asItem())
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
                this.setTame(false);
                this.setOwnerUUID(null);
                this.setHealth((float) this.mygetMaxHealth());
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && stack.isEmpty()
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(!this.isOrderedToSit());
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.TerribleTerror_stats.health;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.TERRIBLETERROR_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.TERRIBLETERROR_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.TERRIBLETERROR_DEAD;
    }

    @Override
    public void tick() {
        boolean sitting = this.isSittingNow();
        if (sitting) {
            MyUtils.clearChaosFlight(this);
            this.setNoGravity(false);
            this.noPhysics = false;
            this.xxa = 0.0f;
            this.zza = 0.0f;
            this.yya = 0.0f;
        }
        super.tick();
        if (sitting) {
            Vec3 dm = this.getDeltaMovement();
            this.setDeltaMovement(0.0, this.onGround() ? 0.0 : Math.min(dm.y, -0.25), 0.0);
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        return par1Entity.hurt(
                this.damageSources().mobAttack(this), (float) ChaosPersists.TerribleTerror_stats.attack);
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        Vec3 from = new Vec3(this.getX(), this.getY() + 0.75, this.getZ());
        Vec3 to = new Vec3(pX, pY, pZ);
        return this.level().clip(new ClipContext(from, to, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this))
                        .getType()
                == HitResult.Type.MISS;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isSittingNow()) {
            super.travel(Vec3.ZERO);
            return;
        }
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        PetCombatHelper.tickPetCombat(this);
        if (this.isSittingNow()) {
            MyUtils.clearChaosFlight(this);
            this.setNoGravity(false);
            this.noPhysics = false;
            this.setTarget(null);
            this.currentFlightTarget = null;
            this.xxa = 0.0f;
            this.zza = 0.0f;
            this.yya = 0.0f;
            Vec3 dm = this.getDeltaMovement();
            this.setDeltaMovement(0.0, this.onGround() ? 0.0 : Math.min(dm.y, -0.2), 0.0);
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            return;
        }
        super.customServerAiStep();
        if (this.isTame()) {
            RoyalPetFollowHelper.catchUpFlyingRoyal(this);
        }
        int xdir = 1;
        int zdir = 1;
        int keepTrying = 50;
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        LivingEntity owner = this.isTame() ? this.getOwner() : null;
        LivingEntity prey = null;
        if (this.isTame()) {
            prey = PetCombatHelper.resolveCombatTarget(this, this.getTarget(), this::findSomethingToAttack);
            if (prey != this.getTarget()) {
                this.setTarget(prey);
            }
        } else if (this.getRandom().nextInt(9) == 0) {
            prey = this.findSomethingToAttack();
            this.setTarget(prey);
        }
        boolean chasing = prey != null && this.level().getDifficulty() != Difficulty.PEACEFUL;
        if (chasing) {
            this.currentFlightTarget =
                    new BlockPos((int) prey.getX(), (int) (prey.getY() + 1.0), (int) prey.getZ());
            if (this.distanceToSqr(prey) < 36.0) {
                this.doHurtTarget(prey);
            }
        } else if (this.getRandom().nextInt(100) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 2.1
                || (owner != null && this.distanceToSqr(owner) > 100.0)) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (!bid.isAir() && keepTrying != 0) {
                int gox = (int) this.getX();
                int goy = (int) this.getY();
                int goz = (int) this.getZ();
                zdir = this.getRandom().nextInt(5) + 5;
                xdir = this.getRandom().nextInt(5) + 5;
                if (owner != null) {
                    gox = (int) owner.getX();
                    goy = (int) owner.getY();
                    goz = (int) owner.getZ();
                    zdir = this.getRandom().nextInt(4) + 6;
                    xdir = this.getRandom().nextInt(4) + 6;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.getRandom().nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget =
                        new BlockPos(gox + xdir, goy + this.getRandom().nextInt(5) - 2, goz + zdir);
                bid = this.level().getBlockState(this.currentFlightTarget);
                if (bid.isAir()
                        && !this.canSeeTarget(
                                this.currentFlightTarget.getX(),
                                this.currentFlightTarget.getY(),
                                this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE.defaultBlockState();
                }
                --keepTrying;
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.4 - motion.x) * 0.30000000149011613,
                        (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.20000000149011612,
                        (Math.signum(var5) * 0.4 - motion.z) * 0.30000000149011613));
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
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (ret && !this.isDeadOrDying() && this.isSittingNow()) {
            this.setOrderedToSit(false);
        }
        if (e != null && !this.isSittingNow()) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
        }
        return ret;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = this.getRandom().nextInt(3);
        Item drop = null;
        if (i == 0) {
            drop = Items.ROTTEN_FLESH;
        } else if (i == 1) {
            drop = Items.EMERALD;
        } else if (i == 2) {
            drop = Items.FEATHER;
        }
        if (drop != null) {
            this.spawnAtLocation(drop);
        }
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (this.isTame()) {
            if (!PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
                return false;
            }
            if (par1EntityLiving instanceof Mothra) {
                return true;
            }
            return PetCombatHelper.isAutoHostileTarget(par1EntityLiving);
        }
        if (par1EntityLiving instanceof RockBase) {
            return false;
        }
        if (par1EntityLiving instanceof TerribleTerror) {
            return false;
        }
        if (par1EntityLiving instanceof Mothra) {
            return false;
        }
        if (par1EntityLiving instanceof Rotator) {
            return false;
        }
        if (par1EntityLiving instanceof Bee) {
            return false;
        }
        if (par1EntityLiving instanceof Mantis) {
            return false;
        }
        if (par1EntityLiving instanceof CreepingHorror) {
            return false;
        }
        if (par1EntityLiving instanceof Dragon) {
            return false;
        }
        if (par1EntityLiving instanceof EntityButterfly) {
            return false;
        }
        if (par1EntityLiving instanceof Firefly) {
            return false;
        }
        if (par1EntityLiving instanceof EnderReaper) {
            return false;
        }
        if (par1EntityLiving instanceof EnderKnight) {
            return false;
        }
        if (par1EntityLiving instanceof LeafMonster) {
            return false;
        }
        if (par1EntityLiving instanceof LurkingTerror) {
            return false;
        }
        if (par1EntityLiving instanceof CloudShark) {
            return false;
        }
        if (par1EntityLiving instanceof Triffid) {
            return false;
        }
        if (par1EntityLiving instanceof PitchBlack) {
            return false;
        }
        if (par1EntityLiving instanceof Island) {
            return false;
        }
        if (par1EntityLiving instanceof IslandToo) {
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
        if (this.isTame() && this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
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

    public static boolean checkTerribleTerrorSpawnRules(
            EntityType<TerribleTerror> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
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
                    ResourceLocation terrorId =
                            new ResourceLocation("chaospersists", "terrible_terror");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, terrorId)
                            || "Terrible Terror".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (level.getMaxLocalRawBrightness(pos) > 7) {
            return false;
        }
        if (MyUtils.isDay(level)) {
            return false;
        }
        if (!level.getLevel().dimension().equals(ChaosPersists.getDimensionKey(6)) && pos.getY() > 40) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
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
                    ResourceLocation terrorId =
                            new ResourceLocation("chaospersists", "terrible_terror");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, terrorId)
                            || "Terrible Terror".equals(id.getPath())) {
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
        if (!this.level().dimension().equals(ChaosPersists.getDimensionKey(6)) && this.getY() > 40.0) {
            return false;
        }
        return true;
    }
}
