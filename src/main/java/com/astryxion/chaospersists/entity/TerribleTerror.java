package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

public class TerribleTerror extends MonsterEntity {
    private BlockPos currentFlightTarget = null;
    private GenericTargetSorter TargetSorter = null;

    public TerribleTerror(EntityType<? extends TerribleTerror> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=1.0f, height=0.75f
        this.xpReward = 10;
        this.TargetSorter = new GenericTargetSorter(this);
    }
    @Override
    public boolean fireImmune() {
        return true;
    }


    public static AttributeModifierMap createAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, ChaosPersists.TerribleTerror_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, ChaosPersists.TerribleTerror_stats.attack)
                .build();
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (!this.level.isDay()) {
            return false;
        }
        return true;
    }

    @Override
    protected float getSoundVolume() {
        return 0.45f;
    }

    @Override
    protected float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return ChaosSounds.TERRIBLETERROR_LIVING;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.TERRIBLETERROR_HIT;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return ChaosSounds.TERRIBLETERROR_DEAD;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.TerribleTerror_stats.health;
    }

    public int getArmorValue() {
        return ChaosPersists.TerribleTerror_stats.defense;
    }

    @Override
    public void tick() {
        super.tick();
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * 0.6, this.getDeltaMovement().z);
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        return par1Entity.hurt(DamageSource.mobAttack(this), 5.0f);
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d(this.getX(), this.getY() + 0.75, this.getZ()), new Vector3d(pX, pY, pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @Override
    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        if (this.removed) {
            return;
        }
        super.customServerAiStep();
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ());
        }
        if (this.random.nextInt(100) == 0 || this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), true) < 2.1) {
            Block bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                zdir = this.random.nextInt(5) + 5;
                xdir = this.random.nextInt(5) + 5;
                if (this.random.nextInt(2) == 0) {
                    zdir = -zdir;
                }
                if (this.random.nextInt(2) == 0) {
                    xdir = -xdir;
                }
                this.currentFlightTarget = new BlockPos((int) this.getX() + xdir, (int) this.getY() + this.random.nextInt(5) - 2, (int) this.getZ() + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double) this.currentFlightTarget.getX(), (double) this.currentFlightTarget.getY(), (double) this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        } else if (this.random.nextInt(9) == 0) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.currentFlightTarget = new BlockPos((int) e.getX(), (int) (e.getY() + 1.0), (int) e.getZ());
                if (this.distanceToSqr(e) < 6.0) {
                    this.doHurtTarget(e);
                }
            }
        }
        double var1 = (double) this.currentFlightTarget.getX() + 0.4 - this.getX();
        double var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        double var5 = (double) this.currentFlightTarget.getZ() + 0.4 - this.getZ();
        double mx = this.getDeltaMovement().x + (Math.signum(var1) * 0.4 - this.getDeltaMovement().x) * 0.30000000149011613;
        double my = this.getDeltaMovement().y + (Math.signum(var3) * 0.699999988079071 - this.getDeltaMovement().y) * 0.20000000149011612;
        double mz = this.getDeltaMovement().z + (Math.signum(var5) * 0.4 - this.getDeltaMovement().z) * 0.30000000149011613;
        this.setDeltaMovement(mx, my, mz);
        float var7 = (float) (Math.atan2(mz, mx) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees(var7 - this.yRot);
        this.xxa = 0.75f;
        this.yRot += var8 / 4.0f;
    }

    @Override
    protected boolean isMovementNoisy() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) { return false; }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
        fallDistance = 0.0f;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = super.hurt(par1DamageSource, par2);
        Entity e = par1DamageSource.getEntity();
        if (e != null && this.currentFlightTarget != null) {
            this.currentFlightTarget = new BlockPos((int) e.getX(), (int) e.getY(), (int) e.getZ());
        }
        return ret;
    }

    public boolean checkSpawnRules(IWorldReader level, SpawnReason reason) {
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = level.getBlockState(new BlockPos((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k)).getBlock();
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
                    if (s == null || !s.equals("Terrible Terror")) {
                        continue;
                    }
                    return true;
                }
            }
        }
        if (!MonsterEntity.isDarkEnoughToSpawn((net.minecraft.world.IServerWorld)this.level, this.blockPosition(), this.random)) {
            return false;
        }
        if (level instanceof net.minecraft.world.World && ((net.minecraft.world.World)level).isDay()) {
            return false;
        }
        if (ChaosPersists.getDimensionId(this.level) != ChaosPersists.getDimension(6) && this.getY() > 40.0) {
            return false;
        }
        return true;
    }

    private boolean isSuitableTarget(LivingEntity par1LivingEntity, boolean par2) {
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
        if (par1LivingEntity instanceof RockBase) {
            return false;
        }
        if (par1LivingEntity instanceof TerribleTerror) {
            return false;
        }
        if (par1LivingEntity instanceof EnderReaper) {
            return false;
        }
        if (par1LivingEntity instanceof Mothra) {
            return false;
        }
        if (par1LivingEntity instanceof LurkingTerror) {
            return false;
        }
        if (par1LivingEntity instanceof CloudShark) {
            return false;
        }
        if (par1LivingEntity instanceof Rotator) {
            return false;
        }
        if (par1LivingEntity instanceof Bee) {
            return false;
        }
        if (par1LivingEntity instanceof Mantis) {
            return false;
        }
        if (par1LivingEntity instanceof LeafMonster) {
            return false;
        }
        if (par1LivingEntity instanceof CreepingHorror) {
            return false;
        }
        if (par1LivingEntity instanceof Triffid) {
            return false;
        }
        if (par1LivingEntity instanceof PitchBlack) {
            return false;
        }
        if (par1LivingEntity instanceof Dragon) {
            return false;
        }
        if (par1LivingEntity instanceof Island) {
            return false;
        }
        if (par1LivingEntity instanceof IslandToo) {
            return false;
        }
        if (par1LivingEntity instanceof EntityButterfly) {
            return false;
        }
        if (par1LivingEntity instanceof Firefly) {
            return false;
        }
        if (par1LivingEntity instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity) par1LivingEntity;
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
        List<LivingEntity> var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 8.0, 12.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity var4;
        while (var2.hasNext()) {
            var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    protected Item getDropItem() {
        int i = this.level.random.nextInt(3);
        if (i == 0) {
            return Items.ROTTEN_FLESH;
        }
        if (i == 1) {
            return Items.EMERALD;
        }
        return Items.FEATHER;
    }
}
