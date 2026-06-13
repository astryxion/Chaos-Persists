package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.item.ItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ExperienceCatcher extends Item {

    public ExperienceCatcher(int i) {
        super(new Item.Properties().stacksTo(16).tab(ItemGroup.TAB_TOOLS));
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
        float hitX = (float) (context.getClickLocation().x - pos.getX());
        float hitZ = (float) (context.getClickLocation().z - pos.getZ());

        ItemStack stack = player.getItemInHand(hand);
        player.swing(hand);

        if (!world.isClientSide) {

            AxisAlignedBB bb = new AxisAlignedBB(
                    pos.getX() - 0.5D + hitX,
                    pos.getY(),
                    pos.getZ() - 0.5D + hitZ,
                    pos.getX() + 0.5D + hitX,
                    pos.getY() + 2.0D,
                    pos.getZ() + 0.5D + hitZ
            );

            List<ExperienceOrbEntity> xpOrbs = world.getEntitiesOfClass(ExperienceOrbEntity.class, bb);

            for (ExperienceOrbEntity orb : xpOrbs) {

                if (orb.getValue() < 3 || world.random.nextInt(5) == 1) {
                    continue;
                }

                orb.remove();

                spawnItem(world, pos, hitX, hitZ, new ItemStack(Items.EXPERIENCE_BOTTLE));
                spawnItem(world, pos, hitX, hitZ, new ItemStack(Items.STRING));
                spawnItem(world, pos, hitX, hitZ, new ItemStack(Items.STICK));

                if (!player.isCreative()) {
                    stack.shrink(1);
                }

                return ActionResultType.SUCCESS;
            }

            // No XP found — drop the catcher itself
            spawnItem(world, pos, hitX, hitZ,
                    new ItemStack(ChaosPersists.MyExperienceCatcher));

            if (!player.isCreative()) {
                stack.shrink(1);
            }
        }

        return ActionResultType.SUCCESS;
    }

    private void spawnItem(World world, BlockPos pos, float hitX, float hitZ, ItemStack stack) {
        ItemEntity entityItem = new ItemEntity(
                world,
                pos.getX() + hitX,
                pos.getY() + 1.0D,
                pos.getZ() + hitZ,
                stack
        );
        world.addFreshEntity(entityItem);
    }

    @Override
    public ActionResult<ItemStack> use(World world,
                                                    PlayerEntity player,
                                                    Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.swing(hand);
        return ActionResult.success(stack);
    }
}
