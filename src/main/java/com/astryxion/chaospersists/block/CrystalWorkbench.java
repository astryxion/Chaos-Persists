package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.menu.ContainerCrystalWorkbench;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class CrystalWorkbench extends CraftingTableBlock {

    public CrystalWorkbench(float hardness, float resistance) {
        super(net.minecraft.world.level.block.Block.Properties.of().strength(hardness, resistance).noOcclusion());
    }

    @Override
    public InteractionResult use(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(
                    serverPlayer,
                    new SimpleMenuProvider(
                            (windowId, inventory, ignored) ->
                                    new ContainerCrystalWorkbench(windowId, inventory, level, pos),
                            Component.translatable("container.crafting")),
                    buf -> buf.writeBlockPos(pos));
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, net.minecraft.core.Direction side) {
        return false;
    }

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {
        // Ensure the block is ray-traceable even though it is non-occluding.
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {
        return Shapes.block();
    }
}
