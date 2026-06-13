package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.container.ContainerCrystalWorkbench;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CrystalWorkbench extends CraftingTableBlock {

    public CrystalWorkbench(float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.WOOD)
                .strength(hardness, resistance)
                .noOcclusion());
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player,
                                Hand hand, BlockRayTraceResult hit) {
        if (!world.isClientSide && player instanceof ServerPlayerEntity) {
            INamedContainerProvider provider = new SimpleNamedContainerProvider(
                    (windowId, playerInventory, playerEntity) ->
                            new ContainerCrystalWorkbench(windowId, playerInventory, world, pos.getX(), pos.getY(), pos.getZ()),
                    new TranslationTextComponent("container.crafting"));
            NetworkHooks.openGui((ServerPlayerEntity) player, provider, buf -> buf.writeBlockPos(pos));
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, net.minecraft.world.IBlockReader reader, BlockPos pos) {
        return false;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}
