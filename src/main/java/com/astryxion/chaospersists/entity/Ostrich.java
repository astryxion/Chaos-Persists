package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.ChaosMountHelper;
import com.astryxion.chaospersists.util.MyEntityAIAvoidEntity;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.RandomSource;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.registries.ForgeRegistries;

public class Ostrich extends EntityCannonFodder {
    private float moveSpeed = 0.38f;
    private RenderInfo renderdata = new RenderInfo();
    private int dismountCooldown = 0;

    public Ostrich(EntityType<? extends Ostrich> type, Level level) {
        super(type, level);
        this.setOrderedToSit(false);
        this.xpReward = 10;
        this.renderdata = new RenderInfo();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(
                3, new MyEntityAIAvoidEntity(this, Monster.class, 8.0f, 1.0, 1.899999976158142));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2000000476837158, Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, LivingEntity.class, 5.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new OpenDoorGoal(this, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 25.0)
                .add(Attributes.MOVEMENT_SPEED, 0.38)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
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

    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType spawnType,
            @Nullable SpawnGroupData spawnData,
            @Nullable CompoundTag data) {
        SpawnGroupData result = super.finalizeSpawn(level, difficulty, spawnType, spawnData, data);
        this.setOrderedToSit(false);
        this.noPhysics = false;
        this.setNoGravity(false);
        MyUtils.clearChaosFlight(this);
        return result;
    }

