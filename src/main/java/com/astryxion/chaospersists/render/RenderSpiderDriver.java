package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.SpiderDriver;
import net.minecraft.client.model.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.ResourceLocation;

public class RenderSpiderDriver extends SpiderRenderer<SpiderDriver> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/spiderdriver.png");

    public RenderSpiderDriver(EntityRendererProvider.Context context, float shadow) {
        super(context);
        this.shadowRadius = shadow;
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderDriver entity) {
        return TEXTURE;
    }
}
