package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.util.MyUtils;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.PitchBlack;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class ItemSpawnEgg extends Item {
    public int my_id = 0;
    public final int backgroundColor;
    public final int highlightColor;

    public ItemSpawnEgg(int i, int j) {
        this(i, j, -1, -1);
    }

    public ItemSpawnEgg(int i, int j, int backgroundColor, int highlightColor) {
        super(new Properties().stacksTo(64));
        this.my_id = j;
        this.backgroundColor = backgroundColor;
        this.highlightColor = highlightColor;
    }

    public boolean hasSpawnEggColors() {
        return this.backgroundColor >= 0 && this.highlightColor >= 0;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        // Same path as vanilla SpawnEggItem: configure monster spawners instead of spawning on top.
        if (level.getBlockEntity(pos) instanceof SpawnerBlockEntity spawner) {
            EntityType<?> type = resolveEggEntityType(this.my_id);
            if (type == null) {
                return InteractionResult.FAIL;
            }
            spawner.setEntityId(type, level.getRandom());
            level.sendBlockUpdated(pos, state, state, 3);
            level.gameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, pos);
            Player player = context.getPlayer();
            if (player == null || !player.getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }
            return InteractionResult.SUCCESS;
        }
        double spawnX = pos.getX() + 0.5;
        double spawnY = pos.getY() + 1.0;
        double spawnZ = pos.getZ() + 0.5;
        if (doSpawn(context.getItemInHand(), context.getPlayer(), level, spawnX, spawnY, spawnZ)) {
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }

    private boolean doSpawn(ItemStack stack, Player player, Level level, double spawnX, double spawnY, double spawnZ) {
        Entity ent = ItemSpawnEgg.spawn_something(this.my_id, level, spawnX, spawnY, spawnZ);
        if (ent == null) {
            return false;
        }
        if (ent instanceof PitchBlack pitchBlack) {
            pitchBlack.setSpawnedFromEgg();
        }
        if (ent instanceof Godzilla) {
            ChaosPersists.godzilla_has_spawned = 1;
        }
        if (ent instanceof LivingEntity living && stack.hasCustomHoverName()) {
            living.setCustomName(stack.getHoverName());
        }
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
        return true;
    }

    public static Entity spawn_something(int id, Level level, double d0, double d1, double d2) {
        return spawnEntityType(level, resolveEggEntityType(id), d0, d1, d2);
    }

    /** Entity type this egg configures on a monster spawner / spawns in the world. */
    @Nullable
    public static EntityType<?> resolveEggEntityType(int id) {
        int entityID = 0;
        int skelly_type = 0;
        String name = null;
        switch (id) {
            case 192 -> {
                skelly_type = 1;
                entityID = 51;
            }
            case 193 -> entityID = 63;
            case 194 -> entityID = 97;
            case 195 -> entityID = 99;
            case 196 -> entityID = 64;
            case 197 -> name = "girlfriend";
            case 198 -> name = "apple_cow";
            case 363 -> name = "crystal_apple_cow";
            case 199 -> name = "golden_apple_cow";
            case 200 -> name = "enchanted_golden_apple_cow";
            case 202 -> name = "alosaurus";
            case 203 -> name = "cryolophosaurus";
            case 204 -> name = "camarasaurus";
            case 205 -> name = "velocity_raptor";
            case 206 -> name = "hydrolisc";
            case 207 -> name = "basilisk";
            case 201 -> name = "mothra";
            case 221 -> name = "dragonfly";
            case 223 -> name = "emperor_scorpion";
            case 225 -> name = "scorpion";
            case 227 -> name = "cave_fisher";
            case 229 -> name = "baby_dragon";
            case 231 -> name = "baryonyx";
            case 233 -> name = "gamma_metroid";
            case 235 -> name = "bird";
            case 237 -> name = "kyuubi";
            case 239 -> name = "alien";
            case 241 -> name = "attack_squid";
            case 243 -> name = "water_dragon";
            case 245 -> name = "the_kraken";
            case 247 -> name = "lizard";
            case 249 -> name = "cephadrome";
            case 251 -> name = "dragon";
            case 254 -> name = "bee";
            case 262 -> name = "jumpy_bug";
            case 263 -> name = "spit_bug";
            case 264 -> name = "stink_bug";
            case 265 -> name = "ostrich";
            case 266 -> name = "gazelle";
            case 267 -> name = "chipmunk";
            case 274 -> name = "creeping_horror";
            case 275 -> name = "terrible_terror";
            case 276 -> name = "cliff_racer";
            case 277 -> name = "triffid";
            case 278 -> name = "nightmare";
            case 279 -> name = "lurking_terror";
            case 288 -> name = "small_worm";
            case 289 -> name = "medium_worm";
            case 290 -> name = "large_worm";
            case 291 -> name = "cassowary";
            case 292 -> name = "cloud_shark";
            case 293 -> name = "gold_fish";
            case 294 -> name = "leaf_monster";
            case 295 -> name = "tshirt";
            case 280 -> name = "mobzilla";
            case 298 -> name = "ender_knight";
            case 299 -> name = "ender_reaper";
            case 301 -> name = "beaver";
            case 306 -> name = "dungeon_beast";
            case 303 -> name = "vortex";
            case 302 -> name = "rotator";
            case 304 -> name = "peacock";
            case 305 -> name = "fairy";
            case 307 -> name = "rat";
            case 308 -> name = "flounder";
            case 309 -> name = "whale";
            case 310 -> name = "irukandji";
            case 311 -> name = "skate";
            case 312 -> name = "crystal_urchin";
            case 324 -> name = "bomb_omb";
            case 325 -> name = "robo_pounder";
            case 326 -> name = "robo_gunner";
            case 327 -> name = "robo_warrior";
            case 328 -> name = "ghost";
            case 329 -> name = "ghost_pumpkin_skelly";
            case 330 -> name = "ant";
            case 331 -> name = "red_ant";
            case 332 -> name = "rainbow_ant";
            case 333 -> name = "unstable_ant";
            case 334 -> name = "termite";
            case 335 -> name = "butterfly";
            case 336 -> name = "moth";
            case 337 -> name = "mosquito";
            case 338 -> name = "firefly";
            case 339 -> name = "trex";
            case 340 -> name = "hercules_beetle";
            case 341 -> name = "mantis";
            case 342 -> name = "stinky";
            case 343 -> name = "robo_sniper";
            case 344 -> name = "coin";
            case 349 -> name = "boyfriend";
            case 350 -> name = "the_king";
            case 366 -> name = "the_queen";
            case 351 -> name = "the_prince";
            case 352 -> name = "easter_bunny";
            case 353 -> name = "molenoid";
            case 354 -> name = "sea_monster";
            case 355 -> name = "sea_viper";
            case 356 -> name = "caterkiller";
            case 358 -> name = "leonopteryx";
            case 360 -> name = "hammerhead";
            case 362 -> name = "rubber_ducky";
            case 365 -> name = "criminal";
            case 367 -> name = "brutalfly";
            case 368 -> name = "nastysaurus";
            case 369 -> name = "pointysaurus";
            case 370 -> name = "cricket";
            case 371 -> name = "the_princess";
            case 372 -> name = "frog";
            case 378 -> name = "jeffery";
            case 379 -> name = "robot_red_ant";
            case 380 -> name = "robot_spider";
            case 381 -> name = "spider_driver";
            case 383 -> name = "crab";
            case 385 -> name = "rock";
            default -> {
            }
        }
        return resolveEggEntityType(entityID, skelly_type, name);
    }

    @Nullable
    private static EntityType<?> resolveEggEntityType(int entityId, int skellyType, @Nullable String name) {
        if (name != null) {
            ResourceLocation loc = legacySpawnNameToRegistry(name);
            if (loc != null) {
                return ForgeRegistries.ENTITY_TYPES.getValue(loc);
            }
            return null;
        }
        if (entityId == 0) {
            return null;
        }
        if (entityId == 51 && skellyType != 0) {
            return EntityType.WITHER_SKELETON;
        }
        return legacyVanillaEntityType(entityId);
    }

    public static Entity spawnCreature(Level level, int entityId, String name, double x, double y, double z) {
        return spawnCreature(level, entityId, name, 0, x, y, z);
    }

    public static Entity spawnCreature(
            Level level, int entityId, String name, int skellyType, double x, double y, double z) {
        EntityType<?> type = resolveEggEntityType(entityId, skellyType, name);
        Entity entity = spawnEntityType(level, type, x, y, z);
        if (entity instanceof Mob mob && level instanceof ServerLevel serverLevel) {
            if (entityId == 100 || entityId == 120) {
                BlockPos spawnPos = BlockPos.containing(x, y, z);
                mob.finalizeSpawn(
                        serverLevel,
                        serverLevel.getCurrentDifficultyAt(spawnPos),
                        MobSpawnType.SPAWN_EGG,
                        null,
                        null);
            }
        }
        return entity;
    }

    private static Entity spawnVanillaCreature(Level level, EntityType<?> type, double x, double y, double z) {
        return spawnEntityType(level, type, x, y, z);
    }

    private static Entity spawnEntityType(Level level, EntityType<?> type, double x, double y, double z) {
        if (type == null || !(level instanceof ServerLevel serverLevel)) {
            return null;
        }
        Entity entity = type.create(serverLevel);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, level.getRandom().nextFloat() * 360.0F, 0.0F);
        boolean restorePhysics = false;
        if (entity instanceof Godzilla) {
            restorePhysics = !entity.noPhysics;
            entity.noPhysics = true;
        }
        if (entity instanceof Mob mob) {
            BlockPos spawnPos = BlockPos.containing(x, y, z);
            mob.finalizeSpawn(
                    serverLevel,
                    serverLevel.getCurrentDifficultyAt(spawnPos),
                    MobSpawnType.SPAWN_EGG,
                    null,
                    null);
            mob.setPersistenceRequired();
            MyUtils.playAmbientSound(mob);
        }
        if (!serverLevel.addFreshEntity(entity)) {
            return null;
        }
        if (restorePhysics) {
            entity.noPhysics = false;
        }
        return entity;
    }

    /** 1.12 numeric entity ids used by {@link CritterCage} filled cages (EntityList.getClassFromID). */
    private static EntityType<?> legacyVanillaEntityType(int legacyId) {
        return switch (legacyId) {
            case 50 -> EntityType.CREEPER;
            case 51 -> EntityType.SKELETON;
            case 52 -> EntityType.SPIDER;
            case 54 -> EntityType.ZOMBIE;
            case 55 -> EntityType.SLIME;
            case 56 -> EntityType.GHAST;
            case 57 -> EntityType.ZOMBIFIED_PIGLIN;
            case 58 -> EntityType.ENDERMAN;
            case 59 -> EntityType.CAVE_SPIDER;
            case 60 -> EntityType.SILVERFISH;
            case 61 -> EntityType.BLAZE;
            case 62 -> EntityType.MAGMA_CUBE;
            case 63 -> EntityType.ENDER_DRAGON;
            case 64 -> EntityType.WITHER;
            case 65 -> EntityType.BAT;
            case 66 -> EntityType.WITCH;
            case 90 -> EntityType.PIG;
            case 91 -> EntityType.SHEEP;
            case 92 -> EntityType.COW;
            case 93 -> EntityType.CHICKEN;
            case 94 -> EntityType.SQUID;
            case 95 -> EntityType.WOLF;
            case 96 -> EntityType.MOOSHROOM;
            case 97 -> EntityType.SNOW_GOLEM;
            case 98 -> EntityType.CAT;
            case 99 -> EntityType.IRON_GOLEM;
            case 100 -> EntityType.HORSE;
            case 120 -> EntityType.VILLAGER;
            default -> null;
        };
    }

    private static ResourceLocation legacySpawnNameToRegistry(String legacyName) {
        if (legacyName == null) {
            return null;
        }
        switch (legacyName) {
            case "Rat":
                return new ResourceLocation("chaospersists", "rat");
            case "Fairy":
                return new ResourceLocation("chaospersists", "fairy");
            case "Red Ant":
                return new ResourceLocation("chaospersists", "red_ant");
            case "Termite":
                return new ResourceLocation("chaospersists", "termite");
            default:
                return new ResourceLocation(
                        "chaospersists", legacyName.toLowerCase(java.util.Locale.ROOT).replace(' ', '_'));
        }
    }
}

