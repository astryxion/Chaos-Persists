package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.model.ModelGiantRobot;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGiantRobot extends MobRenderer<GiantRobot, ModelGiantRobot> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/giantrobottexture.png");
    private final float scale;

    public RenderGiantRobot(
            EntityRendererProvider.Context context, ModelGiantRobot model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(GiantRobot entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(GiantRobot entity) {
        return TEXTURE;
    }
}
