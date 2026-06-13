package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.item.Elevator;
import com.astryxion.chaospersists.model.ModelElevator;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderElevator extends EntityRenderer<Elevator> {
    protected ModelElevator modelElevator;

    public RenderElevator(EntityRendererManager manager) {
        super(manager);
        this.shadowRadius = 0.25F;
        this.modelElevator = new ModelElevator();
    }

    public void renderElevator(Elevator entity, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight,
            float entityYaw, float partialTicks) {
        matrixStack.pushPose();
        matrixStack.mulPose(net.minecraft.util.math.vector.Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float timeSinceHit = (float) entity.getTimeSinceHit() - partialTicks;
        float damageTaken = entity.getDamageTaken() - partialTicks;
        if (damageTaken < 0.0F) {
            damageTaken = 0.0F;
        }
        if (timeSinceHit > 0.0F) {
            matrixStack.mulPose(net.minecraft.util.math.vector.Vector3f.XP.rotationDegrees(
                    MathHelper.sin(timeSinceHit) * timeSinceHit * damageTaken / 10.0F * (float) entity.getForwardDirection()));
        }
        float scale = 0.75F;
        matrixStack.scale(scale, scale, scale);
        matrixStack.scale(1.0F / scale, 1.0F / scale, 1.0F / scale);
        ResourceLocation tex = entity.getTexture();
        this.entityRenderDispatcher.textureManager.bind(tex);
        matrixStack.scale(-1.0F, -1.0F, 1.0F);
        this.modelElevator.setupAnim(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F);
        com.mojang.blaze3d.vertex.IVertexBuilder vertexBuilder = buffer.getBuffer(this.modelElevator.renderType(tex));
        this.modelElevator.renderToBuffer(matrixStack, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.popPose();
    }

    @Override
    public void render(Elevator entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer,
            int packedLight) {
        this.renderElevator(entity, matrixStack, buffer, packedLight, entityYaw, partialTicks);
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(Elevator entity) {
        return entity.getTexture();
    }
}
