package com.astryxion.chaospersists.client.render;

import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.EnchantedCow;
import com.astryxion.chaospersists.entity.GoldCow;
import com.astryxion.chaospersists.entity.RedCow;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RenderEnchantedCow extends MobRenderer<RedCow, CowModel<RedCow>> {
    private static final ResourceLocation TEXTURE_RED =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/red_cow.png");
    private static final ResourceLocation TEXTURE_GOLD =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/gold_cow.png");
    private static final ResourceLocation TEXTURE_CRYSTAL =
            ResourceLocation.fromNamespaceAndPath("chaospersists", "textures/entity/crystal_cow.png");
    private static final ResourceLocation ENCHANTED_GLINT =
            ResourceLocation.parse("textures/misc/enchanted_glint_entity.png");

    public RenderEnchantedCow(EntityRendererProvider.Context context, CowModel<RedCow> model, float shadow) {
        super(context, model, shadow);
        this.addLayer(new EnchantedCowGlintLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(RedCow entity) {
        if (entity instanceof EnchantedCow) {
            return TEXTURE_GOLD;
        }
        if (entity instanceof GoldCow) {
            return TEXTURE_GOLD;
        }
        if (entity instanceof CrystalCow) {
            return TEXTURE_CRYSTAL;
        }
        return TEXTURE_RED;
    }

    private static class EnchantedCowGlintLayer extends RenderLayer<RedCow, CowModel<RedCow>> {
        private final RenderEnchantedCow renderer;

        EnchantedCowGlintLayer(RenderEnchantedCow renderer) {
            super(renderer);
            this.renderer = renderer;
        }

        @Override
        public void render(
                PoseStack poseStack,
                MultiBufferSource buffer,
                int packedLight,
                RedCow entity,
                float limbSwing,
                float limbSwingAmount,
                float partialTicks,
                float ageInTicks,
                float netHeadYaw,
                float headPitch) {
            if (!(entity instanceof EnchantedCow)) {
                return;
            }
            float scroll = (entity.tickCount + partialTicks) * 0.01f;
            VertexConsumer vertexConsumer =
                    buffer.getBuffer(RenderType.energySwirl(ENCHANTED_GLINT, scroll % 1.0f, scroll % 1.0f));
            this.renderer
                    .getModel()
                    .renderToBuffer(
                            poseStack,
                            vertexConsumer,
                            packedLight,
                            OverlayTexture.NO_OVERLAY,
                            net.minecraft.util.FastColor.ARGB32.colorFromFloat(1.0f, 0.38f, 0.19f, 0.608f));
        }
    }
}
