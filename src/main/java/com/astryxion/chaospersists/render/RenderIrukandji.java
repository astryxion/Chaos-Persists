package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.model.ModelIrukandji;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderIrukandji extends MobRenderer<Irukandji, ModelIrukandji> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/irukandjitexture.png");
    private final float scale;

    public RenderIrukandji(
            EntityRendererProvider.Context context, ModelIrukandji model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Irukandji entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Irukandji entity) {
        return TEXTURE;
    }
}
