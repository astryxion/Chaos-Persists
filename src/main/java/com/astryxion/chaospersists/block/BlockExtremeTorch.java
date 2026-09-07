package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import org.joml.Vector3f;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockExtremeTorch extends ChaosDirectionalTorchBlock {

    /** OreSpawn 1.7.10 used light level 15; extend reach with a hidden light halo. */
    private static final int HALO_RADIUS = 3;
    private static final int HALO_TICK_RATE = 40;
    private static final BlockState HALO_LIGHT =
            Blocks.LIGHT.defaultBlockState().setValue(LightBlock.LEVEL, 15);

    private static final DustParticleOptions RED_DUST =
            new DustParticleOptions(new Vector3f(1.0f, 0.0f, 0.0f), 1.0f);

    public BlockExtremeTorch() {
        super(
                net.minecraft.world.level.block.Block.Properties.of().noCollission().instabreak().lightLevel(state -> 15).sound(net.minecraft.world.level.block.SoundType.WOOD),
                ParticleTypes.FLAME);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return 15;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            maintainLightHalo(level, pos);
            level.scheduleTick(pos, this, HALO_TICK_RATE);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.getBlockState(pos).is(this)) {
            maintainLightHalo(level, pos);
            level.scheduleTick(pos, this, HALO_TICK_RATE);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && !level.isClientSide) {
            clearLightHalo(level, pos);
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    private void maintainLightHalo(LevelAccessor level, BlockPos origin) {
        BlockPos.betweenClosedStream(
                        origin.offset(-HALO_RADIUS, -1, -HALO_RADIUS),
                        origin.offset(HALO_RADIUS, 1, HALO_RADIUS))
                .filter(target -> !target.equals(origin))
                .forEach(target -> {
                    BlockState current = level.getBlockState(target);
                    if (current.isAir() || current.is(Blocks.LIGHT)) {
                        if (!current.equals(HALO_LIGHT)) {
                            level.setBlock(target, HALO_LIGHT, Block.UPDATE_CLIENTS);
                        }
                    }
                });
    }

    private void clearLightHalo(Level level, BlockPos removedTorch) {
        BlockPos.betweenClosedStream(
                        removedTorch.offset(-HALO_RADIUS, -1, -HALO_RADIUS),
                        removedTorch.offset(HALO_RADIUS, 1, HALO_RADIUS))
                .forEach(target -> {
                    if (!level.getBlockState(target).is(Blocks.LIGHT)) {
                        return;
                    }
                    if (isSupportedByExtremeTorch(level, target, removedTorch)) {
                        return;
                    }
                    level.removeBlock(target, false);
                });
    }

    private boolean isSupportedByExtremeTorch(Level level, BlockPos lightPos, BlockPos ignoreTorch) {
        BlockPos min = lightPos.offset(-HALO_RADIUS, -1, -HALO_RADIUS);
        BlockPos max = lightPos.offset(HALO_RADIUS, 1, HALO_RADIUS);
        for (BlockPos torchPos : BlockPos.betweenClosed(min, max)) {
            if (torchPos.equals(ignoreTorch)) {
                continue;
            }
            if (level.getBlockState(torchPos).is(this)
                    && lightPos.distManhattan(torchPos) <= HALO_RADIUS + 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("block", BuiltInRegistries.BLOCK.getKey(this));
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, net.minecraft.util.RandomSource rand) {
        Direction facing = stateIn.getValue(FACING);
        double d0 = pos.getX() + 0.5D;
        double d1 = pos.getY() + 0.7D;
        double d2 = pos.getZ() + 0.5D;
        if (facing.getAxis().isHorizontal()) {
            Direction attach = facing.getOpposite();
            d0 += attach.getStepX() * 0.3D;
            d1 += 0.22D;
            d2 += attach.getStepZ() * 0.3D;
        } else if (facing == Direction.UP) {
            d1 -= 0.1D;
        } else {
            d1 += 0.15D;
        }
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(RED_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int x = par2;
        int y = par3;
        int z = par4;
        boolean found = false;

        Block eyeBlock =
                ForgeRegistries.BLOCKS.getValue(new net.minecraft.resources.ResourceLocation("chaospersists", "eyeofender"));
        if (eyeBlock != null && world.getBlockState(new BlockPos(x, y - 1, z)).is(eyeBlock)) {
            block0:
            for (int tries = 0; tries < 100 && !found; ++tries) {
                x = world.getRandom().nextInt(2) == 0
                        ? par2 + 4 + world.getRandom().nextInt(3) - world.getRandom().nextInt(3)
                        : par2 - 4 + world.getRandom().nextInt(3) - world.getRandom().nextInt(3);
                z = world.getRandom().nextInt(2) == 0
                        ? par4 + 4 + world.getRandom().nextInt(3) - world.getRandom().nextInt(3)
                        : par4 - 4 + world.getRandom().nextInt(3) - world.getRandom().nextInt(3);
                for (y = par3 - 2; y <= par3 + 2; ++y) {
                    BlockPos below = new BlockPos(x, y - 1, z);
                    BlockState belowState = world.getBlockState(below);
                    if (!belowState.isFaceSturdy(world, below, Direction.UP)
                            || world.getBlockState(new BlockPos(x, y, z)).getBlock() != Blocks.AIR
                            || world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() != Blocks.AIR) {
                        continue;
                    }
                    found = true;
                    continue block0;
                }
            }
            if (found) {
                if (!world.isClientSide) {
                    spawnCreature(world, new net.minecraft.resources.ResourceLocation("chaospersists", "cephadrome"), (double) x + 0.5D, (double) y + 0.01D, (double) z + 0.5D);
                } else {
                    for (int var3 = 0; var3 < 16; ++var3) {
                        world.addParticle(
                                ParticleTypes.SMOKE,
                                (float) par2 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                (float) par3 + world.getRandom().nextFloat(),
                                (float) par4 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                        world.addParticle(
                                ParticleTypes.EXPLOSION,
                                (float) par2 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                (float) par3 + world.getRandom().nextFloat(),
                                (float) par4 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                        world.addParticle(
                                RED_DUST,
                                (float) par2 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                (float) par3 + world.getRandom().nextFloat(),
                                (float) par4 + world.getRandom().nextFloat() - world.getRandom().nextFloat(),
                                0.0,
                                0.0,
                                0.0);
                    }
                }
                if (placer != null) {
                    world.playSound(null, placer.getX(), placer.getY(), placer.getZ(), SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);
                } else {
                    world.playSound(null, par2, par3, par4, SoundEvents.GENERIC_EXPLODE, SoundSource.BLOCKS, 1.0f, world.getRandom().nextFloat() * 0.2f + 0.9f);
                }
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
        super.setPlacedBy(world, pos, state, placer, stack);
    }

    private static Entity spawnCreature(Level world, net.minecraft.resources.ResourceLocation entityId, double px, double py, double pz) {
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(entityId);
        if (type == null || !(world instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity != null) {
            entity.moveTo(px, py, pz, world.getRandom().nextFloat() * 360.0f, 0.0f);
            serverLevel.addFreshEntity(entity);
            if (entity instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return entity;
    }
}
