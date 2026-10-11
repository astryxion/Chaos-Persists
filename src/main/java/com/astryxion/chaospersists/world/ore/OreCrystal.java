package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

public class OreCrystal extends Block {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public OreCrystal(float lightLevel, float hardness, float resistance) {
        super(Block.Properties.of()
                .strength(hardness, resistance)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> (int) lightLevel)
                .randomTicks()
                .noOcclusion());
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
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if (world.getRandom().nextInt(5) == 0) {
            sparkle(world, pos);
        }
    }

    private void sparkle(Level world, BlockPos pos) {
        float dx = 0.5f;
        float dy = 0.5f;
        float dz = 0.5f;

        for (int i = 0; i < 5; ++i) {
            int which = world.getRandom().nextInt(3);

            if (which == 0) {
                world.addParticle(
                        ParticleTypes.FLAME,
                        pos.getX() + dx,
                        pos.getY() + dy,
                        pos.getZ() + dz,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f);
            }

            if (which == 1) {
                world.addParticle(
                        ParticleTypes.SMOKE,
                        pos.getX() + dx,
                        pos.getY() + dy,
                        pos.getZ() + dz,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f);
            }

            if (which == 2) {
                world.addParticle(
                        RED_DUST,
                        pos.getX() + dx,
                        pos.getY() + dy,
                        pos.getZ() + dz,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f,
                        (world.getRandom().nextFloat() - world.getRandom().nextFloat()) / 4.0f);
            }
        }
    }

    @Override
    public void playerWillDestroy(Level world, BlockPos pos, BlockState state, Player player) {
        if (!world.isClientSide && world.getRandom().nextInt(3) == 1) {
            world.explode(
                    (Entity) null,
                    pos.getX() + 0.5f,
                    pos.getY() + 0.5f,
                    pos.getZ() + 0.5f,
                    1.5f,
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
}
