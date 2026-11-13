package com.astryxion.chaospersists.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ItemPizza extends BlockItem {
    public ItemPizza(Block block) {
        super(block, new Properties());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() != Direction.UP) {
            return InteractionResult.FAIL;
        }
        Level level = context.getLevel();
        BlockPos placePos = context.getClickedPos().above();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        Block block = this.getBlock();
        if (player == null || !player.mayUseItemAt(placePos, context.getClickedFace(), stack)) {
            return InteractionResult.FAIL;
        }
        if (!block.defaultBlockState().canSurvive(level, placePos)) {
            return InteractionResult.FAIL;
        }
        BlockPlaceContext placeContext = new BlockPlaceContext(context);
        BlockState state = block.getStateForPlacement(placeContext);
        if (state == null || !level.getBlockState(placePos).canBeReplaced(placeContext)) {
            return InteractionResult.FAIL;
        }
        if (!level.setBlock(placePos, state, 11)) {
            return InteractionResult.FAIL;
        }
        if (level.getBlockState(placePos).getBlock() == block) {
            block.setPlacedBy(level, placePos, state, player, stack);
        }
        level.playSound(
                player,
                placePos,
                state.getSoundType(level, placePos, player).getPlaceSound(),
                SoundSource.BLOCKS,
                (state.getSoundType(level, placePos, player).getVolume() + 1.0F) / 2.0F,
                state.getSoundType(level, placePos, player).getPitch() * 0.8F);
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return level.isClientSide ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
    }
}
