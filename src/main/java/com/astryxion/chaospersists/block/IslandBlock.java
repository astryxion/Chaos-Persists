package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.registries.ForgeRegistries;

public class IslandBlock extends Block {
    private static final float HALF = 0.375f;
    private static final VoxelShape SHAPE =
            Shapes.box(0.5 - HALF, 0.0, 0.5 - HALF, 0.5 + HALF, 1.0, 0.5 + HALF);

    public IslandBlock() {
        this(0);
    }

    protected IslandBlock(int par1) {
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
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 40);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        this.runIslandSpawn(worldIn, pos.getX(), pos.getY(), pos.getZ(), rand);
    }

    @Override
    public void tick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource rand) {
        this.runIslandSpawn(worldIn, pos.getX(), pos.getY(), pos.getZ(), rand);
    }

    private void runIslandSpawn(Level world, int par2, int par3, int par4, RandomSource par5Random) {
        boolean isok;
        int n = 1 + par5Random.nextInt(3);
        int m = 64;
        if (ChaosPersists.IslandSizeFactor == 2) {
            m = 55;
        }
        if (ChaosPersists.IslandSizeFactor == 1) {
            m = 45;
        }
        for (int i = 0; i < n; ++i) {
            int height = 12 + par5Random.nextInt(m);
            isok = true;
            block1:
            for (int k = -10; k <= 10; ++k) {
                for (int j = -10; j <= 10; ++j) {
                    Block bid = world.getBlockState(new BlockPos(par2 + j, par3 + height, par4 + k)).getBlock();
                    if (bid == Blocks.AIR) {
                        continue;
                    }
                    isok = false;
                    continue block1;
                }
            }
            if (!isok) {
                continue;
            }
            if (par5Random.nextInt(25) == 1) {
                IslandBlock.spawnCreature(world, "Island", (double) par2, (double) (par3 + height), (double) par4);
                continue;
            }
            IslandBlock.spawnCreature(world, "IslandToo", (double) par2, (double) (par3 + height), (double) par4);
        }
        world.setBlock(new BlockPos(par2, par3, par4), Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(new BlockPos(par2, par3 + 1, par4), Blocks.AIR.defaultBlockState(), 2);
    }

    @Override
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
        if (worldIn.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            worldIn.addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    (float) pos.getX() + rand.nextFloat(),
                    (double) pos.getY() + rand.nextFloat(),
                    (float) pos.getZ() + rand.nextFloat(),
                    0.0,
                    0.0,
                    0.0);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return Collections.singletonList(new ItemStack(ChaosPersists.MyIslandBlock));
    }

    public static Entity spawnCreature(Level par0World, String par1, double par2, double par4, double par6) {
        ResourceLocation rl;
        if ("Island".equals(par1)) {
            rl = new ResourceLocation("chaospersists", "island");
        } else if ("IslandToo".equals(par1)) {
            rl = new ResourceLocation("chaospersists", "island_too");
        } else {
            rl = new ResourceLocation("chaospersists", par1.toLowerCase(Locale.ROOT));
        }
        EntityType<?> type = ForgeRegistries.ENTITY_TYPES.getValue(rl);
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
        }
        return var8;
    }

    /**
     * Same UX as {@link com.astryxion.chaospersists.item.ItemRandomDungeon}: Fortune, use on any block to place the island above if the space is valid.
     */
    public static class ItemIslandBlock extends BlockItem {

        public ItemIslandBlock(Block block, Item.Properties properties) {
            super(block, properties.stacksTo(1));
        }

        @Override
        public void onCraftedBy(ItemStack stack, Level world, Player player) {
            stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
        }

        @Override
        public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
            int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack);
            if (lvl <= 0) {
                stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
            }
        }

        @Override
        public InteractionResult useOn(
                net.minecraft.world.item.context.UseOnContext context) {
            Player player = context.getPlayer();
            Level world = context.getLevel();
            BlockPos placePos = context.getClickedPos().above();
            ItemStack stack = context.getItemInHand();
            BlockState placeState = ChaosPersists.MyIslandBlock.defaultBlockState();
            if (!world.isEmptyBlock(placePos) || !placeState.canSurvive(world, placePos)) {
                return InteractionResult.FAIL;
            }
            if (!world.isClientSide) {
                world.setBlock(placePos, placeState, 2);
                if (player != null && !player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
    }
}
