package com.astryxion.chaospersists.legacy.forge.common;

import com.astryxion.chaospersists.block.ChaosDirectionalTorchBlock;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;

/** Legacy 1.12 registry iteration and {@code getRegistryName()} for Chaos Persists. */
public final class RegistryCompat {

  private RegistryCompat() {}

  public static final class BlockRegistry {
    private BlockRegistry() {}

    public static Iterable<Block> iterable() {
      return BuiltInRegistries.BLOCK;
    }
  }

  public static final class ItemRegistry {
    private ItemRegistry() {}

    public static Iterable<Item> iterable() {
      return BuiltInRegistries.ITEM;
    }
  }

  public static final BlockRegistry REGISTRY = new BlockRegistry();

  public static ResourceLocation getRegistryName(Block block) {
    return block == null ? null : BuiltInRegistries.BLOCK.getKey(block);
  }

  public static ResourceLocation getRegistryName(Item item) {
    return item == null ? null : BuiltInRegistries.ITEM.getKey(item);
  }

  /**
   * 1.12 {@code BlockTorch} metadata: 1=east, 2=west, 3=south, 4=north, 5=up. Structure code passes
   * {@code 0} everywhere, which behaved as a floor torch in practice.
   */
  public static Direction legacyTorchMetaToFacing(int meta) {
    return switch (meta & 7) {
      case 1 -> Direction.EAST;
      case 2 -> Direction.WEST;
      case 3 -> Direction.SOUTH;
      case 4 -> Direction.NORTH;
      case 5, 0 -> Direction.UP;
      default -> Direction.UP;
    };
  }

  /** Legacy 1.12 {@link Block#getStateFromMeta(int)} for worldgen fast paths. */
  public static BlockState getStateFromMeta(Block block, int meta) {
    if (block == null || block == Blocks.AIR) {
      return Blocks.AIR.defaultBlockState();
    }

    if (block instanceof ChaosDirectionalTorchBlock) {
      return block.defaultBlockState()
          .setValue(ChaosDirectionalTorchBlock.FACING, legacyTorchMetaToFacing(meta));
    }

    if (block == Blocks.GRASS_BLOCK || block == Blocks.MYCELIUM || block == Blocks.PODZOL) {
      return block.defaultBlockState().setValue(BlockStateProperties.SNOWY, false);
    }

    if (block == Blocks.TORCH) {
      Direction facing = legacyTorchMetaToFacing(meta);
      if (facing.getAxis().isHorizontal()) {
        return Blocks.WALL_TORCH
            .defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
      }
      return Blocks.TORCH.defaultBlockState();
    }

    if (block == Blocks.REDSTONE_TORCH) {
      Direction facing = legacyTorchMetaToFacing(meta);
      if (facing.getAxis().isHorizontal()) {
        return Blocks.REDSTONE_WALL_TORCH
            .defaultBlockState()
            .setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
      }
      return Blocks.REDSTONE_TORCH.defaultBlockState();
    }

    if (block == Blocks.WALL_TORCH || block == Blocks.REDSTONE_WALL_TORCH) {
      Direction facing = legacyTorchMetaToFacing(meta);
      if (facing.getAxis().isVertical()) {
        facing = Direction.NORTH;
      }
      return block.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, facing);
    }

    // 1.12 STAINED_HARDENED_CLAY metadata → 1.20.x colored terracotta blocks.
    if (block == Blocks.TERRACOTTA) {
      return legacyStainedTerracotta(meta).defaultBlockState();
    }

    // 1.7.10 / 1.12 button metadata, not 1.20 possible-state index.
    // 0 ceiling, 1 east, 2 west, 3 south, 4 north, 5 floor. Bit 8 = powered.
    if (block instanceof ButtonBlock) {
      return legacyButton(block, meta);
    }

