package net.zoranpavlovic.orachat.chats.create;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 30/07/2017.
 */

public interface CreateChatPresenter extends BasePresenter{

    void createChat(String name, String message);
}
