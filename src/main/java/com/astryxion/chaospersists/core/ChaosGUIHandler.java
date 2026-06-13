package com.astryxion.chaospersists.core;

import com.astryxion.chaospersists.container.ContainerCrystalFurnace;
import com.astryxion.chaospersists.container.ContainerCrystalWorkbench;
import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import com.astryxion.chaospersists.util.CrystalFurnaceGUI;
import com.astryxion.chaospersists.util.CrystalWorkbenchGUI;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Legacy GUI id routing retained for parity with 1.12.2 gui ids 0 (furnace) and 1 (workbench).
 * Crystal furnace and workbench blocks open menus directly on 1.16.5; these helpers remain for any
 * code paths that still reference the old handler ids.
 */
public class ChaosGUIHandler {

    public static Container getServerGuiElement(int id, PlayerEntity player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockEntity(new BlockPos(x, y, z));
        switch (id) {
            case 0:
                if (tileEntity instanceof TileEntityCrystalFurnace) {
                    return new ContainerCrystalFurnace(player.inventory, (TileEntityCrystalFurnace) tileEntity);
                }
                break;
            case 1:
                return new ContainerCrystalWorkbench(0, player.inventory, world, x, y, z);
            default:
                break;
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static Object getClientGuiElement(int id, PlayerEntity player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getBlockEntity(new BlockPos(x, y, z));
        switch (id) {
            case 0:
                if (tileEntity instanceof TileEntityCrystalFurnace) {
                    return new CrystalFurnaceGUI(player.inventory, (TileEntityCrystalFurnace) tileEntity);
                }
                break;
            case 1:
                return new CrystalWorkbenchGUI(
                        new ContainerCrystalWorkbench(0, player.inventory, world, x, y, z),
                        player.inventory,
                        new TranslationTextComponent("container.crafting"));
            default:
                break;
        }
        return null;
    }
}
