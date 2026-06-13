/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.api.distmarker.Dist
 *  net.minecraftforge.api.distmarker.OnlyIn
 *  com.astryxion.chaospersists.BlockCrystalPlant
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.block.GrassBlock
 *  net.minecraft.block.SugarCaneBlock
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.block;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.block.material.Material;

public class BlockCrystalPlant
extends BushBlock {
    protected static final AxisAlignedBB SAPLING_AABB =
        new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 0.8D, 0.9D);

    public BlockCrystalPlant() { this(0); }
    protected BlockCrystalPlant(int par1) {
        super(AbstractBlock.Properties.of(Material.PLANT).randomTicks().noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader source, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.create(SAPLING_AABB);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return false;
    }

    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
        Block bid = par1World.getBlockState(new BlockPos(par2, par3 - 1, par4)).getBlock();
        if (bid == Blocks.AIR) {
            return false;
        }
        if (bid == Blocks.GRASS_BLOCK || bid == Blocks.DIRT || bid == Blocks.FARMLAND || bid == ChaosPersists.CrystalGrass) {
            return true;
        }
        return false;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState state, World par1World, BlockPos pos, Random par5Random) {
        randomDisplayTick(par1World, pos.getX(), pos.getY(), pos.getZ(), par5Random);
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.random.nextInt(30) != 1) {
            return;
        }
        for (int j1 = 0; j1 < 10; ++j1) {
            par1World.addParticle(ParticleTypes.HAPPY_VILLAGER, (double)((float)par2 + par1World.random.nextFloat()), (double)par3 + (double)par1World.random.nextFloat(), (double)((float)par4 + par1World.random.nextFloat()), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld par1World, BlockPos pos, Random par5Random) {
        updateTick(par1World, pos.getX(), pos.getY(), pos.getZ(), par5Random);
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (par1World.isClientSide) {
            return;
        }
        if (par1World.random.nextInt(5) != 1) {
            return;
        }
        par1World.setBlock(new BlockPos(par2, par3, par4), Blocks.AIR.defaultBlockState(), 2);
        if (this == ChaosPersists.MyCrystalPlant) {
            this.TallCrystalTree(par1World, par2, par3, par4);
        }
        if (this == ChaosPersists.MyCrystalPlant2) {
            this.ScragglyCrystalTreeWithBranches(par1World, par2, par3, par4);
        }
        if (this == ChaosPersists.MyCrystalPlant3) {
            this.TallCrystalTreeBlue(par1World, par2, par3, par4);
        }
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        if (this == ChaosPersists.MyCrystalPlant) {
            return new ItemStack(ChaosPersists.MyCrystalPlant);
        }
        if (this == ChaosPersists.MyCrystalPlant2) {
            return new ItemStack(ChaosPersists.MyCrystalPlant2);
        }
        return new ItemStack(ChaosPersists.MyCrystalPlant3);
    }

    public Item getItemDropped(int par1, Random par2Random, int par3) {
        if (this == ChaosPersists.MyCrystalPlant) {
            return ChaosPersists.MyCrystalPlant.asItem();
        }
        if (this == ChaosPersists.MyCrystalPlant2) {
            return ChaosPersists.MyCrystalPlant2.asItem();
        }
        return ChaosPersists.MyCrystalPlant3.asItem();
    }

    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public int idPicked(World par1World, int par2, int par3, int par4) {
        return 0;
    }

    protected int getSeedItem() {
        return 0;
    }

    protected int getCropItem() {
        return 0;
    }

    public void TallCrystalTree(World world, int x, int y, int z) {
        int n;
        int m;
        int k;
        Block bid;
        int i = 10 + world.random.nextInt(12);
        int j = i + world.random.nextInt(18);
        for (k = 0; k < i; ++k) {
            bid = world.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + k), (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
        }
        y += i - 1;
        for (k = i; k < j && ((bid = world.getBlockState(new BlockPos(x, ++y, z)).getBlock()) == Blocks.AIR || bid == ChaosPersists.MyCrystalTreeLog || bid == ChaosPersists.MyCrystalLeaves); ++k) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            if (k % 4 != 0) continue;
            for (m = -1; m < 2; ++m) {
                for (n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves, (int)0, (int)2);
                }
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            }
        }
        for (m = -3; m < 4; ++m) {
            for (n = -3; n < 4; ++n) {
                bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves, (int)0, (int)2);
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves, (int)0, (int)2);
            }
        }
    }

    public void makeScragglyCrystalBranch(World world, int x, int y, int z, int len, int biasx, int biasz) {
        for (int k = 0; k < len; ++k) {
            Block bid;
            int iy;
            int ix = world.random.nextInt(2) - world.random.nextInt(2) + biasx;
            int iz = world.random.nextInt(2) - world.random.nextInt(2) + biasz;
            if (ix > 1) {
                ix = 1;
            }
            if (ix < -1) {
                ix = -1;
            }
            if (iz > 1) {
                iz = 1;
            }
            if (iz < -1) {
                iz = -1;
            }
            if ((bid = world.getBlockState(new BlockPos(x += ix, y += (iy = world.random.nextInt(3) > 0 ? 1 : 0), z += iz)).getBlock()) != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves2, (int)0, (int)2);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + 1), (int)z, (Block)ChaosPersists.MyCrystalLeaves2, (int)0, (int)2);
        }
    }

    public void ScragglyCrystalTreeWithBranches(World world, int x, int y, int z) {
        int k;
        Block bid;
        int i = 1 + world.random.nextInt(2);
        int j = i + world.random.nextInt(8);
        for (k = 0; k < i; ++k) {
            bid = world.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + k), (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
        }
        y += i - 1;
        for (k = i; k < j; ++k) {
            int ix = world.random.nextInt(2) - world.random.nextInt(2);
            int iz = world.random.nextInt(2) - world.random.nextInt(2);
            int iy = world.random.nextInt(4) > 0 ? 1 : 0;
            bid = world.getBlockState(new BlockPos(x += ix, y += iy, z += iz)).getBlock();
            if (bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves2) break;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            if (world.random.nextInt(4) == 1) {
                this.makeScragglyCrystalBranch(world, x, y, z, world.random.nextInt(1 + j - k), world.random.nextInt(2) - world.random.nextInt(2), world.random.nextInt(2) - world.random.nextInt(2));
            }
            for (int m = -1; m < 2; ++m) {
                for (int n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves2, (int)0, (int)2);
                }
            }
            if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock()) != Blocks.AIR) continue;
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + 1), (int)z, (Block)ChaosPersists.MyCrystalLeaves2, (int)0, (int)2);
        }
    }

    public void TallCrystalTreeBlue(World world, int x, int y, int z) {
        int n;
        int m;
        int k;
        Block bid;
        int i = 5 + world.random.nextInt(6);
        int j = 2 + i + world.random.nextInt(12);
        for (k = 0; k < i; ++k) {
            bid = world.getBlockState(new BlockPos(x, y + k, z)).getBlock();
            if (k >= 1 && bid != Blocks.AIR && bid != ChaosPersists.MyCrystalTreeLog && bid != ChaosPersists.MyCrystalLeaves3) {
                return;
            }
            ChaosPersists.setBlockFast((World)world, (int)x, (int)(y + k), (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
        }
        y += i - 1;
        for (k = i; k < j && ((bid = world.getBlockState(new BlockPos(x, ++y, z)).getBlock()) == Blocks.AIR || bid == ChaosPersists.MyCrystalTreeLog || bid == ChaosPersists.MyCrystalLeaves3); ++k) {
            ChaosPersists.setBlockFast((World)world, (int)x, (int)y, (int)z, (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            if (k % 3 != 0) continue;
            for (m = -1; m < 2; ++m) {
                for (n = -1; n < 2; ++n) {
                    if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                    ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves3, (int)0, (int)2);
                }
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                if (world.random.nextInt(2) != 1 || (bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock()) != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalTreeLog, (int)0, (int)2);
            }
        }
        for (m = -3; m < 4; ++m) {
            for (n = -3; n < 4; ++n) {
                bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves3, (int)0, (int)2);
            }
        }
        ++y;
        for (m = -1; m < 2; ++m) {
            for (n = -1; n < 2; ++n) {
                bid = world.getBlockState(new BlockPos(x + m, y, z + n)).getBlock();
                if (bid != Blocks.AIR) continue;
                ChaosPersists.setBlockFast((World)world, (int)(x + m), (int)y, (int)(z + n), (Block)ChaosPersists.MyCrystalLeaves3, (int)0, (int)2);
            }
        }
    }}