    // 1.12 ladder: meta & 3 = EnumFacing.byHorizontalIndex (0=S, 1=W, 2=N, 3=E).
    if (block instanceof LadderBlock) {
      return block.defaultBlockState()
          .setValue(LadderBlock.FACING, Direction.from2DDataValue(meta & 3))
          .setValue(BlockStateProperties.WATERLOGGED, false);
    }

    // 1.12 trapdoor: bits 0-1 facing, bit 2 open, bit 3 top half.
    if (block instanceof TrapDoorBlock) {
      return block.defaultBlockState()
          .setValue(TrapDoorBlock.FACING, Direction.from2DDataValue(meta & 3))
          .setValue(TrapDoorBlock.OPEN, (meta & 4) != 0)
          .setValue(TrapDoorBlock.HALF, (meta & 8) != 0 ? Half.TOP : Half.BOTTOM)
          .setValue(TrapDoorBlock.POWERED, false)
          .setValue(BlockStateProperties.WATERLOGGED, false);
    }

    // 1.7.10 log metadata: 0/3 = Y (trunk), 1 = X, 2 = Z. 1.20 indexes states as X,Y,Z so meta 0 was sideways.
    if (block instanceof RotatedPillarBlock) {
      Direction.Axis axis =
          switch (meta & 3) {
            case 1 -> Direction.Axis.X;
            case 2 -> Direction.Axis.Z;
            default -> Direction.Axis.Y;
          };
      return block.defaultBlockState().setValue(BlockStateProperties.AXIS, axis);
    }

    if (meta == 0) {
      return block.defaultBlockState();
    }

    List<BlockState> states = block.getStateDefinition().getPossibleStates();
    if (states.isEmpty()) {
      return Blocks.AIR.defaultBlockState();
    }
    int idx = meta & 15;
    if (idx >= 0 && idx < states.size()) {
      return states.get(idx);
    }
    return block.defaultBlockState();
  }

  /**
   * 1.12 {@code BlockButton#getStateFromMeta}: 0=ceiling, 1=east, 2=west, 3=south, 4=north, 5=floor.
   * Robot/triffid buttons sit at {@code cposz - 1} with meta 4, attached to the wall at {@code cposz}.
   */
  private static BlockState legacyButton(Block block, int meta) {
    boolean powered = (meta & 8) != 0;
    BlockState state = block.defaultBlockState().setValue(BlockStateProperties.POWERED, powered);
    return switch (meta & 7) {
      case 0 -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.CEILING)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH);
      case 1 -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.WALL)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST);
      case 2 -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.WALL)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST);
      case 3 -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.WALL)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH);
      case 4 -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.WALL)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH);
      default -> state
          .setValue(BlockStateProperties.ATTACH_FACE, AttachFace.FLOOR)
          .setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH);
    };
  }

  /** 1.12 {@code Blocks.STAINED_HARDENED_CLAY} dye metadata order. */
  private static Block legacyStainedTerracotta(int meta) {
    return switch (meta & 15) {
      case 0 -> Blocks.WHITE_TERRACOTTA;
      case 1 -> Blocks.ORANGE_TERRACOTTA;
      case 2 -> Blocks.MAGENTA_TERRACOTTA;
      case 3 -> Blocks.LIGHT_BLUE_TERRACOTTA;
      case 4 -> Blocks.YELLOW_TERRACOTTA;
      case 5 -> Blocks.LIME_TERRACOTTA;
      case 6 -> Blocks.PINK_TERRACOTTA;
      case 7 -> Blocks.GRAY_TERRACOTTA;
      case 8 -> Blocks.LIGHT_GRAY_TERRACOTTA;
      case 9 -> Blocks.CYAN_TERRACOTTA;
      case 10 -> Blocks.PURPLE_TERRACOTTA;
      case 11 -> Blocks.BLUE_TERRACOTTA;
      case 12 -> Blocks.BROWN_TERRACOTTA;
      case 13 -> Blocks.GREEN_TERRACOTTA;
      case 14 -> Blocks.RED_TERRACOTTA;
      default -> Blocks.BLACK_TERRACOTTA;
    };
  }
}
