package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.WaterDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class InkSack extends ThrowableEntity {
    private float my_rotation = 0.0f;
    private int my_index = 65;

    @SuppressWarnings("unchecked")
    private static EntityType<? extends InkSack> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ink_sack"));
        if (type != null) {
            return (EntityType<? extends InkSack>) type;
        }
        return (EntityType<? extends InkSack>) (EntityType<?>) EntityType.SNOWBALL;
    }

    public InkSack(EntityType<? extends InkSack> type, World par1World) {
        super(type, par1World);
    }

    public InkSack(World par1World) {
        super(resolveEntityType(), par1World);
    }

    public InkSack(World par1World, int par2) {
        super(resolveEntityType(), par1World);
    }

    public InkSack(World par1World, MobEntity par2Mob) {
        super(resolveEntityType(), par1World);
        this.setOwner(par2Mob);
    }

    public InkSack(World par1World, MobEntity par2Mob, int par3) {
        super(resolveEntityType(), par1World);
        this.setOwner(par2Mob);
    }

    public InkSack(World par1World, double par2, double par4, double par6) {
        super(resolveEntityType(), par2, par4, par6, par1World);
    }

    @Override
    protected void defineSynchedData() {
    }

    public int getInkSackIndex() {
        return this.my_index;
    }

    @Override
    protected void onHit(RayTraceResult par1MovingObjectPosition) {
        Entity entityHit = null;
        if (par1MovingObjectPosition.getType() == net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
            entityHit = ((net.minecraft.util.math.EntityRayTraceResult) par1MovingObjectPosition).getEntity();
        }
        if (entityHit != null) {
            float var2 = 1.0f;
            if (entityHit instanceof CreeperEntity) {
                var2 = 4.0f;
            }
            if (entityHit instanceof WaterDragon) {
                return;
            }
            if (entityHit instanceof AttackSquid) {
                return;
            }
            if (entityHit instanceof LivingEntity) {
                LivingEntity livingHit = (LivingEntity) entityHit;
                livingHit.hurt(DamageSource.thrown(this, this.getOwner()), var2);
                if (this.level.random.nextInt(2) == 0) {
                    livingHit.addEffect(new EffectInstance(Effects.BLINDNESS, 100 + 50 * this.level.random.nextInt(8), 0));
                }
            }
        }
        for (int var3 = 0; var3 < 4; ++var3) {
            this.level.addParticle(ParticleTypes.SMOKE, this.getX() + (double) this.random.nextFloat() - (double) this.random.nextFloat(), this.getY() + (double) this.random.nextFloat() - (double) this.random.nextFloat(), this.getZ() + (double) this.random.nextFloat(), 0.0, 0.0, 0.0);
        }
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.GENERIC_SPLASH, SoundCategory.NEUTRAL, 0.5f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.5f);
        if (!this.level.isClientSide) {
            this.remove();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.my_rotation += 30.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.xRot = (this.xRotO = this.my_rotation);
    }
}
