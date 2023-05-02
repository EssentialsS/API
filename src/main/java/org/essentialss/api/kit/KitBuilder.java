package org.essentialss.api.kit;

import org.essentialss.api.EssentialsSAPI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.plugin.PluginContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KitBuilder {

    private final Map<ItemStackSnapshot, Integer> kitSlots = new HashMap<>();
    private String displayName;
    private String idName;
    private PluginContainer plugin;

    public void addKitSlot(@NotNull ItemStackSnapshot item, @Nullable Integer slot) {
        this.kitSlots.put(item, slot);
    }

    public void addKitSlot(@NotNull ItemStackSnapshot item) {
        this.addKitSlot(item, null);
    }

    public void addKitSlots(@SuppressWarnings("TypeMayBeWeakened") @NotNull PlayerInventory inventory) {
        inventory
                .slots()
                .stream()
                .filter(slot -> !slot.peek().equals(ItemStack.empty()))
                .forEach(slot -> this.addKitSlot(slot.peek().createSnapshot(), slot.get(Keys.SLOT_INDEX).orElse(null)));
    }

    public void addKitSlots(@NotNull Inventory inventory) {
        this.addKitSlots(inventory
                                 .slots()
                                 .stream()
                                 .map(Inventory::peek)
                                 .filter(item -> !item.equals(ItemStack.empty()))
                                 .map(ItemStack::createSnapshot)
                                 .collect(Collectors.toList()));
    }

    public void addKitSlots(@NotNull Iterable<ItemStackSnapshot> items) {
        for (ItemStackSnapshot item : items) {
            this.addKitSlot(item);
        }
    }

    public String displayName() {
        return this.displayName;
    }

    public String idName() {
        return this.idName;
    }

    public Map<ItemStackSnapshot, Integer> kitSlots() {
        return this.kitSlots;
    }

    public PluginContainer plugin() {
        return this.plugin;
    }

    public Kit register() {
        return EssentialsSAPI.get().kitManager().get().register(this);
    }

    public KitBuilder setDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
        return this;
    }

    public KitBuilder setIdName(@NotNull String idName) {
        if (!idName.toLowerCase().equals(idName)) {
            throw new IllegalArgumentException("idName cannot have uppercase letters");
        }
        this.idName = idName;
        return this;
    }

    public KitBuilder setPlugin(@NotNull PluginContainer plugin) {
        this.plugin = plugin;
        return this;
    }
}
