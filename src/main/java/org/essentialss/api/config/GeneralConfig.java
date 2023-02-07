package org.essentialss.api.config;

import org.essentialss.api.config.value.SingleConfigValue;

public interface GeneralConfig extends SConfig {

    SingleConfigValue.Default<Integer> pageSize();

}
