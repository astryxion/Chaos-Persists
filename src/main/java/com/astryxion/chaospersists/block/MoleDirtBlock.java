package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * OreSpawn 1.7.10 MoleDirt: hardness 0.6 + gravel sound + strong xz slowdown while inside.
 * The slowdown is what made mining feel like it took forever in 1.7.10.
 */
public class MoleDirtBlock extends Block {

    public MoleDirtBlock() {
        this(0);
    }

    public MoleDirtBlock(int i) {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .mapColor(MapColor.DIRT)
                .strength(0.6f)
                .sound(SoundType.GRAVEL)
                .randomTicks());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        float f = 0.125f;
        return Shapes.box(0.0, 0.0, 0.0, 1.0, 1.0 - f, 1.0);
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity != null) {
            Vec3 motion = entity.getDeltaMovement();
            entity.setDeltaMovement(motion.x * 0.3, motion.y, motion.z * 0.3);
        }
    }
}
