package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
public class CrystalGrass extends Block {

    public CrystalGrass(float hardness, float resistance) {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .mapColor(net.minecraft.world.level.material.MapColor.COLOR_LIGHT_BLUE)
                .strength(hardness, resistance)
                .sound(SoundType.GRASS)
                .forceSolidOn()
                .noOcclusion()
                .isValidSpawn((state, level, pos, entityType) -> Blocks.GRASS.defaultBlockState().isValidSpawn(level, pos, entityType))
                .isSuffocating((state, level, pos) -> false)
                .isViewBlocking((state, level, pos) -> false));
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter level, BlockPos pos, Direction direction, net.minecraftforge.common.IPlantable plantable) {
        return true;
    }
}
