package org.essentialss.api.events.player.afk;

import net.kyori.adventure.text.Component;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

import java.util.Optional;

public interface PlayerKickedForIdlingEvent extends Event, Cancellable {

    Optional<Component> kickAdaptedMessage();

    @Deprecated
    @NotNull
    default Component kickMessage() {
        return this.kickAdaptedMessage().orElse(Component.empty());
    }

    Optional<Component> originalKickAdaptedMessage();

    @Deprecated
    @NotNull
    default Component originalKickMessage() {
        return this.originalKickAdaptedMessage().orElse(Component.empty());
    }

    @NotNull
    SGeneralPlayerData player();

    void setKickMessage(@Nullable Component kickMessage);

}
