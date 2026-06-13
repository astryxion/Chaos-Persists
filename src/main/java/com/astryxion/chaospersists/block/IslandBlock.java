/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  com.astryxion.chaospersists.IslandBlock
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.SugarCaneBlock
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.block;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.item.ItemUseContext;
import net.minecraft.entity.Entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Locale;

/*
 * Exception performing whole class analysis ignored.
 */
public class IslandBlock
extends SugarCaneBlock {
    public IslandBlock() {
        this(0.9F);
    }

    public IslandBlock(float lightLevel) {
        super(AbstractBlock.Properties.copy(Blocks.SUGAR_CANE).randomTicks().noCollission()
                .lightLevel(state -> (int) (lightLevel * 15.0F)));
    }

    protected IslandBlock(int par1) {
        this(0.9F);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context) {
        float var3 = 0.375f;
        return VoxelShapes.box(0.5 - var3, 0.0, 0.5 - var3, 0.5 + var3, 1.0, 0.5 + var3);
    }

    @Override
    public boolean canSurvive(BlockState state, net.minecraft.world.IWorldReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.below()).isFaceSturdy(worldIn, pos.below(), net.minecraft.util.Direction.UP);
    }

    @Override
    public void onPlace(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            ((ServerWorld) world).getBlockTicks().scheduleTick(pos, this, 40);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        this.runIslandSpawn(worldIn, pos.getX(), pos.getY(), pos.getZ(), rand);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        this.runIslandSpawn(worldIn, pos.getX(), pos.getY(), pos.getZ(), rand);
    }

    private void runIslandSpawn(World world, int par2, int par3, int par4, Random par5Random) {
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
                IslandBlock.spawnCreature(world, "Island", (double)par2, (double)(par3 + height), (double)par4);
                continue;
            }
            IslandBlock.spawnCreature(world, "IslandToo", (double)par2, (double)(par3 + height), (double)par4);
        }
        world.setBlock(new BlockPos(par2, par3, par4), Blocks.AIR.defaultBlockState(), 2);
        world.setBlock(new BlockPos(par2, par3 + 1, par4), Blocks.AIR.defaultBlockState(), 2);
    }
    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (worldIn.random.nextInt(20) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 20; ++j1) {
            worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER,
                    (double)((float)pos.getX() + worldIn.random.nextFloat()),
                    (double)pos.getY() + (double)worldIn.random.nextFloat(),
                    (double)((float)pos.getZ() + worldIn.random.nextFloat()),
                    0.0, 0.0, 0.0);
        }
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(ChaosPersists.MyIslandBlock);
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        return ChaosPersists.MyIslandBlock.asItem();
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        ResourceLocation rl;
        if ("Island".equals(par1)) {
            rl = new ResourceLocation("chaospersists", "island");
        } else if ("IslandToo".equals(par1)) {
            rl = new ResourceLocation("chaospersists", "island_too");
        } else {
            rl = new ResourceLocation("chaospersists", par1.toLowerCase(Locale.ROOT));
        }
        EntityType<?> _et = ForgeRegistries.ENTITIES.getValue(rl); var8 = _et != null ? _et.create(par0World) : null;
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
            if (var8 instanceof LivingEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
            }
        }
        return var8;
    }

    /**
     * Same UX as {@link com.astryxion.chaospersists.item.ItemRandomDungeon}: Fortune, and use on stone/cobble/grass/dirt (y≥40) to place the island block above.
     */
    public static class ItemIslandBlock extends BlockItem {

        public ItemIslandBlock(Block block) {
            super(block, new Item.Properties().stacksTo(1));
        }

        public ItemIslandBlock(Block block, Item.Properties properties) {
            super(block, properties.stacksTo(1));
        }

        @Override
        public void onCraftedBy(ItemStack stack, World world, PlayerEntity player) {
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack) <= 0) {
                stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
            }
        }

        @Override
        public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack) <= 0) {
                stack.enchant(Enchantments.BLOCK_FORTUNE, 2);
            }
        }

        @Override
        public ActionResultType useOn(ItemUseContext context) {
            PlayerEntity player = context.getPlayer();
            World world = context.getLevel();
            BlockPos pos = context.getClickedPos();
            if (player == null) {
                return ActionResultType.FAIL;
            }
            ItemStack stack = context.getItemInHand();
            Block clicked = world.getBlockState(pos).getBlock();
            if (clicked != Blocks.STONE && clicked != Blocks.COBBLESTONE && clicked != Blocks.GRASS_BLOCK && clicked != Blocks.DIRT) {
                return ActionResultType.FAIL;
            }
            if (world.dimension() == World.OVERWORLD && pos.getY() < 40) {
                return ActionResultType.FAIL;
            }
            if (!world.isClientSide) {
                BlockPos up = pos.above();
                if (!world.isEmptyBlock(up) || !((IslandBlock) ChaosPersists.MyIslandBlock).canSurvive(ChaosPersists.MyIslandBlock.defaultBlockState(), world, up)) {
                    return ActionResultType.FAIL;
                }
                world.setBlock(up, ChaosPersists.MyIslandBlock.defaultBlockState(), 2);
            }
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            return ActionResultType.SUCCESS;
        }
    }
}

