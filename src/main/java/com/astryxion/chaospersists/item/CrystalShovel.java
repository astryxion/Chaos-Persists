package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class CrystalShovel extends ShovelItem {

    public CrystalShovel(Tier tier, float attackDamage) {
        super(tier, new Properties().stacksTo(1).attributes(ShovelItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, attackDamage), -3.0f)));
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (state.is(Blocks.SNOW)) {
            return true;
        }
        return super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (isCrystalShovelEffective(state.getBlock())) {
            return this.getTier().getSpeed();
        }
        return super.getDestroySpeed(stack, state);
    }

    private static boolean isCrystalShovelEffective(Block block) {
        if (block == Blocks.GRASS_BLOCK
                || block == Blocks.DIRT
                || block == Blocks.SAND
                || block == Blocks.GRAVEL
                || block == Blocks.SNOW_BLOCK
                || block == Blocks.SNOW
                || block == Blocks.CLAY
                || block == Blocks.FARMLAND
                || block == Blocks.MYCELIUM) {
            return true;
        }
        return block == ChaosPersists.CrystalGrass;
    }
}
