package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRuby extends Block {

    public BlockRuby() {
        super(AbstractBlock.Properties.of(Material.STONE)
                .strength(4.0f, 4.0f)
                
                .lightLevel(s -> 6)
                .noOcclusion());
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
        if (this == ChaosPersists.MyBlockMobzillaScaleBlock && entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 200, 0));
        }
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if (this == ChaosPersists.MyBlockMobzillaScaleBlock && entity instanceof LivingEntity) {
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 200, 0));
        }
    }
}
