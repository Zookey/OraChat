package net.zoranpavlovic.orachat.messages.create;

import android.util.Log;

import net.zoranpavlovic.orachat.messages.MessagesService;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

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

public class CreateChatMessagePresenterImpl implements CreateChatMessagePresenter {

    private Retrofit retrofit;
    private CreateChatMessageView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    private CreateChatMessagePresenterImpl(Retrofit retrofit, CreateChatMessageView view){
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
    public void createMessage(int id, String message) {
        compositeDisposable.add(retrofit.create(MessagesService.class).createMessage(id, message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<Response<CreateChatMessageResponse>>() {
                    @Override
                    public void onNext(Response<CreateChatMessageResponse> response) {
                        if(view != null){
                            view.onChatMessageCreateSuccess(response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onChatMessageCreateError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
