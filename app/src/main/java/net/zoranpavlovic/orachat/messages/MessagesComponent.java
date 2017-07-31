package net.zoranpavlovic.orachat.messages;

import net.zoranpavlovic.orachat.chats.list.ListChatsModule;
import net.zoranpavlovic.orachat.core.di.ActivityScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;
import net.zoranpavlovic.orachat.messages.MessagesActivity;
import net.zoranpavlovic.orachat.messages.create.CreateChatMessageModule;
import net.zoranpavlovic.orachat.messages.list.ListChatMessagesModule;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@ActivityScoped
@Component(dependencies = AppComponent.class, modules = {ListChatMessagesModule.class, CreateChatMessageModule.class})
public interface MessagesComponent {
    void inject(MessagesActivity activity);
}
