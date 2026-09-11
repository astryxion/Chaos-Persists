package com.astryxion.chaospersists.compat.jade;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

/**
 * Jade's built-in growth timer only reads vanilla {@code AgeableMob} age ticks. Prince stages
 * grow from kill / food / day counters instead, so this provider shows those.
 */
public enum PrinceGrowJadeProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
    INSTANCE;

    private static final int KILL_NEEDED = 26;
    private static final int FED_NEEDED = 11;
    private static final int DAY_NEEDED = 11;

    @Override
    public void appendServerData(CompoundTag data, EntityAccessor accessor) {
        Entity entity = accessor.getEntity();
        data.putBoolean("CPAuto", ChaosPersists.PrinceAutoGrow != 0);
        if (entity instanceof ThePrince prince) {
            data.putInt("CPKills", prince.getPrinceKillCount());
            data.putInt("CPFed", prince.getPrinceFedCount());
            data.putInt("CPDays", prince.getPrinceDayCount());
            data.putBoolean("CPFedItem", prince.isPrinceGrowthItemFed());
        } else if (entity instanceof ThePrinceTeen teen) {
            data.putInt("CPKills", teen.getPrinceKillCount());
            data.putInt("CPDays", teen.getPrinceDayCount());
            data.putBoolean("CPFedItem", teen.isPrinceGrowthItemFed());
        }
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor, IPluginConfig config) {
        CompoundTag data = accessor.getServerData();
        if (!data.contains("CPKills")) {
            return;
        }
        boolean auto = data.getBoolean("CPAuto");
        boolean fedItem = data.getBoolean("CPFedItem");
        if (auto) {
            tooltip.add(Component.translatable("jade.chaospersists.prince_grow.natural"));
        } else if (fedItem) {
            tooltip.add(Component.translatable("jade.chaospersists.prince_grow.diamond_ready"));
        } else {
            tooltip.add(Component.translatable("jade.chaospersists.prince_grow.diamond_only"));
        }
        tooltip.add(
                Component.translatable(
                        "jade.chaospersists.prince_grow.kills",
                        data.getInt("CPKills"),
                        KILL_NEEDED));
        if (data.contains("CPFed")) {
            tooltip.add(
                    Component.translatable(
                            "jade.chaospersists.prince_grow.food",
                            data.getInt("CPFed"),
                            FED_NEEDED));
        }
        tooltip.add(
                Component.translatable(
                        "jade.chaospersists.prince_grow.days",
                        data.getInt("CPDays"),
                        DAY_NEEDED));
    }

    @Override
    public ResourceLocation getUid() {
        return ChaosPersistsJadePlugin.PRINCE_GROW;
    }
}
