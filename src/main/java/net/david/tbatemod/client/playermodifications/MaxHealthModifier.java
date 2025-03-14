package net.david.tbatemod.client.playermodifications;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import java.util.UUID;

public class MaxHealthModifier {

    // Unique UUID for the modifier (ensure it's consistent across restarts)
    private static final UUID MAX_HEALTH_MODIFIER_UUID = UUID.fromString("cb38493f-4fb7-4cc0-b6cf-87d5cf5c2051");

    public static void modifyMaxHealth(Player player, float newHealth) {
        // Check if the MAX_HEALTH attribute is available
        if (player.getAttribute(Attributes.MAX_HEALTH) != null) {
            // Create a unique modifier for max health change
            AttributeModifier modifier = new AttributeModifier(MAX_HEALTH_MODIFIER_UUID, "Max Health Modifier", newHealth - player.getMaxHealth(), AttributeModifier.Operation.ADDITION);

            // Remove any previous modifiers with the same UUID (to prevent stacking)
            player.getAttribute(Attributes.MAX_HEALTH).removeModifier(MAX_HEALTH_MODIFIER_UUID);

            // Apply the new modifier to increase max health
            player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier);

            // Set the player's current health to the new max health value
            player.setHealth(newHealth);
        }
    }
}

