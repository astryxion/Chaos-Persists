package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.item.WaterBall;
import com.astryxion.chaospersists.render.RenderInfo;
import com.astryxion.chaospersists.util.GenericTargetSorter;
import com.astryxion.chaospersists.util.MyEntityAIFollowOwner;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.PetCombatHelper;
import com.astryxion.chaospersists.util.SpawnerFixHelper;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import com.astryxion.chaospersists.util.ChaosHurtByTargetGoal;
import com.astryxion.chaospersists.util.SurfaceWaterFloat;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.registries.ForgeRegistries;

public class WaterDragon extends TamableAnimal {
    private static final Ingredient TAMING_FISH =
            Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Byte> ATTACKING =
            net.minecraft.network.syncher.SynchedEntityData.defineId(
                    WaterDragon.class, net.minecraft.network.syncher.EntityDataSerializers.BYTE);
    private final GenericTargetSorter targetSorter;
    private RenderInfo renderdata = new RenderInfo();
    private int stream_count = 0;
    private int hurt_timer = 0;
    private int combat_tick = 0;
    private float moveSpeed = 0.25f;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public WaterDragon(EntityType<? extends WaterDragon> type, Level level) {
        super(type, level);
        this.xpReward = 100;
        this.fireImmune();
        this.targetSorter = new GenericTargetSorter(this);
        this.renderdata = new RenderInfo();
        this.getNavigation().setCanFloat(true);
        // 1.1 clears a full block via collide() step-up; 1.0 often fails on exact 1-block height.
        this.setMaxUpStep(1.1F);
        // Surface skim via SurfaceWaterFloat — FloatGoal hop-jumps with swim speed.
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(2, new MyEntityAIFollowOwner(this, 2.0f, 10.0f, 2.0f));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2000000476837158, TAMING_FISH, false));
        this.goalSelector.addGoal(4, new MyEntityAIWanderALot(this, 16, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new ChaosHurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.WaterDragon_stats.health)
                .add(Attributes.MOVEMENT_SPEED, (double) 0.25f)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.WaterDragon_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.WaterDragon_stats.defense)
                // 1.13+ water travel ignores MOVEMENT_SPEED; Forge swim speed is the only multiplier.
                .add(ForgeMod.SWIM_SPEED.get(), 4.0D);
    }

    /**
     * Forge multiplies liquid jumps by {@link ForgeMod#SWIM_SPEED}; keep vanilla 0.04 upward
     * so any jump (pathing, etc.) does not become a surface hop.
     */
    @Override
    protected void jumpInLiquid(TagKey<Fluid> fluidTag) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.04, 0.0));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, (byte) 0);
        if (this.renderdata == null) {
            this.renderdata = new RenderInfo();
        }
        this.renderdata.rf1 = 0.0f;
        this.renderdata.rf2 = 0.0f;
        this.renderdata.rf3 = 0.0f;
        this.renderdata.rf4 = 0.0f;
        this.renderdata.ri1 = 0;
        this.renderdata.ri2 = 0;
        this.renderdata.ri3 = 0;
        this.renderdata.ri4 = 0;
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
        if (!var2.isEmpty() && isTamingFish(var2) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
            if (!this.isTame()) {
                if (!this.level().isClientSide) {
                    if (this.getRandom().nextInt(3) == 0) {
                        this.setTame(true);
                        this.setOwnerUUID(par1EntityPlayer.getUUID());
                        spawnTamingParticles(true);
                        this.level().broadcastEntityEvent(this, (byte) 7);
                        this.heal((float) this.mygetMaxHealth() - this.getHealth());
                    } else {
                        spawnTamingParticles(false);
                        this.level().broadcastEntityEvent(this, (byte) 6);
                    }
                }
            } else if (this.isOwnedBy(par1EntityPlayer)) {
                if (this.level().isClientSide) {
                    spawnTamingParticles(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
                if ((float) this.mygetMaxHealth() > this.getHealth()) {
                    this.heal((float) this.mygetMaxHealth() - this.getHealth());
                }
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Blocks.DEAD_BUSH.asItem())
                && par1EntityPlayer.distanceToSqr(this) < 25.0
                && this.isOwnedBy(par1EntityPlayer)) {
            if (!this.level().isClientSide) {
                this.setTame(false);
                this.setOwnerUUID(null);
                spawnTamingParticles(false);
                this.level().broadcastEntityEvent(this, (byte) 6);
            }
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame()
                && !var2.isEmpty()
                && var2.is(Items.NAME_TAG)
                && par1EntityPlayer.distanceToSqr(this) < 16.0
                && this.isOwnedBy(par1EntityPlayer)) {
            this.setCustomName(var2.getHoverName());
            if (!par1EntityPlayer.getAbilities().instabuild) {
                var2.shrink(1);
                if (var2.isEmpty()) {
                    par1EntityPlayer.setItemInHand(hand, ItemStack.EMPTY);
                }
            }
            return InteractionResult.SUCCESS;
        }
        if (this.isTame() && this.isOwnedBy(par1EntityPlayer) && par1EntityPlayer.distanceToSqr(this) < 25.0) {
            // OreSpawn 1.7.10 setSitting toggled one flag; on 1.20 ordered-sit and pose are separate.
            this.setOrderedToSit(!this.isOrderedToSit());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    /** Keep sitting pose in sync with stay order (1.7.10 EntityTameable.setSitting parity). */
    @Override
    public void setOrderedToSit(boolean orderedToSit) {
        super.setOrderedToSit(orderedToSit);
        this.setInSittingPose(orderedToSit);
        if (!this.level().isClientSide && orderedToSit) {
            PetCombatHelper.onPetSit(this);
        }
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
        if (this.isTame()) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.moveSpeed = this.isInWater() ? 0.55f : 0.25f;
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double) this.moveSpeed);
        super.tick();
    }

    @Override
    public void aiStep() {
        if (this.isDeadOrDying()) {
            super.aiStep();
            return;
        }
        super.aiStep();
        SurfaceWaterFloat.keepOnSurface(this);
    }

    public int mygetMaxHealth() {
        return ChaosPersists.WaterDragon_stats.health;
    }

    public RenderInfo getRenderInfo() {
        return this.renderdata;
    }

    public void setRenderInfo(RenderInfo r) {
        this.renderdata.rf1 = r.rf1;
        this.renderdata.rf2 = r.rf2;
        this.renderdata.rf3 = r.rf3;
        this.renderdata.rf4 = r.rf4;
        this.renderdata.ri1 = r.ri1;
        this.renderdata.ri2 = r.ri2;
        this.renderdata.ri3 = r.ri3;
        this.renderdata.ri4 = r.ri4;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.WaterDragon_stats.defense;
    }

    public int getWaterDragonHealth() {
        return (int) this.getHealth();
    }

    public int getAttackStrength(Entity par1Entity) {
        int var2 = 4;
        if (this.level().getDifficulty() == Difficulty.EASY) {
            var2 = 6;
            if (this.level().getDifficulty() == Difficulty.NORMAL) {
                var2 = 8;
            } else if (this.level().getDifficulty() == Difficulty.HARD) {
                var2 = 10;
            }
        }
        return var2;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.WATERDRAGON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ChaosSounds.WATERDRAGON_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 1.0f;
    }

    @Override
    public float getVoicePitch() {
        return 1.0f;
    }

    protected Item getDropItem() {
        return Items.COD;
    }

    private ItemStack dropItemRand(Item item, int par1) {
        ItemStack is = new ItemStack(item, par1);
        ItemEntity itemEntity =
                new ItemEntity(
                        this.level(),
                        this.getX()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        this.getY() + 1.0,
                        this.getZ()
                                + (double) ChaosPersists.ChaosRand.nextInt(2)
                                - (double) ChaosPersists.ChaosRand.nextInt(2),
                        is);
        this.level().addFreshEntity(itemEntity);
        return is;
    }

    private ItemStack dropItemRandMod(String path, int count) {
        Item item =
                ForgeRegistries.ITEMS.getValue(
                        new ResourceLocation("chaospersists", path));
        if (item == null) {
            return null;
        }
        return this.dropItemRand(item, count);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        this.dropFewItems();
    }

    private void dropFewItems() {
        int var4;
        ItemStack is = null;
        this.dropItemRandMod("waterdragonscale", 1);
        this.dropItemRand(Items.ITEM_FRAME, 1);
        int var5 = 9 + this.getRandom().nextInt(6);
        for (var4 = 0; var4 < var5; ++var4) {
            this.dropItemRand(Items.COD, 1);
        }
        var4 = this.getRandom().nextInt(20);
        switch (var4) {
            case 0: {
                is = this.dropItemRandMod("ultimateaxe", 1);
                break;
            }
            case 1: {
                is = this.dropItemRand(Items.IRON_INGOT, 1);
                break;
            }
            case 2: {
                is = this.dropItemRandMod("ultimatepickaxe", 1);
                break;
            }
            case 3: {
                is = this.dropItemRand(Items.IRON_SWORD, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BANE_OF_ARTHROPODS, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.KNOCKBACK, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.MOB_LOOTING, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_ASPECT, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.SHARPNESS, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 4: {
                is = this.dropItemRand(Items.IRON_SHOVEL, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 5: {
                is = this.dropItemRand(Items.IRON_PICKAXE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_FORTUNE, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 6: {
                is = this.dropItemRand(Items.IRON_AXE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 7: {
                is = this.dropItemRand(Items.IRON_HOE, 1);
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 8: {
                is = this.dropItemRand(Items.IRON_HELMET, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) == 1) {
                    is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.RESPIRATION, 1 + this.getRandom().nextInt(2));
                }
                if (this.getRandom().nextInt(6) != 1) {
                    break;
                }
                is.enchant(Enchantments.AQUA_AFFINITY, 1 + this.getRandom().nextInt(5));
                break;
            }
            case 9: {
                is = this.dropItemRand(Items.IRON_CHESTPLATE, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 10: {
                is = this.dropItemRand(Items.IRON_LEGGINGS, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.BLAST_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FIRE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.PROJECTILE_PROTECTION, 1 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 11: {
                is = this.dropItemRand(Items.IRON_BOOTS, 1);
                if (this.getRandom().nextInt(6) == 1) {
                    is.enchant(Enchantments.FALL_PROTECTION, 5 + this.getRandom().nextInt(5));
                }
                if (this.getRandom().nextInt(2) != 1) {
                    break;
                }
                is.enchant(Enchantments.UNBREAKING, 2 + this.getRandom().nextInt(4));
                break;
            }
            case 12: {
                is = this.dropItemRandMod("ultimateshovel", 1);
                break;
            }
            case 13: {
                this.dropItemRand(Items.IRON_BLOCK, 1);
                break;
            }
            case 14: {
                break;
            }
        }
    }

    @Override
    public boolean doHurtTarget(Entity par1Entity) {
        boolean var4 =
                par1Entity.hurt(
                        this.damageSources().mobAttack(this),
                        (float) ChaosPersists.WaterDragon_stats.attack);
        if (var4) {
            if (par1Entity instanceof LivingEntity living) {
                double ks = 1.1;
                double inair = 0.14;
                float f3 =
                        (float)
                                Math.atan2(
                                        par1Entity.getZ() - this.getZ(), par1Entity.getX() - this.getX());
                if (!par1Entity.isAlive() || par1Entity instanceof Player) {
                    inair *= 2.0;
                }
                living.setDeltaMovement(
                        living.getDeltaMovement()
                                .add(
                                        Math.cos(f3) * ks,
                                        inair,
                                        Math.sin(f3) * ks));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        boolean ret = false;
        if (par1DamageSource.is(DamageTypes.CACTUS)) {
            return false;
        }
        Entity e = par1DamageSource.getEntity();
        if (e instanceof WaterDragon) {
            return false;
        }
        if (e instanceof AttackSquid) {
            return false;
        }
        if (e instanceof WaterBall) {
            return false;
        }
        if (this.hurt_timer <= 0 && !this.isInvulnerableTo(par1DamageSource)) {
            ret = super.hurt(par1DamageSource, par2);
            if (ret) {
                this.hurt_timer = 10;
            }
        }
        if (e instanceof LivingEntity living && MyUtils.isValidAggroTarget(living)) {
            if (e instanceof AttackSquid) {
                return false;
            }
            if (e instanceof WaterDragon) {
                return false;
            }
            this.setTarget(living);
            this.getNavigation().moveTo(living, 1.2);
        }
        return ret;
    }

    private boolean isWaterBlock(Block block) {
        return block == Blocks.WATER;
    }

    private boolean isWaterState(BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }

    private boolean isTamingFish(ItemStack stack) {
        return !stack.isEmpty() && TAMING_FISH.test(stack);
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j));
                Block bid = state.getBlock();
                int d = dx * dx + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x - dx, y + i, z + j));
                bid = state.getBlock();
                d = dx * dx + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x - dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + dy, z + j));
                Block bid = state.getBlock();
                int d = dy * dy + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y - dy, z + j));
                bid = state.getBlock();
                d = dy * dy + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y - dy;
                    this.tz = z + j;
                    ++found;
                }
            }
        }
        for (int i = -dx; i <= dx; ++i) {
            for (int j = -dy; j <= dy; ++j) {
                BlockState state = this.level().getBlockState(new BlockPos(x + i, y + j, z + dz));
                Block bid = state.getBlock();
                int d = dz * dz + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                state = this.level().getBlockState(new BlockPos(x + i, y + j, z - dz));
                bid = state.getBlock();
                d = dz * dz + j * j + i * i;
                if ((this.isWaterBlock(bid) || this.isWaterState(state)) && d < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z - dz;
                    ++found;
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
        PetCombatHelper.tickPetCombat(this);
        super.customServerAiStep();
        if (this.hurt_timer > 0) {
            --this.hurt_timer;
        }
        if (!this.isInWater()
                && this.onGround()
                && this.horizontalCollision
                && !this.isInSittingPose()) {
            this.getJumpControl().jump();
        }
        ++this.combat_tick;
        boolean followingOwner =
                this.isTame()
                        && this.getOwner() != null
                        && !this.isOrderedToSit()
                        && this.distanceToSqr(this.getOwner()) > 16.0;
        if (!followingOwner
                && !this.isInWater()
                && this.getRandom().nextInt(25) == 0
                && !this.isInSittingPose()) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 12; ++i) {
                int j = i;
                if (j > 10) {
                    j = 10;
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
                this.getNavigation().moveTo((double) this.tx, (double) (this.ty - 1), (double) this.tz, 1.33);
            } else {
                if (this.getRandom().nextInt(50) == 1) {
                    this.heal(-1.0f);
                }
                if (this.getHealth() <= 0.0f) {
                    this.discard();
                    return;
                }
            }
        }
        if (this.level().getDifficulty() != Difficulty.PEACEFUL) {
            LivingEntity prior = this.getTarget();
            LivingEntity e = PetCombatHelper.resolveCombatTarget(this, prior, this::findSomethingToAttack);
            if (e != prior) {
                this.setTarget(e);
            }
            if (e == null && !this.isTame()) {
                Player p = this.level().getNearestPlayer(this, 14.0);
                if (p != null && !p.isCreative() && this.getSensing().hasLineOfSight(p)) {
                    e = p;
                    this.setTarget(p);
                }
            }
            if (e != null) {
                this.getNavigation().moveTo(e, 1.0);
                if (this.combat_tick % 5 == 0) {
                    MyUtils.faceEntity(this, e, 10.0f, 10.0f);
                    if (this.distanceToSqr(e)
                            < (double)
                                    ((4.0f + e.getBbWidth() / 2.0f)
                                            * (4.0f + e.getBbWidth() / 2.0f))) {
                        this.setAttacking(1);
                        if (this.getRandom().nextInt(4) == 0 || this.getRandom().nextInt(5) == 1) {
                            this.doHurtTarget(e);
                        }
                    } else {
                        this.watercanon(e);
                    }
                }
            } else {
                this.setAttacking(0);
            }
        }
        if (this.getRandom().nextInt(100) == 1
                && this.isInWater()
                && this.getHealth() < (float) this.mygetMaxHealth()) {
            this.playSound(
                    SoundEvents.GENERIC_SPLASH,
                    1.5f,
                    this.getRandom().nextFloat() * 0.2f + 0.9f);
            this.heal(1.0f);
        }
    }

    private void watercanon(LivingEntity e) {
        double yoff = 1.75;
        double xzoff = 1.5;
        if (this.stream_count > 0) {
            this.setAttacking(2);
            if (this.getRandom().nextInt(15) == 1) {
                SmallFireball sf =
                        new SmallFireball(
                                this.level(),
                                this,
                                e.getX() - this.getX(),
                                e.getY() + 0.75 - (this.getY() + yoff),
                                e.getZ() - this.getZ());
                sf.moveTo(
                        this.getX() - xzoff * Math.sin(Math.toRadians(this.getYHeadRot())),
                        this.getY() + yoff,
                        this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYHeadRot())),
                        this.getYHeadRot(),
                        this.getXRot());
                this.level()
                        .playSound(
                                null,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                this.getSoundSource(),
                                0.75f,
                                1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
                this.level().addFreshEntity(sf);
            }
            WaterBall var2 =
                    new WaterBall(
                            this.level(),
                            e.getX() - this.getX(),
                            e.getY() + 0.75 - (this.getY() + yoff),
                            e.getZ() - this.getZ());
            var2.moveTo(
                    this.getX() - xzoff * Math.sin(Math.toRadians(this.getYHeadRot())),
                    this.getY() + yoff,
                    this.getZ() + xzoff * Math.cos(Math.toRadians(this.getYRot())),
                    this.getYHeadRot(),
                    this.getXRot());
            double var3 = e.getX() - var2.getX();
            double var5 = e.getY() + 0.25 - var2.getY();
            double var7 = e.getZ() - var2.getZ();
            float var9 = Mth.sqrt((float) (var3 * var3 + var7 * var7)) * 0.2f;
            var2.shoot(var3, var5 + (double) var9, var7, 1.4f, 5.0f);
            this.level()
                    .playSound(
                            null,
                            this.getX(),
                            this.getY(),
                            this.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            this.getSoundSource(),
                            0.75f,
                            1.0f / (this.getRandom().nextFloat() * 0.4f + 0.8f));
            this.level().addFreshEntity(var2);
            --this.stream_count;
        } else {
            this.setAttacking(0);
        }
        if (this.stream_count <= 0 && this.getRandom().nextInt(4) == 1) {
            this.stream_count = 8;
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
        if (par1EntityLiving instanceof WaterDragon) {
            return false;
        }
        if (this.isTame() && !PetCombatHelper.wantsPetToAttack(this, par1EntityLiving)) {
            return false;
        }
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        if (this.isTame()) {
            return false;
        }
        if (par1EntityLiving instanceof Player player) {
            if (player.isCreative()) {
                return false;
            }
            return true;
        }
        if (this.isAttackableNonMobTarget(par1EntityLiving)) {
            return true;
        }
        return false;
    }

    private boolean isAttackableNonMobTarget(LivingEntity par1EntityLiving) {
        if (PetCombatHelper.isAutoHostileTarget(par1EntityLiving)) {
            return true;
        }
        if (MyUtils.isAttackableNonMob(par1EntityLiving)) {
            return true;
        }
        String cn = par1EntityLiving.getClass().getName();
        if (cn.endsWith("Leon")
                || cn.endsWith("Spyro")
                || cn.endsWith("Girlfriend")
                || cn.endsWith("Boyfriend")
                || cn.endsWith("GammaMetroid")
                || cn.endsWith("Cephadrome")
                || cn.endsWith("Stinky")) {
            return true;
        }
        if (cn.endsWith("Dragon") && !cn.endsWith("WaterDragon")) {
            return true;
        }
        if (par1EntityLiving instanceof Villager) {
            return true;
        }
        return false;
    }

    private LivingEntity findSomethingToAttack() {
        if (ChaosPersists.PlayNicely != 0) {
            return null;
        }
        if (this.isBaby()) {
            return null;
        }
        List<LivingEntity> var5 =
                this.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, this.getBoundingBox().inflate(14.0, 4.0, 14.0));
        Collections.sort(var5, this.targetSorter);
        Iterator<LivingEntity> var2 = var5.iterator();
        LivingEntity e = this.getTarget();
        if (e != null && e.isAlive()) {
            return e;
        }
        this.setTarget(null);
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, false)) {
                continue;
            }
            return var4;
        }
        return null;
    }

    public int getAttacking() {
        return this.entityData.get(ATTACKING).intValue();
    }

    public void setAttacking(int par1) {
        this.entityData.set(ATTACKING, (byte) par1);
    }

    public static boolean checkWaterDragonSpawnRules(
            EntityType<WaterDragon> type,
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
                    if (id != null) {
                        String s = id.getPath();
                        if ("water_dragon".equals(s) || "Water Dragon".equals(s)) {
                            return true;
                        }
                    }
                }
            }
        }
        if (pos.getY() < 50) {
            return false;
        }
        if (!MyUtils.isDay(level)) {
            return false;
        }
        List<WaterDragon> nearby =
                level.getLevel()
                        .getEntitiesOfClass(
                                WaterDragon.class, new AABB(pos).inflate(16.0, 5.0, 16.0));
        return nearby.isEmpty();
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();
        for (int k = -3; k < 3; ++k) {
            for (int j = -3; j < 3; ++j) {
                for (int i = 0; i < 5; ++i) {
                    checkPos.set((int) this.getX() + j, (int) this.getY() + i, (int) this.getZ() + k);
                    if (MyUtils.getBlockStateForSpawnRules(level, checkPos).getBlock() != Blocks.SPAWNER) {
                        continue;
                    }
                    if (!(MyUtils.getBlockEntityForSpawnRules(level, checkPos) instanceof SpawnerBlockEntity spawner)) {
                        continue;
                    }
                    ResourceLocation id = SpawnerFixHelper.getMobSpawnerEntityIdFromBlockEntity(spawner);
                    if (id != null) {
                        String s = id.getPath();
                        if ("water_dragon".equals(s) || "Water Dragon".equals(s)) {
                            return true;
                        }
                    }
                }
            }
        }
        if (this.getY() < 50.0) {
            return false;
        }
        if (level instanceof Level world && !world.isDay()) {
            return false;
        }
        if (level instanceof Level world) {
            List<WaterDragon> nearby =
                    world.getEntitiesOfClass(
                            WaterDragon.class,
                            this.getBoundingBox().inflate(16.0, 5.0, 16.0),
                            e -> e != this);
            if (!nearby.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        WaterDragon w = (WaterDragon) this.getType().create(level);
        if (this.isTame()) {
            w.setTame(true);
            w.setOwnerUUID(this.getOwnerUUID());
        }
        return w;
    }

    public boolean isWheat(ItemStack par1ItemStack) {
        return isTamingFish(par1ItemStack);
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

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }
}
