package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.RockBase;
import com.astryxion.chaospersists.model.ModelRockBase;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderRockBase extends MobRenderer<RockBase, ModelRockBase> {
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/rockredtexture.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/rockgreentexture.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/rockbluetexture.png");
    private static final ResourceLocation TEXTURE6 =
            new ResourceLocation("chaospersists", "textures/entity/rockpurpletexture.png");
    private static final ResourceLocation TEXTURE7 =
            new ResourceLocation("chaospersists", "textures/entity/rocktexture.png");
    private static final ResourceLocation TEXTURE8 =
            new ResourceLocation("chaospersists", "textures/entity/rocktnttexture.png");
    private static final ResourceLocation TEXTURE9 =
            new ResourceLocation("chaospersists", "textures/entity/rockcrystaltexture.png");
    private static final ResourceLocation TEXTURE10 =
            new ResourceLocation("chaospersists", "textures/entity/rockcrystalgreentexture.png");
    private static final ResourceLocation TEXTURE11 =
            new ResourceLocation("chaospersists", "textures/entity/rockcrystalbluetexture.png");
    private static final ResourceLocation TEXTURE12 =
            new ResourceLocation("chaospersists", "textures/entity/rockcrystaltnttexture.png");
    private final float scale;

    public RenderRockBase(EntityRendererProvider.Context context, ModelRockBase model, float shadow, float scale) {
        super(context, model, shadow * scale);
        this.scale = scale;
    }

    @Override
    protected void scale(RockBase entity, PoseStack poseStack, float partialTick) {
        poseStack.scale(this.scale, this.scale, this.scale);
    }

    @Override
    public void render(
            RockBase entity,
            float entityYaw,
            float partialTicks,
            PoseStack poseStack,
            MultiBufferSource buffer,
            int packedLight) {
        int rt = entity.rock_type > 0 ? entity.rock_type : entity.getRockType();
        super.render(
                entity,
                entityYaw,
                partialTicks,
                poseStack,
                buffer,
                rt >= 9 && rt <= 12 ? 15728880 : packedLight);
    }

    @Override
    protected RenderType getRenderType(
            RockBase entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        int rt = entity.rock_type > 0 ? entity.rock_type : entity.getRockType();
        if (rt >= 9 && rt <= 12) {
            if (glowing) {
                return RenderType.outline(this.getTextureLocation(entity));
            }
            return RenderType.entityTranslucent(this.getTextureLocation(entity));
        }
        return super.getRenderType(entity, bodyVisible, translucent, glowing);
    }

    @Override
    public ResourceLocation getTextureLocation(RockBase entity) {
        int rt = entity.rock_type > 0 ? entity.rock_type : entity.getRockType();
        return switch (rt) {
            case 2 -> TEXTURE2;
            case 3 -> TEXTURE3;
            case 4 -> TEXTURE4;
            case 5 -> TEXTURE5;
            case 6 -> TEXTURE6;
            case 7 -> TEXTURE7;
            case 8 -> TEXTURE8;
            case 9 -> TEXTURE9;
            case 10 -> TEXTURE10;
            case 11 -> TEXTURE11;
            case 12 -> TEXTURE12;
            default -> TEXTURE1;
        };
    }
}
