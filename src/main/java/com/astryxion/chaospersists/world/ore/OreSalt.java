package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.entity.EntityAnt;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OreSalt extends Block {

    public OreSalt() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(5.0f, 2.0f));
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof EntityAnt) {
            entity.hurt(DamageSource.CACTUS, 5.0f);
        }
        super.entityInside(state, world, pos, entity);
    }
}
