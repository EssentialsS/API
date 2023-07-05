package org.essentialss.api.config.configs;

import org.essentialss.api.config.SConfig;
import org.essentialss.api.config.value.CollectionConfigValue;
import org.essentialss.api.config.value.SingleConfigValue;
import org.essentialss.api.group.Group;
import org.spongepowered.api.event.cause.entity.damage.DamageType;

public interface GeneralConfig extends SConfig {

    SingleConfigValue.Default<Boolean> checkForUpdatesOnStartup();

    CollectionConfigValue.Default<DamageType> demiGodImmuneTo();

    CollectionConfigValue<Group> groups();

    SingleConfigValue.Default<Integer> pageSize();

}
