package org.essentialss.api.kit;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;
import org.spongepowered.api.item.inventory.type.ViewableInventory;
import org.spongepowered.plugin.PluginContainer;

import java.io.File;
import java.util.stream.Stream;

public interface Kit {

    default Stream<InventoryTransactionResult> apply(PlayerInventory inventory) {
        return inventory().get().stream().map(kitSlot -> kitSlot.apply(inventory));
    }

    ViewableInventory createInventory();

    String displayName();

    default File file() {
        return new File(EssentialsSAPI.get().kitManager().get().kitFolder(), plugin().metadata().id() + "/" + name() + ".conf");
    }

    default ResourceKey getKey() {
        return ResourceKey.of(this.plugin(), this.name());
    }

    Singleton<UnmodifiableCollection<KitSlot>> inventory();

    String name();

    PluginContainer plugin();

}
