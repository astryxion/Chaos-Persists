package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntityButterfly extends AmbientCreature {
    private static final EntityDataAccessor<Integer> BUTTERFLY_TYPE =
            SynchedEntityData.defineId(EntityButterfly.class, EntityDataSerializers.INT);
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/butterfly.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/butterfly2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/butterfly3.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/butterfly4.png");
    private static final ResourceLocation TEXTURE9 =
            new ResourceLocation("chaospersists", "textures/entity/vbutterfly1.png");
    private static final ResourceLocation TEXTURE_EYEMOTH =
            new ResourceLocation("chaospersists", "textures/entity/eyemoth.png");
    private static final ResourceLocation TEXTURE_LUNAMOTH =
            new ResourceLocation("chaospersists", "textures/entity/lunamoth.png");
    private static final ResourceLocation TEXTURE_DARKMOTH =
            new ResourceLocation("chaospersists", "textures/entity/darkmoth.png");
    private static final ResourceLocation TEXTURE_FIREMOTH =
            new ResourceLocation("chaospersists", "textures/entity/firemoth.png");
    public int butterfly_type = ChaosPersists.ChaosRand.nextInt(4);
    private final GenericTargetSorter targetSorter;
    private int force_sync = 25;
    private BlockPos currentFlightTarget = null;

    public EntityButterfly(EntityType<? extends EntityButterfly> type, Level par1World) {
        super(type, par1World);
        this.targetSorter = new GenericTargetSorter(this);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BUTTERFLY_TYPE, this.butterfly_type);
    }

    public ResourceLocation getTexture(EntityButterfly a) {
        if (a instanceof Mothra) {
            return TEXTURE_EYEMOTH;
        }
        if (a instanceof EntityLunaMoth lunaMoth) {
            if (lunaMoth.moth_type == 1) {
                return TEXTURE_EYEMOTH;
            }
            if (lunaMoth.moth_type == 2) {
                return TEXTURE_DARKMOTH;
            }
            if (lunaMoth.moth_type == 3) {
                return TEXTURE_FIREMOTH;
            }
            return TEXTURE_LUNAMOTH;
        }
        if (this.getButterflyType() == 1) {
            if (this.level().dimension() == ChaosPersists.getDimensionKey(4)) {
                return TEXTURE9;
            }
            return TEXTURE2;
        }
        if (this.getButterflyType() == 2) {
            return TEXTURE3;
        }
        if (this.getButterflyType() == 3) {
            return TEXTURE4;
        }
        return TEXTURE1;
    }

    public int getButterflyType() {
        return this.entityData.get(BUTTERFLY_TYPE);
    }

    public void setButterflyType(int type) {
        this.butterfly_type = type;
        this.entityData.set(BUTTERFLY_TYPE, type);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6000000238418579, 1.0));
        --this.force_sync;
        if (this.force_sync < 0) {
            this.force_sync = 25;
            if (this.level().isClientSide) {
                this.butterfly_type = this.getButterflyType();
            } else {
                this.entityData.set(BUTTERFLY_TYPE, this.butterfly_type);
            }
        }
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
        int keep_trying = 25;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.getRandom().nextInt(100) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ()) < 4.0) {
            BlockState bid = Blocks.STONE.defaultBlockState();
            while (bid.getBlock() != Blocks.AIR && keep_trying != 0) {
                this.currentFlightTarget = new BlockPos(
                        (int) this.getX() + this.getRandom().nextInt(7) - this.getRandom().nextInt(7),
                        (int) this.getY() + this.getRandom().nextInt(6) - 2,
                        (int) this.getZ() + this.getRandom().nextInt(7) - this.getRandom().nextInt(7));
                bid = this.level().getBlockState(this.currentFlightTarget);
                --keep_trying;
            }
        } else if (this.getRandom().nextInt(10) == 0
                && this.level().dimension() == ChaosPersists.getDimensionKey(4)
                && this.getButterflyType() == 1
                && this.level().getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.currentFlightTarget = new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                if (this.distanceToSqr(e) < 6.0) {
                    this.doHurtTarget(e);
                }
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add((Math.signum(var1) * 0.5 - motion.x) * 0.10000000149011612, (Math.signum(var3) * 0.699999988079071 - motion.y) * 0.10000000149011612, (Math.signum(var5) * 0.5 - motion.z) * 0.10000000149011612));
        motion = this.getDeltaMovement();
        float var7 = (float) (Mth.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        float var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8);
        MyUtils.applyChaosFlightMovement(this);
}

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (ChaosPersists.ChaosRand.nextInt(2) != 0) {
            return false;
        }
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return par1Entity.hurt(this.damageSources().mobAttack(this), 1.0f);
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1EntityLiving instanceof AbstractHorse) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        List<LivingEntity> var5 =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 5.0, 8.0));
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

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player != null && player.getItemInHand(hand).isEmpty()) {
            if (this.interactButterfly(player)) {
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    public boolean interactButterfly(Player par1EntityPlayer) {
        if (par1EntityPlayer == null) {
            return false;
        }
        if (!(par1EntityPlayer instanceof ServerPlayer serverPlayer)) {
            return false;
        }
        ItemStack var2 = par1EntityPlayer.getMainHandItem();
        if (!var2.isEmpty()) {
            return false;
        }
        if (serverPlayer.server == null) {
            return false;
        }
        net.minecraft.resources.ResourceKey<Level> targetDim =
                par1EntityPlayer.level().dimension() == ChaosPersists.getDimensionKey(6)
                        ? Level.OVERWORLD
                        : ChaosPersists.getDimensionKey(6);
        ServerLevel world = serverPlayer.server.getLevel(targetDim);
        if (world == null) {
            return false;
        }
        serverPlayer.changeDimension(world, new UtopiaTeleporter(serverPlayer.getX(), serverPlayer.getZ()));
        return true;
    }

    public static boolean checkButterflySpawnRules(
            EntityType<EntityButterfly> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (MyUtils.getBlockStateForSpawnRules(level, pos).getBlock() != Blocks.AIR) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (level.getLevel().dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (pos.getY() < 50) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos origin = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        if (!(level instanceof net.minecraft.server.level.WorldGenRegion)) {
            for (int k = -3; k < 3; ++k) {
                for (int j = -3; j < 3; ++j) {
                    for (int i = 0; i < 5; ++i) {
                        checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                        if (!MyUtils.canAccessBlockDuringWorldGen(level, checkPos)) {
                            continue;
                        }
                        BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                        if (state.getBlock() != Blocks.SPAWNER) {
                            continue;
                        }
                        if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                            continue;
                        }
                        ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                        if (id != null && "Butterfly".equals(id.getPath())) {
                            this.setButterflyType(1);
                            return true;
                        }
                    }
                }
            }
        }
        if (MyUtils.canAccessBlockDuringWorldGen(level, origin)
                && MyUtils.getBlockStateForSpawnRules(level, origin).getBlock() != Blocks.AIR) {
            return false;
        }
        if (!isDaytime(level)) {
            return false;
        }
        if (level instanceof Level world && world.dimension() == ChaosPersists.getDimensionKey(4)) {
            return true;
        }
        if (origin.getY() < 50) {
            return false;
        }
        return true;
    }

    private static boolean isDaytime(LevelAccessor level) {
        if (level instanceof Level world) {
            return world.isDay();
        }
        return false;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        par1NBTTagCompound.putInt("ButterflyType", this.butterfly_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.butterfly_type = par1NBTTagCompound.getInt("ButterflyType");
        this.entityData.set(BUTTERFLY_TYPE, this.butterfly_type);
    }
}
