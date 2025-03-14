package net.david.tbatemod.manasystem.manabar;

import net.david.tbatemod.TbateMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TbateMod.MOD_ID)
public class ManaEvents {

    private static final ResourceLocation MANA_CAPABILITY = new ResourceLocation(TbateMod.MOD_ID, "mana");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(MANA_CAPABILITY, new ManaProvider(new Mana(100))); // 100 is max mana
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ManaStorage.MANA).ifPresent(oldMana -> {
                event.getEntity().getCapability(ManaStorage.MANA).ifPresent(newMana -> {
                    newMana.setMana(oldMana.getMana());
                });
            });
        }
    }
}
