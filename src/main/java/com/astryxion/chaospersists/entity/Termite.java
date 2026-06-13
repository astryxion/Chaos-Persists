package com.astryxion.chaospersists.entity;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.core.ChaosTeleporter;
import com.astryxion.chaospersists.util.MyEntityAIWanderALot;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.util.DamageSource;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.minecraftforge.common.util.ITeleporter;
import net.minecraftforge.registries.ForgeRegistries;

public class Termite extends EntityAnt {
    int attack_delay = 20;
    private int closest = 99999;
    private int tx = 0;
    private int ty = 0;
    private int tz = 0;

    public Termite(EntityType<? extends Termite> type, World par1World) {
        super(type, par1World);
        // EntityType registration: width=0.2f, height=0.2f
        this.moveSpeed = 0.20000000298023224;
        this.xpReward = 1;
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.399999976158142));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new MyEntityAIWanderALot(this, 8, 1.0));
        if (ChaosPersists.PlayNicely == 0) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, false));
        }
    }

    public static AttributeModifierMap createAttributes() {
        return MobEntity.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 5.0)
                .add(Attributes.MOVEMENT_SPEED, 0.20000000298023224)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .build();
    }

    @Override
    public int mygetMaxHealth() {
        return 5;
    }

    public boolean doHurtTarget(LivingEntity par1Entity) {
        if (ChaosPersists.ChaosRand.nextInt(15) != 0) {
            return false;
        }
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return false;
        }
        return par1Entity.hurt(DamageSource.mobAttack(this), 1.0f);
    }

    @Override
    public boolean interact(PlayerEntity par1PlayerEntityEntity) {
        if (par1PlayerEntityEntity == null) {
            return false;
        }
        if (!(par1PlayerEntityEntity instanceof ServerPlayerEntity)) {
            return false;
        }
        ItemStack var2 = par1PlayerEntityEntity.getMainHandItem();
        if (!var2.isEmpty() && var2.getCount() <= 0) {
            par1PlayerEntityEntity.inventory.setItem(par1PlayerEntityEntity.inventory.selected, ItemStack.EMPTY);
            var2 = ItemStack.EMPTY;
        }
        if (!var2.isEmpty()) {
            par1PlayerEntityEntity.sendMessage(new StringTextComponent("Empty your hand!"), par1PlayerEntityEntity.getUUID());
            return false;
        }
        ServerWorld crystalDim = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(5));
        if (crystalDim != null && par1PlayerEntityEntity.level != crystalDim) {
            int i;
            for (i = 0; i < par1PlayerEntityEntity.inventory.items.size(); ++i) {
                if (par1PlayerEntityEntity.inventory.items.get(i).isEmpty()) {
                    continue;
                }
                par1PlayerEntityEntity.sendMessage(new StringTextComponent("Empty your inventory!"), par1PlayerEntityEntity.getUUID());
                return false;
            }
            for (i = 0; i < par1PlayerEntityEntity.inventory.armor.size(); ++i) {
                if (par1PlayerEntityEntity.inventory.armor.get(i).isEmpty()) {
                    continue;
                }
                par1PlayerEntityEntity.sendMessage(new StringTextComponent("Take off your armor!"), par1PlayerEntityEntity.getUUID());
                return false;
            }
            MinecraftServer server = this.level.getServer();
            if (server != null) {
                ServerWorld targetWorld = ChaosPersists.getServerWorldByDimensionId(ChaosPersists.getDimension(5));
                if (targetWorld != null) {
                    ((ServerPlayerEntity) par1PlayerEntityEntity).changeDimension(targetWorld, (ITeleporter) new ChaosTeleporter(targetWorld, ChaosPersists.getDimension(5), this.level));
                }
            }
        } else {
            MinecraftServer srv = this.level.getServer();
            if (srv != null) {
                ServerWorld overworld = srv.getLevel(World.OVERWORLD);
                if (overworld != null) {
                    ((ServerPlayerEntity) par1PlayerEntityEntity).changeDimension(overworld, (ITeleporter) new ChaosTeleporter(overworld, 0, this.level));
                }
            }
        }
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.removed) {
            return;
        }
        if (this.attack_delay > 0) {
            --this.attack_delay;
        }
        if (this.attack_delay > 0) {
            return;
        }
        this.attack_delay = 20;
        if (this.level.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }
        PlayerEntity e = this.level.getNearestPlayer(this, 1.5);
        if (e != null) {
            this.doHurtTarget(e);
        }
    }

    public boolean isWood(Block bid) {
        if (bid == Blocks.OAK_FENCE || bid == Blocks.OAK_FENCE_GATE || bid == Blocks.OAK_PLANKS || bid == Blocks.OAK_SLAB) {
            return true;
        }
        if (bid == Blocks.OAK_SLAB || bid == Blocks.RED_BED || bid == Blocks.CRAFTING_TABLE) {
            return true;
        }
        if (bid == Blocks.OAK_SIGN || bid == Blocks.BOOKSHELF || bid == Blocks.OAK_DOOR || bid == Blocks.OAK_PRESSURE_PLATE) {
            return true;
        }
        if (bid == Blocks.BIRCH_STAIRS || bid == Blocks.OAK_STAIRS || bid == Blocks.JUNGLE_STAIRS || bid == Blocks.SPRUCE_STAIRS) {
            return true;
        }
        if (bid == ChaosPersists.CrystalPlanksBlock) {
            return true;
        }
        return false;
    }

    private boolean scan_it(int x, int y, int z, int dx, int dy, int dz) {
        int i;
        Block bid;
        int d;
        int j;
        int found = 0;
        for (i = -dy; i <= dy; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + dx, y + i, z + j)).getBlock();
                if (this.isWood(bid) && (d = dx * dx + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + dx;
                    this.ty = y + i;
                    this.tz = z + j;
                    ++found;
                }
                if (!this.isWood(bid = this.level.getBlockState(new BlockPos(x - dx, y + i, z + j)).getBlock()) || (d = dx * dx + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x - dx;
                this.ty = y + i;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dz; j <= dz; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + i, y + dy, z + j)).getBlock();
                if (this.isWood(bid) && (d = dy * dy + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + dy;
                    this.tz = z + j;
                    ++found;
                }
                if (!this.isWood(bid = this.level.getBlockState(new BlockPos(x + i, y - dy, z + j)).getBlock()) || (d = dy * dy + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y - dy;
                this.tz = z + j;
                ++found;
            }
        }
        for (i = -dx; i <= dx; ++i) {
            for (j = -dy; j <= dy; ++j) {
                bid = this.level.getBlockState(new BlockPos(x + i, y + j, z + dz)).getBlock();
                if (this.isWood(bid) && (d = dz * dz + j * j + i * i) < this.closest) {
                    this.closest = d;
                    this.tx = x + i;
                    this.ty = y + j;
                    this.tz = z + dz;
                    ++found;
                }
                if (!this.isWood(bid = this.level.getBlockState(new BlockPos(x + i, y + j, z - dz)).getBlock()) || (d = dz * dz + j * j + i * i) >= this.closest) {
                    continue;
                }
                this.closest = d;
                this.tx = x + i;
                this.ty = y + j;
                this.tz = z - dz;
                ++found;
            }
        }
        return found != 0;
    }

    @Override
    protected void customServerAiStep() {
        if (this.removed) {
            return;
        }
        if (this.level.random.nextInt(200) == 1) {
            this.setLastHurtByMob(null);
        }
        if (this.level.random.nextInt(200) == 1 && ChaosPersists.PlayNicely == 0) {
            int i;
            this.closest = 99999;
            this.tz = 0;
            this.ty = 0;
            this.tx = 0;
            for (i = 1; i < 8; ++i) {
                int j = i;
                if (j > 4) {
                    j = 4;
                }
                if (this.scan_it((int) this.getX(), (int) this.getY() + 1, (int) this.getZ(), i, j, i)) {
                    break;
                }
                if (i < 5) {
                    continue;
                }
                ++i;
            }
            if (this.closest < 99999) {
                this.getNavigation().moveTo((double) this.tx, (double) this.ty, (double) this.tz, 1.0);
                if (this.closest < 6) {
                    if (this.level.random.nextInt(3) != 0) {
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                            this.level.setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.DIRT.defaultBlockState(), 2);
                        }
                        if (this.findBuddies() < 10) {
                            Termite.spawnCreature(this.level, "Termite", this.getX() + 0.10000000149011612, this.getY() + 0.10000000149011612, this.getZ() + 0.10000000149011612);
                        }
                    } else {
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
                            this.level.setBlock(new BlockPos(this.tx, this.ty, this.tz), Blocks.AIR.defaultBlockState(), 2);
                        }
                        if (this.findBuddies() < 10) {
                            Termite.spawnCreature(this.level, "Termite", (double) ((float) this.tx + 0.1f), (double) ((float) this.ty + 0.1f), (double) ((float) this.tz + 0.1f));
                        }
                    }
                    this.heal(1.0f);
                }
            }
        }
        super.customServerAiStep();
    }

    private int findBuddies() {
        List<Termite> var5 = this.level.getEntitiesOfClass(Termite.class, this.getBoundingBox().inflate(3.0, 3.0, 3.0));
        return var5.size();
    }

    public static Entity spawnCreature(World par0World, String par1, double par2, double par4, double par6) {
        Entity var8 = null;
        net.minecraft.entity.EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(new ResourceLocation("chaospersists", par1.toLowerCase().replace(" ", "_")));
        if (entityType != null) {
            var8 = entityType.create(par0World);
        }
        if (var8 != null) {
            var8.moveTo(par2, par4, par6, par0World.random.nextFloat() * 360.0f, 0.0f);
            par0World.addFreshEntity(var8);
        }
        return var8;
    }
}
