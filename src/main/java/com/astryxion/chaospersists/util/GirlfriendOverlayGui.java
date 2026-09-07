package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.Crab;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.item.BandP;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GirlfriendOverlayGui {
    private final Minecraft mc;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("chaospersists", "textures/entity/girlfriendgui.png");
    private static final ResourceLocation LEGACY_TEXTURE =
            new ResourceLocation("chaospersists", "textures/girlfriendgui.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;

    public GirlfriendOverlayGui(Minecraft mc) {
        this.mc = mc;
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay() != VanillaGuiOverlay.HOTBAR.type()) {
            return;
        }

        int u = 0;
        int v = 0;
        String outstring = null;
        int color = 16725044;
        var fr = this.mc.font;

        int barWidth = 182;
        int barHeight = 5;
        float gfHealth = 0.0F;
        Entity entity = null;

        if (this.mc.options.hideGui || this.mc.screen != null) {
            return;
        }

        Player player = this.mc.player;
        if (player == null) {
            return;
        }

        for (int i = 1; i <= 6; i++) {
            if (player.level().dimension().equals(ChaosPersists.getDimensionKey(i))) {
                ChaosPersists.current_dimension = ChaosPersists.getDimension(i);
                break;
            }
        }

        if (this.mc.options.graphicsMode().get() != GraphicsStatus.FAST) {
            ChaosPersists.FastGraphicsLeaves = 0;
        } else {
            ChaosPersists.FastGraphicsLeaves = 1;
        }

        if (ChaosPersists.GuiOverlayEnable == 0) {
            return;
        }

        entity = this.mc.crosshairPickEntity;
        if (entity == null) {
            HitResult over = this.mc.hitResult;
            if (over != null && over.getType() == HitResult.Type.ENTITY) {
                entity = ((EntityHitResult) over).getEntity();
            }
        }

        if (entity == null) {
            entity = ChaosPersists.getPointedAtEntity(player.level(), player, 16.0D);
            if (entity == null) {
                return;
            }
            if (!(entity instanceof LivingEntity)) {
                return;
            }
        }

        if (entity instanceof Girlfriend gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.passenger != 0) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "Girlfriend";
            }
            gfHealth = gf.getGirlfriendHealth() / gf.getMaxHealth();
        }

        if (entity instanceof Boyfriend gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.passenger != 0) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "Boyfriend";
            }
            gfHealth = gf.getBoyfriendHealth() / gf.getMaxHealth();
        }

        if (entity instanceof ThePrince gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "The Toddler Prince";
            }
            gfHealth = gf.getHealth() / gf.getMaxHealth();
        }

        if (entity instanceof ThePrincess gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "The Toddler Princess";
            }
            gfHealth = gf.getHealth() / gf.getMaxHealth();
        }

        if (entity instanceof ThePrinceTeen gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "The Young Prince";
            }
            if (gf.getActivity() != 0) {
                return;
            }
            gfHealth = gf.getHealth() / gf.getMaxHealth();
        }

        if (entity instanceof ThePrinceAdult gf) {
            if (!gf.isOwnedBy(player)) {
                return;
            }
            if (gf.hasCustomName()) {
                outstring = gf.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "The Young Adult Prince";
            }
            if (gf.getActivity() != 0) {
                return;
            }
            gfHealth = gf.getHealth() / gf.getMaxHealth();
        }

        if (entity instanceof Dragon df) {
            if (df.hasCustomName()) {
                outstring = df.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "Dragon";
            }
            if (df.getActivity() != 0) {
                return;
            }
            gfHealth = df.getDragonHealth() / df.getMaxHealth();
        }

        if (entity instanceof EmperorScorpion e) {
            outstring = "Emperor Scorpion";
            gfHealth = e.getEmperorScorpionHealth() / e.getMaxHealth();
        }

        if (entity instanceof Basilisk e) {
            outstring = "Basilisk";
            gfHealth = e.getBasiliskHealth() / e.getMaxHealth();
        }

        if (entity instanceof Mothra e) {
            outstring = "Mothra!";
            gfHealth = e.getMothraHealth() / e.getMaxHealth();
        }

        if (entity instanceof Spyro e) {
            if (e.hasCustomName()) {
                outstring = e.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "Baby Dragon";
            }
            gfHealth = e.getSpyroHealth() / e.getMaxHealth();
        }

        if (entity instanceof WormLarge e) {
            if (!e.noPhysics) {
                outstring = "Worm";
                gfHealth = e.getHealth() / e.getMaxHealth();
            }
        }

        if (entity instanceof Alien e) {
            outstring = "Alien!";
            gfHealth = e.getAlienHealth() / e.getMaxHealth();
        }

        if (entity instanceof WaterDragon e) {
            if (e.hasCustomName()) {
                outstring = e.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "WaterDragon";
            }
            gfHealth = e.getWaterDragonHealth() / e.getMaxHealth();
        }

        if (entity instanceof Kraken e) {
            outstring = "Kraken";
            gfHealth = e.getKrakenHealth() / e.getMaxHealth();
        }

        if (entity instanceof Cephadrome e) {
            outstring = "Cephadrome";
            gfHealth = e.getCephadromeHealth() / e.getMaxHealth();
            if (e.getActivity() != 0) {
                return;
            }
        }

        if (entity instanceof TrooperBug e) {
            outstring = "Jumpy Bug";
            gfHealth = e.getTrooperBugHealth() / e.getMaxHealth();
        }

        if (entity instanceof SpitBug e) {
            outstring = "Spit Bug";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof PitchBlack e) {
            outstring = "Nightmare";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Alosaurus e) {
            outstring = "Alosaurus";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Nastysaurus e) {
            outstring = "Nastysaurus";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof TRex e) {
            outstring = "T. Rex";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Kyuubi e) {
            outstring = "Kyuubi";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Robot2 e) {
            outstring = "Robo-Pounder";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Robot4 e) {
            outstring = "Robo-Warrior";
            gfHealth = e.getRobot4Health() / e.getMaxHealth();
        }

        if (entity instanceof Triffid e) {
            outstring = "Triffid";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Godzilla e) {
            outstring = "Mobzilla";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Vortex e) {
            outstring = "Vortex";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Irukandji e) {
            outstring = "Irukandji";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Mantis e) {
            outstring = "Mantis";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof HerculesBeetle e) {
            outstring = "Hercules Beetle";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof TheKing e) {
            outstring = "The King";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof TheQueen e) {
            outstring = "The Queen";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof SeaViper e) {
            outstring = "Sea Viper";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof SeaMonster e) {
            outstring = "Sea Monster";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Molenoid e) {
            outstring = "Molenoid";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof CaterKiller e) {
            outstring = "CaterKiller";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Leon e) {
            if (e.hasCustomName()) {
                outstring = e.getCustomName().getString();
            }
            if (outstring == null || outstring.isEmpty()) {
                outstring = "Leonopteryx";
            }
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Hammerhead e) {
            outstring = "Hammerhead";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof BandP e) {
            if (e.getWhat() == 0) {
                outstring = "Banker";
            } else {
                outstring = "Politician";
            }
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof SpiderRobot e) {
            outstring = "Giant Robot Spider";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof GiantRobot e) {
            outstring = "Jeffery";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof AntRobot e) {
            outstring = "Giant Robot Red Ant";
            gfHealth = e.getHealth() / e.getMaxHealth();
        }

        if (entity instanceof Crab e) {
            float myf = e.getCrabScale();
            if (myf > 0.75F) {
                outstring = "Very Large Crab";
                gfHealth = e.getHealth() / e.getMaxHealth();
            }
        }

        if (outstring == null) {
            return;
        }

        gfHealth = Mth.clamp(gfHealth, 0.0F, 1.0F);

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int width = this.mc.getWindow().getGuiScaledWidth();
        int barWidthFilled = Math.min((int) (gfHealth * (barWidth + 1)), barWidth);
        int x = width / 2 - barWidth / 2;
        int y = 25;

        if (player.isEyeInFluid(FluidTags.WATER) || player.getArmorValue() > 0) {
            y -= 10;
        }

        guiGraphics.drawString(fr, outstring, width / 2 - fr.width(outstring) / 2, y - 10, color, true);

        ResourceLocation texture = resolveGuiTexture();
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(texture, x, y, u, v, barWidth, barHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        if (barWidthFilled > 0) {
            guiGraphics.blit(texture, x, y, u, v + barHeight, barWidthFilled, barHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    private ResourceLocation resolveGuiTexture() {
        if (this.mc.getResourceManager().getResource(TEXTURE).isPresent()) {
            return TEXTURE;
        }
        return LEGACY_TEXTURE;
    }
}
