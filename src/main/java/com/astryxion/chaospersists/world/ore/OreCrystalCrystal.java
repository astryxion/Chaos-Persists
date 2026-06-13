package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OreCrystalCrystal extends Block {

    public OreCrystalCrystal(float lightLevel, float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.STONE).strength(hardness, resistance).lightLevel(
                state -> (int) lightLevel).randomTicks().noOcclusion());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.random.nextInt(20) == 0) {
            sparkle(world, pos);
        }
    }

    private void sparkle(World world, BlockPos pos) {
        float dx = 0.5f;
        float dy = 0.5f;
        float dz = 0.5f;

        if (this == ChaosPersists.TigersEye) {
            world.addParticle(ParticleTypes.FLAME, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f);
        } else {
            world.addParticle(ParticleTypes.FIREWORK, pos.getX() + dx, pos.getY() + dy, pos.getZ() + dz,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f,
                    (world.random.nextFloat() - world.random.nextFloat()) / 4.0f);
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (this == ChaosPersists.CrystalCrystal && !world.isClientSide && world.random.nextInt(10) == 1) {
            world.explode((Entity)null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 1.0f,
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

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        int count = 1;
        if (this == ChaosPersists.TigersEye) {
            count = builder.getLevel().random.nextInt(2);
        }
        if (count <= 0) {
            return Collections.emptyList();
        }
        List<ItemStack> drops = super.getDrops(state, builder);
        if (drops.isEmpty()) {
            return drops;
        }
        ItemStack stack = drops.get(0);
        java.util.List<ItemStack> result = new java.util.ArrayList<>();
        for (int i = 0; i < count; ++i) {
            result.add(stack.copy());
        }
        return result;
    }
}
