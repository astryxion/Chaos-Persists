package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.CrystalCow;
import com.astryxion.chaospersists.entity.EnchantedCow;
import com.astryxion.chaospersists.entity.GoldCow;
import com.astryxion.chaospersists.entity.RedCow;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import org.lwjgl.opengl.GL11;

public class RenderEnchantedCow extends LivingRenderer<RedCow, CowModel<RedCow>> {
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/crystal_cow.png");
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/red_cow.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/gold_cow.png");
    private static final ResourceLocation ENCHANTED_GLINT = new ResourceLocation("minecraft", "textures/misc/enchanted_item_glint.png");

    public RenderEnchantedCow(EntityRendererManager manager, CowModel<RedCow> par1Model, float par2) {
        super(manager, par1Model, par2);
        this.addLayer(new LayerRenderer<RedCow, CowModel<RedCow>>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, RedCow entity, float animationPosition, float animationSpeedOld, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                if (!(entity instanceof EnchantedCow)) {
                    return;
                }
                RenderEnchantedCow.this.entityRenderDispatcher.textureManager.bind(RenderEnchantedCow.ENCHANTED_GLINT);

                RenderSystem.enableBlend();
                RenderSystem.depthMask(false);
                RenderSystem.depthFunc(GL11.GL_EQUAL);
                com.mojang.blaze3d.platform.GlStateManager._disableLighting();
                RenderSystem.blendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                RenderSystem.color4f(0.38F, 0.19F, 0.608F, 1.0F);

                matrixStack.pushPose();
                float scroll = (entity.tickCount + partialTicks) * 0.01F;
                matrixStack.translate(scroll, scroll * 0.5F, 0.0F);

                RenderEnchantedCow.this.getModel().renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(RenderEnchantedCow.ENCHANTED_GLINT)), packedLight, OverlayTexture.NO_OVERLAY, 0.38F, 0.19F, 0.608F, 1.0F);
                matrixStack.popPose();

                RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                com.mojang.blaze3d.platform.GlStateManager._enableLighting();
                RenderSystem.depthMask(true);
                RenderSystem.depthFunc(GL11.GL_LEQUAL);
                RenderSystem.disableBlend();
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(RedCow entity) {
        if (entity instanceof EnchantedCow) {
            return texture2;
        }
        if (entity instanceof GoldCow) {
            return texture2;
        }
        if (entity instanceof CrystalCow) {
            return texture3;
        }
        return texture1;
    }
}
