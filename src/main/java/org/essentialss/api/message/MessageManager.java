package org.essentialss.api.message;

import org.essentialss.api.config.configs.MessageConfig;
import org.essentialss.api.message.placeholder.SPlaceHolder;
import org.essentialss.api.utils.Singleton;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;

public interface MessageManager {

    @NotNull MessageAdapters adapters();

    @NotNull Singleton<MessageConfig> config();

    <T> @NotNull UnmodifiableCollection<SPlaceHolder<T>> mappedPlaceholdersFor(@NotNull Class<T> type);

    @NotNull UnmodifiableCollection<SPlaceHolder<?>> placeholdersFor(@NotNull Class<?> type);

    void register(@NotNull SPlaceHolder<?> placeholder);


}
