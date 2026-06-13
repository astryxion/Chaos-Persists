/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Elevator
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.ItemChaosArmor
 *  com.astryxion.chaospersists.MyEntityAIDance
 *  com.astryxion.chaospersists.MyEntityAIFollowOwner
 *  com.astryxion.chaospersists.MyEntityAIJealousy
 *  com.astryxion.chaospersists.MyEntityAINearestAttackableTarget
 *  com.astryxion.chaospersists.MyEntityAIWander
 *  com.astryxion.chaospersists.MyValentineTarget
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.Shoes
 *  com.astryxion.chaospersists.UltimateArrow
 *  net.minecraft.block.Block
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.FlowerBlock
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.AgeableEntity
 *  net.minecraft.entity.CreatureEntity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.ai.attributes.Attributes
 *  net.minecraft.entity.ai.EntityAIArrowAttack
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIPanic
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITempt
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.attributes.BaseAttributeMap
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.monster.Creeper
 *  net.minecraft.entity.monster.Monster
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.Inventory
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.entity.projectile.ArrowEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ArmorItem
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompoundNBT
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.EffectInstance
 *  net.minecraft.tileentity.MobSpawnerBaseLogic
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.MobSpawnerTileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.util.MyEntityAIDance;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIJealousy;
import com.astryxion.chaospersists.util.MyEntityAINearestAttackableTarget;
import com.astryxion.chaospersists.util.MyEntityAIWander;
import com.astryxion.chaospersists.util.MyValentineTarget;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.item.Shoes;
import com.astryxion.chaospersists.item.UltimateArrow;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.FlowerBlock;
import com.google.common.base.Predicate;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;

import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResultType;
import net.minecraft.potion.Effects;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;

