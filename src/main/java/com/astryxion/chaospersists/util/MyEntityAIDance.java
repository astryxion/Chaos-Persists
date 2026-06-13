/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  com.astryxion.chaospersists.Girlfriend
 *  com.astryxion.chaospersists.MyEntityAIDance
 *  com.astryxion.chaospersists.ChaosPersists
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.goal.Goal
 *  net.minecraft.entity.passive.TameableEntity
 *  net.minecraft.block.Blocks
 *  net.minecraft.pathfinding.PathNavigator
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.core.ChaosPersists;
import java.util.List;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.block.Blocks;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class MyEntityAIDance
extends Goal {
    private Girlfriend thePet;
    World theWorld;
    public int ticker = 0;
    public int dance_move = 0;
    public int is_dancing = 0;

    public MyEntityAIDance(Girlfriend par1TameableEntity) {
        this.thePet = par1TameableEntity;
        this.theWorld = par1TameableEntity.level;
    }

    public boolean is_dance_block(Block bid) {
        if (bid == Blocks.GOLD_BLOCK || bid == Blocks.DIAMOND_BLOCK || bid == Blocks.EMERALD_BLOCK || bid == ChaosPersists.MyBlockRubyBlock || bid == ChaosPersists.MyBlockAmethystBlock || bid == ChaosPersists.MyBlockTitaniumBlock || bid == ChaosPersists.MyBlockUraniumBlock) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canUse() {
        if (this.thePet.isOrderedToSit()) {
            return false;
        }
        long t = this.theWorld.getGameTime();
        if ((t %= 24000L) < 14000L || t > 22000L) {
            return false;
        }
        int ic = 0;
        int iz = 0;
        int ix = 0;
        for (int i = -3; i < 4; ++i) {
            for (int j = -3; j < 4; ++j) {
                Block bid = this.theWorld.getBlockState(new net.minecraft.util.math.BlockPos((int)this.thePet.getX() + i, (int)this.thePet.getY() - 1, (int)this.thePet.getZ() + j)).getBlock();
                if (!this.is_dance_block(bid)) continue;
                ++ic;
                ix += i;
                iz += j;
            }
        }
        if (ic == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.thePet.isOrderedToSit()) {
            return false;
        }
        long t = this.theWorld.getGameTime();
        if ((t %= 24000L) < 14000L || t > 22000L) {
            return false;
        }
        int ic = 0;
        int iz = 0;
        int ix = 0;
        for (int i = -3; i < 4; ++i) {
            for (int j = -3; j < 4; ++j) {
                Block bid = this.theWorld.getBlockState(new net.minecraft.util.math.BlockPos((int)this.thePet.getX() + i, (int)this.thePet.getY() - 1, (int)this.thePet.getZ() + j)).getBlock();
                if (!this.is_dance_block(bid)) continue;
                ++ic;
                ix += i;
                iz += j;
            }
        }
        if (ic == 0) {
            return false;
        }
        ix /= ic;
        iz /= ic;
        if (ic < 40) {
            this.thePet.getNavigation().moveTo((double)((int)this.thePet.getX() + ix), (double)((int)this.thePet.getY()), (double)((int)this.thePet.getZ() + iz), 1.0);
        } else if (this.theWorld.random.nextInt(3) == 1) {
            this.thePet.getNavigation().moveTo((double)((int)this.thePet.getX()), (double)((int)this.thePet.getY()), (double)((int)this.thePet.getZ()), 1.0);
        }
        this.is_dancing = 1;
        return true;
    }

    @Override
    public void start() {
        this.thePet.setShiftKeyDown(false);
        this.ticker = 0;
        this.dance_move = 0;
        this.is_dancing = 1;
        int ic = 0;
        int iz = 0;
        int ix = 0;
        for (int i = -3; i < 4; ++i) {
            for (int j = -3; j < 4; ++j) {
                Block bid = this.theWorld.getBlockState(new net.minecraft.util.math.BlockPos((int)this.thePet.getX() + i, (int)this.thePet.getY() - 1, (int)this.thePet.getZ() + j)).getBlock();
                if (!this.is_dance_block(bid)) continue;
                ++ic;
                ix += i;
                iz += j;
            }
        }
        if (ic > 0) {
            ix /= ic;
            iz /= ic;
            if (ic < 40) {
                this.thePet.getNavigation().moveTo((double)((int)this.thePet.getX() + ix), (double)((int)this.thePet.getY()), (double)((int)this.thePet.getZ() + iz), 1.0);
            }
        }
    }

    @Override
    public void stop() {
        this.thePet.setShiftKeyDown(false);
        this.ticker = 0;
        this.dance_move = 0;
        this.is_dancing = 0;
    }


    @Override
    public void tick()
    {
      int cycle = 20;
      int halfc = cycle / 2;
      int mover = cycle * 8;
      int tempid = this.thePet.getId();

      AxisAlignedBB bb = new AxisAlignedBB(this.thePet.getX() - 4.0D, this.thePet.getY() - 3.0D, this.thePet.getZ() - 4.0D, this.thePet.getX() + 4.0D, this.thePet.getY() + 3.0D, this.thePet.getZ() + 4.0D);
      List var5 = this.theWorld.getEntitiesOfClass(Girlfriend.class, bb);
      Iterator var2 = var5.iterator();
      while (var2.hasNext())
      {
        Girlfriend var3 = (Girlfriend)var2.next();
        if (var3.getId() < tempid)
        {
          if (var3.Dance.is_dancing == 1) {
            this.ticker = var3.Dance.ticker;
            this.dance_move = var3.Dance.dance_move;
          }
          tempid = var3.getId();
        }
      }

      this.ticker += 1;

      if (this.dance_move == 0) {
        this.dance_move = (1 + this.theWorld.random.nextInt(10));
        this.thePet.setDeltaMovement(0.0D, thePet.getDeltaMovement().y, thePet.getDeltaMovement().z);
        this.thePet.setDeltaMovement(thePet.getDeltaMovement().x, thePet.getDeltaMovement().y, 0.0D);
        this.ticker = 0;
        this.thePet.setShiftKeyDown(false);
      }

      switch (this.dance_move) {
      case 1:
        move_it(this.thePet, this.ticker, cycle, 0);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 2:
        move_it(this.thePet, this.ticker, cycle, 1);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 3:
        if (this.ticker % cycle < halfc)
          this.thePet.setShiftKeyDown(false);
        else {
          this.thePet.setShiftKeyDown(true);
        }
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 4:
        if (this.ticker % halfc == 1) {
          this.thePet.swing(net.minecraft.util.Hand.MAIN_HAND);
          this.thePet.setDeltaMovement(thePet.getDeltaMovement().x, 0.25D, thePet.getDeltaMovement().z);
        }

        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 5:
        if (this.ticker % halfc == 1) {
          this.thePet.swing(net.minecraft.util.Hand.MAIN_HAND);
        }
        move_it(this.thePet, this.ticker, cycle, 0);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 6:
        if (this.ticker % halfc == 1) {
          this.thePet.swing(net.minecraft.util.Hand.MAIN_HAND);
        }
        move_it(this.thePet, this.ticker, cycle, 1);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 7:
        if (this.ticker % cycle < halfc)
          this.thePet.setShiftKeyDown(false);
        else {
          this.thePet.setShiftKeyDown(true);
        }
        move_it(this.thePet, this.ticker, cycle, 0);
        move_it(this.thePet, this.ticker, cycle, 2);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 8:
        if (this.ticker % cycle < halfc)
          this.thePet.setShiftKeyDown(false);
        else {
          this.thePet.setShiftKeyDown(true);
        }
        move_it(this.thePet, this.ticker, cycle, 1);
        move_it(this.thePet, this.ticker, cycle, 2);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 9:
        if (this.ticker % cycle < halfc)
          this.thePet.setShiftKeyDown(false);
        else {
          this.thePet.setShiftKeyDown(true);
        }
        if (this.ticker % halfc == 1) {
          this.thePet.swing(net.minecraft.util.Hand.MAIN_HAND);
        }
        move_it(this.thePet, this.ticker, cycle, 0);
        move_it(this.thePet, this.ticker, cycle, 3);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      case 10:
        if (this.ticker % cycle < halfc) {
          this.thePet.setShiftKeyDown(false);
          this.thePet.setDeltaMovement(thePet.getDeltaMovement().x, 0.25D, thePet.getDeltaMovement().z);
        } else {
          this.thePet.setShiftKeyDown(true);
        }
        if (this.ticker % halfc == 1) {
          this.thePet.swing(net.minecraft.util.Hand.MAIN_HAND);
        }
        move_it(this.thePet, this.ticker, cycle, 1);
        move_it(this.thePet, this.ticker, cycle, 3);
        if (this.ticker <= mover) break; this.dance_move = 0; break;
      default:
        this.dance_move = 0;
      }
    }

    private void move_it(TameableEntity et, int t, int cycle, int dir) {
        float dirx = 0.0f;
        float dirz = 0.0f;
        float dirYaw = 0.0f;
        float dirYawH = 0.0f;
        switch (dir) {
            case 0: {
                dirx = 0.02f;
                dirz = 0.0f;
                dirYaw = 0.0f;
                dirYawH = 0.0f;
                break;
            }
            case 1: {
                dirx = 0.0f;
                dirz = 0.02f;
                dirYaw = 0.0f;
                dirYawH = 0.0f;
                break;
            }
            case 2: {
                dirx = 0.0f;
                dirz = 0.0f;
                dirYaw = 10.0f;
                dirYawH = 0.0f;
                break;
            }
            case 3: {
                dirx = 0.0f;
                dirz = 0.0f;
                dirYaw = 0.0f;
                dirYawH = 10.0f;
                break;
            }
        }
        if ((t %= cycle) >= cycle / 2) {
            dirx = - dirx;
            dirz = - dirz;
            dirYaw = - dirYaw;
            dirYawH = - dirYawH;
        }
        if ((t %= cycle / 2) >= cycle / 4) {
            dirYaw = - dirYaw;
            dirYawH = - dirYawH;
        }
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(et, (double)dirx, 0.0, 0.0);
        com.astryxion.chaospersists.util.MyUtils.addDeltaMovement(et, 0.0, 0.0, (double)dirz);
        et.yRot += dirYaw;
        et.yHeadRot += dirYawH;
    }
}

