package net.david.tbatemod.manasystem.manalevels;

import net.david.tbatemod.TbateMod;
import net.david.tbatemod.manasystem.manabar.ManaStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TbateMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class ManaGains {

    @SubscribeEvent
    public static void onEnemyKilled(LivingDeathEvent event) {
        if (!(event.getEntity().level().isClientSide) && event.getSource().getEntity() instanceof ServerPlayer player) {
            int maxManaGain = getMaxManaForEntity(event.getEntity());



            if (maxManaGain > 0) {
                player.getCapability(ManaStorage.MANA).ifPresent(mana -> {
                    System.out.println("Killed entity: " + event.getEntity().getName().getString() + " MaxMana Gain: " + maxManaGain + " Total MaxMana is now: " + mana.getMaxMana());
                    mana.addMaxMana(maxManaGain);
                });
            }
        }
    }

    private static int getMaxManaForEntity(LivingEntity entity) {
        if (entity instanceof Zombie) return 1;
        if (entity instanceof Skeleton) return 1;
        if (entity instanceof EnderMan) return 5;
        if (entity instanceof Creeper) return 2;
        if (entity instanceof Spider) return 1;
        if (entity instanceof Witch) return 3;
        if (entity instanceof Pillager) return 2;
        if (entity instanceof Slime) return 1;
        if (entity instanceof Vindicator) return 3;
        if (entity instanceof Evoker) return 5;
        if (entity instanceof Ravager) return 10;
        if (entity instanceof Phantom) return 3;
        if (entity instanceof Blaze) return 3;
        if (entity instanceof Ghast) return 3;
        if (entity instanceof WitherSkeleton) return 5;
        if (entity instanceof Piglin) return 2;
        if (entity instanceof PiglinBrute) return 3;
        if (entity instanceof Endermite) return 2;
        if (entity instanceof Silverfish) return 2;
        if (entity instanceof Shulker) return 4;
        if (entity instanceof WitherBoss) return 50;
        if (entity instanceof Warden) return 100;
        if (entity instanceof EnderDragon) return 100;

        return 0;
    }
}
