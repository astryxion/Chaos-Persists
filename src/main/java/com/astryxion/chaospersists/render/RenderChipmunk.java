/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Chipmunk
 *  com.astryxion.chaospersists.EntityCannonFodder
 *  com.astryxion.chaospersists.ModelChipmunk
 *  com.astryxion.chaospersists.RenderChipmunk
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
import com.astryxion.chaospersists.entity.Chipmunk;
import com.astryxion.chaospersists.entity.EntityCannonFodder;
import com.astryxion.chaospersists.model.ModelChipmunk;
import net.minecraft.util.ResourceLocation;

public class RenderChipmunk
extends LivingRenderer<Chipmunk, ModelChipmunk> {
    protected ModelChipmunk model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/chipmunktexture.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/chipmunktexture2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/chipmunktexture3.png");

    public RenderChipmunk(EntityRendererManager manager, ModelChipmunk par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }
    protected void applyScale(MatrixStack matrixStack, Chipmunk par1Entity) {
        float s = this.scale;
        if (par1Entity != null && par1Entity.isBaby()) {
            s = this.scale / 2.0f;
        }
        matrixStack.scale(s, s, s);
    }

    @Override
    protected void scale(Chipmunk entity, MatrixStack matrixStack, float partialTick) {
        this.applyScale(matrixStack, entity);
    }

    @Override
    public ResourceLocation getTextureLocation(Chipmunk entity) {
        EntityCannonFodder c;
        if (entity instanceof EntityCannonFodder && (c = (EntityCannonFodder)entity).get_is_activated() != 0) {
            if (c.getHatColor() == 2) {
                return texture2;
            }
            if (c.getHatColor() == 3) {
                return texture3;
            }
        }
        return texture;
    }
}

