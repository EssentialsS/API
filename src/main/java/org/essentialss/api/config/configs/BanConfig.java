package org.essentialss.api.config.configs;

import net.kyori.adventure.text.Component;
import org.essentialss.api.ban.BanMultiplayerScreenOptions;
import org.essentialss.api.config.SConfig;
import org.essentialss.api.config.value.SingleConfigValue;

public interface BanConfig extends SConfig {

    SingleConfigValue.Default<Component> banMessage();

    SingleConfigValue.Default<BanMultiplayerScreenOptions> showBanOnMultiplayerScreen();

    SingleConfigValue.Default<Boolean> showFullOnMultiplayerScreen();

    SingleConfigValue<Component> tempBanMessage();

    SingleConfigValue.Default<Boolean> useBanMessageForTempBan();

    SingleConfigValue.Default<Boolean> useEssentialsSBanCommands();

}
