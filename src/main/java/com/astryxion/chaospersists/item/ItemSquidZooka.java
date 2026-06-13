package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.AttackSquid;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemSquidZooka extends Item {

    public ItemSquidZooka(int i) { super(new Item.Properties().stacksTo(1).durability(100)); }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack stack = player.getItemInHand(hand);

        // Prevent breaking
        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return ActionResult.fail(stack);
        }

        // Play explosion sound
        world.playSound(
                player,
                player.getX(),
                player.getY(),
                player.getZ(),
                net.minecraft.util.SoundEvents.GENERIC_EXPLODE,
                SoundCategory.PLAYERS,
                0.5f,
                0.5f
        );

        if (!world.isClientSide) {

            double xzoff = 2.5;
            double yoff = 1.65;

            Entity e = spawnCreature(
                    world,
                    player.getX() - xzoff * Math.sin(Math.toRadians(player.yHeadRot + 15.0f)),
                    player.getY() + yoff,
                    player.getZ() + xzoff * Math.cos(Math.toRadians(player.yHeadRot + 15.0f))
            );

            if (e != null) {

                if (e instanceof AttackSquid) {
                    ((AttackSquid) e).setWasShot();
                }

                float f = 3.6f;

                e.setDeltaMovement((-MathHelper.sin(player.yRot * 0.017453292F))
                        * MathHelper.cos(player.xRot * 0.017453292F) * f, e.getDeltaMovement().y, e.getDeltaMovement().z);

                e.setDeltaMovement(e.getDeltaMovement().x, e.getDeltaMovement().y, (MathHelper.cos(player.yRot * 0.017453292F))
                        * MathHelper.cos(player.xRot * 0.017453292F) * f);

                e.setDeltaMovement(e.getDeltaMovement().x, (-MathHelper.sin(player.xRot * 0.017453292F)) * f, e.getDeltaMovement().z);

                // Add slight randomness
com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(e, (world.random.nextFloat() - world.random.nextFloat()) * 0.05, (world.random.nextFloat() - world.random.nextFloat()) * 0.05, (world.random.nextFloat() - world.random.nextFloat()) * 0.05);

                e.hasImpulse = true;
            } else {
                System.out.println("SquidZooka failed to spawn AttackSquid");
            }
        }

        player.swing(hand);

        // Apply recoil
        player.setDeltaMovement(player.getDeltaMovement().add(
                Math.cos(Math.toRadians(player.yHeadRot - 90.0f)) * 0.45,
                0.1,
                Math.sin(Math.toRadians(player.yHeadRot - 90.0f)) * 0.45
        ));

        stack.hurtAndBreak(1, player, (broken) -> broken.broadcastBreakEvent(hand));

        return ActionResult.success(stack);
    }

    public static Entity spawnCreature(World world, double x, double y, double z) {

        net.minecraft.entity.EntityType<?> spawnType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(
                new ResourceLocation("chaospersists", "attack_squid"));
        Entity entity = spawnType != null ? spawnType.create(world) : null;

        if (entity != null) {
            entity.moveTo(x, y, z, world.random.nextFloat() * 360.0f, 0.0f);
            world.addFreshEntity(entity);
        }

        return entity;
    }
}
