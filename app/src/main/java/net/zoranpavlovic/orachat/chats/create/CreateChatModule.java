package net.zoranpavlovic.orachat.chats.create;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class CreateChatModule {

    private CreateChatView view;

    public CreateChatModule(CreateChatView view){
        this.view = view;
    }

    @Provides
    @FragmentScoped
    CreateChatView providesView(){
        return view;
    }

    @Provides
    @FragmentScoped
    CreateChatPresenter providesPresenter(CreateChatPresenterImpl presenter){
        return presenter;
    }
}
