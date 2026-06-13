package com.astryxion.chaospersists.world.ore;

import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OreUranium extends Block {
    private boolean glowing = false;
    private int glowcount = 0;

    public OreUranium() {
        super(AbstractBlock.Properties.of(Material.STONE).strength(10.0f, 1.0f).randomTicks());
        this.glowing = false;
    }

    @Override
    public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        this.glow(world, pos.getX(), pos.getY(), pos.getZ());
        super.attack(state, world, pos, player);
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        this.glow(world, pos.getX(), pos.getY(), pos.getZ());
        super.stepOn(world, pos, entity);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockRayTraceResult hit) {
        this.glow(world, pos.getX(), pos.getY(), pos.getZ());
        return super.use(state, world, pos, player, hand, hit);
    }

    private void glow(World par1World, int par2, int par3, int par4) {
        this.glowing = true;
        this.glowcount = 10;
        this.sparkle(par1World, par2, par3, par4);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World par1World, BlockPos pos, Random par5Random) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        if (this.glowing) {
            this.sparkle(par1World, par2, par3, par4);
            if (this.glowcount > 0) {
                --this.glowcount;
            } else {
                this.glowing = false;
            }
        }
    }

    private void sparkle(World par1World, int par2, int par3, int par4) {
        Random var5 = par1World.random;
        double var6 = 0.0625;
        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (float)par2 + var5.nextFloat();
            double var11 = (float)par3 + var5.nextFloat();
            double var13 = (float)par4 + var5.nextFloat();
            if (var8 == 0 && !par1World.getBlockState(new BlockPos(par2, par3 + 1, par4)).isSolidRender(par1World,
                    new BlockPos(par2, par3 + 1, par4))) {
                var11 = (double)(par3 + 1) + var6;
            }
            if (var8 == 1 && !par1World.getBlockState(new BlockPos(par2, par3 - 1, par4)).isSolidRender(par1World,
                    new BlockPos(par2, par3 - 1, par4))) {
                var11 = (double)(par3 + 0) - var6;
            }
            if (var8 == 2 && !par1World.getBlockState(new BlockPos(par2, par3, par4 + 1)).isSolidRender(par1World,
                    new BlockPos(par2, par3, par4 + 1))) {
                var13 = (double)(par4 + 1) + var6;
            }
            if (var8 == 3 && !par1World.getBlockState(new BlockPos(par2, par3, par4 - 1)).isSolidRender(par1World,
                    new BlockPos(par2, par3, par4 - 1))) {
                var13 = (double)(par4 + 0) - var6;
            }
            if (var8 == 4 && !par1World.getBlockState(new BlockPos(par2 + 1, par3, par4)).isSolidRender(par1World,
                    new BlockPos(par2 + 1, par3, par4))) {
                var9 = (double)(par2 + 1) + var6;
            }
            if (var8 == 5 && !par1World.getBlockState(new BlockPos(par2 - 1, par3, par4)).isSolidRender(par1World,
                    new BlockPos(par2 - 1, par3, par4))) {
                var9 = (double)(par2 + 0) - var6;
            }
            if (var9 >= (double)par2 && var9 <= (double)(par2 + 1) && var11 >= 0.0 && var11 <= (double)(par3 + 1)
                    && var13 >= (double)par4 && var13 <= (double)(par4 + 1)) {
                continue;
            }
            par1World.addParticle(RedstoneParticleData.REDSTONE, var9, var11, var13, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void spawnAfterBreak(BlockState state, net.minecraft.world.server.ServerWorld world, BlockPos pos, ItemStack stack) {
        super.spawnAfterBreak(state, world, pos, stack);
        if (pos.getY() < 40) {
            int xp = 5 + world.random.nextInt(5) + world.random.nextInt(10);
            popExperience(world, pos, xp);
        }
    }
}
