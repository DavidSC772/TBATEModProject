package net.david.tbatemod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.david.tbatemod.manasystem.manabar.ManaStorage;
import net.david.tbatemod.network.ModMessages;
import net.david.tbatemod.network.ManaSyncPacket;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

@Mod.EventBusSubscriber(modid = "tbatemod", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ManaCommand {
    public static void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("givemana")
                .then(argument("amount", IntegerArgumentType.integer())
                        .executes(context -> giveMana(context.getSource(), IntegerArgumentType.getInteger(context, "amount")))));

        dispatcher.register(literal("removemana")
                .then(argument("amount", IntegerArgumentType.integer())
                        .executes(context -> removeMana(context.getSource(), IntegerArgumentType.getInteger(context, "amount")))));
    }

    private static int giveMana(CommandSourceStack source, int amount) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();

        player.getCapability(ManaStorage.MANA).ifPresent(mana -> {
            mana.addMana(amount);
            ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ManaSyncPacket(mana.getMana(), mana.getMaxMana()));
            player.sendSystemMessage(Component.literal("Has recibido " + amount + " mana. Mana actual: " + mana.getMana()));
        });

        return 1;
    }

    private static int removeMana(CommandSourceStack source, int amount) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();

        player.getCapability(ManaStorage.MANA).ifPresent(mana -> {
            mana.consumeMana(amount);
            ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ManaSyncPacket(mana.getMana(), mana.getMaxMana()));
            player.sendSystemMessage(Component.literal("Has perdido " + amount + " mana. Mana actual: " + mana.getMana()));
        });

        return 1;
    }
}
