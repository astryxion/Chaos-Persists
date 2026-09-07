package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.AttackSquid;
import com.astryxion.chaospersists.model.ModelAttackSquid;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderAttackSquid extends MobRenderer<AttackSquid, ModelAttackSquid> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/attacksquid.png");
    private final float scale;

    public RenderAttackSquid(
            EntityRendererProvider.Context context, ModelAttackSquid model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(AttackSquid entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(AttackSquid entity) {
        return TEXTURE;
    }
}
