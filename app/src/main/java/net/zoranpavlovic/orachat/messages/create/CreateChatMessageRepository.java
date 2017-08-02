package net.zoranpavlovic.orachat.messages.create;

import net.zoranpavlovic.orachat.chats.create.CreateChatRepository;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.messages.MessagesService;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class CreateChatMessageRepository {

    private Retrofit retrofit;

    public CreateChatMessageRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<Response<CreateChatMessageResponse>> createMessage(int id, String message) {
        return retrofit.create(MessagesService.class).createMessage(id, message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
