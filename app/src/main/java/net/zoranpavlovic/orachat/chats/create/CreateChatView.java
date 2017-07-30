package net.zoranpavlovic.orachat.chats.create;

import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface CreateChatView {

    void onChatCreateSuccess(CreateChatResponse createChatResponse);

    void onChatCreateError(String error);
}
