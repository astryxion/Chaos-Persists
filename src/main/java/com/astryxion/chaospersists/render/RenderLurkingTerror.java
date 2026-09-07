package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.LurkingTerror;
import com.astryxion.chaospersists.model.ModelLurkingTerror;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderLurkingTerror extends MobRenderer<LurkingTerror, ModelLurkingTerror> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/lurkingterror.png");
    private final float scale;

    public RenderLurkingTerror(EntityRendererProvider.Context context, ModelLurkingTerror model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(LurkingTerror entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(LurkingTerror entity) {
        return TEXTURE;
    }
}
