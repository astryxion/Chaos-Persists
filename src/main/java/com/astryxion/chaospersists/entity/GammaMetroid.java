package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import com.astryxion.chaospersists.util.ChaosChaseMoveControl;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GammaMetroid extends TamableAnimal {
    private final GenericTargetSorter targetSorter;
    private final float moveSpeed = 0.15f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public GammaMetroid(EntityType<? extends GammaMetroid> type, Level level) {
        super(type, level);
        this.moveControl = new ChaosChaseMoveControl(this);
        this.xpReward = 20;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2000000476837158, Ingredient.of(Items.IRON_INGOT), false));
        this.goalSelector.addGoal(4, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.GammaMetroid_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.GammaMetroid_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.GammaMetroid_stats.defense);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        return entity.hurt(this.damageSources().mobAttack(this), (float) ChaosPersists.GammaMetroid_stats.attack);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!stack.isEmpty() && stack.getCount() <= 0) {
            player.setItemInHand(hand, ItemStack.EMPTY);
            stack = ItemStack.EMPTY;
        }
        if (super.mobInteract(player, hand) == InteractionResult.SUCCESS) {
            return InteractionResult.SUCCESS;
        }
        if (!stack.isEmpty() && stack.is(Items.IRON_INGOT) && player.distanceToSqr(this) < 25.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(3) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(player.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(player)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !stack.isEmpty()
                && stack.is(Blocks.DEAD_BUSH.asItem())
                && player.distanceToSqr(this) < 25.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !stack.isEmpty()
                && stack.is(Items.NAME_TAG)
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            this.setCustomName(stack.getHoverName());
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy(player) && player.distanceToSqr(this) < 25.0) {
            // Toggle on ordered sit (not pose) — pose was never set, so unsit never fired
            if (!this.level().isClientSide) {
                boolean sit = !this.isOrderedToSit();
                this.setOrderedToSit(sit);
                this.setInSittingPose(sit);
                if (sit) {
                    PetCombatHelper.onPetSit(this);
                    if (this.getNavigation() != null) {
                        this.getNavigation().stop();
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void setOrderedToSit(boolean sitting) {
        super.setOrderedToSit(sitting);
        this.setInSittingPose(sitting);
        if (sitting && this.getNavigation() != null) {
            this.getNavigation().stop();
        }
    }

    @Override
    public boolean isImmobile() {
        return super.isImmobile()
                || (this.isOrderedToSit() && this.getPassengers().isEmpty());
    }

    public int mygetMaxHealth() {
        return ChaosPersists.GammaMetroid_stats.health;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.GammaMetroid_stats.defense;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setPersistenceRequired();
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(5) == 1) {
            return ChaosSounds.WTF_LIVING;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.ALO_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.5f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int i = 5 + ChaosPersists.ChaosRand.nextInt(10);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.GOLD_NUGGET, 1);
        }
        i = 6 + ChaosPersists.ChaosRand.nextInt(10);
        for (int var4 = 0; var4 < i; ++var4) {
            this.dropItemRand(Items.IRON_INGOT, 1);
        }
    }

    private void dropItemRand(net.minecraft.world.item.Item index, int count) {
        ItemEntity entityItem =
                new ItemEntity(
                        this.level(),
                        this.getX() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        this.getY() + 1.0,
                        this.getZ() + ChaosPersists.ChaosRand.nextInt(4) - ChaosPersists.ChaosRand.nextInt(4),
                        new ItemStack(index, count));
        this.level().addFreshEntity(entityItem);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.isOrderedToSit()) {
            this.setTarget(null);
            if (this.getNavigation() != null) {
                this.getNavigation().stop();
            }
            return;
        }
        PetCombatHelper.tickPetCombat(this);
        super.customServerAiStep();
        LivingEntity target;
        if (this.level().getDifficulty() != Difficulty.PEACEFUL
                && this.getRandom().nextInt(5) == 0
                && (target =
                        PetCombatHelper.resolveCombatTarget(
                                this, this.getTarget(), this::findSomethingToAttack))
                        != null) {
            if (target != this.getTarget()) {
                this.setTarget(target);
            }
            this.setTarget(target);
            if (this.distanceToSqr(target) <= 9.0) {
                MyUtils.faceEntity(this, target, 10.0f, 10.0f);
                if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
                    this.doHurtTarget(target);
                }
            } else {
                this.getNavigation().moveTo(target, 1.25);
            }
        }
        if ((this.getRandom().nextInt(20) == 0 && this.getHealth() < (float) this.mygetMaxHealth()
                        || this.getRandom().nextInt(100) == 0)
                && ChaosPersists.PlayNicely == 0
                && !this.isOrderedToSit()) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 6; ++i) {
                int j = i;
                if (j > 2) {
                    j = 2;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 4) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo(this.tx, this.ty, this.tz, 1.0);
                if (this.closest < 12) {
                    if (this.level().getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                        this.level().setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                    }
                    this.heal(1.0f);
                    this.playSound(
                            SoundEvents.PLAYER_BURP,
                            0.5f,
                            this.getRandom().nextFloat() * 0.2f + 1.5f);
                }
            }
        }
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
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (MyUtils.isIgnoreable(par1EntityLiving)) {
            return false;
        }
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (par1EntityLiving instanceof GammaMetroid) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return false;
        }
        if (this.isTame()) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.getAbilities().instabuild) {
                return false;
            }
        }
        return true;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        if (this.isBaby()) {
            return null;
        }
        List<LivingEntity> candidates =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                this.getBoundingBox().inflate(10.0, 3.0, 10.0));
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

    protected boolean isValidLightLevel(LevelAccessor level) {
        BlockPos pos = this.blockPosition();
        if (level.getBrightness(LightLayer.SKY, pos) > level.getRandom().nextInt(32)) {
            return false;
        }
        int l = level.getBrightness(LightLayer.BLOCK, pos);
        return l <= level.getRandom().nextInt(8);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos origin = this.blockPosition();
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        if (!(level instanceof net.minecraft.server.level.WorldGenRegion)) {
            for (int k = -3; k < 3; ++k) {
                for (int j = -3; j < 3; ++j) {
                    for (int i = 0; i < 5; ++i) {
                        checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                        if (!MyUtils.canAccessBlockDuringWorldGen(level, checkPos)) {
                            continue;
                        }
                        BlockState state = MyUtils.getBlockStateForSpawnRules(level, checkPos);
                        if (state.getBlock() != Blocks.SPAWNER) {
                            continue;
                        }
                        if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                            continue;
                        }
                        ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                        if (id != null && "WTF?".equals(id.getPath())) {
                            return true;
                        }
                    }
                }
            }
        }
        if (!this.isValidLightLevel(level)) {
            return false;
        }
        if (level instanceof Level world && world.dimension().equals(ChaosPersists.getDimensionKey(4))) {
            return true;
        }
        if (origin.getY() > 50) {
            return false;
        }
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 4; ++i) {
                    checkPos.set(origin.getX() + j, origin.getY() + i, origin.getZ() + k);
                    if (!MyUtils.canAccessBlockDuringWorldGen(level, checkPos)) {
                        continue;
                    }
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.AIR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                if (state.is(Blocks.STONE)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + dx;
                        this.ty = y + i;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                if (state.is(Blocks.STONE)) {
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
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                if (state.is(Blocks.STONE)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + dy;
                        this.tz = z + j;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                if (state.is(Blocks.STONE)) {
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
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                if (state.is(Blocks.STONE)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = x + i;
                        this.ty = y + j;
                        this.tz = z + dz;
                        ++found;
                    }
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                if (state.is(Blocks.STONE)) {
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
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return this.spawnBabyAnimal(level, partner);
    }

    public GammaMetroid spawnBabyAnimal(ServerLevel level, AgeableMob par1EntityAgeable) {
        GammaMetroid w = (GammaMetroid) this.getType().create(level);
        if (this.isTame()) {
            this.setOwnerUUID(this.getOwnerUUID());
            w.setTame(true);
        }
        return w;
    }

    @Override
    public boolean isFood(ItemStack par1ItemStack) {
        return ChaosPersists.MyCrystalApple != null && par1ItemStack.is(ChaosPersists.MyCrystalApple);
    }

    public static boolean checkGammaMetroidSpawnRules(
            EntityType<GammaMetroid> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return Animal.checkAnimalSpawnRules(type, level, spawnType, pos, random);
    }
}
