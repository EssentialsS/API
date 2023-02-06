package org.essentialss.api.events.player.hat;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.item.inventory.ItemStack;

public interface ChangeHatEvent extends Event, Cancellable {

    @NotNull Player spongePlayer();

    @NotNull Transaction<ItemStack> item();

}
