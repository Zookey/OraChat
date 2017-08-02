package net.zoranpavlovic.orachat.account.logout;

import net.zoranpavlovic.orachat.account.AccountService;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by osx on 02/08/2017.
 */

public class LogoutRepository {

    private Retrofit retrofit;

    public LogoutRepository(Retrofit retrofit){
        this.retrofit = retrofit;
    }

    public Flowable<ResponseBody> logout(){
        return retrofit.create(AccountService.class).logout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
