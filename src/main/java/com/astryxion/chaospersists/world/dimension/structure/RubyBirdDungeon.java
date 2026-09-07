package com.astryxion.chaospersists.world.dimension.structure;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.WeightedRandomChestContent;
import com.astryxion.chaospersists.world.ore.OreRuby;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;

public class RubyBirdDungeon {
    private final WeightedRandomChestContent[] chestContentsList =
            new WeightedRandomChestContent[] {
                new WeightedRandomChestContent(ChaosPersists.CageEmpty, 0, 3, 10, 20),
                new WeightedRandomChestContent(ChaosPersists.MyRuby, 0, 2, 8, 15),
                new WeightedRandomChestContent(ChaosPersists.MyBacon, 0, 6, 12, 20),
                new WeightedRandomChestContent(ChaosPersists.MyButterCandy, 0, 6, 12, 20),
                new WeightedRandomChestContent(ChaosPersists.MyRubyPickaxe, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.MyRubyShovel, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.MyRubyHoe, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.MyRubyAxe, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.MyRubySword, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.RubyBody, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.RubyLegs, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.RubyHelmet, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.RubyBoots, 0, 1, 1, 15),
                new WeightedRandomChestContent(ChaosPersists.MyThunderStaff, 0, 1, 1, 5)
            };

    private static Block rubyOreBlock() {
        if (ChaosPersists.MyOreRubyBlock instanceof OreRuby oreRuby) {
            return oreRuby;
        }
        return (Block) (Object) ChaosPersists.MyOreRubyBlock;
    }

    private void setThisBlock(Level level, RandomSource rand, int cposx, int cposy, int cposz) {
        if (rand.nextInt(20) == 1) {
            this.FastSetBlock(level, cposx, cposy, cposz, rubyOreBlock());
        } else if (rand.nextInt(2) == 1) {
            this.FastSetBlock(level, cposx, cposy, cposz, Blocks.MOSSY_COBBLESTONE);
        } else {
            this.FastSetBlock(level, cposx, cposy, cposz, Blocks.COBBLESTONE);
        }
    }

    public void makeDungeon(Level level, int cposx, int cposy, int cposz) {
        int width = 10;
        int height = 5;
        RandomSource rand = level.getRandom();

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                for (int k = 0; k < width; ++k) {
                    this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, Blocks.AIR);
                }
            }
        }
        for (int i = 0; i < width; ++i) {
            int j = 0;
            for (int k = 0; k < width; ++k) {
                this.FastSetBlock(level, cposx + i, cposy + j, cposz + k, Blocks.MOSSY_COBBLESTONE);
            }
        }
        for (int i = 0; i < width; ++i) {
            int j = height - 1;
            for (int k = 0; k < width; ++k) {
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                int k = 0;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
                k = width - 1;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }
        for (int k = 0; k < width; ++k) {
            for (int j = 0; j < height; ++j) {
                int i = 0;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
                i = width - 1;
                this.setThisBlock(level, rand, cposx + i, cposy + j, cposz + k);
            }
        }

        BlockPos spawnerPos = new BlockPos(cposx + width / 2, cposy + 1, cposz + width / 2);
        level.setBlock(spawnerPos, Blocks.SPAWNER.defaultBlockState(), 2);
        BlockEntity spawnerEntity = level.getBlockEntity(spawnerPos);
        if (spawnerEntity instanceof SpawnerBlockEntity spawner) {
            EntityType<?> rubyBird =
                    ForgeRegistries.ENTITY_TYPES.getValue(
                            new ResourceLocation("chaospersists", "ruby_bird"));
            if (rubyBird != null) {
                spawner.setEntityId(rubyBird, level.getRandom());
            }
        }

        BlockPos chestPos = new BlockPos(cposx + width / 2, cposy + 1, cposz + 1);
        level.setBlock(chestPos, Blocks.CHEST.defaultBlockState(), 2);
        BlockEntity chestEntity = level.getBlockEntity(chestPos);
        if (chestEntity instanceof ChestBlockEntity chest) {
            WeightedRandomChestContent.generateChestContents(
                    rand, this.chestContentsList, chest, 4 + rand.nextInt(7));
        }
    }

    public void FastSetBlock(Level level, int ix, int iy, int iz, Block id) {
        ChaosPersists.setBlockFast(level, ix, iy, iz, id, 0, 2);
    }
}
