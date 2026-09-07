package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.BetterFireball;
import com.astryxion.chaospersists.item.IceBall;
import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.item.ThunderBolt;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class TheKing extends Monster {
    private static final EntityDataAccessor<Byte> BYTE20 =
            SynchedEntityData.defineId(TheKing.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> PLAY_NICELY =
            SynchedEntityData.defineId(TheKing.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IS_END =
            SynchedEntityData.defineId(TheKing.class, EntityDataSerializers.INT);

    private BlockPos currentFlightTarget = null;
    private final GenericTargetSorter targetSorter;
    private LivingEntity rt = null;
    private double attdam = 250.0;
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
    private int headEntityId = -1;
    private int wing_sound = 0;
    private int large_unknown_detected = 0;
    private int isEnd = 0;
    private int endCounter = 0;

    public TheKing(EntityType<? extends TheKing> type, Level level) {
        super(type, level);
        this.attdam = ChaosPersists.TheKing_stats.attack;
        this.xpReward = 25000;
        this.fireImmune();
        this.noPhysics = true;
        this.targetSorter = new GenericTargetSorter(this);
        this.getNavigation().setCanFloat(true);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.TheKing_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.6200000047683716)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.TheKing_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.TheKing_stats.defense);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BYTE20, (byte) 0);
        this.entityData.define(PLAY_NICELY, ChaosPersists.PlayNicely);
        this.entityData.define(IS_END, this.isEnd);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.refreshDimensions();
        MyUtils.ensureBossMaxHealth(this, this.mygetMaxHealth());
        if (!this.level().isClientSide && this.tickCount <= 1) {
            this.setHealth((float) this.mygetMaxHealth());
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (ChaosPersists.PlayNicely == 0) {
            return EntityDimensions.scalable(22.0f, 24.0f);
        }
        return EntityDimensions.scalable(5.5f, 6.0f);
    }

    public int getPlayNicely() {
        return this.entityData.get(PLAY_NICELY);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldRenderAtSqrDistance(double distance) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    public final int getAttacking() {
        return this.entityData.get(BYTE20);
    }

    public final void setAttacking(int par1) {
        this.entityData.set(BYTE20, (byte) par1);
    }

    @Override
    protected float getSoundVolume() {
        return 1.35f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ChaosSounds.KING_LIVING;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.KING_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.TREX_DEATH;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public int mygetMaxHealth() {
        return ChaosPersists.TheKing_stats.health;
    }

    private void dropRegistryItem(String path, int par1) {
        Item item =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", path));
        if (item != null) {
            this.dropItemRand(item, par1);
        }
    }

    private void dropItemRand(Item index, int par1) {
        if (index == null) {
            return;
        }
        ItemEntity var3 =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(20)
                                - (double) ChaosPersists.ChaosRand.nextInt(20),
                        this.getY() + 12.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(20)
                                - (double) ChaosPersists.ChaosRand.nextInt(20),
                        new ItemStack(index, par1));
        this.level().addFreshEntity(var3);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        Item it;
        Block bl;
        spawnCreature(this.level(), "The Prince", this.getX(), this.getY() + 10.0, this.getZ());
        this.dropRegistryItem("royal_chest", 1);
        this.dropRegistryItem("royal_helmet", 1);
        this.dropRegistryItem("royal_leggings", 1);
        this.dropRegistryItem("royal_boots", 1);
        this.dropRegistryItem("royalsmall", 1);
        List<Item> itemList = new ArrayList<>();
        for (Item itemObj : ForgeRegistries.ITEMS.getValues()) {
            if (itemObj != null) {
                itemList.add(itemObj);
            }
        }
        int icount = itemList.size();
        int j = 0;
        while (j < 150) {
            it = itemList.get(this.level().getRandom().nextInt(icount));
            if (it == null) {
                continue;
            }
            ++j;
            this.dropItemRand(it, 1);
        }
        List<Block> blockList = new ArrayList<>();
        for (Block blk : ForgeRegistries.BLOCKS.getValues()) {
            if (blk != null) {
                blockList.add(blk);
            }
        }
        int bcount = blockList.size();
        j = 0;
        while (j < 150) {
            bl = blockList.get(this.level().getRandom().nextInt(bcount));
            if (bl == null) {
                continue;
            }
            ++j;
            this.dropItemRand(Item.byBlock(bl), 1);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.wing_sound += 1;
        if (this.wing_sound > 30) {
            if (!this.level().isClientSide && ChaosSounds.MOTHRA_WINGS != null) {
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                ChaosSounds.MOTHRA_WINGS,
                                this.getSoundSource(),
                                1.75f,
                                0.75f);
            }
            this.wing_sound = 0;
        }
        this.noPhysics = true;
        Vec3 dm = this.getDeltaMovement();
        this.setDeltaMovement(dm.x, dm.y * 0.6, dm.z);
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() * 2 / 3)) {
            this.attdam = ChaosPersists.TheKing_stats.attack * 2;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 2)) {
            this.attdam = ChaosPersists.TheKing_stats.attack * 4;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 4)) {
            this.attdam = ChaosPersists.TheKing_stats.attack * 8;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 8)) {
            this.attdam = ChaosPersists.TheKing_stats.attack * 16;
        }
        if (this.level().isClientSide) {
            float f = 7.0f;
            this.isEnd = this.entityData.get(IS_END);
            if (this.isEnd != 0 && this.level().getRandom().nextInt(3) == 1) {
                for (int i = 0; i < 10; i++) {
                    this.level()
                            .addParticle(
                                    ParticleTypes.FIREWORK,
                                    this.getX() - (double) f * Math.sin(Math.toRadians(this.getYRot())),
                                    this.getY() + 14.0,
                                    this.getZ() + (double) f * Math.cos(Math.toRadians(this.getYRot())),
                                    (this.level().getRandom().nextGaussian()
                                                    - this.level().getRandom().nextGaussian())
                                            / 4.0
                                            + dm.x * 6.0,
                                    (this.level().getRandom().nextGaussian()
                                                    - this.level().getRandom().nextGaussian())
                                            / 4.0,
                                    (this.level().getRandom().nextGaussian()
                                                    - this.level().getRandom().nextGaussian())
                                            / 4.0
                                            + dm.z * 6.0);
                }
            }
        }
    }

    private void discardAttachedHeads() {
        if (this.headEntityId >= 0) {
            Entity head = this.level().getEntity(this.headEntityId);
            if (head != null) {
                head.discard();
            }
            this.headEntityId = -1;
        }
        AABB box = this.getBoundingBox().inflate(64.0, 64.0, 64.0);
        for (KingHead head : this.level().getEntitiesOfClass(KingHead.class, box)) {
            head.discard();
        }
        this.head_found = 0;
    }

    @Override
    public void die(DamageSource source) {
        this.discardAttachedHeads();
        super.die(source);
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        this.discardAttachedHeads();
        super.remove(reason);
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        if (par1Entity instanceof LivingEntity living) {
            float s = living.getBbHeight() * living.getBbWidth();
            if (s > 30.0f
                    && !MyUtils.isRoyalty(par1Entity)
                    && !isGodzilla(par1Entity)
                    && !isGodzillaHead(par1Entity)
                    && !(par1Entity instanceof PitchBlack)
                    && !(par1Entity instanceof Kraken)) {
                living.setHealth(living.getHealth() / 2.0f);
                living.hurt(this.damageSources().mobAttack(this), (float) this.attdam * 10.0f);
                this.large_unknown_detected = 1;
            }
        }
        if (par1Entity instanceof EnderDragon dr) {
            DamageSource var21 = this.damageSources().explosion(null);
            if (this.getRandom().nextInt(6) == 1) {
                dr.hurt(dr.head, var21, (float) this.attdam);
            } else {
                dr.hurt(var21, (float) this.attdam);
            }
        }
        boolean var4 = par1Entity.hurt(this.damageSources().mobAttack(this), (float) this.attdam);
        if (var4 && par1Entity instanceof LivingEntity living) {
            double ks = 3.3;
            double inair = 0.25;
            float f3 = (float) Math.atan2(living.getZ() - this.getZ(), living.getX() - this.getX());
            inair += (double) this.getRandom().nextFloat() * 0.25;
            if (!living.isAlive() || par1Entity instanceof Player) {
                inair *= 1.5;
            }
            living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
        }
        return var4;
    }

    public boolean canSeeTarget(double pX, double pY, double pZ) {
        return this.level()
                        .clip(
                                new net.minecraft.world.level.ClipContext(
                                        new Vec3(this.getX(), this.getY() + 8.75, this.getZ()),
                                        new Vec3(pX, pY, pZ),
                                        net.minecraft.world.level.ClipContext.Block.COLLIDER,
                                        net.minecraft.world.level.ClipContext.Fluid.NONE,
                                        this))
                        .getType()
                == net.minecraft.world.phys.HitResult.Type.MISS;
    }

    private boolean tooFarFromHome() {
        float d1 = (float) (this.getX() - (double) this.homex);
        float d2 = (float) (this.getZ() - (double) this.homez);
        d1 = (float) Math.sqrt(d1 * d1 + d2 * d2);
        return d1 > 120.0f;
    }

    private void msgToPlayers(String s) {
        List<Player> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Player.class, this.getBoundingBox().inflate(80.0, 64.0, 80.0));
        Collections.sort(var5, this.targetSorter);
        for (Player var4 : var5) {
            var4.sendSystemMessage(Component.literal(s));
        }
    }

    private Player findNearestPlayer() {
        List<Player> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Player.class, this.getBoundingBox().inflate(80.0, 64.0, 80.0));
        Collections.sort(var5, this.targetSorter);
        Player var4 = null;
        for (Player var3 : var5) {
            var4 = var3;
        }
        return var4;
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (MyUtils.usesChaosFlight(this)) {
            return;
        }
        super.travel(travelVector);
    }
    @Override
    protected void customServerAiStep() {
        int xdir = 1;
        int zdir = 1;
        int attrand = 5;
        LivingEntity e = null;
        LivingEntity f = null;
        double rr;
        double rhdir;
        double rdd;
        double pi = 3.1415926545;
        double var1;
        double var3;
        double var5;
        float var7;
        float var8;
        int which;
        Player p;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        this.entityData.set(IS_END, this.isEnd);
        this.entityData.set(PLAY_NICELY, ChaosPersists.PlayNicely);
        if (this.isEnd == 1) {
            this.endCounter += 1;
            this.noPhysics = true;
            this.setDeltaMovement(0.0, 0.0, 0.0);
            this.hurt_timer = 10;
            if (this.isDeadOrDying()) {
                return;
            }
            p = this.findNearestPlayer();
            if (p != null) {
                MyUtils.faceEntity(this, p, 10.0f, 10.0f);
                p.setDeltaMovement(0.0, 0.0, 0.0);
                double dd0 = this.getX() - p.getX();
                double dd1 = this.getZ() - p.getZ();
                float f2 = (float) (Math.atan2(dd1, dd0) * 180.0 / Math.PI) - 90.0f;
                p.setYRot(f2);
                p.setHealth(1.0f);
            }
            if (this.endCounter == 10) {
                this.msgToPlayers(
                        "The King: Enough of this charade. I am done. You have shown me what I wanted to know.");
                return;
            }
            if (this.endCounter == 80) {
                this.msgToPlayers(
                        "The King: That's right my little pet. It has all been a game. You never killed me. You can't.");
                return;
            }
            if (this.endCounter == 160) {
                this.msgToPlayers(
                        "The King: I am the one. The only. The many. I exist within both space and time. Everywhere and always.");
                return;
            }
            if (this.endCounter == 240) {
                this.msgToPlayers(
                        "The King: I used you to learn your ways, and I have reached my conclusion on your species.");
                return;
            }
            if (this.endCounter == 300) {
                this.msgToPlayers("The King: You have 10 seconds to run...");
                return;
            }
            if (this.endCounter == 320) {
                this.msgToPlayers("9.");
                return;
            }
            if (this.endCounter == 340) {
                this.msgToPlayers("8.");
                return;
            }
            if (this.endCounter == 360) {
                this.msgToPlayers("7.");
                return;
            }
            if (this.endCounter == 380) {
                this.msgToPlayers("6.");
                return;
            }
            if (this.endCounter == 400) {
                this.msgToPlayers("5.");
                return;
            }
            if (this.endCounter == 420) {
                this.msgToPlayers("4.");
                return;
            }
            if (this.endCounter == 440) {
                this.msgToPlayers("3.");
                return;
            }
            if (this.endCounter == 460) {
                this.msgToPlayers("2.");
                return;
            }
            if (this.endCounter == 480) {
                this.msgToPlayers("1.");
                return;
            }
            if (this.endCounter == 500) {
                this.msgToPlayers("The King: Prepare to die!");
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
            if (this.backoff_timer > 0) {
                this.backoff_timer -= 1;
            }
        }
        if (this.hurt_timer > 0) {
            this.hurt_timer -= 1;
        }
        if ((this.homex == 0 && this.homez == 0) || this.guard_mode == 0) {
            this.homex = (int) this.getX();
            this.homez = (int) this.getZ();
        }
        this.ticker += 1;
        if (this.ticker > 30000) {
            this.ticker = 0;
        }
        if (this.ticker % 80 == 0) {
            this.stream_count = 10;
        }
        if (this.ticker % 90 == 0) {
            this.stream_count_l = 5;
        }
        if (this.ticker % 70 == 0) {
            this.stream_count_i = 8;
        }
        if (this.backoff_timer > 0) {
            this.backoff_timer -= 1;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 2)) {
            attrand = 3;
        }
        this.noPhysics = true;
        if (this.currentFlightTarget == null) {
            this.currentFlightTarget = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        }
        if (this.tooFarFromHome()
                || this.getRandom().nextInt(200) == 0
                || this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ())
                        < 9.1) {
            zdir = this.getRandom().nextInt(120);
            xdir = this.getRandom().nextInt(120);
            if (this.getRandom().nextInt(2) == 0) {
                zdir = -zdir;
            }
            if (this.getRandom().nextInt(2) == 0) {
                xdir = -xdir;
            }
            int dist = 0;
            for (int i = -5; i <= 5; i += 5) {
                for (int j = -5; j <= 5; j += 5) {
                    Block bid =
                            this.level()
                                    .getBlockState(
                                            new BlockPos(
                                                    this.homex + j, (int) this.getY(), this.homez + i))
                                    .getBlock();
                    if (!bid.defaultBlockState().isAir()) {
                        for (int k = 1; k < 20; k++) {
                            bid =
                                    this.level()
                                            .getBlockState(
                                                    new BlockPos(
                                                            this.homex + j,
                                                            (int) this.getY() + k,
                                                            this.homez + i))
                                            .getBlock();
                            dist++;
                            if (bid.defaultBlockState().isAir()) {
                                break;
                            }
                        }
                    }
                    for (int k = 1; k < 20; k++) {
                        bid =
                                this.level()
                                        .getBlockState(
                                                new BlockPos(
                                                        this.homex + j,
                                                        (int) this.getY() - k,
                                                        this.homez + i))
                                        .getBlock();
                        dist--;
                        if (!bid.defaultBlockState().isAir()) {
                            break;
                        }
                    }
                }
            }
            dist = dist / 9 + 2;
            if ((int) (this.getY() + (double) dist) > 230) {
                dist = 230 - (int) this.getY();
            }
            this.currentFlightTarget =
                    new BlockPos(this.homex + xdir, (int) (this.getY() + (double) dist), this.homez + zdir);
        } else if (this.getRandom().nextInt(attrand) == 0) {
            e = this.rt;
            if (ChaosPersists.PlayNicely != 0) {
                e = null;
            }
            if (e != null
                    && (e instanceof TheKing || e instanceof KingHead)) {
                this.rt = null;
                e = null;
            }
            if (e != null) {
                float d1 = (float) (e.getX() - (double) this.homex);
                float d2 = (float) (e.getZ() - (double) this.homez);
                d1 = (float) Math.sqrt(d1 * d1 + d2 * d2);
                if (e.isDeadOrDying()
                        || this.getRandom().nextInt(250) == 1
                        || (d1 > 128.0f && this.guard_mode == 1)) {
                    e = null;
                    this.rt = null;
                }
                if (e != null && !this.MyCanSee(e)) {
                    e = null;
                }
            }
            f = this.findSomethingToAttack();
            if (this.head_found == 0) {
                Entity spawned = spawnCreature(this.level(), "KingHead", this.getX(), this.getY() + 20.0, this.getZ());
                if (spawned != null) {
                    this.head_found = 1;
                    this.headEntityId = spawned.getId();
                }
            }
            if (e == null) {
                e = f;
            }
            if (e != null) {
                this.setAttacking(1);
                if (this.backoff_timer == 0) {
                    int dist = (int) (e.getY() + (double) (e.getBbHeight() / 2.0f) + 1.0);
                    if (dist > 230) {
                        dist = 230;
                    }
                    this.currentFlightTarget = new BlockPos((int) e.getX(), dist, (int) e.getZ());
                    if (this.getRandom().nextInt(70) == 1) {
                        this.backoff_timer = 80 + this.getRandom().nextInt(80);
                    }
                } else if (this.currentFlightTarget.distToCenterSqr(this.getX(), this.getY(), this.getZ())
                        < 9.1) {
                    zdir = this.getRandom().nextInt(20) + 30;
                    xdir = this.getRandom().nextInt(20) + 30;
                    if (this.getRandom().nextInt(2) == 0) {
                        zdir = -zdir;
                    }
                    if (this.getRandom().nextInt(2) == 0) {
                        xdir = -xdir;
                    }
                    int dist = 0;
                    for (int i = -5; i <= 5; i += 5) {
                        for (int j = -5; j <= 5; j += 5) {
                            Block bid =
                                    this.level()
                                            .getBlockState(
                                                    new BlockPos(
                                                            (int) e.getX() + j,
                                                            (int) this.getY(),
                                                            (int) e.getZ() + i))
                                            .getBlock();
                            if (!bid.defaultBlockState().isAir()) {
                                for (int k = 1; k < 20; k++) {
                                    bid =
                                            this.level()
                                                    .getBlockState(
                                                            new BlockPos(
                                                                    (int) e.getX() + j,
                                                                    (int) this.getY() + k,
                                                                    (int) e.getZ() + i))
                                                    .getBlock();
                                    dist++;
                                    if (bid.defaultBlockState().isAir()) {
                                        break;
                                    }
                                }
                            }
                            for (int k = 1; k < 20; k++) {
                                bid =
                                        this.level()
                                                .getBlockState(
                                                        new BlockPos(
                                                                (int) e.getX() + j,
                                                                (int) this.getY() - k,
                                                                (int) e.getZ() + i))
                                                .getBlock();
                                dist--;
                                if (!bid.defaultBlockState().isAir()) {
                                    break;
                                }
                            }
                        }
                    }
                    dist = dist / 9 + 2;
                    if ((int) (this.getY() + (double) dist) > 230) {
                        dist = 230 - (int) this.getY();
                    }
                    this.currentFlightTarget =
                            new BlockPos(
                                    (int) e.getX() + xdir,
                                    (int) (this.getY() + (double) dist),
                                    (int) e.getZ() + zdir);
                }
                if (this.distanceToSqr(e) < 900.0) {
                    if (this.getRandom().nextInt(2) == 1) {
                        this.doJumpDamage(
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                15.0,
                                (double) (ChaosPersists.TheKing_stats.attack / 4),
                                0);
                    }
                    this.doHurtTarget(e);
                }
                double dx =
                        this.getX() + 20.0 * Math.sin(Math.toRadians((double) this.getYHeadRot()));
                double dz =
                        this.getZ()
                                - 20.0 * Math.cos(Math.toRadians((double) this.getYHeadRot()));
                if (this.getRandom().nextInt(3) == 1) {
                    this.doJumpDamage(
                            dx,
                            this.getY() + 10.0,
                            dz,
                            15.0,
                            (double) (ChaosPersists.TheKing_stats.attack / 2),
                            1);
                }
                if (this.getHorizontalDistanceSqToEntity(e) > 900.0) {
                    which = this.getRandom().nextInt(3);
                    if (which == 0) {
                        if (this.stream_count > 0) {
                            this.setAttacking(1);
                            rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                            rhdir = Math.toRadians((this.getYHeadRot() + 90.0f) % 360.0f);
                            rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                            if (rdd > pi) {
                                rdd -= pi * 2.0;
                            }
                            rdd = Math.abs(rdd);
                            if (rdd < 0.5) {
                                this.firecanon(e);
                            }
                        }
                    } else if (which == 1) {
                        if (this.stream_count_l > 0) {
                            this.setAttacking(1);
                            rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                            rhdir = Math.toRadians((this.getYHeadRot() + 90.0f) % 360.0f);
                            rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                            if (rdd > pi) {
                                rdd -= pi * 2.0;
                            }
                            rdd = Math.abs(rdd);
                            if (rdd < 0.5) {
                                this.firecanonl(e);
                            }
                        }
                    } else if (this.stream_count_i > 0) {
                        this.setAttacking(1);
                        rr = Math.atan2(e.getZ() - this.getZ(), e.getX() - this.getX());
                        rhdir = Math.toRadians((this.getYHeadRot() + 90.0f) % 360.0f);
                        rdd = Math.abs(rr - rhdir) % (pi * 2.0);
                        if (rdd > pi) {
                            rdd -= pi * 2.0;
                        }
                        rdd = Math.abs(rdd);
                        if (rdd < 0.5) {
                            this.firecanoni(e);
                        }
                    }
                }
            } else {
                this.setAttacking(0);
                this.stream_count = 10;
                this.stream_count_l = 5;
                this.stream_count_i = 8;
            }
        }
        if (this.getAttacking() != 0 && this.isEnd == 2) {
            double xzoff = 10.0;
            double yoff = 14.0;
            Entity ppwr =
                    spawnCreature(
                            this.level(),
                            "PurplePower",
                            this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot())),
                            this.getY() + yoff,
                            this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())));
            if (ppwr instanceof PurplePower pwr) {
                Vec3 kingMotion = this.getDeltaMovement();
                pwr.setDeltaMovement(kingMotion.x * 3.0, kingMotion.y * 3.0, kingMotion.z * 3.0);
                pwr.setPurpleType(10);
            }
        }
        var1 = (double) this.currentFlightTarget.getX() + 0.5 - this.getX();
        var3 = (double) this.currentFlightTarget.getY() + 0.1 - this.getY();
        var5 = (double) this.currentFlightTarget.getZ() + 0.5 - this.getZ();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(
                motion.add(
                        (Math.signum(var1) * 0.7 - motion.x) * 0.35,
                        (Math.signum(var3) * 0.69999 - motion.y) * 0.3,
                        (Math.signum(var5) * 0.7 - motion.z) * 0.35));
        motion = this.getDeltaMovement();
        var7 = (float) (Math.atan2(motion.z, motion.x) * 180.0 / Math.PI) - 90.0f;
        var8 = Mth.wrapDegrees(var7 - this.getYRot());
        this.setYRot(this.getYRot() + var8 / 8.0f);
        if (this.getRandom().nextInt(30) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(5.0f);
            if (this.large_unknown_detected != 0) {
                this.heal(200.0f);
            }
        }
        if (this.player_hit_count < 10 && this.getHealth() < 2000.0f) {
            this.heal(2000.0f - this.getHealth());
        }
        MyUtils.applyChaosFlightMovement(this);
}

    private double getHorizontalDistanceSqToEntity(Entity e) {
        double d1 = e.getZ() - this.getZ();
        double d2 = e.getX() - this.getX();
        return d1 * d1 + d2 * d2;
    }

    private void firecanon(LivingEntity e) {
        double yoff = 14.0;
        double xzoff = 32.0;
        BetterFireball bf;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        if (this.stream_count > 0) {
            bf =
                    new BetterFireball(
                            this.level(),
                            this,
                            e.getX() - cx,
                            e.getY() + (double) (e.getBbHeight() / 2.0f) - (this.getY() + yoff),
                            e.getZ() - cz);
            bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
            bf.setReallyBig();
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.TNT_PRIMED,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(bf);
            for (int i = 0; i < 6; i++) {
                float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                bf =
                        new BetterFireball(
                                this.level(),
                                this,
                                e.getX() - cx + (double) r1,
                                e.getY()
                                                + (double) (e.getBbHeight() / 2.0f)
                                                - (this.getY() + yoff)
                                        + (double) r2,
                                e.getZ() - cz + (double) r3);
                bf.moveTo(cx, this.getY() + yoff, cz, this.getYRot(), 0.0f);
                bf.setBig();
                if (this.getRandom().nextInt(2) == 1) {
                    bf.setSmall();
                }
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                1.0f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(bf);
            }
            this.stream_count -= 1;
        }
    }

    private void firecanonl(LivingEntity e) {
        double yoff = 14.0;
        double xzoff = 32.0;
        double var3;
        double var5;
        double var7;
        float var9;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        if (this.stream_count_l > 0) {
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            for (int i = 0; i < 3; i++) {
                float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                ThunderBolt lb =
                        new ThunderBolt(
                                ChaosPersists.ENTITY_TYPE_THUNDER_BOLT.get(),
                                cx,
                                this.getY() + yoff,
                                cz,
                                this.level());
                lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
                var3 = e.getX() - lb.getX() + (double) r1;
                var5 = e.getY() + 0.25 - lb.getY() + (double) r2;
                var7 = e.getZ() - lb.getZ() + (double) r3;
                var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
                Vec3 lbdm = lb.getDeltaMovement();
                lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
                this.level().addFreshEntity(lb);
            }
            this.stream_count_l -= 1;
        }
    }

    private void firecanoni(LivingEntity e) {
        double yoff = 14.0;
        double xzoff = 32.0;
        double var3;
        double var5;
        double var7;
        float var9;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        if (this.stream_count_i > 0) {
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            for (int i = 0; i < 5; i++) {
                float r1 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r2 = 3.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                float r3 = 5.0f * (this.getRandom().nextFloat() - this.getRandom().nextFloat());
                IceBall lb =
                        new IceBall(
                                ChaosPersists.ENTITY_TYPE_ICE_BALL.get(),
                                cx,
                                this.getY() + yoff,
                                cz,
                                this.level());
                lb.setIceMaker(1);
                lb.moveTo(cx, this.getY() + yoff, cz, 0.0f, 0.0f);
                var3 = e.getX() - lb.getX() + (double) r1;
                var5 = e.getY() + 0.25 - lb.getY() + (double) r2;
                var7 = e.getZ() - lb.getZ() + (double) r3;
                var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
                lb.shoot(var3, var5 + (double) var9, var7, 1.4f, 4.0f);
                Vec3 lbdm = lb.getDeltaMovement();
                lb.setDeltaMovement(lbdm.x * 3.0, lbdm.y * 3.0, lbdm.z * 3.0);
                this.level().addFreshEntity(lb);
            }
            this.stream_count_i -= 1;
        }
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    protected float getDamageAfterArmorAbsorb(DamageSource damageSource, float damageAmount) {
        return Math.min(super.getDamageAfterArmorAbsorb(damageSource, damageAmount), 120.0f);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        float dm = par2;
        if (this.hurt_timer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(par1DamageSource)) {
            return false;
        }
        if (dm > 750.0f) {
            dm = 750.0f;
        }
        if (dm > 120.0f && par1DamageSource.is(DamageTypeTags.BYPASSES_ARMOR)) {
            dm = 120.0f;
        }
        if (par1DamageSource.is(DamageTypes.IN_WALL)) {
            return false;
        }
        Entity ent = par1DamageSource.getEntity();
        if (ent instanceof LivingEntity enl) {
            float s = enl.getBbHeight() * enl.getBbWidth();
            if (s > 30.0f
                    && !MyUtils.isRoyalty(ent)
                    && !isGodzilla(ent)
                    && !isGodzillaHead(ent)
                    && !(ent instanceof PitchBlack)
                    && !(ent instanceof Kraken)) {
                dm /= 10.0f;
                this.large_unknown_detected = 1;
            }
            if (ent instanceof Monster && s < 3.0f) {
                ent.discard();
                return false;
            }
        }
        if (!par1DamageSource.is(DamageTypes.CACTUS)) {
            ret = super.hurt(par1DamageSource, dm);
            if (ret) {
                this.hurt_timer = 20;
            }
            if (ent instanceof Player) {
                this.player_hit_count += 1;
            }
            if (ent instanceof LivingEntity living && this.currentFlightTarget != null) {
                if (!MyUtils.isRoyalty(ent)) {
                    this.rt = living;
                    int dist = (int) living.getY();
                    if (dist > 230) {
                        dist = 230;
                    }
                    this.currentFlightTarget = new BlockPos((int) living.getX(), dist, (int) living.getZ());
                }
            }
        }
        return ret;
    }

    @Override
    public int getArmorValue() {
        if (this.large_unknown_detected != 0) {
            return 25;
        }
        if (this.player_hit_count < 10
                && this.getHealth() < (float) (this.mygetMaxHealth() * 2 / 3)) {
            return ChaosPersists.TheKing_stats.defense + 1;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 2)) {
            return ChaosPersists.TheKing_stats.defense + 2;
        }
        if (this.player_hit_count < 10 && this.getHealth() < (float) (this.mygetMaxHealth() / 4)) {
            return ChaosPersists.TheKing_stats.defense + 3;
        }
        return ChaosPersists.TheKing_stats.defense;
    }

    public boolean MyCanSee(LivingEntity e) {
        double xzoff = 22.0;
        int nblks = 20;
        double cx = this.getX() - xzoff * Math.sin(Math.toRadians(this.getYRot()));
        double cz = this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot()));
        float startx = (float) cx;
        float starty = (float) (this.getY() + (double) (this.getBbHeight() * 7.0f / 8.0f));
        float startz = (float) cz;
        float dx = (float) ((e.getX() - (double) startx) / 20.0);
        float dy = (float) ((e.getY() + (double) (e.getBbHeight() / 2.0f) - (double) starty) / 20.0);
        float dz = (float) ((e.getZ() - (double) startz) / 20.0);
        if (Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int) ((double) nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if (Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int) ((double) nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if (Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int) ((double) nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; i++) {
            startx += dx;
            starty += dy;
            startz += dz;
            BlockState bstate =
                    this.level()
                            .getBlockState(new BlockPos((int) startx, (int) starty, (int) startz));
            if (!bstate.isAir()
                    && !bstate.is(Blocks.WATER)
                    && !bstate.is(Blocks.KELP)
                    && !bstate.is(Blocks.KELP_PLANT)
                    && !bstate.is(Blocks.VINE)
                    && !bstate.is(net.minecraft.tags.BlockTags.LEAVES)) {
                return false;
            }
        }
        return true;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (par1EntityLiving instanceof KingHead) {
            this.head_found = 1;
            return false;
        }
        if (MyUtils.isRoyalty(par1EntityLiving)) {
            return false;
        }
        float d1 = (float) (par1EntityLiving.getX() - (double) this.homex);
        float d2 = (float) (par1EntityLiving.getZ() - (double) this.homez);
        d1 = (float) Math.sqrt(d1 * d1 + d2 * d2);
        if (d1 > 144.0f) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (this.isEnd == 2) {
            if (par1EntityLiving instanceof Player player) {
                return !player.getAbilities().instabuild;
            }
            if (par1EntityLiving instanceof Girlfriend
                    || "Girlfriend".equals(par1EntityLiving.getClass().getSimpleName())) {
                return true;
            }
            if (par1EntityLiving instanceof Boyfriend
                    || "Boyfriend".equals(par1EntityLiving.getClass().getSimpleName())) {
                return true;
            }
            if (par1EntityLiving instanceof Villager) {
                return true;
            }
        }
        if (!this.MyCanSee(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            return !player.getAbilities().instabuild;
        }
        if (par1EntityLiving instanceof Horse) {
            return true;
        }
        if (par1EntityLiving instanceof Monster) {
            return true;
        }
        if (par1EntityLiving instanceof EnderDragon) {
            return true;
        }
        return MyUtils.isAttackableNonMob(par1EntityLiving);
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            this.head_found = 1;
            return null;
        }
        if (this.isEnd == 2) {
            List<Player> var5p =
                    this.level()
                            .getEntitiesOfClass(
                                    Player.class, this.getBoundingBox().inflate(80.0, 64.0, 80.0));
            Collections.sort(var5p, this.targetSorter);
            for (LivingEntity var4p : var5p) {
                if (this.isSuitableTarget(var4p, false)) {
                    return var4p;
                }
            }
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(80.0, 64.0, 80.0));
        Collections.sort(var5, this.targetSorter);
        LivingEntity ret = null;
        this.head_found = 0;
        for (LivingEntity var4 : var5) {
            if (this.isSuitableTarget(var4, false)) {
                if (ret == null) {
                    ret = var4;
                }
            }
            if (ret != null && this.head_found != 0) {
                continue;
            }
        }
        return ret;
    }

    public void setGuardMode(int i) {
        this.guard_mode = i;
    }

    public void setFree() {
        this.isEnd = 1;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("KingHomeX", this.homex);
        tag.putInt("KingHomeZ", this.homez);
        tag.putInt("GuardMode", this.guard_mode);
        tag.putInt("PlayerHits", this.player_hit_count);
        tag.putInt("IsEnd", this.isEnd);
        tag.putInt("EndCounter", this.endCounter);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.homex = tag.getInt("KingHomeX");
        this.homez = tag.getInt("KingHomeZ");
        this.guard_mode = tag.getInt("GuardMode");
        this.player_hit_count = tag.getInt("PlayerHits");
        this.isEnd = tag.getInt("IsEnd");
        this.endCounter = tag.getInt("EndCounter");
    }

    public static Entity spawnCreature(Level level, String par1, double par2, double par4, double par6) {
        ResourceLocation res = resolveSpawnId(par1);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(par2, par4, par6, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    private static ResourceLocation resolveSpawnId(String par1) {
        if (par1.contains(":")) {
            return new ResourceLocation(par1);
        }
        return switch (par1) {
            case "KingHead" -> new ResourceLocation("chaospersists", "king_head");
            case "PurplePower" -> new ResourceLocation("chaospersists", "purple_power");
            case "The Prince" -> new ResourceLocation("chaospersists", "the_prince");
            default ->
                    new ResourceLocation(
                            "chaospersists", par1.toLowerCase(Locale.ROOT).replace(' ', '_'));
        };
    }

    private static void invokePurpleType(Entity entity, int type) {
        try {
            Method m = entity.getClass().getMethod("setPurpleType", int.class);
            m.invoke(entity, type);
        } catch (ReflectiveOperationException ignored) {
        }
    }

    private static boolean isGodzilla(Entity e) {
        return "Godzilla".equals(e.getClass().getSimpleName());
    }

    private static boolean isGodzillaHead(Entity e) {
        return "GodzillaHead".equals(e.getClass().getSimpleName());
    }

    private LivingEntity doJumpDamage(double X, double Y, double Z, double dist, double damage, int knock) {
        AABB bb = new AABB(X - dist, Y - 10.0, Z - dist, X + dist, Y + 10.0, Z + dist);
        List<LivingEntity> var5 = this.level().getEntitiesOfClass(LivingEntity.class, bb);
        Collections.sort(var5, this.targetSorter);
        for (LivingEntity var4 : var5) {
            if (var4 == null
                    || var4 == this
                    || !var4.isAlive()
                    || MyUtils.isRoyalty(var4)
                    || var4 instanceof Ghost
                    || var4 instanceof GhostSkelly) {
                continue;
            }
            var4.hurt(this.damageSources().explosion(null), (float) damage / 2.0f);
            var4.hurt(this.damageSources().fall(), (float) damage / 2.0f);
            this.level()
                    .playSound(
                            null,
                            var4.getX(),
                            var4.getY(),
                            var4.getZ(),
                            SoundEvents.GENERIC_EXPLODE,
                            this.getSoundSource(),
                            0.65f,
                            1.0f + (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.5f);
            if (knock != 0) {
                double ks = 2.75;
                double inair = 0.65;
                float f3 = (float) Math.atan2(var4.getZ() - this.getZ(), var4.getX() - this.getX());
                var4.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
        }
        return null;
    }
}
