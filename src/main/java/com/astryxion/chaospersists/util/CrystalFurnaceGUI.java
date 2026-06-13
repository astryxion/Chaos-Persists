package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.container.ContainerCrystalFurnace;
import com.astryxion.chaospersists.tileentity.TileEntityCrystalFurnace;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalFurnaceGUI extends FurnaceScreen {

    public CrystalFurnaceGUI(PlayerInventory playerInventory, TileEntityCrystalFurnace furnace) {
        super(new ContainerCrystalFurnace(playerInventory, furnace), playerInventory, furnace.getDisplayName());
    }
}
