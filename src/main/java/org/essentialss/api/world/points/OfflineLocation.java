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

    @NotNull
    private final SWorldData data;
    @NotNull
    private final Vector3d position;

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

    @Override
    public int hashCode() {
        return Integer.parseInt(this.identifier().hashCode() + String.valueOf(this.position.hashCode()));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OfflineLocation)) {
            return false;
        }
        OfflineLocation compare = (OfflineLocation) obj;
        if (!this.identifier().equals(compare.identifier())) {
            return false;
        }
        return this.position.equals(compare.position);
    }

    @Override
    @NotNull
    public String identifier() {
        return this.data.identifier();
    }

    @NotNull
    public Optional<Location<?, ?>> location() {
        return this.world().map(world -> world.location(this.position));
    }

    @NotNull
    public Vector3d position() {
        return this.position;
    }

    @NotNull
    public Optional<World<?, ?>> world() {
        return this.data.spongeWorld();
    }

    @NotNull
    public SWorldData worldData() {
        return this.data;
    }
}
