package net.zoranpavlovic.orachat.messages.create;

import net.zoranpavlovic.orachat.core.di.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class CreateChatMessageModule {

    private CreateChatMessageView view;

    public CreateChatMessageModule(CreateChatMessageView view){
        this.view = view;
    }

    @Provides
    @ActivityScoped
    CreateChatMessageView providesView(){
        return view;
    }

    @Provides
    @ActivityScoped
    CreateChatMessagePresenter providesPresenter(CreateChatMessagePresenterImpl presenter){
        return presenter;
    }
}
