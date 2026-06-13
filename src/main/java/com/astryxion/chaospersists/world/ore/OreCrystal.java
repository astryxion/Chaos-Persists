package com.astryxion.chaospersists.world.ore;

import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OreCrystal extends Block {

    public OreCrystal(float lightLevel, float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.STONE).strength(hardness, resistance).lightLevel(
                state -> (int) lightLevel).randomTicks().noOcclusion());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.random.nextInt(5) == 0) {
            sparkle(world, pos);
        }
    }

    private void sparkle(World world, BlockPos pos) {
        float dx = 0.5f;
        float dy = 0.5f;
        float dz = 0.5f;

        for (int i = 0; i < 5; ++i) {
            int which = world.random.nextInt(3);

            if (which == 0) {
                world.addParticle(ParticleTypes.FLAME, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f);
            }

            if (which == 1) {
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f);
            }

            if (which == 2) {
                world.addParticle(RedstoneParticleData.REDSTONE, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                        (world.random.nextFloat() - world.random.nextFloat()) / 4.0f);
            }
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClientSide && world.random.nextInt(3) == 1) {
            world.explode((Entity)null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 1.5f,
                    world.getGameRules().getBoolean(net.minecraft.world.GameRules.RULE_MOBGRIEFING)
                            ? Explosion.Mode.DESTROY
                            : Explosion.Mode.BREAK);
        }
        super.playerWillDestroy(world, pos, state, player);
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
