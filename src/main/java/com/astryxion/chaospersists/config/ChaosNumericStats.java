package com.astryxion.chaospersists.config;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.ArmorStats;
import com.astryxion.chaospersists.util.MobStats;
import com.astryxion.chaospersists.util.OreStats;
import com.astryxion.chaospersists.util.WeaponStats;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

/** Armor / weapon / ore / mob numbers from the cfg, edited on the Stats tab. */
public final class ChaosNumericStats {
    public static final String CAT_ARMOR = "chaospersistsARMOR";
    public static final String CAT_WEAPONS = "chaospersistsWEAPONS";
    public static final String CAT_ORES = "chaospersistsORES";
    public static final String CAT_MOBS = "chaospersistsMOBS";

    public enum Group {
        ARMOR,
        WEAPON,
        ORE,
        MOB
    }

    public record Field(
            Group group,
            String cfgCategory,
            String cfgKey,
            int cfgDefault,
            IntSupplier getter,
            IntConsumer setter,
            String setKey,
            String statKey) {}

    public static final List<Field> ALL = new ArrayList<>();

    static {
        armor("Amethyst", () -> ChaosPersists.Amethyst_armorstats, 100, 4, 8, 7, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
        armor("Emerald", () -> ChaosPersists.Emerald_armorstats, 60, 3, 8, 6, 3, 40, 0, 0, 0, 0, 0, 0, 0, 0);
        armor("Experience", () -> ChaosPersists.Experience_armorstats, 70, 5, 9, 7, 4, 50, 0, 0, 2, 0, 1, 0, 0, 1);
        armor("MothScale", () -> ChaosPersists.MothScale_armorstats, 50, 2, 7, 5, 2, 50, 0, 0, 3, 3, 3, 0, 0, 5);
        armor("LavaEel", () -> ChaosPersists.LavaEel_armorstats, 40, 2, 7, 5, 2, 35, 1, 2, 3, 2, 10, 0, 0, 2);
        armor("Ultimate", () -> ChaosPersists.Ultimate_armorstats, 200, 6, 12, 10, 6, 100, 2, 3, 5, 5, 5, 5, 0, 3);
        armor("Pink", () -> ChaosPersists.Pink_armorstats, 50, 3, 7, 5, 2, 40, 0, 0, 0, 0, 0, 0, 0, 0);
        armor("TigersEye", () -> ChaosPersists.TigersEye_armorstats, 80, 4, 8, 7, 4, 55, 0, 0, 0, 0, 0, 0, 0, 0);
        armor("Peacock", () -> ChaosPersists.Peacock_armorstats, 40, 2, 5, 4, 2, 30, 0, 0, 0, 0, 0, 0, 0, 10);
        armor("Mobzilla", () -> ChaosPersists.Mobzilla_armorstats, 1000, 7, 13, 11, 7, 150, 0, 0, 10, 10, 10, 10, 5, 10);
        armor("Ruby", () -> ChaosPersists.Ruby_armorstats, 90, 4, 9, 8, 4, 40, 0, 0, 0, 0, 0, 0, 0, 0);
        armor("Royal", () -> ChaosPersists.Royal_armorstats, 2000, 8, 14, 12, 8, 200, 1, 2, 10, 10, 10, 10, 5, 10);
        armor("Lapis", () -> ChaosPersists.Lapis_armorstats, 60, 2, 7, 5, 2, 60, 1, 1, 1, 0, 0, 1, 0, 0);
        armor("Queen", () -> ChaosPersists.Queen_armorstats, 1500, 9, 16, 14, 9, 150, 0, 0, 0, 0, 0, 0, 0, 0);

        weapon("Ultimate", () -> ChaosPersists.ultimate_stats, 10, 3000, 15, 36, 100);
        weapon("Nightmare", () -> ChaosPersists.nightmare_stats, 3, 1800, 12, 26, 60);
        weapon("Bertha", () -> ChaosPersists.bertha_stats, 3, 9000, 15, 496, 100);
        weapon("CrystalWood", () -> ChaosPersists.crystalwood_stats, 2, 300, 3, 2, 15);
        weapon("CrystalStone", () -> ChaosPersists.crystalstone_stats, 3, 800, 6, 5, 45);
        weapon("Pink", () -> ChaosPersists.crystalpink_stats, 4, 1100, 10, 7, 65);
        weapon("TigersEye", () -> ChaosPersists.tigerseye_stats, 4, 1600, 12, 8, 75);
        weapon("Ruby", () -> ChaosPersists.ruby_stats, 5, 1500, 11, 16, 85);
        weapon("Amethyst", () -> ChaosPersists.amethyst_stats, 4, 2000, 11, 11, 70);
        weapon("Emerald", () -> ChaosPersists.emerald_stats, 3, 1300, 10, 6, 75);
        weapon("Royal", () -> ChaosPersists.royal_stats, 3, 10000, 15, 746, 150);
        weapon("Attitude", () -> ChaosPersists.hammy_stats, 5, 2000, 15, 82, 100);
        weapon("BattleAxe", () -> ChaosPersists.battleaxe_stats, 3, 1500, 15, 46, 75);
        weapon("Chainsaw", () -> ChaosPersists.chainsaw_stats, 3, 1500, 10, 56, 75);
        weapon("QueenBattleAxe", () -> ChaosPersists.queenbattleaxe_stats, 3, 2200, 15, 662, 100);
        extra(Group.WEAPON, CAT_WEAPONS, "UltimateSwordEnchantmentLevel", 5, () -> ChaosPersists.UltimateSwordMagic, v -> ChaosPersists.UltimateSwordMagic = v, "UltimateSword", "enchant_level");
        extra(Group.WEAPON, CAT_WEAPONS, "UltimateBowDamage", 10, () -> ChaosPersists.UltimateBowDamage, v -> ChaosPersists.UltimateBowDamage = v, "UltimateBow", "damage");
        extra(Group.WEAPON, CAT_WEAPONS, "UltimateBowPetHeal", 1, () -> ChaosPersists.UltimateBowPetHeal, v -> ChaosPersists.UltimateBowPetHeal = v, "UltimateBow", "pet_heal");

        ore("Ruby", () -> ChaosPersists.Ruby_stats, 10, 1, 0, 50);
        ore("BlockRuby", () -> ChaosPersists.BlkRuby_stats, 1, 2, 0, 15);
        ore("Uranium", () -> ChaosPersists.Uranium_stats, 3, 4, 0, 30);
        ore("Titanium", () -> ChaosPersists.Titanium_stats, 3, 4, 0, 20);
        ore("Amethyst", () -> ChaosPersists.Amethyst_stats, 2, 6, 0, 25);
        ore("Salt", () -> ChaosPersists.Salt_stats, 5, 12, 50, 128);
        ore("SpawnOres", () -> ChaosPersists.SpawnOres_stats, 28, 4, 50, 128);
        ore("Diamond", () -> ChaosPersists.Diamond_stats, 4, 6, 0, 30);
        ore("BlockDiamond", () -> ChaosPersists.BlkDiamond_stats, 2, 4, 0, 20);
        ore("Emerald", () -> ChaosPersists.Emerald_stats, 4, 6, 0, 40);
        ore("BlockEmerald", () -> ChaosPersists.BlkEmerald_stats, 2, 4, 0, 20);
        ore("Gold", () -> ChaosPersists.Gold_stats, 4, 8, 0, 40);
        ore("BlockGold", () -> ChaosPersists.BlkGold_stats, 2, 4, 0, 25);

        mob("Bee", () -> ChaosPersists.Bee_stats, 80, 12, 5);
        mob("Mantis", () -> ChaosPersists.Mantis_stats, 120, 16, 10);
        mob("HerculesBeetle", () -> ChaosPersists.HerculesBeetle_stats, 250, 30, 19);
        mob("Mothra", () -> ChaosPersists.Mothra_stats, 150, 12, 8);
        mob("Brutalfly", () -> ChaosPersists.Brutalfly_stats, 110, 10, 6);
        mob("Nastysaurus", () -> ChaosPersists.Nastysaurus_stats, 200, 32, 17);
        mob("Pointysaurus", () -> ChaosPersists.Pointysaurus_stats, 80, 10, 16);
        mob("Alosaurus", () -> ChaosPersists.Alosaurus_stats, 110, 18, 8);
        mob("SpiderRobot", () -> ChaosPersists.SpiderRobot_stats, 1500, 100, 16);
        mob("AntRobot", () -> ChaosPersists.AntRobot_stats, 300, 30, 16);
        mob("Jeffery", () -> ChaosPersists.Jeffery_stats, 550, 40, 18);
        mob("Hammerhead", () -> ChaosPersists.Hammerhead_stats, 240, 75, 20);
        mob("Molenoid", () -> ChaosPersists.Molenoid_stats, 200, 18, 12);
        mob("TRex", () -> ChaosPersists.TRex_stats, 160, 22, 14);
        mob("BandP", () -> ChaosPersists.BandP_stats, 100, 1, 18);
        mob("CaterKiller", () -> ChaosPersists.CaterKiller_stats, 450, 32, 19);
        mob("Cryolophosaurus", () -> ChaosPersists.Cryolophosaurus_stats, 10, 3, 1);
        mob("Rat", () -> ChaosPersists.Rat_stats, 5, 3, 1);
        mob("Urchin", () -> ChaosPersists.Urchin_stats, 25, 10, 4);
        mob("Kyuubi", () -> ChaosPersists.Kyuubi_stats, 125, 10, 10);
        mob("GammaMetroid", () -> ChaosPersists.GammaMetroid_stats, 100, 10, 12);
        mob("Basilisk", () -> ChaosPersists.Basilisk_stats, 200, 24, 15);
        mob("EmperorScorpion", () -> ChaosPersists.EmperorScorpion_stats, 350, 35, 20);
        mob("TrooperBug", () -> ChaosPersists.TrooperBug_stats, 200, 20, 15);
        mob("SpitBug", () -> ChaosPersists.SpitBug_stats, 100, 10, 12);
        mob("Alien", () -> ChaosPersists.Alien_stats, 100, 12, 8);
        mob("WaterDragon", () -> ChaosPersists.WaterDragon_stats, 150, 20, 8);
        mob("SeaMonster", () -> ChaosPersists.SeaMonster_stats, 110, 14, 8);
        mob("SeaViper", () -> ChaosPersists.SeaViper_stats, 160, 22, 12);
        mob("Robot2", () -> ChaosPersists.Robot2_stats, 200, 22, 18);
        mob("Robot3", () -> ChaosPersists.Robot3_stats, 80, 16, 14);
        mob("Robot4", () -> ChaosPersists.Robot4_stats, 170, 12, 18);
        mob("Robot5", () -> ChaosPersists.Robot5_stats, 20, 5, 6);
        mob("Rotator", () -> ChaosPersists.Rotator_stats, 35, 10, 8);
        mob("Vortex", () -> ChaosPersists.Vortex_stats, 150, 26, 10);
        mob("DungeonBeast", () -> ChaosPersists.DungeonBeast_stats, 65, 12, 6);
        mob("Triffid", () -> ChaosPersists.Triffid_stats, 100, 20, 12);
        mob("LurkingTerror", () -> ChaosPersists.LurkingTerror_stats, 30, 6, 5);
        mob("WormSmall", () -> ChaosPersists.WormSmall_stats, 10, 3, 0);
        mob("WormMedium", () -> ChaosPersists.WormMedium_stats, 30, 10, 8);
        mob("WormLarge", () -> ChaosPersists.WormLarge_stats, 90, 18, 14);
        mob("EnderKnight", () -> ChaosPersists.EnderKnight_stats, 60, 12, 6);
        mob("EnderReaper", () -> ChaosPersists.EnderReaper_stats, 90, 18, 8);
        mob("Irukandji", () -> ChaosPersists.Irukandji_stats, 1, 20, 0);
        mob("AttackSquid", () -> ChaosPersists.AttackSquid_stats, 10, 8, 0);
        mob("CaveFisher", () -> ChaosPersists.CaveFisher_stats, 10, 4, 4);
        mob("CloudShark", () -> ChaosPersists.CloudShark_stats, 15, 6, 5);
        mob("CreepingHorror", () -> ChaosPersists.CreepingHorror_stats, 10, 3, 2);
        mob("Mobzilla", () -> ChaosPersists.Godzilla_stats, 4000, 175, 21);
        mob("Kraken", () -> ChaosPersists.Kraken_stats, 1000, 40, 10);
        mob("LeafMonster", () -> ChaosPersists.LeafMonster_stats, 6, 2, 1);
        mob("Nightmare", () -> ChaosPersists.PitchBlack_stats, 250, 30, 10);
        mob("Scorpion", () -> ChaosPersists.Scorpion_stats, 15, 4, 10);
        mob("Skate", () -> ChaosPersists.Skate_stats, 8, 8, 4);
        mob("TerribleTerror", () -> ChaosPersists.TerribleTerror_stats, 10, 5, 3);
        mob("TheKing", () -> ChaosPersists.TheKing_stats, 7000, 350, 21);
        mob("TheQueen", () -> ChaosPersists.TheQueen_stats, 6000, 225, 21);
        mob("Leonopteryx", () -> ChaosPersists.Leon_stats, 150, 20, 8);
        mob("Crab", () -> ChaosPersists.Crab_stats, 180, 24, 16);
    }

    private ChaosNumericStats() {}

    private static void armor(
            String id,
            Supplier<ArmorStats> src,
            int dura,
            int head,
            int chest,
            int leg,
            int boots,
            int ench,
            int eResp,
            int eAqua,
            int eProt,
            int eFire,
            int eBlast,
            int eProj,
            int eUnbreak,
            int eFeather) {
        add(
                Group.ARMOR,
                CAT_ARMOR,
                id + "_durability",
                dura,
                () -> src.get() == null ? dura : src.get().durability,
                v -> {
                    if (src.get() != null) {
                        src.get().durability = v;
                    }
                },
                id,
                "durability");
        add(Group.ARMOR, CAT_ARMOR, id + "_head_damage_reduce", head, () -> src.get() == null ? head : src.get().head_protection, v -> {
            if (src.get() != null) {
                src.get().head_protection = v;
            }
        }, id, "helmet");
        add(Group.ARMOR, CAT_ARMOR, id + "_chest_damage_reduce", chest, () -> src.get() == null ? chest : src.get().chest_protection, v -> {
            if (src.get() != null) {
                src.get().chest_protection = v;
            }
        }, id, "chest");
        add(Group.ARMOR, CAT_ARMOR, id + "_leggings_damage_reduce", leg, () -> src.get() == null ? leg : src.get().leg_protection, v -> {
            if (src.get() != null) {
                src.get().leg_protection = v;
            }
        }, id, "leggings");
        add(Group.ARMOR, CAT_ARMOR, id + "_boots_damage_reduce", boots, () -> src.get() == null ? boots : src.get().boot_protection, v -> {
            if (src.get() != null) {
                src.get().boot_protection = v;
            }
        }, id, "boots");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchantability", ench, () -> src.get() == null ? ench : src.get().enchantability, v -> {
            if (src.get() != null) {
                src.get().enchantability = v;
            }
        }, id, "enchantability");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_respiration", eResp, () -> src.get() == null ? eResp : src.get().e_respiration, v -> {
            if (src.get() != null) {
                src.get().e_respiration = v;
            }
        }, id, "craft_respiration");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_aquaaffinity", eAqua, () -> src.get() == null ? eAqua : src.get().e_aquaaffinity, v -> {
            if (src.get() != null) {
                src.get().e_aquaaffinity = v;
            }
        }, id, "craft_aqua");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_protection", eProt, () -> src.get() == null ? eProt : src.get().e_protection, v -> {
            if (src.get() != null) {
                src.get().e_protection = v;
            }
        }, id, "craft_protection");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_fireprotection", eFire, () -> src.get() == null ? eFire : src.get().e_fireprotection, v -> {
            if (src.get() != null) {
                src.get().e_fireprotection = v;
            }
        }, id, "craft_fire");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_blastprotection", eBlast, () -> src.get() == null ? eBlast : src.get().e_blastprotection, v -> {
            if (src.get() != null) {
                src.get().e_blastprotection = v;
            }
        }, id, "craft_blast");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_projectileprotection", eProj, () -> src.get() == null ? eProj : src.get().e_projectileprotection, v -> {
            if (src.get() != null) {
                src.get().e_projectileprotection = v;
            }
        }, id, "craft_projectile");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_unbreaking", eUnbreak, () -> src.get() == null ? eUnbreak : src.get().e_unbreaking, v -> {
            if (src.get() != null) {
                src.get().e_unbreaking = v;
            }
        }, id, "craft_unbreaking");
        add(Group.ARMOR, CAT_ARMOR, id + "_enchant_featherfalling", eFeather, () -> src.get() == null ? eFeather : src.get().e_featherfalling, v -> {
            if (src.get() != null) {
                src.get().e_featherfalling = v;
            }
        }, id, "craft_feather");
    }

    private static void weapon(String id, Supplier<WeaponStats> src, int harvest, int uses, int eff, int dmg, int ench) {
        add(Group.WEAPON, CAT_WEAPONS, id + "_harvestlevel", harvest, () -> src.get() == null ? harvest : src.get().harvestlevel, v -> {
            if (src.get() != null) {
                src.get().harvestlevel = v;
            }
        }, id, "harvest");
        add(Group.WEAPON, CAT_WEAPONS, id + "_maxuses", uses, () -> src.get() == null ? uses : src.get().maxuses, v -> {
            if (src.get() != null) {
                src.get().maxuses = v;
            }
        }, id, "maxuses");
        add(Group.WEAPON, CAT_WEAPONS, id + "_efficiency", eff, () -> src.get() == null ? eff : src.get().efficiency, v -> {
            if (src.get() != null) {
                src.get().efficiency = v;
            }
        }, id, "efficiency");
        add(Group.WEAPON, CAT_WEAPONS, id + "_damage", dmg, () -> src.get() == null ? dmg : src.get().damage, v -> {
            if (src.get() != null) {
                src.get().damage = v;
            }
        }, id, "damage");
        add(Group.WEAPON, CAT_WEAPONS, id + "_enchantability", ench, () -> src.get() == null ? ench : src.get().enchantability, v -> {
            if (src.get() != null) {
                src.get().enchantability = v;
            }
        }, id, "enchantability");
    }

    private static void ore(String id, Supplier<OreStats> src, int rate, int clump, int min, int max) {
        add(Group.ORE, CAT_ORES, id + "_rate", rate, () -> src.get() == null ? rate : src.get().rate, v -> {
            if (src.get() != null) {
                src.get().rate = v;
            }
        }, id, "rate");
        add(Group.ORE, CAT_ORES, id + "_clumpsize", clump, () -> src.get() == null ? clump : src.get().clumpsize, v -> {
            if (src.get() != null) {
                src.get().clumpsize = v;
            }
        }, id, "clump");
        add(Group.ORE, CAT_ORES, id + "_mindepth", min, () -> src.get() == null ? min : src.get().mindepth, v -> {
            if (src.get() != null) {
                src.get().mindepth = v;
            }
        }, id, "mindepth");
        add(Group.ORE, CAT_ORES, id + "_maxdepth", max, () -> src.get() == null ? max : src.get().maxdepth, v -> {
            if (src.get() != null) {
                src.get().maxdepth = v;
            }
        }, id, "maxdepth");
    }

    private static void mob(String id, Supplier<MobStats> src, int health, int attack, int defense) {
        add(Group.MOB, CAT_MOBS, id + "_health", health, () -> src.get() == null ? health : src.get().health, v -> {
            if (src.get() != null) {
                src.get().health = v;
            }
        }, id, "health");
        add(Group.MOB, CAT_MOBS, id + "_attack", attack, () -> src.get() == null ? attack : src.get().attack, v -> {
            if (src.get() != null) {
                src.get().attack = v;
            }
        }, id, "attack");
        add(Group.MOB, CAT_MOBS, id + "_defense", defense, () -> src.get() == null ? defense : src.get().defense, v -> {
            if (src.get() != null) {
                src.get().defense = v;
            }
        }, id, "defense");
    }

    private static void extra(
            Group group,
            String cat,
            String key,
            int def,
            IntSupplier getter,
            IntConsumer setter,
            String setKey,
            String statKey) {
        add(group, cat, key, def, getter, setter, setKey, statKey);
    }

    public static void reset(Field field) {
        field.setter().accept(field.cfgDefault());
    }

    public static void reset(Iterable<Field> fields) {
        for (Field field : fields) {
            reset(field);
        }
    }

    public static void resetAll() {
        reset(ALL);
    }

    private static void add(
            Group group,
            String cat,
            String key,
            int def,
            IntSupplier getter,
            IntConsumer setter,
            String setKey,
            String statKey) {
        ALL.add(new Field(group, cat, key, def, getter, setter, setKey, statKey));
    }
}
