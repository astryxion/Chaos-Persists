package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.Tshirt;
import com.astryxion.chaospersists.model.ModelTshirt;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderTshirt extends MobRenderer<Tshirt, ModelTshirt> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/tshirttexture.png");
    private final float scale;

    public RenderTshirt(EntityRendererProvider.Context context, ModelTshirt model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Tshirt entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Tshirt entity) {
        return TEXTURE;
    }
}
