package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.RubberDucky;
import com.astryxion.chaospersists.model.ModelRubberDucky;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRubberDucky extends MobRenderer<RubberDucky, ModelRubberDucky> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/rubberduckytexture.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/evilrubberduckytexture.png");
    private final float scale;

    public RenderRubberDucky(EntityRendererProvider.Context context, ModelRubberDucky model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(RubberDucky entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        if (entity.isBaby()) {
            poseStack.scale(this.scale / 2.0f, this.scale / 2.0f, this.scale / 2.0f);
            return;
        }
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(RubberDucky entity) {
        if (entity.getKillCount() >= 5) {
            return TEXTURE2;
        }
        return TEXTURE;
    }
}
