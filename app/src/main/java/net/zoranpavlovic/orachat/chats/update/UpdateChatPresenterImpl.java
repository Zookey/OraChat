package net.zoranpavlovic.orachat.chats.update;

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

public class UpdateChatPresenterImpl implements UpdateChatPresenter {

    private UpdateChatRepository repository;
    private UpdateChatView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public UpdateChatPresenterImpl(UpdateChatRepository repository, UpdateChatView view){
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
    public void updateChat(int id) {
        DisposableSubscriber disposableSubscriber = repository.updateChat(id)
                .subscribeWith(new DisposableSubscriber<Response<CreateChatResponse>>() {
                    @Override
                    public void onNext(Response<CreateChatResponse> response) {
                        if(view != null){
                            view.onChatUpdateSuccess(response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onChatUpdateError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }
}
