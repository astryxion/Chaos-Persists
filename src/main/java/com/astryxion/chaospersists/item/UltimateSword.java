package com.astryxion.chaospersists.item;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosSounds;
import com.astryxion.chaospersists.util.FriendlyWeaponHits;
import com.astryxion.chaospersists.util.MyUtils;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class UltimateSword extends SwordItem {
    private int swingtimer = 0;
    private boolean leaf = false;

    public UltimateSword(Tier tier) {
        super(tier, ChaosWeaponDamage.TIER_SWORD_MODIFIER, -2.4f, new Properties().stacksTo(1).durability(3000));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        ensureEnchantments(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (this == ChaosPersists.MyChainsaw) {
            if (this.swingtimer > 0) {
                --this.swingtimer;
            }
            if (level.isClientSide && this.swingtimer > 0) {
                float f = 1.0f;
                float dx = (float) (f * Math.cos(Math.toRadians(entity.getYRot() + 90.0f + 45.0f)));
                float dz = (float) (f * Math.sin(Math.toRadians(entity.getYRot() + 90.0f + 45.0f)));
                if (level.getRandom().nextInt(8) == 0) {
                    level.addParticle(
                            ParticleTypes.FLAME,
                            entity.getX() + dx,
                            entity.getY(),
                            entity.getZ() + dz,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f,
                            level.getRandom().nextFloat() / 10.0f,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f);
                }
                if (level.getRandom().nextInt(2) == 0) {
                    level.addParticle(
                            ParticleTypes.SMOKE,
                            entity.getX() + dx,
                            entity.getY(),
                            entity.getZ() + dz,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f,
                            level.getRandom().nextFloat() / 10.0f,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f);
                }
                if (level.getRandom().nextInt(10) == 0) {
                    level.addParticle(
                            ParticleTypes.FIREWORK,
                            entity.getX() + dx,
                            entity.getY(),
                            entity.getZ() + dz,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f,
                            level.getRandom().nextFloat() / 5.0f,
                            (level.getRandom().nextFloat() - level.getRandom().nextFloat()) / 20.0f);
                }
            }
            return;
        }
        ensureEnchantments(stack);
    }

    private void ensureEnchantments(ItemStack stack) {
        if (this == ChaosPersists.MyChainsaw) {
            return;
        }
        int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MOB_LOOTING, stack);
        if (lvl <= 0) {
            int m = ChaosPersists.UltimateSwordMagic;
            if (this != ChaosPersists.MyBattleAxe) {
                stack.enchant(Enchantments.SHARPNESS, m);
                stack.enchant(Enchantments.SMITE, m);
                stack.enchant(Enchantments.BANE_OF_ARTHROPODS, m);
                stack.enchant(Enchantments.KNOCKBACK, 1 + m / 2);
                stack.enchant(Enchantments.MOB_LOOTING, 1 + m / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + m / 2);
                stack.enchant(Enchantments.FIRE_ASPECT, 1 + m / 3);
            } else {
                stack.enchant(Enchantments.MOB_LOOTING, 1 + m / 2);
                stack.enchant(Enchantments.UNBREAKING, 1 + m / 2);
            }
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entityLiving) {
        if (this == ChaosPersists.MyChainsaw && entityLiving != null && this.swingtimer == 0) {
            entityLiving.playSound(
                    ChaosSounds.CHAINSAWSHORT,
                    1.0f,
                    entityLiving.getRandom().nextFloat() * 0.2f + 0.9f);
            this.swingtimer = 50;
        }
        return false;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, e -> e.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity != null
                && (FriendlyWeaponHits.isListedIgnore(entity)
                        || (ChaosPersists.ultimate_sword_pvp == 0
                                && FriendlyWeaponHits.isFriendlyWhenPvpOff(entity)))) {
            return true;
        }
        if (this == ChaosPersists.MyChainsaw && player != null) {
            this.findSomethingToHit(player);
        }
        return false;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 9000;
    }

    private void findSomethingToHit(Player player) {
        List<LivingEntity> candidates =
                player.level()
                        .getEntitiesOfClass(
                                LivingEntity.class, player.getBoundingBox().inflate(5.0, 5.0, 5.0));
        Iterator<LivingEntity> var2 = candidates.iterator();
        while (var2.hasNext()) {
            LivingEntity var4 = var2.next();
            if (!this.isSuitableTarget(var4, player)) {
                continue;
            }
            var4.hurt(
                    player.damageSources().playerAttack(player),
                    (float) ChaosPersists.chainsaw_stats.damage);
        }
    }

    private boolean isSuitableTarget(LivingEntity par1EntityLiving, Player player) {
        if (par1EntityLiving == null) {
            return false;
        }
        if (par1EntityLiving == player) {
            return false;
        }
        if (MyUtils.shouldSkipCombatTarget(player, par1EntityLiving)) {
            return false;
        }
        if (!par1EntityLiving.isAlive()) {
            return false;
        }
        if (FriendlyWeaponHits.isListedIgnore(par1EntityLiving)
                || (ChaosPersists.ultimate_sword_pvp == 0
                        && FriendlyWeaponHits.isFriendlyWhenPvpOff(par1EntityLiving))) {
            return false;
        }
        if (!this.myCanSee(par1EntityLiving, player)) {
            return false;
        }
        return true;
    }

    public boolean myCanSee(LivingEntity e, Player player) {
        int nblks = 10;
        double cx = player.getX();
        double cz = player.getZ();
        float startx = (float) cx;
        float starty = (float) (player.getY() + 1.399999976158142);
        float startz = (float) cz;
        float dx = (float) ((e.getX() - (double) startx) / 10.0);
        float dy = (float) ((e.getY() + (double) (e.getBbHeight() / 2.0f) - (double) starty) / 10.0);
        float dz = (float) ((e.getZ() - (double) startz) / 10.0);
        if ((double) Math.abs(dx) > 1.0) {
            dy /= Math.abs(dx);
            dz /= Math.abs(dx);
            nblks = (int) ((float) nblks * Math.abs(dx));
            if (dx > 1.0f) {
                dx = 1.0f;
            }
            if (dx < -1.0f) {
                dx = -1.0f;
            }
        }
        if ((double) Math.abs(dy) > 1.0) {
            dx /= Math.abs(dy);
            dz /= Math.abs(dy);
            nblks = (int) ((float) nblks * Math.abs(dy));
            if (dy > 1.0f) {
                dy = 1.0f;
            }
            if (dy < -1.0f) {
                dy = -1.0f;
            }
        }
        if ((double) Math.abs(dz) > 1.0) {
            dy /= Math.abs(dz);
            dx /= Math.abs(dz);
            nblks = (int) ((float) nblks * Math.abs(dz));
            if (dz > 1.0f) {
                dz = 1.0f;
            }
            if (dz < -1.0f) {
                dz = -1.0f;
            }
        }
        Level level = player.level();
        for (int i = 0; i < nblks; ++i) {
            BlockPos pos = BlockPos.containing(startx += dx, starty += dy, startz += dz);
            if (level.getBlockState(pos).isAir()) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (this == ChaosPersists.MyChainsaw) {
            return this.canCrush(state.getBlock());
        }
        return super.isCorrectToolForDrops(stack, state);
    }

    private boolean canCrush(Block blockID) {
        if (this == ChaosPersists.MyChainsaw) {
            if (blockID == Blocks.COBWEB) {
                return true;
            }
            if (blockID == Blocks.OAK_LOG
                    || blockID == Blocks.BIRCH_LOG
                    || blockID == Blocks.SPRUCE_LOG
                    || blockID == Blocks.JUNGLE_LOG
                    || blockID == Blocks.ACACIA_LOG
                    || blockID == Blocks.DARK_OAK_LOG
                    || blockID == Blocks.MANGROVE_LOG
                    || blockID == Blocks.CHERRY_LOG
                    || blockID == Blocks.CRIMSON_STEM
                    || blockID == Blocks.WARPED_STEM
                    || blockID == Blocks.OAK_LEAVES
                    || blockID == Blocks.BIRCH_LEAVES
                    || blockID == Blocks.SPRUCE_LEAVES
                    || blockID == Blocks.JUNGLE_LEAVES
                    || blockID == Blocks.ACACIA_LEAVES
                    || blockID == Blocks.DARK_OAK_LEAVES
                    || blockID == Blocks.MANGROVE_LEAVES
                    || blockID == Blocks.CHERRY_LEAVES
                    || blockID == Blocks.AZALEA_LEAVES
                    || blockID == Blocks.FLOWERING_AZALEA_LEAVES
                    || blockID == Blocks.OAK_PLANKS
                    || blockID == Blocks.OAK_SAPLING
                    || blockID == net.minecraft.world.level.block.Blocks.GRASS
                    || blockID == Blocks.TALL_GRASS
                    || blockID == Blocks.CACTUS) {
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
        if (blockID == Blocks.OAK_LEAVES
                || blockID == Blocks.BIRCH_LEAVES
                || blockID == Blocks.SPRUCE_LEAVES
                || blockID == Blocks.JUNGLE_LEAVES
                || blockID == Blocks.ACACIA_LEAVES
                || blockID == Blocks.DARK_OAK_LEAVES
                || blockID == Blocks.MANGROVE_LEAVES
                || blockID == Blocks.CHERRY_LEAVES
                || blockID == Blocks.AZALEA_LEAVES
                || blockID == Blocks.FLOWERING_AZALEA_LEAVES
                || blockID == Blocks.OAK_SAPLING
                || blockID == net.minecraft.world.level.block.Blocks.GRASS
                || blockID == Blocks.TALL_GRASS) {
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
    public boolean mineBlock(
            ItemStack stack,
            Level level,
            BlockState state,
            BlockPos pos,
            LivingEntity entityLiving) {
        Block par3 = state.getBlock();
        int par4 = pos.getX();
        int par5 = pos.getY();
        int par6 = pos.getZ();
        if (this == ChaosPersists.MyChainsaw && !level.isClientSide) {
            for (int i = -5; i <= 5; ++i) {
                for (int j = -5; j <= 10; ++j) {
                    for (int k = -5; k <= 5; ++k) {
                        BlockPos targetPos = new BlockPos(par4 + i, par5 + j, par6 + k);
                        Block bid = level.getBlockState(targetPos).getBlock();
                        if (this.leaf) {
                            if (!this.isLeaves(bid)) {
                                continue;
                            }
                            this.dropItemRand(
                                    level,
                                    bid.asItem(),
                                    1,
                                    par4 + i,
                                    par5 + j,
                                    par6 + k);
                            level.setBlockAndUpdate(targetPos, Blocks.AIR.defaultBlockState());
                            continue;
                        }
                        if (!this.canCrush(bid)) {
                            continue;
                        }
                        this.dropItemRand(
                                level, bid.asItem(), 1, par4 + i, par5 + j, par6 + k);
                        level.setBlockAndUpdate(targetPos, Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
        return super.mineBlock(stack, level, state, pos, entityLiving);
    }

    private void dropItemRand(Level world, Item index, int count, int x, int y, int z) {
        if (index == null) {
            return;
        }
        ItemStack is = new ItemStack(index, count);
        ItemEntity drop =
                new ItemEntity(
                        world,
                        (double) (x + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5)),
                        (double) y + 1.0 + (double) world.getRandom().nextInt(5),
                        (double) (z + ChaosPersists.ChaosRand.nextInt(5) - ChaosPersists.ChaosRand.nextInt(5)),
                        is);
        world.addFreshEntity(drop);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Block block = state.getBlock();
        if (this == ChaosPersists.MyChainsaw && block != null) {
            this.leaf = this.isLeaves(block);
            if (state.is(BlockTags.LOGS)
                    || state.is(BlockTags.LEAVES)
                    || state.is(BlockTags.SAPLINGS)
                    || state.is(BlockTags.REPLACEABLE)) {
                return ChaosPersists.chainsaw_stats.efficiency;
            }
            if (this.canCrush(block)) {
                return ChaosPersists.chainsaw_stats.efficiency;
            }
        }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        com.astryxion.chaospersists.client.BigWeaponClientExtensions.register(consumer, this);
    }
}
