package net.zoranpavlovic.orachat.chats;

import net.zoranpavlovic.orachat.chats.create.model.CreateChatResponse;
import net.zoranpavlovic.orachat.chats.list.models.ChatsResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by osx on 30/07/2017.
 */

public interface ChatsService {

    @GET("chats")
    Flowable<Response<ChatsResponse>> getChats(@Query("name") String name, @Query("page") int page, @Query("limit") int limit);

    @FormUrlEncoded
    @POST("chats")
    Flowable<Response<CreateChatResponse>> createChat(@Field(("name")) String name, @Field(("message")) String message);

    @PATCH("chats/{id}")
    Flowable<Response<CreateChatResponse>> updateChat(@Field("id") int id);

}
