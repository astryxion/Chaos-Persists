package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.PitchBlack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemSpawnEgg extends Item {
    public int my_id = 0;

    public ItemSpawnEgg(int i, int j) {
        super(new Item.Properties().stacksTo(64).tab(ItemGroup.TAB_MISC));
        this.my_id = j;
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        ItemStack stack = context.getItemInHand();
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        BlockPos pos = context.getClickedPos();
        double spawnX = pos.getX() + 0.5;
        double spawnY = pos.getY() + 1.0;
        double spawnZ = pos.getZ() + 0.5;
        if (doSpawn(stack, player, world, spawnX, spawnY, spawnZ)) {
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getItemInHand(handIn);
        return new ActionResult<>(ActionResultType.PASS, stack);
    }

    private boolean doSpawn(ItemStack stack, PlayerEntity player, World world, double spawnX, double spawnY, double spawnZ) {
        Entity ent = ItemSpawnEgg.spawn_something((int) this.my_id, world, spawnX, spawnY, spawnZ);
        if (ent == null) {
            return false;
        }
        if (ent instanceof PitchBlack) {
            ((PitchBlack) ent).setSpawnedFromEgg();
        }
        if (ent instanceof MobEntity && stack.hasCustomHoverName()) {
            ((MobEntity) ent).setCustomName(stack.getHoverName());
        }
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        return true;
    }

    public static Entity spawn_something(int id, World world, double d0, double d1, double d2) {
        int entityID = 0;
        int skelly_type = 0;
        String name = null;
        EntityType<?> entityType = null;
        switch (id) {
            case 192: {
                skelly_type = 1;
                entityID = 51;
                break;
            }
            case 193: {
                entityID = 63;
                break;
            }
            case 194: {
                entityID = 97;
                break;
            }
            case 195: {
                entityID = 99;
                break;
            }
            case 196: {
                entityID = 64;
                break;
            }
            case 197: {
                name = "girlfriend";
                break;
            }
            case 198: {
                name = "apple_cow";
                break;
            }
            case 363: {
                name = "crystal_apple_cow";
                break;
            }
            case 199: {
                name = "golden_apple_cow";
                break;
            }
            case 200: {
                name = "enchanted_golden_apple_cow";
                break;
            }
            case 202: {
                name = "alosaurus";
                break;
            }
            case 203: {
                name = "cryolophosaurus";
                break;
            }
            case 204: {
                name = "camarasaurus";
                break;
            }
            case 205: {
                name = "velocity_raptor";
                break;
            }
            case 206: {
                name = "hydrolisc";
                break;
            }
            case 207: {
                name = "basilisk";
                break;
            }
            case 201: {
                name = "mothra";
                break;
            }
            case 221: {
                name = "dragonfly";
                break;
            }
            case 223: {
                name = "emperor_scorpion";
                break;
            }
            case 225: {
                name = "scorpion";
                break;
            }
            case 227: {
                name = "cave_fisher";
                break;
            }
            case 229: {
                name = "baby_dragon";
                break;
            }
            case 231: {
                name = "baryonyx";
                break;
            }
            case 233: {
                name = "gamma_metroid";
                break;
            }
            case 235: {
                name = "bird";
                break;
            }
            case 237: {
                name = "kyuubi";
                break;
            }
            case 239: {
                name = "alien";
                break;
            }
            case 241: {
                name = "attack_squid";
                break;
            }
            case 243: {
                name = "water_dragon";
                break;
            }
            case 245: {
                name = "the_kraken";
                break;
            }
            case 247: {
                name = "lizard";
                break;
            }
            case 249: {
                name = "cephadrome";
                break;
            }
            case 251: {
                name = "dragon";
                break;
            }
            case 254: {
                name = "bee";
                break;
            }
            case 262: {
                name = "jumpy_bug";
                break;
            }
            case 263: {
                name = "spit_bug";
                break;
            }
            case 264: {
                name = "stink_bug";
                break;
            }
            case 265: {
                name = "ostrich";
                break;
            }
            case 266: {
                name = "gazelle";
                break;
            }
            case 267: {
                name = "chipmunk";
                break;
            }
            case 274: {
                name = "creeping_horror";
                break;
            }
            case 275: {
                name = "terrible_terror";
                break;
            }
            case 276: {
                name = "cliff_racer";
                break;
            }
            case 277: {
                name = "triffid";
                break;
            }
            case 278: {
                name = "nightmare";
                break;
            }
            case 279: {
                name = "lurking_terror";
                break;
            }
            case 288: {
                name = "small_worm";
                break;
            }
            case 289: {
                name = "medium_worm";
                break;
            }
            case 290: {
                name = "large_worm";
                break;
            }
            case 291: {
                name = "cassowary";
                break;
            }
            case 292: {
                name = "cloud_shark";
                break;
            }
            case 293: {
                name = "gold_fish";
                break;
            }
            case 294: {
                name = "leaf_monster";
                break;
            }
            case 295: {
                name = "tshirt";
                break;
            }
            case 280: {
                name = "mobzilla";
                break;
            }
            case 298: {
                name = "ender_knight";
                break;
            }
            case 299: {
                name = "ender_reaper";
                break;
            }
            case 301: {
                name = "beaver";
                break;
            }
            case 306: {
                name = "dungeon_beast";
                break;
            }
            case 303: {
                name = "vortex";
                break;
            }
            case 302: {
                name = "rotator";
                break;
            }
            case 304: {
                name = "peacock";
                break;
            }
            case 305: {
                name = "fairy";
                break;
            }
            case 307: {
                name = "rat";
                break;
            }
            case 308: {
                name = "flounder";
                break;
            }
            case 309: {
                name = "whale";
                break;
            }
            case 310: {
                name = "irukandji";
                break;
            }
            case 311: {
                name = "skate";
                break;
            }
            case 312: {
                name = "crystal_urchin";
                break;
            }
            case 324: {
                name = "bomb_omb";
                break;
            }
            case 325: {
                name = "robo_pounder";
                break;
            }
            case 326: {
                name = "robo_gunner";
                break;
            }
            case 327: {
                name = "robo_warrior";
                break;
            }
            case 328: {
                name = "ghost";
                break;
            }
            case 329: {
                name = "ghost_pumpkin_skelly";
                break;
            }
            case 330: {
                name = "ant";
                break;
            }
            case 331: {
                name = "red_ant";
                break;
            }
            case 332: {
                name = "rainbow_ant";
                break;
            }
            case 333: {
                name = "unstable_ant";
                break;
            }
            case 334: {
                name = "termite";
                break;
            }
            case 335: {
                name = "butterfly";
                break;
            }
            case 336: {
                name = "moth";
                break;
            }
            case 337: {
                name = "mosquito";
                break;
            }
            case 338: {
                name = "firefly";
                break;
            }
            case 339: {
                name = "trex";
                break;
            }
            case 340: {
                name = "hercules_beetle";
                break;
            }
            case 341: {
                name = "mantis";
                break;
            }
            case 342: {
                name = "stinky";
                break;
            }
            case 343: {
                name = "robo_sniper";
                break;
            }
            case 344: {
                name = "coin";
                break;
            }
            case 349: {
                name = "boyfriend";
                break;
            }
            case 350: {
                name = "the_king";
                break;
            }
            case 366: {
                name = "the_queen";
                break;
            }
            case 351: {
                name = "the_prince";
                break;
            }
            case 352: {
                name = "easter_bunny";
                break;
            }
            case 353: {
                name = "molenoid";
                break;
            }
            case 354: {
                name = "sea_monster";
                break;
            }
            case 355: {
                name = "sea_viper";
                break;
            }
            case 356: {
                name = "caterkiller";
                break;
            }
            case 358: {
                name = "leonopteryx";
                break;
            }
            case 360: {
                name = "hammerhead";
                break;
            }
            case 362: {
                name = "rubber_ducky";
                break;
            }
            case 365: {
                name = "criminal";
                break;
            }
            case 367: {
                name = "brutalfly";
                break;
            }
            case 368: {
                name = "nastysaurus";
                break;
            }
            case 369: {
                name = "pointysaurus";
                break;
            }
            case 370: {
                name = "cricket";
                break;
            }
            case 371: {
                name = "the_princess";
                break;
            }
            case 372: {
                name = "frog";
                break;
            }
            case 378: {
                name = "jeffery";
                break;
            }
            case 379: {
                name = "robot_red_ant";
                break;
            }
            case 380: {
                name = "robot_spider";
                break;
            }
            case 381: {
                name = "spider_driver";
                break;
            }
            case 383: {
                name = "crab";
                break;
            }
        }
        if (entityID == 51 && skelly_type != 0) {
            entityType = EntityType.WITHER_SKELETON;
            entityID = 0;
        }
        if (entityID == 0 && name == null && entityType == null) {
            return null;
        }
        return ItemSpawnEgg.spawnCreature(world, entityID, name, entityType, d0, d1, d2);
    }

    public static Entity spawnCreature(World world, int legacyEntityId, String name, EntityType<?> presetType, double x, double y, double z) {
        EntityType<?> entityType = presetType;
        if (entityType == null && name != null) {
            entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", name));
        } else if (entityType == null && legacyEntityId != 0) {
            entityType = Registry.ENTITY_TYPE.byId(legacyEntityId);
        }
        if (entityType == null) {
            return null;
        }
        Entity entity = entityType.create(world);
        if (entity == null) {
            return null;
        }
        entity.moveTo(x, y, z, world.random.nextFloat() * 360.0F, 0.0F);
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).addFreshEntity(entity);
        } else {
            world.addFreshEntity(entity);
        }
        if (entity instanceof MobEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) entity);
        }
        return entity;
    }
}
