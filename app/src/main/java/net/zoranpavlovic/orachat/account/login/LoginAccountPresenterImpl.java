package net.zoranpavlovic.orachat.account.login;

import android.util.Log;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.account.register.AccountResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 29/07/2017.
 */

public class LoginAccountPresenterImpl  implements LoginPresenter{

    private Retrofit retrofit;
    private LoginAccountView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginAccountPresenterImpl(Retrofit retrofit, LoginAccountView view){
        this.retrofit = retrofit;
        this.view  = view;
    }

    @Override
    public void onViewDestroy() {
        view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void login(String email, String password) {
        compositeDisposable.add(retrofit.create(AccountService.class).login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<Response<AccountResponse>>() {
                    @Override
                    public void onNext(Response<AccountResponse> response) {
                        if(view != null){
                            view.onLoginSuccess(response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onLoginError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }
}
