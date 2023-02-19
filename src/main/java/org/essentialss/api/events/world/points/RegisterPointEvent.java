package org.essentialss.api.events.world.points;

import org.essentialss.api.world.points.SPoint;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;

public interface RegisterPointEvent extends Event {

    @NotNull SPoint point();

    interface Pre extends RegisterPointEvent, Cancellable {

    }

    interface Post extends RegisterPointEvent {

    }

}
