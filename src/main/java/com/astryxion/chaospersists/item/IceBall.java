package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.MyUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

public class IceBall extends LaserBall {
    private int my_index = 84;
    private int icemaker = 0;

    public IceBall(EntityType<? extends IceBall> type, World level) {
        super(type, level);
        super.setIceBall();
    }

    public IceBall(World level) {
        this(resolveEntityType(), level);
    }

    public IceBall(World level, int par2) {
        super(level);
        super.setIceBall();
    }

    public IceBall(World level, LivingEntity thrower) {
        super(level, thrower);
        super.setIceBall();
    }

    public IceBall(World level, LivingEntity thrower, int par3) {
        super(level, thrower);
        super.setIceBall();
    }

    public IceBall(World level, double x, double y, double z) {
        super(level, x, y, z);
        super.setIceBall();
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends IceBall> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "ice_ball"));
        return type != null ? (EntityType<? extends IceBall>) type : (EntityType<? extends IceBall>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "laser_ball"));
    }

    public int getIceBallIndex() {
        return this.my_index;
    }

    public void setIceMaker(int i) {
        this.icemaker = i;
    }

    @Override
    protected void onHit(RayTraceResult hitResult) {
        if (this.level.isClientSide) {
            return;
        }
        if (hitResult.getType() == RayTraceResult.Type.ENTITY) {
            Entity entityHit = ((EntityRayTraceResult) hitResult).getEntity();
            if (MyUtils.isRoyalty(entityHit)) {
                this.remove();
                return;
            }
        }
        super.onHit(hitResult);
        if (this.icemaker != 0) {
            net.minecraft.util.math.vector.Vector3d hitVec = hitResult.getLocation();
            for (int i = 0; i < 5; ++i) {
                int x = this.level.random.nextInt(4);
                if (this.level.random.nextInt(2) == 1) {
                    x = -x;
                }
                int y = this.level.random.nextInt(4);
                if (this.level.random.nextInt(2) == 1) {
                    y = -y;
                }
                int z = this.level.random.nextInt(4);
                if (this.level.random.nextInt(2) == 1) {
                    z = -z;
                }
                x = (int)((double)x + hitVec.x);
                y = (int)((double)y + hitVec.y);
                z = (int)((double)z + hitVec.z);
                this.level.setBlock(new BlockPos(x, y, z), Blocks.ICE.defaultBlockState(), 3);
            }
        }
    }
}
