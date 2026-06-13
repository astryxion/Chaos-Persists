package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class Shoes extends ThrowableEntity {
    private static final DataParameter<Integer> SHOE_ID = EntityDataManager.defineId(Shoes.class, DataSerializers.INT);
    public int ShoeId = 0;
    private float my_rotation = 0.0f;

    @SuppressWarnings("unchecked")
    private static EntityType<? extends Shoes> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "shoes"));
        return type != null ? (EntityType<? extends Shoes>) type : (EntityType<? extends Shoes>)(EntityType<?>)EntityType.SNOWBALL;
    }

    public Shoes(EntityType<? extends Shoes> type, World par1World) {
        super(type, par1World);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(World par1World) {
        super(resolveEntityType(), par1World);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(World par1World, int par2) {
        super(resolveEntityType(), par1World);
        this.ShoeId = par2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(World par1World, LivingEntity par2Mob) {
        super(resolveEntityType(), par1World);
        this.setOwner(par2Mob);
        this.ShoeId = this.random.nextInt(4) + 2;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(World par1World, LivingEntity par2Mob, int par3) {
        super(resolveEntityType(), par1World);
        this.setOwner(par2Mob);
        this.ShoeId = par3;
        this.entityData.set(SHOE_ID, this.ShoeId);
    }

    public Shoes(World par1World, double par2, double par4, double par6) {
        super(resolveEntityType(), par2, par4, par6, par1World);
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
     * Same trajectory as {@link com.astryxion.chaospersists.entity.Boyfriend#attackEntityWithRangedAttack} /
     * {@link com.astryxion.chaospersists.entity.Girlfriend#attackEntityWithRangedAttack} (1.7.10 behavior).
     */
    public static void shootTowardTarget(LivingEntity thrower, LivingEntity target, int shoeId) {
        World world = thrower.level;
        if (world.isClientSide || target == null || target == thrower) {
            return;
        }
        Shoes shoes = new Shoes(world, thrower, shoeId);
        double dx = target.getX() - thrower.getX();
        double dy = target.getY() + (double) target.getEyeHeight() - 1.1 - shoes.getY();
        double dz = target.getZ() - thrower.getZ();
        float lift = MathHelper.sqrt(dx * dx + dz * dz) * 0.2f;
        shoes.shoot(dx, dy + (double) lift, dz, 1.8f, 4.0f);
        world.addFreshEntity(shoes);
        world.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(), SoundEvents.SKELETON_SHOOT,
                SoundCategory.NEUTRAL, 0.75f, 1.0f / (world.random.nextFloat() * 0.4f + 0.8f));
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        Entity entityHit = null;
        if (par1MovingObjectPosition.getType() == net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
            entityHit = ((net.minecraft.util.math.EntityRayTraceResult) par1MovingObjectPosition).getEntity();
        }
        if (entityHit != null) {
            float var2 = 2.0f;
            if (this.getShoeId() == 6) {
                var2 = 6.0f;
            }
            if (entityHit instanceof CreeperEntity) {
                var2 += 4.0f;
            }
            if (entityHit instanceof Girlfriend) {
                var2 = 1.0f;
            }
            if (entityHit instanceof Boyfriend) {
                var2 = 1.0f;
            }
            if (entityHit instanceof PlayerEntity) {
                var2 = 0.0f;
            }
            if (ChaosPersists.valentines_day != 0) {
                var2 = 10.0f;
            }
            if (entityHit instanceof LivingEntity) {
                ((LivingEntity) entityHit).hurt(DamageSource.thrown(this, this.getOwner()), var2);
            }
        }
        for (int var3 = 0; var3 < 4; ++var3) {
            this.level.addParticle(net.minecraft.particles.ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            this.level.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.my_rotation += 20.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.xRot = (this.xRotO = this.my_rotation);
    }
}
