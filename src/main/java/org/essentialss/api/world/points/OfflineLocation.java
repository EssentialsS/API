package org.essentialss.api.world.points;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.utils.identifier.StringIdentifier;
import org.essentialss.api.world.SWorldData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.math.vector.Vector3d;

import java.util.Optional;

public class OfflineLocation implements StringIdentifier {

    private final @NotNull SWorldData data;
    private final @NotNull Vector3d position;

    public OfflineLocation(@NotNull ServerLocation location) {
        this(location.worldKey(), location.position());
    }

    public OfflineLocation(@NotNull Location<?, ?> location) {
        this.position = location.position();
        this.data = EssentialsSAPI.get().worldManager().get().dataFor(location.world());
    }

    public OfflineLocation(@NotNull SWorldData world, @NotNull Vector3d position) {
        this.position = position;
        this.data = world;
    }

    public OfflineLocation(@NotNull ResourceKey key, @NotNull Vector3d position) {
        this.data = EssentialsSAPI.get().worldManager().get().dataFor(key);
        this.position = position;
    }

    public @NotNull Vector3d position() {
        return this.position;
    }

    public @NotNull Optional<Location<?, ?>> location() {
        return this.world().map(world -> world.location(this.position));
    }

    public @NotNull Optional<World<?, ?>> world() {
        return this.data.spongeWorld();
    }

    public @NotNull SWorldData worldData() {
        return this.data;
    }

    @Override
    public @NotNull String identifier() {
        return this.data.identifier();
    }
}
