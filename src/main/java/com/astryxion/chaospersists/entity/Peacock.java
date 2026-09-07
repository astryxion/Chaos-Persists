package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class Peacock extends Animal {
    private final float moveSpeed = 0.38f;
    int my_blink = 0;
    int blinkcount = 0;
    int blinker = 0;
    private final GenericTargetSorter targetSorter;

    public Peacock(EntityType<? extends Peacock> type, Level level) {
        super(type, level);
        this.xpReward = 8;
        this.my_blink = 20 + this.getRandom().nextInt(50);
        this.blinkcount = 0;
        this.blinker = 0;
        this.targetSorter = new GenericTargetSorter(this);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Player.class, 12.0f, 1.2000000476837158, 1.600000023841858));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new MyEntityAIWander(this, 1.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.38)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    public int getBlink() {
        return this.blinker;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
        ++this.blinkcount;
        if (this.blinkcount > this.my_blink) {
            this.blinkcount = 0;
            if (this.blinker > 0) {
                this.blinker = 0;
                this.my_blink = 50 + this.getRandom().nextInt(300);
            } else {
                this.blinker = 1;
                this.my_blink = 25 + this.getRandom().nextInt(100);
            }
        }
    }

    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    public int mygetMaxHealth() {
        return 15;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getRandom().nextInt(8) != 1) {
            return null;
        }
        return ChaosSounds.PEACOCKLIVE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.PEACOCKHIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.PEACOCKDEAD;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        this.spawnAtLocation(ChaosPersists.MyRawPeacock);
        if (this.getRandom().nextInt(3) == 1) {
            this.spawnAtLocation(ChaosPersists.MyRawPeacock);
        }
        if (this.getRandom().nextInt(2) == 1) {
            this.spawnAtLocation(ChaosPersists.MyPeacockFeather);
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        return target.hurt(this.damageSources().mobAttack(this), 6.0f);
    }

    private ItemStack layAnEgg(Item index, int par1) {
        ItemStack is = new ItemStack(index, par1);
        if (index == null) {
            return is;
        }
        this.spawnAtLocation(is);
        return is;
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        if (this.getRandom().nextInt(10) == 1) {
            LivingEntity e = this.findSomethingToAttack();
            if (e != null) {
                if (this.distanceToSqr(e) < 4.0) {
                    this.doHurtTarget(e);
                } else {
                    this.getNavigation().moveTo(e, 1.2);
                }
            }
        }
        if (this.getRandom().nextInt(5000) == 1) {
            this.layAnEgg(ChaosPersists.PeacockEgg, 1 + this.getRandom().nextInt(3));
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
        if (!this.hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        return par1EntityLiving instanceof Termite;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(10.0, 2.0, 10.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        if (this.isBaby()) {
            this.setAge(-24000);
            return false;
        }
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public Peacock getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (Peacock) this.getType().create(level);
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

    public static boolean checkPeacockSpawnRules(
            EntityType<Peacock> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 3; ++i) {
                    BlockState bid = MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k));
                    if (!bid.isAir()) {
                        return false;
                    }
                }
            }
        }
        long t = level.getLevel().getDayTime() % 24000L;
        if (t > 12000L) {
            return false;
        }
        if (pos.getY() < 50 || pos.getY() > 100) {
            return false;
        }
        List<Peacock> buddies =
                level.getLevel()
                        .getEntitiesOfClass(Peacock.class, new AABB(pos).inflate(16.0, 10.0, 16.0));
        return buddies.size() <= 2;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos pos = this.blockPosition();
        for (int k = -1; k < 1; ++k) {
            for (int j = -1; j < 1; ++j) {
                for (int i = 1; i < 3; ++i) {
                    if (!MyUtils.getBlockStateForSpawnRules(level, pos.offset(j, i, k)).isAir()) {
                        return false;
                    }
                }
            }
        }
        long t = 0L;
        if (level instanceof Level l) {
            t = l.getDayTime() % 24000L;
        }
        if (t > 12000L) {
            return false;
        }
        if (this.getY() < 50.0 || this.getY() > 100.0) {
            return false;
        }
        return this.findBuddies() <= 2;
    }

    private int findBuddies() {
        return this.level()
                .getEntitiesOfClass(Peacock.class, this.getBoundingBox().inflate(16.0, 10.0, 16.0))
                .size();
    }
}
