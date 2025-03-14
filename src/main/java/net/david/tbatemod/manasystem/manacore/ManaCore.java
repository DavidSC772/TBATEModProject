package net.david.tbatemod.manasystem.manacore;

import net.david.tbatemod.TbateMod;
import net.david.tbatemod.client.playermodifications.MaxHealthModifier;
import net.david.tbatemod.manasystem.manabar.ManaStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TbateMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ManaCore implements IManaCore {

    @SubscribeEvent
    public static void onRenderGameOverlayManaCore(RenderGuiOverlayEvent.Post event) {

        int corePos = 18;
        int coreSize = 22;

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;


        if (mc.player != null) {
            mc.player.getCapability(ManaStorage.MANA).ifPresent(mana -> {

                    //BLACK CORE
                if (mana.getMaxMana() >= 50 && mana.getMaxMana() < 200) {

                    float newHealth = player.getMaxHealth() + 20.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(BLACK, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //DARK RED CORE
                } else if (mana.getMaxMana() >= 200 && mana.getMaxMana() < 500) {

                    float newHealth = player.getMaxHealth() + 50.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(D_RED, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //SOLID RED CORE
                } else if (mana.getMaxMana() >= 500 && mana.getMaxMana() < 1000) {

                    float newHealth = player.getMaxHealth() + 60.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(S_RED, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //LIGHT RED CORE
                } else if (mana.getMaxMana() >= 1000 && mana.getMaxMana() < 2000) {

                    float newHealth = player.getMaxHealth() + 70.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(L_RED, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //DARK ORANGE CORE
                } else if (mana.getMaxMana() >= 2000 && mana.getMaxMana() < 5000) {

                    float newHealth = player.getMaxHealth() + 100.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(D_ORANGE, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //SOLID ORANGE CORE
                } else if (mana.getMaxMana() >= 5000 && mana.getMaxMana() < 10000) {

                    float newHealth = player.getMaxHealth() + 120.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(S_ORANGE, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    // LIGHT ORANGE CORE
                } else if (mana.getMaxMana() >= 10000 && mana.getMaxMana() < 20000) {

                    float newHealth = player.getMaxHealth() + 150.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(L_ORANGE, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);

                    //DARK YELLOW CORE
                } else if (mana.getMaxMana() >= 20000 && mana.getMaxMana() < 50000) {

                    float newHealth = player.getMaxHealth() + 200.0f;
                    MaxHealthModifier.modifyMaxHealth(player, newHealth);

                    event.getGuiGraphics().blit(D_YELLOW, corePos, corePos, 0, 0, coreSize, coreSize, coreSize, coreSize);
                }
            });
        }

    }

}
