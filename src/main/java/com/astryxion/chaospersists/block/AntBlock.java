package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class AntBlock extends GrassBlock {

    public AntBlock(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.copy(Blocks.GRASS_BLOCK).randomTicks());
    }

    /**
     * Official-style 1.12.2 passive nest spawning.
     * Daytime only, no player requirement, cap 20, spawn 2â€“7.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (level.getBlockState(pos.above()).getBlock() != Blocks.AIR) {
            return;
        }

        if (!MyUtils.isDay(level)) {
            return;
        }

        EntityType<? extends Mob> entityType = getEntityTypeForBlock();
        if (entityType == null) {
            return;
        }

        int radius = 16;

        AABB aabb = new AABB(
                pos.getX() - radius,
                0.0D,
                pos.getZ() - radius,
                pos.getX() + radius,
                200.0D,
                pos.getZ() + radius);

        List<? extends Mob> nearby =
                level.getEntitiesOfClass(Mob.class, aabb, mob -> mob.getType() == entityType);

        if (nearby.size() > 20) {
            return;
        }

        int count = rand.nextInt(6) + 2;

        for (int i = 0; i < count; i++) {
            spawnCreature(level, entityType, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        }
    }

    private EntityType<? extends Mob> getEntityTypeForBlock() {
        if (this == ChaosPersists.MyAntBlock) {
            return ChaosPersists.BlackAntEnable != 0 ? ChaosPersists.ENTITY_TYPE_ANT.get() : null;
        }
        if (this == ChaosPersists.MyRedAntBlock) {
            return ChaosPersists.RedAntEnable != 0 ? ChaosPersists.ENTITY_TYPE_RED_ANT.get() : null;
        }
        if (this == ChaosPersists.MyRainbowAntBlock) {
            return ChaosPersists.RainbowAntEnable != 0 ? ChaosPersists.ENTITY_TYPE_RAINBOW_ANT.get() : null;
        }
        if (this == ChaosPersists.MyUnstableAntBlock) {
            return ChaosPersists.UnstableAntEnable != 0 ? ChaosPersists.ENTITY_TYPE_UNSTABLE_ANT.get() : null;
        }
        if (this == ChaosPersists.TermiteBlock) {
            return ChaosPersists.TermiteEnable != 0 ? ChaosPersists.ENTITY_TYPE_TERMITE.get() : null;
        }
        return null;
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
        return com.astryxion.chaospersists.util.MiningDropHelper.selfDrops(this, builder);
    }

    public static Entity spawnCreature(
            Level world, EntityType<? extends Mob> entityType, double x, double y, double z) {
        Entity entity = entityType.create(world);

        if (entity != null) {
            entity.moveTo(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            world.addFreshEntity(entity);
            if (entity instanceof LivingEntity living) {
                MyUtils.playAmbientSound(living);
            }
        }

        return entity;
    }
}
