package net.david.tbatemod.manasystem.manabar;

import net.david.tbatemod.TbateMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TbateMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaStorage {
    public static final Capability<IMana> MANA = CapabilityManager.get(new CapabilityToken<>() {});

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(IMana.class);
    }
}
