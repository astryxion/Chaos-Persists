package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.model.ModelRobot2;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRobot2 extends MobRenderer<Robot2, ModelRobot2> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robot2.png");
    private final float scale;

    public RenderRobot2(
            EntityRendererProvider.Context context, ModelRobot2 model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Robot2 entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Robot2 entity) {
        return TEXTURE;
    }
}
