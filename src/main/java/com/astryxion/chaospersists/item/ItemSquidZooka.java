package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.AttackSquid;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class ItemSquidZooka extends Item {

    public ItemSquidZooka(int i) {
        super(new Properties().stacksTo(1).durability(100));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getMaxDamage() - stack.getDamageValue() <= 1) {
            return InteractionResultHolder.fail(stack);
        }

        level.playSound(
                player,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.GENERIC_EXPLODE,
                SoundSource.PLAYERS,
                0.5f,
                0.5f);

        if (!level.isClientSide) {
            double xzoff = 2.5;
            double yoff = 1.65;

            Entity e =
                    spawnCreature(
                            level,
                            player.getX()
                                    - xzoff
                                            * Math.sin(
                                                    Math.toRadians(player.getYHeadRot() + 15.0f)),
                            player.getY() + yoff,
                            player.getZ()
                                    + xzoff
                                            * Math.cos(
                                                    Math.toRadians(player.getYHeadRot() + 15.0f)));

            if (e != null) {
                if (e instanceof AttackSquid attackSquid) {
                    attackSquid.setWasShot();
                }

                float f = 3.6f;

                double motionX =
                        -Mth.sin(player.getYRot() * 0.017453292F)
                                * Mth.cos(player.getXRot() * 0.017453292F)
                                * f;
                double motionZ =
                        Mth.cos(player.getYRot() * 0.017453292F)
                                * Mth.cos(player.getXRot() * 0.017453292F)
                                * f;
                double motionY = -Mth.sin(player.getXRot() * 0.017453292F) * f;

                motionX += (level.random.nextFloat() - level.random.nextFloat()) * 0.05;
                motionY += (level.random.nextFloat() - level.random.nextFloat()) * 0.05;
                motionZ += (level.random.nextFloat() - level.random.nextFloat()) * 0.05;

                e.setDeltaMovement(motionX, motionY, motionZ);
                e.hurtMarked = true;
            } else {
                System.out.println("SquidZooka failed to spawn AttackSquid");
            }
        }

        player.swing(hand);

        player.push(
                Math.cos(Math.toRadians(player.getYHeadRot() - 90.0f)) * 0.45,
                0.1,
                Math.sin(Math.toRadians(player.getYHeadRot() - 90.0f)) * 0.45);

        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        com.astryxion.chaospersists.client.item.BigWeaponClientExtensions.register(consumer, this);
    }

    public static Entity spawnCreature(Level level, double x, double y, double z) {
        Entity entity = ChaosPersists.ENTITY_TYPE_ATTACK_SQUID.get().create(level);
        if (entity != null) {
            entity.moveTo(x, y, z, level.random.nextFloat() * 360.0f, 0.0f);
            level.addFreshEntity(entity);
        }
        return entity;
    }
}
