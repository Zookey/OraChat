package net.zoranpavlovic.orachat.account.register;

import android.content.SharedPreferences;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.core.Constants;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Headers;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class RegisterRepository {

    private Retrofit retrofit;
    private SharedPreferences sharedPreferences;

    public RegisterRepository(Retrofit retrofit, SharedPreferences sharedPreferences){
        this.retrofit = retrofit;
        this.sharedPreferences = sharedPreferences;
    }

    public Flowable<AccountResponse> register(String name, String email, String password, String confirm) {
        return retrofit.create(AccountService.class).register(name, email, password, confirm)
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
