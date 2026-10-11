package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.loot.DatapackLoot;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
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

public class DungeonSpawnerBlock extends Block {
    private static final float HALF = 0.375f;
    private static final VoxelShape SHAPE =
            Shapes.box(0.5 - HALF, 0.0, 0.5 - HALF, 0.5 + HALF, 1.0, 0.5 + HALF);

    public DungeonSpawnerBlock() {
        this(0);
    }

    protected DungeonSpawnerBlock(int par1) {
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
    public void animateTick(BlockState stateIn, Level par1World, BlockPos pos, RandomSource par5Random) {
        for (int j1 = 0; j1 < 5; ++j1) {
            par1World.addParticle(
                    ParticleTypes.FIREWORK,
                    (float) pos.getX() + par1World.random.nextFloat(),
                    (double) pos.getY() + par1World.random.nextFloat(),
                    (float) pos.getZ() + par1World.random.nextFloat(),
                    (double) (par1World.random.nextFloat() - par1World.random.nextFloat()) / 4.0,
                    (double) par1World.random.nextFloat() / 2.0,
                    (double) (par1World.random.nextFloat() - par1World.random.nextFloat()) / 4.0);
        }
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 400);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource par5Random) {
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(pos.above(), Blocks.AIR.defaultBlockState(), 2);
        int clickedX = pos.getX();
        int clickedY = pos.getY();
        int clickedZ = pos.getZ();
        int type = world.random.nextInt(50);
        if (type == 0) {
            ChaosPersists.chaospersistsTrees.FairyTree(world, clickedX, clickedY, clickedZ);
        }
        if (type == 1) {
            ChaosPersists.chaospersistsTrees.FairyCastleTree(world, clickedX, clickedY, clickedZ);
        }
        if (type == 2) {
            ChaosPersists.MyDungeon.makeEnormousCastle(world, clickedX, clickedY, clickedZ);
        }
        if (type == 3) {
            ChaosPersists.MyDungeon.makeRotatorStation(world, clickedX, clickedY, clickedZ);
        }
        if (type == 4) {
            ChaosPersists.MyDungeon.makeBeeHive(world, clickedX, clickedY, clickedZ);
        }
        if (type == 5) {
            ChaosPersists.MyDungeon.makeHauntedHouse(world, clickedX, clickedY, clickedZ);
        }
        if (type == 6) {
            ChaosPersists.MyDungeon.makeMantisHive(world, clickedX, clickedY, clickedZ);
        }
        if (type == 7) {
            ChaosPersists.MyDungeon.makeKyuubiDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 8) {
            ChaosPersists.MyDungeon.makeSmallBeeHive(world, clickedX, clickedY, clickedZ);
        }
        if (type == 9) {
            ChaosPersists.MyDungeon.makeShadowDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 10) {
            ChaosPersists.MyDungeon.makeAlienWTFDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 11) {
            ChaosPersists.MyDungeon.makeEnderKnightDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 12) {
            ChaosPersists.MyDungeon.makePlayPool(world, clickedX, clickedY, clickedZ);
        }
        if (type == 13) {
            ChaosPersists.MyDungeon.makeWaterDragonLair(world, clickedX, clickedY, clickedZ);
        }
        if (type == 14) {
            ChaosPersists.MyDungeon.makeCloudSharkDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 15) {
            ChaosPersists.MyDungeon.makeLeafMonsterDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 16) {
            ChaosPersists.MyDungeon.makeMiniDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 17) {
            ChaosPersists.MyDungeon.makeGoldFishBowl(world, clickedX, clickedY, clickedZ);
        }
        if (type == 18) {
            ChaosPersists.MyDungeon.makeEnderReaperGraveyard(world, clickedX, clickedY, clickedZ);
        }
        if (type == 19) {
            ChaosPersists.MyDungeon.makeSpitBugLair(world, clickedX, clickedY, clickedZ);
        }
        if (type == 20) {
            ChaosPersists.MyDungeon.makeIgloo(world, clickedX, clickedY, clickedZ);
        }
        if (type == 21) {
            ChaosPersists.MyDungeon.makeDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 22) {
            ChaosPersists.RubyDungeon.makeDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 23) {
            ChaosPersists.BMaze.buildBasiliskMaze(world, clickedX, clickedY, clickedZ);
        }
        if (type == 24) {
            ChaosPersists.MyDungeon.makeEnderDragonHospital(world, clickedX, clickedY, clickedZ);
        }
        if (type == 25) {
            ChaosPersists.MyDungeon.makeCrystalHauntedHouse(world, clickedX, clickedY, clickedZ);
        }
        if (type == 26) {
            ChaosPersists.MyDungeon.makeBouncyCastle(world, clickedX, clickedY, clickedZ);
        }
        if (type == 27) {
            ChaosPersists.MyDungeon.makeEnderCastle(world, clickedX, clickedY, clickedZ);
        }
        if (type == 28) {
            ChaosPersists.MyDungeon.makeDamselInDistress(world, clickedX, clickedY, clickedZ);
        }
        if (type == 29) {
            ChaosPersists.MyDungeon.makeIncaPyramid(world, clickedX, clickedY, clickedZ);
        }
        if (type == 30) {
            ChaosPersists.MyDungeon.makeRobotLab(world, clickedX, clickedY, clickedZ);
        }
        if (type == 31) {
            ChaosPersists.MyDungeon.makeKingAltar(world, clickedX, clickedY, clickedZ);
        }
        if (type == 32) {
            ChaosPersists.MyDungeon.makeLeonNest(world, clickedX, clickedY, clickedZ);
        }
        if (type == 33) {
            ChaosPersists.MyDungeon.makeCrystalBattleTower(world, clickedX, clickedY, clickedZ);
        }
        if (type == 34) {
            ChaosPersists.MyDungeon.makeCephadromeAltar(world, clickedX, clickedY, clickedZ);
        }
        if (type == 35) {
            ChaosPersists.MyDungeon.makeGirlfriendIsland(world, clickedX, clickedY, clickedZ);
        }
        if (type == 36) {
            ChaosPersists.MyDungeon.makeGreenhouseDungeon(world, clickedX, clickedY, clickedZ);
        }
        if (type == 37) {
            ChaosPersists.MyDungeon.makeMonsterIsland(world, clickedX, clickedY, clickedZ);
        }
        if (type == 38) {
            ChaosPersists.MyDungeon.makeNightmareRookery(world, clickedX, clickedY, clickedZ);
        }
        if (type == 39) {
            ChaosPersists.MyDungeon.makeStinkyHouse(world, clickedX, clickedY, clickedZ);
        }
        if (type == 40) {
            ChaosPersists.MyDungeon.makeRubberDuckyPond(world, clickedX, clickedY, clickedZ);
        }
        if (type == 41) {
            ChaosPersists.MyDungeon.makeWhiteHouse(world, clickedX, clickedY, clickedZ);
        }
        if (type == 42) {
            ChaosPersists.MyDungeon.makeQueenAltar(world, clickedX, clickedY, clickedZ);
        }
        if (type == 43) {
            ChaosPersists.MyDungeon.makeFrogPond(world, clickedX, clickedY + 1, clickedZ);
        }
        if (type == 44) {
            ChaosPersists.MyDungeon.makePumpkin(world, clickedX, clickedY + 1, clickedZ);
        }
        if (type == 45) {
            ChaosPersists.MyDungeon.makeRoundRotator(world, clickedX, clickedY + 1, clickedZ);
        }
        if (type == 46) {
            ChaosPersists.MyDungeon.makeRainbow(world, clickedX, clickedY, clickedZ);
        }
        if (type == 47) {
            ChaosPersists.MyDungeon.makeEnormousCastleQ(world, clickedX, clickedY, clickedZ);
        }
        if (type == 48) {
            ChaosPersists.MyDungeon.makeSpiderHangout(world, clickedX, clickedY, clickedZ);
        }
        if (type == 49) {
            ChaosPersists.MyDungeon.makeRedAntHangout(world, clickedX, clickedY, clickedZ);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        var datapackDrops = DatapackLoot.tryBlockTable(this, state, builder);
        if (datapackDrops != null) {
            return datapackDrops;
        }
        return Collections.singletonList(new ItemStack(ChaosPersists.RandomDungeon));
    }
}
