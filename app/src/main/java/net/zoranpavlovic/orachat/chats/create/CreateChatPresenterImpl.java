package net.zoranpavlovic.orachat.chats.create;

import android.util.Log;

import net.zoranpavlovic.orachat.chats.ChatsService;
import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;
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
        compositeDisposable.add(retrofit.create(ChatsService.class).createChat(name, message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<Response<CreateChatResponse>>() {
                    @Override
                    public void onNext(Response<CreateChatResponse> response) {
                        if(view != null){
                            view.onChatCreateSuccess(response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onChatCreateError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
