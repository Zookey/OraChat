package net.zoranpavlovic.orachat.messages.list;

import net.zoranpavlovic.orachat.chats.list.ListChatsModule;
import net.zoranpavlovic.orachat.core.di.ActivityScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;
import net.zoranpavlovic.orachat.messages.MessagesActivity;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@ActivityScoped
@Component(dependencies = AppComponent.class, modules = ListChatsModule.class)
public interface ListChatMessagesComponent {
    void inject(MessagesActivity activity);
}
