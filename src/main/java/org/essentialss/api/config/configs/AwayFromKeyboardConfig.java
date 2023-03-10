package org.essentialss.api.config.configs;

import org.essentialss.api.config.SConfig;
import org.essentialss.api.config.value.CollectionConfigValue;
import org.essentialss.api.config.value.SingleConfigValue;
import org.essentialss.api.modifier.SPlayerModifier;

import java.time.Duration;

public interface AwayFromKeyboardConfig extends SConfig {

    SingleConfigValue.Default<Boolean> canJoinInPlaceOfAwayFromKeyboard();

    SingleConfigValue<Duration> durationUntilKick();

    SingleConfigValue<Duration> durationUntilModifiers();

    SingleConfigValue<Duration> durationUntilStatus();

    SingleConfigValue.Default<Boolean> lockPosition();

    CollectionConfigValue<SPlayerModifier<?>> modifiers();

    SingleConfigValue.Default<Boolean> showAwayFromKeyboardPlayersOnMultiplayerScreen();

}
