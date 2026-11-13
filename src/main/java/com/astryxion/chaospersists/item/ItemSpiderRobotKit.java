package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.AntRobot;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ItemSpiderRobotKit extends Item {
    public ItemSpiderRobotKit(int i) {
        super(
                new Properties()
                        .stacksTo(1)
                        .durability(
                                i == ChaosPersists.BaseItemID + 471
                                        ? ChaosPersists.SpiderRobot_stats.health
                                        : ChaosPersists.AntRobot_stats.health));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        double spawnX = pos.getX() + 0.5;
        double spawnY = pos.getY() + 1.01;
        double spawnZ = pos.getZ() + 0.5;
        String name = "robot_spider";
        if (stack.getItem() == ChaosPersists.AntRobotKit) {
            name = "robot_red_ant";
        }
        Entity ent = ItemSpawnEgg.spawnCreature(level, 0, name, spawnX, spawnY, spawnZ);
        if (ent != null) {
            if (ent instanceof LivingEntity living) {
                living.setHealth((float) (stack.getMaxDamage() - stack.getDamageValue()));
                if (stack.has(net.minecraft.core.component.DataComponents.CUSTOM_NAME)) {
                    living.setCustomName(stack.getHoverName());
                }
            }
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.GENERIC_EXPLODE.value(),
                    SoundSource.PLAYERS,
                    1.0f,
                    level.getRandom().nextFloat() * 0.2f + 0.9f);
            if (ent instanceof AntRobot antRobot) {
                antRobot.setOwned();
            }
        }
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return InteractionResult.SUCCESS;
    }

    public static Entity spawnCreature(Level level, int par1, String name, double x, double y, double z) {
        return ItemSpawnEgg.spawnCreature(level, par1, name, x, y, z);
    }
}
