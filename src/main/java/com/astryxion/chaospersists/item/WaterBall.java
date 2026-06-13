package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.core.ChaosPersists;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class WaterBall extends ThrowableEntity
{
  private float my_rotation = 0.0F;
  private int my_index = 49;

  public WaterBall(EntityType<? extends WaterBall> type, World par1World)
  {
    super(type, par1World);
  }

  public WaterBall(World par1World)
  {
    this(resolveEntityType(), par1World);
  }

  @SuppressWarnings("unchecked")
  private static EntityType<? extends WaterBall> resolveEntityType() {
    EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "water_ball"));
    return type != null ? (EntityType<? extends WaterBall>) type : (EntityType<? extends WaterBall>)(EntityType<?>)EntityType.SNOWBALL;
  }

  public WaterBall(World par1World, LivingEntity par2Mob)
  {
    super(resolveEntityType(), par1World);
    this.setOwner(par2Mob);
  }

  public WaterBall(World worldObj, double d, double e, double f)
  {
    super(resolveEntityType(), d, e, f, worldObj);
  }

  protected void defineSynchedData() {
  }

  public int getWaterBallIndex()
  {
    return this.my_index;
  }

  @Override
  protected void onHit(RayTraceResult par1MovingObjectPosition)
  {
    Entity entityHit = null;
    if (par1MovingObjectPosition.getType() == net.minecraft.util.math.RayTraceResult.Type.ENTITY) {
      entityHit = ((net.minecraft.util.math.EntityRayTraceResult) par1MovingObjectPosition).getEntity();
    }
    if (entityHit != null)
    {
      float var2 = 2.0F;

      if ((entityHit instanceof CreeperEntity))
      {
        var2 = 5.0F;
      }
      if ((entityHit instanceof WaterDragon))
      {
        return;
      }
      if ((entityHit instanceof AttackSquid))
      {
        return;
      }
      if ((entityHit instanceof Dragon))
      {
        Dragon d = (Dragon)entityHit;
        if (d.getDragonType() != 0) {
          return;
        }
      }
      if ((entityHit instanceof PlayerEntity))
      {
        PlayerEntity d = (PlayerEntity)entityHit;
        if (d.getVehicle() != null) {
          return;
        }
      }
      if (entityHit instanceof LivingEntity) {
        LivingEntity livingHit = (LivingEntity) entityHit;
        livingHit.hurt(DamageSource.thrown(this, getOwner()), var2);
        if (this.level.random.nextInt(10) == 1) livingHit.spawnAtLocation(ChaosPersists.MyWaterBall, 1);
        livingHit.clearFire();
      }
    }

    for (int var3 = 0; var3 < 8; var3++)
    {
      this.level.addParticle(net.minecraft.particles.ParticleTypes.BUBBLE, this.getX() + this.random.nextFloat() - this.random.nextFloat(), this.getY() + this.random.nextFloat() - this.random.nextFloat(), this.getZ() + this.random.nextFloat() - this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
      this.level.addParticle(net.minecraft.particles.ParticleTypes.SPLASH, this.getX() + this.random.nextFloat() - this.random.nextFloat(), this.getY() + this.random.nextFloat() - this.random.nextFloat(), this.getZ() + this.random.nextFloat() - this.random.nextFloat(), 0.0D, 0.0D, 0.0D);
    }
    this.playSound(SoundEvents.GENERIC_SPLASH, 0.5F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.5F);

    if (!this.level.isClientSide)
    {
      this.remove();
    }
  }

  @Override
  public void tick()
  {
    super.tick();
    this.my_rotation += 30.0F;

    while (this.my_rotation > 360.0F) {
      this.my_rotation -= 360.0F;
    }

    this.xRot = (this.xRotO = this.my_rotation);

    this.level.addParticle(net.minecraft.particles.ParticleTypes.SPLASH, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
  }
}
