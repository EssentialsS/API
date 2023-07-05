package org.essentialss.api.utils.compare;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.player.data.SGeneralUnloadedData;

import java.util.Comparator;

public final class PlayerCompare {

    private PlayerCompare() {
        throw new RuntimeException("Should not generate");
    }

    public static Comparator<SGeneralUnloadedData> displayName() {
        return Comparator.comparing(p -> PlainTextComponentSerializer.plainText().serialize(p.displayName()));
    }

    public static Comparator<SGeneralUnloadedData> isOnline() {
        return (player, comparing) -> {
            if ((player instanceof SGeneralPlayerData) && !(comparing instanceof SGeneralPlayerData)) {
                return 1;
            }
            if ((comparing instanceof SGeneralPlayerData) && !(player instanceof SGeneralPlayerData)) {
                return -1;
            }
            return 0;
        };
    }

}
