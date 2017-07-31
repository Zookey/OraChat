package net.zoranpavlovic.orachat.messages.create;

import net.zoranpavlovic.orachat.core.BasePresenter;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;

/**
 * Created by osx on 30/07/2017.
 */

public interface CreateChatMessageView  {

    void onChatMessageCreateSuccess(CreateChatMessageResponse body);

    void onChatMessageCreateError(String error);

}
