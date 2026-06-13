package com.astryxion.chaospersists.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class Acid extends LaserBall {
    private int my_index = 85;

    public Acid(EntityType<? extends Acid> type, World level) {
        super(type, level);
        super.setAcid();
    }

    public Acid(World level) {
        this(resolveEntityType(), level);
    }

    public Acid(World level, int par2) {
        super(level);
        super.setAcid();
    }

    public Acid(World level, LivingEntity thrower) {
        super(level, thrower);
        super.setAcid();
    }

    public Acid(World level, LivingEntity thrower, int par3) {
        super(level, thrower);
        super.setAcid();
    }

    public Acid(World level, double x, double y, double z) {
        super(level, x, y, z);
        super.setAcid();
    }

    public int getAcidIndex() {
        return this.my_index;
    }

    @SuppressWarnings("unchecked")
    private static EntityType<? extends Acid> resolveEntityType() {
        EntityType<?> type = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "acid"));
        return type != null ? (EntityType<? extends Acid>) type : (EntityType<? extends Acid>) ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "laser_ball"));
    }
}
