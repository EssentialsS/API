package org.essentialss.api.events.player.chat;

import org.essentialss.api.message.MuteType;
import org.essentialss.api.player.data.SGeneralPlayerData;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ChangeMuteTypeEvent {

    @NotNull UnmodifiableCollection<MuteType> muteTypes();

    @NotNull Collection<MuteType> muteTypesToSet();

    default @NotNull UnmodifiableCollection<MuteType> originalMuteTypes() {
        return this.player().muteTypes();
    }

    @NotNull SGeneralPlayerData player();

}
