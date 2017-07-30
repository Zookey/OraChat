package net.zoranpavlovic.orachat.messages.create;

import net.zoranpavlovic.orachat.core.di.ActivityScoped;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;
import net.zoranpavlovic.orachat.messages.MessagesActivity;

import dagger.Component;

/**
 * Created by osx on 30/07/2017.
 */

@ActivityScoped
@Component(dependencies = NetComponent.class, modules = CreateChatMessageModule.class)
public interface CreateChatMessageComponent {
    void  inject(MessagesActivity activity);
}
