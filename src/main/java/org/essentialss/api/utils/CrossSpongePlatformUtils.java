package org.essentialss.api.utils;

import org.jetbrains.annotations.Async;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Engine;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Scheduler;

public class CrossSpongePlatformUtils {

    public static @NotNull Engine spongeEngine() {
        if (Sponge.isServerAvailable()) {
            return Sponge.server();
        }
        return Sponge.client();
    }
}
