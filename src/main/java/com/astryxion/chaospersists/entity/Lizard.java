package com.astryxion.chaospersists.entity;


import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
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
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraftforge.registries.ForgeRegistries;

public class Lizard extends EntityCannonFodder {
    private static final EntityDataAccessor<Byte> STATE =
            SynchedEntityData.defineId(Lizard.class, EntityDataSerializers.BYTE);

    private final GenericTargetSorter targetSorter;
    public boolean should_despawn = true;
    private LivingEntity buddy = null;
    private int follow_time = 0;
    private float moveSpeed = 0.3f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Lizard(EntityType<? extends Lizard> type, Level level) {
        super(type, level);
        this.xpReward = 15;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(Tags.Items.DYES), false));
        this.goalSelector.addGoal(4, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 6.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATE, (byte) 0);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return 30;
    }

    @Override
    public int getArmorValue() {
        return 5;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        Entity e = par1DamageSource.getEntity();
        if (!par1DamageSource.is(DamageTypes.CACTUS)) {
            ret = super.hurt(par1DamageSource, par2);
            if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
                this.setTarget(living);
            }
        }
        this.follow_time = 0;
        return ret;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.ALO_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
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
        if (!var2.isEmpty() && var2.is(Tags.Items.DYES) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            if (!this.level().isClientSide) {
                this.buddy = par1EntityPlayer;
                this.follow_time = 3000 + this.getRandom().nextInt(2000);
            }
            spawnTamingParticles(true);
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (!this.level().isClientSide) {
            this.buddy = null;
            this.follow_time = 0;
        }
        spawnTamingParticles(false);
        return InteractionResult.SUCCESS;
    }

    private boolean isWaterBlock(Block bid) {
        return bid == Blocks.WATER;
    }

    private boolean isWaterState(BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                Block bid = state.getBlock();
                if ((this.isWaterBlock(bid) || this.isWaterState(state))
                        && (dx * dx + j * j + i * i) < this.closest) {
                    this.closest = dx * dx + j * j + i * i;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                bid = state.getBlock();
                if ((this.isWaterBlock(bid) || this.isWaterState(state))
                        && (dx * dx + j * j + i * i) < this.closest) {
                    this.closest = dx * dx + j * j + i * i;
                    this.tx = x - dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                Block bid = state.getBlock();
                int d = dy * dy + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                bid = state.getBlock();
                d = dy * dy + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y - dy;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                Block bid = state.getBlock();
                int d = dz * dz + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                bid = state.getBlock();
                d = dz * dz + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z - dz;
                    ++found;
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
        super.customServerAiStep();
        if (this.follow_time > 0) {
            --this.follow_time;
            this.should_despawn = false;
        } else {
            this.should_despawn = true;
        }
        if (!this.isInWater() && this.getRandom().nextInt(100) == 0) {
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
        if (this.getHealth() < (float) this.mygetMaxHealth() && this.getRandom().nextInt(300) == 1) {
            this.heal(1.0f);
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL && this.getRandom().nextInt(10) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                this.follow_time = 0;
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
        if (this.buddy != null
                && this.buddy.isAlive()
                && this.follow_time > 0
                && this.getRandom().nextInt(20) == 1) {
            this.getNavigation().moveTo(this.buddy, 1.0);
        }
    }

    private static boolean isAttackSquid(LivingEntity entity) {
        return entity instanceof AttackSquid;
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving) {
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (isAttackSquid(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof Spider) {
            return true;
        }
        if (par1EntityLiving instanceof CaveSpider) {
            return true;
        }
        if (par1EntityLiving instanceof Chicken) {
            return true;
        }
        if (par1EntityLiving instanceof Lizard
                && this.getRandom().nextInt(10) == 1
                && this.follow_time <= 0) {
            this.buddy = par1EntityLiving;
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
                                LivingEntity.class, this.getBoundingBox().inflate(12.0, 4.0, 12.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        if (this.getRandom().nextInt(100) == 0) {
            this.setTarget(null);
        }
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public final int getAttacking() {
        return this.entityData.get(STATE).intValue();
    }

    public final void setAttacking(int par1) {
        this.entityData.set(STATE, (byte) par1);
    }

    public static boolean checkLizardSpawnRules(
            EntityType<Lizard> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return pos.getY() >= 50;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        return this.getY() >= 50.0;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setPersistenceRequired();
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
    public Lizard getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Lizard) this.getType().create(level);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.APPLE);
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        Item crystal =
                ForgeRegistries.ITEMS.getValue(new ResourceLocation("chaospersists", "crystalapple"));
        if (crystal == null) {
            crystal = ChaosPersists.MyCrystalApple;
        }
        return crystal != null && par1ItemStack.is(crystal);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return this.isBreedingItem(stack);
    }
}
