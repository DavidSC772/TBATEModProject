package net.david.tbatemod.manasystem.manabar;

import net.david.tbatemod.network.ModMessages;
import net.david.tbatemod.network.ManaSyncPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class Mana implements IMana, INBTSerializable<CompoundTag> {
    private int mana;
    private int maxMana;
    private ServerPlayer player; // Referencia al jugador

    public static final int MAX_MANA_CAP = 1000000;

    public Mana(int maxMana) {
        this.maxMana = maxMana;
        this.mana = maxMana;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = Math.min(mana, maxMana);
        syncMana();
    }

    @Override
    public void addMana(int mana) {
        this.mana = Math.min(this.mana + mana, maxMana);
        syncMana();
    }

    @Override
    public void consumeMana(int mana) {
        this.mana = Math.max(this.mana - mana, 0);
        syncMana();
    }

    @Override
    public void addMaxMana(int amountToAdd){
        this.maxMana = Math.min(this.maxMana + amountToAdd, MAX_MANA_CAP);
        syncMana();
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
        syncMana();
    }

    @Override
    public int getMaxMana() {
        return maxMana;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("mana", mana);
        tag.putInt("maxMana", maxMana);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana = nbt.getInt("mana");
    }
    // Method to synchronize mana
    private void syncMana() {
        if (player != null && !player.level().isClientSide) {
            ModMessages.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new ManaSyncPacket(this.mana, this.maxMana));
        }
    }
}
