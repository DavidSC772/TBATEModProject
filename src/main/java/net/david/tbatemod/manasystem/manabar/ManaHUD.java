package net.david.tbatemod.manasystem.manabar;

import com.mojang.blaze3d.systems.RenderSystem;
import net.david.tbatemod.TbateMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TbateMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaHUD {
    // Define the texture location
    private static final ResourceLocation STATUS_BARS = new ResourceLocation(TbateMod.MOD_ID, "textures/bars/bars.png");
    private static final ResourceLocation MANA_BAR = new ResourceLocation(TbateMod.MOD_ID, "textures/bars/mana_bar.png");
    private static final ResourceLocation HEALTH_BAR = new ResourceLocation(TbateMod.MOD_ID, "textures/bars/health_bar.png");
    private static final ResourceLocation FOOD_BAR = new ResourceLocation(TbateMod.MOD_ID, "textures/bars/food_bar.png");
    private static final ResourceLocation ICON_HEALTH = new ResourceLocation(TbateMod.MOD_ID, "textures/icons/hearticon.png");
    private static final ResourceLocation ICON_FOOD = new ResourceLocation(TbateMod.MOD_ID, "textures/icons/foodicon.png");
    private static final ResourceLocation ICON_MANA = new ResourceLocation(TbateMod.MOD_ID, "textures/icons/manaicon.png");

    @SubscribeEvent
    public static void removeBars (RenderGuiOverlayEvent.Pre event) {
        if (VanillaGuiOverlay.PLAYER_HEALTH.type() == event.getOverlay()) {
            event.setCanceled(true);
        }

        if (VanillaGuiOverlay.FOOD_LEVEL.type() == event.getOverlay()) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlayBars(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (mc.player != null) {
            mc.player.getCapability(ManaStorage.MANA).ifPresent(mana -> {

                // Status bars dimensions
                int xBars = 180;
                int yBars = 120;

                // Bars position
                int xBarsPos = 5;
                int yBarsPos = 5;

                event.getGuiGraphics().blit(STATUS_BARS, xBarsPos, yBarsPos, 0, 0, xBars, yBars, xBars, yBars);

                // Health Bar position
                int xHealthPos = 59;
                int yHealthPos = 10;

                int maxHealth = (int) player.getMaxHealth();
                int currentHealth = (int) player.getHealth();
                int healthWidth = Math.max(1, (int) ((currentHealth / (float) maxHealth) * 80));

                event.getGuiGraphics().blit(HEALTH_BAR, xHealthPos, yHealthPos, 0, 0, healthWidth, yBars, xBars, yBars);

                // Food Bar position
                int xFoodPos = 59;
                int yFoodPos = 25;

                float maxFood = 20.0f;
                float currentFood = player.getFoodData().getFoodLevel();

                int foodWidth = Math.max(1, (int) ((currentFood / maxFood) * 80));

                event.getGuiGraphics().blit(FOOD_BAR, xFoodPos, yFoodPos, 0, 0, foodWidth, yBars, xBars, yBars);

                // Mana Bar position
                int xManaPos = 59;
                int yManaPos = 40;

                int maxMana = mana.getMaxMana();
                int currentMana = mana.getMana();
                int manaWidth = Math.max(1, (int) ((currentMana / (float) maxMana) * 80));

                event.getGuiGraphics().blit(MANA_BAR, xManaPos, yManaPos, 0, 0, manaWidth, yBars, xBars, yBars);

                int iconSize = 48;

                int xIconHealth = 130;
                int yIconHealth = 8;

                int xIconFood = 130;
                int yIconFood = 23;

                int xIconMana = 130;
                int yIconMana = 38;

                event.getGuiGraphics().blit(ICON_HEALTH, xIconHealth, yIconHealth, 0, 0, iconSize, iconSize, iconSize, iconSize);
                event.getGuiGraphics().blit(ICON_FOOD, xIconFood, yIconFood, 0, 0, iconSize, iconSize, iconSize, iconSize);
                event.getGuiGraphics().blit(ICON_MANA, xIconMana, yIconMana, 0, 0, iconSize, iconSize, iconSize, iconSize);

            });
        }
    }
}
