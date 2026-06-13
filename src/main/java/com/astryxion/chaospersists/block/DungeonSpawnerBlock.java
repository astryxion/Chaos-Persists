package com.astryxion.chaospersists.block;



import com.astryxion.chaospersists.world.dimension.structure.BasiliskMaze;
import com.astryxion.chaospersists.world.dimension.structure.GenericDungeon;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.structure.RubyBirdDungeon;
import com.astryxion.chaospersists.util.Trees;
import java.util.Random;



import net.minecraft.block.Blocks;
import net.minecraft.item.Item; import net.minecraft.item.ItemStack;
import net.minecraft.world.World; import net.minecraft.world.server.ServerWorld; import net.minecraft.block.SugarCaneBlock; import net.minecraft.block.BlockState; import net.minecraft.block.AbstractBlock; import net.minecraft.block.Block; import net.minecraft.util.math.BlockPos; import net.minecraft.util.math.shapes.VoxelShape; import net.minecraft.util.math.shapes.VoxelShapes; import net.minecraft.world.IBlockReader; import net.minecraft.particles.ParticleTypes; import net.minecraftforge.api.distmarker.Dist; import net.minecraftforge.api.distmarker.OnlyIn; import java.util.Collections; import java.util.List; import net.minecraft.loot.LootContext; import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootParameters;

public class DungeonSpawnerBlock
extends SugarCaneBlock {
    private static final float var3 = 0.375f;

    public DungeonSpawnerBlock() {
        this(0.9F);
    }

    public DungeonSpawnerBlock(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.SUGAR_CANE).randomTicks().noCollission()
                .lightLevel(state -> (int) (lightLevel * 15.0F)));
    }

    protected DungeonSpawnerBlock(int par1) {
        this(0.9F);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, net.minecraft.util.math.shapes.ISelectionContext context) { return VoxelShapes.box(0.5 - var3, 0.0, 0.5 - var3, 0.5 + var3, 1.0, 0.5 + var3);
    }

    @Override
    public boolean canSurvive(BlockState state, net.minecraft.world.IWorldReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).isFaceSturdy(world, pos.below(), net.minecraft.util.Direction.UP);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World world, BlockPos pos, Random rand) {
        for (int j1 = 0; j1 < 5; ++j1) {
            world.addParticle(ParticleTypes.FIREWORK,
                    (double) ((float) pos.getX() + world.random.nextFloat()),
                    (double) pos.getY() + (double) world.random.nextFloat(),
                    (double) ((float) pos.getZ() + world.random.nextFloat()),
                    (double) (world.random.nextFloat() - world.random.nextFloat()) / 4.0,
                    (double) world.random.nextFloat() / 2.0,
                    (double) (world.random.nextFloat() - world.random.nextFloat()) / 4.0);
        }
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            ((ServerWorld) world).getBlockTicks().scheduleTick(pos, this, 400);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
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
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(ChaosPersists.RandomDungeon));
    }
}


