package com.astryxion.chaospersists.client;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.model.ModelBattleAxe;
import com.astryxion.chaospersists.model.ModelBertha;
import com.astryxion.chaospersists.model.ModelHammy;
import com.astryxion.chaospersists.model.ModelQueenBattleAxe;
import com.astryxion.chaospersists.model.ModelSlice;
import com.astryxion.chaospersists.model.ModelSquidZooka;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ModelEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Registers giant in-hand weapon renderers during model baking (must listen on the mod event bus).
 */
public final class BigWeaponModelHandler {

    private static final Logger LOGGER = LogManager.getLogger("chaospersists");

    private BigWeaponModelHandler() {}

    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        wrapTeisrHandItem(event, item("chainsawsmall"), ChainsawItemStackRenderer::new);

        ModelBertha berthaModel = new ModelBertha();
        wrapTeisrHandItem(
                event,
                item("berthasmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("berthatexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.BERTHA,
                                berthaModel::render));

        ModelHammy hammyModel = new ModelHammy();
        wrapTeisrHandItem(
                event,
                item("hammysmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("attitudeadjustertexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.HAMMY,
                                hammyModel::render));

        ModelSlice sliceModel = new ModelSlice();
        wrapTeisrHandItem(
                event,
                item("slicesmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("slicetexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.SLICE,
                                sliceModel::render));

        ModelSlice royalModel = new ModelSlice();
        wrapTeisrHandItem(
                event,
                item("royalsmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("royaltexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.ROYAL,
                                royalModel::render));

        ModelBattleAxe battleAxeModel = new ModelBattleAxe();
        wrapTeisrHandItem(
                event,
                item("battleaxesmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("battleaxetexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.BATTLE_AXE,
                                battleAxeModel::render));

        ModelQueenBattleAxe queenAxeModel = new ModelQueenBattleAxe();
        wrapTeisrHandItem(
                event,
                item("queenbattleaxesmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("queenbattleaxetexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.QUEEN_BATTLE_AXE,
                                queenAxeModel::render));

        ModelSquidZooka squidModel = new ModelSquidZooka();
        wrapTeisrHandItem(
                event,
                item("squidzookasmall"),
                flat ->
                        new StaticBigWeaponItemStackRenderer(
                                flat,
                                texture("squidzookatexture.png"),
                                StaticBigWeaponItemStackRenderer.Style.SQUID_ZOOKA,
                                squidModel::render));
    }

    private static Item item(String path) {
        return BuiltInRegistries.ITEM.get(new ResourceLocation(ChaosPersists.MODID, path));
    }

    private static ResourceLocation texture(String file) {
        return new ResourceLocation(ChaosPersists.MODID, "textures/entity/" + file);
    }

    private static void wrapTeisrHandItem(
            ModelEvent.ModifyBakingResult event,
            Item item,
            java.util.function.Function<BakedModel, net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer>
                    createRenderer) {
        if (item == null) {
            return;
        }
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(item);
        if (itemId == null) {
            return;
        }
        ModelResourceLocation mrl = new ModelResourceLocation(itemId, "inventory");
        java.util.Map<ResourceLocation, BakedModel> models = event.getModels();
        BakedModel baked = models.get(mrl);
        BakedModel original =
                baked instanceof TeisrHandBakedModelWrapper
                        ? ((TeisrHandBakedModelWrapper) baked).getInner()
                        : baked;
        if (original != null) {
            models.put(mrl, new TeisrHandBakedModelWrapper(original));
            TeisrHandBakedModelWrapper.registerCustomRenderer(item, createRenderer.apply(original));
            LOGGER.debug("Registered giant weapon renderer for {}", itemId);
        } else {
            LOGGER.warn("Missing baked model for giant weapon item {}", itemId);
        }
    }
}
