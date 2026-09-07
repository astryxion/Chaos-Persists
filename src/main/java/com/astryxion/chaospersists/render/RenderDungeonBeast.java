package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.DungeonBeast;
import com.astryxion.chaospersists.model.ModelDungeonBeast;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderDungeonBeast extends MobRenderer<DungeonBeast, ModelDungeonBeast> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/botwtexture.png");
    private final float scale;

    public RenderDungeonBeast(
            EntityRendererProvider.Context context, ModelDungeonBeast model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(DungeonBeast entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(DungeonBeast entity) {
        return TEXTURE;
    }
}
