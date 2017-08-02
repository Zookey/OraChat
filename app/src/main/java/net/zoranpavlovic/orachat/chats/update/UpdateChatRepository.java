package net.zoranpavlovic.orachat.chats.update;

import net.zoranpavlovic.orachat.chats.ChatsService;
import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class UpdateChatRepository {

    private Retrofit retrofit;

    public UpdateChatRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<Response<CreateChatResponse>> updateChat(int id){
        return retrofit.create(ChatsService.class).updateChat(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
