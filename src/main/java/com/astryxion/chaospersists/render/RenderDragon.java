package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.model.ModelDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderDragon extends MobRenderer<Dragon, ModelDragon> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/dragon.png");
    private static final ResourceLocation TEXTURE_WHITE =
            new ResourceLocation("chaospersists", "textures/entity/whitedragon.png");
    private final float scale;

    public RenderDragon(EntityRendererProvider.Context context, ModelDragon model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Dragon entity, PoseStack poseStack, float partialTick) {
        float s = this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    protected RenderType getRenderType(Dragon entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        if (glowing) {
            return RenderType.outline(this.getTextureLocation(entity));
        }
        return RenderType.entityCutoutNoCull(this.getTextureLocation(entity));
    }

    @Override
    public ResourceLocation getTextureLocation(Dragon entity) {
        if (entity.getDragonType() != 0) {
            return TEXTURE_WHITE;
        }
        return TEXTURE;
    }
}
