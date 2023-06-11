package org.essentialss.api.kit;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.group.Group;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.type.ViewableInventory;
import org.spongepowered.plugin.PluginContainer;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

public interface Kit {

    default void apply(PlayerInventory inventory) {
        UnmodifiableCollection<KitSlot> kitSlots = this.inventory().get();
        kitSlots.forEach(kitSlot -> kitSlot.apply(inventory));
    }

    Optional<Duration> cooldown(Group group);

    ViewableInventory createInventory();

    String displayName();

    default File file() {
        return new File(EssentialsSAPI.get().kitManager().get().kitFolder(), this.plugin().metadata().id() + "/" + this.name() + ".conf");
    }

    default ResourceKey getKey() {
        return ResourceKey.of(this.plugin(), this.name());
    }

    Singleton<UnmodifiableCollection<KitSlot>> inventory();

    String name();

    PluginContainer plugin();

    void setCooldown(Group group, Duration duration);

}
