package com.astryxion.chaospersists.tileentity;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.FurnaceFuelSlot;

import javax.annotation.Nullable;
import java.util.Optional;

public class TileEntityCrystalFurnace extends LockableTileEntity implements ITickableTileEntity, ISidedInventory {
    public static TileEntityType<TileEntityCrystalFurnace> TYPE;

    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};

    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private final IntArray furnaceData = new IntArray(4);

    public TileEntityCrystalFurnace() {
        super(TYPE != null ? TYPE : TileEntityType.FURNACE);
    }

    public static TileEntityType<TileEntityCrystalFurnace> createType() {
        return TileEntityType.Builder.of(TileEntityCrystalFurnace::new, ChaosPersists.CrystalFurnaceBlock).build(null);
    }

    public IntArray getFurnaceData() {
        return this.furnaceData;
    }

    private void syncFurnaceData() {
        this.furnaceData.set(0, this.furnaceBurnTime);
        this.furnaceData.set(1, this.currentItemBurnTime);
        this.furnaceData.set(2, this.cookTime);
        this.furnaceData.set(3, this.totalCookTime);
    }

    @Override
    public int getContainerSize() {
        return this.furnaceItemStacks.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : this.furnaceItemStacks) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clearContent() {
        this.furnaceItemStacks.clear();
    }

    @Override
    public ItemStack getItem(int index) {
        return this.furnaceItemStacks.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ItemStackHelper.removeItem(this.furnaceItemStacks, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.furnaceItemStacks, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack old = this.furnaceItemStacks.get(index);
        boolean sameStack = !stack.isEmpty()
                && ItemStack.isSame(old, stack)
                && ItemStack.matches(old, stack);
        this.furnaceItemStacks.set(index, stack);

        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }

        if (index == 0 && !sameStack) {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.setChanged();
        }
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.furnace");
    }

    public void setCustomInventoryName(String name) {
        this.setCustomName(new TranslationTextComponent(name));
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        this.furnaceItemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.totalCookTime = compound.getInt("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        compound.putInt("BurnTime", this.furnaceBurnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.totalCookTime);
        ItemStackHelper.saveAllItems(compound, this.furnaceItemStacks);
        return compound;
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    public boolean isOnFire() {
        return this.furnaceBurnTime > 0;
    }

    @Override
    public void tick() {
        boolean wasBurning = this.isOnFire();
        boolean dirty = false;

        if (this.isOnFire()) {
            --this.furnaceBurnTime;
        }

        if (this.level != null && !this.level.isClientSide) {
            ItemStack fuel = this.furnaceItemStacks.get(1);

            if (this.isOnFire() || !fuel.isEmpty() && !this.furnaceItemStacks.get(0).isEmpty()) {
                if (!this.isOnFire() && this.canSmelt()) {
                    this.furnaceBurnTime = getItemBurnTime(fuel);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isOnFire()) {
                        dirty = true;
                        if (!fuel.isEmpty()) {
                            Item item = fuel.getItem();
                            fuel.shrink(1);

                            if (fuel.isEmpty()) {
                                ItemStack container = fuel.getContainerItem();
                                this.furnaceItemStacks.set(1, container);
                            }
                        }
                    }
                }

                if (this.isOnFire() && this.canSmelt()) {
                    ++this.cookTime;
                    if (this.cookTime == this.totalCookTime) {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks.get(0));
                        this.smeltItem();
                        dirty = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isOnFire() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (wasBurning != this.isOnFire()) {
                dirty = true;
                CrystalFurnace.setState(this.isOnFire(), this.level, this.worldPosition);
            }
        }

        if (dirty) {
            this.setChanged();
        }

        this.syncFurnaceData();
    }

    private int getCookTime(ItemStack stack) {
        return 200;
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks.get(0).isEmpty()) {
            return false;
        }

        ItemStack result = this.getSmeltingResult(this.furnaceItemStacks.get(0));
        if (result.isEmpty()) {
            return false;
        }

        ItemStack output = this.furnaceItemStacks.get(2);
        if (output.isEmpty()) {
            return true;
        }
        if (!ItemStack.isSame(output, result)) {
            return false;
        }

        int combined = output.getCount() + result.getCount();
        return combined <= this.getMaxStackSize() && combined <= output.getMaxStackSize();
    }

    private ItemStack getSmeltingResult(ItemStack stack) {
        if (this.level == null || stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        Inventory inv = new Inventory(1);
        inv.setItem(0, stack);
        Optional<? extends IRecipe<IInventory>> recipe = this.level.getRecipeManager()
                .getRecipeFor(IRecipeType.SMELTING, inv, this.level);
        if (recipe.isPresent()) {
            return recipe.get().getResultItem();
        }
        return ItemStack.EMPTY;
    }

    public void smeltItem() {
        if (!this.canSmelt()) {
            return;
        }

        ItemStack input = this.furnaceItemStacks.get(0);
        ItemStack result = this.getSmeltingResult(input);
        ItemStack output = this.furnaceItemStacks.get(2);

        if (output.isEmpty()) {
            this.furnaceItemStacks.set(2, result.copy());
        } else if (ItemStack.isSame(output, result)) {
            output.grow(result.getCount());
        }

        if (input.getItem() == Item.byBlock(Blocks.WET_SPONGE)
                && !this.furnaceItemStacks.get(1).isEmpty()
                && this.furnaceItemStacks.get(1).getItem() == Items.BUCKET) {
            this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
        }

        input.shrink(1);
    }

    public static int getItemBurnTime(ItemStack stack) {
        return net.minecraft.tileentity.AbstractFurnaceTileEntity.getFuel().getOrDefault(stack.getItem(), 0);
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        if (this.level == null || this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
                (double) this.worldPosition.getY() + 0.5D,
                (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void startOpen(PlayerEntity player) {
    }

    @Override
    public void stopOpen(PlayerEntity player) {
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        }
        if (index == 1) {
            return isItemFuel(stack) || FurnaceFuelSlot.isBucket(stack);
        }
        return true;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_BOTTOM;
        }
        if (side == Direction.UP) {
            return SLOTS_TOP;
        }
        return SLOTS_SIDES;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStackIn);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            Item item = stack.getItem();
            return item == Items.WATER_BUCKET || item == Items.BUCKET;
        }
        return true;
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return new FurnaceContainer(id, playerInventory, this, this.furnaceData);
    }
}
