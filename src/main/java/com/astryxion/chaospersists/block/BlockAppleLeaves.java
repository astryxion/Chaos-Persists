package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.IForgeShearable;

public class BlockAppleLeaves extends LeavesBlock implements IForgeShearable {

    public BlockAppleLeaves() {
        super(net.minecraft.world.level.block.Block.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.GRASS).randomTicks());
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack tool, boolean dropExperience) {
        if (com.astryxion.chaospersists.loot.DatapackLoot.hasTable(level, this.getLootTable())) {
            return;
        }
        if (isSilkOrShears(tool)) {
            return;
        }
        RandomSource random = level.getRandom();
        if (random.nextInt(25) == 1) {
            popResource(level, pos, new ItemStack(Items.APPLE));
        }
        if (random.nextInt(500) == 2) {
            popResource(level, pos, new ItemStack(Items.GOLDEN_APPLE));
        }
        if (random.nextInt(1000) == 3) {
            popResource(level, pos, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
        }
        if (random.nextInt(10000) == 4) {
            popResource(level, pos, new ItemStack(ChaosPersists.MagicApple));
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
        if (isSilkOrShears(tool)) {
            return Collections.singletonList(new ItemStack(this));
        }
        return Collections.emptyList();
    }

    static boolean isSilkOrShears(ItemStack tool) {
        return tool != null
                && !tool.isEmpty()
                && (tool.is(Items.SHEARS)
                        || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool) > 0);
    }

    @Override
    protected boolean decaying(BlockState state) {
        return false;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int chance = 20;
        if (level.dimension().equals(ChaosPersists.getDimensionKey(4))) {
            chance = 100;
        }
        int radius = DECAY_DISTANCE;
        if (!level.hasChunksAt(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius))) {
            return;
        }
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    if (dx == 0 && dy == 0 && dz == 0) {
                        continue;
                    }
                    cursor.set(pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz);
                    if (!level.getBlockState(cursor).is(BlockTags.LOGS)) {
                        continue;
                    }
                    if (level.getBlockState(pos.below()).isAir() && rand.nextInt(chance) == 3) {
                        spawnAfterBreak(state, level, pos.below(), ItemStack.EMPTY, false);
                    }
                    long t = level.getDayTime() % 24000L;
                    if (t > 12000L && level.dimension().equals(ChaosPersists.getDimensionKey(4))) {
                        ChaosPersists.setBlockFast(
                                level,
                                pos.getX(),
                                pos.getY(),
                                pos.getZ(),
                                ChaosPersists.MyScaryLeaves,
                                0,
                                3);
                    }
                    return;
                }
            }
        }
        dropResources(state, level, pos);
        level.removeBlock(pos, false);
    }

    @Override
    public boolean isShearable(ItemStack item, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(net.minecraft.world.entity.player.Player player, ItemStack item, Level level, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 1;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (ChaosPersists.FastGraphicsLeaves == 0 && adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }
}
