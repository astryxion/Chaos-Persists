package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityCannonFodder extends TamableAnimal {
    private static final EntityDataAccessor<Integer> IS_ACTIVATED =
            SynchedEntityData.defineId(EntityCannonFodder.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HAT_COLOR =
            SynchedEntityData.defineId(EntityCannonFodder.class, EntityDataSerializers.INT);

    String name_one = null;
    String name_two = null;
    private int is_activated = 0;
    private int hat_color = 0;
    private int syncer = 0;
    private int px = 0;
    private int pz = 0;
    private int py = 0;
    private final GenericTargetSorter localTargetSorter;

    public EntityCannonFodder(EntityType<? extends EntityCannonFodder> type, Level level) {
        super(type, level);
        this.localTargetSorter = new GenericTargetSorter(this);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_ACTIVATED, 0);
        this.entityData.define(HAT_COLOR, 0);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.syncer;
        if (this.syncer > 5) {
            if (this.level().isClientSide) {
                this.is_activated = this.entityData.get(IS_ACTIVATED);
                this.hat_color = this.entityData.get(HAT_COLOR);
            } else {
                this.entityData.set(IS_ACTIVATED, this.is_activated);
                this.entityData.set(HAT_COLOR, this.hat_color);
            }
            this.syncer = 0;
        }
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
        if (this.name_one != null && this.isTame()) {
            if (this.name_one.equals(par1EntityPlayer.getUUID().toString())) {
                if (this.name_two == null) {
                    this.name_two = this.name_one;
                    this.name_one = par1EntityPlayer.getUUID().toString();
                    this.setOwnerUUID(
                            this.name_one != null && !this.name_one.isEmpty()
                                    ? UUID.fromString(this.name_one)
                                    : null);
                    this.is_activated = 2;
                }
            } else if (this.name_two != null) {
                if (!this.name_two.equals(par1EntityPlayer.getUUID().toString())) {
                    return InteractionResult.SUCCESS;
                }
                this.name_two = this.name_one;
                this.name_one = par1EntityPlayer.getUUID().toString();
                this.setOwnerUUID(
                        this.name_one != null && !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
                this.is_activated = 2;
            } else {
                this.name_two = this.name_one;
                this.name_one = par1EntityPlayer.getUUID().toString();
                this.setOwnerUUID(
                        this.name_one != null && !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
                this.is_activated = 2;
            }
        }
        if (!var2.isEmpty() && var2.is(Items.CARROT) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            this.hat_color = 1;
            if (this.name_one == null) {
                this.name_one = par1EntityPlayer.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(
                    this.name_one != null && !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
            spawnTamingParticles(true);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (!var2.isEmpty() && var2.is(Items.POTATO) && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            this.hat_color = 3;
            if (this.name_one == null) {
                this.name_one = par1EntityPlayer.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(
                    this.name_one != null && !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
            spawnTamingParticles(true);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        Item quinoa = ChaosPersists.MyQuinoa;
        if (!var2.isEmpty()
                && quinoa != null
                && var2.is(quinoa)
                && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            this.hat_color = 2;
            if (this.name_one == null) {
                this.name_one = par1EntityPlayer.getUUID().toString();
            }
            if (this.is_activated == 0) {
                this.is_activated = 1;
            }
            this.setTame(true);
            this.setOwnerUUID(
                    this.name_one != null && !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
            spawnTamingParticles(true);
            this.heal(this.getMaxHealth() - this.getHealth());
            this.setAge(-24000);
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        Item corn = ChaosPersists.MyCornCob;
        if (!var2.isEmpty()
                && corn != null
                && this.is_activated == 2
                && var2.is(corn)
                && par1EntityPlayer.distanceToSqr(this) < 16.0) {
            String myname = "Ostrich";
            if (this instanceof Lizard) {
                myname = "Lizard";
            }
            if (this instanceof Chipmunk) {
                myname = "Chipmunk";
            }
            if (this instanceof VelocityRaptor) {
                myname = "Velocity Raptor";
            }
            if (!this.level().isClientSide) {
                Entity newent =
                        spawnCreature(
                                this.level(),
                                myname,
                                this.getX() + (double) this.getRandom().nextFloat(),
                                this.getY() + 0.01,
                                this.getZ() + (double) this.getRandom().nextFloat());
                if (newent instanceof EntityCannonFodder cf) {
                    cf.setOwnerUUID(this.getOwnerUUID());
                    cf.setTame(true);
                    cf.setStuff(this.hat_color, this.is_activated, this.name_one, this.name_two);
                }
            }
            spawnTamingParticles(true);
            par1EntityPlayer.playSound(SoundEvents.GENERIC_EXPLODE, 0.75f, 2.0f);
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.is_activated != 2 || par1EntityPlayer.distanceToSqr(this) >= 16.0) {
            return InteractionResult.PASS;
        }
        if (this.isInSittingPose()) {
            this.setOrderedToSit(false);
            spawnTamingParticles(true);
            return InteractionResult.SUCCESS;
        }
        this.setOrderedToSit(true);
        if (!this.level().isClientSide) {
            PetCombatHelper.onPetSit(this);
        }
        spawnTamingParticles(false);
        this.px = (int) this.getX();
        this.py = (int) this.getY();
        this.pz = (int) this.getZ();
        return InteractionResult.SUCCESS;
    }

    public static Entity spawnCreature(Level par0World, String par1, double par2, double par4, double par6) {
        ResourceLocation key;
        if (par1.indexOf(':') >= 0) {
            key = new ResourceLocation(par1);
        } else {
            key =
                    switch (par1) {
                        case "Chipmunk" -> new ResourceLocation("chaospersists", "chipmunk");
                        case "Ostrich" -> new ResourceLocation("chaospersists", "ostrich");
                        case "Lizard" -> new ResourceLocation("chaospersists", "lizard");
                        case "Velocity Raptor" ->
                                new ResourceLocation("chaospersists", "velocity_raptor");
                        default -> new ResourceLocation("chaospersists", par1);
                    };
        }
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(key);
        if (type == null) {
            return null;
        }
        Entity var8 = type.create(par0World);
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.getRandom().nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            if (var8 instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return var8;
    }

    public void setStuff(int hc, int ia, String s1, String s2) {
        this.hat_color = hc;
        this.is_activated = ia;
        this.name_one = s1;
        this.name_two = s2;
        this.setAge(-24000);
    }

    public int getHatColor() {
        return this.hat_color;
    }

    public int get_is_activated() {
        return this.is_activated;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.addAdditionalSaveData(par1NBTTagCompound);
        if (this.name_one == null) {
            par1NBTTagCompound.putString("NameOne", "");
        } else {
            par1NBTTagCompound.putString("NameOne", this.name_one);
        }
        if (this.name_two == null) {
            par1NBTTagCompound.putString("NameTwo", "");
        } else {
            par1NBTTagCompound.putString("NameTwo", this.name_two);
        }
        par1NBTTagCompound.putInt("IsActivated", this.is_activated);
        par1NBTTagCompound.putInt("HatColor", this.hat_color);
        par1NBTTagCompound.putInt("PatrolX", this.px);
        par1NBTTagCompound.putInt("PatrolY", this.py);
        par1NBTTagCompound.putInt("PatrolZ", this.pz);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag par1NBTTagCompound) {
        super.readAdditionalSaveData(par1NBTTagCompound);
        this.name_one = par1NBTTagCompound.getString("NameOne");
        if (this.name_one != null && this.name_one.equals("")) {
            this.name_one = null;
        }
        this.name_two = par1NBTTagCompound.getString("NameTwo");
        if (this.name_two != null && this.name_two.equals("")) {
            this.name_two = null;
        }
        this.is_activated = par1NBTTagCompound.getInt("IsActivated");
        this.hat_color = par1NBTTagCompound.getInt("HatColor");
        this.px = par1NBTTagCompound.getInt("PatrolX");
        this.py = par1NBTTagCompound.getInt("PatrolY");
        this.pz = par1NBTTagCompound.getInt("PatrolZ");
        if (this.name_one != null) {
            this.setTame(true);
            this.setOwnerUUID(
                    !this.name_one.isEmpty() ? UUID.fromString(this.name_one) : null);
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
        if (!this.getSensing().hasLineOfSight(par1EntityLiving)) {
            return false;
        }
        if (this.isInSittingPose()) {
            double dx = (double) this.px - par1EntityLiving.getX();
            double dy = (double) this.py - par1EntityLiving.getY();
            double dz = (double) this.pz - par1EntityLiving.getZ();
            if (dx * dx + dy * dy + dz * dz > 144.0) {
                return false;
            }
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        if (par1EntityLiving instanceof EntityCannonFodder cf) {
            int i = cf.getHatColor();
            if (i != 0 && i != this.hat_color) {
                return true;
            }
            return false;
        }
        if (par1EntityLiving instanceof Player p) {
            if (p.getAbilities().instabuild) {
                return false;
            }
            if (this.name_one != null && this.name_one.equals(p.getUUID().toString())) {
                return false;
            }
            if (this.name_two != null && this.name_two.equals(p.getUUID().toString())) {
                return false;
            }
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class,
                                this.getBoundingBox().inflate(10.0, 4.0, 10.0));
        Collections.sort(var5, this.localTargetSorter);
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
    public int getArmorValue() {
        if (this.is_activated == 2) {
            return 3;
        }
        return 0;
    }

    public void attackEntityAsFodder(Entity par1Entity, float f) {
        par1Entity.hurt(this.damageSources().mobAttack(this), f);
    }

    @Override
    protected void customServerAiStep() {
        if (this.isDeadOrDying()) {
            return;
        }
        PetCombatHelper.tickPetCombat(this);
        super.customServerAiStep();
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.is_activated != 2) {
            return;
        }
        int pfreq = 5;
        int sfreq = 7;
        float dm = 4.0f;
        if (this instanceof Chipmunk) {
            dm = 3.0f;
            sfreq = 6;
        }
        if (this instanceof Lizard) {
            dm = 6.0f;
            sfreq = 8;
        }
        if (this instanceof VelocityRaptor) {
            sfreq = 6;
            pfreq = 4;
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL && this.getRandom().nextInt(pfreq) == 1) {
            LivingEntity e =
                    PetCombatHelper.resolveCombatTarget(
                            this, this.getTarget(), this::findSomethingToAttack);
            if (e != this.getTarget()) {
                this.setTarget(e);
            }
            if (e != null) {
                this.getNavigation().moveTo(e, 1.25);
                if (this.distanceToSqr(e) < 9.0
                        && (this.getRandom().nextInt(sfreq + 1) == 0 || this.getRandom().nextInt(sfreq) == 1)) {
                    this.attackEntityAsFodder(e, dm);
                }
            } else if (this.isInSittingPose()) {
                this.getNavigation().moveTo((double) this.px, (double) this.py, (double) this.pz, 0.6499999761581421);
            }
        }
        if (this.getRandom().nextInt(250) == 1) {
            this.heal(1.0f);
        }
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }
}
