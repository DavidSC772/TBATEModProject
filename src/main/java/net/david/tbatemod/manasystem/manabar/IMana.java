package net.david.tbatemod.manasystem.manabar;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IMana extends INBTSerializable<CompoundTag> {
    int getMana();

    void setMana(int mana);

    void addMana(int mana);

    void consumeMana(int mana);

    int getMaxMana();

    void setMaxMana(int maxMana);

    void addMaxMana(int amountToAdd);

}
