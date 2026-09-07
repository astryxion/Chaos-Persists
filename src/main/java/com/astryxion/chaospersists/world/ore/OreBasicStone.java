package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.MiningDropHelper;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.ForgeRegistries;

public class OreBasicStone extends Block {

    private final boolean crystalBlock;

    public OreBasicStone(float hardness, float resistance) {
        this(hardness, resistance, false);
    }

    public OreBasicStone(float hardness, float resistance, boolean crystalBlock) {
        super(buildProperties(hardness, resistance, crystalBlock));
        this.crystalBlock = crystalBlock;
    }

    private static Block.Properties buildProperties(float hardness, float resistance, boolean crystalBlock) {
        Block.Properties properties =
                Block.Properties.of()
                        .mapColor(MapColor.STONE)
                        .strength(hardness, resistance)
                        .sound(SoundType.STONE)
                        .requiresCorrectToolForDrops();
        if (crystalBlock) {
            properties = properties.noOcclusion();
        }
        return properties;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return MiningDropHelper.selfDrops(this, builder);
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && !world.isClientSide) {
            if (this == ChaosPersists.CrystalRat) {
                int num = 1 + world.getRandom().nextInt(10);
                for (int i = 0; i < num; ++i) {
                    spawnCreature(
                            world,
                            0,
                            "Rat",
                            pos.getX() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2,
                            pos.getY() + 0.01,
                            pos.getZ() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2);
                }
            }
            if (this == ChaosPersists.CrystalFairy) {
                int num = 1 + world.getRandom().nextInt(6);
                for (int i = 0; i < num; ++i) {
                    spawnCreature(
                            world,
                            0,
                            "Fairy",
                            pos.getX() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2,
                            pos.getY() + 0.01,
                            pos.getZ() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2);
                }
            }
            if (this == ChaosPersists.RedAntTroll || this == ChaosPersists.DeepslateRedAntTroll) {
                int num = 15 + world.getRandom().nextInt(6);
                for (int i = 0; i < num; ++i) {
                    spawnCreature(
                            world,
                            0,
                            "Red Ant",
                            pos.getX() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2,
                            pos.getY() + 0.01,
                            pos.getZ() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2);
                }
            }
            if (this == ChaosPersists.TermiteTroll || this == ChaosPersists.DeepslateTermiteTroll) {
                int num = 15 + world.getRandom().nextInt(6);
                for (int i = 0; i < num; ++i) {
                    spawnCreature(
                            world,
                            0,
                            "Termite",
                            pos.getX() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2,
                            pos.getY() + 0.01,
                            pos.getZ() + 0.5 + (world.getRandom().nextFloat() - world.getRandom().nextFloat()) * 0.2);
                }
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }

    private boolean isCrystalBlock() {
        return crystalBlock;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        if (isCrystalBlock()) {
            return false;
        }
        return super.useShapeForLightOcclusion(state);
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (isCrystalBlock() && adjacentState.getBlock() == this) {
            return true;
        }
        return super.skipRendering(state, adjacentState, side);
    }

    private static ResourceLocation legacySpawnNameToRegistry(String legacyName) {
        if (legacyName == null) {
            return null;
        }
        switch (legacyName) {
            case "Rat":
                return new ResourceLocation("chaospersists", "rat");
            case "Fairy":
                return new ResourceLocation("chaospersists", "fairy");
            case "Red Ant":
                return new ResourceLocation("chaospersists", "red_ant");
            case "Termite":
                return new ResourceLocation("chaospersists", "termite");
            default:
                return new ResourceLocation(
                        "chaospersists", legacyName.toLowerCase(java.util.Locale.ROOT).replace(' ', '_'));
        }
    }

    public static Entity spawnCreature(Level world, int id, String name, double x, double y, double z) {
        ResourceLocation entityId = legacySpawnNameToRegistry(name);
        EntityType<?> type = entityId != null ? ForgeRegistries.ENTITY_TYPES.getValue(entityId) : null;
        if (type == null || !(world instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity != null) {
            entity.moveTo(x, y, z, world.getRandom().nextFloat() * 360.0f, 0.0f);
            serverLevel.addFreshEntity(entity);
            if (entity instanceof Mob mob) {
                MyUtils.playAmbientSound(mob);
            }
        }
        return entity;
    }
}
