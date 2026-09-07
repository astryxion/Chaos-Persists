package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Ostrich;
import com.astryxion.chaospersists.model.ModelOstrich;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderOstrich extends MobRenderer<Ostrich, ModelOstrich> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/ostrichtexture.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/ostrichtexture2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/ostrichtexture3.png");
    private final float scale;

    public RenderOstrich(EntityRendererProvider.Context context, ModelOstrich model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Ostrich entity, PoseStack poseStack, float partialTick) {
        float s = entity.isBaby() ? this.scale / 2.0f : this.scale;
        poseStack.scale(s, s, s);
    }

    @Override
    public void render(
            Ostrich entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(getTextureLocation(entity)));
        this.model.renderHats(
                poseStack, consumer, packedLight, OverlayTexture.NO_OVERLAY, entity, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public ResourceLocation getTextureLocation(Ostrich entity) {
        if (entity.get_is_activated() != 0) {
            if (entity.getHatColor() == 2) {
                return TEXTURE2;
            }
            if (entity.getHatColor() == 3) {
                return TEXTURE3;
            }
        }
        return TEXTURE;
    }
}
