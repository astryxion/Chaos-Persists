package com.astryxion.chaospersists.util;

import com.astryxion.chaospersists.entity.Mothra;
import com.astryxion.chaospersists.entity.Spyro;
import com.astryxion.chaospersists.entity.WormLarge;
import com.astryxion.chaospersists.entity.Alien;
import com.astryxion.chaospersists.entity.WaterDragon;
import com.astryxion.chaospersists.entity.Kraken;
import com.astryxion.chaospersists.entity.Cephadrome;
import com.astryxion.chaospersists.entity.TrooperBug;
import com.astryxion.chaospersists.entity.SpitBug;
import com.astryxion.chaospersists.entity.PitchBlack;
import com.astryxion.chaospersists.core.ChaosPersists;
import com.astryxion.chaospersists.entity.Girlfriend;
import com.astryxion.chaospersists.entity.Boyfriend;
import com.astryxion.chaospersists.entity.ThePrince;
import com.astryxion.chaospersists.entity.ThePrincess;
import com.astryxion.chaospersists.entity.ThePrinceTeen;
import com.astryxion.chaospersists.entity.ThePrinceAdult;
import com.astryxion.chaospersists.entity.Dragon;
import com.astryxion.chaospersists.entity.Alosaurus;
import com.astryxion.chaospersists.entity.Mantis;
import com.astryxion.chaospersists.entity.HerculesBeetle;
import com.astryxion.chaospersists.entity.TheKing;
import com.astryxion.chaospersists.entity.TheQueen;
import com.astryxion.chaospersists.entity.SeaViper;
import com.astryxion.chaospersists.entity.SeaMonster;
import com.astryxion.chaospersists.entity.Molenoid;
import com.astryxion.chaospersists.entity.CaterKiller;
import com.astryxion.chaospersists.entity.Leon;
import com.astryxion.chaospersists.entity.Hammerhead;
import com.astryxion.chaospersists.item.BandP;
import com.astryxion.chaospersists.entity.EmperorScorpion;
import com.astryxion.chaospersists.entity.Basilisk;
import com.astryxion.chaospersists.entity.Nastysaurus;
import com.astryxion.chaospersists.entity.TRex;
import com.astryxion.chaospersists.entity.Kyuubi;
import com.astryxion.chaospersists.entity.Robot2;
import com.astryxion.chaospersists.entity.Robot4;
import com.astryxion.chaospersists.entity.Triffid;
import com.astryxion.chaospersists.entity.Godzilla;
import com.astryxion.chaospersists.entity.Vortex;
import com.astryxion.chaospersists.entity.Irukandji;
import com.astryxion.chaospersists.entity.SpiderRobot;
import com.astryxion.chaospersists.entity.GiantRobot;
import com.astryxion.chaospersists.entity.AntRobot;
import com.astryxion.chaospersists.entity.Crab;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.tags.FluidTags;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.settings.GraphicsFanciness;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;
import java.io.IOException;

public class GirlfriendOverlayGui extends AbstractGui {
  private final Minecraft mc;
  private static final ResourceLocation texture = new ResourceLocation("chaospersists", "textures/entity/girlfriendgui.png");
  private static final ResourceLocation legacyTexture = new ResourceLocation("chaospersists", "textures/girlfriendgui.png");

  public GirlfriendOverlayGui(Minecraft mc)
  {
    this.mc = mc;
  }

  @SubscribeEvent
  public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
    if (event.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) {
      return;
    }

    int u = 0;
    int v = 0;
    String outstring = null;
    int color = 16725044;
    FontRenderer fr = this.mc.font;

    int barWidth = 182;

    int barHeight = 5;

    float gfHealth = 0.0F;

    Entity entity = null;
    PlayerEntity player = null;

    if ((this.mc.options.hideGui) || (this.mc.screen != null)) {
      return;
    }

    player = this.mc.player;

    if (player == null)
    {
      return;
    }

    ChaosPersists.current_dimension = resolveDimensionId(player.level);
    if (this.mc.options.graphicsMode != GraphicsFanciness.FAST)
      ChaosPersists.FastGraphicsLeaves = 0;
    else {
      ChaosPersists.FastGraphicsLeaves = 1;
    }

    if (ChaosPersists.GuiOverlayEnable == 0) {
      return;
    }

    entity = this.mc.crosshairPickEntity;
    if (entity == null) {
      RayTraceResult over = this.mc.hitResult;
      if (over != null && over.getType() == RayTraceResult.Type.ENTITY) {
        entity = ((EntityRayTraceResult) over).getEntity();
      }
    }

