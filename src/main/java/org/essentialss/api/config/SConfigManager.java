package org.essentialss.api.config;

import org.essentialss.api.config.configs.AwayFromKeyboardConfig;
import org.essentialss.api.config.configs.BanConfig;
import org.essentialss.api.config.configs.GeneralConfig;
import org.essentialss.api.config.configs.MessageConfig;
import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.utils.Singleton;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface SConfigManager {

    @NotNull
    Singleton<AwayFromKeyboardConfig> awayFromKeyboard();

    @NotNull
    default Singleton<BanConfig> ban() {
        return EssentialsSAPI.get().banManager().get().banConfig();
    }

    @NotNull
    Singleton<GeneralConfig> general();

    @NotNull
    default Singleton<MessageConfig> message(@NotNull Locale locale) {
        return EssentialsSAPI.get().messageManager().get().config(locale);
    }

    @NotNull
    default Singleton<MessageConfig> message() {
        return this.message(Locale.ENGLISH);
    }

}
