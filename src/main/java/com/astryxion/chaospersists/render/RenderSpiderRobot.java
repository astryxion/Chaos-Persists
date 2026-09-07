package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.model.ModelSpiderRobot;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSpiderRobot extends MobRenderer<SpiderRobot, ModelSpiderRobot> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/spiderrobottexture.png");
    private final float scale;

    public RenderSpiderRobot(
            EntityRendererProvider.Context context, ModelSpiderRobot model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(SpiderRobot entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
        // LivingEntityRenderer always translates -1.501 after scale(-1,-1,1) for humanoid
        // models. 1.7/1.12 SpiderRobot used a custom doRender without that offset; cancel
        // it so the body lines up with passenger seats (Spider Driver on the abdomen).
        poseStack.translate(0.0F, 1.501F, 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderRobot entity) {
        return TEXTURE;
    }
}
