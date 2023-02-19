package org.essentialss.api.config;

import net.kyori.adventure.text.Component;
import org.essentialss.api.ban.BanMultiplayerScreenOptions;
import org.essentialss.api.config.value.SingleConfigValue;

public interface BanConfig extends SConfig {

    SingleConfigValue.Default<BanMultiplayerScreenOptions> showBanOnMultiplayerScreen();

    SingleConfigValue.Default<Boolean> showFullOnMultiplayerScreen();

    SingleConfigValue.Default<Component> banMessage();

    SingleConfigValue<Component> tempBanMessage();

    SingleConfigValue.Default<Boolean> useBanMessageForTempBan();

}
