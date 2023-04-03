package org.essentialss.api.config;

import org.essentialss.api.config.value.ConfigValue;
import org.essentialss.api.utils.Singleton;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.Collection;
import java.util.function.Supplier;

public interface SConfig {

    @NotNull Collection<ConfigValue<?>> expectedNodes();

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

    default void generateDefault() throws ConfigurateException {
        this.file().delete();
        this.update();
    }

    void update() throws ConfigurateException;

    static <C extends SConfig> Singleton<C> singletonLoad(Supplier<C> supplier) {
        return new Singleton<>(() -> {
            C config = supplier.get();
            try {
                config.update();
            } catch (ConfigurateException e) {
                e.printStackTrace();
            }
            return config;
        });
    }


}
