package com.astryxion.chaospersists.container;

import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class ContainerCrystalWorkbench extends Container {
    public static ContainerType<ContainerCrystalWorkbench> TYPE;

    public CraftingInventory craftMatrix;
    public IInventory craftResult;
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    private final PlayerEntity player;

    public ContainerCrystalWorkbench(int id, PlayerInventory playerInventory, World world, int x, int y, int z) {
        super(TYPE, id);
        this.player = playerInventory.player;
        this.worldObj = world;
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.craftMatrix = new CraftingInventory(this, 3, 3);
        this.craftResult = new CraftResultInventory();
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.craftMatrix, this.craftResult, 0, 124, 35));

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                this.addSlot(new Slot(this.craftMatrix, col + row * 3, 30 + col * 18, 17 + row * 18));
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
    }

    public ContainerCrystalWorkbench(int windowId, PlayerInventory inv, PacketBuffer data) {
        this(windowId, inv, inv.player.level, data.readBlockPos());
    }

    public ContainerCrystalWorkbench(int id, PlayerInventory playerInventory, World world, BlockPos pos) {
        this(id, playerInventory, world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void slotsChanged(IInventory inventory) {
        if (!(this.worldObj instanceof ServerWorld)) {
            return;
        }
        ServerWorld serverWorld = (ServerWorld) this.worldObj;
        ItemStack result = ItemStack.EMPTY;
        java.util.Optional<? extends IRecipe<CraftingInventory>> recipe = serverWorld.getRecipeManager()
                .getRecipeFor(IRecipeType.CRAFTING, this.craftMatrix, serverWorld);
        if (recipe.isPresent()) {
            result = recipe.get().getResultItem();
        }
        this.craftResult.setItem(0, result);
        if (this.player instanceof ServerPlayerEntity) {
            ((ServerPlayerEntity) this.player).connection.send(new SSetSlotPacket(this.containerId, 0, result));
        }
    }

    @Override
    public void removed(PlayerEntity player) {
        super.removed(player);
        if (!this.worldObj.isClientSide) {
            for (int i = 0; i < 9; ++i) {
                ItemStack stack = this.craftMatrix.removeItemNoUpdate(i);
                if (!stack.isEmpty()) {
                    player.drop(stack, false);
                }
            }
        }
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return this.worldObj.getBlockState(new BlockPos(this.posX, this.posY, this.posZ)).getBlock() == ChaosPersists.CrystalWorkbenchBlock
                && player.distanceToSqr((double) this.posX + 0.5D, (double) this.posY + 0.5D, (double) this.posZ + 0.5D) <= 64.0D;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else if (index >= 10 && index < 37) {
                if (!this.moveItemStackTo(stack, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 46) {
                if (!this.moveItemStackTo(stack, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack);
        }
        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
        return slot.container != this.craftResult && super.canTakeItemForPickAll(stack, slot);
    }

    public static ContainerType<ContainerCrystalWorkbench> createContainerType() {
        return IForgeContainerType.create(ContainerCrystalWorkbench::new);
    }
}
