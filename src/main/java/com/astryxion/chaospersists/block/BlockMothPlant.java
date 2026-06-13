package com.astryxion.chaospersists.block;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityLunaMoth;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.EntityType;

import java.util.List;
import java.util.Random;

import net.minecraft.block.CropsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockMothPlant extends CropsBlock {

    public BlockMothPlant() {
        super(AbstractBlock.Properties.copy(Blocks.WHEAT).noCollission().randomTicks());
    }

    @OnlyIn(Dist.CLIENT)
public RenderType getRenderType(BlockState state) {
        return RenderType.cutout();
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.randomTick(state, world, pos, rand);
        float radius = 50.0F;
        AxisAlignedBB aabb = new AxisAlignedBB((pos.getX() - radius), 0.0D, (pos.getZ() - radius),
                (pos.getX() + radius), 200.0D, (pos.getZ() + radius));
        List<EntityLunaMoth> MothList = world.getEntitiesOfClass(EntityLunaMoth.class, aabb);
        if (MothList.size() > 15) {
            return;
        }
        int rate = state.getValue(AGE) & 0x7;
        rate = 7 - rate;
        if (rate > 1 && ChaosPersists.ChaosRand.nextInt(rate) != 0) {
            return;
        }
        if (world.getBlockState(pos.above()).getBlock() == Blocks.AIR && world.isDay()) {
            ResourceLocation rl = new ResourceLocation("chaospersists", "moth");
            EntityType<?> type = ForgeRegistries.ENTITIES.getValue(rl);
            if (type != null) {
                Entity Moth = type.create(world);
                if (Moth != null) {
                    Moth.moveTo(pos.getX(), pos.getY() + 1, pos.getZ(),
                            world.random.nextFloat() * 360.0F, 0.0F);
                    world.addFreshEntity(Moth);
                }
            }
        }
    }

    @Override
    protected Item getBaseSeedId() {
        return ChaosPersists.MyMothSeed;
    }
}

