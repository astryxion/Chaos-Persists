package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import com.astryxion.chaospersists.world.dimension.teleporter.UtopiaTeleporter;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;

public class EntityAnt extends Animal {
    private static final ResourceLocation TEXTURE1 =
            new ResourceLocation("chaospersists", "textures/entity/ant.png");
    private static final ResourceLocation TEXTURE2 =
            new ResourceLocation("chaospersists", "textures/entity/red_ant.png");
    private static final ResourceLocation TEXTURE3 =
            new ResourceLocation("chaospersists", "textures/entity/rainbow_ant.png");
    private static final ResourceLocation TEXTURE4 =
            new ResourceLocation("chaospersists", "textures/entity/unstableant.png");
    private static final ResourceLocation TEXTURE5 =
            new ResourceLocation("chaospersists", "textures/entity/termite.png");

    public double moveSpeed = 0.15000000596046448;

    public EntityAnt(EntityType<? extends EntityAnt> type, Level level) {
        super(type, level);
        this.xpReward = 0;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.4));
        this.goalSelector.addGoal(1, new MyEntityAIWanderALot(this, 9, 1.0));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15000000596046448)
                .add(Attributes.ATTACK_DAMAGE, 0.0);
    }

    public ResourceLocation getTexture(EntityAnt a) {
        String n = a.getClass().getSimpleName();
        if ("EntityRedAnt".equals(n)) {
            return TEXTURE2;
        }
        if ("EntityRainbowAnt".equals(n)) {
            return TEXTURE3;
        }
        if ("EntityUnstableAnt".equals(n)) {
            return TEXTURE4;
        }
        if ("Termite".equals(n)) {
            return TEXTURE5;
        }
        return TEXTURE1;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND
                && player != null
                && player.getMainHandItem().isEmpty()
                && this.interactAnt(player)) {
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    public boolean interactAnt(Player par1EntityPlayer) {
        if (par1EntityPlayer == null) {
            return false;
        }
        if (!(par1EntityPlayer instanceof ServerPlayer serverPlayer)) {
            return false;
        }
        ItemStack var2 = par1EntityPlayer.getMainHandItem();
        if (!var2.isEmpty()) {
            return false;
        }
        if (serverPlayer.server == null) {
            return false;
        }
        ResourceKey<Level> utopiaKey = ChaosPersists.getUtopiaDimensionKey();
        ResourceKey<Level> targetDim =
                par1EntityPlayer.level().dimension().equals(utopiaKey) ? Level.OVERWORLD : utopiaKey;
        ServerLevel world = serverPlayer.server.getLevel(targetDim);
        if (world == null) {
            return false;
        }
        serverPlayer.changeDimension(world, new UtopiaTeleporter(serverPlayer.getX(), serverPlayer.getZ()));
        return true;
    }

    public int mygetMaxHealth() {
        return 1;
    }

    @Override
    protected float getSoundVolume() {
        return 0.0f;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    public static boolean checkAntSpawnRules(
            EntityType<? extends EntityAnt> type,
            ServerLevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            net.minecraft.util.RandomSource random) {
        if (pos.getY() < 50) {
            return false;
        }
        if (!(level instanceof Level world)) {
            return false;
        }
        List<? extends EntityAnt> buddies =
                world.getEntitiesOfClass(EntityAnt.class, new AABB(pos).inflate(20.0, 10.0, 20.0));
        return buddies.size() <= 4;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor level, MobSpawnType spawnReason) {
        if (this.getY() < 50.0) {
            return false;
        }
        return this.findAntBuddies() <= 4;
    }

    protected int findAntBuddies() {
        return this.level()
                .getEntitiesOfClass(EntityAnt.class, this.getBoundingBox().inflate(20.0, 10.0, 20.0))
                .size();
    }

    @Override
    protected void customServerAiStep() {
        if (this.getRandom().nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        super.customServerAiStep();
    }
}
