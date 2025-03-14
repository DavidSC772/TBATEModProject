package net.david.tbatemod.manasystem.manabar;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final IMana mana;
    private final LazyOptional<IMana> optional;

    public ManaProvider(IMana mana) {
        this.mana = mana;
        this.optional = LazyOptional.of(() -> this.mana);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return ManaStorage.MANA.orEmpty(cap, optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return mana.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        mana.deserializeNBT(nbt);
    }
}
