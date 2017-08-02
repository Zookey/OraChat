package net.zoranpavlovic.orachat.chats.create;

import net.zoranpavlovic.orachat.account.register.AccountResponse;
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

public class CreateChatRepository {

    private Retrofit retrofit;

    public CreateChatRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<Response<CreateChatResponse>> createChat(String name, String message){
        return retrofit.create(ChatsService.class).createChat(name, message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
