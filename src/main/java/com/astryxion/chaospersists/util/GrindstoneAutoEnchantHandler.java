package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Bertha;
import com.astryxion.chaospersists.item.EmeraldPickaxe;
import com.astryxion.chaospersists.item.ExperienceSword;
import com.astryxion.chaospersists.item.ItemChaosArmor;
import com.astryxion.chaospersists.item.ItemNetherLost;
import com.astryxion.chaospersists.item.ItemRandomDungeon;
import com.astryxion.chaospersists.item.NightmareSword;
import com.astryxion.chaospersists.item.PoisonSword;
import com.astryxion.chaospersists.item.Slice;
import com.astryxion.chaospersists.item.UltimateAxe;
import com.astryxion.chaospersists.item.UltimateBow;
import com.astryxion.chaospersists.item.UltimateFishingRod;
import com.astryxion.chaospersists.item.UltimateHoe;
import com.astryxion.chaospersists.item.UltimatePickaxe;
import com.astryxion.chaospersists.item.UltimateShovel;
import com.astryxion.chaospersists.item.UltimateSword;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.GrindstoneEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChaosPersists.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class GrindstoneAutoEnchantHandler {

    private GrindstoneAutoEnchantHandler() {}

    @SubscribeEvent
    public static void onGrindstonePlace(GrindstoneEvent.OnPlaceItem event) {
        ItemStack top = event.getTopItem();
        ItemStack bottom = event.getBottomItem();
        boolean topRestores = restoresDefaultEnchantments(top);
        boolean bottomRestores = restoresDefaultEnchantments(bottom);
        if (!topRestores && !bottomRestores) {
            return;
        }
        if (topRestores && bottom.isEmpty()) {
            event.setOutput(top.copy());
            event.setXp(0);
            return;
        }
        if (bottomRestores && top.isEmpty()) {
            event.setOutput(bottom.copy());
            event.setXp(0);
        }
    }

    @SubscribeEvent
    public static void onGrindstoneTake(GrindstoneEvent.OnTakeItem event) {
        if (restoresDefaultEnchantments(event.getTopItem())
                || restoresDefaultEnchantments(event.getBottomItem())) {
            event.setXp(0);
        }
    }

    private static boolean restoresDefaultEnchantments(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }
        Item item = stack.getItem();
        if (item == ChaosPersists.MyChainsaw) {
            return false;
        }
        return item instanceof UltimateSword
                || item instanceof UltimatePickaxe
                || item instanceof UltimateAxe
                || item instanceof UltimateShovel
                || item instanceof UltimateHoe
                || item instanceof UltimateBow
                || item instanceof UltimateFishingRod
                || item instanceof Bertha
                || item instanceof Slice
                || item instanceof NightmareSword
                || item instanceof ExperienceSword
                || item instanceof PoisonSword
                || item instanceof ItemChaosArmor
                || item instanceof ItemNetherLost
                || item instanceof ItemRandomDungeon
                || item instanceof EmeraldPickaxe;
    }
}
