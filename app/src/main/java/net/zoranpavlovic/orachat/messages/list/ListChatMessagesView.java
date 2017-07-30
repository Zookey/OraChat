package net.zoranpavlovic.orachat.messages.list;

import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface ListChatMessagesView {

    void onChatMessagesLoaded(MessagesResponse response);

    void onChatMessagesError(String error);
}
