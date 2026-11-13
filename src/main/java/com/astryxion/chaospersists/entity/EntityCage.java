package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.item.CritterCage;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.neoforged.neoforge.entity.PartEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
public class EntityCage
extends ThrowableProjectile {
    private float my_rotation = 0.0f;
    public int my_index = 160;
    private Level throwerLevel = null;
    private LivingEntity thrower = null;

    public EntityCage(EntityType<? extends EntityCage> type, Level level) {
        super(type, level);
        this.throwerLevel = level;
    }

    public EntityCage(EntityType<? extends EntityCage> type, Level level, int i) {
        super(type, level);
        this.throwerLevel = level;
        this.my_index = i;
    }

    public EntityCage(EntityType<? extends EntityCage> type, Level level, LivingEntity throwerIn, int cageId) {
        super(type, throwerIn, level);
        this.throwerLevel = level;
        this.thrower = throwerIn;
        this.my_index = cageId;
        if (this.thrower != null) {
            this.throwerLevel = this.thrower.level();
        }
    }

    
    private void dropCageItem(Item item, int count) {
        if (!this.level().isClientSide && item != null) {
            this.spawnAtLocation(new ItemStack(item, count));
        }
    }

    @Override
    protected void defineSynchedData(net.minecraft.network.syncher.SynchedEntityData.Builder builder) {}

    public int getCageIndex() {
        return this.my_index;
    }

    @Override
    protected void onHit(HitResult result) {
        if (this.isRemoved()) {
            return;
        }
        int empty_id = ((CritterCage)ChaosPersists.CageEmpty).cage_id;
        if (this.my_index != empty_id) {
            if (!this.level().isClientSide) {
                Entity ent = CritterCage.spawnMobForCage(this.level(), this.my_index, this.getX(), this.getY(), this.getZ());
                if (ent != null) {
                    ent.spawnAtLocation(new ItemStack(ChaosPersists.CageEmpty));
                }
            }
            this.discard();
            return;
        }
        if (result.getType() == HitResult.Type.ENTITY && this.random.nextInt(10) >= 2) {
            Entity hitEntity = ((EntityHitResult) result).getEntity();
            if (this.throwerLevel != null) {
                for (int var3 = 0; var3 < 4; ++var3) {
                    this.throwerLevel.addParticle(ParticleTypes.SMOKE, hitEntity.getX(), hitEntity.getY() + 0.25, hitEntity.getZ(), 0.0, 0.0, 0.0);
                    this.throwerLevel.addParticle(ParticleTypes.EXPLOSION, hitEntity.getX(), hitEntity.getY() + 0.25, hitEntity.getZ(), 0.0, 0.0, 0.0);
                    this.throwerLevel.addParticle(net.minecraft.core.particles.ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, 0xFFFF0000), hitEntity.getX(), hitEntity.getY() + 0.25, hitEntity.getZ(), 1.0, 0.0, 0.0);
                }
                if (this.thrower != null) {
                    this.throwerLevel.playSound(null, this.thrower.getX(), this.thrower.getY(), this.thrower.getZ(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 1.0f, 1.5f);
                }
            }
            if (!this.level().isClientSide) {
            if (hitEntity instanceof Player) {
                if (!this.level().isClientSide) {
                    this.dropCageItem(ChaosPersists.CageEmpty, 1);
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof SpiderDriver) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSpiderDriver, 1);
            } else if (hitEntity instanceof Spider) {
                if (hitEntity instanceof CaveSpider) {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedCaveSpider, 1);
                } else {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedSpider, 1);
                }
            }
            if (hitEntity instanceof Crab) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCrab, 1);
            }
            if (hitEntity instanceof Bat) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBat, 2);
            }
            if (hitEntity instanceof Pig) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedPig, 1);
            }
            if (hitEntity instanceof Squid) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSquid, 1);
            }
            if (hitEntity instanceof Chicken) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedChicken, 1);
            }
            if (hitEntity instanceof Creeper) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCreeper, 1);
            }
            if (hitEntity instanceof Horse) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedHorse, 1);
            }
            if (hitEntity instanceof Skeleton) {
                Skeleton sk = (Skeleton)hitEntity;
                if (hitEntity instanceof WitherSkeleton) {
                    this.dropCageItem(ChaosPersists.CagedWitherSkeleton, 1);
                } else {
                    this.dropCageItem(ChaosPersists.CagedSkeleton, 1);
                }
                hitEntity.discard();
            }
            if (hitEntity instanceof Zombie) {
                if (hitEntity instanceof ZombifiedPiglin) {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedZombiePigman, 1);
                } else {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedZombie, 1);
                }
            }
            if (hitEntity instanceof Slime) {
                if (hitEntity instanceof MagmaCube) {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedMagmaCube, 1);
                } else {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedSlime, 1);
                }
            }
            if (hitEntity instanceof Ghast) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGhast, 1);
            }
            if (hitEntity instanceof EnderMan) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEnderman, 1);
            }
            if (hitEntity instanceof Silverfish) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSilverfish, 2);
            }
            if (hitEntity instanceof Witch) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedWitch, 1);
            }
            if (hitEntity instanceof Sheep) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSheep, 1);
            }
            if (hitEntity instanceof Wolf) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedWolf, 1);
            }
            if (hitEntity instanceof Ocelot) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedOcelot, 1);
            }
            if (hitEntity instanceof Blaze) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBlaze, 1);
            }
            if (hitEntity instanceof Girlfriend girlfriend && !girlfriend.isTame()) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGirlfriend, 1);
            }
            if (hitEntity instanceof Boyfriend boyfriend && !boyfriend.isTame()) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBoyfriend, 1);
            }
            if (hitEntity instanceof EnderDragon) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                EnderDragon dr = (EnderDragon)hitEntity;
                dr.discard();
                this.dropCageItem(ChaosPersists.CagedEnderDragon, 1);
            }
            if (hitEntity instanceof PartEntity<?> part) {
                if (!(part.getParent() instanceof EnderDragon)) return;
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                EnderDragon dr = (EnderDragon) part.getParent();
                dr.discard();
                this.dropCageItem(ChaosPersists.CagedEnderDragon, 1);
            }
            if (hitEntity instanceof SnowGolem) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSnowGolem, 1);
            }
            if (hitEntity instanceof IronGolem) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedIronGolem, 1);
            }
            if (hitEntity instanceof WitherBoss) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedWitherBoss, 1);
            }
            if (hitEntity instanceof CrystalCow) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCrystalCow, 1);
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof EnchantedCow) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEnchantedCow, 1);
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof GoldCow) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGoldCow, 1);
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof RedCow) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedRedCow, 1);
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof Cow) {
                if (hitEntity instanceof MushroomCow) {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedMooshroom, 1);
                } else {
                    hitEntity.discard();
                    this.dropCageItem(ChaosPersists.CagedCow, 1);
                }
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof Villager) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedVillager, 1);
                if (!this.level().isClientSide) {
                    this.discard();
                }
                return;
            }
            if (hitEntity instanceof Mothra) {
                if (this.random.nextInt(10) < 4) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedMOTHRA, 1);
            }
            if (hitEntity instanceof Alosaurus) {
                if (this.random.nextInt(10) < 4) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedAlo, 1);
            }
            if (hitEntity instanceof Cryolophosaurus) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCryo, 1);
            }
            if (hitEntity instanceof Camarasaurus) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCama, 1);
            }
            if (hitEntity instanceof VelocityRaptor) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedVelo, 1);
            }
            if (hitEntity instanceof Hydrolisc) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedHydro, 1);
            }
            if (hitEntity instanceof Basilisk) {
                if (this.random.nextInt(10) < 6) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBasil, 1);
            }
            if (hitEntity instanceof Dragonfly) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedDragonfly, 2);
            }
            if (hitEntity instanceof EmperorScorpion) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEmperorScorpion, 1);
            }
            if (hitEntity instanceof Cephadrome) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCephadrome, 1);
            }
            if (hitEntity instanceof Dragon) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedDragon, 1);
            }
            if (hitEntity instanceof Scorpion) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedScorpion, 1);
            }
            if (hitEntity instanceof CaveFisher) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCaveFisher, 1);
            }
            if (hitEntity instanceof Spyro) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSpyro, 1);
            }
            if (hitEntity instanceof Baryonyx) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBaryonyx, 1);
            }
            if (hitEntity instanceof GammaMetroid) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGammaMetroid, 1);
            }
            if (hitEntity instanceof Cockateil) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCockateil, 4);
            }
            if (hitEntity instanceof AttackSquid) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedAttackSquid, 6);
            }
            if (hitEntity instanceof Kyuubi) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedKyuubi, 1);
            }
            if (hitEntity instanceof WaterDragon) {
                if (this.random.nextInt(10) < 6) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedWaterDragon, 1);
            }
            if (hitEntity instanceof Kraken) {
                if (this.random.nextInt(100) < 95) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedKraken, 1);
            }
            if (hitEntity instanceof Lizard) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedLizard, 1);
            }
            if (hitEntity instanceof Alien) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedAlien, 1);
            }
            if (hitEntity instanceof Bee) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBee, 1);
            }
            if (hitEntity instanceof Firefly) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedFirefly, 1);
            }
            if (hitEntity instanceof Chipmunk) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedChipmunk, 1);
            }
            if (hitEntity instanceof Gazelle) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGazelle, 1);
            }
            if (hitEntity instanceof Ostrich) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedOstrich, 1);
            }
            if (hitEntity instanceof TrooperBug) {
                if (this.random.nextInt(10) < 6) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedTrooper, 1);
            }
            if (hitEntity instanceof SpitBug) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSpit, 1);
            }
            if (hitEntity instanceof StinkBug) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedStink, 1);
            }
            if (hitEntity instanceof CreepingHorror) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCreepingHorror, 1);
            }
            if (hitEntity instanceof TerribleTerror) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedTerribleTerror, 1);
            }
            if (hitEntity instanceof CliffRacer) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCliffRacer, 1);
            }
            if (hitEntity instanceof Triffid) {
                if (this.random.nextInt(10) < 6) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedTriffid, 1);
            }
            if (hitEntity instanceof PitchBlack) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedPitchBlack, 1);
            }
            if (hitEntity instanceof LurkingTerror) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedLurkingTerror, 1);
            }
            if (hitEntity instanceof WormSmall) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSmallWorm, 1);
            }
            if (hitEntity instanceof WormMedium) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedMediumWorm, 1);
            }
            if (hitEntity instanceof Cassowary) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCassowary, 1);
            }
            if (hitEntity instanceof CloudShark) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCloudShark, 1);
            }
            if (hitEntity instanceof GoldFish) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedGoldFish, 1);
            }
            if (hitEntity instanceof LeafMonster) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedLeafMonster, 1);
            }
            if (hitEntity instanceof WormLarge) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedLargeWorm, 1);
            }
            if (hitEntity instanceof EnderKnight) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEnderKnight, 1);
            }
            if (hitEntity instanceof EnderReaper) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEnderReaper, 1);
            }
            if (hitEntity instanceof Beaver) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBeaver, 1);
            }
            if (hitEntity instanceof Urchin) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedUrchin, 1);
            }
            if (hitEntity instanceof Flounder) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedFlounder, 1);
            }
            if (hitEntity instanceof Skate) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSkate, 1);
            }
            if (hitEntity instanceof Rotator) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedRotator, 1);
            }
            if (hitEntity instanceof Peacock) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedPeacock, 1);
            }
            if (hitEntity instanceof Fairy) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedFairy, 1);
            }
            if (hitEntity instanceof DungeonBeast) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedDungeonBeast, 1);
            }
            if (hitEntity instanceof Vortex) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedVortex, 1);
            }
            if (hitEntity instanceof Rat) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedRat, 1);
            }
            if (hitEntity instanceof Whale) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedWhale, 1);
            }
            if (hitEntity instanceof Irukandji) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedIrukandji, 1);
            }
            if (hitEntity instanceof Stinky) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedStinky, 1);
            }
            if (hitEntity instanceof Mantis) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedMantis, 1);
            }
            if (hitEntity instanceof TRex) {
                if (this.random.nextInt(10) < 4) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedTRex, 1);
            }
            if (hitEntity instanceof HerculesBeetle) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedHercules, 1);
            }
            if (hitEntity instanceof EasterBunny) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedEasterBunny, 1);
            }
            if (hitEntity instanceof CaterKiller) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCaterKiller, 1);
            }
            if (hitEntity instanceof Molenoid) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedMolenoid, 1);
            }
            if (hitEntity instanceof SeaMonster) {
                if (this.random.nextInt(10) < 3) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSeaMonster, 1);
            }
            if (hitEntity instanceof SeaViper) {
                if (this.random.nextInt(10) < 4) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedSeaViper, 1);
            }
            if (hitEntity instanceof RubberDucky) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedRubberDucky, 1);
            }
            if (hitEntity instanceof Leon) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedLeon, 1);
            }
            if (hitEntity instanceof Hammerhead) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedHammerhead, 1);
            }
            if (hitEntity instanceof BandP) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCriminal, 1);
            }
            if (hitEntity instanceof Cricket) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedCricket, 1);
            }
            if (hitEntity instanceof Frog) {
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedFrog, 1);
            }
            if (hitEntity instanceof Brutalfly) {
                if (this.random.nextInt(10) < 5) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedBrutalfly, 1);
            }
            if (hitEntity instanceof Nastysaurus) {
                if (this.random.nextInt(10) < 7) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedNastysaurus, 1);
            }
            if (hitEntity instanceof Pointysaurus) {
                if (this.random.nextInt(10) < 2) {
                    if (!this.level().isClientSide) {
                        this.dropCageItem(ChaosPersists.CageEmpty, 1);
                        this.discard();
                    }
                    return;
                }
                hitEntity.discard();
                this.dropCageItem(ChaosPersists.CagedPointysaurus, 1);
            }
            }
            this.discard();
            return;
        } else if (!this.level().isClientSide) {
            this.dropCageItem(ChaosPersists.CageEmpty, 1);
        }
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.my_rotation += 20.0f;
        while (this.my_rotation > 360.0f) {
            this.my_rotation -= 360.0f;
        }
        this.setXRot(this.my_rotation);
        this.xRotO = this.my_rotation;
    }
}

