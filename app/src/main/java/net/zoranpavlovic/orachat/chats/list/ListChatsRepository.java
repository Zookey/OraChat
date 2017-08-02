package net.zoranpavlovic.orachat.chats.list;

import net.zoranpavlovic.orachat.chats.ChatsService;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class ListChatsRepository {

    private Retrofit retrofit;

    public ListChatsRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<ChatsResponse> getChats(String name, int page, int limit) {
        return retrofit.create(ChatsService.class).getChats(name, page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
