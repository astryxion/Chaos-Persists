package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ActionResultType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StepUp extends Item {

    public StepUp(int i) {
        super(new Item.Properties().stacksTo(16));
    }

    private static int stepOctantFromYaw(float yawDegrees) {
        float f = yawDegrees + 22.5f;
        f = (f % 360.0f + 360.0f) % 360.0f;
        return (int) (f / 45.0f);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getLevel();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        int deltax = 0;
        int deltaz = 0;
        int length = 33;
        int x = pos.getX();
        int y = pos.getY() + 1;
        int z = pos.getZ();
        switch (stepOctantFromYaw(player.yRot)) {
            case 0: deltax = 0; deltaz = 1; break;
            case 1: deltax = -1; deltaz = 1; break;
            case 2: deltax = -1; deltaz = 0; break;
            case 3: deltax = -1; deltaz = -1; break;
            case 4: deltax = 0; deltaz = -1; break;
            case 5: deltax = 1; deltaz = -1; break;
            case 6: deltax = 1; deltaz = 0; break;
            case 7: deltax = 1; deltaz = 1; break;
            default: break;
        }
        if (deltax == 0 && deltaz == 0) {
            return ActionResultType.FAIL;
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, 1.5f);
        if (world.isClientSide) {
            for (int var3 = 0; var3 < 6; ++var3) {
                world.addParticle(ParticleTypes.SMOKE, (double) ((float) x + world.random.nextFloat() - world.random.nextFloat()),
                        (double) ((float) y + world.random.nextFloat() + 1.0f),
                        (double) ((float) z + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.EXPLOSION, (double) ((float) x + world.random.nextFloat() - world.random.nextFloat()),
                        (double) ((float) y + world.random.nextFloat() + 1.0f),
                        (double) ((float) z + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
                world.addParticle(new net.minecraft.particles.RedstoneParticleData(1.0F, 0.0F, 0.0F, 1.0F), (double) ((float) x + world.random.nextFloat() - world.random.nextFloat()),
                        (double) ((float) y + world.random.nextFloat() + 1.0f),
                        (double) ((float) z + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
            }
            return ActionResultType.SUCCESS;
        }
        for (int k = 1; k < length && world.getBlockState(new BlockPos(x + k * deltax, y + k - 1, z + k * deltaz)).getBlock() == Blocks.AIR; ++k) {
            world.setBlock(new BlockPos(x + k * deltax, y + k - 1, z + k * deltaz), Blocks.COBBLESTONE.defaultBlockState(), 2);
            if ((k - 1) % 8 == 0 && world.getBlockState(new BlockPos(x + k * deltax, y + k, z + k * deltaz)).getBlock() == Blocks.AIR) {
                world.setBlock(new BlockPos(x + k * deltax, y + k, z + k * deltaz), ChaosPersists.ExtremeTorch.defaultBlockState(), 2);
            }
        }
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }
}
