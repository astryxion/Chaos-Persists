package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.container.ContainerCrystalWorkbench;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalWorkbenchGUI extends ContainerScreen<ContainerCrystalWorkbench> {
    private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES =
            new ResourceLocation("textures/gui/container/crafting_table.png");

    public CrystalWorkbenchGUI(ContainerCrystalWorkbench container, PlayerInventory playerInventory, ITextComponent title) {
        super(container, playerInventory, title);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(CRAFTING_TABLE_GUI_TEXTURES);
        int left = this.leftPos;
        int top = this.topPos;
        this.blit(matrixStack, left, top, 0, 0, this.imageWidth, this.imageHeight);
    }
}
