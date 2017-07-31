package net.zoranpavlovic.orachat.account;

import android.accounts.Account;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

/**
 * Created by osx on 29/07/2017.
 */

public interface AccountService {

    @FormUrlEncoded
    @POST("users")
    Flowable<Response<AccountResponse>> register(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("password_confirmation") String confirm);

    @FormUrlEncoded
    @POST("auth/login")
    Flowable<Response<AccountResponse>> login(@Field("email") String email, @Field("password") String password);

    @GET("auth/logout")
    Flowable<ResponseBody> logout();

    @GET("users/current")
    Flowable<Response<AccountResponse>> getCurrentUser();

    @PATCH("users/current")
    Flowable<Response<AccountResponse>> updateCurrentUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("confirm_password") String confirm);


}
