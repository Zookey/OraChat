package net.zoranpavlovic.orachat.chats.list;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

public class ListChatsPresenterImpl implements ListChatsPresenter {

    private Retrofit retrofit;
    private ListChatsView view;

    @Inject
    public ListChatsPresenterImpl(Retrofit retrofit, ListChatsView view){
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void getChats(String name, int page, int limit) {

    }
}
