package com.astryxion.chaospersists.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class ItemDuctTape extends Item {
    private final Block placeBlock;

    public ItemDuctTape(Block block) {
        super(new Properties().stacksTo(1));
        this.placeBlock = block;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getClickedFace();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (stack.isEmpty()) {
            return InteractionResult.PASS;
        }
        if (facing != Direction.UP) {
            return InteractionResult.FAIL;
        }
        BlockPos placePos = pos.above();
        if (player != null && !player.mayUseItemAt(placePos, facing, stack)) {
            return InteractionResult.FAIL;
        }
        BlockState state = this.placeBlock.defaultBlockState();
        if (!state.canSurvive(level, placePos) || !level.getBlockState(placePos).canBeReplaced()) {
            return InteractionResult.FAIL;
        }
        if (!level.setBlock(placePos, state, 3)) {
            return InteractionResult.FAIL;
        }
        if (level.getBlockState(placePos).is(this.placeBlock)) {
            this.placeBlock.setPlacedBy(level, placePos, state, player, stack);
        }
        SoundType soundtype = state.getSoundType(level, placePos, player);
        level.playSound(
                player,
                placePos,
                soundtype.getPlaceSound(),
                SoundSource.BLOCKS,
                (soundtype.getVolume() + 1.0F) / 2.0F,
                soundtype.getPitch() * 0.8F);
        level.gameEvent(GameEvent.BLOCK_PLACE, placePos, GameEvent.Context.of(player, state));
        stack.shrink(1);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
