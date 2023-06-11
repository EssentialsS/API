package org.essentialss.api.group;

import org.essentialss.api.EssentialsSAPI;
import org.essentialss.api.kit.Kit;

import java.time.Duration;
import java.util.Optional;

public interface Group {

    String groupName();

    default Optional<Duration> kitCooldown(Kit kit) {
        return kit.cooldown(this);
    }

    default Duration kitCooldownDefault(Kit kit) {
        return this.kitCooldown(kit).orElseGet(() -> kit.cooldown(EssentialsSAPI.get().groupManager().get().defaultGroup()).orElse(Duration.ZERO));
    }
}
