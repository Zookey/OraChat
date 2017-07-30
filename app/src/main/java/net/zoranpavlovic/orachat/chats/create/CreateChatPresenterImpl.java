package net.zoranpavlovic.orachat.chats.create;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

public class CreateChatPresenterImpl implements CreateChatPresenter {

    private Retrofit retrofit;
    private CreateChatView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CreateChatPresenterImpl(Retrofit retrofit, CreateChatView view){
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void onViewDestroy() {
        this.view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void createChat(String name, String message) {

    }
}
