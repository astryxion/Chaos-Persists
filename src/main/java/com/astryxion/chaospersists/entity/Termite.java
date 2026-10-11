package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.world.dimension.teleporter.CrystalTeleporter;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;

public class Termite extends EntityAnt {
    int attack_delay = 20;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Termite(EntityType<? extends Termite> type, Level level) {
        super(type, level);
        this.moveSpeed = 0.20000000298023224;
        this.xpReward = 1;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 8, 1.0));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 6, true, false, null));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, 2.0);
    }

    @Override
    public int mygetMaxHealth() {
        return 5;
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (ChaosPersists.ChaosRand.nextInt(15) != 0) {
            return false;
        }
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return target.hurt(this.damageSources().mobAttack(this), 1.0f);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (player == null) {
            return InteractionResult.PASS;
        }
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.PASS;
        }
        ItemStack var2 = player.getItemInHand(hand);
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            player.setItemInHand(hand, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()) {
            player.displayClientMessage(Component.literal("Empty your hand!"), true);
            return InteractionResult.FAIL;
        }
        if (serverPlayer.server == null) {
            return InteractionResult.PASS;
        }
        ResourceKey<Level> crystalKey = ChaosPersists.getDimensionKey(5);
        if (!player.level().dimension().equals(crystalKey)) {
            for (int i = 0; i < player.getInventory().items.size(); ++i) {
                if (!player.getInventory().items.get(i).isEmpty()) {
                    player.displayClientMessage(Component.literal("Empty your inventory!"), true);
                    return InteractionResult.FAIL;
                }
            }
            for (int i = 0; i < player.getInventory().armor.size(); ++i) {
                if (!player.getInventory().armor.get(i).isEmpty()) {
                    player.displayClientMessage(Component.literal("Take off your armor!"), true);
                    return InteractionResult.FAIL;
                }
            }
            ServerLevel world = serverPlayer.server.getLevel(crystalKey);
            if (world == null) {
                return InteractionResult.FAIL;
            }
            serverPlayer.changeDimension(world, new CrystalTeleporter(serverPlayer.getX(), serverPlayer.getZ()));
        } else {
            ServerLevel world = serverPlayer.server.getLevel(Level.OVERWORLD);
            if (world == null) {
                return InteractionResult.FAIL;
            }
            serverPlayer.changeDimension(world, new UtopiaTeleporter(serverPlayer.getX(), serverPlayer.getZ()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isDeadOrDying()) {
            return;
        }
        if (this.attack_delay > 0) {
            --this.attack_delay;
        }
        if (this.attack_delay > 0) {
            return;
        }
        this.attack_delay = 20;
        if (this.level().getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        Player e = this.level().getNearestPlayer(this, 1.5);
        if (e != null) {
            this.doHurtTarget(e);
        }
    }

    public boolean isWood(Block bid) {
        if (bid == Blocks.OAK_FENCE
                || bid == Blocks.OAK_FENCE_GATE
                || bid == Blocks.OAK_PLANKS
                || bid == Blocks.OAK_SLAB) {
            return true;
        }
        if (bid == Blocks.WHITE_BED
                || bid == Blocks.CRAFTING_TABLE
                || bid == Blocks.OAK_SIGN
                || bid == Blocks.BOOKSHELF
                || bid == Blocks.OAK_DOOR
                || bid == Blocks.OAK_PRESSURE_PLATE) {
            return true;
        }
        if (bid == Blocks.BIRCH_STAIRS
                || bid == Blocks.OAK_STAIRS
                || bid == Blocks.JUNGLE_STAIRS
                || bid == Blocks.SPRUCE_STAIRS) {
            return true;
        }
        if (ChaosPersists.CrystalPlanksBlock != null && bid == ChaosPersists.CrystalPlanksBlock) {
            return true;
        }
        return false;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int found = 0;
        for (int i = -dy; i <= dy; ++i) {
            for (int j = -dz; j <= dz; ++j) {
                Block bid = this.level().getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (this.isWood(bid)) {
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
                if (this.isWood(bid)) {
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
                if (this.isWood(bid)) {
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
                if (this.isWood(bid)) {
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
                if (this.isWood(bid)) {
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
                if (this.isWood(bid)) {
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
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.getRandom().nextInt(200) == 1 && ChaosPersists.PlayNicely == 0) {
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (int i = 1; i < 8; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 5) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo(this.tx, this.ty, this.tz, 1.0);
                if (this.closest < 6) {
                    if (this.getRandom().nextInt(3) != 0) {
                        if (com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)) {
                            this.level()
                                    .setBlock(
                                            new BlockPos(this.tx, this.ty, this.tz),
                                            Blocks.DIRT.defaultBlockState(),
                                            2);
                        }
                        if (this.findTermiteBuddies() < 10) {
                            spawnCreature(
                                    this.level(),
                                    this.getX() + 0.10000000149011612,
                                    this.getY() + 0.10000000149011612,
                                    this.getZ() + 0.10000000149011612);
                        }
                    } else {
                        if (com.astryxion.chaospersists.config.ChaosGriefing.canGrief(this)) {
                            this.level()
                                    .setBlock(
                                            new BlockPos(this.tx, this.ty, this.tz),
                                            Blocks.AIR.defaultBlockState(),
                                            2);
                        }
                        if (this.findTermiteBuddies() < 10) {
                            spawnCreature(
                                    this.level(),
                                    (float) this.tx + 0.1f,
                                    (float) this.ty + 0.1f,
                                    (float) this.tz + 0.1f);
                        }
                    }
                    this.heal(1.0f);
                }
            }
        }
        super.customServerAiStep();
    }

    private int findTermiteBuddies() {
        List<Termite> var5 =
                this.level()
                        .getEntitiesOfClass(
                                Termite.class, this.getBoundingBox().inflate(3.0, 3.0, 3.0));
        return var5.size();
    }

    public static Entity spawnCreature(Level level, double x, double y, double z) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = ChaosPersists.ENTITY_TYPE_TERMITE.get().create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0f, 0.0f);
        serverLevel.addFreshEntity(entity);
        return entity;
    }
}
