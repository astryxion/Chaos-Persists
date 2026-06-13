/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.ItemPizza
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.DeadBushBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraft.block.Block;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemUseContext;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPizza
extends Item {
    private Block spawnID;

    public ItemPizza(Block par2Block) {
        super(new Item.Properties());
        this.spawnID = par2Block;
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
        if (stack.isEmpty()) {
            return ActionResultType.PASS;
        }
        if (facing != Direction.UP) {
            return ActionResultType.FAIL;
        }
        BlockPos placePos = pos.above();
        if (!world.mayInteract(player, placePos)) {
            return ActionResultType.FAIL;
        }
        BlockState existing = world.getBlockState(placePos);
        BlockItemUseContext replaceCheck = new BlockItemUseContext(context);
        if (!existing.isAir() && !existing.canBeReplaced(replaceCheck)) {
            return ActionResultType.FAIL;
        }
        BlockItemUseContext blockContext = new BlockItemUseContext(context);
        BlockState state = this.spawnID.getStateForPlacement(blockContext);
        if (state == null) {
            state = this.spawnID.defaultBlockState();
        }
        if (!state.canSurvive(world, placePos)) {
            return ActionResultType.FAIL;
        }
        if (!world.setBlock(placePos, state, 3)) {
            return ActionResultType.FAIL;
        }
        if (world.getBlockState(placePos).getBlock() == this.spawnID) {
            this.spawnID.setPlacedBy(world, placePos, state, player, stack);
        }
        SoundCategory sc = SoundCategory.BLOCKS;
        world.playSound(null, placePos, this.spawnID.getSoundType(state, world, placePos, player).getPlaceSound(), sc,
                (this.spawnID.getSoundType(state, world, placePos, player).getVolume() + 1.0F) / 2.0F,
                this.spawnID.getSoundType(state, world, placePos, player).getPitch() * 0.8F);
        stack.shrink(1);
        return ActionResultType.SUCCESS;
    }
}
