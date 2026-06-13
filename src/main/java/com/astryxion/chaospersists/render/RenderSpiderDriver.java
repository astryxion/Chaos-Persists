package com.astryxion.chaospersists.render;

import com.astryxion.chaospersists.entity.SpiderDriver;
import net.minecraft.client.renderer.entity.model.SpiderModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderSpiderDriver extends SpiderRenderer<SpiderDriver> {
    private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/spiderdriver.png");

    public RenderSpiderDriver(EntityRendererManager manager, SpiderModel modelSpider, float par2) {
        super(manager);
    }

    @Override
    public ResourceLocation getTextureLocation(SpiderDriver entity) {
        return texture;
    }
}
