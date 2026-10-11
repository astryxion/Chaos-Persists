package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Girlfriend;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class MyEntityAIDance extends Goal {
    private final Girlfriend thePet;
    private final Level theWorld;
    public int ticker = 0;
    public int dance_move = 0;
    public int is_dancing = 0;

    public MyEntityAIDance(Girlfriend par1EntityTameable) {
        this.thePet = par1EntityTameable;
        this.theWorld = par1EntityTameable.level();
    }

    public boolean is_dance_block(Block bid) {
        return bid == Blocks.GOLD_BLOCK
                || bid == Blocks.DIAMOND_BLOCK
                || bid == Blocks.EMERALD_BLOCK
                || bid == ChaosPersists.MyBlockRubyBlock
                || bid == ChaosPersists.MyBlockAmethystBlock
                || bid == ChaosPersists.MyBlockTitaniumBlock
                || bid == ChaosPersists.MyBlockUraniumBlock;
    }

    @Override
    public boolean canUse() {
        if (this.thePet.isInSittingPose()) {
            return false;
        }
        long t = this.theWorld.getDayTime() % 24000L;
        if (t < 14000L || t > 22000L) {
            return false;
        }
        int ic = 0;
        for (int i = -3; i < 4; ++i) {
            for (int j = -3; j < 4; ++j) {
                Block bid =
                        this.theWorld
                                .getBlockState(
                                        new BlockPos(
                                                Mth.floor(this.thePet.getX()) + i,
                                                Mth.floor(this.thePet.getY()) - 1,
                                                Mth.floor(this.thePet.getZ()) + j))
                                .getBlock();
                if (this.is_dance_block(bid)) {
                    ++ic;
                }
            }
        }
        return ic != 0;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.thePet.isInSittingPose()) {
            return false;
        }
        long t = this.theWorld.getDayTime() % 24000L;
        if (t < 14000L || t > 22000L) {
            return false;
        }
        int ic = 0;
        int iz = 0;
        int ix = 0;
        for (int i = -3; i < 4; ++i) {
            for (int j = -3; j < 4; ++j) {
                Block bid =
                        this.theWorld
                                .getBlockState(
                                        new BlockPos(
                                                Mth.floor(this.thePet.getX()) + i,
                                                Mth.floor(this.thePet.getY()) - 1,
                                                Mth.floor(this.thePet.getZ()) + j))
                                .getBlock();
                if (!this.is_dance_block(bid)) {
                    continue;
                }
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
            this.thePet
                    .getNavigation()
                    .moveTo(
                            (double) (Mth.floor(this.thePet.getX()) + ix),
                            (double) Mth.floor(this.thePet.getY()),
                            (double) (Mth.floor(this.thePet.getZ()) + iz),
                            1.0);
        } else if (this.theWorld.getRandom().nextInt(3) == 1) {
            this.thePet
                    .getNavigation()
                    .moveTo(
                            (double) Mth.floor(this.thePet.getX()),
                            (double) Mth.floor(this.thePet.getY()),
                            (double) Mth.floor(this.thePet.getZ()),
                            1.0);
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
                Block bid =
                        this.theWorld
                                .getBlockState(
                                        new BlockPos(
                                                Mth.floor(this.thePet.getX()) + i,
                                                Mth.floor(this.thePet.getY()) - 1,
                                                Mth.floor(this.thePet.getZ()) + j))
                                .getBlock();
                if (!this.is_dance_block(bid)) {
                    continue;
                }
                ++ic;
                ix += i;
                iz += j;
            }
        }
        if (ic > 0) {
            ix /= ic;
            iz /= ic;
            if (ic < 40) {
                this.thePet
                        .getNavigation()
                        .moveTo(
                                (double) (Mth.floor(this.thePet.getX()) + ix),
                                (double) Mth.floor(this.thePet.getY()),
                                (double) (Mth.floor(this.thePet.getZ()) + iz),
                                1.0);
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
    public void tick() {
        int cycle = 20;
        int halfc = cycle / 2;
        int mover = cycle * 8;
        int tempid = this.thePet.getId();
        AABB bb =
                new AABB(
                        this.thePet.getX() - 4.0,
                        this.thePet.getY() - 3.0,
                        this.thePet.getZ() - 4.0,
                        this.thePet.getX() + 4.0,
                        this.thePet.getY() + 3.0,
                        this.thePet.getZ() + 4.0);
        List<Girlfriend> var5 = this.theWorld.getEntitiesOfClass(Girlfriend.class, bb);
        for (Girlfriend var3 : var5) {
            if (var3.getId() < tempid) {
                if (var3.Dance.is_dancing == 1) {
                    this.ticker = var3.Dance.ticker;
                    this.dance_move = var3.Dance.dance_move;
                }
                tempid = var3.getId();
            }
        }
        this.ticker += 1;
        if (this.dance_move == 0) {
            this.dance_move = 1 + this.theWorld.getRandom().nextInt(10);
            this.thePet.setDeltaMovement(Vec3.ZERO);
            this.ticker = 0;
            this.thePet.setShiftKeyDown(false);
        }
        switch (this.dance_move) {
            case 1:
                move_it(this.thePet, this.ticker, cycle, 0);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 2:
                move_it(this.thePet, this.ticker, cycle, 1);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 3:
                if (this.ticker % cycle < halfc) {
                    this.thePet.setShiftKeyDown(false);
                } else {
                    this.thePet.setShiftKeyDown(true);
                }
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 4:
                if (this.ticker % halfc == 1) {
                    this.thePet.swing(InteractionHand.MAIN_HAND);
                    this.thePet.setDeltaMovement(this.thePet.getDeltaMovement().add(0.0, 0.25, 0.0));
                }
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 5:
                if (this.ticker % halfc == 1) {
                    this.thePet.swing(InteractionHand.MAIN_HAND);
                }
                move_it(this.thePet, this.ticker, cycle, 0);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 6:
                if (this.ticker % halfc == 1) {
                    this.thePet.swing(InteractionHand.MAIN_HAND);
                }
                move_it(this.thePet, this.ticker, cycle, 1);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 7:
                if (this.ticker % cycle < halfc) {
                    this.thePet.setShiftKeyDown(false);
                } else {
                    this.thePet.setShiftKeyDown(true);
                }
                move_it(this.thePet, this.ticker, cycle, 0);
                move_it(this.thePet, this.ticker, cycle, 2);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 8:
                if (this.ticker % cycle < halfc) {
                    this.thePet.setShiftKeyDown(false);
                } else {
                    this.thePet.setShiftKeyDown(true);
                }
                move_it(this.thePet, this.ticker, cycle, 1);
                move_it(this.thePet, this.ticker, cycle, 2);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 9:
                if (this.ticker % cycle < halfc) {
                    this.thePet.setShiftKeyDown(false);
                } else {
                    this.thePet.setShiftKeyDown(true);
                }
                if (this.ticker % halfc == 1) {
                    this.thePet.swing(InteractionHand.MAIN_HAND);
                }
                move_it(this.thePet, this.ticker, cycle, 0);
                move_it(this.thePet, this.ticker, cycle, 3);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            case 10:
                if (this.ticker % cycle < halfc) {
                    this.thePet.setShiftKeyDown(false);
                    this.thePet.setDeltaMovement(this.thePet.getDeltaMovement().add(0.0, 0.25, 0.0));
                } else {
                    this.thePet.setShiftKeyDown(true);
                }
                if (this.ticker % halfc == 1) {
                    this.thePet.swing(InteractionHand.MAIN_HAND);
                }
                move_it(this.thePet, this.ticker, cycle, 1);
                move_it(this.thePet, this.ticker, cycle, 3);
                if (this.ticker > mover) {
                    this.dance_move = 0;
                }
                break;
            default:
                this.dance_move = 0;
        }
    }

    private void move_it(Girlfriend et, int t, int cycle, int dir) {
        float dirx = 0.0f;
        float dirz = 0.0f;
        float dirYaw = 0.0f;
        float dirYawH = 0.0f;
        switch (dir) {
            case 0:
                dirx = 0.02f;
                break;
            case 1:
                dirz = 0.02f;
                break;
            case 2:
                dirYaw = 10.0f;
                break;
            case 3:
                dirYawH = 10.0f;
                break;
            default:
                break;
        }
        int modCycle = t % cycle;
        if (modCycle >= cycle / 2) {
            dirx = -dirx;
            dirz = -dirz;
            dirYaw = -dirYaw;
            dirYawH = -dirYawH;
        }
        if (modCycle % (cycle / 2) >= cycle / 4) {
            dirYaw = -dirYaw;
            dirYawH = -dirYawH;
        }
        Vec3 motion = et.getDeltaMovement();
        et.setDeltaMovement(motion.x + (double) dirx, motion.y, motion.z + (double) dirz);
        et.setYRot(et.getYRot() + dirYaw);
        et.setYHeadRot(et.getYHeadRot() + dirYawH);
    }
}
