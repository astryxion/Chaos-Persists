package com.astryxion.chaospersists.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FishingHookRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Ultimate fish hook uses vanilla bobber rendering (hook sprite + line to rod hand).
 * {@link com.astryxion.chaospersists.item.UltimateFishHook} extends {@link net.minecraft.world.entity.projectile.FishingHook}.
 */
@OnlyIn(Dist.CLIENT)
public class RenderUltimateFishHook extends FishingHookRenderer {

    public RenderUltimateFishHook(EntityRendererProvider.Context context) {
        super(context);
    }
}
