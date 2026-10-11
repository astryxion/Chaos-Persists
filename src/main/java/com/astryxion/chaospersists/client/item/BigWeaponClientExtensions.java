package com.astryxion.chaospersists.client.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

/** Lazy BEWLR lookup so {@link ModelEvent.ModifyBakingResult} can register renderers after item client init. */
public final class BigWeaponClientExtensions {

    private BigWeaponClientExtensions() {}

    public static void register(Consumer<IClientItemExtensions> consumer, Item item) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                BlockEntityWithoutLevelRenderer renderer = TeisrHandBakedModelWrapper.getCustomRenderer(item);
                return renderer != null
                        ? renderer
                        : Minecraft.getInstance().getItemRenderer().getBlockEntityRenderer();
            }
        });
    }
}
