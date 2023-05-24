package org.essentialss.api.kit;

import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.entity.PlayerInventory;
import org.spongepowered.api.item.inventory.transaction.InventoryTransactionResult;

import java.util.OptionalInt;

public interface KitSlot {

    default InventoryTransactionResult apply(PlayerInventory inventory) {
        OptionalInt opSlot = this.preferredSlotIndex();
        Slot slot = null;
        if (opSlot.isPresent()) {
            Slot indexSlot = inventory
                    .slot(opSlot.getAsInt())
                    .orElseThrow(() -> new IllegalStateException("SlotIndex of " + opSlot.getAsInt() + " cannot be found in player inventory"));
            if (indexSlot.peek().equals(ItemStack.empty())) {
                slot = indexSlot;
            }
        }

        ItemStackSnapshot item = this.item();

        if (null == slot) {
            return inventory.offer(item.createStack());
        }
        return slot.set(item.createStack());
    }

    ItemStackSnapshot item();

    OptionalInt preferredSlotIndex();

}
