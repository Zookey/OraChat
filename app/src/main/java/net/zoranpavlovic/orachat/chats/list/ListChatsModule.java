package net.zoranpavlovic.orachat.chats.list;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 30/07/2017.
 */
@Module
public class ListChatsModule {

    private ListChatsView view;

    public ListChatsModule(ListChatsView view){
        this.view = view;
    }

    @Provides
    @FragmentScoped
    ListChatsView providesListChatsView(){
        return view;
    }

    @Provides
    @FragmentScoped
    ListChatsPresenter providesListChatsPresenter(ListChatsPresenterImpl presenter){
        return presenter;
    }
}
