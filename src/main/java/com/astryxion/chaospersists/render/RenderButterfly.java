/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityButterfly
 *  com.astryxion.chaospersists.EntityLunaMoth
 *  com.astryxion.chaospersists.ModelButterfly
 *  com.astryxion.chaospersists.Mothra
 *  com.astryxion.chaospersists.RenderButterfly
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
import com.astryxion.chaospersists.entity.EntityButterfly;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import com.astryxion.chaospersists.model.ModelButterfly;
import com.astryxion.chaospersists.entity.Mothra;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderButterfly
extends LivingRenderer<EntityButterfly, ModelButterfly> {
    protected ModelButterfly model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("textures/entity/creeper/creeper_armor.png");

    public RenderButterfly(EntityRendererManager manager, ModelButterfly par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
        this.addLayer(new LayerRenderer<EntityButterfly, ModelButterfly>(this) {
            @Override
            public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, EntityButterfly entity, float animationPosition, float animationSpeedOld, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
                boolean doit = false;
                if (entity instanceof Mothra) {
                    doit = true;
                } else if (entity instanceof EntityLunaMoth && ((EntityLunaMoth)entity).moth_type == 0) {
                    doit = true;
                }
                if (doit) {
                    RenderButterfly.this.entityRenderDispatcher.textureManager.bind(texture);
                    RenderSystem.enableBlend();
                    RenderSystem.depthMask(false);
                    RenderSystem.depthFunc(GL11.GL_EQUAL);
                    com.mojang.blaze3d.platform.GlStateManager._disableLighting();
                    RenderSystem.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
                    RenderSystem.color4f(0.5f, 0.5f, 0.5f, 1.0f);
                    matrixStack.pushPose();
                    float scroll = (entity.tickCount + partialTicks) * 0.01f;
                    matrixStack.translate(scroll, scroll, 0.0f);
                    RenderButterfly.this.getModel().renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityCutoutNoCull(texture)), packedLight, OverlayTexture.NO_OVERLAY, 0.5f, 0.5f, 0.5f, 1.0f);
                    matrixStack.popPose();
                    RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
                    RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    com.mojang.blaze3d.platform.GlStateManager._enableLighting();
                    RenderSystem.depthMask(true);
                    RenderSystem.depthFunc(GL11.GL_LEQUAL);
                    RenderSystem.disableBlend();
                }
            }
        });
    }

    protected void applyScale(MatrixStack matrixStack) {
        float s = this.scale;
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(EntityButterfly entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityButterfly entity) {
        EntityButterfly a = (EntityButterfly)entity;
        return a.getTexture(a);
    }
}
