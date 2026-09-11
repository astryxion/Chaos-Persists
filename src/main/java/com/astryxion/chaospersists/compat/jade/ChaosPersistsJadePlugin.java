package com.astryxion.chaospersists.compat.jade;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public final class ChaosPersistsJadePlugin implements IWailaPlugin {

    public static final ResourceLocation PRINCE_GROW =
            new ResourceLocation(ChaosPersists.MODID, "prince_grow");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerEntityDataProvider(PrinceGrowJadeProvider.INSTANCE, ThePrince.class);
        registration.registerEntityDataProvider(PrinceGrowJadeProvider.INSTANCE, ThePrinceTeen.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(PrinceGrowJadeProvider.INSTANCE, ThePrince.class);
        registration.registerEntityComponent(PrinceGrowJadeProvider.INSTANCE, ThePrinceTeen.class);
    }
}
