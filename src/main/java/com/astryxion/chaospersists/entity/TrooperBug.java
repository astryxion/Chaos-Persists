package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class TrooperBug extends Monster {
    private static final EntityDataAccessor<Byte> ATTACKING =
            SynchedEntityData.defineId(TrooperBug.class, EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int hurtTimer = 0;
    private float moveSpeed = 0.4f;

    public TrooperBug(EntityType<? extends TrooperBug> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 150;
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
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.TrooperBug_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.TrooperBug_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.TrooperBug_stats.defense);
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
        return ChaosPersists.TrooperBug_stats.health;
    }

    public int getTrooperBugHealth() {
        return (int) this.getHealth();
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
                this.getDeltaMovement().y + 1.149999976158142,
                this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 1.5, this.getZ());
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
                this.getDeltaMovement().y + 1.25,
                this.getDeltaMovement().z);
        this.setPos(this.getX(), this.getY() + 1.25, this.getZ());
        float f = 0.3f + Math.abs(this.getRandom().nextFloat() * 0.25f);
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
        return 1.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.BEEF;
    }

    private ItemStack dropItemRand(Item index, int par1) {
        if (index == null) {
            return null;
        }
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5),
                        is);
        this.level().addFreshEntity(entityItem);
        return is;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.dropItemRand(ChaosPersists.MyJumpyBugScale, 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int i = 2 + this.getRandom().nextInt(5);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(ChaosPersists.MyAmethyst, 1);
        }
        i = 1 + this.getRandom().nextInt(5);
        block16:
        for (int var4 = 0; var4 < i; ++var4) {
            int var3 = this.getRandom().nextInt(14);
            ItemStack is;
            switch (var3) {
                case 0: {
                    continue block16;
                }
                case 1: {
                    continue block16;
                }
                case 2: {
                    if (ChaosPersists.MyBlockAmethystBlock != null) {
                        is = this.dropItemRand(ChaosPersists.MyBlockAmethystBlock.asItem(), 1);
                    }
                    continue block16;
                }
                case 3: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystSword, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 4: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystShovel, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 5: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystPickaxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 6: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystAxe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 7: {
                    is = this.dropItemRand(ChaosPersists.MyAmethystHoe, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 8: {
                    is = this.dropItemRand(ChaosPersists.AmethystHelmet, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) == 1) {
                            is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(2));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                        }
                    }
                    continue block16;
                }
                case 9: {
                    is = this.dropItemRand(ChaosPersists.AmethystBody, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block16;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    continue block16;
                }
                case 10: {
                    is = this.dropItemRand(ChaosPersists.AmethystLegs, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block16;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    continue block16;
                }
                case 11: {
                    is = this.dropItemRand(ChaosPersists.AmethystBoots, 1);
                    if (is != null) {
                        if (this.getRandom().nextInt(6) == 1) {
                            is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                        }
                        if (this.getRandom().nextInt(2) != 1) {
                            continue block16;
                        }
                        is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                    }
                    continue block16;
                }
                case 12: {
                    break;
                }
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        double ks = 1.8;
        double inair = 0.2;
        if (super.doHurtTarget(target)) {
            if (target instanceof LivingEntity living) {
                float f3 = (float) Mth.atan2(target.getZ() - this.getZ(), target.getX() - this.getX());
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
            this.hurtTimer = 20;
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
                if (this.getRandom().nextInt(10) == 1 && this.onGround()) {
                    this.jumpAtEntity(e);
                } else {
                    float reach = 5.0f + e.getBbWidth() / 2.0f;
                    if (this.distanceToSqr(e) < (double) (reach * reach)) {
                        MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                        this.setAttacking(1);
                        if (this.getRandom().nextInt(6) == 0 || this.getRandom().nextInt(7) == 1) {
                            this.doHurtTarget(e);
                            if (!this.level().isClientSide) {
                                if (this.getRandom().nextInt(3) == 1) {
                                    this.level()
                                            .playSound(
                                                    null,
                                                    e.getX(),
                                                    e.getY(),
                                                    e.getZ(),
                                                    ChaosSounds.SCORPION_ATTACK,
                                                    this.getSoundSource(),
                                                    1.4f,
                                                    1.0f);
                                } else {
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
                        }
                    } else if (this.onGround()) {
                        this.getNavigation().moveTo(e, 1.2);
                    }
                }
                if (this.getRandom().nextInt(30) == 1) {
                    Entity spawned =
                            spawnCreature(
                                    this.level(),
                                    "spit_bug",
                                    (this.getX() + e.getX()) / 2.0
                                            + (double) this.getRandom().nextInt(5)
                                            - (double) this.getRandom().nextInt(5),
                                    (this.getY() + e.getY()) / 2.0 + 1.01,
                                    (this.getZ() + e.getZ()) / 2.0
                                            + (double) this.getRandom().nextInt(5)
                                            - (double) this.getRandom().nextInt(5));
                    if (spawned instanceof LivingEntity living) {
                        MyUtils.playAmbientSound(living);
                    }
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(150) == 1 && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.heal(1.0f);
        }
    }

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res =
                par1.contains(":")
                        ? new ResourceLocation(par1)
                        : new ResourceLocation("chaospersists", par1);
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    private boolean isSuitableTarget(LivingEntity entity, boolean par2) {
        if (entity == null) {
            return false;
        }
        if (entity == this) {
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
        if ("Hydrolisc".equals(entity.getClass().getSimpleName())) {
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
        if (entity instanceof Creeper) {
            return false;
        }
        if (entity instanceof TrooperBug) {
            return false;
        }
        if (entity instanceof SpitBug) {
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

    public static boolean checkTrooperBugSpawnRules(
            EntityType<TrooperBug> type,
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
                    if (id != null && "Jumpy Bug".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (MyUtils.isDay(level) && level.getRandom().nextInt(20) > 1) {
            return false;
        }
        for (int k = -2; k < 2; ++k) {
            for (int j = -2; j < 2; ++j) {
                for (int i = 1; i < 5; ++i) {
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
                    ChaosPersists.ENTITY_TYPE_TROOPER_BUG.get(),
                    serverLevel,
                    MobSpawnType.NATURAL,
                    this.blockPosition(),
                    serverLevel.getRandom());
        }
        return level.getMaxLocalRawBrightness(this.blockPosition()) < 8;
    }
}
