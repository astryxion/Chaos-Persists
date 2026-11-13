package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.joml.Vector3f;

public class StepUp extends Item {
    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public StepUp(int i) {
        super(new Item.Properties().stacksTo(16));
    }

    private static int stepOctantFromYaw(float yawDegrees) {
        float f = yawDegrees + 22.5f;
        f = (f % 360.0f + 360.0f) % 360.0f;
        return (int) (f / 45.0f);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        int deltax = 0;
        int deltaz = 0;
        int length = 33;
        int x = pos.getX();
        int y = pos.getY() + 1;
        int z = pos.getZ();
        switch (stepOctantFromYaw(player.getYRot())) {
            case 0:
                deltax = 0;
                deltaz = 1;
                break;
            case 1:
                deltax = -1;
                deltaz = 1;
                break;
            case 2:
                deltax = -1;
                deltaz = 0;
                break;
            case 3:
                deltax = -1;
                deltaz = -1;
                break;
            case 4:
                deltax = 0;
                deltaz = -1;
                break;
            case 5:
                deltax = 1;
                deltaz = -1;
                break;
            case 6:
                deltax = 1;
                deltaz = 0;
                break;
            case 7:
                deltax = 1;
                deltaz = 1;
                break;
            default:
                break;
        }
        if (deltax == 0 && deltaz == 0) {
            return InteractionResult.FAIL;
        }
        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.GENERIC_EXPLODE.value(),
                SoundSource.PLAYERS,
                1.0f,
                1.5f);
        if (world.isClientSide) {
            for (int var3 = 0; var3 < 6; ++var3) {
                world.addParticle(
                        ParticleTypes.LARGE_SMOKE,
                        (float) x + world.random.nextFloat() - world.random.nextFloat(),
                        (float) y + world.random.nextFloat() + 1.0f,
                        (float) z + world.random.nextFloat() - world.random.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
                world.addParticle(
                        ParticleTypes.EXPLOSION,
                        (float) x + world.random.nextFloat() - world.random.nextFloat(),
                        (float) y + world.random.nextFloat() + 1.0f,
                        (float) z + world.random.nextFloat() - world.random.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
                world.addParticle(
                        RED_DUST,
                        (float) x + world.random.nextFloat() - world.random.nextFloat(),
                        (float) y + world.random.nextFloat() + 1.0f,
                        (float) z + world.random.nextFloat() - world.random.nextFloat(),
                        0.0,
                        0.0,
                        0.0);
            }
            return InteractionResult.SUCCESS;
        }
        Block bid;
        for (int k = 1;
                k < length
                        && (bid = world.getBlockState(new BlockPos(x + k * deltax, y + k - 1, z + k * deltaz)).getBlock())
                                == Blocks.AIR;
                ++k) {
            world.setBlock(
                    new BlockPos(x + k * deltax, y + k - 1, z + k * deltaz), Blocks.COBBLESTONE.defaultBlockState(), 2);
            if ((k - 1) % 8 != 0
                    || (bid = world.getBlockState(new BlockPos(x + k * deltax, y + k, z + k * deltaz)).getBlock())
                            != Blocks.AIR) {
                continue;
            }
            world.setBlock(
                    new BlockPos(x + k * deltax, y + k, z + k * deltaz),
                    ChaosPersists.ExtremeTorch.defaultBlockState(),
                    2);
        }
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }
}
