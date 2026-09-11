package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.Acid;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
public class SpitBug extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(SpitBug.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurtTimer = 0;
    private float moveSpeed = 0.33f;
    private int streamCount = 0;

    public SpitBug(EntityType<? extends SpitBug> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 50;
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MoveThroughVillageGoal(this, 0.8999999761581421, false, 14, () -> false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 14, 1.0));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 10.0f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.SpitBug_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.33)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.SpitBug_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.SpitBug_stats.defense);
    }

    @Override
    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (!this.onGround()) {
            this.getNavigation().stop();
        }
    }

    public int mygetMaxHealth() {
        return ChaosPersists.SpitBug_stats.health;
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

    protected void jump() {
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                this.getDeltaMovement().y + 0.75,
                this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 0.75, this.getZ());
        float f = 0.2f + Math.abs(this.getRandom().nextFloat() * 0.45f);
        this.setDeltaMovement(
                this.getDeltaMovement().x - (double) f * Math.sin(Math.toRadians(this.getYHeadRot())),
                this.getDeltaMovement().y,
                this.getDeltaMovement().z + (double) f * Math.cos(Math.toRadians(this.getYHeadRot())));
        this.setOnGround(false);
    }

    protected void jumpAtEntity(LivingEntity e) {
        this.setDeltaMovement(
                this.getDeltaMovement().x,
                this.getDeltaMovement().y + 0.75,
                this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 0.75, this.getZ());
        float f = 0.2f + Math.abs(this.getRandom().nextFloat() * 0.25f);
        float d = (float) Math.atan2(e.getX() - this.getX(), e.getZ() - this.getZ());
        this.setDeltaMovement(
                this.getDeltaMovement().x + (double) f * Math.sin(d),
                this.getDeltaMovement().y,
                this.getDeltaMovement().z + (double) f * Math.cos(d));
        this.setOnGround(false);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(4) == 0) {
            return ChaosSounds.CLATTER;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.CRUNCH;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.EMPERORSCORPION_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.75f;
    }

    @Override
    public float getVoicePitch() {
        return 1.5f;
    }

    protected Item getDropItem() {
        int i = this.getRandom().nextInt(10);
        if (i == 0) {
            return Items.GOLD_NUGGET;
        }
        if (i == 1) {
            return ChaosPersists.UraniumNugget;
        }
        if (i == 2) {
            return ChaosPersists.TitaniumNugget;
        }
        return null;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 1 + this.getRandom().nextInt(3);
        for (int var4 = 0; var4 < i; ++var4) {
            if (ChaosPersists.MyAmethyst != null) {
                this.spawnAtLocation(new ItemStack(ChaosPersists.MyAmethyst, 1));
            }
        }
        Item drop = this.getDropItem();
        if (drop != null) {
            this.spawnAtLocation(drop);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        double ks = 0.5;
        double inair = 0.1;
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                float f3 = (float) Math.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
                if (!living.isAlive() || target instanceof Player) {
                    inair *= 2.0;
                }
                living.push(Math.cos(f3) * ks, inair, Math.sin(f3) * ks);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.hurtTimer > 0) {
            return false;
        }
        if (this.isInvulnerableTo(source)) {
            return false;
        }
        if (source.getMsgId().equals("cactus") || source.getMsgId().equals("fall")) {
            return false;
        }
        boolean ret = super.hurt(source, amount);
        if (ret) {
            this.hurtTimer = 15;
        }
        Entity e = source.getEntity();
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
            ret = true;
        }
        return ret;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (this.hurtTimer > 0) {
            --this.hurtTimer;
        }
        if (this.getRandom().nextInt(5) == 0) {
            LivingEntity e = this.getTarget();
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
                if (this.getRandom().nextInt(15) == 1 && this.onGround()) {
                    this.jumpAtEntity(e);
                } else if (this.distanceToSqr(e) < 9.0) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(6) == 0 || this.getRandom().nextInt(7) == 1) {
                        this.doHurtTarget(e);
                        if (!this.level().isClientSide && this.getRandom().nextInt(3) != 1) {
                            this.level()
                                    .playSound(
                                            null,
                                            e.getX(),
                                            e.getY(),
                                            e.getZ(),
                                            ChaosSounds.CLATTER,
                                            this.getSoundSource(),
                                            1.0f,
                                            1.0f);
                        }
                    }
                } else if (this.onGround()) {
                    this.getNavigation().moveTo(e, 0.5);
                    this.watercanon(e);
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(150) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
    }

    private void watercanon(LivingEntity e) {
        double yoff = 1.5;
        double xzoff = 1.5;
        if (this.streamCount > 0) {
            this.setAttacking(1);
            Acid projectile =
                    new Acid(
                            ChaosPersists.ENTITY_TYPE_ACID.get(),
                            e.getX() - this.getX(),
                            e.getY() + 0.75 - (this.getY() + yoff),
                            e.getZ() - this.getZ(),
                            this.level());
            projectile.moveTo(
                    this.getX() - xzoff * Math.sin(Math.toRadians(this.getYHeadRot())),
                    this.getY() + yoff,
                    this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                    this.getYHeadRot(),
                    this.getXRot());
            double var3 = e.getX() - projectile.getX();
            double var5 = e.getY() + 0.25 - projectile.getY();
            double var7 = e.getZ() - projectile.getZ();
            float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
            projectile.shoot(var3, var5 + (double) var9, var7, 1.1f, 6.0f);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            this.getSoundSource(),
                            0.75f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(projectile);
            --this.streamCount;
        } else {
            this.setAttacking(0);
        }
        if (this.streamCount <= 0 && this.getRandom().nextInt(7) == 1) {
            this.streamCount = 8;
        }
    }

    private boolean isSuitableTarget(LivingEntity entity, boolean par2) {
        if (entity == null) {
            return false;
        }
        if (entity == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, entity)) {
            return false;
        }
        if (!entity.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(entity)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(entity)) {
            return false;
        }
        if (entity instanceof EnderReaper) {
            return false;
        }
        if (entity instanceof EnderKnight) {
            return false;
        }
        if (entity instanceof EnderMan) {
            return false;
        }
        if ("Hydrolisc".equals(entity.getClass().getSimpleName())) {
            return false;
        }
        if (entity instanceof net.minecraft.world.entity.monster.Creeper) {
            return false;
        }
        if (entity instanceof SpitBug) {
            return false;
        }
        if ("TrooperBug".equals(entity.getClass().getSimpleName())) {
            return false;
        }
        if (entity instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(12.0, 7.0, 12.0));
        Collections.sort(candidates, this.targetSorter);
        Iterator<LivingEntity> it = candidates.iterator();
        while (it.hasNext()) {
            LivingEntity candidate = it.next();
            if (!this.isSuitableTarget(candidate, false)) {
                continue;
            }
            return candidate;
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public static boolean checkSpitBugSpawnRules(
            EntityType<SpitBug> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return Monster.checkMonsterSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                    if (state.getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null && "Spit Bug".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (MyUtils.isDay(level) && level.getRandom().nextInt(20) > 1) {
            return false;
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (!MyUtils.getBlockStateForSpawnRules(level, checkPos).isAir()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected boolean isValidLightLevel(LevelAccessor level) {
        if (level instanceof ServerLevelAccessor serverLevel) {
            return Monster.checkMonsterSpawnRules(
                    ChaosPersists.ENTITY_TYPE_SPIT_BUG.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
