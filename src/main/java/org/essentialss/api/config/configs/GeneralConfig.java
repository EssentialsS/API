package org.essentialss.api.config.configs;

import org.essentialss.api.config.SConfig;
import org.essentialss.api.config.value.SingleConfigValue;

public interface GeneralConfig extends SConfig {

    SingleConfigValue.Default<Boolean> checkForUpdatesOnStartup();

    SingleConfigValue.Default<Integer> pageSize();

}
