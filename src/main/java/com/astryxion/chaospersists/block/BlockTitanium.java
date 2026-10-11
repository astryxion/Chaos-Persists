package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

public class BlockTitanium extends Block {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public BlockTitanium() {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(5.0f, 5.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 10));
    }

    public int tickRate() {
        return 100;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return MiningDropHelper.selfDrops(this, builder);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (level.getRandom().nextInt(20) == 0) {
            sparkle(level, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    private void sparkle(Level level, int par2, int par3, int par4) {
        RandomSource var5 = level.getRandom();
        double var6 = 0.0625;
        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (float) par2 + var5.nextFloat();
            double var11 = (float) par3 + var5.nextFloat();
            double var13 = (float) par4 + var5.nextFloat();
            BlockPos up = new BlockPos(par2, par3 + 1, par4);
            if (var8 == 0 && !level.getBlockState(up).isCollisionShapeFullBlock(level, up)) {
                var11 = (double) (par3 + 1) + var6;
            }
            BlockPos down = new BlockPos(par2, par3 - 1, par4);
            if (var8 == 1 && !level.getBlockState(down).isCollisionShapeFullBlock(level, down)) {
                var11 = (double) (par3 + 0) - var6;
            }
            BlockPos south = new BlockPos(par2, par3, par4 + 1);
            if (var8 == 2 && !level.getBlockState(south).isCollisionShapeFullBlock(level, south)) {
                var13 = (double) (par4 + 1) + var6;
            }
            BlockPos north = new BlockPos(par2, par3, par4 - 1);
            if (var8 == 3 && !level.getBlockState(north).isCollisionShapeFullBlock(level, north)) {
                var13 = (double) (par4 + 0) - var6;
            }
            BlockPos east = new BlockPos(par2 + 1, par3, par4);
            if (var8 == 4 && !level.getBlockState(east).isCollisionShapeFullBlock(level, east)) {
                var9 = (double) (par2 + 1) + var6;
            }
            BlockPos west = new BlockPos(par2 - 1, par3, par4);
            if (var8 == 5 && !level.getBlockState(west).isCollisionShapeFullBlock(level, west)) {
                var9 = (double) (par2 + 0) - var6;
            }
            if (var9 >= (double) par2 && var9 <= (double) (par2 + 1) && var11 >= 0.0 && var11 <= (double) (par3 + 1) && var13 >= (double) par4
                    && var13 <= (double) (par4 + 1)) {
                continue;
            }
            int which = level.getRandom().nextInt(3);
            if (which == 0) {
                level.addParticle(ParticleTypes.FLAME, var9, var11, var13, 0.0, 0.0, 0.0);
            }
            if (which == 1) {
                level.addParticle(ParticleTypes.SMOKE, var9, var11, var13, 0.0, 0.0, 0.0);
            }
            if (which == 2) {
                level.addParticle(RED_DUST, var9, var11, var13, 0.0, 0.0, 0.0);
            }
        }
    }
}
