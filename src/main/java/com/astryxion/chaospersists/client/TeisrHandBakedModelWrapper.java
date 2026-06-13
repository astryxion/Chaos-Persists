package com.astryxion.chaospersists.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.extensions.IForgeBakedModel;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Marks an item as using a TEISR and records the hand {@link ItemCameraTransforms.TransformType}
 * so rendering can use 3D in-hand and the flat JSON model elsewhere.
 */
public class TeisrHandBakedModelWrapper implements IBakedModel, IForgeBakedModel {

    private final IBakedModel inner;

    public TeisrHandBakedModelWrapper(IBakedModel inner) {
        this.inner = inner;
    }

    public IBakedModel getInner() {
        return inner;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, Random rand) {
        return inner.getQuads(state, side, rand);
    }

    @Override
    public boolean useAmbientOcclusion() {
        return inner.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return inner.isGui3d();
    }

    @Override
    public boolean isCustomRenderer() {
        return true;
    }

    @Override
    public boolean usesBlockLight() {
        return inner.usesBlockLight();
    }

    @Override
    public TextureAtlasSprite getParticleIcon() {
        return inner.getParticleIcon();
    }

    @Override
    public ItemOverrideList getOverrides() {
        return inner.getOverrides();
    }

    @Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack mat) {
        switch (cameraTransformType) {
            case FIRST_PERSON_LEFT_HAND:
            case FIRST_PERSON_RIGHT_HAND:
            case THIRD_PERSON_LEFT_HAND:
            case THIRD_PERSON_RIGHT_HAND:
                TeisrHandTransformHolder.set(cameraTransformType);
                return this;
            default:
                return inner.handlePerspective(cameraTransformType, mat);
        }
    }
}
