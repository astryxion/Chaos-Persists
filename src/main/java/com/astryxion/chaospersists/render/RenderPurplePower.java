package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.PurplePower;
import com.astryxion.chaospersists.model.ModelPurplePower;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderPurplePower extends LivingRenderer<PurplePower, ModelPurplePower> {
    protected ModelPurplePower model;
    private float scale = 1.0f;
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/purplepowertexture.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/purplepowertexture2.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/purplepowertexture3.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/purplepowertexture4.png");
    private static final ResourceLocation texture10 = new ResourceLocation("chaospersists", "textures/entity/purplepowertexture10.png");

    public RenderPurplePower(EntityRendererManager manager, ModelPurplePower par1Model, float par2, float par3) {
        super(manager, par1Model, par2 * par3);
        this.model = this.getModel();
        this.scale = par3;
    }

    @Override
    protected void scale(PurplePower entity, MatrixStack matrixStack, float partialTick) {
        float localscale = this.scale;
        if (entity.getPurpleType() != 0) {
            localscale = 0.55f;
        }
        matrixStack.scale(localscale, localscale, localscale);
    }

    @Override
    public ResourceLocation getTextureLocation(PurplePower entity) {
        int i = entity.getPurpleType();
        if (i == 1) {
            return texture2;
        }
        if (i == 2) {
            return texture3;
        }
        if (i == 3) {
            return texture4;
        }
        if (i == 10) {
            return texture10;
        }
        return texture;
    }
}
