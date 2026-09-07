package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.model.ModelWormLarge;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderWormLarge extends MobRenderer<WormLarge, ModelWormLarge> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/wormlargetexture.png");
    private final float scale;

    public RenderWormLarge(EntityRendererProvider.Context context, ModelWormLarge model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(WormLarge entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(WormLarge entity) {
        return TEXTURE;
    }
}
