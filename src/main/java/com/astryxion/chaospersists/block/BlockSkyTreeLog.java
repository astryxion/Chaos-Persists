package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;

public class BlockSkyTreeLog extends Block {

    public BlockSkyTreeLog() {
        this(0, 0);
    }

    protected BlockSkyTreeLog(int par1, int par2) {
        super(net.minecraft.world.level.block.Block.Properties.of().sound(SoundType.WOOD).strength(2.0f));
    }

    public boolean isFlammable(BlockState state, net.minecraft.world.level.BlockGetter level, BlockPos pos, net.minecraft.core.Direction direction) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.MySkyTreeLog));
    }

    public void breakRecursor(Level world, int x, int y, int z, int xf, int yf, int zf, int recursion) {
        int var7 = 1;
        if (recursion > 1000) {
            return;
        }
        for (int var9 = -var7; var9 <= var7; ++var9) {
            for (int var10 = -var7; var10 <= var7; ++var10) {
                for (int var11 = -var7; var11 <= var7; ++var11) {
                    Block var12;
                    if (var9 == 0 && var10 == 0 && var11 == 0
                            || x + var9 == xf && y + var10 == yf && z + var11 == zf
                            || recursion > 0
                                    && x + var9 >= xf - var7
                                    && x + var9 <= xf + var7
                                    && y + var10 >= yf - var7
                                    && y + var10 <= yf + var7
                                    && z + var11 >= zf - var7
                                    && z + var11 <= zf + var7) {
                        continue;
                    }
                    BlockPos p = new BlockPos(x + var9, y + var10, z + var11);
                    var12 = world.getBlockState(p).getBlock();
                    if (var12 != this) {
                        continue;
                    }
                    BlockState oldState = world.getBlockState(p);
                    world.setBlock(p, Blocks.AIR.defaultBlockState(), 2);
                    popResource((ServerLevel) world, p, new ItemStack(ChaosPersists.MySkyTreeLog));
                    this.breakRecursor(world, x + var9, y + var10, z + var11, x, y, z, recursion + 1);
                }
            }
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            this.breakRecursor(level, pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ(), 0);
        }
        super.playerWillDestroy(level, pos, state, player);
    }
}
