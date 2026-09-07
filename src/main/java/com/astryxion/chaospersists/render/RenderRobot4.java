package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.model.ModelRobot4;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRobot4 extends MobRenderer<Robot4, ModelRobot4> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robot4.png");
    private final float scale;

    public RenderRobot4(
            EntityRendererProvider.Context context, ModelRobot4 model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Robot4 entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Robot4 entity) {
        return TEXTURE;
    }
}
