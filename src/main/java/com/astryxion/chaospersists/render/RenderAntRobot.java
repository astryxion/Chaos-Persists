package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.model.ModelAntRobot;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderAntRobot extends MobRenderer<AntRobot, ModelAntRobot> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/antrobottexture.png");
    private final float scale;

    public RenderAntRobot(
            EntityRendererProvider.Context context, ModelAntRobot model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(AntRobot entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
        // Match 1.7 custom doRender (no LivingEntityRenderer -1.501 humanoid offset).
        poseStack.translate(0.0F, 1.501F, 0.0F);
    }

    @Override
    public ResourceLocation getTextureLocation(AntRobot entity) {
        return TEXTURE;
    }
}
