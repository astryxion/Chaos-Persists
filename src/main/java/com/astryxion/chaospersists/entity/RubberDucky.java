package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class RubberDucky extends TamableAnimal {
    private static final EntityDataAccessor<Byte> BYTE22 =
            SynchedEntityData.defineId(RubberDucky.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> BYTE23 =
            SynchedEntityData.defineId(RubberDucky.class, EntityDataSerializers.BYTE);

    private final GenericTargetSorter targetSorter;
    public boolean should_despawn = true;
    private LivingEntity buddy = null;
    private float moveSpeed = 0.22f;
    private int killcount = 0;
    private int died = 0;
    private RenderInfo renderdata = new RenderInfo();
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public RubberDucky(EntityType<? extends RubberDucky> type, Level level) {
        super(type, level);
        this.xpReward = 15;
        this.renderdata = new RenderInfo();
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(4, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ARMOR, 1.0);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.33f, 0.5f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BYTE23, (byte) 0);
        this.entityData.define(BYTE22, (byte) 0);
        this.setOrderedToSit(false);
        if (this.getAge() < 0) {
            this.setAge(-this.getAge());
        }
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

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        if (this.isInWater()) {
            this.setDeltaMovement(
                    this.getDeltaMovement().x,
                    this.getDeltaMovement().y + 0.10000000149011612,
                    this.getDeltaMovement().z);
            if (this.getDeltaMovement().y < -0.05000000074505806) {
                this.setDeltaMovement(
                        this.getDeltaMovement().x, -0.05000000074505806, this.getDeltaMovement().z);
            }
        }
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret;
        Entity w = par1DamageSource.getEntity();
        ret = super.hurt(par1DamageSource, par2);
        this.setOrderedToSit(false);
        if (!this.level().isClientSide
                && w instanceof Player
                && (this.isDeadOrDying() || this.getHealth() <= 0.0f)
                && this.died == 0) {
            this.died = 1;
            ++this.killcount;
            this.setKillCount(this.killcount);
            if (this.killcount < 10) {
                for (int m = 0; m < 20; ++m) {
                    int i = this.getRandom().nextInt(3);
                    if (this.getRandom().nextInt(2) == 1) {
                        i = -i;
                    }
                    int k = this.getRandom().nextInt(3);
                    if (this.getRandom().nextInt(2) == 1) {
                        k = -k;
                    }
                    for (int j = 3; j > -3; --j) {
                        BlockPos airPos =
                                new BlockPos(
                                        (int) this.getX() + i,
                                        (int) this.getY() + j + 1,
                                        (int) this.getZ() + k);
                        BlockPos groundPos =
                                new BlockPos(
                                        (int) this.getX() + i,
                                        (int) this.getY() + j,
                                        (int) this.getZ() + k);
                        if (!this.level().getBlockState(airPos).isAir()
                                || this.level().getBlockState(groundPos).isAir()) {
                            continue;
                        }
                        Entity e =
                                spawnCreature(
                                        this.level(),
                                        (int) this.getX() + i + 1,
                                        (int) this.getY() + j + 1,
                                        (int) this.getZ() + k);
                        if (e instanceof RubberDucky d) {
                            d.setKillCount(this.killcount);
                        }
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

    public int mygetMaxHealth() {
        return 5;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    public static Entity spawnCreature(Level par0World, double par2, double par4, double par6) {
        Entity var8 = null;
        if (par0World instanceof ServerLevel serverLevel) {
            var8 = ChaosPersists.ENTITY_TYPE_RUBBER_DUCKY.get().create(serverLevel);
            if (var8 != null) {
                var8.moveTo(par2, par4, par6, par0World.getRandom().nextFloat() * 360.0f, 0.0f);
                serverLevel.addFreshEntity(var8);
                if (var8 instanceof LivingEntity living) {
                    MyUtils.playAmbientSound(living);
                }
            }
        }
        return var8;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(10) == 1) {
            return ChaosSounds.DUCK_HURT;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected float getSoundVolume() {
        return 0.8f;
    }

    @Override
    public float getVoicePitch() {
        return 1.2f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (this.getRandom().nextInt(2) == 1) {
            this.spawnAtLocation(Items.FEATHER);
        } else if (this.getRandom().nextInt(2) == 1 && ChaosPersists.RubberDuckyEgg != null) {
            this.spawnAtLocation(ChaosPersists.RubberDuckyEgg);
        }
    }

    @Override
    public InteractionResult mobInteract(Player par1EntityPlayer, InteractionHand hand) {
        ItemStack var2 = par1EntityPlayer.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (super.mobInteract(par1EntityPlayer, hand) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
        }
        if (!var2.isEmpty() && var2.is(Items.COD) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(2) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1EntityPlayer.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(par1EntityPlayer)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DEAD_BUSH.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy(par1EntityPlayer) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.isInSittingPose() && this.getKillCount() < 5) {
                this.setOrderedToSit(true);
                if (!this.level().isClientSide) {
                    PetCombatHelper.onPetSit(this);
                }
            } else {
                this.setOrderedToSit(false);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }

    private boolean isWaterBlock(Block block) {
        return block == Blocks.WATER;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x - dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y - dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z + dz;
                        ++found;
                    }
                }
                bid = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz)).getBlock();
                if (this.isWaterBlock(bid)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z - dz;
                        ++found;
                    }
                }
            }
        }
        return found != 0;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        PetCombatHelper.tickPetCombat(this);
        super.customServerAiStep();
        if (!this.isInWater() && this.getRandom().nextInt(50) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 14; ++i) {
                int j = i;
                if (j > 5) {
                    j = 5;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() - 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 5) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double) this.tx, (double) (this.ty - 1), (double) this.tz, 1.33);
            }
        }
        if (this.killcount > 0 && this.getRandom().nextInt(200) == 1) {
            --this.killcount;
            this.setKillCount(this.killcount);
        }
        if (this.getHealth() < (float) this.mygetMaxHealth() && this.getRandom().nextInt(300) == 1) {
            this.heal(1.0f);
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL && this.getRandom().nextInt(5) == 1) {
            LivingEntity prior = this.getTarget();
            LivingEntity e =
                    PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
            if (e != prior) {
                this.setTarget(e);
            }
            if (e != null) {
                if (this.distanceToSqr(e) < 12.0) {
                    this.setAttacking(1);
                    if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
                        this.doHurtTarget(e);
                    }
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            } else {
                if (this.buddy != null
                        && this.buddy.isAlive()
                        && this.getRandom().nextInt(15) == 1) {
                    this.getNavigation().moveTo(this.buddy, 1.0);
                }
                this.setAttacking(0);
            }
        }
        if (this.buddy != null && this.buddy.isAlive() && this.getRandom().nextInt(20) == 1) {
            this.getNavigation().moveTo(this.buddy, 1.0);
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        float i = 1.0f;
        if (this.getKillCount() >= 5) {
            i = 2.0f;
        }
        return par1Entity.hurt(this.damageSources().mobAttack(this), i);
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, boolean par2) {
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == this) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(this, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof AttackSquid) {
            return true;
        }
        if (par1EntityLiving instanceof Squid) {
            return true;
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof RubberDucky && this.getRandom().nextInt(10) == 1) {
            this.buddy = par1EntityLiving;
        }
        if (this.getKillCount() >= 5 && par1EntityLiving instanceof Player p) {
            if (p.isCreative()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                new AABB(
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                this.getX(),
                                                this.getY(),
                                                this.getZ())
                                        .inflate(8.0, 4.0, 8.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity var4;
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        this.buddy = null;
        while (var2.hasNext()) {
            var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        par1NBTTagCompound.putInt("Killcount", this.killcount);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.killcount = par1NBTTagCompound.getInt("Killcount");
        this.setKillCount(this.killcount);
    }

    public final int getAttacking() {
        return this.entityData.get(BYTE23).byteValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(BYTE23, (byte) par1);
    }

    public final int getKillCount() {
        return this.entityData.get(BYTE22).byteValue();
    }

    public final void setKillCount(int par1) {
        this.entityData.set(BYTE22, (byte) par1);
        this.killcount = par1;
    }

    public static boolean checkRubberDuckySpawnRules(
            EntityType<RubberDucky> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation rubberId =
                            new ResourceLocation("chaospersists", "rubber_ducky");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, rubberId)
                            || "Rubber Ducky".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (pos.getY() < 50) {
            return false;
        }
        return MyUtils.isDay(level);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set(pos.getX() + j, pos.getY() + i, pos.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id == null) {
                        continue;
                    }
                    ResourceLocation rubberId =
                            new ResourceLocation("chaospersists", "rubber_ducky");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, rubberId)
                            || "Rubber Ducky".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world) {
            return world.isDay();
        }
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setAge(0);
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        return this.should_despawn;
    }

    @Override
    public RubberDucky getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (RubberDucky) this.getType().create(level);
    }

    @Override
    public boolean isFood(ItemStack par1ItemStack) {
        return ChaosPersists.MyCrystalApple != null && par1ItemStack.is(ChaosPersists.MyCrystalApple);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.COD);
    }
}
