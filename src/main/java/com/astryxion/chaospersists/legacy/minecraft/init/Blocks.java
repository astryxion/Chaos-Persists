package com.astryxion.chaospersists.legacy.minecraft.init;

import net.minecraft.world.level.block.Block;

/** Legacy 1.12 block ids mapped to 1.20.1 blocks (1:1 worldgen behavior). */
public final class Blocks {
  public static final Block AIR = net.minecraft.world.level.block.Blocks.AIR;
  public static final Block STONE = net.minecraft.world.level.block.Blocks.STONE;
  public static final Block GRASS = net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
  public static final Block DIRT = net.minecraft.world.level.block.Blocks.DIRT;
  public static final Block WATER = net.minecraft.world.level.block.Blocks.WATER;
  public static final Block LAVA = net.minecraft.world.level.block.Blocks.LAVA;
  public static final Block LOG = net.minecraft.world.level.block.Blocks.OAK_LOG;
  public static final Block BEDROCK = net.minecraft.world.level.block.Blocks.BEDROCK;
  public static final Block ICE = net.minecraft.world.level.block.Blocks.ICE;
  public static final Block SNOW_LAYER = net.minecraft.world.level.block.Blocks.SNOW;
  public static final Block GRAVEL = net.minecraft.world.level.block.Blocks.GRAVEL;
  public static final Block SAND = net.minecraft.world.level.block.Blocks.SAND;

  public static net.minecraft.world.level.block.state.BlockState defaultBlockState(Block block) {
    return ((net.minecraft.world.level.block.Block) block).defaultBlockState();
  }
  public static final Block RED_FLOWER = net.minecraft.world.level.block.Blocks.POPPY;
  public static final Block YELLOW_FLOWER = net.minecraft.world.level.block.Blocks.DANDELION;
  public static final Block WEB = net.minecraft.world.level.block.Blocks.COBWEB;
  public static final Block PLANKS = net.minecraft.world.level.block.Blocks.OAK_PLANKS;

  private Blocks() {}
}
