package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityButterfly;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BlockButterflyPlant extends CropBlock {

    public BlockButterflyPlant() {
        super(net.minecraft.world.level.block.Block.Properties.ofFullCopy(Blocks.WHEAT).noOcclusion());
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ChaosPersists.MyButterflySeed;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        super.randomTick(state, level, pos, rand);

        float radius = 50.0F;
        AABB aabb = new AABB(
                pos.getX() - radius,
                0.0D,
                pos.getZ() - radius,
                pos.getX() + radius,
                200.0D,
                pos.getZ() + radius);
        List<EntityButterfly> butterflyList = level.getEntitiesOfClass(EntityButterfly.class, aabb);
        if (butterflyList.size() > 15) {
            return;
        }

        int rate = state.getValue(AGE) & 0x7;
        rate = 7 - rate;
        if (rate > 1 && ChaosPersists.ChaosRand.nextInt(rate) != 0) {
            return;
        }

        if (level.getBlockState(pos.above()).isAir() && MyUtils.isDay(level)) {
            Mob butterfly = ChaosPersists.ENTITY_TYPE_BUTTERFLY.get().create(level);
            if (butterfly != null) {
                butterfly.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
                level.addFreshEntity(butterfly);
            }
        }
    }
}
