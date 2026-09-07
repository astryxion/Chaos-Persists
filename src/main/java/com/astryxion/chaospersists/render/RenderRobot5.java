package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Robot5;
import com.astryxion.chaospersists.model.ModelRobot5;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRobot5 extends MobRenderer<Robot5, ModelRobot5> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/robot5texture.png");
    private final float scale;

    public RenderRobot5(
            EntityRendererProvider.Context context, ModelRobot5 model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Robot5 entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Robot5 entity) {
        return TEXTURE;
    }
}
