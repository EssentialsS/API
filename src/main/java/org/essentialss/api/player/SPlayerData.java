package org.essentialss.api.player;

import org.essentialss.api.player.mail.MailMessage;
import org.essentialss.api.player.mail.MailMessageBuilder;
import org.essentialss.api.player.teleport.TeleportRequest;
import org.essentialss.api.player.teleport.TeleportRequestBuilder;
import org.essentialss.api.utils.arrays.UnmodifiableCollection;
import org.essentialss.api.world.SWorldData;
import org.essentialss.api.world.points.home.SHome;
import org.essentialss.api.world.points.jail.SJailSpawnPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public interface SPlayerData {

    @NotNull Player spongePlayer();

    boolean canLooseItemsWhenUsed();

    void setCanLooseItemsWhenUsed(boolean check);

    boolean isAwayFromKeyboard();

    void setAwayFromKeyboard(boolean afk);

    boolean muted();

    void setMuted(boolean mute);

    boolean isInJail();

    boolean isPreventingTeleportRequests();

    void setPreventTeleportRequests(boolean prevent);

    Optional<LocalDateTime> releasedFromJailTime();

    void releaseFromJail(@NotNull Location<?, ?> spawnTo);

    void sendToJail(@NotNull SJailSpawnPoint point, @Nullable Duration length);

    @NotNull UnmodifiableCollection<SHome> homes();

    @NotNull UnmodifiableCollection<TeleportRequest> teleportRequests();

    @NotNull LinkedList<Location<?, ?>> backTeleportLocations();

    @NotNull OptionalInt backTeleportIndex();

    void setBackTeleportLocations(Collection<Location<?, ?>> locations);

    void addBackTeleportLocation(@NotNull Location<?, ?> location);

    void removeBackTeleportLocation(@NotNull Location<?, ?> location);

    default void clearBackTeleportLocations() {
        this.setBackTeleportLocations(Collections.emptyList());
    }

    void register(@NotNull TeleportRequestBuilder builder);

    void decline(@NotNull TeleportRequest request);

    void accept(@NotNull TeleportRequest request) throws IllegalStateException;

    @NotNull SWorldData world();

    @NotNull UnmodifiableCollection<MailMessage> mailMessages();

    void removeMessage(@NotNull MailMessage message);

    void addMailMessage(@NotNull MailMessageBuilder builder);

    default Optional<SHome> home(@NotNull String homeName) {
        return this.homes().parallelStream().filter(home -> home.identifier().equalsIgnoreCase(homeName)).findAny();
    }

    default void releaseFromJail() {
        this.releaseFromJail(this.world().spawnPoint(this.spongePlayer().position()).location());
    }

}
