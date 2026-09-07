package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import org.joml.Vector3f;

public class RTPBlock extends Block {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public RTPBlock() {
        this(0);
    }

    public RTPBlock(int i) {
        // Stone-like: hand mining is slow and drops nothing without a pickaxe (1.7.10 was Material.rock)
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(1.5f, 6.0f)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public ItemStack getCloneItemStack(
            net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player p) {
            ServerPlayer mp = entity instanceof ServerPlayer sp ? sp : null;
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            boolean found = false;
            block0:
            for (int tries = 0; tries < 1000 && !found; ++tries) {
                x = level.getRandom().nextInt(2) == 0
                        ? pos.getX() + 16 + level.getRandom().nextInt(8) - level.getRandom().nextInt(8)
                        : pos.getX() - 16 + level.getRandom().nextInt(8) - level.getRandom().nextInt(8);
                z = level.getRandom().nextInt(2) == 0
                        ? pos.getZ() + 16 + level.getRandom().nextInt(8) - level.getRandom().nextInt(8)
                        : pos.getZ() - 16 + level.getRandom().nextInt(8) - level.getRandom().nextInt(8);
                for (y = pos.getY() - 4; y <= pos.getY() + 4; ++y) {
                    BlockPos below = new BlockPos(x, y - 1, z);
                    BlockPos at = new BlockPos(x, y, z);
                    BlockPos above = new BlockPos(x, y + 1, z);
                    if (!level.getBlockState(below).isSolidRender(level, below)
                            || level.getBlockState(at).getBlock() != Blocks.AIR
                            || level.getBlockState(above).getBlock() != Blocks.AIR) {
                        continue;
                    }
                    found = true;
                    break block0;
                }
            }
            if (found) {
                if (mp != null) {
                    mp.connection.teleport((double) ((float) x + 0.5f), (double) y, (double) ((float) z + 0.5f), p.getYRot(), p.getXRot());
                } else {
                    p.moveTo((double) ((float) x + 0.5f), (double) y, (double) ((float) z + 0.5f), p.getYRot(), p.getXRot());
                }
                for (int var3 = 0; var3 < 6; ++var3) {
                    level.addParticle(ParticleTypes.SMOKE, (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
                    level.addParticle(ParticleTypes.EXPLOSION, (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
                    level.addParticle(RED_DUST, (double) ((float) x + 0.5f), (double) ((float) y + 2.25f), (double) ((float) z + 0.5f), 0.0, 0.0, 0.0);
                }
                level.playSound(null, p.getX(), p.getY(), p.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0f, 1.5f);
            }
        }
        super.stepOn(level, pos, state, entity);
    }
}
