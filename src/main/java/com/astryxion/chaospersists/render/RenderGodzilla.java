package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.model.ModelGodzilla;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderGodzilla extends MobRenderer<Godzilla, ModelGodzilla> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/godzillatexture.png");
    private final float scale;

    public RenderGodzilla(EntityRendererProvider.Context context, ModelGodzilla model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Godzilla entity, PoseStack poseStack, float partialTick) {
        if (entity.getPlayNicely() != 0) {
            float s = this.scale / 4.0f;
            poseStack.scale(s, s, s);
        } else {
            poseStack.scale(this.scale, this.scale, this.scale);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Godzilla entity) {
        return TEXTURE;
    }
}
