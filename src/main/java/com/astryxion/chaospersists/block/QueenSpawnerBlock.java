package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheQueen;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;

public class QueenSpawnerBlock extends SugarCaneBlock {

    public QueenSpawnerBlock() {
        this(0.9F);
    }

    public QueenSpawnerBlock(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.SUGAR_CANE)
                .randomTicks()
                .noCollission()
                .lightLevel(state -> (int) (lightLevel * 15.0F)));
    }

    protected QueenSpawnerBlock(int par1) {
        this(0.9F);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, net.minecraft.util.math.shapes.ISelectionContext context) {
        float var3 = 0.375f;
        return VoxelShapes.box(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, 1.0f, 0.5f + var3);
    }

    @Override
    public boolean canSurvive(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            worldIn.addParticle(ParticleTypes.FIREWORK,
                    (double) ((float) pos.getX() + worldIn.random.nextFloat()),
                    (double) pos.getY() + (double) worldIn.random.nextFloat(),
                    (double) ((float) pos.getZ() + worldIn.random.nextFloat()),
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            ((ServerWorld) world).getBlockTicks().scheduleTick(pos, this, 100);
        }
    }

    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClientSide && world.getBlockState(pos.above()).getBlock() == this) {
            world.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (ChaosPersists.TheQueenEnable != 0) {
            spawnTheQueen(world, (double) pos.getX(), (double) (pos.getY() + 8), (double) pos.getZ());
        }
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(ChaosPersists.MyQueenSpawnerBlock));
    }

    public static Entity spawnTheQueen(World world, double x, double y, double z) {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "the_queen"));
        if (type == null) {
            return null;
        }
        Entity entity = type.create(world);
        if (entity != null) {
            entity.moveTo(x, y, z, world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(entity);
            if (entity instanceof LivingEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) entity);
            }
            if (entity instanceof TheQueen) {
                ((TheQueen) entity).setGuardMode(1);
            }
        }
        return entity;
    }
}
