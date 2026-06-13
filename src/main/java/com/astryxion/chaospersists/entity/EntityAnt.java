package com.astryxion.chaospersists.entity;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.MobEntity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosTeleporter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.ITeleporter;

public class EntityAnt extends AnimalEntity {
    public double moveSpeed = 0.15000000596046448;
    private static final ResourceLocation texture1 = new ResourceLocation("chaospersists", "textures/entity/ant.png");
    private static final ResourceLocation texture2 = new ResourceLocation("chaospersists", "textures/entity/red_ant.png");
    private static final ResourceLocation texture3 = new ResourceLocation("chaospersists", "textures/entity/rainbow_ant.png");
    private static final ResourceLocation texture4 = new ResourceLocation("chaospersists", "textures/entity/unstableant.png");
    private static final ResourceLocation texture5 = new ResourceLocation("chaospersists", "textures/entity/termite.png");

    public EntityAnt(EntityType<? extends EntityAnt> type, World par1World) {
        super(type, par1World);
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 9, 1.0));
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .build();
    }

    public ResourceLocation getTexture(EntityAnt a) {
        if (a instanceof EntityRedAnt) {
            return texture2;
        }
        if (a instanceof EntityRainbowAnt) {
            return texture3;
        }
        if (a instanceof EntityUnstableAnt) {
            return texture4;
        }
        if (a instanceof Termite) {
            return texture5;
        }
        return texture1;
    }
    protected boolean canDespawn(double distanceToClosestPlayerEntity) {
        if (this.isPersistenceRequired()) {
            return false;
        }
        return true;
    }

    @Override
    public void tick() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(this.moveSpeed);
        super.tick();
    }

    @Override
    public net.minecraft.util.ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (player != null && (player.getItemInHand(hand) == null || player.getItemInHand(hand).isEmpty())) {
            return this.interact(player) ? net.minecraft.util.ActionResultType.SUCCESS : net.minecraft.util.ActionResultType.PASS;
        }
        return super.mobInteract(player, hand);
    }

    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        if (par1PlayerEntityEntity == null) {
            return false;
        }
        if (!(par1PlayerEntityEntity instanceof ServerPlayerEntity)) {
            return false;
        }
        ItemStack var2 = par1PlayerEntityEntity.inventory.getSelected();
        if (var2 != null && var2.getCount() <= 0) {
            par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
            var2 = null;
        }
        if (var2 != null) {
            return false;
        }
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) par1PlayerEntityEntity;
        MinecraftServer server = this.level.getServer();
        if (server == null) {
            return true;
        }
        ServerWorld targetWorld = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension());
        ServerWorld overworld = server.getLevel(World.OVERWORLD);
        if (targetWorld == null || overworld == null) {
            return false;
        }
        if (serverPlayerEntity.getLevel() != targetWorld) {
            serverPlayerEntity.changeDimension(targetWorld, (ITeleporter) new ChaosTeleporter(targetWorld, ChaosPersists.getDimension(), this.level));
        } else {
            serverPlayerEntity.changeDimension(overworld, (ITeleporter) new ChaosTeleporter(overworld, 0, this.level));
        }
        return true;
    }

    public boolean isAIEnabled() {
        return true;
    }

    public int mygetMaxHealth() {
        return 1;
    }

    @Override
    protected net.minecraft.util.SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getHurtSound(net.minecraft.util.DamageSource damageSource) {
        return null;
    }

    @Override
    protected net.minecraft.util.SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected float getSoundVolume() {
        return 0.0f;
    }

    @Override
    protected void playStepSound(net.minecraft.util.math.BlockPos pos, net.minecraft.block.BlockState state) {
    }

    @Override
    protected void dropCustomDeathLoot(net.minecraft.util.DamageSource source, int looting, boolean recentlyHit) {
    }

    protected boolean canTriggerWalking() {
        return true;
    }

    public AgeableEntity getBreedOffspring(net.minecraft.world.server.ServerWorld level, AgeableEntity mate) {
        return null;
    }

    public boolean checkSpawnRules(net.minecraft.world.IWorldReader world, net.minecraft.entity.SpawnReason reason) {
        if (this.getY() < 50.0) {
            return false;
        }
        if (this.findBuddies() > 4) {
            return false;
        }
        return true;
    }

    private int findBuddies() {
        List<EntityAnt> var5 = this.level.getEntitiesOfClass(EntityAnt.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0));
        return var5.size();
    }

    @Override
    protected void customServerAiStep() {
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
    }
}
