/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  com.astryxion.chaospersists.Boyfriend
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.ChaosPersists
 *  com.astryxion.chaospersists.UltimateSword
 *  com.astryxion.chaospersists.WeaponStats
 *  net.minecraft.block.Block
 *  net.minecraft.block.LeavesBlock
 *  net.minecraft.block.TallGrassBlock
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.Mob
 *  net.minecraft.entity.LivingEntity
 *  net.minecraft.entity.item.ItemEntity
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.entity.player.PlayerEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.SwordItem
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.IItemTier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.inventory.EquipmentSlotType;

public class UltimateSword
extends SwordItem {
    private int swingtimer = 0;
    private boolean leaf = false;

    public UltimateSword(IItemTier tier) {
        this(tier, new Item.Properties().stacksTo(1).durability(3000));
    }

    public UltimateSword(IItemTier tier, Item.Properties properties) {
        super(tier, 3, -2.4F, properties);
    }

    @Override
    public void onCraftedBy(ItemStack par1ItemStack, World par2World, PlayerEntity par3PlayerEntity) {
        if (this == ChaosPersists.MyChainsaw) {
            return;
        }
        if (this != ChaosPersists.MyBattleAxe) {
            par1ItemStack.enchant(Enchantments.SHARPNESS, ChaosPersists.UltimateSwordMagic);
            par1ItemStack.enchant(Enchantments.SMITE, ChaosPersists.UltimateSwordMagic);
            par1ItemStack.enchant(Enchantments.BANE_OF_ARTHROPODS, ChaosPersists.UltimateSwordMagic);
            par1ItemStack.enchant(Enchantments.KNOCKBACK, 1 + ChaosPersists.UltimateSwordMagic / 2);
            par1ItemStack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
            par1ItemStack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
            par1ItemStack.enchant(Enchantments.FIRE_ASPECT, 1 + ChaosPersists.UltimateSwordMagic / 3);
        } else {
            par1ItemStack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
            par1ItemStack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
        }
    }

    public boolean onEntitySwing(LivingEntity entityLiving, ItemStack stack) {
        if (this == ChaosPersists.MyChainsaw && entityLiving != null && this.swingtimer == 0) {
            entityLiving.playSound(com.astryxion.chaospersists.core.ChaosSounds.CHAINSAWSHORT, 1.0f, entityLiving.level.random.nextFloat() * 0.2f + 0.9f);
            this.swingtimer = 50;
        }
        return false;
    }

    @Override
    public void onUseTick(World world, LivingEntity player, ItemStack stack, int count) {
        if (this == ChaosPersists.MyChainsaw) {
            return;
        }
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, (ItemStack)stack);
        if (lvl <= 0) {
            if (this != ChaosPersists.MyBattleAxe) {
                stack.enchant(Enchantments.SHARPNESS, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.SMITE, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.BANE_OF_ARTHROPODS, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.KNOCKBACK, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.FIRE_ASPECT, 1 + ChaosPersists.UltimateSwordMagic / 3);
            } else {
                stack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
            }
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (this == ChaosPersists.MyChainsaw) {
            if (this.swingtimer > 0) {
                --this.swingtimer;
            }
            if (par2World.isClientSide && this.swingtimer > 0) {
                float f = 1.0f;
                float dx = (float)((double)f * Math.cos(Math.toRadians(par3Entity.yRot + 90.0f + 45.0f)));
                float dz = (float)((double)f * Math.sin(Math.toRadians(par3Entity.yRot + 90.0f + 45.0f)));
                if (par2World.random.nextInt(8) == 0) {
                    par2World.addParticle(ParticleTypes.FLAME, par3Entity.getX() + (double)dx, par3Entity.getY(), par3Entity.getZ() + (double)dz, (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f), (double)(par2World.random.nextFloat() / 10.0f), (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f));
                }
                if (par2World.random.nextInt(2) == 0) {
                    par2World.addParticle(ParticleTypes.SMOKE, par3Entity.getX() + (double)dx, par3Entity.getY(), par3Entity.getZ() + (double)dz, (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f), (double)(par2World.random.nextFloat() / 10.0f), (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f));
                }
                if (par2World.random.nextInt(10) == 0) {
                    par2World.addParticle(ParticleTypes.FIREWORK, par3Entity.getX() + (double)dx, par3Entity.getY(), par3Entity.getZ() + (double)dz, (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f), (double)(par2World.random.nextFloat() / 5.0f), (double)((par2World.random.nextFloat() - par2World.random.nextFloat()) / 20.0f));
                }
            }
            return;
        }
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, (ItemStack)stack);
        if (lvl <= 0) {
            if (this != ChaosPersists.MyBattleAxe) {
                stack.enchant(Enchantments.SHARPNESS, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.SMITE, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.BANE_OF_ARTHROPODS, ChaosPersists.UltimateSwordMagic);
                stack.enchant(Enchantments.KNOCKBACK, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.FIRE_ASPECT, 1 + ChaosPersists.UltimateSwordMagic / 3);
            } else {
                stack.enchant(Enchantments.MOB_LOOTING, 1 + ChaosPersists.UltimateSwordMagic / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + ChaosPersists.UltimateSwordMagic / 2);
            }
        }
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    public boolean onAttack(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof PlayerEntity)) {
            return false;
        }
        PlayerEntity player = (PlayerEntity) attacker;
        Entity entity = target;
        if (entity != null && ChaosPersists.ultimate_sword_pvp == 0) {
            TameableEntity t;
            if (entity instanceof PlayerEntity || entity instanceof Girlfriend || entity instanceof Boyfriend) {
                return true;
            }
            if (entity instanceof TameableEntity && (t = (TameableEntity)entity).isTame()) {
                return true;
            }
        }
        if (this == ChaosPersists.MyChainsaw && player != null) {
            this.findSomethingToHit(player);
        }
        return false;
    }

    public int getUseDuration(ItemStack par1ItemStack) {
        return 9000;
    }

    private void findSomethingToHit(PlayerEntity player) {
        List<LivingEntity> var5 = player.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5.0, 5.0, 5.0));
        Iterator var2 = var5.iterator();
        Entity var3 = null;
        LivingEntity var4 = null;
        while (var2.hasNext()) {
            var3 = (Entity)var2.next();
            var4 = (LivingEntity)var3;
            if (!this.isSuitableTarget(var4, false, player)) continue;
            var4.hurt(DamageSource.playerAttack((PlayerEntity)player), (float)ChaosPersists.chainsaw_stats.damage);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1Mob, boolean par2, PlayerEntity player) {
        if (par1Mob == null) {
            return false;
        }
        if (par1Mob == player) {
            return false;
        }
        if (!par1Mob.isAlive()) {
            return false;
        }
        if (ChaosPersists.ultimate_sword_pvp == 0) {
            TameableEntity t;
            if (par1Mob instanceof PlayerEntity || par1Mob instanceof Girlfriend || par1Mob instanceof Boyfriend) {
                return false;
            }
            if (par1Mob instanceof TameableEntity && (t = (TameableEntity)par1Mob).isTame()) {
                return false;
            }
        }
        if (!this.MyCanSee(par1Mob, player)) {
            return false;
        }
        return true;
    }

    public boolean MyCanSee(LivingEntity e, PlayerEntity player) {
        int nblks = 10;
        double cx = player.getX();
        double cz = player.getZ();
        float startx = (float)cx;
        float starty = (float)(player.getY() + 1.399999976158142);
        float startz = (float)cz;
        float dx = (float)((e.getX() - (double)startx) / 10.0);
        float dy = (float)((e.getY() + (double)(e.getBbHeight() / 2.0f) - (double)starty) / 10.0);
        float dz = (float)((e.getZ() - (double)startz) / 10.0);
        if ((double)Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int)((float)nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double)Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int)((float)nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double)Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int)((float)nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        for (int i = 0; i < nblks; ++i) {
            Block bid = player.level.getBlockState(new BlockPos((int)(startx += dx), (int)(starty += dy), (int)(startz += dz))).getBlock();
            if (bid == Blocks.AIR) continue;
            return false;
        }
        return true;
    }

    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        if (this == ChaosPersists.MyChainsaw) {
            return this.canCrush(state.getBlock());
        }
        return super.canHarvestBlock(stack, state);
    }

    private boolean canCrush(Block blockID) {
        if (this == ChaosPersists.MyChainsaw) {
            if (blockID == Blocks.COBWEB) {
                return true;
            }
            if (blockID == Blocks.OAK_LOG || blockID == Blocks.DARK_OAK_LOG) {
                return true;
            }
            if (blockID == Blocks.OAK_LEAVES || blockID == Blocks.DARK_OAK_LEAVES) {
                return true;
            }
            if (blockID == Blocks.OAK_PLANKS) {
                return true;
            }
            if (blockID == Blocks.OAK_SAPLING) {
                return true;
            }
            if (blockID == Blocks.GRASS_BLOCK) {
                return true;
            }
            if (blockID == Blocks.CACTUS) {
                return true;
            }
            if (blockID == ChaosPersists.CrystalPlanksBlock) {
                return true;
            }
            if (blockID == ChaosPersists.MyAppleLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MySkyTreeLog) {
                return true;
            }
            if (blockID == ChaosPersists.MyDT) {
                return true;
            }
            if (blockID == ChaosPersists.MyExperienceLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MyScaryLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MyCherryLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MyPeachLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MyCrystalLeaves) {
                return true;
            }
            if (blockID == ChaosPersists.MyCrystalLeaves2) {
                return true;
            }
            if (blockID == ChaosPersists.MyCrystalLeaves3) {
                return true;
            }
            if (blockID == ChaosPersists.MyCrystalTreeLog) {
                return true;
            }
            return false;
        }
        return blockID == Blocks.COBWEB;
    }

    private boolean isLeaves(Block blockID) {
        if (blockID == Blocks.COBWEB) {
            return true;
        }
        if (blockID == Blocks.OAK_LEAVES || blockID == Blocks.DARK_OAK_LEAVES) {
            return true;
        }
        if (blockID == Blocks.OAK_SAPLING) {
            return true;
        }
        if (blockID == Blocks.GRASS_BLOCK) {
            return true;
        }
        if (blockID == ChaosPersists.MyAppleLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyExperienceLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyScaryLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyCherryLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyPeachLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyCrystalLeaves) {
            return true;
        }
        if (blockID == ChaosPersists.MyCrystalLeaves2) {
            return true;
        }
        if (blockID == ChaosPersists.MyCrystalLeaves3) {
            return true;
        }
        return false;
    }

    @Override
    public boolean mineBlock(ItemStack par1ItemStack, World par2World, BlockState state, BlockPos pos, LivingEntity par7LivingEntity) {
        int par4 = pos.getX();
        int par5 = pos.getY();
        int par6 = pos.getZ();
        if (this == ChaosPersists.MyChainsaw && !par2World.isClientSide) {
            for (int i = -5; i <= 5; ++i) {
                for (int j = -5; j <= 10; ++j) {
                    for (int k = -5; k <= 5; ++k) {
                        BlockPos bp = new BlockPos(par4 + i, par5 + j, par6 + k);
                        Block bid = par2World.getBlockState(bp).getBlock();
                        if (this.leaf) {
                            if (!this.isLeaves(bid)) continue;
                            this.dropItemRand(par2World, Item.byBlock(bid), 1, par4 + i, par5 + j, par6 + k);
                            par2World.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                            continue;
                        }
                        if (!this.canCrush(bid)) continue;
                        this.dropItemRand(par2World, Item.byBlock(bid), 1, par4 + i, par5 + j, par6 + k);
                        par2World.setBlock(bp, Blocks.AIR.defaultBlockState(), 2);
                    }
                }
            }
        }
        return super.mineBlock(par1ItemStack, par2World, state, pos, par7LivingEntity);
    }

    private ItemStack dropItemRand(World world, Item index, int par1, int x, int y, int z) {
        ItemEntity var3 = null;
        ItemStack is = new ItemStack(index, par1);
        var3 = new ItemEntity(world, (double)(x + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5)), (double)y + 1.0 + (double)world.random.nextInt(5), (double)(z + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5)), is);
        if (var3 != null) {
            world.addFreshEntity((Entity)var3);
        }
        return is;
    }

    /**
     * 1.7.10 used {@code getStrVsBlock}; 1.12.2 uses {@link #getDestroySpeed(ItemStack, BlockState)} for mining speed.
     */
    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Block block = state.getBlock();
        if (this == ChaosPersists.MyChainsaw && block != null) {
            this.leaf = this.isLeaves(block);
            Material mat = state.getMaterial();
            if (mat == Material.WOOD || mat == Material.PLANT || mat == Material.REPLACEABLE_PLANT) {
                return ChaosPersists.chainsaw_stats.efficiency;
            }
            if (this.canCrush(block)) {
                return ChaosPersists.chainsaw_stats.efficiency;
            }
        }
        return super.getDestroySpeed(stack, state);
    }
}

