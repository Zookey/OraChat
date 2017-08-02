package net.zoranpavlovic.orachat.account.current;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.account.register.AccountResponse;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class GetCurrentUserRepository {

    private Retrofit retrofit;

    public GetCurrentUserRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<Response<AccountResponse>> getCurrentUser(){
        return retrofit.create(AccountService.class).getCurrentUser()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
