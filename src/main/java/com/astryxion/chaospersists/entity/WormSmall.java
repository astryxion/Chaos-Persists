package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class WormSmall extends Monster {
    public int upcount = 50;
    public int downcount = 0;

    public WormSmall(EntityType<? extends WormSmall> type, Level level) {
        super(type, level);
        this.xpReward = 0;
        this.noPhysics = true;
        this.setMaxUpStep(0.0f);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, (double) ChaosPersists.WormSmall_stats.health)
                .add(Attributes.MOVEMENT_SPEED, 0.10000000149011612)
                .add(Attributes.ATTACK_DAMAGE, (double) ChaosPersists.WormSmall_stats.attack)
                .add(Attributes.ARMOR, (double) ChaosPersists.WormSmall_stats.defense);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Override
    public float getVoicePitch() {
        return 1.5f;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ChaosSounds.LITTLE_SPLAT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public void push(Entity par1Entity) {
    }

    @Override
    protected void pushEntities() {
    }

    public int mygetMaxHealth() {
        return ChaosPersists.WormSmall_stats.health;
    }

    @Override
    public int getArmorValue() {
        return ChaosPersists.WormSmall_stats.defense;
    }

    @Override
    public void tick() {
        if (this.isPersistenceRequired()) {
            this.noPhysics = false;
        }
        super.tick();
        this.wormLivingUpdate();
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y * 0.75, motion.z);
    }

    private void wormLivingUpdate() {
        Player target = this.findNearestPlayerInBox(8.0, 8.0, 8.0);
        if (target != null || ChaosPersists.PlayNicely != 0) {
            if (this.upcount > 0) {
                Block bid;
                --this.upcount;
                if (this.upcount == 0) {
                    this.downcount = 100 + this.getRandom().nextInt(150);
                }
                if (target != null) {
                    this.pointAtEntity(target);
                }
                bid = this.level()
                        .getBlockState(
                                new BlockPos((int) this.getX(), (int) (this.getY() + 0.25), (int) this.getZ()))
                        .getBlock();
                if (bid == Blocks.TALL_GRASS) {
                    bid = Blocks.AIR;
                }
                if (bid != Blocks.AIR) {
                    if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                        this.discard();
                    }
                    Vec3 motion = this.getDeltaMovement();
                    this.setDeltaMovement(motion.x, motion.y + 0.15000000596046448, motion.z);
                    this.setPos(this.getX(), this.getY() + 0.10000000149011612, this.getZ());
                }
            } else {
                if (this.downcount > 0) {
                    --this.downcount;
                } else {
                    this.upcount = 25 + this.getRandom().nextInt(50);
                }
                Block bid = this.level()
                        .getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + 2, (int) this.getZ()))
                        .getBlock();
                if (bid == Blocks.TALL_GRASS) {
                    bid = Blocks.AIR;
                }
                if (bid != Blocks.AIR) {
                    if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                        this.discard();
                    }
                    Vec3 motion = this.getDeltaMovement();
                    this.setDeltaMovement(motion.x, motion.y + 0.20000000298023224, motion.z);
                    this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
                }
            }
        } else {
            this.upcount = this.getRandom().nextInt(50);
            this.downcount = 0;
            Block bid = this.level()
                    .getBlockState(new BlockPos((int) this.getX(), (int) this.getY() + 2, (int) this.getZ()))
                    .getBlock();
            if (bid == Blocks.TALL_GRASS) {
                bid = Blocks.AIR;
            }
            if (bid != Blocks.AIR) {
                if (bid != Blocks.GRASS_BLOCK && bid != Blocks.DIRT && bid != Blocks.STONE) {
                    this.discard();
                }
                Vec3 motion = this.getDeltaMovement();
                this.setDeltaMovement(motion.x, motion.y + 0.10000000149011612, motion.z);
                this.setPos(this.getX(), this.getY() + 0.05000000074505806, this.getZ());
            }
        }
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.x, motion.y - 0.01, motion.z);
        this.setDeltaMovement(0.0, this.getDeltaMovement().y, 0.0);
        this.setZza(0.0f);
    }

    public void pointAtEntity(LivingEntity e) {
        double d1 = e.getX() - this.getX();
        double d2 = e.getZ() - this.getZ();
        float d = (float) Math.atan2(d2, d1);
        float f2 = (float) ((double) d * 180.0 / 3.141592653589793) - 90.0f;
        this.setYRot(f2);
        this.setYHeadRot(f2);
    }

    @Override
    protected void customServerAiStep() {
        int bid = 0;
        Player target = null;
        if (this.isDeadOrDying()) {
            return;
        }
        super.customServerAiStep();
        if (ChaosPersists.PlayNicely != 0) {
            return;
        }
        target = this.findNearestPlayerInBox(1.5, 4.0, 1.5);
        if (target != null && target.isCreative()) {
            target = null;
        }
        if (target != null) {
            this.pointAtEntity(target);
            if (this.upcount > 0 && this.getRandom().nextInt(15) == 1 && !target.isCreative()) {
                ItemStack boots;
                this.doHurtTarget(target);
                if (this.getRandom().nextInt(6) == 1
                        && MyUtils.canMobStripItem(boots = target.getItemBySlot(EquipmentSlot.FEET))) {
                    target.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
                    bid = boots.getMaxDamage() - boots.getDamageValue();
                    bid = bid > 20 ? (bid /= 20) : 1;
                    boots.hurtAndBreak(bid, this, e -> e.broadcastBreakEvent(EquipmentSlot.FEET));
                    ItemEntity dropped = new ItemEntity(
                            this.level(),
                            this.getX()
                                    + (double) ChaosPersists.ChaosRand.nextInt(5)
                                    - (double) ChaosPersists.ChaosRand.nextInt(5),
                            this.getY() + 3.0,
                            this.getZ()
                                    + (double) ChaosPersists.ChaosRand.nextInt(5)
                                    - (double) ChaosPersists.ChaosRand.nextInt(5),
                            boots);
                    this.level().addFreshEntity(dropped);
                }
            }
        }
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.fallDistance = 0.0f;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (level instanceof Level world && world.isDay()) {
            return false;
        }
        return true;
    }

    public static boolean checkWormSmallSpawnRules(
            EntityType<WormSmall> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        return !MyUtils.isDay(level);
    }

    @Override
    public boolean hurt(DamageSource par1DamageSource, float par2) {
        if (par1DamageSource.is(DamageTypes.IN_WALL)) {
            return false;
        }
        return super.hurt(par1DamageSource, par2);
    }

    public static Entity spawnCreature(Level level, String par1, double x, double y, double z) {
        ResourceLocation res = par1.contains(":")
                ? new ResourceLocation(par1)
                : new ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_"));
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(res);
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }

    private Player findNearestPlayerInBox(double expandX, double expandY, double expandZ) {
        AABB box = this.getBoundingBox().inflate(expandX, expandY, expandZ);
        List<Player> players = this.level().getEntitiesOfClass(Player.class, box);
        Player nearest = null;
        double best = Double.MAX_VALUE;
        for (Player player : players) {
            double dist = this.distanceToSqr(player);
            if (dist < best) {
                best = dist;
                nearest = player;
            }
        }
        return nearest;
    }
}
