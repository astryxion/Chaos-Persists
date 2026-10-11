package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class OreCrystalCrystal extends Block {

    public OreCrystalCrystal(float lightLevel, float hardness, float resistance) {
        super(Block.Properties.of()
                .strength(hardness, resistance)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> (int) lightLevel)
                .randomTicks()
                .noOcclusion());
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (world.getRandom().nextInt(20) == 0) {
            sparkle(world, pos);
        }
    }

    private void sparkle(Level world, BlockPos pos) {
        float dx = 0.5f;
        float dy = 0.5f;
        float dz = 0.5f;

        if (this == ChaosPersists.TigersEye) {
            world.addParticle(
                    ParticleTypes.FLAME,
                    pos.getX() + dx,
                    pos.getY() + dy,
                    pos.getZ() + dz,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f);
        } else {
            world.addParticle(
                    ParticleTypes.FIREWORK,
                    pos.getX() + dx,
                    pos.getY() + dy,
                    pos.getZ() + dz,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                    (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f);
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (this == ChaosPersists.CrystalCrystal && !world.isClientSide && world.getRandom().nextInt(10) == 1) {
            world.explode(
                    (Entity) null,
                    pos.getX() + 0.5f,
                    pos.getY() + 0.5f,
                    pos.getZ() + 0.5f,
                    1.0f,
                    Level.ExplosionInteraction.BLOCK);
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, net.minecraft.world.item.ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, world, pos, stack, dropExperience);
        int xp = 5 + world.getRandom().nextInt(5) + world.getRandom().nextInt(10);
        if (pos.getY() < 40) {
            this.popExperience(world, pos, xp);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        RandomSource random = builder.getLevel().getRandom();
        if (this != ChaosPersists.TigersEye) {
            return Collections.singletonList(new ItemStack(this));
        }
        return Collections.singletonList(new ItemStack(this, random.nextInt(2)));
    }
}
