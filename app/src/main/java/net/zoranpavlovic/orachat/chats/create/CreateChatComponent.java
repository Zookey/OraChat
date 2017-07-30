package net.zoranpavlovic.orachat.chats.create;

import net.zoranpavlovic.orachat.chats.ChatsFragment;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@FragmentScoped
@Component(dependencies = NetComponent.class, modules = CreateChatModule.class)
public interface CreateChatComponent {
    void inject(ChatsFragment chatsFragment);
}
