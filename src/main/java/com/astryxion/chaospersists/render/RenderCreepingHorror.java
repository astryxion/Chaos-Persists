package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.CreepingHorror;
import com.astryxion.chaospersists.model.ModelCreepingHorror;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderCreepingHorror extends MobRenderer<CreepingHorror, ModelCreepingHorror> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/creepinghorror.png");
    private final float scale;

    public RenderCreepingHorror(EntityRendererProvider.Context context, ModelCreepingHorror model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(CreepingHorror entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(CreepingHorror entity) {
        return TEXTURE;
    }
}
