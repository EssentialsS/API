package org.essentialss.api;

import org.spongepowered.configurate.ConfigurateException;

public interface Serializable {

    void reloadFromConfig() throws ConfigurateException;

    void saveToConfig() throws ConfigurateException;


}
