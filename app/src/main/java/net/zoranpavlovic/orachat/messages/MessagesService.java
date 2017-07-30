package net.zoranpavlovic.orachat.messages;

import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;
import net.zoranpavlovic.orachat.messages.create.model.CreateChatMessageResponse;
import net.zoranpavlovic.orachat.messages.list.models.MessagesResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by osx on 30/07/2017.
 */

public interface MessagesService {

    @GET("chats/{id}/chat_messages")
    Flowable<Response<MessagesResponse>> getMessagesForChat(@Query("id") int id, @Query("page") int page, @Query("limit") int limit);

    @POST("chats/{id}/chat_messages")
    Flowable<Response<CreateChatMessageResponse>> createMessage(@Field("id") int id, @Field("message") String message);
}
