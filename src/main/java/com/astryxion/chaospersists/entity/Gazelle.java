package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIAvoidEntity;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.PetOrderInteract;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;

public class Gazelle extends TamableAnimal {
    private float moveSpeed = 0.2f;
    private final GenericTargetSorter targetSorter;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Gazelle(EntityType<? extends Gazelle> type, Level level) {
        super(type, level);
        this.moveSpeed = 0.3f;
        this.setOrderedToSit(false);
        this.xpReward = 5;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(3, new MyEntityAIAvoidEntity(this, Monster.class, 8.0f, 1.0, 1.7000000476837158));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2000000476837158, Ingredient.of(Items.APPLE), false));
        this.goalSelector.addGoal(5, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(6, new MyEntityAIAvoidEntity(this, Player.class, 12.0f, 1.0, 2.0));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    private boolean isGrazableBlock(Block block) {
        if (block == ChaosPersists.MyStrawberryPlant) {
            return true;
        }
        if (block == Blocks.POTATOES || block == Blocks.CARROTS) {
            return true;
        }
        if (block == Blocks.TALL_GRASS) {
            return true;
        }
        if (block instanceof DoublePlantBlock) {
            return true;
        }
        return false;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (this.isGrazableBlock(bid)) {
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
                if (this.isGrazableBlock(bid)) {
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
                if (this.isGrazableBlock(bid)) {
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
                if (this.isGrazableBlock(bid)) {
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
                if (this.isGrazableBlock(bid)) {
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
                if (this.isGrazableBlock(bid)) {
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
    public boolean causeFallDamage(float par1, float damageMultiplier, DamageSource source) {
        float i = (float) Mth.ceil(par1 - 3.0f);
        if (i > 0.0f) {
            if (i > 3.0f) {
                this.playSound(SoundEvents.GENERIC_BIG_FALL, 1.0f, 1.0f);
            } else {
                this.playSound(SoundEvents.GENERIC_SMALL_FALL, 1.0f, 1.0f);
            }
            if (i > 2.0f) {
                i = 2.0f;
            }
            this.hurt(this.damageSources().fall(), i);
        }
        return false;
    }

    private Gazelle findBuddy() {
        List<Gazelle> var5 =
                this.level().getEntitiesOfClass(Gazelle.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<Gazelle> var2 = var5.iterator();
        if (var2.hasNext()) {
            return var2.next();
        }
        return null;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (!this.isInSittingPose()) {
            if ((this.getRandom().nextInt(30) == 0
                            && this.getGazelleHealth() < this.mygetMaxHealth()
                    || this.getRandom().nextInt(750) == 1)
                    && ChaosPersists.PlayNicely == 0) {
                this.closest = 99999;
                this.tz = 0;
                this.ty = 0;
                this.tx = 0;
                for (int i = 1; i < 11; ++i) {
                    int j = i;
                    if (j > 2) {
                        j = 2;
                    }
                    if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                        break;
                    }
                    if (i < 6) {
                        continue;
                    }
                    ++i;
                }
                if (this.closest < 99999) {
                    this.getNavigation().moveTo((double) this.tx, (double) this.ty, (double) this.tz, 1.0);
                    if (this.closest < 12) {
                        if (com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)) {
                            this.level().setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                        }
                        this.heal(1.0f);
                        this.playSound(
                                SoundEvents.PLAYER_BURP,
                                1.0f,
                                this.getRandom().nextFloat() * 0.2f + 0.9f);
                    }
                }
            }
            if (this.getRandom().nextInt(250) == 1) {
                Gazelle buddy = this.findBuddy();
                if (buddy != null) {
                    this.getNavigation().moveTo(buddy, 0.5);
                }
            }
        }
        if (this.getRandom().nextInt(250) == 0) {
            this.heal(1.0f);
        }
        super.customServerAiStep();
    }

    public int mygetMaxHealth() {
        return 15;
    }

    public int getGazelleHealth() {
        return (int) this.getHealth();
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
        if (!var2.isEmpty() && var2.is(Items.APPLE) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
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
                if ((float) this.mygetMaxHealth() <= this.getHealth()) {
                    return InteractionResult.SUCCESS;
                }
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                this.heal((float) this.mygetMaxHealth() - this.getHealth());
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
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Items.NAME_TAG)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            this.setCustomName(var2.getHoverName());
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy(par1EntityPlayer) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            PetOrderInteract.setSit(par1EntityPlayer, this, !this.isInSittingPose());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.SCORPION_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.CRYO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        if (this.isTame()) {
            int var3 = this.getRandom().nextInt(5);
            var3 += 2;
            for (int var4 = 0; var4 < var3; ++var4) {
                this.spawnAtLocation(Blocks.POPPY.asItem());
            }
        } else {
            this.spawnAtLocation(Items.BEEF);
        }
    }

    @Override
    public float getVoicePitch() {
        return this.isBaby()
                ? (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.5f
                : (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.0f;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        float p2 = par2;
        if (this.isTame() && p2 > 10.0f) {
            p2 = 10.0f;
        }
        return super.hurt(par1DamageSource, p2);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public Gazelle getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Gazelle) this.getType().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ChaosPersists.MyCrystalApple);
    }

    public static boolean checkGazelleSpawnRules(
            EntityType<Gazelle> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        if (pos.getY() > 100) {
            return false;
        }
        Block bid = MyUtils.getBlockStateForSpawnRules(level, pos.below()).getBlock();
        return bid == Blocks.DIRT || bid == Blocks.GRASS_BLOCK || bid == Blocks.TALL_GRASS;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.getY() > 100.0) {
            return false;
        }
        Block bid = MyUtils.getBlockStateForSpawnRules(level, this.blockPosition().below()).getBlock();
        return bid == Blocks.DIRT || bid == Blocks.GRASS_BLOCK || bid == Blocks.TALL_GRASS;
    }
}
