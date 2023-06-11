package org.essentialss.api.kit;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.configurate.ConfigurateException;

import java.io.File;
import java.util.Optional;

public interface KitManager {

    default Optional<Kit> kit(@NotNull ResourceKey key) {
        return this.kits().stream().filter(kit -> kit.getKey().equals(key)).findAny();
    }

    default File kitFolder() {
        File configFolder = Sponge.configManager().pluginConfig(EssentialsSAPI.get().container()).directory().toFile();
        return new File(configFolder, "kits");
    }

    UnmodifiableCollection<Kit> kits();

    Kit register(@NotNull KitBuilder builder);

    void reloadKits();

    void save(@NotNull Kit kit) throws ConfigurateException;

    void unregister(@NotNull Kit kit);


}
