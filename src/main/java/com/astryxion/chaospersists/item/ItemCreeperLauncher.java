package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.compat.creeperoverhaul.CreeperOverhaulLauncherCompat;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemCreeperLauncher extends Item {

    public ItemCreeperLauncher(int i) {
        super(new Properties().stacksTo(16));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.FIREWORK_ROCKET_LAUNCH,
                SoundSource.PLAYERS,
                1.0f,
                1.0f);

        if (!level.isClientSide) {
            Creeper creeper = CreeperOverhaulLauncherCompat.createLaunchedCreeper(level, player.getRandom());
            if (creeper != null) {
                creeper.moveTo(
                        player.getX(),
                        player.getY() + player.getEyeHeight(),
                        player.getZ());
                creeper.setDeltaMovement(
                        -Math.sin(Math.toRadians(player.getYRot()))
                                * Math.cos(Math.toRadians(player.getXRot()))
                                * 1.5,
                        -Math.sin(Math.toRadians(player.getXRot())) * 1.5,
                        Math.cos(Math.toRadians(player.getYRot()))
                                * Math.cos(Math.toRadians(player.getXRot()))
                                * 1.5);
                level.addFreshEntity(creeper);
            }
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
