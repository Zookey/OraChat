package net.zoranpavlovic.orachat.messages.list;

import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.messages.MessagesService;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class ListChatMessagesRepository {

    private Retrofit retrofit;

    public ListChatMessagesRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<MessagesResponse> getMessagesForChat(int id, int page, int limit) {
        return retrofit.create(MessagesService.class).getMessagesForChat(id, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
