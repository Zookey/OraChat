package net.zoranpavlovic.orachat.account.register;

import android.util.Log;

import net.zoranpavlovic.orachat.account.AccountService;

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

public class RegisterAccountPresenterImpl implements RegisterAccountPresenter{

    private RegisterAccountView view;
    private Retrofit retrofit;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RegisterAccountPresenterImpl(Retrofit retrofit, RegisterAccountView view){
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void register(String name, String email, String password, String confirm) {
        compositeDisposable.add(retrofit.create(AccountService.class).register(name, email, password, confirm)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSubscriber<Response>() {
                    @Override
                    public void onNext(Response response) {
                        if(view != null){
                            view.onRegisterAccountSuccess((AccountResponse) response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onRegisterAccountError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    @Override
    public void onViewDestroy() {
        view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
