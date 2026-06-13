package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.entity.EnderReaper;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.LeafMonster;
import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.TerribleTerror;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.item.WaterBall;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FlowingFluidBlock;
import com.google.common.base.Predicate;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.network.play.client.CInputPacket;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;

/*
 * Exception performing whole class analysis ignored.
 */
public class Dragon
extends TameableEntity {
    private static final DataParameter<Byte> ATTACKING = EntityDataManager.defineId(Dragon.class, DataSerializers.BYTE);
    private static final DataParameter<Byte> STATE2 = EntityDataManager.defineId(Dragon.class, DataSerializers.BYTE);
    private static final DataParameter<Integer> INT22 = EntityDataManager.defineId(Dragon.class, DataSerializers.INT);
    private static final DataParameter<Integer> INT24 = EntityDataManager.defineId(Dragon.class, DataSerializers.INT);
    private int boatPosRotationIncrements;
    private double boatX;
    private double boatY;
    private double boatZ;
    private double boatYaw;
    private double boatPitch;
    private double boatYawHead;
    private int updateit = 1;
    private int color = 1;
    private int playing = 0;
    private GenericTargetSorter TargetSorter = null;
    private RenderInfo renderdata = new RenderInfo();
    private int hurt_timer = 0;
    private int wing_sound = 0;
    private BlockPos currentFlightTarget = null;
    private boolean target_in_sight = false;
    private int owner_flying = 0;
    private int flyaway = 0;
    private int stuck_count = 0;
    private int lastX = 0;
    private int lastZ = 0;
    private int unstick_timer = 0;
    private int fireballticker = 0;
    private float moveSpeed = 0.32f;
    private float deltasmooth = 0.0f;
    private int dragontype = 0;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Dragon(EntityType<? extends Dragon> type, World par1World) {
        super(type, par1World);
        this.xpReward = 100;
        this.setOrderedToSit(false);
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner((TameableEntity)this, 1.1f, 12.0f, 2.0f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.BEEF), false));
        this.goalSelector.addGoal(3, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 9.0f));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(6, new RestrictSunGoal(this));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal((CreatureEntity)this, LivingEntity.class, 0, true, false, new Predicate<LivingEntity>() { @Override public boolean apply(LivingEntity e) { return e instanceof IMob; } }));
        }
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.TargetSorter = new GenericTargetSorter((Entity)this);
        this.renderdata = new RenderInfo();
    }

    public Dragon(World par1World, double par2, double par4, double par6) {
        this((EntityType<? extends Dragon>)net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", "dragon")), par1World);
        this.setPos(par2, par4, par6);
        this.setDeltaMovement(0.0, 0.0, 0.0);
        this.xo = par2;
        this.yo = par4;
        this.zo = par6;
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 200.0)
                .add(Attributes.MOVEMENT_SPEED, 0.32)
                .add(Attributes.ATTACK_DAMAGE, 35.0)
                .build();
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld level, AgeableEntity mate) {
        return null;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public boolean shouldRiderSit() {
        return true;
    }

    public int getTrackingRange() {
        return 128;
    }

    public int getUpdateFrequency() {
        return 10;
    }

    public boolean sendsVelocityUpdates() {
        return true;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
    }

    public boolean canBeSteered() {
        return true;
    }

    public boolean canPassengerSteer() {
        return true;
    }

    /**
     * Same pipeline as {@link ThePrinceTeen} / {@link Cephadrome}: vanilla calls {@code travel} with rider
     * input synced from the client via {@code CPacketInput}. Flight must run here, not only in {@code onLivingUpdate},
     * or {@code moveForward} stays zero on the server and the mount cannot fly.
     */
    @Override
    public void travel(Vector3d travelVector) {
        float strafe = (float) travelVector.x;
        float vertical = (float) travelVector.y;
        float forward = (float) travelVector.z;
        if (!this.getPassengers().isEmpty() && this.getControllingPassenger() instanceof PlayerEntity && this.getActivity() != 0) {
            PlayerEntity pp = (PlayerEntity)this.getControllingPassenger();
            if (pp.removed) {
                this.ejectPassengers();
                this.setNoGravity(false);
                super.travel(travelVector);
                return;
            }
            this.setNoGravity(true);
            List list;
            Entity listEntity;
            double obstruction_factor;
            double relative_g;
            double max_speed = 0.95D;
            double gh;
            double pi = 3.1415926545D;
            double deltav;
            Block bid;
            BetterFireball bf;

            if (this.fireballticker > 0) {
                --this.fireballticker;
            }

            if (this.getDeltaMovement().x < -2.0D) this.setDeltaMovement(-2.0D, this.getDeltaMovement().y, this.getDeltaMovement().z);
            if (this.getDeltaMovement().x > 2.0D) this.setDeltaMovement(2.0D, this.getDeltaMovement().y, this.getDeltaMovement().z);
            if (this.getDeltaMovement().z < -2.0D) this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, -2.0D);
            if (this.getDeltaMovement().z > 2.0D) this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, 2.0D);
            double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);

            gh = 1.25D;
            bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX(), (int)((float)this.getY() - (float)gh), (int)this.getZ())).getBlock();
            if (bid != Blocks.AIR) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.03D, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addEntityY(this, 0.1D);
            } else {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, -(0.018D), 0.0);
            }

            obstruction_factor = 0.0D;
            int distLimit = 3 + (int)(velocity * 7.0D);
            if (distLimit < 3) {
                distLimit = 3;
            }
            if (distLimit > 24) {
                distLimit = 24;
            }
            int iMax = Math.min(distLimit * 2, 48);
            for (int k = 1; k < distLimit; k++) {
                for (int i = 1; i < iMax; i++) {
                    double dx = i * Math.cos(Math.toRadians(this.yRot + 90.0F));
                    double dz = i * Math.sin(Math.toRadians(this.yRot + 90.0F));
                    bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + dz))).getBlock();
                    if (bid != Blocks.AIR) {
                        obstruction_factor += 0.05D;
                    }
                }
            }

            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.07000000000000001D, 0.0);
            com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.07000000000000001D);
            if (this.getDeltaMovement().y > 2.0D) this.setDeltaMovement(this.getDeltaMovement().x, 2.0D, this.getDeltaMovement().z);

            double d4 = pp.yRot;
            d4 %= 360.0D;
            while (d4 < 0.0D) d4 += 360.0D;
            double d5 = this.yRot;
            d5 %= 360.0D;
            while (d5 < 0.0D) d5 += 360.0D;
            relative_g = (d4 - d5) % 180.0D;
            while (relative_g < 0.0D) relative_g += 180.0D;
            if (relative_g > 90.0D) relative_g -= 180.0D;

            if (velocity > 0.01D) {
                d4 = 1.85D - velocity;
                d4 = Math.abs(d4);
                if (d4 < 0.01D) d4 = 0.01D;
                if (d4 > 0.9D) d4 = 0.9D;
                this.yRot = pp.yRot + (float)(relative_g * d4);
            } else {
                this.yRot = pp.yRot;
            }
            relative_g = Math.abs(relative_g) * velocity;
            if (relative_g > 50.0D) relative_g = 0.0D;

            this.xRot = (2.0F * (float)velocity);
            this.yHeadRot = this.yRot;

            double newvelocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
            double rhm = Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x);
            double rhdir = Math.toRadians(((pp.yRot + 90.0F) % 360.0F));
            float im = pp.yya;

            boolean riderJumping = false;
            try {
                riderJumping = ObfuscationReflectionHelper.getPrivateValue(LivingEntity.class, pp, "jumping");
            } catch (Exception ignored) {
            }
            if (riderJumping || ChaosPersists.flyup_keystate != 0) {
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.03D, 0.0);
                com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, velocity * 0.036D, 0.0);
            }

            double rdv = Math.abs(rhm - rhdir) % (pi * 2.0D);
            if (rdv > pi) rdv -= pi * 2.0D;
            rdv = Math.abs(rdv);
            if (Math.abs(newvelocity) < 0.01D) rdv = 0.0D;

            if (rdv > 1.5D) newvelocity = -newvelocity;

            if (Math.abs(im) > 0.001F) {
                if (im > 0.0F) {
                    deltav = 0.025D;
                    if (max_speed > 1.0D) deltav += 0.05D;
                    if (this.deltasmooth < 0.0F) this.deltasmooth = 0.0F;
                    this.deltasmooth = (float)(this.deltasmooth + deltav / 10.0D);
                    if (this.deltasmooth > deltav) this.deltasmooth = (float)deltav;
                } else {
                    max_speed = 0.35D;
                    deltav = -0.02D;
                    if (this.deltasmooth > 0.0F) this.deltasmooth = 0.0F;
                    this.deltasmooth = (float)(this.deltasmooth + deltav / 10.0D);
                    if (this.deltasmooth < deltav) this.deltasmooth = (float)deltav;
                }

                newvelocity += this.deltasmooth;
                if (newvelocity >= 0.0D) {
                    if (newvelocity > max_speed) newvelocity = max_speed;
                    this.setDeltaMovement((Math.cos(Math.toRadians(this.yRot + 90.0F)) * newvelocity), this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, (Math.sin(Math.toRadians(this.yRot + 90.0F)) * newvelocity));
                } else {
                    if (newvelocity < -max_speed) newvelocity = -max_speed;
                    newvelocity = -newvelocity;
                    this.setDeltaMovement((Math.cos(Math.toRadians(this.yRot + 270.0F)) * newvelocity), this.getDeltaMovement().y, this.getDeltaMovement().z);
                    this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, (Math.sin(Math.toRadians(this.yRot + 270.0F)) * newvelocity));
                }

            } else if (newvelocity >= 0.0D) {
                this.setDeltaMovement((Math.cos(Math.toRadians(this.yRot + 90.0F)) * newvelocity), this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, (Math.sin(Math.toRadians(this.yRot + 90.0F)) * newvelocity));
            } else {
                this.setDeltaMovement((Math.cos(Math.toRadians(this.yRot + 270.0F)) * (newvelocity * -1.0D)), this.getDeltaMovement().y, this.getDeltaMovement().z);
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y, (Math.sin(Math.toRadians(this.yRot + 270.0F)) * (newvelocity * -1.0D)));
            }

            if (this.fireballticker == 0) {
                double xzoff = 4.0D;
                double yoff = -0.25D;

                if (getDragonType() == 0) {
                    if (pp.xxa > 0.001F) {
                        bf = new BetterFireball(this.level, this, 0.0D, 0.0D, 0.0D);
                        bf.setNotMe();
                        bf.setSmall();
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                        bf.setPos(cx, this.getY() + yoff, cz);
                        cx = Math.cos(Math.toRadians(pp.yHeadRot + 90.0F));
                        cz = Math.sin(Math.toRadians(pp.yHeadRot + 90.0F));
                        double cy = -Math.sin(Math.toRadians(pp.xRot));

                        double d3 = MathHelper.sqrt(cx * cx + cy * cy + cz * cz);
                        bf.accelerationX = (cx / d3 * 0.15D);
                        bf.accelerationY = (cy / d3 * 0.15D);
                        bf.accelerationZ = (cz / d3 * 0.15D);
                        bf.setDeltaMovement(this.getDeltaMovement().x, bf.getDeltaMovement().y, bf.getDeltaMovement().z);
                        bf.setDeltaMovement(bf.getDeltaMovement().x, this.getDeltaMovement().y, bf.getDeltaMovement().z);
                        bf.setDeltaMovement(bf.getDeltaMovement().x, bf.getDeltaMovement().y, this.getDeltaMovement().z);
                        com.astryxion.chaospersists.util.MyUtils.addEntityX(bf, -(this.getDeltaMovement().x * 9.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityY(bf, -(this.getDeltaMovement().y * 9.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityZ(bf, -(this.getDeltaMovement().z * 9.0D));
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.bow")), net.minecraft.util.SoundCategory.NEUTRAL, 0.75F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
                        this.level.addFreshEntity(bf);
                        this.fireballticker = 10;
                    }
                    if (pp.xxa < -0.001F) {
                        bf = new BetterFireball(this.level, this, 0.0D, 0.0D, 0.0D);
                        bf.setNotMe();
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                        bf.setPos(cx, this.getY() + yoff, cz);
                        cx = Math.cos(Math.toRadians(pp.yHeadRot + 90.0F));
                        cz = Math.sin(Math.toRadians(pp.yHeadRot + 90.0F));
                        double cy = -Math.sin(Math.toRadians(pp.xRot));

                        double d3 = MathHelper.sqrt(cx * cx + cy * cy + cz * cz);
                        bf.accelerationX = (cx / d3 * 0.1D);
                        bf.accelerationY = (cy / d3 * 0.1D);
                        bf.accelerationZ = (cz / d3 * 0.1D);
                        bf.setDeltaMovement(this.getDeltaMovement().x, bf.getDeltaMovement().y, bf.getDeltaMovement().z);
                        bf.setDeltaMovement(bf.getDeltaMovement().x, this.getDeltaMovement().y, bf.getDeltaMovement().z);
                        bf.setDeltaMovement(bf.getDeltaMovement().x, bf.getDeltaMovement().y, this.getDeltaMovement().z);
                        com.astryxion.chaospersists.util.MyUtils.addEntityX(bf, -(this.getDeltaMovement().x * 9.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityY(bf, -(this.getDeltaMovement().y * 9.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityZ(bf, -(this.getDeltaMovement().z * 9.0D));
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.fuse")), net.minecraft.util.SoundCategory.NEUTRAL, 1.0F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
                        this.level.addFreshEntity(bf);
                        this.fireballticker = 20;
                    }
                } else {
                    if (pp.xxa > 0.001F) {
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                        WaterBall var2 = new WaterBall(this.level, cx, this.getY() + yoff, cz);
                        var2.moveTo(cx, this.getY() + yoff, cz, pp.yRot + 90.0F, pp.xRot);
                        double var3 = Math.cos(Math.toRadians(pp.yHeadRot + 90.0F));
                        double var5 = -Math.sin(Math.toRadians(pp.xRot));
                        double var77 = Math.sin(Math.toRadians(pp.yHeadRot + 90.0F));
                        float var9 = MathHelper.sqrt(var3 * var3 + var77 * var77) * 0.2F;
                        var2.shoot(var3, var5 + var9, var77, 1.4F, 5.0F);
                        com.astryxion.chaospersists.util.MyUtils.addEntityX(var2, -(this.getDeltaMovement().x * 7.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityY(var2, -(this.getDeltaMovement().y * 7.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityZ(var2, -(this.getDeltaMovement().z * 7.0D));
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.bow")), net.minecraft.util.SoundCategory.NEUTRAL, 0.75F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
                        this.level.addFreshEntity(var2);
                        this.fireballticker = 5;
                    }
                    if (pp.xxa < -0.001F) {
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                        IceBall var2 = new IceBall(this.level, cx, this.getY() + yoff, cz);
                        var2.moveTo(cx, this.getY() + yoff, cz, pp.yRot + 90.0F, pp.xRot);
                        var2.setSpecial();
                        var2.setIceBall();
                        double var3 = Math.cos(Math.toRadians(pp.yRot + 90.0F));
                        double var5 = -Math.sin(Math.toRadians(pp.xRot));
                        double var77 = Math.sin(Math.toRadians(pp.yRot + 90.0F));
                        float var9 = MathHelper.sqrt(var3 * var3 + var77 * var77) * 0.2F;
                        var2.shoot(var3, var5 + var9, var77, 1.4F, 5.0F);
                        com.astryxion.chaospersists.util.MyUtils.addEntityX(var2, -(this.getDeltaMovement().x * 7.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityY(var2, -(this.getDeltaMovement().y * 7.0D));
                        com.astryxion.chaospersists.util.MyUtils.addEntityZ(var2, -(this.getDeltaMovement().z * 7.0D));
                        com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(var2, 2.0D, 2.0D, 2.0D);
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("fireworks.launch")), net.minecraft.util.SoundCategory.NEUTRAL, 0.75F, 1.0F / (getRandom().nextFloat() * 0.4F + 0.8F));
                        this.level.addFreshEntity(var2);
                        this.fireballticker = 15;
                    }
                }
            }

            this.move(MoverType.SELF, this.getDeltaMovement());

            com.astryxion.chaospersists.util.MyUtils.mulDeltaMovement(this, 0.985D, 0.94D, 0.985D);

            if (!this.level.isClientSide) {
                list = this.level.getEntities(this, this.getBoundingBox().inflate(2.25D, 2.0D, 2.25D));

                if ((list != null) && (!list.isEmpty())) {
                    for (int l = 0; l < list.size(); l++) {
                        listEntity = (Entity)list.get(l);

                        if ((listEntity == pp) || (listEntity.removed) || (!listEntity.isPushable()))
                            continue;
                        listEntity.push(this);
                    }
                }
            }
            return;
        }
        this.setNoGravity(false);
        super.travel(travelVector);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (!this.level.isClientSide && this.getPassengers().isEmpty()) {
            this.setNoGravity(false);
            this.noPhysics = false;
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
            MyUtils.enforceDragonMountGroundSafety(this);
        }
    }

    public void fall(float distance, float damageMultiplier) {
    }

    protected void updateFallState(double y, boolean onGroundIn, net.minecraft.block.BlockState state, net.minecraft.util.math.BlockPos pos) {
        fallDistance = 0.0f;
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte)0);
        this.entityData.define(STATE2, (byte)0);
        this.entityData.define(INT22, 0);
        this.entityData.define(INT24, 1);
        this.setActivity(0);
        this.setAttacking(0);
        this.setTame(false);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
    }

    public int mygetMaxHealth() {
        return 200;
    }

    public int getDragonHealth() {
        return (int)this.getHealth();
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
    }

    public int getArmorValue() {
        return 14;
    }

    protected void jumpFromGround() {
        super.jumpFromGround();
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.25, 0.0);
    }

    public boolean isAIEnabled() {
        return true;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = - dy; i <= dy; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + dx, y + i, z + j)).getBlock();
                if ((bid == Blocks.LAVA || bid == Blocks.LAVA) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x - dx, y + i, z + j)).getBlock()) != Blocks.LAVA && bid != Blocks.LAVA || (d = dx * dx + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + dy, z + j)).getBlock();
                if ((bid == Blocks.LAVA || bid == Blocks.LAVA) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y - dy, z + j)).getBlock()) != Blocks.LAVA && bid != Blocks.LAVA || (d = dy * dy + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = - dx; i <= dx; ++i) {
            for (j = - dy; j <= dy; ++j) {
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z + dz)).getBlock();
                if ((bid == Blocks.LAVA || bid == Blocks.LAVA) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if ((bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos(x + i, y + j, z - dz)).getBlock()) != Blocks.LAVA && bid != Blocks.LAVA || (d = dz * dz + j * j + i * i) >= this.closest) continue;
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        if (found != 0) {
            return true;
        }
        return false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isOrderedToSit()) {
            return null;
        }
        if (this.getAttacking() == 1 && (this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) == null) {
            return com.astryxion.chaospersists.core.ChaosSounds.ROAR;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_HURT;
    }

    protected net.minecraft.util.SoundEvent getDeathSound() {
        return com.astryxion.chaospersists.core.ChaosSounds.ALO_DEATH;
    }

    protected float getSoundVolume() {
        return 0.6f;
    }

    public float getVoicePitch() {
        return 0.75f;
    }

    public boolean canBePushed() {
        return false;
    }

    public double getMountedYOffset() {
        return 1.3;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(5) - (double)ChaosPersists.ChaosRand.nextInt(5), is);
        if (var3 != null) {
            this.level.addFreshEntity((Entity)var3);
        }
        return is;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 1 + this.level.random.nextInt(6);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.BEEF, 1);
        }
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        double ks = 1.75;
        double inair = 0.1;
        float iskraken = 1.0f;
        if (par1Entity != null && par1Entity instanceof LivingEntity) {
            if (par1Entity instanceof Kraken) {
                iskraken = 2.0f;
            }
            par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), iskraken * 35.0f);
            float f3 = (float)Math.atan2(par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
            if (par1Entity.isAlive() == false || par1Entity instanceof PlayerEntity) {
                inair *= 2.0;
            }
            par1Entity.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return true;
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = null;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (par1DamageSource.getMsgId().equals("cactus")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("inFire")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("onFire")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("lava")) {
            return ret;
        }
        if (par1DamageSource.getMsgId().equals("inWall")) {
            return ret;
        }
        this.setOrderedToSit(false);
        this.setActivity(1);
        e = par1DamageSource.getEntity();
        if (e != null && e instanceof BetterFireball && this.dragontype == 0) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof IceBall && this.dragontype != 0) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof WaterBall && this.dragontype != 0) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof SmallFireballEntity && this.dragontype == 0) {
            e.remove();
            return ret;
        }
        if (e != null && e instanceof Dragon) {
            return false;
        }
        if (e != null && e instanceof Spyro) {
            return false;
        }
        ret = super.hurt(par1DamageSource, par2);
        this.hurt_timer = 20;
        if (e != null && e instanceof LivingEntity) {
            if (this.isTame() && e instanceof PlayerEntity) {
                return false;
            }
            this.setTarget((LivingEntity)e);
            this.setTarget(e instanceof LivingEntity ? (LivingEntity)e : null);
            this.getNavigation().moveTo((Entity)((LivingEntity)e), 1.2);
            ret = true;
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        LivingEntity e = null;
        // Same as ThePrinceTeen: while ambient-flying (activity on, no rider), skip task AI so wander/follow/
        // move-indoors do not overwrite moveForward/moveStrafing after fly_without_rider — that caused random
        // backward flight vs. Prince which never runs those tasks in that state.
        if (this.getActivity() == 0 || !this.getPassengers().isEmpty()) {
            super.customServerAiStep();
        }
        if (!this.isOrderedToSit() && this.getActivity() == 0 && this.getPassengers().isEmpty() && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(10) == 1 && (e = this.findSomethingToAttack()) != null) {
            this.setActivity(1);
        }
    }

    public void always_do() {
        PlayerEntity p = null;
        if (this.level.random.nextInt(250) == 1 && this.getHealth() < (float)this.mygetMaxHealth()) {
            this.heal(2.0f);
        }
        if (this.isOrderedToSit()) {
            return;
        }
        this.owner_flying = 0;
        if (this.isTame() && this.getOwner() != null && (this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) == null && !this.isOrderedToSit()) {
            p = (PlayerEntity)this.getOwner();
            if (p.abilities.flying) {
                this.owner_flying = 1;
                this.setActivity(1);
            }
        }
        if (this.isTame() && this.getOwner() != null && !this.isOrderedToSit() && this.distanceToSqr((Entity)(p = (PlayerEntity)this.getOwner())) > 400.0) {
            this.setActivity(1);
        }
        if (this.level.random.nextInt(50) == 1 && !this.isOrderedToSit() && !this.target_in_sight && (this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) == null) {
            if (this.level.random.nextInt(15) == 1) {
                this.setActivity(1);
            } else {
                this.setActivity(0);
            }
        }
        if (this.level.random.nextInt(25) == 0 && !this.target_in_sight && (this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) == null) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 11; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int)this.getX(), (int)this.getY() - 1, (int)this.getZ(), i, j, i)) break;
                if (i < 6) continue;
                ++i;
            }
            if (this.closest < 99999) {
                this.setActivity(0);
                this.getNavigation().moveTo((double)this.tx, (double)(this.ty - 1), (double)this.tz, 1.0);
                if (this.isInLava()) {
                    this.heal(1.0f);
                    this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("entity.generic.splash")), 1.0f, this.level.random.nextFloat() * 0.2f + 0.9f);
                }
            }
        }
    }

    public void fly_with_rider() {
        LivingEntity e = null;
        int freq = 7;
        if (!this.isAlive()) {
            return;
        }
        if (this.isOrderedToSit()) {
            return;
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.level.random.nextInt(freq) == 1 && this.level.getDifficulty() != Difficulty.PEACEFUL) {
            if (this.level.random.nextInt(250) == 0) {
                this.setTarget(null);
            }
            if ((e = this.getTarget()) != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
            }
            if (e != null) {
                this.setAttacking(1);
                if (this.distanceToSqr((Entity)e) < (double)((7.0f + e.getBbWidth() / 2.0f) * (7.0f + e.getBbWidth() / 2.0f))) {
                    this.doHurtTarget((LivingEntity)e);
                }
                return;
            }
            this.setAttacking(0);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2) {
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == this) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (!this.getSensing().canSee((Entity)par1Mob)) {
            return false;
        }
        if (par1Mob instanceof LurkingTerror) {
            return false;
        }
        if (par1Mob instanceof EnderReaper) {
            return false;
        }
        if (par1Mob instanceof TerribleTerror) {
            return false;
        }
        if (par1Mob instanceof LeafMonster) {
            return false;
        }
        if (par1Mob instanceof CreepingHorror) {
            return false;
        }
        if (par1Mob instanceof Triffid) {
            return false;
        }
        if (par1Mob instanceof MonsterEntity) {
            return true;
        }
        if (par1Mob instanceof Mothra) {
            return true;
        }
        if (par1Mob instanceof Kraken) {
            return true;
        }
        if (par1Mob instanceof PlayerEntity) {
            return false;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List var5 = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0, 20.0, 20.0));
        Collections.sort(var5, this.TargetSorter);
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false)) continue;
            return var4;
        }
        return null;
    }

    public boolean doesEntityNotTriggerPressurePlate() {
        return false;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader level, net.minecraft.entity.SpawnReason reason) {
        Dragon target = null;
        if (!this.level.isDay()) {
            return false;
        }
        target = this.level.getNearestEntity(Dragon.class, EntityPredicate.DEFAULT, this, this.getX(), this.getY(), this.getZ(), this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        if (target != null) {
            return false;
        }
        if (com.astryxion.chaospersists.core.ChaosPersists.getDimensionId(this.level) == ChaosPersists.getDimension(4)) {
            return true;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        return true;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level.clip(new RayTraceContext(new Vector3d((double)this.getX(), (double)(this.getY() + 0.75), (double)this.getZ()), new Vector3d((double)pX, (double)pY, (double)pZ), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @OnlyIn(Dist.CLIENT)
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
        this.setPos(par1, par3, par5);
        this.yRot = par7;
        this.xRot = par8;
        this.boatPosRotationIncrements = par9;
        this.boatX = par1;
        this.boatY = par3;
        this.boatZ = par5;
        this.boatYaw = par7;
        this.boatPitch = par8;
        this.boatYawHead = par7;
    }

    @OnlyIn(Dist.CLIENT)
    public void setVelocity(double par1, double par3, double par5) {
        this.setDeltaMovement(par1, par3, par5);
    }

    public void tick() {
        LivingEntity e = null;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        this.noPhysics = false;
        if (!this.level.isClientSide && this.getActivity() != 0 && this.getPassengers().isEmpty() && !this.isOrderedToSit()) {
            this.fly_without_rider();
        }
        super.tick();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (this.getActivity() == 1) {
            ++this.wing_sound;
            if (this.wing_sound > 20) {
                if (!this.level.isClientSide) {
                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), com.astryxion.chaospersists.core.ChaosSounds.MOTHRA_WINGS, net.minecraft.util.SoundCategory.NEUTRAL, 0.5f, 1.0f);
                }
                this.wing_sound = 0;
            }
        }
        if (this.isInWater()) {
            com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, 0.07, 0.0);
        }
        if (this.level.isClientSide) {
            return;
        }
        if (this.getActivity() == 0 && this.isTame() && this.getOwner() != null && !this.isOrderedToSit() && this.distanceToSqr((Entity)(e = this.getOwner())) > 144.0) {
            this.setActivity(1);
        }
        MyUtils.enforceDragonMountGroundSafety(this);
        if (this.getPassengers().isEmpty()) {
            this.moveTowardsClosestSpace(this.getX(), this.getY(), this.getZ());
        }
    }

    private void fly_without_rider() {
        Block bid;
        int xdir = 1;
        int zdir = 1;
        int keep_trying = 50;
        boolean do_new = false;
        double ox = 0.0;
        double oy = 0.0;
        double oz = 0.0;
        boolean has_owner = false;
        LivingEntity e = null;
        double speed_factor = 0.5;
        double var1 = 0.0;
        double var3 = 0.0;
        double var5 = 0.0;
        double yoff = 1.25;
        double xzoff = 2.25;
        double gh = 1.25;
        double obstruction_factor = 0.0;
        SmallFireballEntity sf = null;
        BetterFireball bf = null;
        IceBall ib = null;
        WaterBall wb = null;
        boolean toofar = false;
        if (this.currentFlightTarget == null) {
            do_new = true;
            this.currentFlightTarget = new BlockPos((int)this.getX(), (int)this.getY(), (int)this.getZ());
        }
        if (this.isOrderedToSit()) {
            return;
        }
        if ((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) != null) {
            return;
        }
        if (this.unstick_timer > 0) {
            --this.unstick_timer;
        }
        if (this.lastX == (int)this.getX() && this.lastZ == (int)this.getZ()) {
            ++this.stuck_count;
            if (this.stuck_count > 50) {
                this.stuck_count = 0;
                this.unstick_timer = 100;
                this.target_in_sight = false;
                this.setAttacking(0);
                do_new = true;
            }
        } else {
            this.stuck_count = 0;
            this.lastX = (int)this.getX();
            this.lastZ = (int)this.getZ();
        }
        double flightMotionY = this.getDeltaMovement().y;
        if (this.getY() < (double)this.currentFlightTarget.getY() + 2.0) {
            flightMotionY *= 0.7;
        } else if (this.getY() > (double)this.currentFlightTarget.getY() - 2.0) {
            flightMotionY *= 0.5;
        } else {
            flightMotionY *= 0.61;
        }
        this.setDeltaMovement(this.getDeltaMovement().x, flightMotionY, this.getDeltaMovement().z);
        if (this.level.random.nextInt(300) == 1) {
            do_new = true;
        }
        if (this.isTame() && this.getOwner() != null) {
            e = this.getOwner();
            has_owner = true;
            ox = e.getX();
            oy = e.getY();
            oz = e.getZ();
            if (this.distanceToSqr((Entity)e) > 144.0) {
                toofar = true;
                this.target_in_sight = false;
                this.setAttacking(0);
                this.flyaway = 0;
                do_new = true;
            }
        }
        if (this.flyaway > 0) {
            --this.flyaway;
        }
        if (!toofar && this.unstick_timer == 0 && this.flyaway == 0 && this.level.getDifficulty() != Difficulty.PEACEFUL && this.level.random.nextInt(9) == 1) {
            e = this.getTarget();
            if (e != null && !e.isAlive()) {
                this.setTarget(null);
                e = null;
            }
            if (e == null) {
                e = this.findSomethingToAttack();
                if (e != null) {
                    this.setTarget(e);
                }
            }
            if (e != null) {
                if (this.isTame() && this.getHealth() / (float)this.mygetMaxHealth() < 0.25f) {
                    this.setActivity(1);
                    this.setAttacking(0);
                    this.target_in_sight = false;
                    do_new = false;
                    this.currentFlightTarget = new BlockPos((int)(this.getX() + (this.getX() - e.getX())), (int)(this.getY() + 1.0), (int)(this.getZ() + (this.getZ() - e.getZ())));
                } else {
                    this.setActivity(1);
                    this.setAttacking(1);
                    this.target_in_sight = true;
                    this.currentFlightTarget = new BlockPos((int)e.getX(), (int)(e.getY() + 1.0), (int)e.getZ());
                    do_new = false;
                    if (this.distanceToSqr((Entity)e) < (double)((5.0f + e.getBbWidth() / 2.0f) * (5.0f + e.getBbWidth() / 2.0f))) {
                        this.doHurtTarget((LivingEntity)e);
                        this.flyaway = 5 + this.level.random.nextInt(10);
                        do_new = true;
                    } else if (this.distanceToSqr((Entity)e) < 256.0 && !this.isInWater() && this.getDragonFire() >= 1) {
                        double var7;
                        float var9;
                        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot));
                        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot));
                        if (this.dragontype == 0) {
                            if (this.getDragonFire() == 1) {
                                sf = new SmallFireballEntity(this.level, (LivingEntity)this, e.getX() - cx, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - cz);
                                sf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                                sf.setPos(cx, this.getY() + yoff, cz);
                                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.bow")), net.minecraft.util.SoundCategory.NEUTRAL, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                                this.level.addFreshEntity((Entity)sf);
                            } else {
                                bf = new BetterFireball(this.level, (LivingEntity)this, e.getX() - cx, e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - cz);
                                bf.moveTo(cx, this.getY() + yoff, cz, this.yRot, 0.0f);
                                bf.setPos(cx, this.getY() + yoff, cz);
                                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.fuse")), net.minecraft.util.SoundCategory.NEUTRAL, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                                this.level.addFreshEntity((Entity)bf);
                            }
                        } else if (this.getDragonFire() == 1) {
                            wb = new WaterBall(this.level, e.getX() - this.getX(), e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - this.getZ());
                            wb.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)), this.yHeadRot, this.xRot);
                            var3 = e.getX() - wb.getX();
                            var5 = e.getY() + 0.25 - wb.getY();
                            var7 = e.getZ() - wb.getZ();
                            var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                            wb.shoot(var3, var5 + (double)var9, var7, 1.4f, 5.0f);
                            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.bow")), net.minecraft.util.SoundCategory.NEUTRAL, 0.75f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                            this.level.addFreshEntity((Entity)wb);
                        } else {
                            ib = new IceBall(this.level, e.getX() - this.getX(), e.getY() + (double)(e.getBbHeight() / 2.0f) - (this.getY() + yoff), e.getZ() - this.getZ());
                            ib.moveTo(this.getX() - xzoff * Math.sin(Math.toRadians(this.yRot)), this.getY() + yoff, this.getZ() + xzoff * Math.cos(Math.toRadians(this.yRot)), this.yHeadRot, this.xRot);
                            ib.setSpecial();
                            ib.setIceBall();
                            var3 = e.getX() - ib.getX();
                            var5 = e.getY() + 0.25 - ib.getY();
                            var7 = e.getZ() - ib.getZ();
                            var9 = MathHelper.sqrt((double)(var3 * var3 + var7 * var7)) * 0.2f;
                            ib.shoot(var3, var5 + (double)var9, var7, 1.4f, 5.0f);
                            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new net.minecraft.util.ResourceLocation("random.fuse")), net.minecraft.util.SoundCategory.NEUTRAL, 1.0f, 1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                            this.level.addFreshEntity((Entity)ib);
                        }
                    }
                }
            } else {
                this.target_in_sight = false;
                this.flyaway = 0;
                this.setAttacking(0);
            }
        }
        if (this.currentFlightTarget.distSqr(this.getX(), this.getY(), this.getZ(), false) < 2.1f) {
            do_new = true;
        }
        if (do_new && !this.target_in_sight || do_new && this.flyaway != 0) {
            bid = Blocks.STONE;
            while (bid != Blocks.AIR && keep_trying != 0) {
                int gox = (int)this.getX();
                int goy = (int)this.getY();
                int goz = (int)this.getZ();
                if (has_owner && this.unstick_timer == 0) {
                    gox = (int)ox;
                    goy = (int)oy;
                    goz = (int)oz;
                    if (this.owner_flying == 0) {
                        zdir = this.level.random.nextInt(10) + 4;
                        xdir = this.level.random.nextInt(10) + 4;
                    } else {
                        zdir = this.level.random.nextInt(6);
                        xdir = this.level.random.nextInt(6);
                    }
                } else {
                    zdir = this.level.random.nextInt(10) + 16;
                    xdir = this.level.random.nextInt(10) + 16;
                }
                if (this.level.random.nextInt(2) == 1) {
                    zdir = - zdir;
                }
                if (this.level.random.nextInt(2) == 1) {
                    xdir = - xdir;
                }
                this.currentFlightTarget = new BlockPos(gox + xdir, goy + this.level.random.nextInt(9 + this.owner_flying * 2) - 4, goz + zdir);
                bid = this.level.getBlockState(this.currentFlightTarget).getBlock();
                if (bid == Blocks.AIR && !this.canSeeTarget((double)this.currentFlightTarget.getX(), (double)this.currentFlightTarget.getY(), (double)this.currentFlightTarget.getZ())) {
                    bid = Blocks.STONE;
                }
                --keep_trying;
            }
        }
        obstruction_factor = 0.0;
        // Match ThePrinceTeen: horizontal speed for kMax after targeting/damping, not at method entry.
        double velocity = Math.sqrt(this.getDeltaMovement().x * this.getDeltaMovement().x + this.getDeltaMovement().z * this.getDeltaMovement().z);
        // Match ThePrinceTeen: small inner loop only — large scanDist*2 loops stacked huge Y boosts (random "rocket up").
        int kMax = 2 + (int)(Math.max(0.0, velocity) * 4.0);
        if (kMax < 2) {
            kMax = 2;
        }
        if (kMax > 24) {
            kMax = 24;
        }
        for (int k = 1; k < kMax; ++k) {
            for (int i = 1; i < 4; ++i) {
                double dz;
                double dx = (double)i * Math.cos(Math.toRadians(this.yRot + 90.0f));
                bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)(this.getX() + dx), (int)this.getY() - k, (int)(this.getZ() + (dz = (double)i * Math.sin(Math.toRadians(this.yRot + 90.0f)))))).getBlock();
                if (bid == Blocks.AIR) continue;
                obstruction_factor += 0.05;
            }
        }
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, 0.0, obstruction_factor * 0.05, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addEntityY(this, obstruction_factor * 0.05);
        speed_factor = 0.5;
        var1 = (double)this.currentFlightTarget.getX() + 0.5 - this.getX();
        var3 = (double)this.currentFlightTarget.getY() + 0.1 - this.getY();
        var5 = (double)this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        if (this.owner_flying != 0) {
            speed_factor = 1.75;
            if (this.isTame() && this.getOwner() != null && this.distanceToSqr((Entity)(e = this.getOwner())) > 49.0) {
                speed_factor = 3.5;
            }
        }
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(this, (Math.signum(var1) - this.getDeltaMovement().x) * 0.15 * speed_factor, (Math.signum(var3) - this.getDeltaMovement().y) * 0.21 * speed_factor, (Math.signum(var5) - this.getDeltaMovement().z) * 0.15 * speed_factor);
        float var7 = (float)(Math.atan2(this.getDeltaMovement().z, this.getDeltaMovement().x) * 180.0 / 3.141592653589793) - 90.0f;
        float var8 = MathHelper.wrapDegrees((float)(var7 - this.yRot));
        this.yya = (float)(0.75 * speed_factor);
        this.yRot += var8 / 4.0f;
        // Movement comes from vanilla travel() in the same tick (Prince pattern); extra move() here fought physics.
    }

    @Override
    public void aiStep()
    {
      List list = null;
      Entity listEntity = null;

      double d6 = this.random.nextFloat() * 2.0F - 1.0F;
      double d7 = (this.random.nextInt(2) * 2 - 1) * 0.7D;

      double obstruction_factor = 0.0D;

      double relative_g = 0.0D;

      double max_speed = 0.95D;
      double gh = 1.0D;

      double rt = 0.0D;

      double pi = 3.1415926545D;
      double deltav = 0.0D;

      int dist = 2;

      BetterFireball bf = null;

      if (!this.isAlive()) {
        super.aiStep();
        return;
      }
      super.aiStep();

      if (this.level.isClientSide)
      {
        if (this.getActivity() != 0 && !this.getPassengers().isEmpty()) {
            Entity rider = this.getPassengers().get(0);
            if (rider instanceof ClientPlayerEntity) {
                ClientPlayerEntity pp = (ClientPlayerEntity)rider;
                pp.connection.send(new CPlayerPacket.RotationPacket(pp.yRot, pp.xRot, pp.isOnGround()));
                pp.connection.send(new CInputPacket(pp.xxa, pp.yya, pp.input.jumping, pp.input.shiftKeyDown));
            }
        }
        if ((this.boatPosRotationIncrements > 0) && (getActivity() != 0))
        {
          double d4 = this.getX() + (this.boatX - this.getX()) / this.boatPosRotationIncrements;
          double d5 = this.getY() + (this.boatY - this.getY()) / this.boatPosRotationIncrements;
          double d11 = this.getZ() + (this.boatZ - this.getZ()) / this.boatPosRotationIncrements;
          this.setPos(d4, d5, d11);

          this.xRot = (float)(this.xRot + (this.boatPitch - this.xRot) / this.boatPosRotationIncrements);
          double d10 = MathHelper.wrapDegrees(this.boatYaw - this.yRot);
          if ((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) != null) d10 = MathHelper.wrapDegrees((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)).yRot - this.yRot);
          this.yRot = (float)(this.yRot + d10 / this.boatPosRotationIncrements);
            this.yHeadRot = this.yRot;
          this.yHeadRot = this.yRot;

          this.boatPosRotationIncrements -= 1;
        }

      }
      else
      {
        if (this.getActivity() != 0 && !this.getPassengers().isEmpty()) {
            this.fly_with_rider();
        }

        always_do();
      }
    }
    public void updateRiderPosition() {
        if ((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) != null) {
            float f = 0.65f;
            this.getPassengers().get(0).setPos(this.getX() - (double)f * Math.sin(Math.toRadians(this.yRot)), this.getY() + this.getMountedYOffset() + this.getPassengers().get(0).getMyRidingOffset(), this.getZ() + (double)f * Math.cos(Math.toRadians(this.yRot)));
        }
    }

    protected void playTameEffect(boolean par1) {
        net.minecraft.particles.BasicParticleType type = par1 ? ParticleTypes.HEART : ParticleTypes.SMOKE;
        for (int i = 0; i < 20; ++i) {
            double d0 = this.random.nextGaussian() * 0.08;
            double d1 = this.random.nextGaussian() * 0.08;
            double d2 = this.random.nextGaussian() * 0.08;
            this.level.addParticle(type, this.getX() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), this.getY() + 0.5 + (double)this.random.nextFloat() * 1.5, this.getZ() + (double)((this.random.nextFloat() - this.random.nextFloat()) * 2.5f), d0, d1, d2);
        }
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity par1PlayerEntityEntity, Hand hand) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(hand);
        if (var2.isEmpty()) {
            var2 = null;
        } else if (var2.getCount() <= 0) {
            par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
            var2 = null;
        }
        if (!this.isTame()) {
            if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.level.isClientSide) {
                    if (this.level.random.nextInt(5) == 1) {
                        this.setTame(true);
                        this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.heal((float)this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                        this.level.broadcastEntityEvent(this, (byte)6);
                    }
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
        } else {
            if (!this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                return super.mobInteract(par1PlayerEntityEntity, hand);
            }
            if (var2 == null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
                if (!this.level.isClientSide) {
                    par1PlayerEntityEntity.startRiding(this);
                    this.setActivity(1);
                    this.setOrderedToSit(false);
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.BEEF && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Blocks.DEAD_BUSH.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.level.isClientSide) {
                    this.setTame(false);
                    this.setOwnerUUID((UUID)null);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.level.broadcastEntityEvent(this, (byte)6);
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Blocks.ICE.asItem() && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.setDragonFire(0);
                    par1PlayerEntityEntity.sendMessage(new StringTextComponent("Dragon fireballs extinguished."), par1PlayerEntityEntity.getUUID());
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.FLINT_AND_STEEL && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.setDragonFire(1);
                    par1PlayerEntityEntity.sendMessage(new StringTextComponent("Dragon fireballs lit!"), par1PlayerEntityEntity.getUUID());
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.GUNPOWDER && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && this.getDragonFire() > 0) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)6);
                    this.setDragonFire(2);
                    par1PlayerEntityEntity.sendMessage(new StringTextComponent("Dragon fireballs supercharged!"), par1PlayerEntityEntity.getUUID());
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.SNOWBALL && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                this.dragontype = 1;
                this.setDragonType(this.dragontype);
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.COAL && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent(this, (byte)7);
                }
                this.dragontype = 0;
                this.setDragonType(this.dragontype);
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && var2.getItem() == Items.DIAMOND && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && !this.level.isClientSide) {
                Entity ent = null;
                Spyro d = null;
                ent = Dragon.spawnCreature((World)this.level, (String)"Baby Dragon", (double)this.getX(), (double)this.getY(), (double)this.getZ());
                if (ent != null) {
                    d = (Spyro)ent;
                    if (this.isTame()) {
                        d.setTame(true);
                        d.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                    }
                    this.remove();
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (this.isTame() && var2 != null && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                this.setCustomName(var2.getHoverName());
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.setItemInHand(hand, ItemStack.EMPTY);
                    }
                }
                return ActionResultType.SUCCESS;
            }
            if (var2 != null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 25.0) {
                if (!this.isOrderedToSit()) {
                    this.setOrderedToSit(true);
                    this.setActivity(0);
                } else {
                    this.setOrderedToSit(false);
                    this.setActivity(0);
                }
                return ActionResultType.SUCCESS;
            }
        }
        return super.mobInteract(par1PlayerEntityEntity, hand);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.BEEF;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(ATTACKING, (byte)par1);
    }

    public int getActivity() {
        return this.entityData.get(STATE2).byteValue();
    }

    public void setActivity(int par1) {
        if (this.level != null && this.level.isClientSide) {
            return;
        }
        this.entityData.set(STATE2, (byte)par1);
    }

    public int getDragonFire() {
        return this.entityData.get(INT24).intValue();
    }

    public void setDragonFire(int par1) {
        if (this.level.isClientSide) {
            return;
        }
        this.entityData.set(INT24, par1);
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new net.minecraft.util.ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_")));
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
        }
        return var8;
    }

    public void setDragonType(int par1) {
        this.entityData.set(INT22, par1);
    }

    public int getDragonType() {
        return this.entityData.get(INT22).intValue();
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        if ((this.getPassengers().isEmpty() ? null : this.getPassengers().get(0)) != null) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("DragonAttacking", this.getAttacking());
        par1CompoundNBT.putInt("DragonActivity", this.getActivity());
        par1CompoundNBT.putInt("DragonFire", this.getDragonFire());
        par1CompoundNBT.putInt("DragonType", this.getDragonType());
    }

    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.setAttacking(par1CompoundNBT.getInt("DragonAttacking"));
        this.setActivity(par1CompoundNBT.getInt("DragonActivity"));
        this.setDragonFire(par1CompoundNBT.getInt("DragonFire"));
        this.dragontype = par1CompoundNBT.getInt("DragonType");
        this.setDragonType(this.dragontype);
    }
}

