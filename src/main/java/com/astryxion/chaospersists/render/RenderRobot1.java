package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Robot1;
import com.astryxion.chaospersists.model.ModelRobot1;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRobot1 extends MobRenderer<Robot1, ModelRobot1> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robot1.png");
    private final float scale;

    public RenderRobot1(
            EntityRendererProvider.Context context, ModelRobot1 model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Robot1 entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Robot1 entity) {
        return TEXTURE;
    }
}
