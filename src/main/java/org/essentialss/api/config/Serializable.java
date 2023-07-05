package org.essentialss.api.config;

import org.spongepowered.configurate.ConfigurateException;

public interface Serializable {

    void reloadFromConfig() throws ConfigurateException;

    void saveToConfig() throws ConfigurateException;


}
