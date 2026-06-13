package com.astryxion.chaospersists.entity;
import net.minecraft.util.math.vector.Vector3d;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityThrownRock extends ThrowableEntity {
    private static final DataParameter<Integer> ROCK_TYPE_DW = EntityDataManager.defineId(EntityThrownRock.class, DataSerializers.INT);
    private int rock_type = 0;
    private int myage = 0;
    private float my_rotation = 0.0f;

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, World par1World) {
        super(type, par1World);
    }

    public EntityThrownRock(World par1World, int par2) {
        this(typeFor(par1World), par1World);
    }

    public EntityThrownRock(World par1World, LivingEntity par2Mob) {
        this(typeFor(par1World), par1World);
        this.setOwner(par2Mob);
    }

    public EntityThrownRock(World par1World, LivingEntity par2Mob, int par3) {
        this(par1World, par2Mob);
        this.rock_type = par3;
    }

    public EntityThrownRock(World par1World, double par2, double par4, double par6) {
        this(typeFor(par1World), par2, par4, par6, par1World);
    }

    protected EntityThrownRock(EntityType<? extends EntityThrownRock> type, double par2, double par4, double par6, World par1World) {
        super(type, par2, par4, par6, par1World);
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends EntityThrownRock> typeFor(World level) {
        return (EntityType<? extends EntityThrownRock>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "thrown_rock"));
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ROCK_TYPE_DW, 0);
    }

    public int getRockType() {
        return this.entityData.get(ROCK_TYPE_DW);
    }

    public void setRockType(int par1) {
        if (this.level == null) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        this.rock_type = par1;
        this.entityData.set(ROCK_TYPE_DW, par1);
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        if (this.removed) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        if (par1MovingObjectPosition.getType() == RayTraceResult.Type.ENTITY && this.getOwner() != null) {
            double ks;
            double inair;
            float f3;
            Entity e = ((EntityRayTraceResult) par1MovingObjectPosition).getEntity();
            Entity ownerEntity = this.getOwner();
            LivingEntity thrower = ownerEntity instanceof LivingEntity ? (LivingEntity) ownerEntity : null;
            if (thrower == null) {
                return;
            }
            if (this.rock_type == 1 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 2.0f);
                ks = 0.1;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            if (this.rock_type == 2 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 5.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            if (this.rock_type == 3 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 5.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                e.setSecondsOnFire(20);
            }
            if (this.rock_type == 4 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 5.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.POISON, 100, 0));
                }
            }
            if (this.rock_type == 5 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 10.0f);
                ks = 0.1;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 100, 0));
                }
            }
            if (this.rock_type == 6 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 20.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
                }
            }
            if (this.rock_type == 7 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 40.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
            }
            if (this.rock_type == 8 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 40.0f);
                ks = 0.5;
                inair = 0.055;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                this.level.explode(null, e.getX(), e.getY() + 0.25, e.getZ(), 2.1f, this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            }
            if (this.rock_type == 9 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 150.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                e.setSecondsOnFire(50);
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
                }
            }
            if (this.rock_type == 10 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 150.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.POISON, 200, 0));
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
                }
            }
            if (this.rock_type == 11 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 150.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 0));
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
                }
            }
            if (this.rock_type == 12 && e != thrower) {
                e.hurt(DamageSource.playerAttack((net.minecraft.entity.player.PlayerEntity) thrower), 250.0f);
                ks = 0.2;
                inair = 0.025;
                f3 = (float) Math.atan2(e.getZ() - thrower.getZ(), e.getX() - thrower.getX());
                if (!e.isAlive()) {
                    inair *= 2.0;
                }
                e.setDeltaMovement(e.getDeltaMovement().add(Math.cos(f3) * ks, inair, Math.sin(f3) * ks));
                if (e instanceof LivingEntity) {
                    ((LivingEntity) e).addEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
                }
                this.level.explode(null, e.getX(), e.getY() + 0.25, e.getZ(), 5.1f, this.level.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE);
            }
        } else if (this.rock_type != 0 && par1MovingObjectPosition.getType() == RayTraceResult.Type.BLOCK) {
            int played = 0;
            BlockPos hitPos = ((BlockRayTraceResult) par1MovingObjectPosition).getBlockPos();
            int x = hitPos.getX();
            int y = hitPos.getY();
            int z = hitPos.getZ();
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    for (int k = -1; k <= 1; ++k) {
                        Block bid = this.level.getBlockState(new BlockPos(x + i, y + j, z + k)).getBlock();
                        if (bid != Blocks.GLASS && bid != Blocks.GLASS_PANE) {
                            continue;
                        }
                        if (!this.level.isClientSide) {
                            this.level.setBlock(new BlockPos(x + i, y + j, z + k), Blocks.AIR.defaultBlockState(), 3);
                        }
                        if (played != 0) {
                            continue;
                        }
                        SoundEvent glassDead = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("chaospersists", "glassdead"));
                        if (glassDead != null) {
                            this.level.playSound(null, x, y, z, glassDead, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        }
                        ++played;
                    }
                }
            }
            if (!this.level.isClientSide) {
                if (this.rock_type == 1) {
                    this.spawnAtLocation(ChaosPersists.MySmallRock, 1);
                }
                if (this.rock_type == 2) {
                    this.spawnAtLocation(ChaosPersists.MyRock, 1);
                }
                if (this.rock_type == 3) {
                    this.spawnAtLocation(ChaosPersists.MyRedRock, 1);
                }
                if (this.rock_type == 4) {
                    this.spawnAtLocation(ChaosPersists.MyGreenRock, 1);
                }
                if (this.rock_type == 5) {
                    this.spawnAtLocation(ChaosPersists.MyBlueRock, 1);
                }
                if (this.rock_type == 6) {
                    this.spawnAtLocation(ChaosPersists.MyPurpleRock, 1);
                }
                if (this.rock_type == 7) {
                    this.spawnAtLocation(ChaosPersists.MySpikeyRock, 1);
                }
                if (this.rock_type == 8) {
                    this.spawnAtLocation(ChaosPersists.MyTNTRock, 1);
                }
                if (this.rock_type == 9) {
                    this.spawnAtLocation(ChaosPersists.MyCrystalRedRock, 1);
                }
                if (this.rock_type == 10) {
                    this.spawnAtLocation(ChaosPersists.MyCrystalGreenRock, 1);
                }
                if (this.rock_type == 11) {
                    this.spawnAtLocation(ChaosPersists.MyCrystalBlueRock, 1);
                }
                if (this.rock_type == 12) {
                    this.spawnAtLocation(ChaosPersists.MyCrystalTNTRock, 1);
                }
            }
        }
        this.remove();
    }

    @Override
    public void tick() {
        int x = (int) this.getX();
        int y = (int) this.getY();
        int z = (int) this.getZ();
        super.tick();
        this.my_rotation += 30.0f;
        this.my_rotation %= 360.0f;
        this.xRot = this.xRotO = this.my_rotation;
        ++this.myage;
        if (this.myage > 1000) {
            this.remove();
        }
        if (this.level.isClientSide) {
            this.rock_type = this.getRockType();
        } else {
            this.setRockType(this.rock_type);
        }
        Block bid = this.level.getBlockState(new BlockPos(x, y, z)).getBlock();
        Vector3d motion = this.getDeltaMovement();
        if (bid == Blocks.WATER && motion.y < -0.15000000596046448 && motion.y > -0.550000011920929 && (float) (motion.x * motion.x + motion.z * motion.z) > 0.5f) {
            this.setDeltaMovement(-motion.x * 0.75, -motion.y * 0.75, -motion.z * 0.75);
        }
    }
}
