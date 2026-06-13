/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.AntRobot
 *  com.astryxion.chaospersists.ItemSpiderRobotKit
 *  com.astryxion.chaospersists.MobStats
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.entity.player.PlayerEntityCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.core.ChaosPersists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * Exception performing whole class analysis ignored.
 */
public class ItemSpiderRobotKit
extends Item {
    public ItemSpiderRobotKit(int i) {
        super(new Item.Properties().stacksTo(1).tab(ItemGroup.TAB_TOOLS).durability(
                i == ChaosPersists.BaseItemID + 471 ? ChaosPersists.SpiderRobot_stats.health : ChaosPersists.AntRobot_stats.health));
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        PlayerEntity par2PlayerEntity = context.getPlayer();
        World par3World = context.getLevel();
        if (par2PlayerEntity == null) {
            return ActionResultType.FAIL;
        }
        ItemStack par1ItemStack = context.getItemInHand();
        BlockPos pos = context.getClickedPos();
        int par4 = pos.getX();
        int par5 = pos.getY();
        int par6 = pos.getZ();
        Entity ent;
        if (par3World.isClientSide) {
            return ActionResultType.SUCCESS;
        }
        String name = "robot_spider";
        if (par1ItemStack.getItem() == ChaosPersists.AntRobotKit) {
            name = "robot_red_ant";
        }
        if ((ent = ItemSpiderRobotKit.spawnCreature((World)par3World, (int)0, (String)name, (double)((double)par4 + 0.5), (double)((double)par5 + 1.01), (double)((double)par6 + 0.5))) != null) {
            MobEntity e = (MobEntity)ent;
            e.setHealth((float)(par1ItemStack.getMaxDamage() - par1ItemStack.getDamageValue()));
            if (ent instanceof MobEntity && par1ItemStack.hasCustomHoverName()) {
                ((MobEntity)ent).setCustomName(par1ItemStack.getHoverName());
            }
            par3World.playSound(null, par2PlayerEntity.getX(), par2PlayerEntity.getY(), par2PlayerEntity.getZ(), SoundEvents.GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0f, par3World.random.nextFloat() * 0.2f + 0.9f);
            if (ent instanceof AntRobot) {
                AntRobot a = (AntRobot)ent;
                a.setOwned();
            }
        }
        if (!par2PlayerEntity.isCreative()) {
            par1ItemStack.shrink(1);
        }
        return ActionResultType.SUCCESS;
    }

    public static Entity spawnCreature(World par0World, int par1, String name, double par2, double par4, double par6) {
        EntityType<?> entityType = null;
        if (name != null) {
            entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", name));
        }
        if (entityType == null) {
            return null;
        }
        Entity var8 = entityType.create(par0World);
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            if (par0World instanceof ServerWorld) {
                ((ServerWorld) par0World).addFreshEntity(var8);
            } else {
                par0World.addFreshEntity(var8);
            }
            if (var8 instanceof MobEntity) {
                com.astryxion.chaospersists.entity.RockBase.playSpawnAmbientSound((LivingEntity) var8);
            }
        }
        return var8;
    }
}
