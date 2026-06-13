package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.item.LaserBall;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class DeadIrukandji extends LaserBall {
    private int my_index = 86;

    public DeadIrukandji(EntityType<? extends DeadIrukandji> type, World par1World) {
        super(type, par1World);
        super.setIrukandji();
    }

    public DeadIrukandji(World par1World) {
        super(deadIrukandjiType(), par1World);
        super.setIrukandji();
    }

    public DeadIrukandji(World par1World, int par2) {
        super(par1World);
        super.setIrukandji();
    }

    public DeadIrukandji(World par1World, LivingEntity par2LivingEntity) {
        super(deadIrukandjiType(), par2LivingEntity, par1World);
        this.setIrukandji();
    }

    public DeadIrukandji(World par1World, LivingEntity par2LivingEntity, int par3) {
        this(par1World, par2LivingEntity);
        super.setIrukandji();
    }

    public DeadIrukandji(World par1World, double par2, double par4, double par6) {
        super(deadIrukandjiType(), par2, par4, par6, par1World);
        super.setIrukandji();
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends LaserBall> deadIrukandjiType() {
        return (EntityType<? extends LaserBall>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "dead_irukandji"));
    }

    public int getIrukandjiIndex() {
        return this.my_index;
    }
}
