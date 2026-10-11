package com.astryxion.chaospersists.client.gui;

import com.astryxion.chaospersists.config.ChaosMobToggles;
import com.astryxion.chaospersists.config.ChaosNumericStats;
import com.astryxion.chaospersists.config.ChaosPackConfig;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.world.dimension.structure.ChaosLocateStructures;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/** Mods-menu config: General holds pack/gameplay options; other tabs are lists. */
@OnlyIn(Dist.CLIENT)
public class ChaosConfigScreen extends Screen {
    private enum Tab {
        GENERAL,
        STRUCTURES,
        MOBS,
        STATS
    }

    private enum Kind {
        HEADER,
        BOOL,
        DISABLE,
        GRIEF,
        INT
    }

    private enum StatFilter {
        ALL,
        ARMOR,
        WEAPON,
        ORE,
        MOB
    }

    private final Screen parent;
    private Tab tab = Tab.GENERAL;
    private EditBox searchBox;
    private String structureQuery = "";
    private String mobQuery = "";
    private String statQuery = "";
    private StatFilter statFilter = StatFilter.ALL;
    private OptionList optionList;
    private StructureList structureList;
    private MobList mobList;
    private StatList statList;

    public ChaosConfigScreen(Screen parent) {
        super(Component.translatable("config.chaospersists.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        this.searchBox = null;
        this.optionList = null;
        this.structureList = null;
        this.mobList = null;
        this.statList = null;
        this.addTabBar();
        switch (this.tab) {
            case STRUCTURES -> this.addSearchTab(true);
            case MOBS -> this.addSearchTab(false);
            case STATS -> this.addStatsTab();
            default -> this.addOptionTab(this.generalOptions());
        }
        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_DONE, btn -> this.onClose())
                        .bounds(this.width / 2 - 100, this.height - 26, 200, 20)
                        .build());
    }

    private void addTabBar() {
        int tabCount = Tab.values().length;
        int gap = 4;
        int tabWidth = Math.min(120, (this.width - 16 - gap * (tabCount - 1)) / tabCount);
        int total = tabWidth * tabCount + gap * (tabCount - 1);
        int x = (this.width - total) / 2;
        int y = 22;
        for (Tab next : Tab.values()) {
            Component label =
                    switch (next) {
                        case GENERAL -> Component.translatable("config.chaospersists.tab.general");
                        case STRUCTURES -> Component.translatable("config.chaospersists.category.structures");
                        case MOBS -> Component.translatable("config.chaospersists.category.mobs");
                        case STATS -> Component.translatable("config.chaospersists.category.stats");
                    };
            int tabX = x;
            boolean selected = this.tab == next;
            this.addRenderableWidget(
                    Button.builder(
                                    selected ? Component.literal("[").append(label).append("]") : label,
                                    btn -> {
                                        this.tab = next;
                                        this.rebuildWidgets();
                                    })
                            .bounds(tabX, y, tabWidth, 20)
                            .build());
            x += tabWidth + gap;
        }
    }

    private void addOptionTab(List<OptionDef> options) {
        this.optionList = new OptionList(this.minecraft, this.width, this.height, 48, this.height - 32, 24, options);
        this.addWidget(this.optionList);
    }

