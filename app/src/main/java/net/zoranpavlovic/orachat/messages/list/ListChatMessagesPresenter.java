package net.zoranpavlovic.orachat.messages.list;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 30/07/2017.
 */

public interface ListChatMessagesPresenter extends BasePresenter {

    void getMessagesForChat(int id, int page, int limit);
}