public class Girlfriend
extends TameableEntity
implements IRangedAttackMob {
    private static final DataParameter<Integer> WHICH_GIRL = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final DataParameter<Integer> VOICE = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final DataParameter<Integer> WHICH_WET_GIRL = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final DataParameter<Integer> VOICE_ENABLE = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final DataParameter<Integer> IS_PRINCESS = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final DataParameter<Integer> FEELING_BETTER = EntityDataManager.defineId(Girlfriend.class, DataSerializers.INT);
    private static final EquipmentSlotType[] EQUIPMENT_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.MAINHAND, EquipmentSlotType.FEET, EquipmentSlotType.LEGS, EquipmentSlotType.CHEST, EquipmentSlotType.HEAD};
    public int which_girl = 0;
    public int which_wet_girl = 0;
    public int wet_count = 0;
    private int auto_heal = 200;
    private int force_sync = 50;
    private int fight_sound_ticker = 0;
    private int taunt_sound_ticker = 0;
    private int attackTime = 0;
    private int had_target = 0;
    private int voice = 0;
    private int is_princess = 0;
    public MyEntityAIDance Dance = null;
    private float moveSpeed = 0.3f;
    private int voice_enable = 1;
    public int passenger = 0;
    public int feelingBetter = 0;
    private static final ResourceLocation DryTexture0 = new ResourceLocation("chaospersists", "textures/entity/girlfriend0.png");
    private static final ResourceLocation DryTexture1 = new ResourceLocation("chaospersists", "textures/entity/girlfriend1.png");
    private static final ResourceLocation DryTexture2 = new ResourceLocation("chaospersists", "textures/entity/girlfriend2.png");
    private static final ResourceLocation DryTexture3 = new ResourceLocation("chaospersists", "textures/entity/girlfriend3.png");
    private static final ResourceLocation DryTexture4 = new ResourceLocation("chaospersists", "textures/entity/girlfriend4.png");
    private static final ResourceLocation DryTexture5 = new ResourceLocation("chaospersists", "textures/entity/girlfriend5.png");
    private static final ResourceLocation DryTexture6 = new ResourceLocation("chaospersists", "textures/entity/girlfriend6.png");
    private static final ResourceLocation DryTexture7 = new ResourceLocation("chaospersists", "textures/entity/girlfriend7.png");
    private static final ResourceLocation DryTexture8 = new ResourceLocation("chaospersists", "textures/entity/girlfriend8.png");
    private static final ResourceLocation DryTexture9 = new ResourceLocation("chaospersists", "textures/entity/girlfriend9.png");
    private static final ResourceLocation DryTexture10 = new ResourceLocation("chaospersists", "textures/entity/girlfriend10.png");
    private static final ResourceLocation DryTexture11 = new ResourceLocation("chaospersists", "textures/entity/girlfriend11.png");
    private static final ResourceLocation DryTexture12 = new ResourceLocation("chaospersists", "textures/entity/girlfriend12.png");
    private static final ResourceLocation DryTexture13 = new ResourceLocation("chaospersists", "textures/entity/girlfriend13.png");
    private static final ResourceLocation DryTexture14 = new ResourceLocation("chaospersists", "textures/entity/girlfriend14.png");
    private static final ResourceLocation DryTexture15 = new ResourceLocation("chaospersists", "textures/entity/girlfriend15.png");
    private static final ResourceLocation DryTexture16 = new ResourceLocation("chaospersists", "textures/entity/girlfriend16.png");
    private static final ResourceLocation DryTexture17 = new ResourceLocation("chaospersists", "textures/entity/girlfriend17.png");
    private static final ResourceLocation DryTexture18 = new ResourceLocation("chaospersists", "textures/entity/girlfriend18.png");
    private static final ResourceLocation DryTexture19 = new ResourceLocation("chaospersists", "textures/entity/girlfriend19.png");
    private static final ResourceLocation DryTexture20 = new ResourceLocation("chaospersists", "textures/entity/girlfriend20.png");
    private static final ResourceLocation DryTexture21 = new ResourceLocation("chaospersists", "textures/entity/girlfriend21.png");
    private static final ResourceLocation DryTexture22 = new ResourceLocation("chaospersists", "textures/entity/girlfriend22.png");
    private static final ResourceLocation DryTexture23 = new ResourceLocation("chaospersists", "textures/entity/girlfriend23.png");
    private static final ResourceLocation DryTexture24 = new ResourceLocation("chaospersists", "textures/entity/girlfriend24.png");
    private static final ResourceLocation DryTexture25 = new ResourceLocation("chaospersists", "textures/entity/girlfriend25.png");
    private static final ResourceLocation DryTexture26 = new ResourceLocation("chaospersists", "textures/entity/girlfriend26.png");
    private static final ResourceLocation DryTexture27 = new ResourceLocation("chaospersists", "textures/entity/girlfriend27.png");
    private static final ResourceLocation DryTexture28 = new ResourceLocation("chaospersists", "textures/entity/girlfriend28.png");
    private static final ResourceLocation DryTexture29 = new ResourceLocation("chaospersists", "textures/entity/girlfriend29.png");
    private static final ResourceLocation DryTexture30 = new ResourceLocation("chaospersists", "textures/entity/girlfriend30.png");
    private static final ResourceLocation DryTexture31 = new ResourceLocation("chaospersists", "textures/entity/girlfriend31.png");
    private static final ResourceLocation DryTexture32 = new ResourceLocation("chaospersists", "textures/entity/girlfriend32.png");
    private static final ResourceLocation DryTexture33 = new ResourceLocation("chaospersists", "textures/entity/girlfriend33.png");
    private static final ResourceLocation DryTexture34 = new ResourceLocation("chaospersists", "textures/entity/girlfriend34.png");
    private static final ResourceLocation DryTexture35 = new ResourceLocation("chaospersists", "textures/entity/girlfriend35.png");
    private static final ResourceLocation DryTexture36 = new ResourceLocation("chaospersists", "textures/entity/girlfriend36.png");
    private static final ResourceLocation DryTexture37 = new ResourceLocation("chaospersists", "textures/entity/girlfriend37.png");
    private static final ResourceLocation DryTexture38 = new ResourceLocation("chaospersists", "textures/entity/girlfriend38.png");
    private static final ResourceLocation DryTexture39 = new ResourceLocation("chaospersists", "textures/entity/girlfriend39.png");
    private static final ResourceLocation DryTexture40 = new ResourceLocation("chaospersists", "textures/entity/girlfriend40.png");
    private static final ResourceLocation ValentineTexture = new ResourceLocation("chaospersists", "textures/entity/girlfriendv.png");
    private static final ResourceLocation WetTexture0 = new ResourceLocation("chaospersists", "textures/entity/bikini0.png");
    private static final ResourceLocation WetTexture1 = new ResourceLocation("chaospersists", "textures/entity/bikini1.png");
    private static final ResourceLocation WetTexture2 = new ResourceLocation("chaospersists", "textures/entity/bikini2.png");
    private static final ResourceLocation WetTexture3 = new ResourceLocation("chaospersists", "textures/entity/bikini3.png");
    private static final ResourceLocation WetTexture4 = new ResourceLocation("chaospersists", "textures/entity/bikini4.png");
    private static final ResourceLocation WetTexture5 = new ResourceLocation("chaospersists", "textures/entity/bikini5.png");
    private static final ResourceLocation WetTexture6 = new ResourceLocation("chaospersists", "textures/entity/bikini6.png");
    private static final ResourceLocation WetTexture7 = new ResourceLocation("chaospersists", "textures/entity/bikini7.png");
    private static final ResourceLocation WetTexture8 = new ResourceLocation("chaospersists", "textures/entity/bikini8.png");
    private static final ResourceLocation WetTexture9 = new ResourceLocation("chaospersists", "textures/entity/bikini9.png");
    private static final ResourceLocation WetTexture10 = new ResourceLocation("chaospersists", "textures/entity/bikini10.png");
    private static final ResourceLocation WetTexture11 = new ResourceLocation("chaospersists", "textures/entity/bikini11.png");
    private static final ResourceLocation WetTexture12 = new ResourceLocation("chaospersists", "textures/entity/bikini12.png");
    private static final ResourceLocation WetTexture13 = new ResourceLocation("chaospersists", "textures/entity/bikini13.png");
    private static final ResourceLocation WetTexture14 = new ResourceLocation("chaospersists", "textures/entity/bikini14.png");
    private static final ResourceLocation WetTexture15 = new ResourceLocation("chaospersists", "textures/entity/bikini15.png");
    private static final ResourceLocation WetTexture16 = new ResourceLocation("chaospersists", "textures/entity/bikini16.png");
    private static final ResourceLocation WetTexture17 = new ResourceLocation("chaospersists", "textures/entity/bikini17.png");
    private static final ResourceLocation PrincessTexture1 = new ResourceLocation("chaospersists", "textures/entity/frogprincess.png");
    private static final ResourceLocation PrincessTexture2 = new ResourceLocation("chaospersists", "textures/entity/frogprincess2.png");

    public Girlfriend(EntityType<? extends Girlfriend> type, World par1World) {
        super(type, par1World);
        this.which_girl = this.random.nextInt(41);
        this.which_wet_girl = this.random.nextInt(18);
        this.voice = this.random.nextInt(10);
        if (ChaosPersists.valentines_day != 0) {
        }
        this.setOrderedToSit(false);
        this.goalSelector.addGoal(1, new MyEntityAIFollowOwner((TameableEntity)this, 1.4f, 12.0f, 1.5f));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, net.minecraft.item.crafting.Ingredient.of(Items.POPPY), false));
        this.Dance = new MyEntityAIDance(this);
        this.goalSelector.addGoal(3, this.Dance);
        this.goalSelector.addGoal(4, new RangedAttackGoal((IRangedAttackMob)this, 1.25, 20, 10.0f));
        this.goalSelector.addGoal(5, new SwimGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.addGoal(8, new MyEntityAIWander(this, 0.75f));
        this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(10, new OpenDoorGoal((MobEntity)this, true));
        this.goalSelector.addGoal(11, new RestrictSunGoal(this));
        this.targetSelector.addGoal(1, new MyValentineTarget((MobEntity)this, PlayerEntity.class, 16.0f, 0, true, true));
        this.targetSelector.addGoal(2, new MyValentineTarget((MobEntity)this, Boyfriend.class, 16.0f, 0, true, true));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(2, new MyEntityAINearestAttackableTarget((MobEntity)this, CreeperEntity.class, 20.0f, 0, true, true, (Predicate<LivingEntity>)null));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(3, new MyEntityAINearestAttackableTarget((MobEntity)this, LivingEntity.class, 15.0f, 0, true, true, (Predicate<LivingEntity>)null));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(4, new MyEntityAIJealousy((TameableEntity)this, Girlfriend.class, 6.0f, 5, true));
        }
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(5, new MyEntityAIJealousy((TameableEntity)this, Girlfriend.class, 3.0f, 15, true));
        }
        this.xpReward = 0;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.which_girl = this.random.nextInt(41);
        this.entityData.define(WHICH_GIRL, this.which_girl);
        this.wet_count = 0;
        this.which_wet_girl = this.random.nextInt(18);
        this.entityData.define(WHICH_WET_GIRL, this.which_wet_girl);
        this.voice = this.random.nextInt(10);
        this.entityData.define(VOICE, this.voice);
        this.entityData.define(VOICE_ENABLE, this.voice_enable);
        this.entityData.define(IS_PRINCESS, this.is_princess);
        this.entityData.define(FEELING_BETTER, this.feelingBetter);
        this.auto_heal = 200;
        this.force_sync = 50;
        this.fight_sound_ticker = 0;
        this.taunt_sound_ticker = 0;
        this.had_target = 0;
        this.setOrderedToSit(false);
    }

    public static AttributeModifierMap createAttributes() {
        return TameableEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .build();
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source.isFire() || super.isInvulnerableTo(source);
    }

    public int getArmorValue() {
        int i = 0;
        for (ItemStack itemstack : this.getArmorSlots()) {
            if (itemstack == null || !(itemstack.getItem() instanceof ArmorItem)) continue;
            int l = ((ArmorItem)itemstack.getItem()).getDefense();
            i += l;
        }
        if (i < 8) {
            i = 8;
        }
        if (i > 23) {
            i = 23;
        }
        return i;
    }

    @Override
    public void tick() {
        LivingEntity e;
        this.updateSwingTime();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)this.moveSpeed);
        super.tick();
        this.passenger = 0;
        if (this.isTame() && !this.isOrderedToSit() && (e = this.getOwner()) != null && e instanceof PlayerEntity) {
            PlayerEntity p = (PlayerEntity)e;
            Entity r = e.getVehicle();
            if (r != null && r instanceof Elevator) {
                float f = -0.45f;
                this.setPos(r.getX() - (double)f * Math.sin(Math.toRadians(r.yRot)), r.getY(), r.getZ() + (double)f * Math.cos(Math.toRadians(r.yRot)));
                this.yRot = r.yRot;
                this.xRot = r.xRot;
                this.animationSpeedOld = 0.0f;
                this.animationPosition = 0.0f;
                this.fallDistance = 0.0f;
                this.passenger = 1;
            }
        }
        if (this.isInWater() || this.isInLava()) {
            this.wet_count = 500;
        } else if (this.wet_count > 0) {
            --this.wet_count;
        }
        --this.auto_heal;
        if (this.auto_heal <= 0) {
            if (this.mygetMaxHealth() > this.getGirlfriendHealth()) {
                this.heal(1.0f);
            }
            this.auto_heal = 100;
        }
        --this.force_sync;
        if (this.force_sync <= 0) {
            this.force_sync = 20;
            if (!this.level.isClientSide) {
                this.entityData.set(VOICE, this.voice);
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.entityData.set(IS_PRINCESS, this.is_princess);
                this.entityData.set(FEELING_BETTER, this.feelingBetter);
                this.setOrderedToSit(this.isOrderedToSit());
            } else {
                this.voice = this.getVoice();
                this.voice_enable = this.entityData.get(VOICE_ENABLE).intValue();
                int nowfeeling = this.entityData.get(FEELING_BETTER).intValue();
                if (nowfeeling != this.feelingBetter && nowfeeling != 0) {
                    this.feelingBetter = nowfeeling;
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.addAdditionalSaveData(par1CompoundNBT);
        par1CompoundNBT.putInt("GirlType", this.getTameSkin());
        par1CompoundNBT.putInt("WetGirlType", this.getWetTameSkin());
        par1CompoundNBT.putInt("GirlVoice", this.entityData.get(VOICE).intValue());
        par1CompoundNBT.putInt("GirlVoiceEnable", this.entityData.get(VOICE_ENABLE).intValue());
        par1CompoundNBT.putInt("IsPrincess", this.entityData.get(IS_PRINCESS).intValue());
        par1CompoundNBT.putInt("feelingBetter", this.entityData.get(FEELING_BETTER).intValue());
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT par1CompoundNBT) {
        super.readAdditionalSaveData(par1CompoundNBT);
        this.which_girl = par1CompoundNBT.getInt("GirlType");
        this.setTameSkin(this.which_girl);
        this.which_wet_girl = par1CompoundNBT.getInt("WetGirlType");
        this.setWetTameSkin(this.which_wet_girl);
        this.voice = par1CompoundNBT.getInt("GirlVoice");
        this.entityData.set(VOICE, this.voice);
        this.voice_enable = par1CompoundNBT.getInt("GirlVoiceEnable");
        this.entityData.set(VOICE_ENABLE, this.voice_enable);
        this.is_princess = par1CompoundNBT.getInt("IsPrincess");
        this.entityData.set(IS_PRINCESS, this.is_princess);
        this.feelingBetter = par1CompoundNBT.getInt("feelingBetter");
        this.entityData.set(FEELING_BETTER, this.feelingBetter);
        if (ChaosPersists.valentines_day != 0 && this.feelingBetter != 0) {
        }
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
        ItemStack stack = this.getMainHandItem();
        LivingEntity victim = this.getTarget();
        if (ChaosPersists.PlayNicely != 0) {
            victim = null;
        }
        if (this.level.random.nextInt(100) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setTarget(null);
        }
        if (stack != null && !this.isOrderedToSit()) {
            if (victim != null) {
                if (victim instanceof LivingEntity && this.getMainHandItem() != null && !this.getMainHandItem().isEmpty()) {
                    if (this.distanceTo(victim) < 4.0f || stack.getItem() == ChaosPersists.MyBertha && this.distanceTo(victim) < 10.0f) {
                        --this.attackTime;
                        if (this.attackTime <= 0) {
                            this.attackTime = 25;
                            this.swing(net.minecraft.util.Hand.MAIN_HAND);
                            this.attackTargetEntityWithCurrentItem((Entity)victim);
                            --this.fight_sound_ticker;
                            if (this.fight_sound_ticker <= 0) {
                                if (!this.level.isClientSide && this.voice_enable != 0) {
                                    this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("chaospersists", "o_fight")), net.minecraft.util.SoundCategory.NEUTRAL, 0.5f, this.getVoicePitch());
                                }
                                this.fight_sound_ticker = 3;
                            }
                            this.had_target = 1;
                        }
                    } else if (this.distanceTo(victim) < 7.0f && stack.getItem() != ChaosPersists.MyUltimateBow) {
                        --this.taunt_sound_ticker;
                        if (this.taunt_sound_ticker <= 0) {
                            if (!this.level.isClientSide && this.voice_enable != 0) {
                                this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("chaospersists", "o_taunt")), net.minecraft.util.SoundCategory.NEUTRAL, 0.5f, this.getVoicePitch());
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
                    if (!this.level.isClientSide && this.voice_enable != 0) {
                        this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("chaospersists", "o_woohoo")), net.minecraft.util.SoundCategory.NEUTRAL, 0.4f, this.getVoicePitch());
                    }
                }
            }
        }
    }

    public void setPrincess(int par1) {
        this.is_princess = par1;
    }

    public void setSwingingArms(boolean swingingArms) {
    }

    public ResourceLocation getTexture() {
        if (ChaosPersists.valentines_day != 0 && this.feelingBetter == 0) {
            return ValentineTexture;
        }
        if (this.wet_count <= 0) {
            int txture = this.getTameSkin();
            if (this.is_princess == 1) {
                return PrincessTexture1;
            }
            if (this.is_princess == 2) {
                return PrincessTexture2;
            }
            if (txture == 0) {
                return DryTexture0;
            }
            if (txture == 1) {
                return DryTexture1;
            }
            if (txture == 2) {
                return DryTexture2;
            }
            if (txture == 3) {
                return DryTexture3;
            }
            if (txture == 4) {
                return DryTexture4;
            }
            if (txture == 5) {
                return DryTexture5;
            }
            if (txture == 6) {
                return DryTexture6;
            }
            if (txture == 7) {
                return DryTexture7;
            }
            if (txture == 8) {
                return DryTexture8;
            }
            if (txture == 9) {
                return DryTexture9;
            }
            if (txture == 10) {
                return DryTexture10;
            }
            if (txture == 11) {
                return DryTexture11;
            }
            if (txture == 12) {
                return DryTexture12;
            }
            if (txture == 13) {
                return DryTexture13;
            }
            if (txture == 14) {
                return DryTexture14;
            }
            if (txture == 15) {
                return DryTexture15;
            }
            if (txture == 16) {
                return DryTexture16;
            }
            if (txture == 17) {
                return DryTexture17;
            }
            if (txture == 18) {
                return DryTexture18;
            }
            if (txture == 19) {
                return DryTexture19;
            }
            if (txture == 20) {
                return DryTexture20;
            }
            if (txture == 21) {
                return DryTexture21;
            }
            if (txture == 22) {
                return DryTexture22;
            }
            if (txture == 23) {
                return DryTexture23;
            }
            if (txture == 24) {
                return DryTexture24;
            }
            if (txture == 25) {
                return DryTexture25;
            }
            if (txture == 26) {
                return DryTexture26;
            }
            if (txture == 27) {
                return DryTexture27;
            }
            if (txture == 28) {
                return DryTexture28;
            }
            if (txture == 29) {
                return DryTexture29;
            }
            if (txture == 30) {
                return DryTexture30;
            }
            if (txture == 31) {
                return DryTexture31;
            }
            if (txture == 32) {
                return DryTexture32;
            }
            if (txture == 33) {
                return DryTexture33;
            }
            if (txture == 34) {
                return DryTexture34;
            }
            if (txture == 35) {
                return DryTexture35;
            }
            if (txture == 36) {
                return DryTexture36;
            }
            if (txture == 37) {
                return DryTexture37;
            }
            if (txture == 38) {
                return DryTexture38;
            }
            if (txture == 39) {
                return DryTexture39;
            }
            if (txture == 40) {
                return DryTexture40;
            }
        } else {
            int temp = this.getWetTameSkin();
            if (temp == 0) {
                return WetTexture0;
            }
            if (temp == 1) {
                return WetTexture1;
            }
            if (temp == 2) {
                return WetTexture2;
            }
            if (temp == 3) {
                return WetTexture3;
            }
            if (temp == 4) {
                return WetTexture4;
            }
            if (temp == 5) {
                return WetTexture5;
            }
            if (temp == 6) {
                return WetTexture6;
            }
            if (temp == 7) {
                return WetTexture7;
            }
            if (temp == 8) {
                return WetTexture8;
            }
            if (temp == 9) {
                return WetTexture9;
            }
            if (temp == 10) {
                return WetTexture10;
            }
            if (temp == 11) {
                return WetTexture11;
            }
            if (temp == 12) {
                return WetTexture12;
            }
            if (temp == 13) {
                return WetTexture13;
            }
            if (temp == 14) {
                return WetTexture14;
            }
            if (temp == 15) {
                return WetTexture15;
            }
            if (temp == 16) {
                return WetTexture16;
            }
            if (temp == 17) {
                return WetTexture17;
            }
        }
        return null;
    }

    public int getTameSkin() {
        return this.entityData.get(WHICH_GIRL).intValue();
    }

    public int getVoice() {
        return this.entityData.get(VOICE).intValue();
    }

    public void setTameSkin(int par1) {
        this.entityData.set(WHICH_GIRL, par1);
        this.which_girl = par1;
    }

    public int getWetTameSkin() {
        return this.entityData.get(WHICH_WET_GIRL).intValue();
    }

    public void setWetTameSkin(int par1) {
        this.entityData.set(WHICH_WET_GIRL, par1);
        this.which_wet_girl = par1;
    }

    public boolean isAIEnabled() {
        return true;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    protected void fall(float par1) {
        float i = MathHelper.ceil(par1 - 3.0f);
        if (i > 0.0f) {
            if (i > 3.0f) {
                this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("entity.generic.big_fall")), 1.0f, 1.0f);
                i = 3.0f;
            } else {
                this.playSound(net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("entity.generic.small_fall")), 1.0f, 1.0f);
            }
            this.hurt(DamageSource.FALL, i);
        }
    }

    public int mygetMaxHealth() {
        if (ChaosPersists.valentines_day != 0 && this.feelingBetter == 0) {
            return 800;
        }
        return 80;
    }

    public int getGirlfriendHealth() {
        return (int)this.getHealth();
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (hand != Hand.MAIN_HAND) {
            return super.mobInteract(player, hand);
        }
        if (this.interact(player)) {
            return ActionResultType.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        ItemStack var2 = par1PlayerEntityEntity.getItemInHand(Hand.MAIN_HAND);
        if (var2.isEmpty()) {
            var2 = null;
        }
        if (var2 != null && (var2.getItem() == Items.POPPY || var2.getItem() == Item.byBlock((Block)ChaosPersists.CrystalFlowerRedBlock)) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (!this.isTame()) {
                if (!this.level.isClientSide) {
                    if (this.random.nextInt(3) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
                        this.level.broadcastEntityEvent(this, (byte)7);
                        this.level.broadcastEntityEvent((Entity)this, (byte)7);
                        this.heal((float)this.mygetMaxHealth() - this.getHealth());
                    } else {
                        this.level.broadcastEntityEvent(this, (byte)6);
                        this.level.broadcastEntityEvent((Entity)this, (byte)6);
                    }
                }
            } else if (this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
                if (this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                }
                if ((float)this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float)this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.DEAD_BUSH) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.setTame(false);
                this.setOwnerUUID((UUID)null);
                this.level.broadcastEntityEvent(this, (byte)6);
                this.level.broadcastEntityEvent((Entity)this, (byte)6);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && var2.getItem() == ChaosPersists.MyRuby && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.voice_enable = 0;
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent((Entity)this, (byte)7);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && var2.getItem() == ChaosPersists.MyAmethyst && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                this.voice_enable = 1;
                this.entityData.set(VOICE_ENABLE, this.voice_enable);
                this.level.broadcastEntityEvent(this, (byte)7);
                this.level.broadcastEntityEvent((Entity)this, (byte)7);
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && (var2.getItem() == Item.byBlock((Block)Blocks.DANDELION) || var2.getItem() == Item.byBlock((Block)ChaosPersists.CrystalFlowerYellowBlock)) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            if (!this.level.isClientSide) {
                if (this.wet_count > 0 || this.isInWater() || this.isInLava()) {
                    ++this.which_wet_girl;
                    if (this.which_wet_girl > 17) {
                        this.which_wet_girl = 0;
                    }
                    this.setWetTameSkin(this.which_wet_girl);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                    if (this.isInWater() || this.isInLava()) {
                        this.wet_count = 500;
                    }
                } else {
                    ++this.which_girl;
                    if (this.which_girl > 40) {
                        this.which_girl = 0;
                    }
                    this.setTameSkin(this.which_girl);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                }
            }
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            if (var2.isEdible()) {
                if (!this.level.isClientSide) {
                    if ((float)this.mygetMaxHealth() > this.getHealth()) {
                        this.heal((float)(var2.getItem().getFoodProperties().getNutrition() * 5));
                    }
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                }
                if (!par1PlayerEntityEntity.isCreative()) {
                    var2.shrink(1);
                    if (var2.getCount() <= 0) {
                        par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                    }
                }
            } else if (var2.getItem() instanceof ArmorItem) {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                }
                ArmorItem armorItem = (ArmorItem) var2.getItem();
                EquipmentSlotType slot = armorItem.getSlot();
                ItemStack oldArmor = this.getItemBySlot(slot).copy();
                if (par1PlayerEntityEntity.isCreative()) {
                    ItemStack equipCopy = var2.copy();
                    equipCopy.setCount(1);
                    this.setItemSlot(slot, equipCopy);
                } else {
                    ItemStack equipOne = var2.copy();
                    equipOne.setCount(1);
                    this.setItemSlot(slot, equipOne);
                    var2.shrink(1);
                    if (var2.isEmpty()) {
                        par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                    }
                }
                if (!oldArmor.isEmpty()) {
                    if (par1PlayerEntityEntity.inventory.getSelected().isEmpty()) {
                        par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, oldArmor);
                    } else if (!par1PlayerEntityEntity.inventory.add(oldArmor)) {
                        par1PlayerEntityEntity.spawnAtLocation(oldArmor);
                    }
                }
            } else {
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent(this, (byte)7);
                    this.level.broadcastEntityEvent((Entity)this, (byte)7);
                }
                ItemStack var3 = this.getMainHandItem();
                this.setItemSlot(EquipmentSlotType.MAINHAND, var2);
                if (var2.getItem() == Items.DIAMOND) {
                    this.setOrderedToSit(true);
                } else {
                    this.setOrderedToSit(false);
                }
                if (var3 != null && !var3.isEmpty()) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, var3);
                } else {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Item.byBlock((Block)Blocks.DIAMOND_BLOCK) && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0) {
            this.setOrderedToSit(false);
            this.setTame(true);
            this.setOwnerUUID(par1PlayerEntityEntity.getUUID());
            this.level.broadcastEntityEvent(this, (byte)7);
            this.level.broadcastEntityEvent((Entity)this, (byte)7);
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 != null && var2.getItem() == Items.NAME_TAG && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            this.setCustomName(var2.getDisplayName());
            if (!par1PlayerEntityEntity.isCreative()) {
                var2.shrink(1);
                if (var2.getCount() <= 0) {
                    par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
                }
            }
            return true;
        }
        if (this.isTame() && var2 == null && par1PlayerEntityEntity.distanceToSqr((Entity)this) < 16.0 && this.isOwnedBy((LivingEntity)par1PlayerEntityEntity)) {
            EquipmentSlotType slotToStrip = null;
            ItemStack toGive = ItemStack.EMPTY;
            for (EquipmentSlotType s : EQUIPMENT_SLOTS) {
                ItemStack equipped = this.getItemBySlot(s);
                if (!equipped.isEmpty()) {
                    toGive = equipped;
                    slotToStrip = s;
                    break;
                }
            }
            if (slotToStrip != null) {
                par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, toGive);
                this.setItemSlot(slotToStrip, ItemStack.EMPTY);
                this.setOrderedToSit(false);
                if (!this.level.isClientSide) {
                    this.level.broadcastEntityEvent((Entity)this, (byte)6);
                }
            } else if (!this.level.isClientSide) {
                this.setOrderedToSit(false);
                String healthMessage = String.format("I have %d health. Thank you for asking! xoxo", this.getGirlfriendHealth());
                par1PlayerEntityEntity.displayClientMessage(new StringTextComponent(healthMessage), false);
            }
            return true;
        }
        return false;
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return par1ItemStack != null && par1ItemStack.getItem() == Items.POPPY;
    }

    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        return false;
    }

    protected net.minecraft.util.SoundEvent getAmbientSound() {
        if (this.isOrderedToSit() || this.voice_enable == 0) {
            return null;
        }
        if (this.Dance.is_dancing != 0) {
            return null;
        }
        if (this.random.nextInt(11) == 1) {
            LivingEntity victim = this.getTarget();
            if (victim != null) {
                return null;
            }
            if (this.isInWater() || this.isInLava()) {
                return com.astryxion.chaospersists.core.ChaosSounds.O_WATER;
            }
            if (this.random.nextInt(4) != 0) {
                if (this.getY() < 60.0) {
                    return null;
                }
                if (this.level.isThundering()) {
                    return com.astryxion.chaospersists.core.ChaosSounds.O_THUNDER;
                }
                if (this.level.isRaining()) {
                    return com.astryxion.chaospersists.core.ChaosSounds.O_RAIN;
                }
                if (!this.level.isDay() && this.level.canSeeSky(new net.minecraft.util.math.BlockPos(this.getX(), this.getY(), this.getZ()))) {
                    if (this.level.random.nextInt(3) == 0) {
                        return com.astryxion.chaospersists.core.ChaosSounds.O_DARK;
                    }
                    return null;
                }
            }
            if (this.isTame()) {
                if ((float)this.mygetMaxHealth() > this.getHealth() || ChaosPersists.valentines_day != 0 && this.feelingBetter == 0) {
                    return com.astryxion.chaospersists.core.ChaosSounds.O_HURT;
                }
                return com.astryxion.chaospersists.core.ChaosSounds.O_HAPPY;
            }
            return null;
        }
        return null;
    }

    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        if (this.voice_enable == 0) {
            return null;
        }
        return com.astryxion.chaospersists.core.ChaosSounds.O_OW;
    }

    protected SoundEvent getDeathSound() {
        return this.isTame() ? com.astryxion.chaospersists.core.ChaosSounds.O_DEATH_GIRLFRIEND : com.astryxion.chaospersists.core.ChaosSounds.O_DEATH_SINGLE;
    }

    protected float getSoundVolume() {
        return 0.3f;
    }

    protected Item getDropItem() {
        return Items.POPPY;
    }

    private void dropItemRand(Item index, int par1) {
        ItemEntity var3 = new ItemEntity(this.level, this.getX() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), this.getY() + 1.0, this.getZ() + (double)ChaosPersists.ChaosRand.nextInt(4) - (double)ChaosPersists.ChaosRand.nextInt(4), new ItemStack(index, par1));
        this.level.addFreshEntity(var3);
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        int var3 = 0;
        if (this.isTame()) {
            var3 = this.random.nextInt(5) + 2;
            this.spawnAtLocation(Items.POPPY, var3);
        }
        Item v6 = ChaosPersists.MyItemShoes;
        Item v7 = ChaosPersists.MyItemShoes_1;
        Item v8 = ChaosPersists.MyItemShoes_2;
        Item v9 = ChaosPersists.MyItemShoes_3;
        var3 = this.random.nextInt(16) + 4;
        this.spawnAtLocation(v6, var3);
        var3 = this.random.nextInt(16) + 4;
        this.spawnAtLocation(v7, var3);
        var3 = this.random.nextInt(16) + 4;
        this.spawnAtLocation(v8, var3);
        var3 = this.random.nextInt(16) + 4;
        this.spawnAtLocation(v9, var3);
        if (this.isTame()) {
            ItemStack var5 = this.getMainHandItem();
            if (var5 != null && var5.getCount() > 0) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if ((var5 = this.getItemBySlot(EquipmentSlotType.FEET)) != null && var5.getCount() > 0) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if ((var5 = this.getItemBySlot(EquipmentSlotType.LEGS)) != null && var5.getCount() > 0) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if ((var5 = this.getItemBySlot(EquipmentSlotType.CHEST)) != null && var5.getCount() > 0) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
            if ((var5 = this.getItemBySlot(EquipmentSlotType.HEAD)) != null && var5.getCount() > 0) {
                this.spawnAtLocation(var5.getItem(), var5.getCount());
            }
        }
    }

    public void performRangedAttack(LivingEntity par1Mob) {
        ItemStack it = null;
        if (this.swinging) {
            return;
        }
        it = this.getMainHandItem();
        if (it != null && it.getItem() == ChaosPersists.MyUltimateBow) {
            int var10;
            UltimateArrow var8 = new UltimateArrow(this.level, (MobEntity)this, par1Mob, 2.0f, 10.0f);
            if (this.level.random.nextInt(4) == 1) {
                var8.setCritArrow(true);
            }
            if ((var10 = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.PUNCH_ARROWS, it)) > 0) {
                var8.setKnockbackStrength(var10);
            }
            if (EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.FIRE_ASPECT, it) > 0) {
                var8.setRemainingFireTicks(100);
            }
            it.hurtAndBreak(1, this, e -> {});
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), net.minecraft.util.registry.Registry.SOUND_EVENT.get(new ResourceLocation("entity.skeleton.shoot")), net.minecraft.util.SoundCategory.NEUTRAL, 1.0f, 1.0f / (this.level.random.nextFloat() * 0.4f + 1.2f) + 0.5f);
            var8.pickup = ArrowEntity.PickupStatus.CREATIVE_ONLY;
            this.level.addFreshEntity(var8);
        } else {
            Shoes.shootTowardTarget(this, par1Mob, 2 + this.random.nextInt(4));
        }
        this.swing(Hand.MAIN_HAND);
    }

    public ItemStack getCurrentEquippedItem() {
        return this.getItemBySlot(EquipmentSlotType.MAINHAND);
    }

    public void attackTargetEntityWithCurrentItem(Entity par1Entity) {
        ItemStack stack = this.getMainHandItem();
        if (stack != null) {
            float var2 = 0.0f;
            if (this.hasEffect(Effects.DAMAGE_BOOST)) {
                var2 += (float)(3 << this.getEffect(Effects.DAMAGE_BOOST).getAmplifier());
            }
            if (this.hasEffect(Effects.WEAKNESS)) {
                var2 -= (float)(2 << this.getEffect(Effects.WEAKNESS).getAmplifier());
            }
            int var3 = 0;
            float var4 = (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
            if (par1Entity instanceof MobEntity) {
                var4 += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), ((LivingEntity)par1Entity).getMobType());
                var3 += EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.KNOCKBACK, this.getMainHandItem());
            }
            if (this.isSprinting()) {
                ++var3;
            }
            if (var2 > 0.0f || var4 > 0.0f) {
                int var8;
                boolean var6;
                boolean var5;
                boolean bl = var5 = this.fallDistance > 0.0f && !this.onGround && !this.onClimbable() && !this.isInWater() && !this.isInLava() && !this.hasEffect(Effects.BLINDNESS) && this.getVehicle() == null && par1Entity instanceof MobEntity;
                if (var5) {
                    var2 += (float)this.random.nextInt((int)var2 / 2 + 2);
                }
                if ((var6 = par1Entity.hurt(DamageSource.mobAttack((LivingEntity)this), var2 += var4)) && var3 > 0) {
                    par1Entity.push((double)((- MathHelper.sin((float)(this.yRot * 3.1415927f / 180.0f))) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.cos((float)(this.yRot * 3.1415927f / 180.0f)) * (float)var3 * 0.5f));
                    this.setDeltaMovement(this.getDeltaMovement().multiply(0.6, 1.0, 0.6));
                    
                    this.setSprinting(false);
                }
                ItemStack var7 = this.getMainHandItem();
                if (par1Entity instanceof MobEntity && (var8 = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.enchantment.Enchantments.FIRE_ASPECT, var7)) > 0 && var6) {
                    par1Entity.setRemainingFireTicks(var8 * 4);
                }
            }
        }
    }

    protected float getVoicePitch() {
        return (float)(this.voice - 5) * 0.02f + 1.0f;
    }

    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) { return null; }

    public void performRangedAttack(LivingEntity entityliving, float f) {
        this.performRangedAttack(entityliving);
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        float p2 = par2;
        if (p2 > 10.0f) {
            p2 = 10.0f;
        }
        if (!par1DamageSource.getMsgId().equals("cactus")) {
            Entity e;
            Item it;
            PlayerEntity eb;
            ItemStack ist;
            if (par1DamageSource.getMsgId().equals("inWall") && ChaosPersists.valentines_day != 0) {
                return ret;
            }
            if (ChaosPersists.valentines_day != 0 && !this.level.isClientSide && this.feelingBetter == 0 && (e = par1DamageSource.getEntity()) != null && e instanceof PlayerEntity && (ist = (eb = (PlayerEntity)e).getMainHandItem()) != null && (it = ist.getItem()) == ChaosPersists.MyRoseSword) {
                if (this.level.random.nextInt(4) == 1) {
                    this.feelingBetter = 1;
                    this.setTarget(null);
                    this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)this.mygetMaxHealth());
                    int morelove = this.level.random.nextInt(10);
                    for (int i = 0; i < 10 + morelove; ++i) {
                        this.dropItemRand(ChaosPersists.MyLove, 1);
                    }
                } else {
                    this.dropItemRand(ChaosPersists.MyLove, 1);
                }
            }
            int prevHurtTime = this.hurtTime;
            ret = super.hurt(par1DamageSource, p2);
            if (!this.level.isClientSide && ret && this.hurtTime > prevHurtTime) {
                Entity src = par1DamageSource.getEntity();
                if (src instanceof LivingEntity && src != this) {
                    Shoes.shootTowardTarget(this, (LivingEntity) src, 2 + this.random.nextInt(4));
                }
            }
        }
        return ret;
    }

    public boolean getCanSpawnHere() {
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    Block bid = this.level.getBlockState(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k)).getBlock();
                    if (bid != Blocks.SPAWNER) continue;
                    MobSpawnerTileEntity tileMonsterspawner = null;
                    tileMonsterspawner = (MobSpawnerTileEntity)this.level.getBlockEntity(new net.minecraft.util.math.BlockPos((int)this.getX() + j, (int)this.getY() + i, (int)this.getZ() + k));
                                        String s = null;
                    net.minecraft.util.ResourceLocation id = com.astryxion.chaospersists.util.SpawnerFixHelper.getMobSpawnerEntityId(tileMonsterspawner.getSpawner());
                    if (id != null) s = id.getPath();
                    if (s == null || !s.equals("Girlfriend")) continue;
                    return true;
                }
            }
        }
        return super.checkSpawnRules(this.level, SpawnReason.NATURAL);
    }
}

