package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.TheKing;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

public class KingSpawnerBlock extends Block {
    private static final float HALF = 0.375f;
    private static final VoxelShape SHAPE =
            Shapes.box(0.5f - HALF, 0.0, 0.5f - HALF, 0.5f + HALF, 1.0, 0.5f + HALF);

    public KingSpawnerBlock() {
        this(0);
    }

    protected KingSpawnerBlock(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of().mapColor(MapColor.PLANT).noCollission().randomTicks().noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).isSolid();
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (worldIn.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            worldIn.addParticle(
                    ParticleTypes.FIREWORK,
                    (float) pos.getX() + rand.nextFloat(),
                    (double) pos.getY() + rand.nextFloat(),
                    (float) pos.getZ() + rand.nextFloat(),
                    0.0,
                    0.0,
                    0.0);
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!level.isClientSide) {
            level.scheduleTick(pos, this, 100);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && level.getBlockState(pos.above()).is(this)) {
            level.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (ChaosPersists.TheKingEnable != 0) {
            spawnTheKing(level, (double) pos.getX(), (double) (pos.getY() + 8), (double) pos.getZ());
        }
        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        level.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return Collections.singletonList(new ItemStack(ChaosPersists.MyKingSpawnerBlock));
    }

    public static Entity spawnTheKing(Level par0World, double par2, double par4, double par6) {
        EntityType<?> type =
                ForgeRegistries.ENTITY_TYPES.getValue(new ResourceLocation("chaospersists", "the_king"));
        if (type == null) {
            return null;
        }
        Entity var8 = type.create(par0World);
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.getRandom().nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            if (var8 instanceof LivingEntity living) {
                MyUtils.playAmbientSound(living);
            }
            if (var8 instanceof TheKing king) {
                king.setGuardMode(1);
            }
        }
        return var8;
    }

}
