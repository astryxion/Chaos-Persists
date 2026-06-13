package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityAnt;
import com.astryxion.chaospersists.entity.EntityRedAnt;
import com.astryxion.chaospersists.entity.EntityRainbowAnt;
import com.astryxion.chaospersists.entity.EntityUnstableAnt;
import com.astryxion.chaospersists.entity.Termite;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Random;

import net.minecraft.block.GrassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import com.astryxion.chaospersists.entity.RockBase;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeColors;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ResourceLocation;

public class AntBlock extends GrassBlock {

    public AntBlock(int par1) {
        super(AbstractBlock.Properties.copy(Blocks.GRASS_BLOCK).randomTicks());
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        if (world.getBlockState(pos.above()).getBlock() != Blocks.AIR) {
            return;
        }
        if (!world.isDay()) {
            return;
        }

        String mobName = getMobNameForBlock();
        if (mobName == null) {
            return;
        }

        Class<? extends Entity> entityClass = getEntityClassForBlock();
        if (entityClass == null) {
            return;
        }

        int radius = 16;
        AxisAlignedBB aabb = new AxisAlignedBB(
                pos.getX() - radius, 0.0D, pos.getZ() - radius,
                pos.getX() + radius, 200.0D, pos.getZ() + radius);

        List<Entity> nearby = world.getEntitiesOfClass(entityClass, aabb);
        if (nearby.size() > 20) {
            return;
        }

        int count = rand.nextInt(6) + 2;
        for (int i = 0; i < count; i++) {
            spawnCreature(world, mobName, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5);
        }
    }

    private String getMobNameForBlock() {
        if (this == ChaosPersists.MyAntBlock) {
            return ChaosPersists.BlackAntEnable != 0 ? "Ant" : null;
        }
        if (this == ChaosPersists.MyRedAntBlock) {
            return ChaosPersists.RedAntEnable != 0 ? "Red Ant" : null;
        }
        if (this == ChaosPersists.MyRainbowAntBlock) {
            return ChaosPersists.RainbowAntEnable != 0 ? "Rainbow Ant" : null;
        }
        if (this == ChaosPersists.MyUnstableAntBlock) {
            return ChaosPersists.UnstableAntEnable != 0 ? "Unstable Ant" : null;
        }
        if (this == ChaosPersists.TermiteBlock) {
            return ChaosPersists.TermiteEnable != 0 ? "Termite" : null;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Entity> getEntityClassForBlock() {
        if (this == ChaosPersists.MyAntBlock) return EntityAnt.class;
        if (this == ChaosPersists.MyRedAntBlock) return EntityRedAnt.class;
        if (this == ChaosPersists.MyRainbowAntBlock) return EntityRainbowAnt.class;
        if (this == ChaosPersists.MyUnstableAntBlock) return EntityUnstableAnt.class;
        if (this == ChaosPersists.TermiteBlock) return Termite.class;
        return null;
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    public static Entity spawnCreature(World world, String name, double x, double y, double z) {
        ResourceLocation loc = new ResourceLocation("chaospersists",
                name.toLowerCase().replace(" ", "_"));
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(loc);
        if (type == null) {
            return null;
        }
        Entity entity = type.create(world);
        if (entity != null) {
            entity.moveTo(x, y, z, world.random.nextFloat() * 360.0F, 0.0F);
            world.addFreshEntity(entity);
            if (entity instanceof LivingEntity) {
                RockBase.playSpawnAmbientSound((LivingEntity) entity);
            }
        }
        return entity;
    }

    @OnlyIn(Dist.CLIENT)
    public int getBlockColor() {
        return GrassColors.get(0.5F, 1.0F);
    }

    @OnlyIn(Dist.CLIENT)
    public int getRenderColor(int meta) {
        return this.getBlockColor();
    }

    @OnlyIn(Dist.CLIENT)
    public int colorMultiplier(IBlockReader world, BlockPos pos, int renderPass) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos sample = pos.offset(dx, 0, dz);
                Biome biome = ((net.minecraft.world.IWorldReader) world).getBiome(sample);
                int color = biome.getGrassColor((float) sample.getX(), (float) sample.getZ());
                r += (color >> 16) & 255;
                g += (color >> 8) & 255;
                b += color & 255;
            }
        }
        return ((r / 9) << 16) | ((g / 9) << 8) | (b / 9);
    }
}
