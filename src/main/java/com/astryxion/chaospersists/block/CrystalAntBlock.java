package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.util.MyUtils;
import com.astryxion.chaospersists.util.MiningDropHelper;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class CrystalAntBlock extends Block {

    public CrystalAntBlock(int par1) {
        super(net.minecraft.world.level.block.Block.Properties.of()
                .strength(0.6f)
                .sound(SoundType.GRASS)
                .randomTicks()
                .noOcclusion());
    }

    /**
     * Matches {@link AntBlock}: daytime only, air above, cap nearby mobs, spawn 2-7.
     */
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand) {
        if (level.getBlockState(pos.above()).getBlock() != Blocks.AIR) {
            return;
        }

        if (!MyUtils.isDay(level)) {
            return;
        }

        EntityType<? extends Mob> entityType = getEntityTypeForBlock();
        if (entityType == null) {
            return;
        }

        int radius = 16;
        AABB aabb = new AABB(
                pos.getX() - radius,
                0.0D,
                pos.getZ() - radius,
                pos.getX() + radius,
                200.0D,
                pos.getZ() + radius);

        if (level.getEntitiesOfClass(entityType.getBaseClass(), aabb).size() > 20) {
            return;
        }

        int count = rand.nextInt(6) + 2;
        for (int i = 0; i < count; i++) {
            spawnCreature(level, entityType, pos.getX() + 0.5D, pos.getY() + 1.01D, pos.getZ() + 0.5D);
        }
    }

    private EntityType<? extends Mob> getEntityTypeForBlock() {
        if (this == ChaosPersists.CrystalTermiteBlock) {
            return ChaosPersists.TermiteEnable != 0 ? ChaosPersists.ENTITY_TYPE_TERMITE.get() : null;
        }
        return null;
    }

    @Override
    public ItemStack getCloneItemStack(net.minecraft.world.level.BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, net.minecraft.world.level.storage.loot.LootParams.Builder builder) {
        return MiningDropHelper.selfDrops(this, builder);
    }

    public static Entity spawnCreature(
            net.minecraft.world.level.Level world, EntityType<? extends Mob> entityType, double x, double y, double z) {
        Entity entity = entityType.create(world);

        if (entity != null) {
            entity.moveTo(x, y, z, world.getRandom().nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(entity);
            if (entity instanceof LivingEntity living) {
                MyUtils.playAmbientSound(living);
            }
        }

        return entity;
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }
}
