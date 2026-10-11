package com.astryxion.chaospersists.core;

import com.astryxion.chaospersists.menu.ContainerCrystalFurnace;
import com.astryxion.chaospersists.menu.ContainerCrystalWorkbench;
import com.astryxion.chaospersists.blockentity.TileEntityCrystalFurnace;
import com.astryxion.chaospersists.client.gui.CrystalWorkbenchGUI;
import com.astryxion.chaospersists.client.gui.CrystalFurnaceGUI;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Legacy GUI id routing. Crystal furnace uses {@link net.minecraft.world.MenuProvider} directly;
 * workbench (id 1) remains until that block is ported to {@code openMenu}.
 */
public class ChaosGUIHandler {

    public static AbstractContainerMenu createServerMenu(int id, Player player, Level level, int x, int y, int z) {
        BlockEntity tileEntity = level.getBlockEntity(new BlockPos(x, y, z));
        switch (id) {
            case 0:
                if (tileEntity instanceof TileEntityCrystalFurnace furnace) {
                    return new ContainerCrystalFurnace(0, player.getInventory(), furnace);
                }
                break;
            case 1:
                return new ContainerCrystalWorkbench(0, player.getInventory(), level, new BlockPos(x, y, z));
            default:
                break;
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static Object createClientScreen(int id, Player player, Level level, int x, int y, int z) {
        BlockEntity tileEntity = level.getBlockEntity(new BlockPos(x, y, z));
        switch (id) {
            case 0:
                if (tileEntity instanceof TileEntityCrystalFurnace furnace) {
                    return new CrystalFurnaceGUI(player.getInventory(), furnace);
                }
                break;
            case 1:
                return new CrystalWorkbenchGUI(
                        new ContainerCrystalWorkbench(0, player.getInventory(), level, new BlockPos(x, y, z)),
                        player.getInventory(),
                        Component.translatable("container.crafting"));
            default:
                break;
        }
        return null;
    }
}
