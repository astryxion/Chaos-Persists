package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.projectile.ThrownExperienceBottle;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraftforge.common.IForgeShearable;

public class BlockExperienceLeaves extends LeavesBlock implements IForgeShearable {

    public BlockExperienceLeaves() {
        super(net.minecraft.world.level.block.Block.Properties.copy(Blocks.OAK_LEAVES).sound(SoundType.GRASS).randomTicks());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        ItemStack tool = builder.getOptionalParameter(LootContextParams.TOOL);
        if (BlockAppleLeaves.isSilkOrShears(tool)) {
            return Collections.singletonList(new ItemStack(this));
        }
        return Collections.emptyList();
    }

    @Override
    protected boolean decaying(BlockState state) {
        return false;
    }

    @Override
    public boolean isShearable(ItemStack item, Level level, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(
            net.minecraft.world.entity.player.Player player, ItemStack item, Level level, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(this));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int var7 = 2;
        if (level.hasChunksAt(
                new BlockPos(par2 - var7, par3 - var7, par4 - var7),
                new BlockPos(par2 + var7, par3 + var7, par4 + var7))) {
            for (int var12 = -var7; var12 <= var7; ++var12) {
                for (int var13 = -var7; var13 <= 0; ++var13) {
                    for (int var14 = -var7; var14 <= var7; ++var14) {
                        Block bid;
                        BlockPos off = new BlockPos(par2 + var12, par3 + var13, par4 + var14);
                        int totaldist = Math.abs(var12) + Math.abs(var13) + Math.abs(var14);
                        BlockState offState = level.getBlockState(off);
                        bid = offState.getBlock();
                        if (totaldist > 3 || !offState.isFaceSturdy(level, off, net.minecraft.core.Direction.UP)) {
                            continue;
                        }
                        long t = level.getDayTime();
                        if ((t %= 24000L) < 14000L || t > 22000L) {
                            return;
                        }
                        if (level.getRandom().nextInt(65) == 1
                                && level.getBlockState(new BlockPos(par2, par3 + 1, par4)).isAir()) {
                            BlockPos dropPos = new BlockPos(par2, par3 + 2, par4);
                            popResource(level, dropPos, new ItemStack(Items.EXPERIENCE_BOTTLE));
                        }
                        if (level.getRandom().nextInt(75) == 1
                                && level.getBlockState(new BlockPos(par2, par3 - 1, par4)).isAir()) {
                            ThrownExperienceBottle entity = new ThrownExperienceBottle(level, par2, par3 - 1, par4);
                            entity.setPos(par2, par3 - 1, par4);
                            entity.shoot(
                                    (double) ((level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 2.0f),
                                    -0.10000000149011612,
                                    (double) ((level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 2.0f),
                                    0.4f,
                                    5.0f);
                            level.addFreshEntity(entity);
                        }
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        long t = worldIn.getDayTime();
        if ((t %= 24000L) < 13000L || t > 23000L) {
            return;
        }
        int rate = 0;
        if (t < 14000L) {
            rate = (14000 - (int) t) / 2;
        }
        if (t > 22000L) {
            rate = (int) (t - 22000L) / 2;
        }
        if (worldIn.random.nextInt(200 + rate) == 1
                && worldIn.getBlockState(new BlockPos(par2, par3 + 1, par4)).isAir()) {
            for (int i = 0; i < 10; ++i) {
                worldIn.addParticle(
                        ParticleTypes.FIREWORK,
                        (double) par2,
                        (double) par3 + 1.25,
                        (double) par4,
                        worldIn.random.nextGaussian(),
                        Math.abs(worldIn.random.nextGaussian()),
                        worldIn.random.nextGaussian());
            }
        }
        if (worldIn.random.nextInt(40 + rate) == 1
                && worldIn.getBlockState(new BlockPos(par2, par3 - 1, par4)).isAir()) {
            for (int i = 0; i < 4; ++i) {
                worldIn.addParticle(
                        ParticleTypes.FIREWORK,
                        (double) par2,
                        (double) par3 - 1.25,
                        (double) par4,
                        (double) (worldIn.random.nextFloat() - worldIn.random.nextFloat()),
                        (double) (-Math.abs(worldIn.random.nextFloat())),
                        (double) (worldIn.random.nextFloat() - worldIn.random.nextFloat()));
            }
        }
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (ChaosPersists.FastGraphicsLeaves == 0 && adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return ChaosPersists.FastGraphicsLeaves != 0;
    }
}
