package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.model.ModelMolenoid;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderMolenoid extends MobRenderer<Molenoid, ModelMolenoid> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/molenoidtexture.png");
    private final float scale;

    public RenderMolenoid(EntityRendererProvider.Context context, ModelMolenoid model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Molenoid entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        float s = this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public ResourceLocation getTextureLocation(Molenoid entity) {
        return TEXTURE;
    }
}
