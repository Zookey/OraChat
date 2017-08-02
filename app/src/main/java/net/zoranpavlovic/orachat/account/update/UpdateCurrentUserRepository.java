package net.zoranpavlovic.orachat.account.update;

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

public class UpdateCurrentUserRepository {

    private Retrofit retrofit;

    public UpdateCurrentUserRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<Response<AccountResponse>> updateCurrentUser(String name, String email, String password, String confirm){
        return retrofit.create(AccountService.class).login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
