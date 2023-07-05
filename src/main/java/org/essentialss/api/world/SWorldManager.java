package org.essentialss.api.world;

import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.utils.arrays.UnmodifiableCollectors;
import org.essentialss.api.utils.arrays.impl.SingleUnmodifiableCollection;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;

public interface SWorldManager {

    default UnmodifiableCollection<SWorldData> allWorldData() {
        if (Sponge.isServerAvailable()) {
            return Sponge.server().worldManager().worldKeys().stream().map(this::dataFor).collect(UnmodifiableCollectors.asUnordered());
        }
        if (Sponge.isClientAvailable()) {
            //noinspection unchecked
            return Sponge
                    .client()
                    .world()
                    .map(this::dataFor)
                    .map(data -> new SingleUnmodifiableCollection<>(data))
                    .orElseGet(SingleUnmodifiableCollection::new);
        }
        return new SingleUnmodifiableCollection<>();
    }

    @NotNull
    SWorldData dataFor(@NotNull ResourceKey worldKey);

    @NotNull
    SWorldData dataFor(@NotNull World<?, ?> worldData);
}
