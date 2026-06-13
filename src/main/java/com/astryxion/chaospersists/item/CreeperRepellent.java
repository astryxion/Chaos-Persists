package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.EntityAnt;
import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.Direction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

public class CreeperRepellent extends TorchBlock {

    public CreeperRepellent() {
        this(0.8F);
    }

    public CreeperRepellent(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.TORCH)
                .lightLevel(state -> (int) (lightLevel * 15.0F)), ParticleTypes.FLAME);
    }

    public CreeperRepellent(int par1) {
        this(0.8F);
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("block", this.getRegistryName());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        this.spawnRepellentParticles(worldIn, pos.getX() + 0.5D, pos.getY() + 0.7D, pos.getZ() + 0.5D);
    }

    @OnlyIn(Dist.CLIENT)
    private void spawnRepellentParticles(World worldIn, double x, double y, double z) {
        worldIn.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (!world.isClientSide) {
            this.findSomethingToRepell(world, pos);
            world.getBlockTicks().scheduleTick(pos, this, 10);
        }
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).getBlockTicks().scheduleTick(pos, this, 10);
        }
    }

    @Override
    public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).getBlockTicks().scheduleTick(pos, this, 10);
        }
    }

    /** 1.7.10: repell Creepers, Ants, and PurplePower mobs (except type 10). */
    private void findSomethingToRepell(World world, BlockPos pos) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        AxisAlignedBB bb = new AxisAlignedBB(
                (double) par2 - 20.0D, (double) par3 - 10.0D, (double) par4 - 20.0D,
                (double) par2 + 20.0D, (double) par3 + 10.0D, (double) par4 + 20.0D);
        List<LivingEntity> list = world.getEntitiesOfClass(LivingEntity.class, bb);
        for (LivingEntity var3 : list) {
            if (var3 != null && var3 instanceof CreeperEntity) {
                this.applyRepelPush(var3, par2, par3, par4);
            }
            if (var3 != null && var3 instanceof EntityAnt) {
                this.applyRepelPush(var3, par2, par3, par4);
            }
            if (var3 != null && var3 instanceof PurplePower) {
                PurplePower p = (PurplePower) var3;
                if (p.getPurpleType() == 10) {
                    return;
                }
                this.applyRepelPush(var3, par2, par3, par4);
            }
        }
    }

    private void applyRepelPush(LivingEntity var3, int par2, int par3, int par4) {
        double d1 = var3.getX() - (double) par2;
        double d2 = var3.getY() - (double) par3;
        double d3 = var3.getZ() - (double) par4;
        double f = d1 * d1 + d2 * d2 + d3 * d3;
        f = Math.sqrt(f);
        f = 20.0D - f;
        if (f > 20.0D) {
            f = 20.0D;
        }
        if (f < 0.0D) {
            f = 0.0D;
        }
        double dir = Math.atan2(var3.getX() - (double) par2, var3.getZ() - (double) par4);
        f *= 0.4D;
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(var3, f * Math.sin(dir), 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(var3, 0.0, 0.0, f * Math.cos(dir));
    }
}
