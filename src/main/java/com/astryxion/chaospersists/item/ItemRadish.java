package com.astryxion.chaospersists.item;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/** 1.12 {@code ItemSeedFood}: edible seed that plants a crop on the configured soil block. */
public class ItemRadish extends BlockItem {
    private final Block soilBlock;

    public ItemRadish(int nutrition, float saturation, Block cropBlock, Block soilBlock) {
        super(cropBlock, foodProperties(nutrition, saturation));
        this.soilBlock = soilBlock;
    }

    private static Properties foodProperties(int nutrition, float saturation) {
        return new Properties()
                .food(new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build());
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getClickedFace() != Direction.UP) {
            return InteractionResult.FAIL;
        }
        BlockState ground = context.getLevel().getBlockState(context.getClickedPos());
        if (ground.getBlock() != this.soilBlock) {
            return InteractionResult.FAIL;
        }
        return super.useOn(context);
    }
}
