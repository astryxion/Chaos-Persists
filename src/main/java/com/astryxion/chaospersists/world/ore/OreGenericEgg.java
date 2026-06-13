package com.astryxion.chaospersists.world.ore;

import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OreGenericEgg extends FallingBlock {

    public OreGenericEgg() {
        this(0);
    }

    public OreGenericEgg(int oldid) {
        super(AbstractBlock.Properties.of(Material.SAND).strength(0.6f, 3.0f).sound(SoundType.GRAVEL).noOcclusion());
    }

    @Override
    public void spawnAfterBreak(BlockState state, net.minecraft.world.server.ServerWorld world, BlockPos pos, net.minecraft.item.ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
        if (world.random.nextInt(2) == 1) {
            int xp = 5 + world.random.nextInt(3) + world.random.nextInt(3);
            popExperience((net.minecraft.world.server.ServerWorld) world, pos, xp);
        }
    }
}
