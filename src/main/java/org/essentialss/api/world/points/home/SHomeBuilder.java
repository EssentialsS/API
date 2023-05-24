package org.essentialss.api.world.points.home;

import org.essentialss.api.world.points.OfflineLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("allow-nullable")
public class SHomeBuilder {

    private String home;
    private OfflineLocation location;

    @Nullable
    public String home() {
        return this.home;
    }

    @Nullable
    public OfflineLocation point() {
        return this.location;
    }

    @NotNull
    public SHomeBuilder setHome(@NotNull String home) {
        this.home = home;
        return this;
    }

    @NotNull
    public SHomeBuilder setPoint(@NotNull OfflineLocation point) {
        this.location = point;
        return this;
    }
}
