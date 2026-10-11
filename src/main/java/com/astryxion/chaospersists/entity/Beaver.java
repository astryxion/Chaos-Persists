package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class Beaver extends Animal {
    private float moveSpeed = 0.15f;
    private final GenericTargetSorter targetSorter;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Beaver(EntityType<? extends Beaver> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.moveSpeed = 0.2f;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 1.0, 1.5));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new AvoidEntityGoal<>(this, Player.class, 8.0f, 1.0, 1.5));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(7, new MyEntityAIWanderALot(this, 10, 1.0));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 1.0);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    /**
     * 1.7.10 ate {@code Blocks.log}, OreSpawn tree logs, and also oak fence/gate/sign.
     * Fences let beavers flood-fill entire villages; restrict to tree logs only (forest balance).
     */
    public boolean isWood(Block bid) {
        if (bid == null) {
            return false;
        }
        BlockState state = bid.defaultBlockState();
        if (state.is(BlockTags.LOGS)) {
            return true;
        }
        return bid == ChaosPersists.MyDT
                || bid == ChaosPersists.MySkyTreeLog
                || bid == ChaosPersists.MyCrystalTreeLog;
    }

    /** True if this log looks like part of a living/natural tree (leaves nearby), not a house beam. */
    private boolean isTreeLogTarget(BlockPos pos) {
        BlockState state = this.level().getBlockState(pos);
        if (!this.isWood(state.getBlock())) {
            return false;
        }
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        for (int dx = -3; dx <= 3; ++dx) {
            for (int dy = -1; dy <= 6; ++dy) {
                for (int dz = -3; dz <= 3; ++dz) {
                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }
                    cursor.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    if (this.isTreeLeaf(this.level().getBlockState(cursor))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isTreeLeaf(BlockState state) {
        if (state.is(BlockTags.LEAVES)) {
            return true;
        }
        Block bid = state.getBlock();
        return bid == ChaosPersists.MyAppleLeaves
                || bid == ChaosPersists.MyScaryLeaves
                || bid == ChaosPersists.MyCherryLeaves
                || bid == ChaosPersists.MyPeachLeaves
                || bid == ChaosPersists.MyExperienceLeaves
                || bid == ChaosPersists.MyCrystalLeaves
                || bid == ChaosPersists.MyCrystalLeaves2
                || bid == ChaosPersists.MyCrystalLeaves3;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockPos posA = new BlockPos(x + dx, y + i, z + j);
                if (this.isTreeLogTarget(posA)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posA.getX();
                        this.ty = posA.getY();
                        this.tz = posA.getZ();
                        ++found;
                    }
                }
                BlockPos posB = new BlockPos(x - dx, y + i, z + j);
                if (this.isTreeLogTarget(posB)) {
                    int d = dx * dx + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posB.getX();
                        this.ty = posB.getY();
                        this.tz = posB.getZ();
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockPos posA = new BlockPos(x + i, y + dy, z + j);
                if (this.isTreeLogTarget(posA)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posA.getX();
                        this.ty = posA.getY();
                        this.tz = posA.getZ();
                        ++found;
                    }
                }
                BlockPos posB = new BlockPos(x + i, y - dy, z + j);
                if (this.isTreeLogTarget(posB)) {
                    int d = dy * dy + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posB.getX();
                        this.ty = posB.getY();
                        this.tz = posB.getZ();
                        ++found;
                    }
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockPos posA = new BlockPos(x + i, y + j, z + dz);
                if (this.isTreeLogTarget(posA)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posA.getX();
                        this.ty = posA.getY();
                        this.tz = posA.getZ();
                        ++found;
                    }
                }
                BlockPos posB = new BlockPos(x + i, y + j, z - dz);
                if (this.isTreeLogTarget(posB)) {
                    int d = dz * dz + j * j + i * i;
                    if (d < this.closest) {
                        this.closest = d;
                        this.tx = posB.getX();
                        this.ty = posB.getY();
                        this.tz = posB.getZ();
                        ++found;
                    }
                }
            }
        }
        return found != 0;
    }

    private void dropItemRand(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        ItemEntity entityitem =
                new ItemEntity(
                        this.level(),
                        this.getX() + (double) this.getRandom().nextInt(4) - (double) this.getRandom().nextInt(4),
                        this.getY() + 4.0 + (double) this.level().getRandom().nextInt(4),
                        this.getZ() + (double) this.getRandom().nextInt(4) - (double) this.getRandom().nextInt(4),
                        is);
        this.level().addFreshEntity(entityitem);
    }

    public void breakRecursor(Level world, int x, int y, int z, int xf, int yf, int zf, int recursion) {
        int var7 = 1;
        if (recursion > 200) {
            return;
        }
        for (int var9 = -var7; var9 <= var7; ++var9) {
            for (int var10 = -var7; var10 <= var7; ++var10) {
                for (int var11 = -var7; var11 <= var7; ++var11) {
                    if (var9 == 0 && var10 == 0 && var11 == 0) {
                        continue;
                    }
                    if (x + var9 == xf && y + var10 == yf && z + var11 == zf) {
                        continue;
                    }
                    if (recursion > 0
                            && x + var9 >= xf - var7
                            && x + var9 <= xf + var7
                            && y + var10 >= yf - var7
                            && y + var10 <= yf + var7
                            && z + var11 >= zf - var7
                            && z + var11 <= zf + var7) {
                        continue;
                    }
                    Block var12 = world.getBlockState(new BlockPos(x + var9, y + var10, z + var11)).getBlock();
                    if (!this.isWood(var12)) {
                        continue;
                    }
                    world.setBlock(new BlockPos(x + var9, y + var10, z + var11), Blocks.AIR.defaultBlockState(), 2);
                    this.dropItemRand(var12.asItem(), 1);
                    this.breakRecursor(world, x + var9, y + var10, z + var11, x, y, z, recursion + 1);
                }
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if ((this.getRandom().nextInt(30) == 0 && this.getBeaverHealth() < this.mygetMaxHealth()
                        || this.getRandom().nextInt(350) == 1)
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
            int i = 0;
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double) this.tx, (double) this.ty, (double) this.tz, 1.0);
                if (this.closest < 12) {
                    if (com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)) {
                        this.level().setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                        this.breakRecursor(this.level(), this.tx, this.ty, this.tz, this.tx, this.ty, this.tz, i);
                    }
                    this.heal(1.0f);
                    this.playSound(
                            ChaosSounds.CHAINSAW,
                            1.0f,
                            this.getRandom().nextFloat() * 0.2f + 0.9f);
                }
            }
        }
        if (this.getRandom().nextInt(200) == 1) {
            Beaver buddy = this.findBuddy();
            if (buddy != null) {
                this.getNavigation().moveTo(buddy.getX(), buddy.getY(), buddy.getZ(), 0.5);
            }
        }
        super.customServerAiStep();
    }

    private Beaver findBuddy() {
        List<Beaver> var5 =
                this.level().getEntitiesOfClass(Beaver.class, this.getBoundingBox().inflate(16.0, 6.0, 16.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<Beaver> var2 = var5.iterator();
        if (var2.hasNext()) {
            return var2.next();
        }
        return null;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public int mygetMaxHealth() {
        return 15;
    }

    public int getBeaverHealth() {
        return (int) this.getHealth();
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
    public float getVoicePitch() {
        return this.isBaby()
                ? (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.5f
                : (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.1f + 1.0f;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob other) {
        return ChaosPersists.ENTITY_TYPE_BEAVER.get().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return !stack.isEmpty() && stack.is(ChaosPersists.MyCrystalApple);
    }

    public static boolean checkBeaverSpawnRules(
            EntityType<Beaver> type,
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
        if (bid != Blocks.DIRT
                && bid != Blocks.GRASS_BLOCK
                && bid != Blocks.TALL_GRASS
                && bid != Blocks.OAK_LEAVES) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.getY() > 100.0) {
            return false;
        }
        BlockState below = MyUtils.getBlockStateForSpawnRules(level, BlockPos.containing(this.getX(), this.getY() - 1.0, this.getZ()));
        Block bid = below.getBlock();
        if (bid != Blocks.DIRT
                && bid != Blocks.GRASS_BLOCK
                && bid != Blocks.TALL_GRASS
                && bid != Blocks.OAK_LEAVES) {
            return false;
        }
        return true;
    }
}
