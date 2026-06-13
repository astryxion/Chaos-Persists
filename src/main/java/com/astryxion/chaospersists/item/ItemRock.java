/*
 * 1.12.2: throwable items use {@code use(World, PlayerEntity, Hand)} and must call
 * {@link net.minecraft.entity.projectile.EntityThrowable#shoot} after construction (same as snowballs / eggs).
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityThrownRock;
import com.astryxion.chaospersists.entity.RockBase;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRock extends Item {

    public ItemRock(int i) { super(new Item.Properties()); }

    private int rockTypeForStack(ItemStack stack) {
        Item it = stack.getItem();
        if (it == ChaosPersists.MySmallRock) {
            return 1;
        }
        if (it == ChaosPersists.MyRock) {
            return 2;
        }
        if (it == ChaosPersists.MyRedRock) {
            return 3;
        }
        if (it == ChaosPersists.MyGreenRock) {
            return 4;
        }
        if (it == ChaosPersists.MyBlueRock) {
            return 5;
        }
        if (it == ChaosPersists.MyPurpleRock) {
            return 6;
        }
        if (it == ChaosPersists.MySpikeyRock) {
            return 7;
        }
        if (it == ChaosPersists.MyTNTRock) {
            return 8;
        }
        if (it == ChaosPersists.MyCrystalRedRock) {
            return 9;
        }
        if (it == ChaosPersists.MyCrystalGreenRock) {
            return 10;
        }
        if (it == ChaosPersists.MyCrystalBlueRock) {
            return 11;
        }
        if (it == ChaosPersists.MyCrystalTNTRock) {
            return 12;
        }
        return 0;
    }

    private void applyRockTypeToMob(RockBase r, ItemStack stack) {
        int t = rockTypeForStack(stack);
        if (t != 0) {
            r.placeRock(t);
        }
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int type = rockTypeForStack(stack);
        if (type == 0) {
            return ActionResult.pass(stack);
        }

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClientSide) {
            EntityThrownRock rock = new EntityThrownRock(world, player, type);
            rock.shootFromRotation(player, player.xRot, player.yRot, 0.0F, 1.5F, 1.0F);
            world.addFreshEntity(rock);
        }

        return ActionResult.success(stack);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResultType.FAIL;
        }
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Hand hand = context.getHand();
        Direction facing = context.getClickedFace();
        ItemStack stack = player.getItemInHand(hand);
        int x = pos.getX();
        int z = pos.getZ();
        if (x < 0) {
            ++x;
        }
        if (z < 0) {
            ++z;
        }

        if (!world.isClientSide) {
            Entity e = spawnPlacedRock(world, (double) x, (double) pos.getY() + 1.01, (double) z);
            if (e instanceof RockBase) {
                applyRockTypeToMob((RockBase) e, stack);
            }
        }

        if (!player.isCreative()) {
            stack.shrink(1);
        }

        return ActionResultType.SUCCESS;
    }

    /** Same centering as 1.7.10 {@code spawnCreature}; registry id {@code chaospersists:rock}. */
    private Entity spawnPlacedRock(World world, double par2, double par4, double par6) {
        net.minecraft.entity.EntityType<?> spawnType = net.minecraftforge.registries.ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", "rock"));
        Entity entity = spawnType != null ? spawnType.create(world) : null;
        if (entity == null) {
            return null;
        }
        if (par2 > 0.0) {
            par2 += 0.5;
        }
        if (par2 < 0.0) {
            par2 -= 0.5;
        }
        if (par6 > 0.0) {
            par6 += 0.5;
        }
        if (par6 < 0.0) {
            par6 -= 0.5;
        }
        entity.moveTo(par2, par4 + 0.01, par6, world.random.nextFloat() * 360.0F, 0.0F);
        world.addFreshEntity(entity);
        if (entity instanceof MobEntity) {
            com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) entity);
        }
        return entity;
    }
}
