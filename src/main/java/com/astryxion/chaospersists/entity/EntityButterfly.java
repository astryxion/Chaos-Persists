package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosTeleporter;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.util.Hand;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.block.BlockState;

import net.minecraftforge.common.util.ITeleporter;

public class EntityButterfly extends FlyingEntity {
    private static final DataParameter<Integer> BUTTERFLY_TYPE = EntityDataManager.defineId(EntityButterfly.class, DataSerializers.INT);
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/butterfly.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/butterfly2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/butterfly3.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/butterfly4.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/eyemoth.png");
    private static final ResourceLocation texture6 = new ResourceLocation("chaospersists", "textures/entity/lunamoth.png");
    private static final ResourceLocation texture7 = new ResourceLocation("chaospersists", "textures/entity/darkmoth.png");
    private static final ResourceLocation texture8 = new ResourceLocation("chaospersists", "textures/entity/firemoth.png");
    private static final ResourceLocation texture9 = new ResourceLocation("chaospersists", "textures/entity/vbutterfly1.png");
    public int butterfly_type = ChaosPersists.ChaosRand.nextInt(4);
    private int attack_delay = 0;
    private GenericTargetSorter TargetSorter = null;
    private int force_sync = 25;
    private BlockPos currentFlightTarget = null;

    public EntityButterfly(EntityType<? extends EntityButterfly> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.4f, height=0.4f
        this.TargetSorter = new GenericTargetSorter(this);
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    public ResourceLocation getTexture(EntityButterfly a) {
        if (a instanceof Mothra) {
            return texture5;
        }
        if (a instanceof EntityLunaMoth) {
            if (((EntityLunaMoth) a).moth_type == 1) {
                return texture5;
            }
            if (((EntityLunaMoth) a).moth_type == 2) {
                return texture7;
            }
            if (((EntityLunaMoth) a).moth_type == 3) {
                return texture8;
            }
            return texture6;
        }
        if (this.butterfly_type == 1) {
            ServerWorld dim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(4));
            if (dim != null && this.level == dim) {
                return texture9;
            }
            return texture2;
        }
        if (this.butterfly_type == 2) {
            return texture3;
        }
        if (this.butterfly_type == 3) {
            return texture4;
        }
        return texture1;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BUTTERFLY_TYPE, this.butterfly_type);
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
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
    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    public int mygetMaxHealth() {
        return 2;
    }

    @Override
    protected void customServerAiStep() {
        int keep_trying = 25;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(100) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 4.0f) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                this.currentFlightTarget = new BlockPos((int) this.getX() + this.random.nextInt(7) - this.random.nextInt(7), (int) this.getY() + this.random.nextInt(6) - 2, (int) this.getZ() + this.random.nextInt(7) - this.random.nextInt(7));
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                --keep_trying;
            }
        } else if (this.random.nextInt(10) == 0) {
            ServerWorld dim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(4));
            if (dim != null && this.level == dim && this.butterfly_type == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
                LivingEntity e = this.findSomethingToAttack();
                if (e != null) {
                    this.currentFlightTarget = new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                    if (this.distanceToSqr(e) < 6.0) {
                        this.doHurtTarget(e);
                    }
                }
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.5 - this.getDeltaMovement().x) * 0.10000000149011612;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.10000000149011612;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.5 - this.getDeltaMovement().z) * 0.10000000149011612;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.zza = 0.5f;
        this.yRot += var8;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (ChaosPersists.ChaosRand.nextInt(2) != 0) {
            return false;
        }
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return par1Entity.hurt(DamageSource.mobAttack(this), 1.0f);
    }

    private boolean isSuitableTarget(LivingEntity par1LivingEntity, boolean par2) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1LivingEntity == null) {
            return false;
        }
        if (par1LivingEntity == this) {
            return false;
        }
        if (!par1LivingEntity.isAlive()) {
            return false;
        }
        if (!this.getSensing().canSee(par1LivingEntity)) {
            return false;
        }
        if (par1LivingEntity instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) par1LivingEntity;
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        if (par1LivingEntity instanceof net.minecraft.entity.passive.horse.HorseEntity) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        List<LivingEntity> var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8.0, 5.0, 8.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6000000238418579, this.getDeltaMovement().z);
        --this.force_sync;
        if (this.force_sync < 0) {
            this.force_sync = 25;
            if (this.level.isClientSide) {
                this.butterfly_type = this.entityData.get(BUTTERFLY_TYPE);
            } else {
                this.entityData.set(BUTTERFLY_TYPE, this.butterfly_type);
            }
        }
    }

    @Override
    protected boolean isMovementNoisy() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean canChangeDimensions() {
        return true;
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (player != null && (player.getItemInHand(hand) == null || player.getItemInHand(hand).isEmpty())) {
            return this.interact(player) ? net.minecraft.util.ActionResultType.SUCCESS : net.minecraft.util.ActionResultType.PASS;
        }
        return super.mobInteract(player, hand);
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        if (par1PlayerEntityEntity == null) {
            return false;
        }
        if (!(par1PlayerEntityEntity instanceof ServerPlayerEntity)) {
            return false;
        }
        ItemStack var2 = par1PlayerEntityEntity.getMainHandItem();
        if (var2 != null && var2.getCount() <= 0) {
            par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()) {
            return false;
        }
        MinecraftServer server = this.level.getServer();
        if (server == null) {
            return true;
        }
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) par1PlayerEntityEntity;
        ServerWorld targetWorld = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(6));
        ServerWorld overworld = server.getLevel(World.OVERWORLD);
        if (targetWorld == null || overworld == null) {
            return false;
        }
        if (serverPlayerEntity.getLevel() != targetWorld) {
            serverPlayerEntity.changeDimension(targetWorld, (ITeleporter) new ChaosTeleporter(targetWorld, ChaosPersists.getDimension(6), this.level));
        } else {
            serverPlayerEntity.changeDimension(overworld, (ITeleporter) new ChaosTeleporter(overworld, 0, this.level));
        }
        return true;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        Block bid;
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    bid = level.getBlockState(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)) instanceof MobSpawnerTileEntity)) {
                        continue;
                    }
                    MobSpawnerTileEntity tileentitymobspawner = (MobSpawnerTileEntity) level.getBlockEntity(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k));
                    String s = null;
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityId(tileentitymobspawner.getSpawner());
                    if (id != null) {
                        s = id.getPath();
                    }
                    if (s == null || !s.equals("Butterfly")) {
                        continue;
                    }
                    this.butterfly_type = 1;
                    return true;
                }
            }
        }
        bid = level.getBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())).getBlock();
        if (bid != Blocks.AIR) {
            return false;
        }
        if (level instanceof World && ((World)level).isDay()) {
            return false;
        }
        if (level instanceof ServerWorld) {
            ServerWorld dim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(4));
            if (dim != null && level == dim) {
                return true;
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("ButterflyType", this.butterfly_type);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.butterfly_type = par1CompoundNBT.getInt("ButterflyType");
    }
}
