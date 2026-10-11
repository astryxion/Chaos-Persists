package com.astryxion.chaospersists.blockentity;

import com.astryxion.chaospersists.block.CrystalFurnace;
import com.astryxion.chaospersists.menu.ContainerCrystalFurnace;
import com.astryxion.chaospersists.core.ChaosPersists;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.util.Mth;
import net.minecraftforge.common.ForgeHooks;

public class TileEntityCrystalFurnace extends BlockEntity implements WorldlyContainer, MenuProvider {
    private static final int[] SLOTS_TOP = new int[] {0};
    private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
    private static final int[] SLOTS_SIDES = new int[] {1};

    private NonNullList<ItemStack> furnaceItemStacks = NonNullList.withSize(3, ItemStack.EMPTY);
    private int furnaceBurnTime;
    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    @Nullable
    private String furnaceCustomName;

    private final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int id) {
            return TileEntityCrystalFurnace.this.getField(id);
        }

        @Override
        public void set(int id, int value) {
            TileEntityCrystalFurnace.this.setField(id, value);
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public TileEntityCrystalFurnace(BlockPos pos, BlockState state) {
        super(ChaosPersists.BLOCK_ENTITY_CRYSTAL_FURNACE.get(), pos, state);
    }

    public ContainerData getDataAccess() {
        return this.dataAccess;
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
    public ItemStack getItem(int index) {
        return this.furnaceItemStacks.get(index);
    }

    @Override
    public ItemStack removeItem(int index, int count) {
        return ContainerHelper.removeItem(this.furnaceItemStacks, index, count);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.furnaceItemStacks, index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        ItemStack old = this.furnaceItemStacks.get(index);
        boolean sameStack = !stack.isEmpty() && ItemStack.isSameItem(stack, old) && ItemStack.matches(stack, old);
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
    public boolean stillValid(Player player) {
        if (this.level == null || this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr(
                        (double) this.worldPosition.getX() + 0.5D,
                        (double) this.worldPosition.getY() + 0.5D,
                        (double) this.worldPosition.getZ() + 0.5D)
                <= 64.0D;
    }

    @Override
    public void clearContent() {
        this.furnaceItemStacks.clear();
    }

    public Component getName() {
        return this.hasCustomName()
                ? Component.literal(this.furnaceCustomName)
                : Component.translatable("container.furnace");
    }

    @Override
    public Component getDisplayName() {
        return this.getName();
    }

    public boolean hasCustomName() {
        return this.furnaceCustomName != null && !this.furnaceCustomName.isEmpty();
    }

    public void setCustomInventoryName(String name) {
        this.furnaceCustomName = name;
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        super.saveAdditional(compound);
        compound.putInt("BurnTime", this.furnaceBurnTime);
        compound.putInt("CookTime", this.cookTime);
        compound.putInt("CookTimeTotal", this.totalCookTime);
        ContainerHelper.saveAllItems(compound, this.furnaceItemStacks);
        if (this.hasCustomName()) {
            compound.putString("CustomName", this.furnaceCustomName);
        }
    }

    @Override
    public void load(CompoundTag compound) {
        super.load(compound);
        this.furnaceItemStacks = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(compound, this.furnaceItemStacks);
        this.furnaceBurnTime = compound.getInt("BurnTime");
        this.cookTime = compound.getInt("CookTime");
        this.totalCookTime = compound.getInt("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks.get(1));
        if (compound.contains("CustomName", 8)) {
            this.furnaceCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, TileEntityCrystalFurnace furnace) {
        furnace.tick(level, pos, state);
    }

    private void tick(Level level, BlockPos pos, BlockState state) {
        boolean wasBurning = this.isBurning();
        boolean dirty = false;

        if (this.isBurning()) {
            --this.furnaceBurnTime;
        }

        if (!level.isClientSide) {
            ItemStack fuel = this.furnaceItemStacks.get(1);

            if (this.isBurning() || !fuel.isEmpty() && !this.furnaceItemStacks.get(0).isEmpty()) {
                if (!this.isBurning() && this.canSmelt(level)) {
                    this.furnaceBurnTime = getItemBurnTime(fuel);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if (this.isBurning()) {
                        dirty = true;
                        if (!fuel.isEmpty()) {
                            Item item = fuel.getItem();
                            fuel.shrink(1);

                            if (fuel.isEmpty()) {
                                ItemStack remainder = item.getCraftingRemainingItem(fuel);
                                this.furnaceItemStacks.set(1, remainder);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(level)) {
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
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = Mth.clamp(this.cookTime - 2, 0, this.totalCookTime);
            }

            if (wasBurning != this.isBurning()) {
                dirty = true;
                CrystalFurnace.setState(this.isBurning(), level, pos);
            }
        }

        if (dirty) {
            this.setChanged();
        }
    }

    private int getCookTime(ItemStack stack) {
        return 200;
    }

    private boolean canSmelt(Level level) {
        if (this.furnaceItemStacks.get(0).isEmpty()) {
            return false;
        }

        ItemStack result = getSmeltingResult(level, this.furnaceItemStacks.get(0));
        if (result.isEmpty()) {
            return false;
        }

        ItemStack output = this.furnaceItemStacks.get(2);
        if (output.isEmpty()) {
            return true;
        }
        if (!ItemStack.isSameItem(output, result)) {
            return false;
        }

        int combined = output.getCount() + result.getCount();
        return combined <= this.getMaxStackSize() && combined <= output.getMaxStackSize();
    }

    public void smeltItem() {
        if (this.level == null || !this.canSmelt(this.level)) {
            return;
        }

        ItemStack input = this.furnaceItemStacks.get(0);
        ItemStack result = getSmeltingResult(this.level, input);
        ItemStack output = this.furnaceItemStacks.get(2);

        if (output.isEmpty()) {
            this.furnaceItemStacks.set(2, result.copy());
        } else if (ItemStack.isSameItem(output, result)) {
            output.grow(result.getCount());
        }

        if (input.is(Blocks.WET_SPONGE.asItem())
                && !this.furnaceItemStacks.get(1).isEmpty()
                && this.furnaceItemStacks.get(1).is(Items.BUCKET)) {
            this.furnaceItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
        }

        input.shrink(1);
    }

    private static ItemStack getSmeltingResult(Level level, ItemStack input) {
        return level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, new SimpleContainer(input), level)
                .map(recipe -> recipe.getResultItem(level.registryAccess()))
                .orElse(ItemStack.EMPTY);
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        return ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        }
        if (index == 1) {
            return isItemFuel(stack) || stack.is(Items.BUCKET);
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
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 1) {
            return stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
        }
        return true;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ContainerCrystalFurnace(id, inventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
                break;
            default:
                break;
        }
    }

    public int getFieldCount() {
        return 4;
    }
}
