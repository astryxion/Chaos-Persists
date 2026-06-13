/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Brutalfly
 *  com.astryxion.chaospersists.ModelBrutalfly
 *  com.astryxion.chaospersists.RenderBrutalfly
 *  net.minecraft.client.renderer.entity.model.Model
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.astryxion.chaospersists.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import com.astryxion.chaospersists.entity.Brutalfly;
import com.astryxion.chaospersists.model.ModelBrutalfly;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderBrutalfly
extends LivingRenderer<Brutalfly, ModelBrutalfly> {
    protected ModelBrutalfly model;
    private float scale = 1.0f;
    private static final ResourceLocation overlay = new ResourceLocation("chaospersists", "textures/entity/brutalfly_overlay2.png");
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/brutalflytexture.png");

    public RenderBrutalfly(EntityRendererManager manager, ModelBrutalfly par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
        this.addLayer(new LayerRenderer<Brutalfly, ModelBrutalfly>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, Brutalfly entity, float animationPosition, float animationSpeedOld, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                RenderBrutalfly.this.entityRenderDispatcher.textureManager.bind(overlay);
                RenderSystem.enableBlend();
                RenderSystem.depthMask(false);
                RenderSystem.depthFunc(GL11.GL_EQUAL);
                com.mojang.blaze3d.platform.GlStateManager._disableLighting();
                RenderSystem.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
                RenderSystem.color4f(0.5f, 0.5f, 0.5f, 1.0f);
                matrixStack.pushPose();
                float scroll = (entity.tickCount + partialTicks) * 0.01f;
                matrixStack.translate(scroll, scroll, 0.0f);
                RenderBrutalfly.this.getModel().renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(overlay)), packedLight, OverlayTexture.NO_OVERLAY, 0.5f, 0.5f, 0.5f, 1.0f);
                matrixStack.popPose();
                RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
                RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                com.mojang.blaze3d.platform.GlStateManager._enableLighting();
                RenderSystem.depthMask(true);
                RenderSystem.depthFunc(GL11.GL_LEQUAL);
                RenderSystem.disableBlend();
            }
        });
    }

    protected void applyScale(MatrixStack matrixStack, Brutalfly par1Entity) {
        float s = this.scale;
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(Brutalfly entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Brutalfly entity) {
        return texture;
    }
}