    private void addSearchTab(boolean structures) {
        int top = 48;
        int searchWidth = 220;
        int small = 90;
        int total = searchWidth + 8 + small + 4 + small;
        int x = (this.width - total) / 2;
        Component hint =
                Component.translatable(
                        structures ? "config.chaospersists.search" : "config.chaospersists.search_mobs");
        this.searchBox = new EditBox(this.font, x, top, searchWidth, 20, hint);
        this.searchBox.setMaxLength(64);
        this.searchBox.setHint(hint);
        this.searchBox.setValue(structures ? this.structureQuery : this.mobQuery);
        this.searchBox.setResponder(
                text -> {
                    if (structures) {
                        this.structureQuery = text;
                        if (this.structureList != null) {
                            this.structureList.reload();
                        }
                    } else {
                        this.mobQuery = text;
                        if (this.mobList != null) {
                            this.mobList.reload();
                        }
                    }
                });
        this.addRenderableWidget(this.searchBox);
        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("config.chaospersists.enable_all"),
                                btn -> {
                                    if (structures) {
                                        ChaosPackConfig.setStructuresEnabled(this.filteredStructureIds(), true);
                                        if (this.structureList != null) {
                                            this.structureList.reload();
                                        }
                                    } else {
                                        this.setMobsEnabled(this.filteredMobs(), true);
                                        if (this.mobList != null) {
                                            this.mobList.reload();
                                        }
                                    }
                                })
                        .bounds(x + searchWidth + 8, top, small, 20)
                        .build());
        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("config.chaospersists.disable_all"),
                                btn -> {
                                    if (structures) {
                                        ChaosPackConfig.setStructuresEnabled(this.filteredStructureIds(), false);
                                        if (this.structureList != null) {
                                            this.structureList.reload();
                                        }
                                    } else {
                                        this.setMobsEnabled(this.filteredMobs(), false);
                                        if (this.mobList != null) {
                                            this.mobList.reload();
                                        }
                                    }
                                })
                        .bounds(x + searchWidth + 8 + small + 4, top, small, 20)
                        .build());
        if (structures) {
            this.structureList =
                    new StructureList(this.minecraft, this.width, this.height, top + 26, this.height - 32, 24);
            this.addWidget(this.structureList);
        } else {
            this.mobList = new MobList(this.minecraft, this.width, this.height, top + 26, this.height - 32, 24);
            this.addWidget(this.mobList);
        }
    }

    private void addStatsTab() {
        int top = 48;
        int groupWidth = 88;
        int searchWidth = 170;
        int small = 90;
        int total = groupWidth + 8 + searchWidth + 8 + small + 4 + small;
        int x = (this.width - total) / 2;
        this.addRenderableWidget(
                CycleButton.<StatFilter>builder(this::statFilterLabel)
                        .withValues(StatFilter.ALL, StatFilter.ARMOR, StatFilter.WEAPON, StatFilter.ORE, StatFilter.MOB)
                        .withInitialValue(this.statFilter)
                        .create(
                                x,
                                top,
                                groupWidth,
                                20,
                                Component.translatable("config.chaospersists.stat.filter"),
                                (button, value) -> {
                                    this.statFilter = value;
                                    if (this.statList != null) {
                                        this.statList.reload();
                                    }
                                }));
        Component hint = Component.translatable("config.chaospersists.search_stats");
        this.searchBox = new EditBox(this.font, x + groupWidth + 8, top, searchWidth, 20, hint);
        this.searchBox.setMaxLength(64);
        this.searchBox.setHint(hint);
        this.searchBox.setValue(this.statQuery);
        this.searchBox.setResponder(
                text -> {
                    this.statQuery = text;
                    if (this.statList != null) {
                        this.statList.reload();
                    }
                });
        this.addRenderableWidget(this.searchBox);
        int resetX = x + groupWidth + 8 + searchWidth + 8;
        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("config.chaospersists.reset_shown"),
                                btn -> {
                                    ChaosNumericStats.reset(this.filteredStats());
                                    if (this.statList != null) {
                                        this.statList.reload();
                                    }
                                })
                        .tooltip(Tooltip.create(Component.translatable("config.chaospersists.tooltip.reset_shown")))
                        .bounds(resetX, top, small, 20)
                        .build());
        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("config.chaospersists.reset_all"),
                                btn -> {
                                    ChaosNumericStats.resetAll();
                                    if (this.statList != null) {
                                        this.statList.reload();
                                    }
                                })
                        .tooltip(Tooltip.create(Component.translatable("config.chaospersists.tooltip.reset_all")))
                        .bounds(resetX + small + 4, top, small, 20)
                        .build());
        this.statList = new StatList(this.minecraft, this.width, this.height, top + 26, this.height - 32, 24);
        this.addWidget(this.statList);
    }

    private Component statFilterLabel(StatFilter filter) {
        return switch (filter) {
            case ALL -> Component.translatable("config.chaospersists.stat.filter.all");
            case ARMOR -> Component.translatable("config.chaospersists.stat.filter.armor");
            case WEAPON -> Component.translatable("config.chaospersists.stat.filter.weapon");
            case ORE -> Component.translatable("config.chaospersists.stat.filter.ore");
            case MOB -> Component.translatable("config.chaospersists.stat.filter.mob");
        };
    }

    private List<OptionDef> generalOptions() {
        List<OptionDef> options = new ArrayList<>();
        options.add(header("overworld"));
        options.add(disable("overworldAll", () -> ChaosPersists.DisableOverworldFeatures, this::setOverworldMasterDisabled));
        options.add(disable("overworldSpawns", () -> ChaosPersists.DisableOverworldNaturalSpawning, v -> ChaosPersists.DisableOverworldNaturalSpawning = v));
        options.add(disable("overworldOre", () -> ChaosPersists.DisableOverworldDriedOre, v -> ChaosPersists.DisableOverworldDriedOre = v));
        options.add(disable("overworldEggs", () -> ChaosPersists.DisableOverworldDriedEggs, v -> ChaosPersists.DisableOverworldDriedEggs = v));
        options.add(disable("overworldAnts", () -> ChaosPersists.DisableOverworldAntNests, v -> ChaosPersists.DisableOverworldAntNests = v));
        options.add(disable("overworldDungeons", () -> ChaosPersists.DisableOverworldDungeons, v -> ChaosPersists.DisableOverworldDungeons = v));
        options.add(header("griefing"));
        options.add(grief("robotsGrief", () -> ChaosPersists.RobotsGriefing, v -> ChaosPersists.RobotsGriefing = v));
        options.add(grief("bossesGrief", () -> ChaosPersists.BossesGriefing, v -> ChaosPersists.BossesGriefing = v));
        options.add(grief("crittersGrief", () -> ChaosPersists.CrittersGriefing, v -> ChaosPersists.CrittersGriefing = v));
        options.add(grief("otherGrief", () -> ChaosPersists.OtherMobsGriefing, v -> ChaosPersists.OtherMobsGriefing = v));
        options.add(header("gameplay"));
        options.add(bool("AllMobsDisable", () -> ChaosPersists.AllMobsDisable, v -> ChaosPersists.AllMobsDisable = v));
        options.add(bool("IgnorePercentHealthDamage", () -> ChaosPersists.IgnorePercentHealthDamage, v -> ChaosPersists.IgnorePercentHealthDamage = v));
        options.add(bool("PlayNicely", () -> ChaosPersists.PlayNicely, v -> ChaosPersists.PlayNicely = v));
        options.add(bool("LessOre", () -> ChaosPersists.LessOre, v -> ChaosPersists.LessOre = v));
        options.add(bool("LessLag", () -> ChaosPersists.LessLag, v -> ChaosPersists.LessLag = v));
        options.add(bool("GuiOverlayEnable", () -> ChaosPersists.GuiOverlayEnable, v -> ChaosPersists.GuiOverlayEnable = v));
        options.add(bool("UltimateSwordPvp", () -> ChaosPersists.ultimate_sword_pvp, v -> ChaosPersists.ultimate_sword_pvp = v));
        options.add(bool("BigBerthaPvp", () -> ChaosPersists.big_bertha_pvp, v -> ChaosPersists.big_bertha_pvp = v));
        options.add(bool("BoyfriendBroMode", () -> ChaosPersists.bro_mode, v -> ChaosPersists.bro_mode = v));
        options.add(bool("RatPlayerFriendly", () -> ChaosPersists.RatPlayerFriendly, v -> ChaosPersists.RatPlayerFriendly = v));
        options.add(bool("RatPetFriendly", () -> ChaosPersists.RatPetFriendly, v -> ChaosPersists.RatPetFriendly = v));
        options.add(bool("DuplicatorTreeEnable", () -> ChaosPersists.enableduplicatortree, v -> ChaosPersists.enableduplicatortree = v));
        options.add(bool("RoyalGlideEnable", () -> ChaosPersists.RoyalGlideEnable, v -> ChaosPersists.RoyalGlideEnable = v));
        options.add(bool("DragonflyHorseFriendly", () -> ChaosPersists.DragonflyHorseFriendly, v -> ChaosPersists.DragonflyHorseFriendly = v));
        options.add(bool("MinersDreamExpensive", () -> ChaosPersists.MinersDreamExpensive, v -> ChaosPersists.MinersDreamExpensive = v));
        options.add(bool("FullPowerKingEnable", () -> ChaosPersists.FullPowerKingEnable, v -> ChaosPersists.FullPowerKingEnable = v));
        options.add(bool("PrinceAutoGrow", () -> ChaosPersists.PrinceAutoGrow, v -> ChaosPersists.PrinceAutoGrow = v));
        options.add(bool("GinormousEmeraldTreeEnable", () -> ChaosPersists.GinormousEmeraldTreeEnable, v -> ChaosPersists.GinormousEmeraldTreeEnable = v));
        options.add(integer("NightmareSize", () -> ChaosPersists.NightmareSize, v -> ChaosPersists.NightmareSize = v, 0, 1, 2, 3, 4, 5));
        options.add(integer("IslandSizeFactor", () -> ChaosPersists.IslandSizeFactor, v -> ChaosPersists.IslandSizeFactor = v, 1, 2, 3, 4, 5));
        options.add(integer("IslandSpeedFactor", () -> ChaosPersists.IslandSpeedFactor, v -> ChaosPersists.IslandSpeedFactor = v, 1, 2, 3, 4, 5));
        return options;
    }

    private static OptionDef header(String key) {
        return new OptionDef(key, Kind.HEADER, () -> 0, v -> {}, new int[0]);
    }

    private static OptionDef bool(String key, IntSupplier getter, IntConsumer setter) {
        return new OptionDef(key, Kind.BOOL, getter, setter, new int[0]);
    }

    private void setOverworldMasterDisabled(int disabled) {
        ChaosPersists.DisableOverworldFeatures = disabled;
        ChaosPersists.DisableOverworldNaturalSpawning = disabled;
        ChaosPersists.DisableOverworldDriedOre = disabled;
        ChaosPersists.DisableOverworldDriedEggs = disabled;
        ChaosPersists.DisableOverworldAntNests = disabled;
        ChaosPersists.DisableOverworldDungeons = disabled;
        if (this.minecraft != null) {
            this.minecraft.execute(this::rebuildWidgets);
        }
    }

    private static OptionDef disable(String key, IntSupplier getter, IntConsumer setter) {
        return new OptionDef(key, Kind.DISABLE, getter, setter, new int[0]);
    }

    private static OptionDef grief(String key, IntSupplier getter, IntConsumer setter) {
        return new OptionDef(key, Kind.GRIEF, getter, setter, new int[] {0, 1, 2});
    }

    private static OptionDef integer(String key, IntSupplier getter, IntConsumer setter, int... values) {
        return new OptionDef(key, Kind.INT, getter, setter, values);
    }

    private List<String> filteredStructureIds() {
        List<String> ids = new ArrayList<>();
        String query = this.structureQuery.toLowerCase(Locale.ROOT).trim();
        for (String id : ChaosPackConfig.STRUCTURE_IDS) {
            Component name = Component.translatable("structure.chaospersists." + id);
            if (query.isEmpty()
                    || id.contains(query)
                    || name.getString().toLowerCase(Locale.ROOT).contains(query)) {
                ids.add(id);
            }
        }
        return ids;
    }

    private List<ChaosMobToggles.Toggle> filteredMobs() {
        List<ChaosMobToggles.Toggle> matches = new ArrayList<>();
        String query = this.mobQuery.toLowerCase(Locale.ROOT).trim();
        for (ChaosMobToggles.Toggle toggle : ChaosMobToggles.ALL) {
            Component name = Component.translatable("config.chaospersists.mob." + toggle.configKey());
            if (query.isEmpty()
                    || toggle.configKey().toLowerCase(Locale.ROOT).contains(query)
                    || name.getString().toLowerCase(Locale.ROOT).contains(query)) {
                matches.add(toggle);
            }
        }
        return matches;
    }

    private List<ChaosNumericStats.Field> filteredStats() {
        List<ChaosNumericStats.Field> matches = new ArrayList<>();
        String query = this.statQuery.toLowerCase(Locale.ROOT).trim();
        for (ChaosNumericStats.Field field : ChaosNumericStats.ALL) {
            if (this.statFilter != StatFilter.ALL && field.group() != ChaosNumericStats.Group.valueOf(this.statFilter.name())) {
                continue;
            }
            Component label = statLabel(field);
            if (query.isEmpty()
                    || field.cfgKey().toLowerCase(Locale.ROOT).contains(query)
                    || field.setKey().toLowerCase(Locale.ROOT).contains(query)
                    || field.statKey().toLowerCase(Locale.ROOT).contains(query)
                    || label.getString().toLowerCase(Locale.ROOT).contains(query)) {
                matches.add(field);
            }
        }
        return matches;
    }

    private void setMobsEnabled(List<ChaosMobToggles.Toggle> toggles, boolean enabled) {
        int value = enabled ? 1 : 0;
        for (ChaosMobToggles.Toggle toggle : toggles) {
            if ("MothraPeaceful".equals(toggle.configKey())) {
                continue;
            }
            toggle.setter().accept(value);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.searchBox != null) {
            this.searchBox.tick();
        }
        if (this.statList != null) {
            this.statList.tickBoxes();
        }
    }

    @Override
    public void onClose() {
        ChaosPackConfig.saveFromGui();
        ChaosCreativeTabs.refresh();
        this.minecraft.setScreen(this.parent);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 8, 0xFFFFFF);
        if (this.optionList != null) {
            this.optionList.render(graphics, mouseX, mouseY, partialTick);
        }
        if (this.structureList != null) {
            this.structureList.render(graphics, mouseX, mouseY, partialTick);
        }
        if (this.mobList != null) {
            this.mobList.render(graphics, mouseX, mouseY, partialTick);
        }
        if (this.statList != null) {
            this.statList.render(graphics, mouseX, mouseY, partialTick);
        }
        super.render(graphics, mouseX, mouseY, partialTick);
    }

    private static Component optionLabel(String key) {
        return Component.translatable("config.chaospersists.option." + key);
    }

    private static Tooltip optionTooltip(String key) {
        return Tooltip.create(Component.translatable("config.chaospersists.tooltip." + key));
    }

    private static Component griefLabel(int value) {
        return switch (value) {
            case 1 -> Component.translatable("config.chaospersists.grief.always");
            case 2 -> Component.translatable("config.chaospersists.grief.never");
            default -> Component.translatable("config.chaospersists.grief.follow");
        };
    }

    private static Component intLabel(String key, int value) {
        if ("NightmareSize".equals(key) && value == 0) {
            return Component.translatable("config.chaospersists.nightmare.default");
        }
        return Component.literal(String.valueOf(value));
    }

    private static Component structureTooltipText(String id) {
        String dims = ChaosLocateStructures.dimensionDisplayNames(ChaosLocateStructures.key(id));
        if (dims == null || dims.isEmpty()) {
            dims = Component.translatable("config.chaospersists.structure.unknown_dim").getString();
        }
        return Component.translatable("config.chaospersists.structure.tooltip", dims);
    }

    private static Component statLabel(ChaosNumericStats.Field field) {
        return Component.translatable(
                "config.chaospersists.stat.row",
                Component.translatable("config.chaospersists.statset." + field.setKey()),
                Component.translatable("config.chaospersists.stat." + field.statKey()));
    }

    private static CycleButton<?> optionButton(OptionDef def) {
        Component label = optionLabel(def.key);
        Tooltip tooltip = optionTooltip(def.key);
        return switch (def.kind) {
            case HEADER -> null;
            case BOOL -> CycleButton.onOffBuilder(def.getter.getAsInt() != 0)
                    .withTooltip(value -> tooltip)
                    .create(0, 0, 190, 20, label, (button, value) -> def.setter.accept(value ? 1 : 0));
            case DISABLE -> CycleButton.onOffBuilder(def.getter.getAsInt() == 0)
                    .withTooltip(value -> tooltip)
                    .create(0, 0, 190, 20, label, (button, value) -> def.setter.accept(value ? 0 : 1));
            case GRIEF -> CycleButton.<Integer>builder(ChaosConfigScreen::griefLabel)
                    .withValues(0, 1, 2)
                    .withInitialValue(def.getter.getAsInt())
                    .withTooltip(value -> tooltip)
                    .create(0, 0, 190, 20, label, (button, value) -> def.setter.accept(value));
            case INT -> {
                List<Integer> values = new ArrayList<>();
                for (int value : def.values) {
                    values.add(value);
                }
                yield CycleButton.<Integer>builder(value -> intLabel(def.key, value))
                        .withValues(values)
                        .withInitialValue(def.getter.getAsInt())
                        .withTooltip(value -> tooltip)
                        .create(0, 0, 190, 20, label, (button, value) -> def.setter.accept(value));
            }
        };
    }

    private record OptionDef(String key, Kind kind, IntSupplier getter, IntConsumer setter, int[] values) {}

    private final class OptionList extends ContainerObjectSelectionList<PairWidgetRow> {
        private OptionList(
                Minecraft minecraft,
                int width,
                int height,
                int y0,
                int y1,
                int itemHeight,
                List<OptionDef> options) {
            super(minecraft, width, height, y0, y1, itemHeight);
            List<OptionDef> pair = new ArrayList<>();
            for (OptionDef def : options) {
                if (def.kind == Kind.HEADER) {
                    this.flushPair(pair);
                    this.addEntry(new PairWidgetRow(def.key));
                } else {
                    pair.add(def);
                    if (pair.size() == 2) {
                        this.flushPair(pair);
                    }
                }
            }
            this.flushPair(pair);
        }

        private void flushPair(List<OptionDef> pair) {
            if (pair.isEmpty()) {
                return;
            }
            CycleButton<?> left = optionButton(pair.get(0));
            CycleButton<?> right = pair.size() > 1 ? optionButton(pair.get(1)) : null;
            this.addEntry(new PairWidgetRow(left, right));
            pair.clear();
        }

        @Override
        public int getRowWidth() {
            return 400;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width / 2 + 210;
        }
    }

    private final class StructureList extends ContainerObjectSelectionList<PairWidgetRow> {
        private StructureList(Minecraft minecraft, int width, int height, int y0, int y1, int itemHeight) {
            super(minecraft, width, height, y0, y1, itemHeight);
            this.reload();
        }

        private void reload() {
            this.clearEntries();
            List<String> ids = ChaosConfigScreen.this.filteredStructureIds();
            for (int i = 0; i < ids.size(); i += 2) {
                String left = ids.get(i);
                String right = i + 1 < ids.size() ? ids.get(i + 1) : null;
                this.addEntry(
                        new PairWidgetRow(
                                structureToggle(left), right == null ? null : structureToggle(right)));
            }
        }

        private CycleButton<Boolean> structureToggle(String id) {
            Tooltip tooltip = Tooltip.create(structureTooltipText(id));
            return CycleButton.onOffBuilder(ChaosPackConfig.isStructureEnabled(id))
                    .withTooltip(value -> tooltip)
                    .create(
                            0,
                            0,
                            190,
                            20,
                            Component.translatable("structure.chaospersists." + id),
                            (button, value) -> ChaosPackConfig.setStructureEnabled(id, value));
        }

        @Override
        public int getRowWidth() {
            return 400;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width / 2 + 210;
        }
    }

    private final class MobList extends ContainerObjectSelectionList<PairWidgetRow> {
        private MobList(Minecraft minecraft, int width, int height, int y0, int y1, int itemHeight) {
            super(minecraft, width, height, y0, y1, itemHeight);
            this.reload();
        }

        private void reload() {
            this.clearEntries();
            List<ChaosMobToggles.Toggle> toggles = ChaosConfigScreen.this.filteredMobs();
            for (int i = 0; i < toggles.size(); i += 2) {
                ChaosMobToggles.Toggle left = toggles.get(i);
                ChaosMobToggles.Toggle right = i + 1 < toggles.size() ? toggles.get(i + 1) : null;
                this.addEntry(
                        new PairWidgetRow(mobToggle(left), right == null ? null : mobToggle(right)));
            }
        }

        private CycleButton<Boolean> mobToggle(ChaosMobToggles.Toggle toggle) {
            String tooltipKey =
                    "MothraPeaceful".equals(toggle.configKey()) ? "MothraPeaceful" : "mobEnable";
            Tooltip tooltip = optionTooltip(tooltipKey);
            return CycleButton.onOffBuilder(toggle.getter().getAsInt() != 0)
                    .withTooltip(value -> tooltip)
                    .create(
                            0,
                            0,
                            190,
                            20,
                            Component.translatable("config.chaospersists.mob." + toggle.configKey()),
                            (button, value) -> toggle.setter().accept(value ? 1 : 0));
        }

        @Override
        public int getRowWidth() {
            return 400;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width / 2 + 210;
        }
    }

    private final class StatList extends ContainerObjectSelectionList<StatRow> {
        private StatList(Minecraft minecraft, int width, int height, int y0, int y1, int itemHeight) {
            super(minecraft, width, height, y0, y1, itemHeight);
            this.reload();
        }

        private void reload() {
            this.clearEntries();
            for (ChaosNumericStats.Field field : ChaosConfigScreen.this.filteredStats()) {
                this.addEntry(new StatRow(field));
            }
        }

        private void tickBoxes() {
            this.children().forEach(StatRow::tick);
        }

        @Override
        public int getRowWidth() {
            return 400;
        }

        @Override
        protected int getScrollbarPosition() {
            return this.width / 2 + 210;
        }
    }

    private final class StatRow extends ContainerObjectSelectionList.Entry<StatRow> {
        private final Component label;
        private final EditBox box;

        private StatRow(ChaosNumericStats.Field field) {
            this.label = statLabel(field);
            this.box =
                    new EditBox(
                            ChaosConfigScreen.this.font,
                            0,
                            0,
                            70,
                            18,
                            this.label);
            this.box.setMaxLength(8);
            this.box.setValue(Integer.toString(field.getter().getAsInt()));
            this.box.setFilter(text -> text.isEmpty() || text.matches("-?\\d+"));
            this.box.setResponder(
                    text -> {
                        if (text.isEmpty() || "-".equals(text)) {
                            return;
                        }
                        try {
                            field.setter().accept(Integer.parseInt(text));
                        } catch (NumberFormatException ignored) {
                        }
                    });
            this.box.setTooltip(Tooltip.create(Component.translatable("config.chaospersists.tooltip.stat." + field.statKey())));
        }

        private void tick() {
            this.box.tick();
        }

        @Override
        public void render(
                GuiGraphics graphics,
                int index,
                int top,
                int left,
                int width,
                int height,
                int mouseX,
                int mouseY,
                boolean hovering,
                float partialTick) {
            int x = ChaosConfigScreen.this.width / 2 - 195;
            graphics.drawString(ChaosConfigScreen.this.font, this.label, x, top + 6, 0xFFFFFF);
            this.box.setX(x + 325);
            this.box.setY(top + 1);
            this.box.render(graphics, mouseX, mouseY, partialTick);
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return List.of(this.box);
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return List.of(this.box);
        }
    }

    private final class PairWidgetRow extends ContainerObjectSelectionList.Entry<PairWidgetRow> {
        private final String headerKey;
        private final CycleButton<?> left;
        private final CycleButton<?> right;

        private PairWidgetRow(String headerKey) {
            this.headerKey = headerKey;
            this.left = null;
            this.right = null;
        }

        private PairWidgetRow(CycleButton<?> left, CycleButton<?> right) {
            this.headerKey = null;
            this.left = left;
            this.right = right;
        }

        @Override
        public void render(
                GuiGraphics graphics,
                int index,
                int top,
                int left,
                int width,
                int height,
                int mouseX,
                int mouseY,
                boolean hovering,
                float partialTick) {
            if (this.headerKey != null) {
                graphics.drawCenteredString(
                        ChaosConfigScreen.this.font,
                        Component.translatable("config.chaospersists.header." + this.headerKey),
                        ChaosConfigScreen.this.width / 2,
                        top + 6,
                        0xFFFFA0);
                return;
            }
            int x = ChaosConfigScreen.this.width / 2 - 195;
            this.left.setX(x);
            this.left.setY(top);
            this.left.render(graphics, mouseX, mouseY, partialTick);
            if (this.right != null) {
                this.right.setX(x + 200);
                this.right.setY(top);
                this.right.render(graphics, mouseX, mouseY, partialTick);
            }
        }

        @Override
        public List<? extends GuiEventListener> children() {
            if (this.headerKey != null) {
                return List.of();
            }
            if (this.right == null) {
                return List.of(this.left);
            }
            return List.of(this.left, this.right);
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            if (this.headerKey != null) {
                return List.of();
            }
            if (this.right == null) {
                return List.of(this.left);
            }
            return List.of(this.left, this.right);
        }
    }
}
