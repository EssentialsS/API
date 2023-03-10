package org.essentialss.api.message;

import org.essentialss.api.message.adapters.player.command.PlayerOnlyCommandMessageAdapter;
import org.essentialss.api.message.adapters.player.command.WhoIsMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardBarMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardForTooLongMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.AwayFromKeyboardMessageAdapter;
import org.essentialss.api.message.adapters.player.listener.afk.BackToKeyboardMessageAdapter;
import org.essentialss.api.message.placeholder.MessageAdapter;
import org.essentialss.api.utils.Singleton;

import java.util.stream.Stream;

public interface MessageAdapters {

    Stream<MessageAdapter> all();

    Singleton<AwayFromKeyboardMessageAdapter> awayFromKeyboard();

    Singleton<AwayFromKeyboardBarMessageAdapter> awayFromKeyboardBar();

    Singleton<AwayFromKeyboardForTooLongMessageAdapter> awayFromKeyboardForTooLong();

    Singleton<BackToKeyboardMessageAdapter> backToKeyboard();

    Singleton<PlayerOnlyCommandMessageAdapter> playerOnlyCommand();

    Singleton<WhoIsMessageAdapter> whoIs();
}
