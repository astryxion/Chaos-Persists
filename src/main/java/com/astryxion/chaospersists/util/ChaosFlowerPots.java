package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;

/**
 * Registers filled flower-pot blocks for OreSpawn flowers and maps them onto the vanilla empty pot.
 */
public final class ChaosFlowerPots {

    /** Plant registry paths that can be placed in a vanilla flower pot. */
    public static final String[] POTTABLE_FLOWERS = {
        "flower_pink",
        "flower_blue",
        "flower_black",
        "flower_scary",
        "crystalflower_red",
        "crystalflower_green",
        "crystalflower_blue",
        "crystalflower_yellow"
    };

    private ChaosFlowerPots() {}

    public static String pottedPath(String flowerPath) {
        return "potted_" + flowerPath;
    }

    public static boolean isPottedFlowerPath(String path) {
        return path != null && path.startsWith("potted_");
    }

    public static void registerBlocks(DeferredRegister<Block> blocks) {
        for (String flower : POTTABLE_FLOWERS) {
            String potted = pottedPath(flower);
            blocks.register(
                    potted,
                    () ->
                            new FlowerPotBlock(
                                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                                    () -> BuiltInRegistries.BLOCK.get(
                                            new ResourceLocation(ChaosPersists.MODID, flower)),
                                    BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)
                                            .instabreak()
                                            .noOcclusion()));
        }
    }

    /** Call during common setup (enqueueWork) after blocks are registered. */
    public static void bindToVanillaFlowerPot() {
        FlowerPotBlock empty = (FlowerPotBlock) Blocks.FLOWER_POT;
        for (String flower : POTTABLE_FLOWERS) {
            ResourceLocation flowerId = new ResourceLocation(ChaosPersists.MODID, flower);
            ResourceLocation pottedId = new ResourceLocation(ChaosPersists.MODID, pottedPath(flower));
            empty.addPlant(flowerId, () -> BuiltInRegistries.BLOCK.get(pottedId));
        }
    }
}
