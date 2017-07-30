package net.zoranpavlovic.orachat.messages.create;

import net.zoranpavlovic.orachat.core.BasePresenter;

/**
 * Created by osx on 30/07/2017.
 */

public interface CreateChatMessagePresenter extends BasePresenter {

    void createMessage(int id, String message);

}
