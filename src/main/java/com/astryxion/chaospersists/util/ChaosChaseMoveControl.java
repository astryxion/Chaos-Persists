package com.astryxion.chaospersists.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;

/**
 * Chase MoveControl for OreSpawn walkers.
 *
 * <p>Uses vanilla {@link MoveControl} for path-node facing and jump-when-blocked (so mobs can route
 * around fences and hop 1-block steps). Only soft-aims the <em>head</em> at the chase target —
 * steering body yaw straight at the living target caused spiral turns and fence-line charges.
 *
 * <p>Do not call {@link MyUtils#faceEntity} while pathing; melee-range face is fine.
 */
public class ChaosChaseMoveControl extends MoveControl {
    public ChaosChaseMoveControl(Mob mob) {
        super(mob);
        // 1.7 EntityLivingBase.stepHeight was 0.5 with MoveHelper jumps for full blocks.
        // 1.0 lets these walkers clear a full block (with vanilla jump still available above that).
        mob.getAttribute(net.minecraft.world.entity.ai.attributes.Attributes.STEP_HEIGHT).setBaseValue(1.0F);
    }

    @Override
    public void tick() {
        // Path progress + obstacle jumps (vanilla).
        super.tick();
        // Look at prey without overriding body heading from the path.
        this.lookAtChaseTarget();
    }

    private void lookAtChaseTarget() {
        if (this.operation != Operation.MOVE_TO && this.operation != Operation.JUMPING) {
            return;
        }
        LivingEntity target = this.resolveChaseTarget();
        if (target == null) {
            return;
        }
        float desiredYaw =
                (float) (Mth.atan2(target.getZ() - this.mob.getZ(), target.getX() - this.mob.getX())
                                * (180.0 / Math.PI))
                        - 90.0F;
        this.mob.yHeadRot = this.rotlerp(this.mob.yHeadRot, desiredYaw, 10.0F);
    }

    private LivingEntity resolveChaseTarget() {
        LivingEntity target = this.mob.getTarget();
        if (target != null && target.isAlive()) {
            return target;
        }
        return MyUtils.getChaseTarget(this.mob);
    }
}
