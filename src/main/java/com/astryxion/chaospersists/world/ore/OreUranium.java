package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.util.MiningDropHelper;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Vector3f;

public class OreUranium extends Block {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    private boolean glowing = false;
    private int glowcount = 0;

    public OreUranium() {
        this(SoundType.STONE);
    }

    public OreUranium(SoundType sound) {
        super(Block.Properties.of()
                .strength(10.0f, 1.0f)
                .sound(sound)
                .requiresCorrectToolForDrops()
                .randomTicks());
        this.glowing = false;
    }

    public int tickRate() {
        return 30;
    }

    @Override
    public void attack(BlockState state, Level level, BlockPos pos, Player player) {
        this.glow(level, pos.getX(), pos.getY(), pos.getZ());
        super.attack(state, level, pos, player);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        this.glow(level, pos.getX(), pos.getY(), pos.getZ());
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        this.glow(level, pos.getX(), pos.getY(), pos.getZ());
        return super.use(state, level, pos, player, hand, hit);
    }

    private void glow(Level level, int par2, int par3, int par4) {
        this.glowing = true;
        this.glowcount = 10;
        this.sparkle(level, par2, par3, par4);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        if (this.glowing) {
            this.sparkle(level, par2, par3, par4);
            if (this.glowcount > 0) {
                --this.glowcount;
            } else {
                this.glowing = false;
            }
        }
    }

    private void sparkle(Level level, int par2, int par3, int par4) {
        RandomSource var5 = level.getRandom();
        double var6 = 0.0625;
        for (int var8 = 0; var8 < 6; ++var8) {
            double var9 = (float) par2 + var5.nextFloat();
            double var11 = (float) par3 + var5.nextFloat();
            double var13 = (float) par4 + var5.nextFloat();
            if (var8 == 0
                    && !level.getBlockState(new BlockPos(par2, par3 + 1, par4)).isCollisionShapeFullBlock(level, new BlockPos(par2, par3 + 1, par4))) {
                var11 = (double) (par3 + 1) + var6;
            }
            if (var8 == 1
                    && !level.getBlockState(new BlockPos(par2, par3 - 1, par4)).isCollisionShapeFullBlock(level, new BlockPos(par2, par3 - 1, par4))) {
                var11 = (double) (par3 + 0) - var6;
            }
            if (var8 == 2
                    && !level.getBlockState(new BlockPos(par2, par3, par4 + 1)).isCollisionShapeFullBlock(level, new BlockPos(par2, par3, par4 + 1))) {
                var13 = (double) (par4 + 1) + var6;
            }
            if (var8 == 3
                    && !level.getBlockState(new BlockPos(par2, par3, par4 - 1)).isCollisionShapeFullBlock(level, new BlockPos(par2, par3, par4 - 1))) {
                var13 = (double) (par4 + 0) - var6;
            }
            if (var8 == 4
                    && !level.getBlockState(new BlockPos(par2 + 1, par3, par4)).isCollisionShapeFullBlock(level, new BlockPos(par2 + 1, par3, par4))) {
                var9 = (double) (par2 + 1) + var6;
            }
            if (var8 == 5
                    && !level.getBlockState(new BlockPos(par2 - 1, par3, par4)).isCollisionShapeFullBlock(level, new BlockPos(par2 - 1, par3, par4))) {
                var9 = (double) (par2 + 0) - var6;
            }
            if (var9 >= (double) par2 && var9 <= (double) (par2 + 1) && var11 >= 0.0 && var11 <= (double) (par3 + 1) && var13 >= (double) par4
                    && var13 <= (double) (par4 + 1)) {
                continue;
            }
            level.addParticle(RED_DUST, var9, var11, var13, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.world.item.ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        if (dropExperience && pos.getY() < 40) {
            int j1 = 5 + level.getRandom().nextInt(5) + level.getRandom().nextInt(10);
            this.popExperience(level, pos, j1);
        }
    }
}
