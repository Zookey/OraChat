package net.zoranpavlovic.orachat.account.login;

import android.content.Intent;
import android.content.SharedPreferences;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.account.register.AccountResponse;
import net.zoranpavlovic.orachat.core.Constants;
import net.zoranpavlovic.orachat.main.MainActivity;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.Headers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class LoginRepository {

    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;

    public LoginRepository(Retrofit retrofit, SharedPreferences sharedPreferences) {
        this.retrofit = retrofit;
        this.sharedPreferences = sharedPreferences;
    }


    public Flowable<AccountResponse> login(String email, String password) {
        return retrofit.create(AccountService.class).login(email, password)
                .subscribeOn(Schedulers.io())
                .map(new Function<Response<AccountResponse>, AccountResponse>() {
                    @Override
                    public AccountResponse apply(@NonNull Response<AccountResponse> accountResponseResponse) throws Exception {
                        saveUser(accountResponseResponse);
                        saveToken(accountResponseResponse);
                        return accountResponseResponse.body();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void saveUser(Response<AccountResponse> accountResponse) {
        int id = accountResponse.body().getData().getId();
        sharedPreferences.edit().putInt(Constants.USER_ID, id).apply();
    }

    private void saveToken(Response<AccountResponse> accountResponse) {
        Headers headers = accountResponse.headers();
        String token = headers.get(Constants.AUTHORIZATION);
        sharedPreferences.edit().putString(Constants.AUTHORIZATION, token).apply();
    }

}