    if (entity == null) {
      entity = ChaosPersists.getPointedAtEntity(this.mc.level, player, 16.0D);
      if (entity == null) return;
      if (!(entity instanceof LivingEntity)) return;
    }

    if ((entity instanceof Girlfriend)) {
      Girlfriend gf = null;
      gf = (Girlfriend)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (!gf.getPassengers().isEmpty()) return;

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "Girlfriend";
      }

      gfHealth = gf.getGirlfriendHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof Boyfriend)) {
      Boyfriend gf = null;
      gf = (Boyfriend)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (!gf.getPassengers().isEmpty()) return;

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "Boyfriend";
      }

      gfHealth = gf.getBoyfriendHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof ThePrince)) {
      ThePrince gf = null;
      gf = (ThePrince)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "The Toddler Prince";
      }

      gfHealth = gf.getHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof ThePrincess)) {
      ThePrincess gf = null;
      gf = (ThePrincess)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "The Toddler Princess";
      }

      gfHealth = gf.getHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof ThePrinceTeen)) {
      ThePrinceTeen gf = null;
      gf = (ThePrinceTeen)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "The Young Prince";
      }
      if (gf.getActivity() != 0) return;

      gfHealth = gf.getHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof ThePrinceAdult)) {
      ThePrinceAdult gf = null;
      gf = (ThePrinceAdult)entity;

      if (!gf.isOwnedBy(player))
      {
        return;
      }

      if (gf.hasCustomName()) outstring = gf.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "The Young Adult Prince";
      }
      if (gf.getActivity() != 0) return;

      gfHealth = gf.getHealth() / gf.getMaxHealth();
    }

    if ((entity instanceof Dragon)) {
      Dragon df = null;
      df = (Dragon)entity;

      if (df.hasCustomName()) outstring = df.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "Dragon";
      }
      if (df.getActivity() != 0) return;

      gfHealth = df.getDragonHealth() / df.getMaxHealth();
    }

    if ((entity instanceof EmperorScorpion)) {
      EmperorScorpion e = (EmperorScorpion)entity;
      outstring = "Emperor Scorpion";
      gfHealth = e.getEmperorScorpionHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Basilisk)) {
      Basilisk e = (Basilisk)entity;
      outstring = "Basilisk";
      gfHealth = e.getBasiliskHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Mothra)) {
      Mothra e = (Mothra)entity;
      outstring = "Mothra!";
      gfHealth = e.getMothraHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Spyro)) {
      Spyro e = (Spyro)entity;
      if (e.hasCustomName()) outstring = e.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "Baby Dragon";
      }

      gfHealth = e.getSpyroHealth() / e.getMaxHealth();
    }

    if ((entity instanceof WormLarge)) {
      WormLarge e = (WormLarge)entity;
      if (!e.noPhysics) {
        outstring = "Worm";
        gfHealth = e.getHealth() / e.getMaxHealth();
      }
    }

    if ((entity instanceof Alien)) {
      Alien e = (Alien)entity;
      outstring = "Alien!";
      gfHealth = e.getAlienHealth() / e.getMaxHealth();
    }

    if ((entity instanceof WaterDragon)) {
      WaterDragon e = (WaterDragon)entity;
      if (e.hasCustomName()) outstring = e.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "WaterDragon";
      }
      gfHealth = e.getWaterDragonHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Kraken)) {
      Kraken e = (Kraken)entity;
      outstring = "Kraken";
      gfHealth = e.getKrakenHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Cephadrome)) {
      Cephadrome e = (Cephadrome)entity;
      outstring = "Cephadrome";
      gfHealth = e.getCephadromeHealth() / e.getMaxHealth();
      if (e.getActivity() != 0) return;
    }

    if ((entity instanceof TrooperBug)) {
      TrooperBug e = (TrooperBug)entity;
      outstring = "Jumpy Bug";
      gfHealth = e.getTrooperBugHealth() / e.getMaxHealth();
    }

    if ((entity instanceof SpitBug)) {
      SpitBug e = (SpitBug)entity;
      outstring = "Spit Bug";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof PitchBlack)) {
      PitchBlack e = (PitchBlack)entity;
      outstring = "Nightmare";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Alosaurus)) {
      Alosaurus e = (Alosaurus)entity;
      outstring = "Alosaurus";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Nastysaurus)) {
      Nastysaurus e = (Nastysaurus)entity;
      outstring = "Nastysaurus";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof TRex)) {
      TRex e = (TRex)entity;
      outstring = "T. Rex";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Kyuubi)) {
      Kyuubi e = (Kyuubi)entity;
      outstring = "Kyuubi";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Robot2)) {
      Robot2 e = (Robot2)entity;
      outstring = "Robo-Pounder";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Robot4)) {
      Robot4 e = (Robot4)entity;
      outstring = "Robo-Warrior";
      gfHealth = e.getRobot4Health() / e.getMaxHealth();
    }

    if ((entity instanceof Triffid)) {
      Triffid e = (Triffid)entity;
      outstring = "Triffid";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Godzilla)) {
      Godzilla e = (Godzilla)entity;
      outstring = "Mobzilla";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Vortex)) {
      Vortex e = (Vortex)entity;
      outstring = "Vortex";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Irukandji)) {
      Irukandji e = (Irukandji)entity;
      outstring = "Irukandji";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Mantis)) {
      Mantis e = (Mantis)entity;
      outstring = "Mantis";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof HerculesBeetle)) {
      HerculesBeetle e = (HerculesBeetle)entity;
      outstring = "Hercules Beetle";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof TheKing)) {
      TheKing e = (TheKing)entity;
      outstring = "The King";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof TheQueen)) {
      TheQueen e = (TheQueen)entity;
      outstring = "The Queen";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof SeaViper)) {
      SeaViper e = (SeaViper)entity;
      outstring = "Sea Viper";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof SeaMonster)) {
      SeaMonster e = (SeaMonster)entity;
      outstring = "Sea Monster";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Molenoid)) {
      Molenoid e = (Molenoid)entity;
      outstring = "Molenoid";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof CaterKiller)) {
      CaterKiller e = (CaterKiller)entity;
      outstring = "CaterKiller";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Leon)) {
      Leon e = (Leon)entity;
      if (e.hasCustomName()) outstring = e.getCustomName().getString();
      if ((outstring == null) || (outstring.equals(""))) {
        outstring = "Leonopteryx";
      }
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Hammerhead)) {
      Hammerhead e = (Hammerhead)entity;
      outstring = "Hammerhead";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof BandP)) {
      BandP e = (BandP)entity;
      if (e.getWhat() == 0)
        outstring = "Banker";
      else {
        outstring = "Politician";
      }
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof SpiderRobot)) {
      SpiderRobot e = (SpiderRobot)entity;
      outstring = "Giant Robot Spider";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof GiantRobot)) {
      GiantRobot e = (GiantRobot)entity;
      outstring = "Jeffery";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof AntRobot)) {
      AntRobot e = (AntRobot)entity;
      outstring = "Giant Robot Red Ant";
      gfHealth = e.getHealth() / e.getMaxHealth();
    }

    if ((entity instanceof Crab)) {
      Crab e = (Crab)entity;
      float myf = e.getCrabScale();
      if (myf > 0.75F) {
        outstring = "Very Large Crab";
        gfHealth = e.getHealth() / e.getMaxHealth();
      }

    }

    if (outstring == null) {
      return;
    }

    int width = this.mc.getWindow().getGuiScaledWidth();
    int barWidthFilled = (int) (gfHealth * (barWidth + 1));

    int x = width / 2 - barWidth / 2;

    int y = 25;

    if (player.isEyeInFluid(FluidTags.WATER) || player.getArmorValue() > 0) {
      y -= 10;
    }

    MatrixStack matrixStack = event.getMatrixStack();
    fr.drawShadow(matrixStack, outstring, width / 2 - fr.width(outstring) / 2, y - 10, color);

    this.mc.getTextureManager().bind(this.resolveGuiTexture());

    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
    this.blit(matrixStack, x, y, u, v, barWidth, barHeight);

    if (barWidthFilled > 0) {
      this.blit(matrixStack, x, y, u, v + barHeight, barWidthFilled, barHeight);
    }
  }

  private static int resolveDimensionId(net.minecraft.world.World level) {
    for (Integer id : ChaosPersists.getRegisteredChaosDimensionIds()) {
      if (ChaosPersists.getServerWorldByDimensionId(id) == level) {
        return id;
      }
    }
    return 0;
  }

  private ResourceLocation resolveGuiTexture() {
    try {
      this.mc.getResourceManager().getResource(texture);
      return texture;
    } catch (IOException ex) {
      return legacyTexture;
    }
  }
}