package org.essentialss.api.message.adapters;

import net.kyori.adventure.text.Component;
import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.config.configs.MessageConfig;
import org.essentialss.api.config.value.SingleConfigValue;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Collection;

public interface MessageAdapter {

    interface Enabled extends MessageAdapter {
        @NotNull SingleConfigValue.Default<Boolean> enabledValue();

        default boolean isEnabled() {
            return this.enabledValue().parseDefault(EssentialsSAPI.get().messageManager().get().config().get());
        }
    }

    @NotNull SingleConfigValue.Default<Component> configValue();

    default @NotNull Component defaultUnadaptedMessage() {
        return this.configValue().defaultValue();
    }

    default void setUnadaptedMessage(@NotNull Component component) throws ConfigurateException, SerializationException {
        MessageConfig config = EssentialsSAPI.get().messageManager().get().config().get();
        ConfigurationLoader<? extends ConfigurationNode> loader = config.configurationLoader();
        ConfigurationNode root = loader.load();
        this.configValue().set(root, component);
        loader.save(root);
    }

    @NotNull Collection<SPlaceHolder<?>> supportedPlaceholders();

    @NotNull Component unadaptedMessage();
}
