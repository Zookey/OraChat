package net.zoranpavlovic.orachat.messages.list;

import android.util.Log;

import net.zoranpavlovic.orachat.chats.ChatsService;
import net.zoranpavlovic.orachat.chats.list.ListChatsPresenter;
import net.zoranpavlovic.orachat.chats.list.ListChatsView;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.messages.MessagesService;
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

public class ListChatMessagesPresenterImpl implements ListChatMessagesPresenter {

    private ListChatMessagesRepository repository;
    private ListChatMessagesView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ListChatMessagesPresenterImpl(ListChatMessagesRepository repository, ListChatMessagesView view){
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
    public void getMessagesForChat(int id, int page, int limit) {
        DisposableSubscriber disposableSubscriber = repository.getMessagesForChat(id, page, limit)
                .subscribeWith(new DisposableSubscriber<MessagesResponse>() {
                    @Override
                    public void onNext(MessagesResponse response) {
                        if(view != null){
                            view.onChatMessagesLoaded(response);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onChatMessagesError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        compositeDisposable.add(disposableSubscriber);
    }
}
