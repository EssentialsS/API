package org.essentialss.api.message.adapters.player.listener.chat.format;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;

public interface ChatFormat {

    @NotNull Component adapt(@NotNull ServerPlayer player,
                             @NotNull SGeneralPlayerData playerData,
                             @NotNull Audience audience,
                             @NotNull Component message,
                             @NotNull Component originalMessage);

}
