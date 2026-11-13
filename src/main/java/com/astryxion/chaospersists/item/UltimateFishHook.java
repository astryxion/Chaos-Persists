package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.WeightedRandomFishable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.util.FakePlayerFactory;

/**
 * Ultimate fishing bobber: 1.12.2 EntityFishHook logic ported to Forge 1.20.x / official mappings.
 * Extends {@link FishingHook} so {@link Player#fishing} and fishing-rod interaction stay compatible.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
public class UltimateFishHook extends FishingHook {

    private static final List<WeightedRandomFishable> field_146039_d =
            Arrays.asList(
                    new WeightedRandomFishable(new ItemStack(Items.LEATHER_BOOTS), 10).func_150709_a(0.9f),
                    new WeightedRandomFishable(new ItemStack(Items.LEATHER), 10),
                    new WeightedRandomFishable(new ItemStack(Items.BONE), 10),
                    new WeightedRandomFishable(new ItemStack(Items.POTION), 10),
                    new WeightedRandomFishable(new ItemStack(Items.STRING), 5),
                    new WeightedRandomFishable(new ItemStack(Items.FISHING_ROD), 2).func_150709_a(0.9f),
                    new WeightedRandomFishable(new ItemStack(Items.BOWL), 10),
                    new WeightedRandomFishable(new ItemStack(Items.STICK), 5),
                    new WeightedRandomFishable(new ItemStack(Items.INK_SAC, 10), 1),
                    new WeightedRandomFishable(new ItemStack(Items.TRIPWIRE_HOOK), 10),
                    new WeightedRandomFishable(new ItemStack(Items.ROTTEN_FLESH), 10));
    private static final List<WeightedRandomFishable> field_146041_e =
            Arrays.asList(
                    new WeightedRandomFishable(new ItemStack(Items.LILY_PAD), 1),
                    new WeightedRandomFishable(new ItemStack(Items.NAME_TAG), 1),
                    new WeightedRandomFishable(new ItemStack(Items.SADDLE), 1),
                    new WeightedRandomFishable(new ItemStack(Items.BOW), 1).func_150709_a(0.25f).func_150707_a(),
                    new WeightedRandomFishable(new ItemStack(Items.FISHING_ROD), 1).func_150709_a(0.25f).func_150707_a(),
                    new WeightedRandomFishable(new ItemStack(Items.BOOK), 1).func_150707_a());
    private static final List<WeightedRandomFishable> field_146036_f =
            Arrays.asList(
                    new WeightedRandomFishable(new ItemStack(Items.COD), 60),
                    new WeightedRandomFishable(new ItemStack(Items.SALMON), 25),
                    new WeightedRandomFishable(new ItemStack(Items.TROPICAL_FISH), 2),
                    new WeightedRandomFishable(new ItemStack(Items.PUFFERFISH), 13));
    private static final List<WeightedRandomFishable> chaospersists_lava_fish =
            Arrays.asList(
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MySunspotUrchin), 25),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyLavaEel), 10),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MySunFish), 15),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MySparkFish), 10),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyFireFish), 15));
    private static final List<WeightedRandomFishable> chaospersists_fish =
            Arrays.asList(
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyBlueFish), 25),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyPinkFish), 10),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyRockFish), 15),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyWoodFish), 10),
                    new WeightedRandomFishable(new ItemStack(ChaosPersists.MyGreyFish), 15));

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

    @SuppressWarnings("unused")
    private int fishing_in_lava = 0;

    private int hookShake = 0;

    /** Spawn-packet / registry constructor: resolves a safe angler so {@link FishingHook} invariants hold. */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public UltimateFishHook(EntityType<? extends UltimateFishHook> entityType, Level level) {
        super((EntityType) entityType, level);
        this.applyHookInit();
        Player angler = resolveAnglerForSpawn(level);
        if (angler != null) {
            this.setOwner(angler);
        }
    }

    private void applyHookInit() {
        this.setDeltaMovement(Vec3.ZERO);
        this.noCulling = true;
    }

    private static Player resolveAnglerForSpawn(Level level) {
        if (level == null) {
            return null;
        }
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            return FakePlayerFactory.getMinecraft(serverLevel);
        }
        if (level.isClientSide) {
            try {
                Class<?> mcClass = Class.forName("net.minecraft.client.Minecraft");
                Object playerObj = mcClass.getMethod("getInstance").invoke(null);
                Object player = mcClass.getField("player").get(playerObj);
                if (player instanceof Player) {
                    return (Player) player;
                }
            } catch (Throwable ignored) {
            }
        }
        List<? extends Player> players = level.players();
        if (!players.isEmpty()) {
            return players.get(0);
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public UltimateFishHook(Level level, double x, double y, double z, Player player) {
        this(ChaosPersists.ENTITY_TYPE_ULTIMATE_FISH_HOOK.get(), level);
        this.setOwner(player);
        this.setPos(x, y, z);
        this.applyHookInit();
        player.fishing = this;
    }

    public UltimateFishHook(Level level, Player player) {
        this(ChaosPersists.ENTITY_TYPE_ULTIMATE_FISH_HOOK.get(), level);
        this.setOwner(player);
        this.applyHookInit();
        player.fishing = this;
        double eyeY = player.getY() + (double) player.getEyeHeight() - 0.1D;
        this.setPos(player.getX(), eyeY, player.getZ());
        this.setYRot(player.getYRot());
        this.setXRot(player.getXRot());
        double px = this.getX() - (double) (Mth.cos((float) (this.getYRot() / 180.0f * (float) Math.PI)) * 0.16f);
        double py = this.getY() - 0.10000000149011612D;
        double pz = this.getZ() - (double) (Mth.sin((float) (this.getYRot() / 180.0f * (float) Math.PI)) * 0.16f);
        this.setPos(px, py, pz);
        float f = 0.4f;
        double mx =
                (-Mth.sin((float) (this.getYRot() / 180.0f * (float) Math.PI)))
                        * Mth.cos((float) (this.getXRot() / 180.0f * (float) Math.PI))
                        * f;
        double mz =
                Mth.cos((float) (this.getYRot() / 180.0f * (float) Math.PI))
                        * Mth.cos((float) (this.getXRot() / 180.0f * (float) Math.PI))
                        * f;
        double my = (-Mth.sin((float) (this.getXRot() / 180.0f * (float) Math.PI))) * f;
        this.func_146035_c(mx, my, mz, 1.5f, 1.0f);
    }

    private static ItemStack getUltimateRodStack(Player player) {
        if (player == null) {
            return ItemStack.EMPTY;
        }
        ItemStack main = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (!main.isEmpty() && main.getItem() == ChaosPersists.MyUltimateFishingRod) {
            return main;
        }
        ItemStack off = player.getItemInHand(InteractionHand.OFF_HAND);
        if (!off.isEmpty() && off.getItem() == ChaosPersists.MyUltimateFishingRod) {
            return off;
        }
        return ItemStack.EMPTY;
    }

    private static boolean isHoldingUltimateRod(Player player) {
        return !getUltimateRodStack(player).isEmpty();
    }

    /**
     * Same normalization / Gaussian spread as 1.12 {@code EntityFishHook.func_146035_c} (shoot helper).
     */
    public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_) {
        float f2 = Mth.sqrt((float) (p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_));
        p_146035_1_ /= (double) f2;
        p_146035_3_ /= (double) f2;
        p_146035_5_ /= (double) f2;
        p_146035_1_ += this.random.nextGaussian() * 0.007499999832361937D * (double) p_146035_8_;
        p_146035_3_ += this.random.nextGaussian() * 0.007499999832361937D * (double) p_146035_8_;
        p_146035_5_ += this.random.nextGaussian() * 0.007499999832361937D * (double) p_146035_8_;
        p_146035_1_ *= (double) p_146035_7_;
        p_146035_3_ *= (double) p_146035_7_;
        p_146035_5_ *= (double) p_146035_7_;
        this.setDeltaMovement(new Vec3(p_146035_1_, p_146035_3_, p_146035_5_));
        float f3 = Mth.sqrt((float) (p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_));
        float yawDeg = (float) (Mth.atan2(p_146035_1_, p_146035_5_) * 180.0D / (float) Math.PI);
        float pitchDeg = (float) (Mth.atan2(p_146035_3_, (double) f3) * 180.0D / (float) Math.PI);
        this.yRotO = yawDeg;
        this.xRotO = pitchDeg;
        this.setYRot(yawDeg);
        this.setXRot(pitchDeg);
        this.field_146049_av = 0;
    }

    /** Renderer compatibility: same name as 1.12 angler accessor. */
    public Player getAngler() {
        return this.getPlayerOwner();
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldRenderAtSqrDistance(double par1) {
        double d1 = (double) this.getBbWidth() * 4.0D;
        d1 *= 64.0D;
        return par1 < d1 * d1;
    }

    /** Ported from 1.12 client {@code setPositionAndRotation2} (velocity snapshot for interpolation). */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.field_146056_aC = par1;
        this.field_146057_aD = par3;
        this.field_146058_aE = par5;
        this.field_146059_aF = par7;
        this.field_146060_aG = par8;
        this.field_146055_aB = par9;
        this.setDeltaMovement(new Vec3(this.field_146061_aH, this.field_146052_aI, this.field_146053_aJ));
    }

    @Override
    public void setDeltaMovement(Vec3 motion) {
        if (this.level().isClientSide) {
            this.field_146061_aH = motion.x;
            this.field_146052_aI = motion.y;
            this.field_146053_aJ = motion.z;
        }
        super.setDeltaMovement(motion);
    }

    @Override
    public void tick() {
        if (this.field_146055_aB > 0) {
            double d7 = this.getX() + (this.field_146056_aC - this.getX()) / (double) this.field_146055_aB;
            double d8 = this.getY() + (this.field_146057_aD - this.getY()) / (double) this.field_146055_aB;
            double d9 = this.getZ() + (this.field_146058_aE - this.getZ()) / (double) this.field_146055_aB;
            double d1 = Mth.wrapDegrees(this.field_146059_aF - (double) this.getYRot());
            this.setYRot((float) ((double) this.getYRot() + d1 / (double) this.field_146055_aB));
            this.setXRot(
                    (float) ((double) this.getXRot() + (this.field_146060_aG - (double) this.getXRot()) / (double) this.field_146055_aB));
            --this.field_146055_aB;
            this.setPos(d7, d8, d9);
            this.setYRot(this.getYRot());
            this.setXRot(this.getXRot());
            return;
        }

        Level level = this.level();
        double d2;
        if (!level.isClientSide) {
            Player angler = this.getAngler();
            if (angler == null
                    || angler.isRemoved()
                    || !angler.isAlive()
                    || !isHoldingUltimateRod(angler)
                    || this.distanceToSqr(angler) > 1024.0D) {
                this.discard();
                if (angler != null && angler.fishing == this) {
                    angler.fishing = null;
                }
                return;
            }
            if (this.field_146043_c != null) {
                if (!this.field_146043_c.isRemoved()) {
                    this.setPos(
                            this.field_146043_c.getX(),
                            this.field_146043_c.getBoundingBox().minY + (double) this.field_146043_c.getBbHeight() * 0.8D,
                            this.field_146043_c.getZ());
                    return;
                }
                this.field_146043_c = null;
            }
        }

        if (this.hookShake > 0) {
            --this.hookShake;
        }

        if (this.field_146051_au) {
            BlockPos stuckPos = new BlockPos(this.field_146037_g, this.field_146048_h, this.field_146050_i);
            if (level.getBlockState(stuckPos).getBlock() == this.field_146046_j) {
                ++this.field_146049_av;
                if (this.field_146049_av == 1200) {
                    this.discard();
                }
                return;
            }
            this.field_146051_au = false;
            Vec3 dm = this.getDeltaMovement();
            this.setDeltaMovement(
                    dm.x * (double) (this.random.nextFloat() * 0.2f),
                    dm.y * (double) (this.random.nextFloat() * 0.2f),
                    dm.z * (double) (this.random.nextFloat() * 0.2f));
            this.field_146049_av = 0;
            this.field_146047_aw = 0;
        } else {
            ++this.field_146047_aw;
        }

        double motionX = this.getDeltaMovement().x;
        double motionY = this.getDeltaMovement().y;
        double motionZ = this.getDeltaMovement().z;

        Vec3 vec31 = new Vec3(this.getX(), this.getY(), this.getZ());
        Vec3 vecTarget = new Vec3(this.getX() + motionX, this.getY() + motionY, this.getZ() + motionZ);
        HitResult blockHit =
                level.clip(new ClipContext(vec31, vecTarget, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        vec31 = new Vec3(this.getX(), this.getY(), this.getZ());
        vecTarget = new Vec3(this.getX() + motionX, this.getY() + motionY, this.getZ() + motionZ);
        if (blockHit.getType() != HitResult.Type.MISS) {
            vecTarget = blockHit.getLocation();
        }

        Entity entity = null;
        List<Entity> list =
                level.getEntities(
                        this,
                        this.getBoundingBox().expandTowards(motionX, motionY, motionZ).inflate(1.0D),
                        e -> true);
        double d0 = 0.0D;
        Player anglerForHit = this.getAngler();
        for (Entity entity1 : list) {
            if (!entity1.isPickable()
                    || entity1 == anglerForHit && this.field_146047_aw < 5) {
                continue;
            }
            float f = 0.3F;
            AABB axisalignedbb = entity1.getBoundingBox().inflate((double) f, (double) f, (double) f);
            Optional<Vec3> hitOpt = axisalignedbb.clip(vec31, vecTarget);
            if (hitOpt.isEmpty()) {
                continue;
            }
            d2 = vec31.distanceTo(hitOpt.get());
            if (d2 < d0 || d0 == 0.0D) {
                entity = entity1;
                d0 = d2;
            }
        }

        if (entity != null) {
            if (entity.hurt(this.damageSources().thrown(this, anglerForHit), 0.0F)) {
                this.field_146043_c = entity;
            }
        } else if (blockHit.getType() == HitResult.Type.BLOCK && blockHit instanceof net.minecraft.world.phys.BlockHitResult bhr) {
            BlockPos bp = bhr.getBlockPos();
            this.field_146037_g = bp.getX();
            this.field_146048_h = bp.getY();
            this.field_146050_i = bp.getZ();
            this.field_146046_j = level.getBlockState(bp).getBlock();
            this.field_146051_au = true;
        }

        if (!this.field_146051_au) {
            this.move(MoverType.SELF, new Vec3(motionX, motionY, motionZ));
            motionX = this.getDeltaMovement().x;
            motionY = this.getDeltaMovement().y;
            motionZ = this.getDeltaMovement().z;
            float f5 = Mth.sqrt((float) (motionX * motionX + motionZ * motionZ));
            this.setYRot((float) (Mth.atan2(motionX, motionZ) * 180.0D / (float) Math.PI));
            this.setXRot((float) (Mth.atan2(motionY, (double) f5) * 180.0D / (float) Math.PI));
            while (this.getXRot() - this.xRotO < -180.0f) {
                this.xRotO -= 360.0f;
            }
            while (this.getXRot() - this.xRotO >= 180.0f) {
                this.xRotO += 360.0f;
            }
            while (this.getYRot() - this.yRotO < -180.0f) {
                this.yRotO -= 360.0f;
            }
            while (this.getYRot() - this.yRotO >= 180.0f) {
                this.yRotO += 360.0f;
            }
            this.setXRot(this.xRotO + (this.getXRot() - this.xRotO) * 0.2f);
            this.setYRot(this.yRotO + (this.getYRot() - this.yRotO) * 0.2f);
            float f6 = 0.92f;
            if (this.onGround() || this.horizontalCollision) {
                f6 = 0.5f;
            }
            int b0 = 5;
            double d10 = 0.0D;
            AABB bb = this.getBoundingBox();
            for (int j = 0; j < b0; ++j) {
                double d3 =
                        bb.minY + (bb.maxY - bb.minY) * (double) (j + 0) / (double) b0 - 0.125D + 0.125D;
                double d4 =
                        bb.minY + (bb.maxY - bb.minY) * (double) (j + 1) / (double) b0 - 0.125D + 0.125D;
                double cx = (bb.minX + bb.maxX) * 0.5D;
                double cy = (d3 + d4) * 0.5D;
                double cz = (bb.minZ + bb.maxZ) * 0.5D;
                BlockPos fluidProbe = BlockPos.containing(cx, cy, cz);
                if (level.getFluidState(fluidProbe).is(FluidTags.WATER)) {
                    d10 += 1.0D / (double) b0;
                }
                if (level.getFluidState(fluidProbe).is(FluidTags.LAVA)) {
                    d10 += 1.0D / (double) b0;
                }
            }
            if (!level.isClientSide && d10 > 0.0D) {
                ServerLevel worldserver = (ServerLevel) level;
                int k = 1;
                BlockPos pos = new BlockPos(Mth.floor(this.getX()), Mth.floor(this.getY()) + 1, Mth.floor(this.getZ()));
                if (this.random.nextFloat() < 0.25f && level.isRainingAt(pos)) {
                    k = 2;
                }
                if (this.random.nextFloat() < 0.5f && !level.canSeeSky(pos)) {
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
                        motionY -= 0.20000000298023224D;
                        this.playSound(
                                SoundEvents.BOAT_PADDLE_WATER,
                                0.25f,
                                1.0f + (this.random.nextFloat() - this.random.nextFloat()) * 0.4f);
                        float f1 = (float) Mth.floor(this.getBoundingBox().minY);
                        worldserver.sendParticles(
                                ParticleTypes.BUBBLE,
                                this.getX(),
                                (double) (f1 + 1.0f),
                                this.getZ(),
                                (int) (1.0f + this.getBbWidth() * 20.0f),
                                (double) this.getBbWidth(),
                                0.0D,
                                (double) this.getBbWidth(),
                                0.20000000298023224D);
                        worldserver.sendParticles(
                                ParticleTypes.FISHING,
                                this.getX(),
                                (double) (f1 + 1.0f),
                                this.getZ(),
                                (int) (1.0f + this.getBbWidth() * 20.0f),
                                (double) this.getBbWidth(),
                                0.0D,
                                (double) this.getBbWidth(),
                                0.20000000298023224D);
                        this.fish_on_hook = this.random.nextInt(21) + 10;
                    } else {
                        this.fish_direction = (float) ((double) this.fish_direction + this.random.nextGaussian() * 4.0D);
                        float f1b = this.fish_direction * 0.017453292f;
                        float f7 = Mth.sin(f1b);
                        float f2 = Mth.cos(f1b);
                        double d11 = this.getX() + (double) (f7 * (float) this.ticks_catchable * 0.1f);
                        double d5 = (float) Mth.floor(this.getBoundingBox().minY) + 1.0f;
                        double d6 = this.getZ() + (double) (f2 * (float) this.ticks_catchable * 0.1f);
                        if (this.random.nextFloat() < 0.15f) {
                            worldserver.sendParticles(
                                    ParticleTypes.BUBBLE,
                                    d11,
                                    d5 - 0.10000000149011612D,
                                    d6,
                                    1,
                                    (double) f7,
                                    0.1D,
                                    (double) f2,
                                    0.0D);
                        }
                        float f3 = f7 * 0.04f;
                        float f4 = f2 * 0.04f;
                        worldserver.sendParticles(
                                ParticleTypes.FISHING, d11, d5, d6, 0, (double) f4, 0.01D, (double) (-f3), 1.0D);
                        worldserver.sendParticles(
                                ParticleTypes.FISHING, d11, d5, d6, 0, (double) (-f4), 0.01D, (double) f3, 1.0D);
                    }
                } else if (this.fish_wait_time > 0) {
                    this.fish_wait_time -= k;
                    float f1w = 0.15f;
                    if (this.fish_wait_time < 20) {
                        f1w = (float) ((double) f1w + (double) (20 - this.fish_wait_time) * 0.05D);
                    } else if (this.fish_wait_time < 40) {
                        f1w = (float) ((double) f1w + (double) (40 - this.fish_wait_time) * 0.02D);
                    } else if (this.fish_wait_time < 60) {
                        f1w = (float) ((double) f1w + (double) (60 - this.fish_wait_time) * 0.01D);
                    }
                    if (this.random.nextFloat() < f1w) {
                        float f7s = (this.random.nextFloat() * 360.0f) * 0.017453292f;
                        float f2s = this.random.nextFloat() * 35.0f + 25.0f;
                        double d11s = this.getX() + (double) (Mth.sin(f7s) * f2s * 0.1f);
                        double d5s = (float) Mth.floor(this.getBoundingBox().minY) + 1.0f;
                        double d6s = this.getZ() + (double) (Mth.cos(f7s) * f2s * 0.1f);
                        worldserver.sendParticles(
                                ParticleTypes.SPLASH,
                                d11s,
                                d5s,
                                d6s,
                                2 + this.random.nextInt(2),
                                0.10000000149011612D,
                                0.0D,
                                0.10000000149011612D,
                                0.0D);
                    }
                    if (this.fish_wait_time <= 0) {
                        this.fish_direction = this.random.nextFloat() * 360.0f;
                        this.ticks_catchable = this.random.nextInt(101) + 100;
                    }
                } else {
                    this.fish_wait_time = this.random.nextInt(251) + 50;
                    Player lurePlayer = this.getAngler();
                    this.fish_wait_time -=
                            com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.LURE, getUltimateRodStack(lurePlayer))
                                    * 20
                                    * 5;
                }
                if (this.fish_on_hook > 0) {
                    motionY -= (double) (this.random.nextFloat() * this.random.nextFloat() * this.random.nextFloat()) * 0.2D;
                }
            }

            d2 = d10 * 2.0D - 1.0D;
            motionY += 0.03999999910593033D * d2;
            if (d10 > 0.0D) {
                f6 = (float) ((double) f6 * 0.9D);
                motionY *= 0.8D;
            }
            motionX *= (double) f6;
            motionY *= (double) f6;
            motionZ *= (double) f6;
            this.setDeltaMovement(new Vec3(motionX, motionY, motionZ));
            this.setPos(this.getX(), this.getY(), this.getZ());
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putShort("xTile", (short) this.field_146037_g);
        tag.putShort("yTile", (short) this.field_146048_h);
        tag.putShort("zTile", (short) this.field_146050_i);
        if (this.field_146046_j != null) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(this.field_146046_j);
            if (key != null) {
                tag.putString("inTile", key.toString());
            }
        }
        tag.putByte("shake", (byte) this.hookShake);
        tag.putByte("inGround", (byte) (this.field_146051_au ? 1 : 0));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.field_146037_g = tag.getShort("xTile");
        this.field_146048_h = tag.getShort("yTile");
        this.field_146050_i = tag.getShort("zTile");
        if (tag.contains("inTile", 8)) {
            ResourceLocation loc = ResourceLocation.parse(tag.getString("inTile"));
            this.field_146046_j = BuiltInRegistries.BLOCK.getOptional(loc).orElse(Blocks.AIR);
        } else if (tag.contains("inTile", 1)) {
            // Legacy numeric block id (1.12-era); not portable — default to air.
            this.field_146046_j = Blocks.AIR;
        } else {
            this.field_146046_j = Blocks.AIR;
        }
        this.hookShake = tag.getByte("shake") & 255;
        this.field_146051_au = tag.getByte("inGround") == 1;
    }

    /**
     * Vanilla 1.20 name for hook retrieval. Implements 1.12 {@code handleHookRetraction} (custom loot, not vanilla
     * tables).
     */
    @Override
    public int retrieve(ItemStack stack) {
        return this.handleHookRetraction();
    }

    /** 1.12 {@code handleHookRetraction} semantics (return codes preserved). */
    public int handleHookRetraction() {
        if (this.level().isClientSide) {
            return 0;
        }
        int b0 = 0;
        Player angler = this.getAngler();
        if (angler == null) {
            this.discard();
            return 0;
        }
        if (this.field_146043_c != null) {
            double d0 = angler.getX() - this.getX();
            double d2 = angler.getY() - this.getY();
            double d4 = angler.getZ() - this.getZ();
            double d6 = Mth.sqrt((float) (d0 * d0 + d2 * d2 + d4 * d4));
            double d8 = 0.1D;
            Vec3 dm = this.field_146043_c.getDeltaMovement();
            this.field_146043_c.setDeltaMovement(
                    dm.x + d0 * d8, dm.y + d2 * d8 + (double) Mth.sqrt((float) d6) * 0.08D, dm.z + d4 * d8);
            b0 = 3;
        } else if (this.fish_on_hook > 0) {
            ItemStack loot = this.func_146033_f();
            ItemEntity entityitem =
                    new ItemEntity(this.level(), this.getX(), this.getY() + 1.25D, this.getZ(), loot);
            double d1 = angler.getX() - this.getX();
            double d3 = angler.getY() - this.getY();
            double d5 = angler.getZ() - this.getZ();
            double d7 = Mth.sqrt((float) (d1 * d1 + d3 * d3 + d5 * d5));
            double d9 = 0.1D;
            entityitem.setDeltaMovement(d1 * d9, d3 * d9 + (double) Mth.sqrt((float) d7) * 0.08D, d5 * d9);
            entityitem.setNoPickUpDelay();
            this.level().addFreshEntity(entityitem);
            this.level()
                    .addFreshEntity(
                            new ExperienceOrb(
                                    this.level(),
                                    angler.getX(),
                                    angler.getY() + 0.5D,
                                    angler.getZ() + 0.5D,
                                    this.random.nextInt(6) + 1));
            b0 = 1;
        }
        if (this.field_146051_au) {
            b0 = 2;
        }
        this.discard();
        if (angler.fishing == this) {
            angler.fishing = null;
        }
        return b0;
    }

    private ItemStack func_146033_f() {
        float f = this.level().random.nextFloat();
        Player angler = this.getAngler();
        int i =
                com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.LUCK_OF_THE_SEA, getUltimateRodStack(angler));
        int j =
                com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.LURE, getUltimateRodStack(angler));
        float f1 = 0.1f - (float) i * 0.025f - (float) j * 0.01f;
        float f2 = 0.05f + (float) i * 0.01f - (float) j * 0.01f;
        f1 = Mth.clamp(f1, 0.0f, 1.0f);
        f2 = Mth.clamp(f2, 0.0f, 1.0f);
        BlockPos bidPos = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        boolean lavaHere =
                this.isInLava()
                        || this.level().getFluidState(bidPos).is(FluidTags.LAVA)
                        || this.level().getBlockState(bidPos).is(Blocks.LAVA);
        if (lavaHere) {
            angler.awardStat(Stats.CUSTOM.get(Stats.FISH_CAUGHT), 1);
            return WeightedRandom.getRandomItem(this.random, chaospersists_lava_fish)
                    .orElseThrow()
                    .getItemStack(this.random);
        }
        if (f < f1) {
            angler.awardStat(Stats.CUSTOM.get(Stats.FISH_CAUGHT), 1);
            return WeightedRandom.getRandomItem(this.random, field_146039_d).orElseThrow().getItemStack(this.random);
        }
        if ((f -= f1) < f2) {
            angler.awardStat(Stats.CUSTOM.get(Stats.FISH_CAUGHT), 1);
            return WeightedRandom.getRandomItem(this.random, field_146041_e).orElseThrow().getItemStack(this.random);
        }
        float f3 = this.level().random.nextFloat();
        angler.awardStat(Stats.CUSTOM.get(Stats.FISH_CAUGHT), 1);
        if (f3 < 0.5f) {
            return WeightedRandom.getRandomItem(this.random, field_146036_f).orElseThrow().getItemStack(this.random);
        }
        return WeightedRandom.getRandomItem(this.random, chaospersists_fish).orElseThrow().getItemStack(this.random);
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        super.remove(reason);
        Player angler = this.getAngler();
        if (angler != null && angler.fishing == this) {
            angler.fishing = null;
        }
    }
}
