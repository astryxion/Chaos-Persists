package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class Baryonyx extends Animal {
    private float moveSpeed = 0.25f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Baryonyx(EntityType<? extends Baryonyx> type, Level level) {
        super(type, level);
        this.fireImmune();
        this.xpReward = 5;
        this.moveSpeed = 0.25f;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 12.0f));
        this.goalSelector.addGoal(6, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 8.0);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Baryonyx) this.getType().create(level);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    public int mygetMaxHealth() {
        return 40;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(net.minecraft.world.damagesource.DamageSource damageSource) {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.DUCK_HURT;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(
            net.minecraft.world.damagesource.DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int var3 = this.getRandom().nextInt(5) + 2;
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(Items.BEEF);
        }
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (bid == Blocks.GRASS_BLOCK) {
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
                if (bid == Blocks.GRASS_BLOCK) {
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
                if (bid == Blocks.GRASS_BLOCK) {
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
                if (bid == Blocks.GRASS_BLOCK) {
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
                if (bid == Blocks.GRASS_BLOCK) {
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
                if (bid == Blocks.GRASS_BLOCK) {
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
        super.customServerAiStep();
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.getRandom().nextInt(60) == 0 && ChaosPersists.PlayNicely == 0) {
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
                this.getNavigation().moveTo(this.tx, this.ty, this.tz, 1.0);
                if (this.closest < 12) {
                    if (com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)) {
                        this.level()
                                .setBlock(
                                        new BlockPos(this.tx, this.ty, this.tz),
                                        Blocks.DIRT.defaultBlockState(),
                                        2);
                    }
                    this.heal(1.0f);
                    this.playSound(SoundEvents.PLAYER_BURP, 1.0f, this.getRandom().nextFloat() * 0.2f + 0.9f);
                }
            }
        }
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

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty() && par1ItemStack.is(Items.APPLE);
    }

    public boolean isBreedingItem(ItemStack par1ItemStack) {
        return ChaosPersists.MyCrystalApple != null && par1ItemStack.is(ChaosPersists.MyCrystalApple);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return this.isBreedingItem(stack);
    }

    private int findBuddies() {
        List<Baryonyx> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Baryonyx.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }

    public static boolean checkBaryonyxSpawnRules(
            EntityType<Baryonyx> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return Animal.checkAnimalSpawnRules(type, level, spawnType, pos, random);
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (this.findBuddies() > 8) {
            return false;
        }
        return true;
    }
}
