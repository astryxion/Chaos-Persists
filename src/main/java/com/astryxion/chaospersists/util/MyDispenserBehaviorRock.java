package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityThrownRock;
import net.minecraft.util.Direction;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.dispenser.IPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.block.DispenserBlock;

public final class MyDispenserBehaviorRock implements IDispenseItemBehavior {
    @Override
    public ItemStack dispense(IBlockSource source, ItemStack stack) {
        World level = source.getLevel();
        IPosition position = DispenserBlock.getDispensePosition(source);
        Direction facing = source.getBlockState().getValue(DispenserBlock.FACING);
        EntityThrownRock projectile = new EntityThrownRock(level, position.x(), position.y(), position.z());
        applyRockType(projectile, stack);
        projectile.shoot(facing.getStepX(), (float) facing.getStepY() + 0.1F, facing.getStepZ(), 1.1F, 6.0F);
        level.addFreshEntity(projectile);
        stack.shrink(1);
        return stack;
    }

    private static void applyRockType(EntityThrownRock rock, ItemStack stack) {
        if (stack.getItem() == ChaosPersists.MySmallRock) {
            rock.setRockType(1);
        }
        if (stack.getItem() == ChaosPersists.MyRock) {
            rock.setRockType(2);
        }
        if (stack.getItem() == ChaosPersists.MyRedRock) {
            rock.setRockType(3);
        }
        if (stack.getItem() == ChaosPersists.MyGreenRock) {
            rock.setRockType(4);
        }
        if (stack.getItem() == ChaosPersists.MyBlueRock) {
            rock.setRockType(5);
        }
        if (stack.getItem() == ChaosPersists.MyPurpleRock) {
            rock.setRockType(6);
        }
        if (stack.getItem() == ChaosPersists.MySpikeyRock) {
            rock.setRockType(7);
        }
        if (stack.getItem() == ChaosPersists.MyTNTRock) {
            rock.setRockType(8);
        }
        if (stack.getItem() == ChaosPersists.MyCrystalRedRock) {
            rock.setRockType(9);
        }
        if (stack.getItem() == ChaosPersists.MyCrystalGreenRock) {
            rock.setRockType(10);
        }
        if (stack.getItem() == ChaosPersists.MyCrystalBlueRock) {
            rock.setRockType(11);
        }
        if (stack.getItem() == ChaosPersists.MyCrystalTNTRock) {
            rock.setRockType(12);
        }
    }
}
