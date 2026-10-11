package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

public class EasterBunny extends Animal {
    private float moveSpeed = 0.45f;

    public EasterBunny(EntityType<? extends EasterBunny> type, Level level) {
        super(type, level);
        this.xpReward = 5;
        this.moveSpeed = 0.45f;
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(
                2, new AvoidEntityGoal<>(this, Monster.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(
                3, new AvoidEntityGoal<>(this, Player.class, 8.0f, 1.0, 1.399999976158142));
        this.goalSelector.addGoal(4, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, LivingEntity.class, 8.0f));
        this.goalSelector.addGoal(6, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.45)
                .add(Attributes.ATTACK_DAMAGE, 8.0);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    public int mygetMaxHealth() {
        return 10;
    }

    @Override
    protected SoundEvent getAmbientSound() {
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
        return 0.4f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        if (DatapackLoot.useEntityTable(this)) {
            return;
        }
        int var3 = this.getRandom().nextInt(3) + 2;
        for (int var4 = 0; var4 < var3; ++var4) {
            this.spawnAtLocation(Items.CHICKEN);
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
        super.customServerAiStep();
        if (this.getRandom().nextInt(600) == 1) {
            this.layAnEgg(1 + this.getRandom().nextInt(3));
        }
    }

    private ItemStack layAnEgg(int par1) {

        ItemEntity var3 = null;
        int i = 0;
        Item index = null;
        int val = 0;
        ItemStack is = null;
        i = this.getRandom().nextInt(115);
        switch (i) {
            case 5: {
                index = ChaosPersists.GirlfriendEgg;
                break;
            }
            case 6: {
                index = ChaosPersists.RedCowEgg;
                break;
            }
            case 7: {
                index = ChaosPersists.GoldCowEgg;
                break;
            }
            case 8: {
                index = ChaosPersists.EnchantedCowEgg;
                break;
            }
            case 9: {
                index = ChaosPersists.MOTHRAEgg;
                break;
            }
            case 10: {
                index = ChaosPersists.AloEgg;
                break;
            }
            case 11: {
                index = ChaosPersists.CryoEgg;
                break;
            }
            case 12: {
                index = ChaosPersists.CamaEgg;
                break;
            }
            case 13: {
                index = ChaosPersists.VeloEgg;
                break;
            }
            case 14: {
                index = ChaosPersists.HydroEgg;
                break;
            }
            case 15: {
                index = ChaosPersists.BasilEgg;
                break;
            }
            case 16: {
                index = ChaosPersists.DragonflyEgg;
                break;
            }
            case 17: {
                index = ChaosPersists.EmperorScorpionEgg;
                break;
            }
            case 18: {
                index = ChaosPersists.ScorpionEgg;
                break;
            }
            case 19: {
                index = ChaosPersists.CaveFisherEgg;
                break;
            }
            case 20: {
                index = ChaosPersists.SpyroEgg;
                break;
            }
            case 21: {
                index = ChaosPersists.BaryonyxEgg;
                break;
            }
            case 22: {
                index = ChaosPersists.GammaMetroidEgg;
                break;
            }
            case 23: {
                index = ChaosPersists.CockateilEgg;
                break;
            }
            case 24: {
                index = ChaosPersists.KyuubiEgg;
                break;
            }
            case 25: {
                index = ChaosPersists.AlienEgg;
                break;
            }
            case 26: {
                index = ChaosPersists.AttackSquidEgg;
                break;
            }
            case 27: {
                index = ChaosPersists.WaterDragonEgg;
                break;
            }
            case 28: {
                index = ChaosPersists.CephadromeEgg;
                break;
            }
            case 29: {
                index = ChaosPersists.DragonEgg;
                break;
            }
            case 30: {
                index = ChaosPersists.KrakenEgg;
                break;
            }
            case 31: {
                index = ChaosPersists.LizardEgg;
                break;
            }
            case 32: {
                index = ChaosPersists.BeeEgg;
                break;
            }
            case 33: {
                index = ChaosPersists.TrooperBugEgg;
                break;
            }
            case 34: {
                index = ChaosPersists.SpitBugEgg;
                break;
            }
            case 35: {
                index = ChaosPersists.StinkBugEgg;
                break;
            }
            case 36: {
                index = ChaosPersists.OstrichEgg;
                break;
            }
            case 37: {
                index = ChaosPersists.GazelleEgg;
                break;
            }
            case 38: {
                index = ChaosPersists.ChipmunkEgg;
                break;
            }
            case 39: {
                index = ChaosPersists.CreepingHorrorEgg;
                break;
            }
            case 40: {
                index = ChaosPersists.TerribleTerrorEgg;
                break;
            }
            case 41: {
                index = ChaosPersists.CliffRacerEgg;
                break;
            }
            case 42: {
                index = ChaosPersists.TriffidEgg;
                break;
            }
            case 43: {
                index = ChaosPersists.PitchBlackEgg;
                break;
            }
            case 44: {
                index = ChaosPersists.LurkingTerrorEgg;
                break;
            }
            case 45: {
                index = ChaosPersists.GodzillaEgg;
                break;
            }
            case 46: {
                index = ChaosPersists.SmallWormEgg;
                break;
            }
            case 47: {
                index = ChaosPersists.MediumWormEgg;
                break;
            }
            case 48: {
                index = ChaosPersists.LargeWormEgg;
                break;
            }
            case 49: {
                index = ChaosPersists.CassowaryEgg;
                break;
            }
            case 50: {
                index = ChaosPersists.CloudSharkEgg;
                break;
            }
            case 51: {
                index = ChaosPersists.GoldFishEgg;
                break;
            }
            case 52: {
                index = ChaosPersists.LeafMonsterEgg;
                break;
            }
            case 53: {
                index = ChaosPersists.TshirtEgg;
                break;
            }
            case 54: {
                index = ChaosPersists.EnderKnightEgg;
                break;
            }
            case 55: {
                index = ChaosPersists.EnderReaperEgg;
                break;
            }
            case 56: {
                index = ChaosPersists.BeaverEgg;
                break;
            }
            case 57: {
                index = ChaosPersists.RotatorEgg;
                break;
            }
            case 58: {
                index = ChaosPersists.VortexEgg;
                break;
            }
            case 59: {
                index = ChaosPersists.PeacockEgg;
                break;
            }
            case 60: {
                index = ChaosPersists.FairyEgg;
                break;
            }
            case 61: {
                index = ChaosPersists.DungeonBeastEgg;
                break;
            }
            case 62: {
                index = ChaosPersists.RatEgg;
                break;
            }
            case 63: {
                index = ChaosPersists.FlounderEgg;
                break;
            }
            case 64: {
                index = ChaosPersists.WhaleEgg;
                break;
            }
            case 65: {
                index = ChaosPersists.IrukandjiEgg;
                break;
            }
            case 66: {
                index = ChaosPersists.SkateEgg;
                break;
            }
            case 67: {
                index = ChaosPersists.UrchinEgg;
                break;
            }
            case 68: {
                index = ChaosPersists.Robot1Egg;
                break;
            }
            case 69: {
                index = ChaosPersists.Robot2Egg;
                break;
            }
            case 70: {
                index = ChaosPersists.Robot3Egg;
                break;
            }
            case 71: {
                index = ChaosPersists.Robot4Egg;
                break;
            }
            case 72: {
                index = ChaosPersists.GhostEgg;
                break;
            }
            case 73: {
                index = ChaosPersists.GhostSkellyEgg;
                break;
            }
            case 74: {
                index = ChaosPersists.BrownAntEgg;
                break;
            }
            case 75: {
                index = ChaosPersists.RedAntEgg;
                break;
            }
            case 76: {
                index = ChaosPersists.RainbowAntEgg;
                break;
            }
            case 77: {
                index = ChaosPersists.UnstableAntEgg;
                break;
            }
            case 78: {
                index = ChaosPersists.TermiteEgg;
                break;
            }
            case 79: {
                index = ChaosPersists.ButterflyEgg;
                break;
            }
            case 80: {
                index = ChaosPersists.MothEgg;
                break;
            }
            case 81: {
                index = ChaosPersists.MosquitoEgg;
                break;
            }
            case 82: {
                index = ChaosPersists.FireflyEgg;
                break;
            }
            case 83: {
                index = ChaosPersists.TRexEgg;
                break;
            }
            case 84: {
                index = ChaosPersists.HerculesEgg;
                break;
            }
            case 85: {
                index = ChaosPersists.MantisEgg;
                break;
            }
            case 86: {
                index = ChaosPersists.StinkyEgg;
                break;
            }
            case 87: {
                index = ChaosPersists.Robot5Egg;
                break;
            }
            case 88: {
                index = ChaosPersists.CoinEgg;
                break;
            }
            case 89: {
                index = ChaosPersists.BoyfriendEgg;
                break;
            }
            case 90: {
                index = ChaosPersists.TheKingEgg;
                break;
            }
            case 91: {
                index = ChaosPersists.ThePrinceEgg;
                break;
            }
            case 92: {
                index = ChaosPersists.EasterBunnyEgg;
                break;
            }
            case 93: {
                index = ChaosPersists.MolenoidEgg;
                break;
            }
            case 94: {
                index = ChaosPersists.SeaMonsterEgg;
                break;
            }
            case 95: {
                index = ChaosPersists.SeaViperEgg;
                break;
            }
            case 96: {
                index = ChaosPersists.CaterKillerEgg;
                break;
            }
            case 97: {
                index = ChaosPersists.LeonEgg;
                break;
            }
            case 98: {
                index = ChaosPersists.HammerheadEgg;
                break;
            }
            case 99: {
                index = ChaosPersists.RubberDuckyEgg;
                break;
            }
            case 100: {
                index = ChaosPersists.CrystalCowEgg;
                break;
            }
            case 101: {
                index = ChaosPersists.CriminalEgg;
                break;
            }
            case 102: {
                index = ChaosPersists.TheQueenEgg;
                break;
            }
            case 103: {
                index = ChaosPersists.BrutalflyEgg;
                break;
            }
            case 104: {
                index = ChaosPersists.NastysaurusEgg;
                break;
            }
            case 105: {
                index = ChaosPersists.PointysaurusEgg;
                break;
            }
            case 106: {
                index = ChaosPersists.CricketEgg;
                break;
            }
            case 107: {
                index = ChaosPersists.ThePrincessEgg;
                break;
            }
            case 108: {
                index = ChaosPersists.FrogEgg;
                break;
            }
            case 109: {
                index = ChaosPersists.JefferyEgg;
                break;
            }
            case 110: {
                index = ChaosPersists.AntRobotEgg;
                break;
            }
            case 111: {
                index = ChaosPersists.SpiderRobotEgg;
                break;
            }
            case 112: {
                index = ChaosPersists.SpiderDriverEgg;
                break;
            }
            case 113: {
                index = ChaosPersists.CrabEgg;
                break;
            }
            default: {
                index = null;
            }
        }
        if (index == null) {
            return null;
        }
        is = new ItemStack(index, par1);
        var3 = new ItemEntity(this.level(), this.getX() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(2) - (double)ChaosPersists.ChaosRand.nextInt(2), is);
        if (var3 != null) {
            this.level().addFreshEntity(var3);
        }
        
        return is;
    }

    public static boolean checkEasterBunnySpawnRules(
            EntityType<EasterBunny> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (ChaosPersists.easter_day == 0) {
            return false;
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        List<EasterBunny> nearby =
                level.getLevel()
                        .getEntitiesOfClass(EasterBunny.class, new AABB(pos).inflate(32.0, 8.0, 32.0));
        return nearby.isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (ChaosPersists.easter_day == 0) {
            return false;
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        List<EasterBunny> nearby =
                this.level()
                        .getEntitiesOfClass(
                                EasterBunny.class, this.getBoundingBox().inflate(32.0, 8.0, 32.0));
        for (EasterBunny eb : nearby) {
            if (eb != this) {
                return false;
            }
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
    public EasterBunny getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return (EasterBunny) this.getType().create(level);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ChaosPersists.MyCrystalApple);
    }
}
