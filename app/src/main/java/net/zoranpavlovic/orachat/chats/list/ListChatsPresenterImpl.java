package net.zoranpavlovic.orachat.chats.list;

import android.util.Log;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.chats.ChatsService;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

public class ListChatsPresenterImpl implements ListChatsPresenter {

    private ListChatsRepository repository;
    private ListChatsView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ListChatsPresenterImpl(ListChatsRepository repository, ListChatsView view){
        this.repository = repository;
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
    public void getChats(String name, int page, int limit) {
        DisposableSubscriber disposableSubscriber = repository.getChats(name, page, limit)
                .subscribeWith(new DisposableSubscriber<ChatsResponse>() {
                    @Override
                    public void onNext(ChatsResponse response) {
                        if(view != null){
                            view.onChatsLoaded(response);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onChatsError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }
}
