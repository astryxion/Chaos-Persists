package com.astryxion.chaospersists.menu;

import com.astryxion.chaospersists.block.CrystalWorkbench;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class ContainerCrystalWorkbench extends AbstractContainerMenu {
    public final TransientCraftingContainer craftSlots;
    public final ResultContainer resultSlots;
    private final Level level;
    private final BlockPos pos;

    public ContainerCrystalWorkbench(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, playerInventory.player.level(), extraData.readBlockPos());
    }

    public ContainerCrystalWorkbench(int containerId, Inventory playerInventory, Level level, BlockPos pos) {
        super(ChaosPersists.MENU_CRYSTAL_WORKBENCH.get(), containerId);
        this.craftSlots = new TransientCraftingContainer(this, 3, 3);
        this.resultSlots = new ResultContainer();
        this.level = level;
        this.pos = pos;

        this.addSlot(new ResultSlot(
                playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new Slot(this.craftSlots, col + row * 3, 30 + col * 18, 17 + row * 18));
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }

        this.slotsChanged(this.craftSlots);
    }

    @Override
    public void slotsChanged(net.minecraft.world.Container container) {
        if (container != this.craftSlots) {
            return;
        }

        ItemStack result = ItemStack.EMPTY;
        if (!this.level.isClientSide) {
            for (CraftingRecipe recipe : this.level.getRecipeManager().getAllRecipesFor(RecipeType.CRAFTING)) {
                if (recipe.matches(this.craftSlots, this.level)) {
                    result = recipe.assemble(this.craftSlots, this.level.registryAccess());
                    break;
                }
            }
        }

        this.resultSlots.setItem(0, result);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        if (!player.level().isClientSide) {
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = this.craftSlots.removeItemNoUpdate(i);
                if (!stack.isEmpty()) {
                    player.drop(stack, false);
                }
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        if (!(this.level.getBlockState(this.pos).getBlock() instanceof CrystalWorkbench)) {
            return false;
        }
        return player.distanceToSqr(
                        this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D)
                <= 64.0D;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack original = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            original = stackInSlot.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stackInSlot, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stackInSlot, original);
            } else if (index >= 10 && index < 37) {
                if (!this.moveItemStackTo(stackInSlot, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 46) {
                if (!this.moveItemStackTo(stackInSlot, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stackInSlot, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stackInSlot.getCount() == original.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stackInSlot);
        }
        return original;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
    }
}
