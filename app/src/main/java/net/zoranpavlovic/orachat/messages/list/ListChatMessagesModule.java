package net.zoranpavlovic.orachat.messages.list;

import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.core.di.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class ListChatMessagesModule {

    private ListChatsView view;

    public ListChatMessagesModule(ListChatsView view){
        this.view = view;
    }

    @Provides
    @ActivityScoped
    ListChatsView providesView(){
        return view;
    }

    @Provides
    @ActivityScoped
    ListChatMessagesPresenter providesPresenter(ListChatMessagesPresenterImpl presenter){
        return presenter;
    }
}
