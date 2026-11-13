package com.astryxion.chaospersists.loot;

import com.astryxion.chaospersists.config.ChaosMobAccess;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.function.Supplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

/** Drops spawn eggs / king-queen spawners for mobs turned off on the Mobs tab. */
public class StripDisabledChaosItemsModifier extends LootModifier {

    public static final Supplier<MapCodec<StripDisabledChaosItemsModifier>> CODEC =
            Suppliers.memoize(
                    () ->
                            RecordCodecBuilder.mapCodec(
                                    inst ->
                                            codecStart(inst)
                                                    .apply(inst, StripDisabledChaosItemsModifier::new)));

    public StripDisabledChaosItemsModifier(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(
            ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.removeIf(stack -> !ChaosMobAccess.isItemAllowed(stack.getItem()));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
