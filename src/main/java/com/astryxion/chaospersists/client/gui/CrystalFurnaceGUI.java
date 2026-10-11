package com.astryxion.chaospersists.client.gui;

import com.astryxion.chaospersists.menu.ContainerCrystalFurnace;
import com.astryxion.chaospersists.blockentity.TileEntityCrystalFurnace;
import net.minecraft.client.gui.screens.inventory.FurnaceScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalFurnaceGUI extends FurnaceScreen {

    public CrystalFurnaceGUI(Inventory playerInventory, TileEntityCrystalFurnace furnace) {
        super(new ContainerCrystalFurnace(0, playerInventory, furnace), playerInventory, furnace.getDisplayName());
    }
}
