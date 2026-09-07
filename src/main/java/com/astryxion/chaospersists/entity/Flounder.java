package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class Flounder extends Animal {
    private float moveSpeed = 0.25f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Flounder(EntityType<? extends Flounder> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.moveSpeed = 0.25f;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 12.0f));
        this.goalSelector.addGoal(6, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    public int mygetMaxHealth() {
        return 5;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GENERIC_SPLASH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LITTLE_SPLAT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.RATDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = this.getRandom().nextInt(2) + 1;
        this.spawnAtLocation(new ItemStack(Items.COD, var3));
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                if (state.is(Blocks.WATER)) {
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
                if (state.is(Blocks.WATER)) {
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
                if (state.is(Blocks.WATER)) {
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
                if (state.is(Blocks.WATER)) {
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
                if (state.is(Blocks.WATER)) {
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
                if (state.is(Blocks.WATER)) {
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
        super.customServerAiStep();
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (!this.isInWater() && this.getRandom().nextInt(20) == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 11; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
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
                this.getNavigation().moveTo((double) this.tx, (double) (this.ty - 1), (double) this.tz, 1.0);
            } else {
                if (this.getRandom().nextInt(25) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.kill();
                    return;
                }
            }
        }
        if (this.isInWater() && this.getRandom().nextInt(50) == 0) {
            this.playSound(SoundEvents.GENERIC_SPLASH, 1.0f, this.getRandom().nextFloat() * 0.2f + 0.9f);
            this.heal(1.0f);
        }
    }

    private int findBuddies() {
        List<Flounder> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Flounder.class, this.getBoundingBox().inflate(16.0, 8.0, 16.0));
        return var5.size();
    }

    private boolean chaosUtopiaOrVillageDimension() {
        if (this.level() == null) {
            return false;
        }
        ResourceKey<Level> dim = this.level().dimension();
        return dim.equals(ChaosPersists.getDimensionKey(1))
                || dim.equals(ChaosPersists.getDimensionKey(3));
    }

    private boolean feetInWater() {
        BlockPos base = BlockPos.containing(this.getX(), this.getY(), this.getZ());
        for (int k = 0; k <= 2; k++) {
            if (this.level().getBlockState(base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    private static boolean chaosUtopiaOrVillageDimension(ServerLevelAccessor level) {
        ResourceKey<Level> dim = level.getLevel().dimension();
        return dim.equals(ChaosPersists.getDimensionKey(1))
                || dim.equals(ChaosPersists.getDimensionKey(3));
    }

    private static boolean feetInWaterAt(ServerLevelAccessor level, BlockPos base) {
        for (int k = 0; k <= 2; k++) {
            if (MyUtils.getBlockStateForSpawnRules(level, base.below(k)).getFluidState().is(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkFlounderSpawnRules(
            EntityType<Flounder> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        if (random.nextInt(20) != 1) {
            return false;
        }
        if (chaosUtopiaOrVillageDimension(level) && !feetInWaterAt(level, pos)) {
            return false;
        }
        List<Flounder> buddies =
                level.getLevel()
                        .getEntitiesOfClass(Flounder.class, new AABB(pos).inflate(16.0, 8.0, 16.0));
        if (buddies.size() > 10) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.getRandom().nextInt(20) != 1) {
            return false;
        }
        if (this.chaosUtopiaOrVillageDimension() && !this.feetInWater()) {
            return false;
        }
        if (this.findBuddies() > 10) {
            return false;
        }
        return true;
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
        return true;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Flounder) this.getType().create(level);
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.COD);
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        Item crystal =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", "crystalapple"));
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
