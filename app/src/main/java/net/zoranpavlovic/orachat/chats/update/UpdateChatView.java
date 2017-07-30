package net.zoranpavlovic.orachat.chats.update;

import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface UpdateChatView {

    void onChatUpdateSuccess(CreateChatResponse createChatResponse);

    void onChatUpdateError(String error);
}
