package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.model.ModelPurplePower;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderPurplePower extends EntityRenderer<PurplePower> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/purplepowertexture.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/purplepowertexture2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/purplepowertexture3.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/purplepowertexture4.png");
    private static final ResourceLocation TEXTURE10 =
            new ResourceLocation("chaospersists", "textures/entity/purplepowertexture10.png");

    /**
     * OreSpawn {@code RenderPurplePower.preRenderScale} for non-zero types (Princess crystals 1-3,
     * King type 10). That method was never hooked via {@code preRenderCallback} in 1.7.10, so
     * beams drew oversized. Use this for all purple/crystal beams — type 0's registered 2.75 never
     * appeared in OG and would make Queen beams worse.
     */
    private static final float CRYSTAL_SCALE = 0.55f;

    private final ModelPurplePower model;

    public RenderPurplePower(
            EntityRendererProvider.Context context, ModelPurplePower model, float shadow, float scale) {
        super(context);
        this.model = model;
        this.shadowRadius = shadow * CRYSTAL_SCALE;
    }

    @Override
    public void render(
            PurplePower entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0F, entity.getBbHeight() * 0.5F, 0.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));

        poseStack.scale(CRYSTAL_SCALE, CRYSTAL_SCALE, CRYSTAL_SCALE);

        float age = entity.tickCount + partialTicks;
        this.model.setupAnim(entity, 0.0F, 0.0F, age, 0.0F, 0.0F);
        this.model.renderToBuffer(
                poseStack,
                buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity))),
                packedLight,
                OverlayTexture.NO_OVERLAY,
                1.0F,
                1.0F,
                1.0F,
                1.0F);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(PurplePower entity) {
        return switch (entity.getPurpleType()) {
            case 1 -> TEXTURE2;
            case 2 -> TEXTURE3;
            case 3 -> TEXTURE4;
            case 10 -> TEXTURE10;
            default -> TEXTURE;
        };
    }
}
