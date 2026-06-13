package com.astryxion.chaospersists.entity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.attributes.Attributes;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.item.IceBall;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Util;
import net.minecraft.world.Explosion;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class TheKing extends MonsterEntity
{
  private static final DataParameter<Byte> BYTE20 = EntityDataManager.defineId(TheKing.class, DataSerializers.BYTE);
  private static final DataParameter<Integer> PLAY_NICELY = EntityDataManager.defineId(TheKing.class, DataSerializers.INT);
  private static final DataParameter<Integer> IS_END = EntityDataManager.defineId(TheKing.class, DataSerializers.INT);
  private BlockPos currentFlightTarget = null;
  private GenericTargetSorter TargetSorter = null;
  private LivingEntity rt = null;
  private double attdam = 250.0D;
  private int hurt_timer = 0;
  private int homex = 0;
  private int homez = 0;
  private int stream_count = 0;
  private int stream_count_l = 0;
  private int stream_count_i = 0;
  private int ticker = 0;
  private int player_hit_count = 0;
  private int backoff_timer = 0;
  private int guard_mode = 0;
  private volatile int head_found = 0;
  private int wing_sound = 0;
  private int large_unknown_detected = 0;
  private int isEnd = 0;
  private int endCounter = 0;

  public TheKing(EntityType<? extends TheKing> type, World par1World) {
    super(type, par1World);
    if (ChaosPersists.PlayNicely == 0) {
    } else {
    }
    this.xpReward = 25000;
    this.noPhysics = true;
    this.TargetSorter = new GenericTargetSorter(this);
    // renderDistanceWeight: EntityType tracking range set in ChaosPersists registration
    this.goalSelector.addGoal(0, new SwimGoal(this));
    this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
  }

  @Override
  public boolean fireImmune() {
    return true;
  }

  public static net.minecraft.entity.ai.attributes.AttributeModifierMap createAttributes()
  {
    return MonsterEntity.createMonsterAttributes()
        .add(Attributes.MAX_HEALTH, ChaosPersists.TheKing_stats.health)
        .add(Attributes.MOVEMENT_SPEED, 0.6200000047683716D)
        .add(Attributes.ATTACK_DAMAGE, ChaosPersists.TheKing_stats.attack)
        .build();
  }

  protected void defineSynchedData()
  {
    super.defineSynchedData();
    this.entityData.define(BYTE20, (byte)0);
    this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
    this.entityData.define(IS_END, this.isEnd);
  }

  public int getPlayNicely() {
    return this.entityData.get(PLAY_NICELY).intValue();
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isInRangeToRenderDist(double par1)
  {
    return true;
  }

  @OnlyIn(Dist.CLIENT)
  public boolean isInRangeToRenderVec3D(net.minecraft.util.math.vector.Vector3d par1Vec3)
  {
    return true;
  }

  protected boolean canDespawn(double distanceToClosestPlayerEntity) {
    return false;
  }

  public final int getAttacking()
  {
    return this.entityData.get(BYTE20).byteValue();
  }

  public final void setAttacking(int par1)
  {
    this.entityData.set(BYTE20, (byte)par1);
  }

  protected float getSoundVolume()
  {
    return 1.35F;
  }

  protected float getVoicePitch()
  {
    return 1.0F;
  }

  protected net.minecraft.util.SoundEvent getAmbientSound()
  {
    return com.astryxion.chaospersists.core.ChaosSounds.KING_LIVING;
  }

  protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource)
  {
    return com.astryxion.chaospersists.core.ChaosSounds.KING_HIT;
  }

  protected net.minecraft.util.SoundEvent getDeathSound()
  {
    return com.astryxion.chaospersists.core.ChaosSounds.TREX_DEATH;
  }

  public boolean canBePushed()
  {
    return false;
  }

  protected void collideWithEntity(Entity par1Entity) {
  }

  public int mygetMaxHealth() {
    return ChaosPersists.TheKing_stats.health;
  }

  protected Item getDropItem()
  {
    return Blocks.DANDELION.asItem();
  }

  private void dropItemRand(Item index, int par1)
  {
    ItemEntity var3 = new ItemEntity(this.level, this.getX() + ChaosPersists.ChaosRand.nextInt(20) - ChaosPersists.ChaosRand.nextInt(20), this.getY() + 12.0D, this.getZ() + ChaosPersists.ChaosRand.nextInt(20) - ChaosPersists.ChaosRand.nextInt(20), new ItemStack(index, par1));

    this.level.addFreshEntity(var3);
  }

  protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
      int k;
      Item it = null;
      Block bl = null;
      TheKing.spawnCreature((World)this.level, (String)"The Prince", (double)this.getX(), (double)(this.getY() + 10.0), (double)this.getZ());
      this.dropItemRand((Item)ChaosPersists.RoyalBody, 1);
      this.dropItemRand((Item)ChaosPersists.RoyalHelmet, 1);
      this.dropItemRand((Item)ChaosPersists.RoyalLegs, 1);
      this.dropItemRand((Item)ChaosPersists.RoyalBoots, 1);
      this.dropItemRand(ChaosPersists.MyRoyal, 1);
      java.util.List<Item> itemList = new java.util.ArrayList<>();
      for (Item itemObj : ForgeRegistries.ITEMS.getValues()) {
          if (itemObj != null) itemList.add(itemObj);
      }
      int icount = itemList.size();
      int j = 0;
      while (j < 150) {
          it = itemList.get(this.level.random.nextInt(icount));
          if (it == null) continue;
          ++j;
          this.dropItemRand(it, 1);
      }
      java.util.List<Block> blockList = new java.util.ArrayList<>();
      for (Block blk : ForgeRegistries.BLOCKS.getValues()) {
          if (blk != null) blockList.add(blk);
      }
      int bcount = blockList.size();
      j = 0;
      while (j < 150) {
          bl = blockList.get(this.level.random.nextInt(bcount));
          if (bl == null) continue;
          ++j;
          this.dropItemRand(bl.asItem(), 1);
      }
  }

  protected boolean isAIEnabled()
  {
    return true;
  }

  public void tick()
  {
    super.tick();

    this.wing_sound += 1;
    if (this.wing_sound > 30)
    {
      if (!this.level.isClientSide) this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, this.getSoundSource(), 1.75F, 0.75F);
      this.wing_sound = 0;
    }

    this.noPhysics = true;
    com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.6D, 1.0);
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() * 2 / 3)) this.attdam = (ChaosPersists.TheKing_stats.attack * 2);
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 2)) this.attdam = (ChaosPersists.TheKing_stats.attack * 4);
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 4)) this.attdam = (ChaosPersists.TheKing_stats.attack * 8);
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 8)) this.attdam = (ChaosPersists.TheKing_stats.attack * 16);

    if (this.level.isClientSide) {
      float f = 7.0F;
      this.isEnd = this.entityData.get(IS_END).intValue();

      if ((this.isEnd != 0) && (this.level.random.nextInt(3) == 1))
      {
        for (int i = 0; i < 10; i++)
          this.level.addParticle(net.minecraft.particles.ParticleTypes.FIREWORK, this.getX() - f * Math.sin(Math.toRadians(this.yRot)), this.getY() + 14.0D, this.getZ() + f * Math.cos(Math.toRadians(this.yRot)), (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 4.0D + this.getDeltaMovement().x * 6.0D, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 4.0D, (this.level.random.nextGaussian() - this.level.random.nextGaussian()) / 4.0D + this.getDeltaMovement().z * 6.0D);
      }
    }
  }

  public boolean doHurtTarget(LivingEntity par1Entity)
  {
    if ((par1Entity != null) && ((par1Entity instanceof LivingEntity)))
    {
      float s = par1Entity.getBbHeight() * par1Entity.getBbWidth();
      if ((s > 30.0F) && 
        (!MyUtils.isRoyalty(par1Entity)) && (!(par1Entity instanceof Godzilla)) && (!(par1Entity instanceof GodzillaHead)) && (!(par1Entity instanceof PitchBlack)) && (!(par1Entity instanceof Kraken)))
      {
        LivingEntity e = (LivingEntity)par1Entity;
        e.setHealth(e.getHealth() / 2.0F);
        e.hurt(DamageSource.mobAttack(this), (float)this.attdam * 10.0F);
        this.large_unknown_detected = 1;
      }

    }

    if ((par1Entity != null) && ((par1Entity instanceof EnderDragonEntity))) {
      EnderDragonEntity dr = (EnderDragonEntity)par1Entity;
      DamageSource var21 = null;
      var21 = DamageSource.explosion((Explosion)null);
      if (this.level.random.nextInt(6) == 1)
        dr.hurt(var21, (float)this.attdam);
      else {
        dr.hurt(var21, (float)this.attdam);
      }
    }

    boolean var4 = par1Entity.hurt(DamageSource.mobAttack(this), (float)this.attdam);
    if (var4) {
      double ks = 3.3D;
      double inair = 0.25D;
      float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
      inair += this.level.random.nextFloat() * 0.25F;
      if ((par1Entity.removed) || ((par1Entity instanceof PlayerEntity))) inair *= 1.5D;
      par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
    }

    return var4;
  }

  public boolean canSeeTarget(double pX, double pY, double pZ)
  {
    return this.level.clip(new RayTraceContext(new Vector3d(this.getX(), this.getY() + 8.75D, this.getZ()), new Vector3d(pX, pY, pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
  }

  private boolean tooFarFromHome()
  {
    float d1 = (float)(this.getX() - this.homex);
    float d2 = (float)(this.getZ() - this.homez);

    d1 = (float)Math.sqrt(d1 * d1 + d2 * d2);
    return d1 > 120.0F;
  }

  private void msgToPlayerEntitys(String s)
  {
    List var5 = this.level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(80.0D, 64.0D, 80.0D));
    Collections.sort(var5, this.TargetSorter);
    Iterator var2 = var5.iterator();
    Entity var3 = null;
    PlayerEntity var4 = null;

    while (var2.hasNext())
    {
      var3 = (Entity)var2.next();
      var4 = (PlayerEntity)var3;
      var4.sendMessage(new net.minecraft.util.text.StringTextComponent(s), Util.NIL_UUID);
    }
  }

  private PlayerEntity findNearestPlayerEntity()
  {
    List var5 = this.level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(80.0D, 64.0D, 80.0D));
    Collections.sort(var5, this.TargetSorter);
    Iterator var2 = var5.iterator();
    Entity var3 = null;
    PlayerEntity var4 = null;

    while (var2.hasNext())
    {
      var3 = (Entity)var2.next();
      if ((var3 instanceof PlayerEntity)) var4 = (PlayerEntity)var3;
      if (var4 == null)
        continue;
    }
    return var4;
  }

  protected void customServerAiStep()
  {
    int xdir = 1;
    int zdir = 1;

    int attrand = 5;
    int updown = 0;
    int which = 0;
    LivingEntity e = null;
    LivingEntity f = null;

    double rr = 0.0D;
    double rhdir = 0.0D;
    double rdd = 0.0D;
    double pi = 3.1415926545D;
    double var1 = 0.0D;
    double var3 = 0.0D;
    double var5 = 0.0D;
    float var7 = 0.0F;
    float var8 = 0.0F;

    PlayerEntity p = null;

    if (!this.isAlive()) return;
    super.customServerAiStep();
    this.entityData.set(IS_END, this.isEnd);
    this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);

    if (this.isEnd == 1) {
      this.endCounter += 1;
      this.noPhysics = true;
      this.setDeltaMovement(0.0D, this.getDeltaMovement().y, this.getDeltaMovement().z);
      this.setDeltaMovement(this.getDeltaMovement().x, 0.0D, this.getDeltaMovement().z);
      this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 0.0D);
      this.hurt_timer = 10;

      if (!this.isAlive()) return;

      p = findNearestPlayerEntity();
      if (p != null) {
        this.getLookControl().setLookAt(p, 10.0F, 10.0F);
        p.setDeltaMovement(0.0D, p.getDeltaMovement().y, p.getDeltaMovement().z);
        p.setDeltaMovement(p.getDeltaMovement().x, 0.0D, p.getDeltaMovement().z);
        p.setDeltaMovement(p.getDeltaMovement().x, p.getDeltaMovement().y, 0.0D);
        double dd0 = this.getX() - p.getX();
        double dd1 = this.getZ() - p.getZ();
        float f2 = (float)(Math.atan2(dd1, dd0) * 180.0D / 3.141592653589793D) - 90.0F;
        p.yRot = f2;
        p.setHealth(1.0F);
      }

      if (this.endCounter == 10) {
        msgToPlayerEntitys("The King: Enough of this charade. I am done. You have shown me what I wanted to know.");
        return;
      }
      if (this.endCounter == 80) {
        msgToPlayerEntitys("The King: That's right my little pet. It has all been a game. You never killed me. You can't.");
        return;
      }
      if (this.endCounter == 160) {
        msgToPlayerEntitys("The King: I am the one. The only. The many. I exist within both space and time. Everywhere and always.");
        return;
      }
      if (this.endCounter == 240) {
        msgToPlayerEntitys("The King: I used you to learn your ways, and I have reached my conclusion on your species.");
        return;
      }
      if (this.endCounter == 300) {
        msgToPlayerEntitys("The King: You have 10 seconds to run...");
        return;
      }
      if (this.endCounter == 320) {
        msgToPlayerEntitys("9.");
        return;
      }
      if (this.endCounter == 340) {
        msgToPlayerEntitys("8.");
        return;
      }
      if (this.endCounter == 360) {
        msgToPlayerEntitys("7.");
        return;
      }
      if (this.endCounter == 380) {
        msgToPlayerEntitys("6.");
        return;
      }
      if (this.endCounter == 400) {
        msgToPlayerEntitys("5.");
        return;
      }
      if (this.endCounter == 420) {
        msgToPlayerEntitys("4.");
        return;
      }
      if (this.endCounter == 440) {
        msgToPlayerEntitys("3.");
        return;
      }
      if (this.endCounter == 460) {
        msgToPlayerEntitys("2.");
        return;
      }
      if (this.endCounter == 480) {
        msgToPlayerEntitys("1.");
        return;
      }
      if (this.endCounter == 500) {
        msgToPlayerEntitys("The King: Prepare to die!");
        this.isEnd = 2;
        return;
      }

      return;
    }

    if (this.isEnd == 2) {
      this.hurt_timer = 10;
      this.player_hit_count = 0;
      this.stream_count = 10;
      this.stream_count_l = 10;
      this.stream_count_i = 10;
      attrand = 3;
      this.guard_mode = 0;
      this.large_unknown_detected = 1;
      if (this.backoff_timer > 0) this.backoff_timer -= 1;
    }

    if (this.hurt_timer > 0) this.hurt_timer -= 1;
    if (((this.homex == 0) && (this.homez == 0)) || (this.guard_mode == 0)) {
      this.homex = (int)this.getX();
      this.homez = (int)this.getZ();
    }

    this.ticker += 1;
    if (this.ticker > 30000) this.ticker = 0;
    if (this.ticker % 80 == 0) this.stream_count = 10;
    if (this.ticker % 90 == 0) this.stream_count_l = 5;
    if (this.ticker % 70 == 0) this.stream_count_i = 8;
    if (this.backoff_timer > 0) this.backoff_timer -= 1;

    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 2)) attrand = 3;
    this.noPhysics = true;

    if (this.currentFlightTarget == null) {
      this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
    }

    if ((tooFarFromHome()) || (this.level.random.nextInt(200) == 0) || (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.1F))
    {
      zdir = this.level.random.nextInt(120);
      xdir = this.level.random.nextInt(120);
      if (this.level.random.nextInt(2) == 0) zdir = -zdir;
      if (this.level.random.nextInt(2) == 0) xdir = -xdir;

      int dist = 0;
      for (int i = -5; i <= 5; i += 5) {
        for (int j = -5; j <= 5; j += 5) {
          Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY(), this.homez + i)).getBlock();
          if (bid != Blocks.AIR)
            for (int k = 1; k < 20; k++) {
              bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY() + k, this.homez + i)).getBlock();
              dist++;
              if (bid == Blocks.AIR)
                break;
            }
          for (int k = 1; k < 20; k++) {
            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.homex + j, (int)this.getY() - k, this.homez + i)).getBlock();
            dist--;
            if (bid != Blocks.AIR)
              break;
          }
        }
      }
      dist = dist / 9 + 2;
      if ((int)(this.getY() + dist) > 230) dist = 230 - (int)this.getY();
      this.currentFlightTarget = new BlockPos(this.homex + xdir, (int)(this.getY() + dist), this.homez + zdir);
    }
    else if (this.level.random.nextInt(attrand) == 0)
    {
      e = this.rt;
      if (ChaosPersists.PlayNicely != 0) e = null;
      if ((e != null) && (
        ((e instanceof TheKing)) || ((e instanceof KingHead)))) {
        this.rt = null;
        e = null;
      }

      if (e != null)
      {
        float d1 = (float)(e.getX() - this.homex);
        float d2 = (float)(e.getZ() - this.homez);
        d1 = (float)Math.sqrt(d1 * d1 + d2 * d2);
        if ((e.removed) || (this.level.random.nextInt(250) == 1) || ((d1 > 128.0F) && (this.guard_mode == 1))) {
          e = null;
          this.rt = null;
        }
        if ((e != null) && 
          (!MyCanSee(e))) {
          e = null;
        }

      }

      f = findSomethingToAttack();
      if (this.head_found == 0)
      {
        MobEntity localMob = (MobEntity)spawnCreature(this.level, "KingHead", this.getX(), this.getY() + 20.0D, this.getZ());
      }

      if (e == null) {
        e = f;
      }

      if (e != null)
      {
        setAttacking(1);
        if (this.backoff_timer == 0) {
          int dist = (int)(e.getY() + e.getBbHeight() / 2.0F + 1.0D);
          if (dist > 230) dist = 230;
          this.currentFlightTarget = new BlockPos((int)e.getX(), dist, (int)e.getZ());
          if (this.level.random.nextInt(70) == 1) this.backoff_timer = (80 + this.level.random.nextInt(80));
        }
        else if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 9.1F)
        {
          zdir = this.level.random.nextInt(20) + 30;
          xdir = this.level.random.nextInt(20) + 30;
          if (this.level.random.nextInt(2) == 0) zdir = -zdir;
          if (this.level.random.nextInt(2) == 0) xdir = -xdir;

          int dist = 0;
          for (int i = -5; i <= 5; i += 5) {
            for (int j = -5; j <= 5; j += 5) {
              Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY(), (int)e.getZ() + i)).getBlock();
              if (bid != Blocks.AIR)
                for (int k = 1; k < 20; k++) {
                  bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY() + k, (int)e.getZ() + i)).getBlock();
                  dist++;
                  if (bid == Blocks.AIR)
                    break;
                }
              for (int k = 1; k < 20; k++) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)e.getX() + j, (int)this.getY() - k, (int)e.getZ() + i)).getBlock();
                dist--;
                if (bid != Blocks.AIR)
                  break;
              }
            }
          }
          dist = dist / 9 + 2;
          if ((int)(this.getY() + dist) > 230) dist = 230 - (int)this.getY();
          this.currentFlightTarget = new BlockPos((int)e.getX() + xdir, (int)(this.getY() + dist), (int)e.getZ() + zdir);
        }

        if (distanceToSqr(e) < 900.0D) {
          if (this.level.random.nextInt(2) == 1) doJumpDamage(this.getX(), this.getY(), this.getZ(), 15.0D, ChaosPersists.TheKing_stats.attack / 4, 0);
          doHurtTarget((LivingEntity)e);
        }

        double dx = this.getX() + 20.0D * Math.sin(Math.toRadians(this.yHeadRot));
        double dz = this.getZ() - 20.0D * Math.cos(Math.toRadians(this.yHeadRot));
        if (this.level.random.nextInt(3) == 1) doJumpDamage(dx, this.getY() + 10.0D, dz, 15.0D, ChaosPersists.TheKing_stats.attack / 2, 1);

        if (getHorizontalDistanceSqToEntity(e) > 900.0D) {
          which = this.level.random.nextInt(3);
          if (which == 0)
          {
            if (this.stream_count > 0) {
              setAttacking(1);

              rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
              rhdir = Math.toRadians((this.yHeadRot + 90.0F) % 360.0F);

              rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
              if (rdd > pi) rdd -= pi * 2.0D;
              rdd = Math.abs(rdd);

              if (rdd < 0.5D) {
                firecanon(e);
              }
            }
          }
          else if (which == 1) {
            if (this.stream_count_l > 0) {
              setAttacking(1);

              rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
              rhdir = Math.toRadians((this.yHeadRot + 90.0F) % 360.0F);

              rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
              if (rdd > pi) rdd -= pi * 2.0D;
              rdd = Math.abs(rdd);

              if (rdd < 0.5D) {
                firecanonl(e);
              }
            }

          }
          else if (this.stream_count_i > 0) {
            setAttacking(1);

            rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
            rhdir = Math.toRadians((this.yHeadRot + 90.0F) % 360.0F);

            rdd = Math.abs(rr - rhdir) % (pi * 2.0D);
            if (rdd > pi) rdd -= pi * 2.0D;
            rdd = Math.abs(rdd);

            if (rdd < 0.5D) {
              firecanoni(e);
            }
          }
        }
      }
      else
      {
        setAttacking(0);
        this.stream_count = 10;
        this.stream_count_l = 5;
        this.stream_count_i = 8;
      }
    }

    if ((getAttacking() != 0) && (this.isEnd == 2)) {
      double xzoff = 10.0D;
      double yoff = 14.0D;
      Entity ppwr = spawnCreature(this.level, "PurplePower", this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)));

      if (ppwr != null) {
        PurplePower pwr = (PurplePower)ppwr;
        pwr.setDeltaMovement((this.getDeltaMovement().x * 3.0D), pwr.getDeltaMovement().y, pwr.getDeltaMovement().z);
        pwr.setDeltaMovement(pwr.getDeltaMovement().x, pwr.getDeltaMovement().y, (this.getDeltaMovement().z * 3.0D));
        pwr.setPurpleType(10);
      }
    }

    var1 = this.currentFlightTarget.getX() + 0.5D - this.getX();
    var3 = this.currentFlightTarget.getY() + 0.1D - this.getY();
    var5 = this.currentFlightTarget.getZ() + 0.5D - this.getZ();
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) * 0.7D - this.getDeltaMovement().x) * 0.35D, (Math.signum(var3) * 0.69999D - this.getDeltaMovement().y) * 0.3D, (Math.signum(var5) * 0.7D - this.getDeltaMovement().z) * 0.35D);

    var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0D / 3.141592653589793D) - 90.0F;
    var8 = MathHelper.wrapDegrees(var7 - this.yRot);
    this.yya = 1.0F;
    this.yRot += var8 / 8.0F;

    if ((this.level.random.nextInt(30) == 1) && 
      (getHealth() < mygetMaxHealth()))
    {
      heal(5.0F);
      if (this.large_unknown_detected != 0) heal(200.0F);
    }

    if ((this.player_hit_count < 10) && (getHealth() < 2000.0F)) heal(2000.0F - getHealth());
  }

  private double getHorizontalDistanceSqToEntity(Entity e)
  {
    double d1 = e.getZ() - this.getZ();
    double d2 = e.getX() - this.getX();
    return d1 * d1 + d2 * d2;
  }

  private void firecanon(LivingEntity e)
  {
    double yoff = 14.0D;
    double xzoff = 32.0D;

    BetterFireball bf = null;

    double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
    double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
    if (this.stream_count > 0)
    {
      bf = new BetterFireball(this.level, this, e.getX() - cx, e.getY() + e.getBbHeight() / 2.0F - (this.getY() + yoff), e.getZ() - cz);
      bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0F);
      bf.setPos(cx, this.getY() + yoff, cz);
      bf.setReallyBig();
      this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.TNT_PRIMED, this.getSoundSource(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
      this.level.addFreshEntity(bf);
      for (int i = 0; i < 6; i++) {
        float r1 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        bf = new BetterFireball(this.level, this, e.getX() - cx + r1, e.getY() + e.getBbHeight() / 2.0F - (this.getY() + yoff) + r2, e.getZ() - cz + r3);
        bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0F);
        bf.setPos(cx, this.getY() + yoff, cz);
        bf.setBig();
        if (this.level.random.nextInt(2) == 1) bf.setSmall();
        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
        this.level.addFreshEntity(bf);
      }
      this.stream_count -= 1;
    }
  }

  private void firecanonl(LivingEntity e)
  {
    double yoff = 14.0D;
    double xzoff = 32.0D;

    double var3 = 0.0D;
    double var5 = 0.0D;
    double var7 = 0.0D;
    float var9 = 0.0F;
    double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
    double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
    if (this.stream_count_l > 0) {
      this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
      for (int i = 0; i < 3; i++) {
        float r1 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        ThunderBolt lb = new ThunderBolt(this.level, cx, this.getY() + yoff, cz);
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0F, 0.0F);
        var3 = e.getX() - lb.getX();
        var5 = e.getY() + 0.25D - lb.getY();
        var7 = e.getZ() - lb.getZ();
        var9 = MathHelper.sqrt(var3 * var3 + var7 * var7) * 0.2F;
        lb.shoot(var3, var5 + var9, var7, 1.4F, 4.0F);
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0D, 3.0D, 3.0D);
        this.level.addFreshEntity(lb);
      }
      this.stream_count_l -= 1;
    }
  }

  private void firecanoni(LivingEntity e)
  {
    double yoff = 14.0D;
    double xzoff = 32.0D;

    double var3 = 0.0D;
    double var5 = 0.0D;
    double var7 = 0.0D;
    float var9 = 0.0F;
    double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
    double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
    if (this.stream_count_i > 0) {
      this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.SoundEvents.ARROW_SHOOT, this.getSoundSource(), 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
      for (int i = 0; i < 5; i++) {
        float r1 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r2 = 3.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        float r3 = 5.0F * (this.level.random.nextFloat() - this.level.random.nextFloat());
        IceBall lb = new IceBall(this.level, cx, this.getY() + yoff, cz);
        lb.setIceMaker(1);
        lb.moveTo(cx, this.getY() + yoff, cz, 0.0F, 0.0F);
        var3 = e.getX() - lb.getX();
        var5 = e.getY() + 0.25D - lb.getY();
        var7 = e.getZ() - lb.getZ();
        var9 = MathHelper.sqrt(var3 * var3 + var7 * var7) * 0.2F;
        lb.shoot(var3, var5 + var9, var7, 1.4F, 4.0F);
        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(lb, 3.0D, 3.0D, 3.0D);
        this.level.addFreshEntity(lb);
      }
      this.stream_count_i -= 1;
    }
  }

  protected boolean canTriggerWalking()
  {
    return true;
  }

  public void fall(float distance, float damageMultiplier) {
  }

  protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
    fallDistance = 0.0f;
  }

  public boolean doesEntityNotTriggerPressurePlate()
  {
    return false;
  }

  @Override
  public boolean hurt(DamageSource par1DamageSource, float par2)
  {
    boolean ret = false;
    float dm = par2;

    if (this.hurt_timer > 0) return false;
    if (dm > 750.0F) dm = 750.0F;

    if (par1DamageSource.getMsgId().equals("inWall")) {
      return false;
    }

    Entity e = par1DamageSource.getEntity();
    if ((e != null) && ((e instanceof LivingEntity)))
    {
      LivingEntity enl = (LivingEntity)e;
      float s = enl.getBbHeight() * enl.getBbWidth();
      if ((s > 30.0F) && 
        (!MyUtils.isRoyalty(enl)) && (!(enl instanceof Godzilla)) && (!(enl instanceof GodzillaHead)) && (!(enl instanceof PitchBlack)) && (!(enl instanceof Kraken)))
      {
        dm /= 10.0F;
        this.hurt_timer = 50;
        this.large_unknown_detected = 1;
      }

      if (((e instanceof MonsterEntity)) && 
        (s < 3.0F)) {
        e.remove();
        return false;
      }

    }

    if (!par1DamageSource.getMsgId().equals("cactus")) {
      this.hurt_timer = 20;
      ret = super.hurt(par1DamageSource, dm);

      if ((e != null) && ((e instanceof PlayerEntity)))
      {
        this.player_hit_count += 1;
      }

      if ((e != null) && ((e instanceof LivingEntity)) && (this.currentFlightTarget != null))
      {
        if (!MyUtils.isRoyalty(e)) {
          this.rt = ((LivingEntity)e);
          int dist = (int)e.getY();
          if (dist > 230) dist = 230;
          this.currentFlightTarget = new BlockPos((int)e.getX(), dist, (int)e.getZ());
        }
      }
    }
    return ret;
  }

  public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason)
  {
    return true;
  }

  public int getArmorValue()
  {
    if (this.large_unknown_detected != 0) return 25;
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() * 2 / 3)) return ChaosPersists.TheKing_stats.defense + 1;
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 2)) return ChaosPersists.TheKing_stats.defense + 2;
    if ((this.player_hit_count < 10) && (getHealth() < mygetMaxHealth() / 4)) return ChaosPersists.TheKing_stats.defense + 3;
    return ChaosPersists.TheKing_stats.defense;
  }

  public void onStruckByLightning(LightningBoltEntity par1LightningBoltEntity)
  {
  }

  public void initCreature()
  {
  }

  public boolean MyCanSee(LivingEntity e)
  {
    double xzoff = 22.0D;

    int nblks = 20;

    double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
    double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
    float startx = (float)cx;
    float starty = (float)(this.getY() + this.getBbHeight() * 7.0F / 8.0F);
    float startz = (float)cz;
    float dx = (float)((e.getX() - startx) / 20.0D);
    float dy = (float)((e.getY() + e.getBbHeight() / 2.0F - starty) / 20.0D);
    float dz = (float)((e.getZ() - startz) / 20.0D);

    if (Math.abs(dx) > 1.0D) {
      dy /= Math.abs(dx);
      dz /= Math.abs(dx);
      nblks = (int)(nblks * Math.abs(dx));
      if (dx > 1.0F) dx = 1.0F;
      if (dx < -1.0F) dx = -1.0F;
    }
    if (Math.abs(dy) > 1.0D) {
      dx /= Math.abs(dy);
      dz /= Math.abs(dy);
      nblks = (int)(nblks * Math.abs(dy));
      if (dy > 1.0F) dy = 1.0F;
      if (dy < -1.0F) dy = -1.0F;
    }
    if (Math.abs(dz) > 1.0D) {
      dy /= Math.abs(dz);
      dx /= Math.abs(dz);
      nblks = (int)(nblks * Math.abs(dz));
      if (dz > 1.0F) dz = 1.0F;
      if (dz < -1.0F) dz = -1.0F;
    }

    for (int i = 0; i < nblks; i++) {
      startx += dx;
      starty += dy;
      startz += dz;
      Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)startx, (int)starty, (int)startz)).getBlock();
      if ((bid != Blocks.WATER) && !(bid instanceof LeavesBlock) && (bid != Blocks.VINE) && 
        (bid != Blocks.AIR)) return false;

    }

    return true;
  }

  private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2)
  {
    if (par1Mob == null)
    {
      return false;
    }

    if (par1Mob == this)
    {
      return false;
    }
    if (!par1Mob.isAlive())
    {
      return false;
    }

    if ((par1Mob instanceof KingHead))
    {
      this.head_found = 1;
      return false;
    }
    if (MyUtils.isRoyalty(par1Mob))
    {
      return false;
    }

    float d1 = (float)(par1Mob.getX() - this.homex);
    float d2 = (float)(par1Mob.getZ() - this.homez);
    d1 = (float)Math.sqrt(d1 * d1 + d2 * d2);
    if (d1 > 144.0F) return false;

    if (MyUtils.isIgnoreable(par1Mob)) return false;

    if (this.isEnd == 2) {
      if ((par1Mob instanceof PlayerEntity))
      {
        PlayerEntity p = (PlayerEntity)par1Mob;

        return !p.isCreative();
      }

      if ((par1Mob instanceof Girlfriend))
      {
        return true;
      }
      if ((par1Mob instanceof Boyfriend))
      {
        return true;
      }
      if ((par1Mob instanceof VillagerEntity))
      {
        return true;
      }

    }

    if (!MyCanSee(par1Mob))
    {
      return false;
    }

    if ((par1Mob instanceof PlayerEntity))
    {
      PlayerEntity p = (PlayerEntity)par1Mob;

      return !p.isCreative();
    }

    if ((par1Mob instanceof HorseEntity))
    {
      return true;
    }
    if ((par1Mob instanceof MonsterEntity))
    {
      return true;
    }

    if ((par1Mob instanceof EnderDragonEntity))
    {
      return true;
    }

    return MyUtils.isAttackableNonMob(par1Mob);
  }

  private LivingEntity findSomethingToAttack()
  {
    if (ChaosPersists.PlayNicely != 0) {
      this.head_found = 1;
      return null;
    }

    if (this.isEnd == 2) {
      List var5p = this.level.getEntitiesOfClass(PlayerEntity.class, this.getBoundingBox().inflate(80.0D, 64.0D, 80.0D));
      Collections.sort(var5p, this.TargetSorter);
      Iterator var2p = var5p.iterator();
      Entity var3p = null;
      LivingEntity var4p = null;
      LivingEntity retp = null;
      this.head_found = 1;
      while (var2p.hasNext())
      {
        var3p = (Entity)var2p.next();
        var4p = (LivingEntity)var3p;

        if (isSuitableTarget(var4p, false))
        {
          return var4p;
        }
      }
    }
    List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(80.0D, 64.0D, 80.0D));
    Collections.sort(var5, this.TargetSorter);
    Iterator var2 = var5.iterator();
    Entity var3 = null;
    LivingEntity var4 = null;
    LivingEntity ret = null;

    this.head_found = 0;
    while (var2.hasNext())
    {
      var3 = (Entity)var2.next();
      var4 = (LivingEntity)var3;

      if (isSuitableTarget(var4, false))
      {
        if (ret == null) ret = var4;
      }
      if ((ret == null) || (this.head_found == 0)) continue;
    }
    return ret;
  }

  public void setGuardMode(int i)
  {
    this.guard_mode = i;
  }

  public void setFree()
  {
    this.isEnd = 1;
  }

  public void addAdditionalSaveData(CompoundNBT par1CompoundNBT)
  {
    super.addAdditionalSaveData(par1CompoundNBT);

    par1CompoundNBT.putInt("KingHomeX", this.homex);
    par1CompoundNBT.putInt("KingHomeZ", this.homez);
    par1CompoundNBT.putInt("GuardMode", this.guard_mode);
    par1CompoundNBT.putInt("PlayerEntityHits", this.player_hit_count);
    par1CompoundNBT.putInt("IsEnd", this.isEnd);
    par1CompoundNBT.putInt("EndCounter", this.endCounter);
  }

  public void readAdditionalSaveData(CompoundNBT par1CompoundNBT)
  {
    super.readAdditionalSaveData(par1CompoundNBT);

    this.homex = par1CompoundNBT.getInt("KingHomeX");
    this.homez = par1CompoundNBT.getInt("KingHomeZ");
    this.guard_mode = par1CompoundNBT.getInt("GuardMode");
    this.player_hit_count = par1CompoundNBT.getInt("PlayerEntityHits");
    this.isEnd = par1CompoundNBT.getInt("IsEnd");
    this.endCounter = par1CompoundNBT.getInt("EndCounter");
  }

  public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6)
  {
    return com.astryxion.chaospersists.util.EntitySpawnHelper.spawn(par0World, par1, par2, par4, par6);
  }

  private LivingEntity doJumpDamage(double X, double Y, double Z, double dist, double damage, int knock)
  {
    AxisAlignedBB bb = new AxisAlignedBB(X - dist, Y - 10.0D, Z - dist, X + dist, Y + 10.0D, Z + dist);
    List var5 = this.level.getEntitiesOfClass(LivingEntity.class, bb);
    Collections.sort(var5, this.TargetSorter);
    Iterator var2 = var5.iterator();
    Entity var3 = null;
    LivingEntity var4 = null;

    while (var2.hasNext())
    {
      var3 = (Entity)var2.next();
      var4 = (LivingEntity)var3;

      if ((var4 == null) || 
        (var4 == this) || 
        (!var4.isAlive()) || 
        (MyUtils.isRoyalty(var4)) || 
        ((var4 instanceof Ghost)) || 
        ((var4 instanceof GhostSkelly)))
        continue;
      DamageSource var21 = null;
      var21 = DamageSource.explosion((Explosion)null);
      var4.hurt(var21, (float)damage / 2.0F);
      var4.hurt(DamageSource.FALL, (float)damage / 2.0F);
      this.level.playSound(null, var4.getX(), var4.getY(), var4.getZ(), net.minecraft.util.SoundEvents.GENERIC_EXPLODE, this.getSoundSource(), 0.65F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.5F);
      if (knock != 0) {
        double ks = 2.75D;
        double inair = 0.65D;
        float f3 = (float)Math.atan2(var4.getZ() - this.getZ(), var4.getX() - this.getX());
        var4.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
      }
    }

    return null;
  }
}
