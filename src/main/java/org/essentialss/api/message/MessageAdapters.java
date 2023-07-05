package org.essentialss.api.message;

import org.essentialss.api.message.adapters.MessageAdapter;
import org.essentialss.api.message.adapters.player.command.PingMessageAdapter;
import org.essentialss.api.message.adapters.player.command.PlayerOnlyCommandMessageAdapter;
import org.essentialss.api.message.adapters.player.command.WhoIsMessageAdapter;
import org.essentialss.api.message.adapters.player.command.mute.MutedMessageAdapter;
import org.essentialss.api.message.adapters.player.command.mute.UnmutedMessageAdapter;
import org.essentialss.api.message.adapters.player.command.mute.YouAreNowMutedMessageAdapter;
import org.essentialss.api.message.adapters.player.command.mute.YouAreNowUnmutedMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardBarMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardForTooLongMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.BackToKeyboardMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.chat.ChatMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.spy.CommandSpyMessageAdapter;
import org.essentialss.api.message.adapters.vanilla.player.PlayerJoinMessageAdapter;
import org.essentialss.api.message.adapters.warp.CreateWarpMessageAdapter;
import org.essentialss.api.message.adapters.world.NoWorldByThatKeyMessageAdapter;
import org.essentialss.api.message.adapters.world.WorldHasAlreadyLoadedMessageAdapter;
import org.essentialss.api.message.adapters.world.create.CreatedWorldMessageAdapter;
import org.essentialss.api.message.adapters.world.create.CreatingWorldMessageAdapter;
import org.essentialss.api.message.adapters.world.load.LoadedWorldMessageAdapter;
import org.essentialss.api.message.adapters.world.load.LoadingWorldMessageAdapter;
import org.essentialss.api.message.adapters.world.unload.UnloadedWorldMessageAdapter;
import org.essentialss.api.message.adapters.world.unload.UnloadingWorldMessageAdapter;
import org.essentialss.api.utils.Singleton;

import java.util.stream.Stream;

public interface MessageAdapters {

    Stream<MessageAdapter> all();

    Singleton<AwayFromKeyboardMessageAdapter> awayFromKeyboard();

    Singleton<AwayFromKeyboardBarMessageAdapter> awayFromKeyboardBar();

    Singleton<AwayFromKeyboardForTooLongMessageAdapter> awayFromKeyboardForTooLong();

    Singleton<BackToKeyboardMessageAdapter> backToKeyboard();

    Singleton<ChatMessageAdapter> chat();

    Singleton<CommandSpyMessageAdapter> commandSpy();

    Singleton<CreateWarpMessageAdapter> createWarp();

    Singleton<CreatedWorldMessageAdapter> createdWorld();

    Singleton<CreatingWorldMessageAdapter> creatingWorld();

    Singleton<LoadedWorldMessageAdapter> loadedWorld();

    Singleton<LoadingWorldMessageAdapter> loadingWorld();

    Singleton<MutedMessageAdapter> muted();

    Singleton<NoWorldByThatKeyMessageAdapter> noWorldByThatKey();

    Singleton<PingMessageAdapter> ping();

    Singleton<PlayerJoinMessageAdapter> playerJoin();

    Singleton<PlayerOnlyCommandMessageAdapter> playerOnlyCommand();

    Singleton<UnloadedWorldMessageAdapter> unloadWorld();

    Singleton<UnloadingWorldMessageAdapter> unloadingWorld();

    Singleton<UnmutedMessageAdapter> unmuted();

    Singleton<WhoIsMessageAdapter> whoIs();

    Singleton<WorldHasAlreadyLoadedMessageAdapter> worldHasAlreadyLoaded();

    Singleton<YouAreNowMutedMessageAdapter> youAreNowMuted();

    Singleton<YouAreNowUnmutedMessageAdapter> youAreNowUnmuted();
}
