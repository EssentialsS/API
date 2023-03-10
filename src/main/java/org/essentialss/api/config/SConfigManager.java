package org.essentialss.api.config;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.config.configs.AwayFromKeyboardConfig;
import org.essentialss.api.config.configs.BanConfig;
import org.essentialss.api.config.configs.GeneralConfig;
import org.essentialss.api.config.configs.MessageConfig;
import org.essentialss.api.utils.Singleton;
import org.jetbrains.annotations.NotNull;

public interface SConfigManager {

    @NotNull Singleton<AwayFromKeyboardConfig> awayFromKeyboard();

    default @NotNull Singleton<BanConfig> ban() {
        return EssentialsSAPI.get().banManager().get().banConfig();
    }

    @NotNull Singleton<GeneralConfig> general();

    default @NotNull Singleton<MessageConfig> message() {
        return EssentialsSAPI.get().messageManager().get().config();
    }

}
