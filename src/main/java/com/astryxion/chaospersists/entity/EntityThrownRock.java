package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityThrownRock extends ThrowableProjectile {
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> ROCK_TYPE_DW =
            net.minecraft.network.syncher.SynchedEntityData.defineId(
                    EntityThrownRock.class, net.minecraft.network.syncher.EntityDataSerializers.INT);
    private int rock_type = 0;
    private int myage = 0;
    private float my_rotation = 0.0f;

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, Level level) {
        super(type, level);
    }

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, Level level, int par2) {
        super(type, level);
    }

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, LivingEntity shooter, Level level) {
        super(type, shooter, level);
    }

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, LivingEntity shooter, Level level, int par3) {
        super(type, shooter, level);
        this.setRockType(par3);
    }

    public EntityThrownRock(EntityType<? extends EntityThrownRock> type, double x, double y, double z, Level level) {
        super(type, x, y, z, level);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(ROCK_TYPE_DW, 0);
    }

    public int getRockType() {
        return this.entityData.get(ROCK_TYPE_DW);
    }

    public void setRockType(int par1) {
        if (this.level() == null) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }
        this.rock_type = par1;
        this.entityData.set(ROCK_TYPE_DW, par1);
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.isRemoved()) {
            return;
        }
        if (this.level().isClientSide) {
            return;
        }
        int rockType = this.resolveRockType();
        Entity owner = this.getOwner();
        if (result.getType() == HitResult.Type.ENTITY && owner != null) {
            Entity e = ((EntityHitResult) result).getEntity();
            if (rockType == 1 && e != owner) {
                applyPlayerRockHit(e, owner, 2.0f, 0.1, 0.025);
            }
            if (rockType == 2 && e != owner) {
                applyPlayerRockHit(e, owner, 5.0f, 0.2, 0.025);
            }
            if (rockType == 3 && e != owner) {
                applyPlayerRockHit(e, owner, 5.0f, 0.2, 0.025);
                e.setSecondsOnFire(1);
            }
            if (rockType == 4 && e != owner) {
                applyPlayerRockHit(e, owner, 5.0f, 0.2, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
                }
            }
            if (rockType == 5 && e != owner) {
                applyPlayerRockHit(e, owner, 10.0f, 0.1, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                }
            }
            if (rockType == 6 && e != owner) {
                applyPlayerRockHit(e, owner, 20.0f, 0.2, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
            }
            if (rockType == 7 && e != owner) {
                applyPlayerRockHit(e, owner, 40.0f, 0.2, 0.025);
            }
            if (rockType == 8 && e != owner) {
                applyPlayerRockHit(e, owner, 40.0f, 0.5, 0.055);
                this.detonateAt(e.getX(), e.getY() + 0.25, e.getZ(), 2.1f);
            }
            if (rockType == 9 && e != owner) {
                applyPlayerRockHit(e, owner, 150.0f, 0.2, 0.025);
                e.setSecondsOnFire(3);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
            }
            if (rockType == 10 && e != owner) {
                applyPlayerRockHit(e, owner, 150.0f, 0.2, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
            }
            if (rockType == 11 && e != owner) {
                applyPlayerRockHit(e, owner, 150.0f, 0.2, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
            }
            if (rockType == 12 && e != owner) {
                applyPlayerRockHit(e, owner, 250.0f, 0.2, 0.025);
                if (e instanceof LivingEntity living) {
                    living.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                }
                this.detonateAt(e.getX(), e.getY() + 0.25, e.getZ(), 5.1f);
            }
        } else if (rockType != 0) {
            this.handleBlockImpact(result, rockType);
        }
        this.discard();
    }

    private int resolveRockType() {
        int synced = this.getRockType();
        return synced != 0 ? synced : this.rock_type;
    }

    private ExplosionInteraction explosionInteraction() {
        return com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)
                ? ExplosionInteraction.MOB
                : ExplosionInteraction.NONE;
    }

    private void detonateAt(double x, double y, double z, float power) {
        this.level().explode(null, x, y, z, power, true, this.explosionInteraction());
    }

    private void handleBlockImpact(HitResult result, int rockType) {
        if (rockType == 8) {
            Vec3 impact = result.getLocation();
            this.detonateAt(impact.x, impact.y, impact.z, 2.1f);
            return;
        }
        if (rockType == 12) {
            Vec3 impact = result.getLocation();
            this.detonateAt(impact.x, impact.y, impact.z, 5.1f);
            return;
        }
        if (result.getType() != HitResult.Type.BLOCK) {
            return;
        }
        int played = 0;
        BlockPos hitPos = ((BlockHitResult) result).getBlockPos();
        int x = hitPos.getX();
        int y = hitPos.getY();
        int z = hitPos.getZ();
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                for (int k = -1; k <= 1; ++k) {
                    BlockState bid = this.level().getBlockState(new BlockPos(x + i, y + j, z + k));
                    if (bid.getBlock() != Blocks.GLASS && bid.getBlock() != Blocks.GLASS_PANE) {
                        continue;
                    }
                    this.level().setBlock(new BlockPos(x + i, y + j, z + k), Blocks.AIR.defaultBlockState(), 3);
                    if (played == 0 && ChaosSounds.GLASSDEAD != null) {
                        this.level()
                                .playSound(
                                        null,
                                        x,
                                        y,
                                        z,
                                        ChaosSounds.GLASSDEAD,
                                        SoundSource.BLOCKS,
                                        1.0f,
                                        1.0f);
                        ++played;
                    }
                }
            }
        }
        Item drop = this.rockDropItem(rockType);
        if (drop != null) {
            this.spawnAtLocation(new ItemStack(drop, 1));
        }
    }

    private Item rockDropItem(int rockType) {
        return switch (rockType) {
            case 1 -> ChaosPersists.MySmallRock;
            case 2 -> ChaosPersists.MyRock;
            case 3 -> ChaosPersists.MyRedRock;
            case 4 -> ChaosPersists.MyGreenRock;
            case 5 -> ChaosPersists.MyBlueRock;
            case 6 -> ChaosPersists.MyPurpleRock;
            case 7 -> ChaosPersists.MySpikeyRock;
            case 8 -> ChaosPersists.MyTNTRock;
            case 9 -> ChaosPersists.MyCrystalRedRock;
            case 10 -> ChaosPersists.MyCrystalGreenRock;
            case 11 -> ChaosPersists.MyCrystalBlueRock;
            case 12 -> ChaosPersists.MyCrystalTNTRock;
            default -> null;
        };
    }

    private void applyPlayerRockHit(Entity e, Entity owner, float damage, double ks, double inair) {
        if (!(owner instanceof Player player)) {
            return;
        }
        e.hurt(this.damageSources().playerAttack(player), damage);
        float f3 = (float) Math.atan2(e.getZ() - owner.getZ(), e.getX() - owner.getX());
        if (!e.isAlive()) {
            inair *= 2.0;
        }
        e.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
    }

    @Override
    public void tick() {
        int x = (int) this.getX();
        int y = (int) this.getY();
        int z = (int) this.getZ();
        super.tick();
        this.my_rotation += 30.0f;
        this.my_rotation %= 360.0f;
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
        ++this.myage;
        if (this.myage > 1000) {
            this.discard();
        }
        if (this.level().isClientSide) {
            this.rock_type = this.getRockType();
        } else {
            this.setRockType(this.rock_type);
        }
        BlockState bid = this.level().getBlockState(new BlockPos(x, y, z));
        if (bid.getBlock() == Blocks.WATER) {
            Vec3 motion = this.getDeltaMovement();
            if (motion.y < -0.15000000596046448
                    && motion.y > -0.550000011920929
                    && (float) (motion.x * motion.x + motion.z * motion.z) > 0.5f) {
                this.setDeltaMovement(motion.x * 3.0 / 4.0, -motion.y * 3.0 / 4.0, motion.z * 3.0 / 4.0);
            }
        }
    }
}
