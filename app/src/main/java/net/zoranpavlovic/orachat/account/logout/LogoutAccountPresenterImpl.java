package net.zoranpavlovic.orachat.account.logout;

import android.util.Log;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.account.register.AccountResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by osx on 29/07/2017.
 */

public class LogoutAccountPresenterImpl implements LogoutAccountPresenter {

    private LogoutRepository repository;
    private LogoutAccountView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LogoutAccountPresenterImpl(LogoutRepository repository, LogoutAccountView view){
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void onViewDestroy() {
        view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void logout() {
        DisposableSubscriber disposableSubscriber = repository.logout()
                .subscribeWith(new DisposableSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody response) {
                        if(view != null){
                            view.onLogoutSuccess();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onLogoutError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(view != null){
                            view.onLogoutSuccess();
                        }
                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }
}
