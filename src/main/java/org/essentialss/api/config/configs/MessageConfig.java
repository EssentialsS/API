package org.essentialss.api.config.configs;

import org.essentialss.api.config.SConfig;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public interface MessageConfig extends SConfig {

    @NotNull Locale locale();


}
