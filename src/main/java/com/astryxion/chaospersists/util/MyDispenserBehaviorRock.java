package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.EntityThrownRock;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;

public final class MyDispenserBehaviorRock extends DefaultDispenseItemBehavior {
    @Override
    protected ItemStack execute(BlockSource source, ItemStack stack) {
        Level level = source.level();
        Position position = DispenserBlock.getDispensePosition(source);
        Direction direction = source.state().getValue(DispenserBlock.FACING);
        EntityThrownRock projectile =
                new EntityThrownRock(
                        ChaosPersists.ENTITY_TYPE_THROWN_ROCK.get(),
                        position.x(),
                        position.y(),
                        position.z(),
                        level);
        projectile.shoot(
                direction.getStepX(),
                direction.getStepY() + 0.1f,
                direction.getStepZ(),
                1.1f,
                6.0f);
        applyRockTypeFromItem(projectile, stack.getItem());
        level.addFreshEntity(projectile);
        stack.shrink(1);
        return stack;
    }

    private static void applyRockTypeFromItem(EntityThrownRock rock, Item item) {
        if (item == ChaosPersists.MySmallRock) {
            rock.setRockType(1);
        } else if (item == ChaosPersists.MyRock) {
            rock.setRockType(2);
        } else if (item == ChaosPersists.MyRedRock) {
            rock.setRockType(3);
        } else if (item == ChaosPersists.MyGreenRock) {
            rock.setRockType(4);
        } else if (item == ChaosPersists.MyBlueRock) {
            rock.setRockType(5);
        } else if (item == ChaosPersists.MyPurpleRock) {
            rock.setRockType(6);
        } else if (item == ChaosPersists.MySpikeyRock) {
            rock.setRockType(7);
        } else if (item == ChaosPersists.MyTNTRock) {
            rock.setRockType(8);
        } else if (item == ChaosPersists.MyCrystalRedRock) {
            rock.setRockType(9);
        } else if (item == ChaosPersists.MyCrystalGreenRock) {
            rock.setRockType(10);
        } else if (item == ChaosPersists.MyCrystalBlueRock) {
            rock.setRockType(11);
        } else if (item == ChaosPersists.MyCrystalTNTRock) {
            rock.setRockType(12);
        }
    }
}
