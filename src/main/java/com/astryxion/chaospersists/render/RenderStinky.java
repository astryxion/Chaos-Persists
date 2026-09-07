package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.Stinky;
import com.astryxion.chaospersists.model.ModelStinky;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderStinky extends MobRenderer<Stinky, ModelStinky> {
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture1.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture2.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture3.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture4.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture5.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture6.png");
    private static final ResourceLocation TEXTURE7 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture7.png");
    private static final ResourceLocation TEXTURE8 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture8.png");
    private static final ResourceLocation TEXTURE9 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture9.png");
    private static final ResourceLocation TEXTURE10 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture10.png");
    private static final ResourceLocation TEXTURE11 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture11.png");
    private static final ResourceLocation TEXTURE12 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture12.png");
    private static final ResourceLocation TEXTURE13 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture13.png");
    private static final ResourceLocation TEXTURE14 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture14.png");
    private static final ResourceLocation TEXTURE15 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture15.png");
    private static final ResourceLocation TEXTURE16 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture16.png");
    private static final ResourceLocation TEXTURE17 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture17.png");
    private static final ResourceLocation TEXTURE18 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture18.png");
    private static final ResourceLocation TEXTURE19 =
            new ResourceLocation("chaospersists", "textures/entity/stinkytexture19.png");
    private final float scale;

    public RenderStinky(EntityRendererProvider.Context context, ModelStinky model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(Stinky entity, com.mojang.blaze3d.vertex.PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public ResourceLocation getTextureLocation(Stinky entity) {
        int i = entity.getSkin();
        if (i == 1) {
            return TEXTURE2;
        }
        if (i == 2) {
            return TEXTURE3;
        }
        if (i == 3) {
            return TEXTURE4;
        }
        if (i == 4) {
            return TEXTURE5;
        }
        if (i == 5) {
            return TEXTURE6;
        }
        if (i == 6) {
            return TEXTURE7;
        }
        if (i == 7) {
            return TEXTURE8;
        }
        if (i == 8) {
            return TEXTURE9;
        }
        if (i == 9) {
            return TEXTURE10;
        }
        if (i == 10) {
            return TEXTURE11;
        }
        if (i == 11) {
            return TEXTURE12;
        }
        if (i == 12) {
            return TEXTURE13;
        }
        if (i == 13) {
            return TEXTURE14;
        }
        if (i == 14) {
            return TEXTURE15;
        }
        if (i == 15) {
            return TEXTURE16;
        }
        if (i == 16) {
            return TEXTURE17;
        }
        if (i == 17) {
            return TEXTURE18;
        }
        if (i == 18) {
            return TEXTURE19;
        }
        return TEXTURE1;
    }
}
