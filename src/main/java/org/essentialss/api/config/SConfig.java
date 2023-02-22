package org.essentialss.api.config;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;

public interface SConfig {

    default @NotNull ConfigurationLoader<? extends ConfigurationNode> configurationLoader() {
        File file = this.file();
        String name = file.getName();
        if (name.endsWith(".conf")) {
            return HoconConfigurationLoader.builder().file(file).build();
        }
        if (name.endsWith(".yml")) {
            return YamlConfigurationLoader.builder().file(file).build();
        }
        throw new RuntimeException("Unsupported filetype of " + name.substring(name.lastIndexOf(".")));
    }

    @NotNull File file();

    default void generateDefault() throws SerializationException {
        this.file().delete();
        this.update();
    }

    void update() throws SerializationException;


}
