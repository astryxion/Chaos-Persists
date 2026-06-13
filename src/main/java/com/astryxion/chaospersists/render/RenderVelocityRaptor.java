/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.ModelVelocityRaptor
 *  com.astryxion.chaospersists.RenderVelocityRaptor
 *  com.astryxion.chaospersists.VelocityRaptor
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
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import com.astryxion.chaospersists.entity.VelocityRaptor;
import com.astryxion.chaospersists.model.ModelVelocityRaptor;
import net.minecraft.util.ResourceLocation;

public class RenderVelocityRaptor
extends LivingRenderer<VelocityRaptor, ModelVelocityRaptor> {
    protected ModelVelocityRaptor model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/velocityraptor.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/velocityraptor2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/velocityraptor3.png");

    public RenderVelocityRaptor(EntityRendererManager manager, ModelVelocityRaptor par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    protected void applyScale(MatrixStack matrixStack, VelocityRaptor par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(VelocityRaptor entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(VelocityRaptor entity) {
        if (entity.get_is_activated() != 0) {
            if (entity.getHatColor() == 2) {
                return texture2;
            }
            if (entity.getHatColor() == 3) {
                return texture3;
            }
        }
        return texture;
    }
}
