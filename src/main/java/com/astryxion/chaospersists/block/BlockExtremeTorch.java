package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class BlockExtremeTorch extends TorchBlock {

    public BlockExtremeTorch() {
        this(1.0F);
    }

    public BlockExtremeTorch(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.TORCH).lightLevel(state -> (int) lightLevel), ParticleTypes.FLAME);
    }

    @Override
    public String getDescriptionId() {
        return Util.makeDescriptionId("block", this.getRegistryName());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        // Floor torch (TorchBlock) has no FACING — use vanilla torch particle offsets.
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(ParticleTypes.ENTITY_EFFECT, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public void setPlacedBy(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        int par2 = pos.getX();
        int par3 = pos.getY();
        int par4 = pos.getZ();
        int x = par2;
        int y = par3;
        int z = par4;
        boolean found = false;

        if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == ChaosPersists.MyEyeOfEnderBlock) {
            block0:
            for (int tries = 0; tries < 100 && !found; ++tries) {
                x = world.random.nextInt(2) == 0 ? par2 + 4 + world.random.nextInt(3) - world.random.nextInt(3) : par2 - 4 + world.random.nextInt(3) - world.random.nextInt(3);
                z = world.random.nextInt(2) == 0 ? par4 + 4 + world.random.nextInt(3) - world.random.nextInt(3) : par4 - 4 + world.random.nextInt(3) - world.random.nextInt(3);
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
                    spawnCreature(world, new ResourceLocation("chaospersists", "cephadrome"), (double) x + 0.5D, (double) y + 0.01D, (double) z + 0.5D);
                } else {
                    for (int var3 = 0; var3 < 16; ++var3) {
                        world.addParticle(ParticleTypes.SMOKE, (double) ((float) par2 + world.random.nextFloat() - world.random.nextFloat()), (double) ((float) par3 + world.random.nextFloat()), (double) ((float) par4 + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
                        world.addParticle(ParticleTypes.EXPLOSION, (double) ((float) par2 + world.random.nextFloat() - world.random.nextFloat()), (double) ((float) par3 + world.random.nextFloat()), (double) ((float) par4 + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
                        world.addParticle(ParticleTypes.ENTITY_EFFECT, (double) ((float) par2 + world.random.nextFloat() - world.random.nextFloat()), (double) ((float) par3 + world.random.nextFloat()), (double) ((float) par4 + world.random.nextFloat() - world.random.nextFloat()), 0.0, 0.0, 0.0);
                    }
                }
                if (placer != null) {
                    world.playSound(null, placer.getX(), placer.getY(), placer.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, world.random.nextFloat() * 0.2f + 0.9f);
                } else {
                    world.playSound(null, (double) par2, (double) par3, (double) par4, SoundEvents.GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, world.random.nextFloat() * 0.2f + 0.9f);
                }
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
        super.setPlacedBy(world, pos, state, placer, stack);
    }

    private static Entity spawnCreature(World world, ResourceLocation entityId, double px, double py, double pz) {
        Entity entity = null;
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(entityId);
        if (entityType != null) {
            entity = entityType.create(world);
        }
        if (entity != null) {
            entity.moveTo(px, py, pz, world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(entity);
            if (entity instanceof LivingEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) entity);
            }
        }
        return entity;
    }
}
