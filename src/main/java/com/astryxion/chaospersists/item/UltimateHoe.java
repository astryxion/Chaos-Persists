package com.astryxion.chaospersists.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class UltimateHoe extends HoeItem {
    private static final int WEAPON_DAMAGE = 1;

    public UltimateHoe(Tier tier) {
        super(tier, new Properties().stacksTo(1).durability(3000).attributes(HoeItem.createAttributes(tier, ChaosWeaponDamage.modifierFor(tier, WEAPON_DAMAGE), -0.0f)));
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level level, Player player) {
        ensureEnchantments(stack, level);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        ensureEnchantments(stack, level);
    }

    private void ensureEnchantments(ItemStack stack, Level level) {
        if (com.astryxion.chaospersists.util.ChaosEnchantments.level(Enchantments.EFFICIENCY, stack) <= 0) {
            stack.enchant(level.registryAccess().registryOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.EFFICIENCY), 2);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (player == null) {
            return InteractionResult.PASS;
        }
        BlockPos pos = context.getClickedPos();
        if (!player.mayUseItemAt(pos, context.getClickedFace(), context.getItemInHand())) {
            return InteractionResult.FAIL;
        }
        Block i1 = level.getBlockState(pos).getBlock();
        boolean air = level.isEmptyBlock(pos.above());
        if (context.getClickedFace() != net.minecraft.core.Direction.DOWN
                && air
                && (i1 == Blocks.GRASS_BLOCK || i1 == Blocks.DIRT)) {
            Block block = Blocks.FARMLAND;
            BlockState farmland = block.defaultBlockState();
            level.playSound(
                    player,
                    pos,
                    SoundEvents.HOE_TILL,
                    SoundSource.BLOCKS,
                    1.0f,
                    1.0f);
            if (level.isClientSide) {
                return InteractionResult.SUCCESS;
            }
            int par4 = pos.getX();
            int par5 = pos.getY();
            int par6 = pos.getZ();
            for (int i = -1; i <= 1; ++i) {
                for (int k = -1; k <= 1; ++k) {
                    for (int j = -1; j <= 1; ++j) {
                        BlockPos targetPos = new BlockPos(par4 + i, par5 + j, par6 + k);
                        Block below = level.getBlockState(targetPos).getBlock();
                        boolean aboveAir = level.isEmptyBlock(targetPos.above());
                        if (!aboveAir
                                || (below != Blocks.GRASS_BLOCK && below != Blocks.DIRT)) {
                            continue;
                        }
                        level.setBlock(targetPos, farmland, 7);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, targetPos, GameEvent.Context.of(player));
                    }
                }
            }
            context.getItemInHand()
                    .hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public String getMaterialName() {
        return "Uranium/Titanium";
    }
}
