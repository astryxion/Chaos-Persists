package com.astryxion.chaospersists.entity;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;

import com.astryxion.chaospersists.compat.eeeabsmobs.EeeabsMobsCompat;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.item.Shoes;
import com.astryxion.chaospersists.item.UltimateArrow;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIJealousy;
import com.astryxion.chaospersists.util.MyEntityAINearestAttackableTarget;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.UUID;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.phys.Vec3;

public class Boyfriend extends TamableAnimal implements RangedAttackMob {
    private static final EntityDataAccessor<Integer> WHICH_GUY =
            SynchedEntityData.defineId(Boyfriend.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VOICE =
            SynchedEntityData.defineId(Boyfriend.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WHICH_WET_GUY =
            SynchedEntityData.defineId(Boyfriend.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VOICE_ENABLE =
            SynchedEntityData.defineId(Boyfriend.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> IS_PRINCE =
            SynchedEntityData.defineId(Boyfriend.class, EntityDataSerializers.INT);
    private static final EquipmentSlot[] EQUIPMENT_SLOTS =
            new EquipmentSlot[] {
                EquipmentSlot.MAINHAND,
                EquipmentSlot.FEET,
                EquipmentSlot.LEGS,
                EquipmentSlot.CHEST,
                EquipmentSlot.HEAD,
                EquipmentSlot.OFFHAND
            };

    public int which_guy = 0;
    public int which_wet_guy = 0;
    public int wet_count = 0;
    private int auto_heal = 200;
    private int force_sync = 50;
    private int fight_sound_ticker = 0;
    private int taunt_sound_ticker = 0;
    private int attackTime = 0;
    private int had_target = 0;
    private int voice = 0;
    private int is_prince = 0;
    /** True after NBT restore or finalizeSpawn assigned a permanent skin. */
    private boolean skinInitialized = false;
    private float moveSpeed = 0.3f;
    private int voice_enable = 1;
    public int passenger = 0;

    private static final ResourceLocation DryTexture0 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend0.png");
    private static final ResourceLocation DryTexture1 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend1.png");
    private static final ResourceLocation DryTexture2 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend2.png");
    private static final ResourceLocation DryTexture3 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend3.png");
    private static final ResourceLocation DryTexture4 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend4.png");
    private static final ResourceLocation DryTexture5 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend5.png");
    private static final ResourceLocation DryTexture6 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend6.png");
    private static final ResourceLocation DryTexture7 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend7.png");
    private static final ResourceLocation DryTexture8 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend8.png");
    private static final ResourceLocation DryTexture9 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend9.png");
    private static final ResourceLocation DryTexture10 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend10.png");
    private static final ResourceLocation DryTexture11 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend11.png");
    private static final ResourceLocation DryTexture12 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend12.png");
    private static final ResourceLocation DryTexture13 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend13.png");
    private static final ResourceLocation DryTexture14 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend14.png");
    private static final ResourceLocation DryTexture15 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend15.png");
    private static final ResourceLocation DryTexture16 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend16.png");
    private static final ResourceLocation DryTexture17 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend17.png");
    private static final ResourceLocation DryTexture18 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend18.png");
    private static final ResourceLocation DryTexture19 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend19.png");
    private static final ResourceLocation DryTexture20 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend20.png");
    private static final ResourceLocation DryTexture21 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend21.png");
    private static final ResourceLocation DryTexture22 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend22.png");
    private static final ResourceLocation DryTexture23 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend23.png");
    private static final ResourceLocation DryTexture24 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend24.png");
    private static final ResourceLocation DryTexture25 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend25.png");
    private static final ResourceLocation DryTexture26 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend26.png");
    private static final ResourceLocation DryTexture27 =
            new ResourceLocation("chaospersists", "textures/entity/boyfriend27.png");
    private static final ResourceLocation WetTexture0 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts0.png");
    private static final ResourceLocation WetTexture1 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts1.png");
    private static final ResourceLocation WetTexture2 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts2.png");
    private static final ResourceLocation WetTexture3 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts3.png");
    private static final ResourceLocation WetTexture4 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts4.png");
    private static final ResourceLocation WetTexture5 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts5.png");
    private static final ResourceLocation WetTexture6 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts6.png");
    private static final ResourceLocation WetTexture7 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts7.png");
    private static final ResourceLocation WetTexture8 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts8.png");
    private static final ResourceLocation WetTexture9 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts9.png");
    private static final ResourceLocation WetTexture10 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts10.png");
    private static final ResourceLocation WetTexture11 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts11.png");
    private static final ResourceLocation WetTexture12 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts12.png");
    private static final ResourceLocation WetTexture13 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts13.png");
    private static final ResourceLocation WetTexture14 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts14.png");
    private static final ResourceLocation WetTexture15 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts15.png");
    private static final ResourceLocation WetTexture16 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts16.png");
    private static final ResourceLocation WetTexture17 =
            new ResourceLocation("chaospersists", "textures/entity/swimshorts17.png");
    private static final ResourceLocation PrinceTexture1 =
            new ResourceLocation("chaospersists", "textures/entity/frogprince.png");
    private static final ResourceLocation PrinceTexture2 =
            new ResourceLocation("chaospersists", "textures/entity/frogprince2.png");

    public Boyfriend(EntityType<? extends Boyfriend> type, Level level) {
        super(type, level);
        this.setOrderedToSit(false);
        if (this.getNavigation() instanceof net.minecraft.world.entity.ai.navigation.GroundPathNavigation ground) {
            ground.setCanOpenDoors(true);
            ground.setCanPassDoors(true);
            ground.setCanFloat(true);
        }
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner(this, 1.4f, 12.0f, 1.5f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, Ingredient.of(Items.COOKED_BEEF), false));
        this.goalSelector.addGoal(4, new RangedAttackGoal(this, 1.25, 20, 10.0f));
        this.goalSelector.addGoal(5, new FloatGoal(this));
        this.goalSelector.addGoal(6, EeeabsMobsCompat.panicGoal(this, 1.5));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(10, new OpenDoorGoal(this, true));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(
                    2, new MyEntityAINearestAttackableTarget(this, Creeper.class, 20.0f, 0, true, true, null));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(
                    3,
                    new MyEntityAINearestAttackableTarget(
                            this, LivingEntity.class, 15.0f, 0, true, true, null));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(4, new MyEntityAIJealousy(this, Boyfriend.class, 6.0f, 5, true));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(5, new MyEntityAIJealousy(this, Boyfriend.class, 3.0f, 15, true));
        }
        this.xpReward = 0;
        this.entityData.set(VOICE_ENABLE, this.voice_enable);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 8.0);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        // Defaults only — randomize in finalizeSpawn so chunk reload does not re-roll skins.
        this.entityData.define(WHICH_GUY, 0);
        this.wet_count = 0;
        this.entityData.define(WHICH_WET_GUY, 0);
        this.entityData.define(VOICE, 0);
        // Literal 1: defineSynchedData runs inside super(), before voice_enable = 1.
        this.entityData.define(VOICE_ENABLE, 1);
        this.entityData.define(IS_PRINCE, this.is_prince);
        this.auto_heal = 200;
        this.force_sync = 50;
        this.fight_sound_ticker = 0;
        this.taunt_sound_ticker = 0;
        this.had_target = 0;
        this.setOrderedToSit(false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("GirlType", this.getTameSkin());
        tag.putInt("WetGirlType", this.getWetTameSkin());
        tag.putInt("GirlVoice", this.entityData.get(VOICE));
        tag.putInt("GirlVoiceEnable", this.entityData.get(VOICE_ENABLE));
        tag.putInt("IsPrince", this.entityData.get(IS_PRINCE));
        tag.putBoolean("GirlSkinInitialized", true);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.contains("GirlType")) {
            this.setTameSkin(tag.getInt("GirlType"));
        }
        if (tag.contains("WetGirlType")) {
            this.setWetTameSkin(tag.getInt("WetGirlType"));
        }
        if (tag.contains("GirlVoice")) {
            this.voice = tag.getInt("GirlVoice");
            this.entityData.set(VOICE, this.voice);
        }
        if (tag.contains("GirlVoiceEnable")) {
            this.voice_enable = tag.getInt("GirlVoiceEnable");
            this.entityData.set(VOICE_ENABLE, this.voice_enable);
        }
        if (tag.contains("IsPrince")) {
            this.is_prince = tag.getInt("IsPrince");
            this.entityData.set(IS_PRINCE, this.is_prince);
        }
        this.skinInitialized = tag.contains("GirlType") || tag.getBoolean("GirlSkinInitialized");
        if (this.getMainHandItem().is(Items.DIAMOND)) {
            this.setOrderedToSit(true);
        }
    }

    private void syncArmorFromEquipment() {
        int i = 0;
        for (ItemStack itemstack : this.getArmorSlots()) {
            if (!itemstack.isEmpty() && itemstack.getItem() instanceof ArmorItem armorItem) {
                i += armorItem.getDefense();
            }
        }
        if (i < 8) {
            i = 8;
        }
        if (i > 23) {
            i = 23;
        }
        this.getAttribute(Attributes.ARMOR).setBaseValue((double) i);
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        this.syncArmorFromEquipment();
        super.tick();
        this.passenger = 0;
        if (this.isTame() && !this.isInSittingPose()) {
            LivingEntity e = this.getOwner();
            if (e instanceof Player) {
                Entity r = e.getVehicle();
                if (r instanceof Elevator elevator) {
                    float f = -0.45f;
                    this.setPos(
                            elevator.getX() - (double) f * Math.sin(Math.toRadians(elevator.getYRot())),
                            elevator.getY(),
                            elevator.getZ() + (double) f * Math.cos(Math.toRadians(elevator.getYRot())));
                    this.setYRot(elevator.getYRot());
                    this.setXRot(elevator.getXRot());
                    this.walkAnimation.setSpeed(0.0f);
                    this.walkAnimation.position(0.0f);
                    this.fallDistance = 0.0f;
                    this.passenger = 1;
                }
            }
        }
        this.setRemainingFireTicks(0);
    }

    @Override
    public void aiStep() {
        this.updateSwingTime();
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        super.aiStep();
        if (this.isInWater() || this.isInLava()) {
            this.wet_count = 500;
        } else if (this.wet_count > 0) {
            --this.wet_count;
        }
        --this.auto_heal;
        if (this.auto_heal <= 0) {
            if (this.mygetMaxHealth() > this.getBoyfriendHealth()) {
                this.heal(1.0f);
            }
            this.auto_heal = 150;
        }
        --this.force_sync;
        if (this.force_sync <= 0) {
            this.force_sync = 20;
            if (!this.level().isClientSide) {
                this.ensureSkinInitialized();
                this.setTameSkin(this.which_guy);
                this.setWetTameSkin(this.which_wet_guy);
                this.entityData.set(VOICE, this.voice);
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.entityData.set(IS_PRINCE, this.is_prince);
            } else {
                this.which_guy = this.getTameSkin();
                this.which_wet_guy = this.getWetTameSkin();
                this.voice = this.getVoice();
                this.voice_enable = this.entityData.get(VOICE_ENABLE);
                this.is_prince = this.entityData.get(IS_PRINCE);
            }
        }
        if (!this.level().isClientSide) {
            this.customCombatAiStep();
        }
        if (this.isOrderedToSit() || this.isInSittingPose()) {
            this.getNavigation().stop();
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (!this.level().isClientSide && orderedToSit) {
            this.getNavigation().stop();
            PetCombatHelper.onPetSit(this);
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    private void customCombatAiStep() {
        PetCombatHelper.tickPetCombat(this);
        ItemStack stack = this.getMainHandItem();
        LivingEntity prior = this.getTarget();
        // Freeroam hostiles come from targetSelector; resolve adds owner-assist + sit guards.
        LivingEntity resolved =
                PetCombatHelper.resolveCombatTarget(this, prior, () -> EeeabsMobsCompat.findNearbyHunt(this));
        if (resolved != prior) {
            this.setTarget(resolved);
        }
        LivingEntity victim = this.getTarget();
        if (ChaosPersists.PlayNicely != 0) {
            victim = null;
        }
        if (victim != null && !PetCombatHelper.shouldRetainTarget(this, victim)) {
            this.setTarget(null);
            victim = null;
        }
        if (this.level().getRandom().nextInt(100) == 1) {
            this.setLastHurtByMob(null);
        }
        if (!stack.isEmpty() && !this.isInSittingPose()) {
            if (victim != null) {
                if (!this.getMainHandItem().isEmpty()) {
                    float extraReach = EeeabsMobsCompat.extraMeleeReach(victim);
                    if (this.distanceTo(victim) < 4.0f + extraReach
                            || stack.is(ChaosPersists.MyBertha)
                                    && this.distanceTo(victim) < 10.0f + extraReach) {
                        --this.attackTime;
                        if (this.attackTime <= 0) {
                            this.attackTime = 25;
                            this.swing(InteractionHand.MAIN_HAND);
                            this.attackTargetWithCurrentItem(victim);
                            --this.fight_sound_ticker;
                            if (this.fight_sound_ticker <= 0) {
                                if (this.voice_enable != 0) {
                                    this.level()
                                            .playSound(
                                                    null,
                                                    this.getX(),
                                                    this.getY(),
                                                    this.getZ(),
                                                    ChaosSounds.B_FIGHT,
                                                    this.getSoundSource(),
                                                    0.5f,
                                                    this.getVoicePitch());
                                }
                                this.fight_sound_ticker = 3;
                            }
                            this.had_target = 1;
                        }
                    } else if (this.distanceTo(victim) < 7.0f + extraReach
                            && !stack.is(ChaosPersists.MyUltimateBow)) {
                        --this.taunt_sound_ticker;
                        if (this.taunt_sound_ticker <= 0) {
                            if (this.voice_enable != 0) {
                                this.level()
                                        .playSound(
                                                null,
                                                this.getX(),
                                                this.getY(),
                                                this.getZ(),
                                                ChaosSounds.B_TAUNT,
                                                this.getSoundSource(),
                                                0.5f,
                                                this.getVoicePitch());
                            }
                            this.taunt_sound_ticker = 300;
                        }
                        this.getNavigation().moveTo(victim, 1.25);
                    }
                }
            } else {
                this.fight_sound_ticker = 0;
                this.attackTime = 0;
                if (this.had_target != 0) {
                    this.had_target = 0;
                    if (this.voice_enable != 0) {
                        this.level()
                                .playSound(
                                        null,
                                        this.getX(),
                                        this.getY(),
                                        this.getZ(),
                                        ChaosSounds.B_WOOHOO,
                                        this.getSoundSource(),
                                        0.4f,
                                        this.getVoicePitch());
                    }
                }
            }
        }
    }

    public void setPrince(int par1) {
        this.is_prince = par1;
    }

    public void setSwingingArms(boolean swingingArms) {}

    public ResourceLocation getTexture() {
        if (this.wet_count <= 0) {
            int txture = this.getTameSkin();
            if (this.is_prince == 1) {
                return PrinceTexture1;
            }
            if (this.is_prince == 2) {
                return PrinceTexture2;
            }
            return switch (txture) {
                case 0 -> DryTexture0;
                case 1 -> DryTexture1;
                case 2 -> DryTexture2;
                case 3 -> DryTexture3;
                case 4 -> DryTexture4;
                case 5 -> DryTexture5;
                case 6 -> DryTexture6;
                case 7 -> DryTexture7;
                case 8 -> DryTexture8;
                case 9 -> DryTexture9;
                case 10 -> DryTexture10;
                case 11 -> DryTexture11;
                case 12 -> DryTexture12;
                case 13 -> DryTexture13;
                case 14 -> DryTexture14;
                case 15 -> DryTexture15;
                case 16 -> DryTexture16;
                case 17 -> DryTexture17;
                case 18 -> DryTexture18;
                case 19 -> DryTexture19;
                case 20 -> DryTexture20;
                case 21 -> DryTexture21;
                case 22 -> DryTexture22;
                case 23 -> DryTexture23;
                case 24 -> DryTexture24;
                case 25 -> DryTexture25;
                case 26 -> DryTexture26;
                case 27 -> DryTexture27;
                default -> DryTexture0;
            };
        } else {
            int temp = this.getWetTameSkin();
            return switch (temp) {
                case 0 -> WetTexture0;
                case 1 -> WetTexture1;
                case 2 -> WetTexture2;
                case 3 -> WetTexture3;
                case 4 -> WetTexture4;
                case 5 -> WetTexture5;
                case 6 -> WetTexture6;
                case 7 -> WetTexture7;
                case 8 -> WetTexture8;
                case 9 -> WetTexture9;
                case 10 -> WetTexture10;
                case 11 -> WetTexture11;
                case 12 -> WetTexture12;
                case 13 -> WetTexture13;
                case 14 -> WetTexture14;
                case 15 -> WetTexture15;
                case 16 -> WetTexture16;
                case 17 -> WetTexture17;
                default -> WetTexture0;
            };
        }
    }

    public int getTameSkin() {
        return this.entityData.get(WHICH_GUY);
    }

    public int getVoice() {
        return this.entityData.get(VOICE);
    }

    public void setTameSkin(int par1) {
        this.entityData.set(WHICH_GUY, par1 == 0 ? 1 : 0);
        this.entityData.set(WHICH_GUY, par1);
        this.which_guy = par1;
        this.skinInitialized = true;
    }

    public int getWetTameSkin() {
        return this.entityData.get(WHICH_WET_GUY);
    }

    public void setWetTameSkin(int par1) {
        this.entityData.set(WHICH_WET_GUY, par1 == 0 ? 1 : 0);
        this.entityData.set(WHICH_WET_GUY, par1);
        this.which_wet_guy = par1;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public boolean causeFallDamage(float par1, float damageMultiplier, DamageSource source) {
        float i = (float) Mth.ceil(par1 - 3.0f);
        if (i > 0.0f) {
            if (i > 3.0f) {
                this.playSound(SoundEvents.GENERIC_BIG_FALL, 1.0f, 1.0f);
                i = 3.0f;
            } else {
                this.playSound(SoundEvents.GENERIC_SMALL_FALL, 1.0f, 1.0f);
            }
            this.hurt(this.damageSources().fall(), i);
        }
        return false;
    }

    public int mygetMaxHealth() {
        return 80;
    }

    public int getBoyfriendHealth() {
        return (int) this.getHealth();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND) {
            return super.mobInteract(player, hand);
        }
        if (this.interact(player)) {
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    public boolean interact(Player player) {
        ItemStack var2 = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (var2.isEmpty()) {
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()
                && (var2.is(Items.COOKED_BEEF) || var2.is(ChaosPersists.MyPeacock))
                && player.distanceToSqr(this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(3) == 0) {
                        this.tame(player);
                        this.spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(player)) {
                if (this.level().isClientSide) {
                    this.spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DEAD_BUSH.asItem())
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setOwnerUUID((UUID) null);
                this.spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(ChaosPersists.MyRuby)
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.voice_enable = 0;
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
            }
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(ChaosPersists.MyAmethyst)
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                this.voice_enable = 1;
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.spawnTamingParticles(true);
                this.level().broadcastEntityEvent(this, (byte) 7);
            }
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && (var2.is(Items.LEATHER) || var2.is(ChaosPersists.MyPeacockFeather))
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            if (!this.level().isClientSide) {
                if (this.wet_count > 0 || this.isInWater() || this.isInLava()) {
                    ++this.which_wet_guy;
                    if (this.which_wet_guy > 17) {
                        this.which_wet_guy = 0;
                    }
                    this.setWetTameSkin(this.which_wet_guy);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                    if (this.isInWater() || this.isInLava()) {
                        this.wet_count = 500;
                    }
                } else {
                    ++this.which_guy;
                    if (this.which_guy > 27) {
                        this.which_guy = 0;
                    }
                    this.setTameSkin(this.which_guy);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
            }
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && !var2.isEmpty() && this.isOwnedBy(player) && player.distanceToSqr(this) < 16.0) {
            if (var2.getItem().isEdible()) {
                if (!this.level().isClientSide) {
                    if ((float) this.mygetMaxHealth() > this.getHealth()
                            && var2.getFoodProperties(this) != null) {
                        this.heal((float) (var2.getFoodProperties(this).getNutrition() * 5));
                    }
                    this.spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if (!player.getAbilities().instabuild) {
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    }
                }
            } else if (var2.getItem() instanceof ArmorItem armorItem) {
                if (!this.level().isClientSide) {
                    this.spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                EquipmentSlot slot = armorItem.getEquipmentSlot();
                ItemStack oldArmor = this.getItemBySlot(slot).copy();
                if (player.getAbilities().instabuild) {
                    ItemStack equipCopy = var2.copy();
                    equipCopy.setCount(1);
                    this.setItemSlot(slot, equipCopy);
                } else {
                    ItemStack equipOne = var2.copy();
                    equipOne.setCount(1);
                    this.setItemSlot(slot, equipOne);
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    }
                }
                if (!oldArmor.isEmpty()) {
                    if (player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, oldArmor);
                    } else if (!player.getInventory().add(oldArmor)) {
                        player.drop(oldArmor, false);
                    }
                }
            } else {
                if (!this.level().isClientSide) {
                    this.spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                ItemStack var3 = this.getMainHandItem();
                if (!this.level().isClientSide) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, var2);
                    if (var2.is(Items.DIAMOND)) {
                        this.setOrderedToSit(true);
                    } else {
                        this.setOrderedToSit(false);
                    }
                }
                if (!var3.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, var3);
                } else {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DIAMOND_BLOCK.asItem())
                && player.distanceToSqr(this) < 16.0) {
            this.setOrderedToSit(false);
            this.tame(player);
            this.spawnTamingParticles(true);
            this.level().broadcastEntityEvent(this, (byte) 7);
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Items.NAME_TAG)
                && player.distanceToSqr(this) < 16.0
                && this.isOwnedBy(player)) {
            this.setCustomName(var2.getHoverName());
            if (!player.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2.isEmpty() && player.distanceToSqr(this) < 16.0 && this.isOwnedBy(player)) {
            EquipmentSlot slotToStrip = null;
            ItemStack toGive = ItemStack.EMPTY;
            for (EquipmentSlot s : EQUIPMENT_SLOTS) {
                ItemStack equipped = this.getItemBySlot(s);
                if (!equipped.isEmpty()) {
                    toGive = equipped;
                    slotToStrip = s;
                    break;
                }
            }
            if (slotToStrip != null) {
                player.setItemInHand(InteractionHand.MAIN_HAND, toGive);
                this.setItemSlot(slotToStrip, ItemStack.EMPTY);
                this.setOrderedToSit(false);
                if (!this.level().isClientSide) {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
            } else if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
                String healthMessage =
                        String.format(
                                "I have %d health. Thank you for asking!", this.getBoyfriendHealth());
                player.sendSystemMessage(Component.literal(healthMessage));
            }
            return true;
        }
        return false;
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return !par1ItemStack.isEmpty()
                && (par1ItemStack.is(Items.COOKED_BEEF) || par1ItemStack.is(ChaosPersists.MyPeacock));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isInSittingPose() || this.voice_enable == 0) {
            return null;
        }
        if (ChaosPersists.bro_mode != 0 && this.getRandom().nextInt(2) == 1) {
            return null;
        }
        if (this.getRandom().nextInt(11) == 1) {
            LivingEntity victim = this.getTarget();
            if (victim != null) {
                return null;
            }
            if (this.isInWater() || this.isInLava()) {
                return ChaosSounds.B_WATER;
            }
            if (this.getRandom().nextInt(4) != 0) {
                if (this.getY() < 60.0) {
                    return null;
                }
                if (this.level().isThundering()) {
                    return ChaosSounds.B_THUNDER;
                }
                if (this.level().isRaining()) {
                    return ChaosSounds.B_RAIN;
                }
                if (!this.level().isDay()
                        && this.level().canSeeSky(this.blockPosition())) {
                    if (this.level().getRandom().nextInt(3) == 0) {
                        return ChaosSounds.B_DARK;
                    }
                    return null;
                }
            }
            if (this.isTame()) {
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    return ChaosSounds.B_HURT;
                }
                if (ChaosPersists.bro_mode != 0) {
                    return ChaosSounds.BB_HAPPY;
                }
                return ChaosSounds.B_HAPPY;
            }
            return null;
        }
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        if (this.voice_enable == 0) {
            return null;
        }
        if (ChaosPersists.bro_mode != 0 && this.getRandom().nextInt(2) == 1) {
            return null;
        }
        return ChaosSounds.B_OW;
    }

    @Override
    protected void playHurtSound(DamageSource source) {
        SoundEvent sound = this.getHurtSound(source);
        if (sound == null) {
            return;
        }
        // Ambient chatter stays at getSoundVolume() (0.3). Hit cries need to cut through
        // overworld noise; village is quiet enough that 0.3 sometimes sounded fine there.
        this.playSound(sound, 1.0f, this.getVoicePitch());
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (ChaosPersists.bro_mode != 0) {
            return null;
        }
        return this.isTame() ? ChaosSounds.B_DEATH_BOYFRIEND : ChaosSounds.B_DEATH_SINGLE;
    }

    @Override
    protected float getSoundVolume() {
        return 0.3f;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3;
        if (this.isTame()) {
            var3 = this.getRandom().nextInt(5) + 2;
            this.spawnAtLocation(Items.POPPY, var3);
        }
        var3 = this.getRandom().nextInt(26) + 10;
        this.spawnAtLocation(ChaosPersists.MyItemGameController, var3);
        if (this.isTame()) {
            ItemStack var5 = this.getMainHandItem();
            if (!var5.isEmpty()) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if (!(var5 = this.getItemBySlot(EquipmentSlot.OFFHAND)).isEmpty()) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if (!(var5 = this.getItemBySlot(EquipmentSlot.FEET)).isEmpty()) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if (!(var5 = this.getItemBySlot(EquipmentSlot.LEGS)).isEmpty()) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if (!(var5 = this.getItemBySlot(EquipmentSlot.CHEST)).isEmpty()) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
        }
    }

    public void attackEntityWithRangedAttack(LivingEntity par1EntityLiving) {
        if (this.swinging) {
            return;
        }
        ItemStack it = this.getMainHandItem();
        if (!it.isEmpty() && it.is(ChaosPersists.MyUltimateBow)) {
            UltimateArrow var8 = new UltimateArrow(this.level(), this, par1EntityLiving, 2.0f, 10.0f);
            if (this.level().getRandom().nextInt(4) == 1) {
                var8.setCritArrow(true);
            }
            int var10 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, it);
            if (var10 > 0) {
                var8.setKnockbackStrength(var10);
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, it) > 0) {
                var8.setSecondsOnFire(100);
            }
            it.hurtAndBreak(1, this, e -> e.broadcastBreakEvent(InteractionHand.MAIN_HAND));
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.SKELETON_SHOOT,
                            this.getSoundSource(),
                            1.0f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 1.2f) + 0.5f);
            var8.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            this.level().addFreshEntity(var8);
        } else {
            Shoes.shootTowardTarget(this, par1EntityLiving, 6);
        }
        this.swing(InteractionHand.MAIN_HAND);
    }

    public void attackTargetWithCurrentItem(Entity par1Entity) {
        ItemStack stack = this.getMainHandItem();
        if (!stack.isEmpty()) {
            float var2 = 0.0f;
            if (this.hasEffect(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST)) {
                var2 += (float)
                        (3 << this.getEffect(net.minecraft.world.effect.MobEffects.DAMAGE_BOOST).getAmplifier());
            }
            if (this.hasEffect(net.minecraft.world.effect.MobEffects.WEAKNESS)) {
                var2 -= (float)
                        (2 << this.getEffect(net.minecraft.world.effect.MobEffects.WEAKNESS).getAmplifier());
            }
            int var3 = 0;
            float var4 = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            if (par1Entity instanceof LivingEntity living) {
                var4 += EnchantmentHelper.getDamageBonus(stack, living.getMobType());
                var3 += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, stack);
            }
            if (this.isSprinting()) {
                ++var3;
            }
            if (var2 > 0.0f || var4 > 0.0f) {
                boolean var6;
                boolean var5 = this.fallDistance > 0.0f
                        && !this.onGround()
                        && !this.onClimbable()
                        && !this.isInWater()
                        && !this.isInLava()
                        && !this.hasEffect(net.minecraft.world.effect.MobEffects.BLINDNESS)
                        && this.getVehicle() == null
                        && par1Entity instanceof LivingEntity;
                if (var5) {
                    var2 += (float) this.getRandom().nextInt((int) var2 / 2 + 2);
                }
                if ((var6 = par1Entity.hurt(this.damageSources().mobAttack(this), var2 + var4)) && var3 > 0) {
                    par1Entity.push(
                            (double) (-Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (float) var3 * 0.5f),
                            0.1,
                            (double) (Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (float) var3 * 0.5f));
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.6, 1.0, 0.6));
                    this.setSprinting(false);
                }
                ItemStack var7 = this.getMainHandItem();
                if (par1Entity instanceof LivingEntity living
                        && (var3 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FIRE_ASPECT, var7)) > 0
                        && var6) {
                    living.setSecondsOnFire(var3 * 4);
                }
            }
        }
    }

    @Override
    public float getVoicePitch() {
        return (float) (this.voice - 5) * 0.02f + 1.0f;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public void setBaby(boolean baby) {}

    @Override
    public void setAge(int age) {
        super.setAge(Math.max(0, age));
    }

    @Override
    public void performRangedAttack(LivingEntity entityliving, float f) {
        this.attackEntityWithRangedAttack(entityliving);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        float p2 = par2;
        if (p2 > 10.0f) {
            p2 = 10.0f;
        }
        if (!par1DamageSource.is(DamageTypes.CACTUS)) {
            int prevHurtTime = this.hurtTime;
            ret = super.hurt(par1DamageSource, p2);
            if (!this.level().isClientSide && ret && this.hurtTime > prevHurtTime) {
                Entity src = par1DamageSource.getEntity();
                if (src instanceof LivingEntity living && src != this) {
                    Shoes.shootTowardTarget(this, living, 6);
                }
            }
        }
        return ret;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    public static boolean checkBoyfriendSpawnRules(
            EntityType<Boyfriend> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
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
                    ResourceLocation boyfriendId =
                            new ResourceLocation("chaospersists", "boyfriend");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, boyfriendId)
                            || "Boyfriend".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        return Mob.checkMobSpawnRules(type, level, spawnType, pos, random);
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
                    ResourceLocation boyfriendId =
                            new ResourceLocation("chaospersists", "boyfriend");
                    ResourceLocation norm = SpawnerFixHelper.normalizeSpawnerEntityId(id);
                    if (SpawnerFixHelper.entityIdsMatchForSpawner(norm, boyfriendId)
                            || "Boyfriend".equals(id.getPath())) {
                        return true;
                    }
                }
            }
        }
        return super.checkSpawnRules(level, spawnReason);
    }

    /** Assign a permanent random skin once for new spawns (OreSpawn entityInit parity). */
    private void ensureSkinInitialized() {
        if (this.skinInitialized || this.level().isClientSide) {
            return;
        }
        this.setTameSkin(this.getRandom().nextInt(28));
        this.setWetTameSkin(this.getRandom().nextInt(18));
        this.voice = this.getRandom().nextInt(10);
        this.entityData.set(VOICE, this.voice);
        this.skinInitialized = true;
    }

    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor level,
            DifficultyInstance difficulty,
            MobSpawnType reason,
            SpawnGroupData spawnData,
            CompoundTag dataTag) {
        this.ensureSkinInitialized();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double) this.mygetMaxHealth());
        SpawnGroupData data = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
        this.setAge(0);
        return data;
    }
}
