package org.essentialss.api.message.adapters.vanilla;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.config.value.SingleConfigValue;
import org.essentialss.api.message.adapters.MessageAdapter;
import org.jetbrains.annotations.NotNull;

public interface VanillaMessageAdapter extends MessageAdapter.Enabled {

    default boolean isUsingVanilla() {
        return this.useVanilla().parseDefault(EssentialsSAPI.get().messageManager().get().config().get());
    }

    @NotNull SingleConfigValue.Default<Boolean> useVanilla();


}
