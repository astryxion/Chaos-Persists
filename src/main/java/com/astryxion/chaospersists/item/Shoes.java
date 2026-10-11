package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.util.PetCombatHelper;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Shoes extends ThrowableProjectile {
    private static final EntityDataAccessor<Integer> SHOE_ID =
            SynchedEntityData.defineId(Shoes.class, EntityDataSerializers.INT);
    public int ShoeId = 0;
    private float my_rotation = 0.0f;

    public Shoes(EntityType<? extends Shoes> type, Level level) {
        super(type, level);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(Level level) {
        this(ChaosPersists.ENTITY_TYPE_SHOES.get(), level);
    }

    public Shoes(Level level, int par2) {
        super(ChaosPersists.ENTITY_TYPE_SHOES.get(), level);
        this.ShoeId = par2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(Level level, LivingEntity par2EntityLiving) {
        super(ChaosPersists.ENTITY_TYPE_SHOES.get(), par2EntityLiving, level);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(Level level, LivingEntity par2EntityLiving, int par3) {
        super(ChaosPersists.ENTITY_TYPE_SHOES.get(), par2EntityLiving, level);
        this.ShoeId = par3;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(Level level, double par2, double par4, double par6) {
        super(ChaosPersists.ENTITY_TYPE_SHOES.get(), level);
        this.setPos(par2, par4, par6);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(SHOE_ID, 0);
    }

    public int getShoeId() {
        return this.entityData.get(SHOE_ID);
    }

    /**
     * Same trajectory as {@link Boyfriend#performRangedAttack} /
     * {@link Girlfriend#performRangedAttack} (1.7.10 behavior).
     */
    public static void shootTowardTarget(LivingEntity thrower, LivingEntity target, int shoeId) {
        Level world = thrower.level();
        if (world.isClientSide || target == null || target == thrower) {
            return;
        }
        Shoes shoes = new Shoes(world, thrower, shoeId);
        double dx = target.getX() - thrower.getX();
        double dy = target.getY() + (double) target.getEyeHeight() - 1.1 - shoes.getY();
        double dz = target.getZ() - thrower.getZ();
        float lift = Mth.sqrt((float) (dx * dx + dz * dz)) * 0.2f;
        shoes.shoot(dx, dy + (double) lift, dz, 1.8f, 4.0f);
        world.addFreshEntity(shoes);
        world.playSound(
                null,
                thrower.getX(),
                thrower.getY(),
                thrower.getZ(),
                SoundEvents.SKELETON_SHOOT,
                SoundSource.NEUTRAL,
                0.75f,
                1.0f / (world.getRandom().nextFloat() * 0.4f + 0.8f));
    }

    @Override
    protected void onHitEntity(EntityHitResult par1MovingObjectPosition) {
        Entity hit = par1MovingObjectPosition.getEntity();
        if (hit != null) {
            float var2 = 2.0f;
            if (this.getShoeId() == 6) {
                var2 = 6.0f;
            }
            if (hit instanceof Creeper) {
                var2 += 4.0f;
            }
            if (hit instanceof Girlfriend) {
                var2 = 1.0f;
            }
            if (hit instanceof Boyfriend) {
                var2 = 1.0f;
            }
            if (hit instanceof Player) {
                var2 = 0.0f;
            }
            if (ChaosPersists.valentines_day != 0) {
                var2 = 10.0f;
            }
            if (var2 > 0.0f && !PetCombatHelper.areAlliedOwnedCompanions(this.getOwner(), hit)) {
                hit.hurt(this.damageSources().thrown(this, this.getOwner()), var2);
            }
        }
        this.spawnImpactParticles();
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (result.getType() != HitResult.Type.ENTITY) {
            this.spawnImpactParticles();
            if (!this.level().isClientSide) {
                this.discard();
            }
        }
    }

    private void spawnImpactParticles() {
        for (int var3 = 0; var3 < 4; ++var3) {
            this.level().addParticle(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.level().addParticle(new DustParticleOptions(new Vector3f(0.5f, 0.5f, 0.5f), 1.0f), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.my_rotation += 20.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
    }

    protected ItemStack getDefaultItem() {
        return ItemStack.EMPTY;
    }
}
