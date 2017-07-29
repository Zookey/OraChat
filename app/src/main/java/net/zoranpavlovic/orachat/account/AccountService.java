package net.zoranpavlovic.orachat.account;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by osx on 29/07/2017.
 */

public interface AccountService {

    @FormUrlEncoded
    @POST("users")
    Flowable<Response<AccountResponse>> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String confirm);
}
