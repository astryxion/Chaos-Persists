package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Random;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ItemRandomDungeon extends Item {

    Random rand = ChaosPersists.ChaosRand;

    public ItemRandomDungeon(int i) {
        super(new Properties().stacksTo(1));
    }

    private static net.minecraft.world.level.block.Block modBlock(Object block) {
        return (net.minecraft.world.level.block.Block) block;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player) {
        stack.enchant(world.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FORTUNE), 2);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, net.minecraft.world.entity.Entity entity, int slot, boolean selected) {
        int lvl = com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.FORTUNE, stack);
        if (lvl <= 0) {
            stack.enchant(world.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.FORTUNE), 2);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level world = context.getLevel();
        if (player == null) {
            return InteractionResult.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        BlockPos placePos = context.getClickedPos().above();
        BlockState spawner = modBlock(ChaosPersists.MyDungeonSpawnerBlock).defaultBlockState();
        if (!world.isEmptyBlock(placePos) || !spawner.canSurvive(world, placePos)) {
            return InteractionResult.FAIL;
        }
        if (!world.isClientSide()) {
            world.setBlock(placePos, spawner, 2);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
