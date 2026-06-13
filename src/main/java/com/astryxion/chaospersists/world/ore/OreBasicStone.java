package com.astryxion.chaospersists.world.ore;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class OreBasicStone extends Block {

    public OreBasicStone(float hardness, float resistance) {
        super(AbstractBlock.Properties.of(Material.STONE).strength(hardness, resistance));
    }

    // 1.12.2: spawns run in breakBlock (player/world removal), not when ores are placed via chunk writes during world gen.
    @Override
    public void playerWillDestroy(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClientSide) {
            spawnCreaturesFromOreBreak(world, pos);
        }
        super.playerWillDestroy(world, pos, state, player);
    }

    private void spawnCreaturesFromOreBreak(World world, BlockPos pos) {
        if (this == ChaosPersists.CrystalRat) {
            int num = 1 + world.random.nextInt(10);
            for (int i = 0; i < num; ++i) {
                spawnCreature(world, 0, "Rat",
                        pos.getX() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2,
                        pos.getY() + 0.01,
                        pos.getZ() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2);
            }
        }
        if (this == ChaosPersists.CrystalFairy) {
            int num = 1 + world.random.nextInt(6);
            for (int i = 0; i < num; ++i) {
                spawnCreature(world, 0, "Fairy",
                        pos.getX() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2,
                        pos.getY() + 0.01,
                        pos.getZ() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2);
            }
        }
        if (this == ChaosPersists.RedAntTroll) {
            int num = 15 + world.random.nextInt(6);
            for (int i = 0; i < num; ++i) {
                spawnCreature(world, 0, "Red Ant",
                        pos.getX() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2,
                        pos.getY() + 0.01,
                        pos.getZ() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2);
            }
        }
        if (this == ChaosPersists.TermiteTroll) {
            int num = 15 + world.random.nextInt(6);
            for (int i = 0; i < num; ++i) {
                spawnCreature(world, 0, "Termite",
                        pos.getX() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2,
                        pos.getY() + 0.01,
                        pos.getZ() + 0.5 + (world.random.nextFloat() - world.random.nextFloat()) * 0.2);
            }
        }
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos,
            EntitySpawnPlacementRegistry.PlacementType type, EntityType<?> entityType) {
        if (isCrystalBlock()) {
            return Blocks.STONE.canCreatureSpawn(Blocks.STONE.defaultBlockState(), world, pos, type, entityType);
        }
        return super.canCreatureSpawn(state, world, pos, type, entityType);
    }

    private boolean isCrystalBlock() {
        return this == ChaosPersists.CrystalStone || this == ChaosPersists.CrystalRat
                || this == ChaosPersists.CrystalFairy;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return isCrystalBlock();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentState, Direction side) {
        if (isCrystalBlock()) {
            if (adjacentState.getBlock() == this) {
                return true;
            }
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
                return new ResourceLocation("chaospersists",
                        legacyName.toLowerCase(java.util.Locale.ROOT).replace(' ', '_'));
        }
    }

    public static Entity spawnCreature(World world, int id, String name, double x, double y, double z) {
        Entity entity = null;
        if (name != null) {
            EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(legacySpawnNameToRegistry(name));
            if (entityType != null) {
                entity = entityType.create(world);
            }
        }
        if (entity != null) {
            entity.moveTo(x, y, z, world.random.nextFloat() * 360.0f, 0.0f);
            if (!world.isClientSide) {
                ((ServerWorld)world).addFreshEntity(entity);
            }
            if (entity instanceof net.minecraft.entity.MobEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) entity);
            }
        }
        return entity;
    }
}
