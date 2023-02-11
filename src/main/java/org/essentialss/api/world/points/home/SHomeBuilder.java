package org.essentialss.api.world.points.home;

import org.essentialss.api.world.points.OfflineLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("allow-nullable")
public class SHomeBuilder {

    private OfflineLocation location;
    private String home;

    public @Nullable OfflineLocation point() {
        return this.location;
    }

    public @NotNull SHomeBuilder setPoint(@NotNull OfflineLocation point) {
        this.location = point;
        return this;
    }

    public @Nullable String home() {
        return this.home;
    }

    public @NotNull SHomeBuilder setHome(@NotNull String home) {
        this.home = home;
        return this;
    }
}
