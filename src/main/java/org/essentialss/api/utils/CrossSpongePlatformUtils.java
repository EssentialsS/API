package org.essentialss.api.utils;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Engine;
import org.spongepowered.api.Sponge;

public final class CrossSpongePlatformUtils {

    private CrossSpongePlatformUtils() {
        throw new RuntimeException("Should not create");
    }

    public static @NotNull Engine spongeEngine() {
        if (Sponge.isServerAvailable()) {
            return Sponge.server();
        }
        return Sponge.client();
    }
}
