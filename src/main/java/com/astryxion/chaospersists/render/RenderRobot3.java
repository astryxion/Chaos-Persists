package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Robot3;
import com.astryxion.chaospersists.model.ModelRobot3;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRobot3 extends MobRenderer<Robot3, ModelRobot3> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robot3.png");
    private final float scale;

    public RenderRobot3(
            EntityRendererProvider.Context context, ModelRobot3 model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Robot3 entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Robot3 entity) {
        return TEXTURE;
    }
}