    @Override
    public void tick() {
        if (this.dismountCooldown > 0) {
            --this.dismountCooldown;
        }
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        if (this.getPassengers().isEmpty()) {
            this.noPhysics = false;
            this.setNoGravity(false);
            MyUtils.clearChaosFlight(this);
        }
        // Stay put: panic/avoid/wander still path while "sitting" unless we hard-stop
        if (!this.level().isClientSide && this.isOrderedToSit() && this.getPassengers().isEmpty()) {
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.0, 1.0, 0.0));
        }
        super.tick();
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
    public void setOrderedToSit(boolean sitting) {
        super.setOrderedToSit(sitting);
        this.setInSittingPose(sitting);
        if (sitting && this.getNavigation() != null) {
            this.getNavigation().stop();
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile()
                || (this.isOrderedToSit() && this.getPassengers().isEmpty());
    }

    /** OreSpawn nest materials, plus common dirt/sand tag variants so stay works on modern blocks. */
    private boolean canNestSitHere() {
        BlockState below =
                this.level()
                        .getBlockState(BlockPos.containing(this.getX(), this.getY() - 0.2, this.getZ()));
        Block bid = below.getBlock();
        return bid == Blocks.SAND
                || bid == Blocks.RED_SAND
                || bid == Blocks.GRAVEL
                || bid == Blocks.DIRT
                || bid == Blocks.COARSE_DIRT
                || bid == Blocks.ROOTED_DIRT
                || bid == Blocks.PODZOL
                || bid == Blocks.MYCELIUM
                || bid == Blocks.MUD
                || bid == Blocks.DIRT_PATH
                || bid == Blocks.FARMLAND
                || bid == Blocks.GRASS_BLOCK
                || bid == Blocks.SANDSTONE
                || bid == Blocks.SMOOTH_SANDSTONE
                || below.is(net.minecraft.tags.BlockTags.DIRT)
                || below.is(net.minecraft.tags.BlockTags.SAND);
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof LivingEntity living ? living : null;
    }

    @Override
    protected void tickRidden(Player player, Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        this.xxa = player.xxa;
        this.zza = player.zza;
        this.setRot(player.getYRot(), player.getXRot() * 0.5f);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isVehicle() && this.getControllingPassenger() instanceof Player player) {
            float forward = player.zza;
            float strafe = player.xxa;
            if (this.level().isClientSide && player instanceof LocalPlayer local) {
                forward = local.input.forwardImpulse;
                strafe = local.input.leftImpulse;
            }
            this.xxa = strafe;
            this.zza = forward;
            this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
            if (Math.abs(forward) > 0.001f || Math.abs(strafe) > 0.001f) {
                super.travel(new Vec3(strafe, travelVector.y, forward));
            } else {
                super.travel(new Vec3(0.0, travelVector.y, 0.0));
            }
            return;
        }
        super.travel(travelVector);
    }

    @Override
    public void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        if (passenger != null) {
            this.positionRider(passenger, Entity::moveTo);
        }
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level().isClientSide && this.getPassengers().isEmpty()) {
            this.dismountCooldown = ChaosMountHelper.GROUND_DISMOUNT_COOLDOWN_TICKS;
            ChaosMountHelper.finishDismountLanding(this);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.is(DamageTypes.CACTUS)) {
            return false;
        }
        if (!this.level().isClientSide) {
            this.setOrderedToSit(false);
        }
        return super.hurt(source, amount);
    }

    public int mygetMaxHealth() {
        return 25;
    }

    public int getOstrichHealth() {
        return (int) this.getHealth();
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (var2.isEmpty()) {
            if (ChaosMountHelper.canPlayerMount(par1EntityPlayer, this, this.dismountCooldown)) {
                if (!this.level().isClientSide) {
                    par1EntityPlayer.startRiding(this);
                    ChaosMountHelper.onPlayerMounted(this);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            if (this.isTame()
                    && this.isOwnedBy(par1EntityPlayer)
                    && this.isOrderedToSit()) {
                if (!this.level().isClientSide) {
                    this.setOrderedToSit(false);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            return InteractionResult.PASS;
        }
        if (!var2.isEmpty() && super.mobInteract(par1EntityPlayer, hand) == InteractionResult.SUCCESS) {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
            }
            return InteractionResult.SUCCESS;
        }
        if (!var2.isEmpty() && var2.is(Items.APPLE) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(2) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1EntityPlayer.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(par1EntityPlayer)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DEAD_BUSH.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (!var2.isEmpty()
                && this.isTame()
                && this.isOwnedBy(par1EntityPlayer)
                && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.level().isClientSide) {
                if (!this.isOrderedToSit()) {
                    // Stay still on nest-like ground (OreSpawn) — expanded for 1.20 blocks
                    if (this.canNestSitHere()) {
                        this.setOrderedToSit(true);
                    }
                } else {
                    this.setOrderedToSit(false);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Items.NAME_TAG)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            this.setCustomName(var2.getHoverName());
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        // TamableAnimal already loads Sitting — keep pose in sync
        this.setInSittingPose(this.isOrderedToSit());
    }

    @Override
    public boolean shouldRiderSit() {
        return true;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.CRYO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.CRYO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (this.isTame()) {
            int var3 = this.getRandom().nextInt(5);
            var3 += 2;
            for (int var4 = 0; var4 < var3; ++var4) {
                this.spawnAtLocation(Items.POPPY);
            }
        } else {
            super.dropCustomDeathLoot(source, looting, recentlyHit);
        }
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby()
                ? (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.5f
                : (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.0f;
    }

    @Override
    public double getPassengersRidingOffset() {
        return 0.85;
    }

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction moveFunction) {
        if (this.hasPassenger(passenger)) {
            float f = -0.15f;
            double x = this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot()));
            double y = this.getY() + this.getPassengersRidingOffset();
            double z = this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot()));
            moveFunction.accept(passenger, x, y, z);
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        super.checkFallDamage(y, onGroundIn, state, pos);
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setPersistenceRequired();
            return false;
        }
        if (!this.getPassengers().isEmpty()) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    @Override
    public Ostrich getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Ostrich) this.getType().create(level);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.APPLE);
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        Item crystal =
                ForgeRegistries.ITEMS.getValue(new ResourceLocation("chaospersists", "crystalapple"));
        if (crystal == null) {
            crystal = ChaosPersists.MyCrystalApple;
        }
        return crystal != null && par1ItemStack.is(crystal);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return this.isBreedingItem(stack);
    }

    public static boolean checkOstrichSpawnRules(
            EntityType<Ostrich> type,
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
        if (random.nextInt(4) != 1) {
            return false;
        }
        List<Ostrich> found =
                level.getLevel()
                        .getEntitiesOfClass(
                                Ostrich.class,
                                new AABB(pos).inflate(16.0, 6.0, 16.0));
        return found.isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (!this.level().isDay()) {
            return false;
        }
        if (this.getRandom().nextInt(4) != 1) {
            return false;
        }
        List<Ostrich> target =
                this.level()
                        .getEntitiesOfClass(
                                Ostrich.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        long others = target.stream().filter(e -> e != this).count();
        return others == 0;
    }
}
