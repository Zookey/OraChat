package net.zoranpavlovic.orachat.chats.list;

import net.zoranpavlovic.orachat.chats.ChatsFragment;
import net.zoranpavlovic.orachat.chats.create.CreateChatModule;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.AppComponent;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@FragmentScoped
@Component(dependencies = AppComponent.class, modules = {ListChatsModule.class, CreateChatModule.class})
public interface ListChatsComponent {
    void inject(ChatsFragment chatsFragment);
}
