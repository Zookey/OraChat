package net.zoranpavlovic.orachat.chats.list;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 30/07/2017.
 */

public interface ListChatsPresenter extends BasePresenter{

    void getChats(String name, int page, int limit);

}
