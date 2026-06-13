package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.UltimateArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderUltimateArrow extends ArrowRenderer<UltimateArrow> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("minecraft", "textures/entity/projectiles/arrow.png");

    public RenderUltimateArrow(EntityRendererManager manager) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(UltimateArrow entity) {
        return TEXTURE;
    }
}
