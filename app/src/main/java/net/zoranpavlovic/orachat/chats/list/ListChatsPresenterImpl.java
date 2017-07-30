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

    private Retrofit retrofit;
    private ListChatsView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ListChatsPresenterImpl(Retrofit retrofit, ListChatsView view){
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
    public void getChats(String name, int page, int limit) {
        compositeDisposable.add(retrofit.create(ChatsService.class).getChats(name, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<Response<ChatsResponse>>() {
                    @Override
                    public void onNext(Response<ChatsResponse> response) {
                        if(view != null){
                            view.onChatsLoaded(response.body());
                            Log.d("TAG", response.headers().toString());
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
                }));
    }
}
