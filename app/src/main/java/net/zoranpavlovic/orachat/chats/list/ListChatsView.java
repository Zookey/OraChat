package net.zoranpavlovic.orachat.chats.list;

import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface ListChatsView {

    void onChatsLoaded(ChatsResponse chatsResponse);

    void onChatsError(String error);
}
