package org.essentialss.api.config;

import org.essentialss.api.utils.Singleton;
import org.jetbrains.annotations.NotNull;

public interface SConfigManager {

    @NotNull Singleton<GeneralConfig> general();

}
