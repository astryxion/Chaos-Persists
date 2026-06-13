package com.astryxion.chaospersists.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RTPBlock extends Block {

    public RTPBlock() {
        this(0);
    }

    public RTPBlock(int i) {
        super(AbstractBlock.Properties.of(Material.STONE)
                .sound(SoundType.STONE)
                );
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity p = (PlayerEntity) entity;
        ServerPlayerEntity mp = entity instanceof ServerPlayerEntity ? (ServerPlayerEntity) entity : null;
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        boolean found = false;
        block0:
        for (int tries = 0; tries < 1000 && !found; ++tries) {
            x = world.random.nextInt(2) == 0
                    ? pos.getX() + 16 + world.random.nextInt(8) - world.random.nextInt(8)
                    : pos.getX() - 16 + world.random.nextInt(8) - world.random.nextInt(8);
            z = world.random.nextInt(2) == 0
                    ? pos.getZ() + 16 + world.random.nextInt(8) - world.random.nextInt(8)
                    : pos.getZ() - 16 + world.random.nextInt(8) - world.random.nextInt(8);
            for (y = pos.getY() - 4; y <= pos.getY() + 4; ++y) {
                BlockPos below = new BlockPos(x, y - 1, z);
                BlockState stateBelow = world.getBlockState(below);
                if (!stateBelow.isFaceSturdy(world, below, net.minecraft.util.Direction.UP)
                        || world.getBlockState(new BlockPos(x, y, z)).getBlock() != Blocks.AIR
                        || world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() != Blocks.AIR) {
                    continue;
                }
                found = true;
                break block0;
            }
        }
        if (found) {
            if (mp != null) {
                mp.teleportTo((double) ((float) x + 0.5f), (double) y, (double) ((float) z + 0.5f));
                mp.yRot = p.yRot;
                mp.xRot = 0.0f;
            } else {
                p.moveTo((double) ((float) x + 0.5f), (double) y, (double) ((float) z + 0.5f), p.yRot, 0.0f);
            }
            for (int var3 = 0; var3 < 6; ++var3) {
                world.addParticle(ParticleTypes.SMOKE, (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.EXPLOSION, (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
                world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
            }
            world.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, 1.5f);
        }
    }
}
