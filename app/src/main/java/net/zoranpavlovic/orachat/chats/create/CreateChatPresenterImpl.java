package net.zoranpavlovic.orachat.chats.create;

import android.util.Log;

import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;

/**
 * Created by osx on 30/07/2017.
 */

public class CreateChatPresenterImpl implements CreateChatPresenter {

    private CreateChatRepository repository;
    private CreateChatView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CreateChatPresenterImpl(CreateChatRepository repository, CreateChatView view){
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
    public void createChat(String name, String message) {
        DisposableSubscriber disposableSubscriber = repository.createChat(name, message)
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
                });
        compositeDisposable.add(disposableSubscriber);
    }
}
