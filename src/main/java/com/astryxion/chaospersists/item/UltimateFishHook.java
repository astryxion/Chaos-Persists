/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.UltimateFishHook
 *  net.minecraft.block.Block
 *  net.minecraft.block.FlowingFluidBlock
 *  net.minecraft.block.BlockTripWireHook
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.item.ExperienceOrbEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemFishFood
 *  net.minecraft.item.ItemFishFood$FishType
 *  net.minecraft.item.FishingRodItem
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.stats.StatBase
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vector3d
 *  net.minecraft.util.WeightedRandom
 *  net.minecraft.util.WeightedRandomFishable
 *  net.minecraft.world.World
 *  net.minecraft.world.ServerWorld
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.WeightedRandomFishable;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.stats.Stats;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.registries.ForgeRegistries;

public class UltimateFishHook
extends Entity {
    private double motionX;
    private double motionY;
    private double motionZ;
    private static final List field_146039_d = Arrays.asList(new WeightedRandomFishable[]{new WeightedRandomFishable(new ItemStack((Item)Items.LEATHER_BOOTS), 10).func_150709_a(0.9f), new WeightedRandomFishable(new ItemStack(Items.LEATHER), 10), new WeightedRandomFishable(new ItemStack(Items.BONE), 10), new WeightedRandomFishable(new ItemStack((Item)Items.POTION), 10), new WeightedRandomFishable(new ItemStack(Items.STRING), 5), new WeightedRandomFishable(new ItemStack((Item)Items.FISHING_ROD), 2).func_150709_a(0.9f), new WeightedRandomFishable(new ItemStack(Items.BOWL), 10), new WeightedRandomFishable(new ItemStack(Items.STICK), 5), new WeightedRandomFishable(new ItemStack(Items.INK_SAC), 1), new WeightedRandomFishable(new ItemStack((Block)Blocks.TRIPWIRE_HOOK), 10), new WeightedRandomFishable(new ItemStack(Items.ROTTEN_FLESH), 10)});
    private static final List field_146041_e = Arrays.asList(new WeightedRandomFishable[]{new WeightedRandomFishable(new ItemStack(Blocks.LILY_PAD), 1), new WeightedRandomFishable(new ItemStack(Items.NAME_TAG), 1), new WeightedRandomFishable(new ItemStack(Items.SADDLE), 1), new WeightedRandomFishable(new ItemStack((Item)Items.BOW), 1).func_150709_a(0.25f).func_150707_a(), new WeightedRandomFishable(new ItemStack((Item)Items.FISHING_ROD), 1).func_150709_a(0.25f).func_150707_a(), new WeightedRandomFishable(new ItemStack(Items.BOOK), 1).func_150707_a()});
    private static final List field_146036_f = Arrays.asList(new WeightedRandomFishable[]{new WeightedRandomFishable(new ItemStack(Items.COD), 60), new WeightedRandomFishable(new ItemStack(Items.SALMON), 25), new WeightedRandomFishable(new ItemStack(Items.TROPICAL_FISH), 2), new WeightedRandomFishable(new ItemStack(Items.PUFFERFISH), 13)});
    private static final List chaospersists_lava_fish = Arrays.asList(new WeightedRandomFishable[]{new WeightedRandomFishable(new ItemStack(ChaosPersists.MySunspotUrchin), 25), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyLavaEel), 10), new WeightedRandomFishable(new ItemStack(ChaosPersists.MySunFish), 15), new WeightedRandomFishable(new ItemStack(ChaosPersists.MySparkFish), 10), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyFireFish), 15)});
    private static final List chaospersists_fish = Arrays.asList(new WeightedRandomFishable[]{new WeightedRandomFishable(new ItemStack(ChaosPersists.MyBlueFish), 25), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyPinkFish), 10), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyRockFish), 15), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyWoodFish), 10), new WeightedRandomFishable(new ItemStack(ChaosPersists.MyGreyFish), 15)});
    private int field_146037_g = -1;
    private int field_146048_h = -1;
    private int field_146050_i = -1;
    private Block field_146046_j;
    private boolean field_146051_au;
    private int field_146049_av;
    private int field_146047_aw;
    private int fish_on_hook;
    private int fish_wait_time;
    private int ticks_catchable;
    private float fish_direction;
    public Entity field_146043_c;
    private int field_146055_aB;
    private double field_146056_aC;
    private double field_146057_aD;
    private double field_146058_aE;
    private double field_146059_aF;
    private double field_146060_aG;
    @OnlyIn(Dist.CLIENT)
    private double field_146061_aH;
    @OnlyIn(Dist.CLIENT)
    private double field_146052_aI;
    @OnlyIn(Dist.CLIENT)
    private double field_146053_aJ;
    private int fishing_in_lava = 0;
    private int hookShake = 0;
    private java.util.UUID anglerUUID;

    @SuppressWarnings("unchecked")
    private static EntityType<? extends UltimateFishHook> hookType() {
        return (EntityType<? extends UltimateFishHook>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ultimate_fish_hook"));
    }

    public UltimateFishHook(EntityType<? extends UltimateFishHook> type, World par1World) {
        super(type, par1World);
        this.noCulling = true;
        PlayerEntity angler = resolveAnglerForSpawn(par1World);
        if (angler != null) {
            this.anglerUUID = angler.getUUID();
        }
    }

    public UltimateFishHook(World par1World) {
        this(hookType(), par1World);
    }

    private static PlayerEntity resolveAnglerForSpawn(World world) {
        if (world == null) {
            return null;
        }
        // Server side: use a FakePlayerEntity (ServerWorld only).
        if (!world.isClientSide && world instanceof ServerWorld) {
            return FakePlayerFactory.getMinecraft((ServerWorld)world);
        }
        // Client side: reflectively grab Minecraft.getInstance().player without hard-linking client classes.
        if (world.isClientSide) {
            try {
                Class<?> mcClass = Class.forName("net.minecraft.client.Minecraft");
                Object mc = mcClass.getMethod("getMinecraft").invoke(null);
                Object player = mcClass.getField("player").get(mc);
                if (player instanceof PlayerEntity) {
                    return (PlayerEntity)player;
                }
            } catch (Throwable ignored) {
            }
        }
        // Fallback: any player in the world.
        try {
            if (world.players() != null && !world.players().isEmpty()) {
                Object p = world.players().get(0);
                if (p instanceof PlayerEntity) {
                    return (PlayerEntity)p;
                }
            }
        } catch (Throwable ignored) {
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public UltimateFishHook(World par1World, double par2, double par4, double par6, PlayerEntity par8PlayerEntity) {
        this(hookType(), par1World);
        this.setPos(par2, par4, par6);
        this.noCulling = true;
        this.anglerUUID = par8PlayerEntity.getUUID();
    }

    public UltimateFishHook(World par1World, PlayerEntity par2PlayerEntity) {
        this(hookType(), par1World);
        this.noCulling = true;
        this.anglerUUID = par2PlayerEntity.getUUID();
        double eyeY = par2PlayerEntity.getY() + (double) par2PlayerEntity.getEyeHeight() - 0.1D;
        this.moveTo(par2PlayerEntity.getX(), eyeY, par2PlayerEntity.getZ(), par2PlayerEntity.yRot, par2PlayerEntity.xRot);
        double px = this.getX() - (double)(MathHelper.cos((float)(this.yRot / 180.0f * 3.1415927f)) * 0.16f);
        double py = this.getY() - 0.10000000149011612;
        double pz = this.getZ() - (double)(MathHelper.sin((float)(this.yRot / 180.0f * 3.1415927f)) * 0.16f);
        this.setPos(px, py, pz);
        float f = 0.4f;
        double mx = (- MathHelper.sin((float)(this.yRot / 180.0f * 3.1415927f))) * MathHelper.cos((float)(this.xRot / 180.0f * 3.1415927f)) * f;
        double mz = MathHelper.cos((float)(this.yRot / 180.0f * 3.1415927f)) * MathHelper.cos((float)(this.xRot / 180.0f * 3.1415927f)) * f;
        double my = (- MathHelper.sin((float)(this.xRot / 180.0f * 3.1415927f))) * f;
        this.func_146035_c(mx, my, mz, 1.5f, 1.0f);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return new SSpawnObjectPacket(this);
    }

    public PlayerEntity getAngler() {
        if (this.level == null || this.anglerUUID == null) {
            return null;
        }
        return this.level.getPlayerByUUID(this.anglerUUID);
    }

    @Override
    protected void defineSynchedData() {
    }

    private static ItemStack getUltimateRodStack(PlayerEntity player) {
        if (player == null) {
            return ItemStack.EMPTY;
        }
        ItemStack main = player.getMainHandItem();
        if (!main.isEmpty() && main.getItem() == ChaosPersists.MyUltimateFishingRod) {
            return main;
        }
        ItemStack off = player.getOffhandItem();
        if (!off.isEmpty() && off.getItem() == ChaosPersists.MyUltimateFishingRod) {
            return off;
        }
        return ItemStack.EMPTY;
    }

    private static boolean isHoldingUltimateRod(PlayerEntity player) {
        return !getUltimateRodStack(player).isEmpty();
    }

    public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_) {
        float f2 = MathHelper.sqrt((double)(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_));
        p_146035_1_ /= (double)f2;
        p_146035_3_ /= (double)f2;
        p_146035_5_ /= (double)f2;
        p_146035_1_ += this.random.nextGaussian() * 0.007499999832361937 * (double)p_146035_8_;
        p_146035_3_ += this.random.nextGaussian() * 0.007499999832361937 * (double)p_146035_8_;
        p_146035_5_ += this.random.nextGaussian() * 0.007499999832361937 * (double)p_146035_8_;
        p_146035_1_ *= (double)p_146035_7_;
        p_146035_3_ *= (double)p_146035_7_;
        p_146035_5_ *= (double)p_146035_7_;
        this.setDeltaMovement(p_146035_1_, p_146035_3_, p_146035_5_);
        float f3 = MathHelper.sqrt((double)(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_));
        this.yRotO = this.yRot = (float)(Math.atan2(p_146035_1_, p_146035_5_) * 180.0 / 3.141592653589793);
        this.xRotO = this.xRot = (float)(Math.atan2(p_146035_3_, f3) * 180.0 / 3.141592653589793);
        this.field_146049_av = 0;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double par1) {
        AxisAlignedBB bb = this.getBoundingBox();
        double d1 = (bb.getXsize() + bb.getYsize() + bb.getZsize()) / 3.0 * 4.0;
        return par1 < (d1 *= 64.0) * d1;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.field_146056_aC = par1;
        this.field_146057_aD = par3;
        this.field_146058_aE = par5;
        this.field_146059_aF = par7;
        this.field_146060_aG = par8;
        this.field_146055_aB = par9;
        this.setDeltaMovement(this.field_146061_aH, this.getDeltaMovement().y, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.field_146052_aI, this.getDeltaMovement().z);
        this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, this.field_146053_aJ);
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.field_146061_aH = par1;
        this.field_146052_aI = par3;
        this.field_146053_aJ = par5;
        this.setDeltaMovement(par1, par3, par5);
    }

    @Override
    public void tick() {
        if (this.field_146055_aB > 0) {
            double d7 = this.getX() + (this.field_146056_aC - this.getX()) / (double)this.field_146055_aB;
            double d8 = this.getY() + (this.field_146057_aD - this.getY()) / (double)this.field_146055_aB;
            double d9 = this.getZ() + (this.field_146058_aE - this.getZ()) / (double)this.field_146055_aB;
            double d1 = MathHelper.wrapDegrees((double)(this.field_146059_aF - (double)this.yRot));
            this.yRot = (float)((double)this.yRot + d1 / (double)this.field_146055_aB);
            this.xRot = (float)((double)this.xRot + (this.field_146060_aG - (double)this.xRot) / (double)this.field_146055_aB);
            --this.field_146055_aB;
            this.setPos(d7, d8, d9);
            this.setRot(this.xRot, this.yRot);
        } else {
            double d2;
            if (!this.level.isClientSide) {
                PlayerEntity angler = this.getAngler();
                if (angler == null || !angler.isAlive() || !isHoldingUltimateRod(angler)
                        || this.distanceToSqr((Entity) angler) > 1024.0) {
                    this.remove();
                    return;
                }
                if (this.field_146043_c != null) {
                    if (this.field_146043_c.isAlive()) {
                        this.setPos(this.field_146043_c.getX(), this.field_146043_c.getBoundingBox().minY + (double)this.field_146043_c.getBbHeight() * 0.8, this.field_146043_c.getZ());
                        return;
                    }
                    this.field_146043_c = null;
                }
            }
            if (this.hookShake > 0) {
                --this.hookShake;
            }
            if (this.field_146051_au) {
                if (this.level.getBlockState(new net.minecraft.util.math.BlockPos(this.field_146037_g, this.field_146048_h, this.field_146050_i)).getBlock() == this.field_146046_j) {
                    ++this.field_146049_av;
                    if (this.field_146049_av == 1200) {
                        this.remove();
                    }
                    return;
                }
                this.field_146051_au = false;
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, (double)(this.random.nextFloat() * 0.2f), (double)(this.random.nextFloat() * 0.2f), (double)(this.random.nextFloat() * 0.2f));
                this.field_146049_av = 0;
                this.field_146047_aw = 0;
            } else {
                ++this.field_146047_aw;
            }
            net.minecraft.util.math.vector.Vector3d vec31 = new Vector3d((double)this.getX(), (double)this.getY(), (double)this.getZ());
            net.minecraft.util.math.vector.Vector3d vec32 = new Vector3d((double)(this.getX() + this.getDeltaMovement().x), (double)(this.getY() + this.getDeltaMovement().y), (double)(this.getZ() + this.getDeltaMovement().z));
            RayTraceResult RayTraceResult = this.level.clip(new net.minecraft.util.math.RayTraceContext(vec31, vec32, net.minecraft.util.math.RayTraceContext.BlockMode.COLLIDER, net.minecraft.util.math.RayTraceContext.FluidMode.NONE, this));
            vec31 = new Vector3d((double)this.getX(), (double)this.getY(), (double)this.getZ());
            vec32 = new Vector3d((double)(this.getX() + this.getDeltaMovement().x), (double)(this.getY() + this.getDeltaMovement().y), (double)(this.getZ() + this.getDeltaMovement().z));
            if (RayTraceResult != null) {
                vec32 = RayTraceResult.getLocation();
            }
            Entity entity = null;
            List list = this.level.getEntities(this, this.getBoundingBox().inflate(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z).inflate(1.0, 1.0, 1.0));
            double d0 = 0.0;
            for (int i = 0; i < list.size(); ++i) {
                java.util.Optional<Vector3d> entityHitVec;
                float f;
                AxisAlignedBB axisalignedbb;
                Entity entity1 = (Entity)list.get(i);
                if (!entity1.canBeCollidedWith() || entity1 == this.getAngler() && this.field_146047_aw < 5 || !(entityHitVec = (axisalignedbb = entity1.getBoundingBox().inflate((double)(f = 0.3f), (double)f, (double)f)).clip(vec31, vec32)).isPresent() || (d2 = vec31.distanceTo(entityHitVec.get())) >= d0 && d0 != 0.0) continue;
                entity = entity1;
                d0 = d2;
            }
            if (entity != null) {
                RayTraceResult = new net.minecraft.util.math.EntityRayTraceResult(entity);
            }
            if (RayTraceResult != null) {
                if (RayTraceResult instanceof net.minecraft.util.math.EntityRayTraceResult) {
                    Entity hit = ((net.minecraft.util.math.EntityRayTraceResult)RayTraceResult).getEntity();
                    if (hit.hurt(DamageSource.indirectMagic(this, this.getAngler()), 0.0f)) {
                        this.field_146043_c = hit;
                    }
                } else {
                    this.field_146051_au = true;
                }
            }
            if (!this.field_146051_au) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.move(MoverType.SELF, this.getDeltaMovement());
                net.minecraft.util.math.vector.Vector3d moved = this.getDeltaMovement();
                this.setDeltaMovement(moved.x, this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, moved.y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, moved.z);
                float f5 = MathHelper.sqrt((double)(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z));
                this.yRot = (float)(Math.atan2(this.getDeltaMovement().x, this.getDeltaMovement().z) * 180.0 / 3.141592653589793);
                this.xRot = (float)(Math.atan2(this.getDeltaMovement().y, f5) * 180.0 / 3.141592653589793);
                while (this.xRot - this.xRotO < -180.0f) {
                    this.xRotO -= 360.0f;
                }
                while (this.xRot - this.xRotO >= 180.0f) {
                    this.xRotO += 360.0f;
                }
                while (this.yRot - this.yRotO < -180.0f) {
                    this.yRotO -= 360.0f;
                }
                while (this.yRot - this.yRotO >= 180.0f) {
                    this.yRotO += 360.0f;
                }
                this.xRot = this.xRotO + (this.xRot - this.xRotO) * 0.2f;
                this.yRot = this.yRotO + (this.yRot - this.yRotO) * 0.2f;
                float f6 = 0.92f;
                if (this.onGround || this.horizontalCollision) {
                    f6 = 0.5f;
                }
                int b0 = 5;
                double d10 = 0.0;
                for (int j = 0; j < b0; ++j) {
                    double d3 = this.getBoundingBox().minY + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (double)(j + 0) / (double)b0 - 0.125 + 0.125;
                    double d4 = this.getBoundingBox().minY + (this.getBoundingBox().maxY - this.getBoundingBox().minY) * (double)(j + 1) / (double)b0 - 0.125 + 0.125;
                    AxisAlignedBB axisalignedbb1 = new AxisAlignedBB(this.getBoundingBox().minX, d3, this.getBoundingBox().minZ, this.getBoundingBox().maxX, d4, this.getBoundingBox().maxZ);
                    if (net.minecraft.util.math.BlockPos.betweenClosedStream(axisalignedbb1).anyMatch(p -> this.level.getFluidState(p).is(net.minecraft.tags.FluidTags.WATER))) {
                        d10 += 1.0 / (double)b0;
                    }
                    if (!net.minecraft.util.math.BlockPos.betweenClosedStream(axisalignedbb1).anyMatch(p -> this.level.getFluidState(p).is(net.minecraft.tags.FluidTags.LAVA))) continue;
                    d10 += 1.0 / (double)b0;
                }
                if (!this.level.isClientSide && d10 > 0.0) {
                    ServerWorld worldserver = (ServerWorld)this.level;
                    int k = 1;
                    net.minecraft.util.math.BlockPos pos = new net.minecraft.util.math.BlockPos(MathHelper.floor(this.getX()), MathHelper.floor(this.getY()) + 1, MathHelper.floor(this.getZ()));
                    if (this.random.nextFloat() < 0.25f && this.level.isRainingAt(pos)) {
                        k = 2;
                    }
                    if (this.random.nextFloat() < 0.5f && !this.level.canSeeSky(pos)) {
                        --k;
                    }
                    if (this.fish_on_hook > 0) {
                        --this.fish_on_hook;
                        if (this.fish_on_hook <= 0) {
                            this.fish_wait_time = 0;
                            this.ticks_catchable = 0;
                        }
                    } else if (this.ticks_catchable > 0) {
                        this.ticks_catchable -= k;
                        if (this.ticks_catchable <= 0) {
                            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.20000000298023224), 0.0);
                            this.playSound(SoundEvents.BOAT_PADDLE_WATER, 0.25f, 1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.4f);
                            float f1 = (float)MathHelper.floor(this.getBoundingBox().minY);
                            worldserver.sendParticles(ParticleTypes.BUBBLE, this.getX(), (double)(f1 + 1.0f), this.getZ(), (int)(1.0f + this.getBbWidth() * 20.0f), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                            worldserver.sendParticles(ParticleTypes.FISHING, this.getX(), (double)(f1 + 1.0f), this.getZ(), (int)(1.0f + this.getBbWidth() * 20.0f), (double)this.getBbWidth(), 0.0, (double)this.getBbWidth(), 0.20000000298023224);
                            this.fish_on_hook = this.random.nextInt(21) + 10;
                        } else {
                            this.fish_direction = (float)((double)this.fish_direction + this.random.nextGaussian() * 4.0);
                            float f1 = this.fish_direction * 0.017453292f;
                            float f7 = MathHelper.sin((float)f1);
                            float f2 = MathHelper.cos((float)f1);
                            double d11 = this.getX() + (double)(f7 * (float)this.ticks_catchable * 0.1f);
                            double d5 = (float)MathHelper.floor((double)this.getBoundingBox().minY) + 1.0f;
                            double d6 = this.getZ() + (double)(f2 * (float)this.ticks_catchable * 0.1f);
                            if (this.random.nextFloat() < 0.15f) {
                                worldserver.sendParticles(ParticleTypes.BUBBLE, d11, d5 - 0.10000000149011612, d6, 1, (double)f7, 0.1, (double)f2, 0.0);
                            }
                            float f3 = f7 * 0.04f;
                            float f4 = f2 * 0.04f;
                            worldserver.sendParticles(ParticleTypes.FISHING, d11, d5, d6, 0, (double)f4, 0.01, (double)(- f3), 1.0);
                            worldserver.sendParticles(ParticleTypes.FISHING, d11, d5, d6, 0, (double)(- f4), 0.01, (double)f3, 1.0);
                        }
                    } else if (this.fish_wait_time > 0) {
                        this.fish_wait_time -= k;
                        float f1 = 0.15f;
                        if (this.fish_wait_time < 20) {
                            f1 = (float)((double)f1 + (double)(20 - this.fish_wait_time) * 0.05);
                        } else if (this.fish_wait_time < 40) {
                            f1 = (float)((double)f1 + (double)(40 - this.fish_wait_time) * 0.02);
                        } else if (this.fish_wait_time < 60) {
                            f1 = (float)((double)f1 + (double)(60 - this.fish_wait_time) * 0.01);
                        }
                        if (this.random.nextFloat() < f1) {
                            float f7 = (this.random.nextFloat() * 360.0f) * 0.017453292f;
                            float f2 = this.random.nextFloat() * 35.0f + 25.0f;
                            double d11 = this.getX() + (double)(MathHelper.sin((float)f7) * f2 * 0.1f);
                            double d5 = (float)MathHelper.floor((double)this.getBoundingBox().minY) + 1.0f;
                            double d6 = this.getZ() + (double)(MathHelper.cos((float)f7) * f2 * 0.1f);
                            worldserver.sendParticles(ParticleTypes.SPLASH, d11, d5, d6, 2 + this.random.nextInt(2), 0.10000000149011612, 0.0, 0.10000000149011612, 0.0);
                        }
                        if (this.fish_wait_time <= 0) {
                            this.fish_direction = this.random.nextFloat() * 360.0f;
                            this.ticks_catchable = this.random.nextInt(101) + 100;
                        }
                    } else {
                        this.fish_wait_time = this.random.nextInt(251) + 50;
                        this.fish_wait_time -= EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.FISHING_SPEED, getUltimateRodStack(this.getAngler())) * 20 * 5;
                    }
                    if (this.fish_on_hook > 0) {
                        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -((double)(this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2), 0.0);
                    }
                }
                d2 = d10 * 2.0 - 1.0;
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.03999999910593033 * d2, 0.0);
                if (d10 > 0.0) {
                    f6 = (float)((double)f6 * 0.9);
                    com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 1.0, 0.8, 1.0);
                }
                com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, (double)f6, (double)f6, (double)f6);
                this.setPos(this.getX(), this.getY(), this.getZ());
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        par1CompoundNBT.putShort("xTile", (short)this.field_146037_g);
        par1CompoundNBT.putShort("yTile", (short)this.field_146048_h);
        par1CompoundNBT.putShort("zTile", (short)this.field_146050_i);
        if (this.field_146046_j != null) {
            par1CompoundNBT.putString("inTile", ForgeRegistries.BLOCKS.getKey(this.field_146046_j).toString());
        }
        par1CompoundNBT.putByte("shake", (byte)this.hookShake);
        par1CompoundNBT.putByte("inGround", (byte)(this.field_146051_au ? 1 : 0));
    }

    @Override
    protected void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        this.field_146037_g = par1CompoundNBT.getShort("xTile");
        this.field_146048_h = par1CompoundNBT.getShort("yTile");
        this.field_146050_i = par1CompoundNBT.getShort("zTile");
        if (par1CompoundNBT.contains("inTile")) {
            this.field_146046_j = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(par1CompoundNBT.getString("inTile")));
        }
        this.hookShake = par1CompoundNBT.getByte("shake") & 255;
        this.field_146051_au = par1CompoundNBT.getByte("inGround") == 1;
    }

    @OnlyIn(Dist.CLIENT)
    public float getShadowSize() {
        return 0.0f;
    }

    public int handleHookRetraction() {
        if (this.level.isClientSide) {
            return 0;
        }
        int b0 = 0;
        if (this.field_146043_c != null) {
            double d0 = this.getAngler().getX() - this.getX();
            double d2 = this.getAngler().getY() - this.getY();
            double d4 = this.getAngler().getZ() - this.getZ();
            double d6 = MathHelper.sqrt((double)(d0 * d0 + d2 * d2 + d4 * d4));
            double d8 = 0.1;
            net.minecraft.util.math.vector.Vector3d hookedMotion = this.field_146043_c.getDeltaMovement();
            this.field_146043_c.setDeltaMovement(hookedMotion.x + d0 * d8, hookedMotion.y + d2 * d8 + (double)MathHelper.sqrt((double)d6) * 0.08, hookedMotion.z + d4 * d8);
            b0 = 3;
        } else if (this.fish_on_hook > 0) {
            ItemEntity entityitem = new ItemEntity(this.level, this.getX(), this.getY() + 1.25, this.getZ(), this.func_146033_f());
            double d1 = this.getAngler().getX() - this.getX();
            double d3 = this.getAngler().getY() - this.getY();
            double d5 = this.getAngler().getZ() - this.getZ();
            double d7 = MathHelper.sqrt((double)(d1 * d1 + d3 * d3 + d5 * d5));
            double d9 = 0.1;
            entityitem.setDeltaMovement(d1 * d9, d3 * d9 + (double)MathHelper.sqrt((double)d7) * 0.08, d5 * d9);
            entityitem.setNoPickUpDelay();
            this.level.addFreshEntity((Entity)entityitem);
            this.getAngler().level.addFreshEntity(new ExperienceOrbEntity(this.getAngler().level, this.getAngler().getX(), this.getAngler().getY() + 0.5, this.getAngler().getZ() + 0.5, this.random.nextInt(6) + 1));
            b0 = 1;
        }
        if (this.field_146051_au) {
            b0 = 2;
        }
        this.remove();
        return b0;
    }

    private ItemStack func_146033_f() {
        float f = this.random.nextFloat();
        int i = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.FISHING_LUCK, getUltimateRodStack(this.getAngler()));
        int j = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.FISHING_SPEED, getUltimateRodStack(this.getAngler()));
        float f1 = 0.1f - (float)i * 0.025f - (float)j * 0.01f;
        float f2 = 0.05f + (float)i * 0.01f - (float)j * 0.01f;
        f1 = MathHelper.clamp((float)f1, (float)0.0f, (float)1.0f);
        f2 = MathHelper.clamp((float)f2, (float)0.0f, (float)1.0f);
        Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ())).getBlock();
        if (this.isInLava() || bid == Blocks.LAVA) {
            this.getAngler().awardStat(Stats.FISH_CAUGHT, 1);
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.random, chaospersists_lava_fish)).getItemStack(this.random);
        }
        if (f < f1) {
            this.getAngler().awardStat(Stats.FISH_CAUGHT, 1);
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.random, field_146039_d)).getItemStack(this.random);
        }
        if ((f -= f1) < f2) {
            this.getAngler().awardStat(Stats.FISH_CAUGHT, 1);
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.random, field_146041_e)).getItemStack(this.random);
        }
        float f3 = this.level.random.nextFloat();
        this.getAngler().awardStat(Stats.FISH_CAUGHT, 1);
        if (f3 < 0.5f) {
            return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.random, field_146036_f)).getItemStack(this.random);
        }
        return ((WeightedRandomFishable)WeightedRandom.getRandomItem(this.random, chaospersists_fish)).getItemStack(this.random);
    }

    public void setDead() {
        this.remove();
    }

    public static UltimateFishHook getHookForPlayer(PlayerEntity player) {
        if (player == null || player.level == null) {
            return null;
        }
        java.util.List<UltimateFishHook> hooks = player.level.getEntitiesOfClass(UltimateFishHook.class, player.getBoundingBox().inflate(64.0));
        for (UltimateFishHook hook : hooks) {
            if (hook.getAngler() == player && hook.isAlive()) {
                return hook;
            }
        }
        return null;
    }
}

