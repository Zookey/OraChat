package net.zoranpavlovic.orachat.account.update;

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
 * Created by osx on 30/07/2017.
 */

public class UpdateCurrentUserPresenterImpl implements UpdateCurrentUserPresenter {

    private UpdateCurrentUserRepository repository;
    private UpdateCurrentUserView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public UpdateCurrentUserPresenterImpl(UpdateCurrentUserRepository repository, UpdateCurrentUserView view){
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void onViewDestroy() {
        this.view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }

    @Override
    public void updateCurrentUser(String name, String email, String password, String confirm) {
        DisposableSubscriber disposableSubscriber = repository.updateCurrentUser(name, email, password, confirm)
                .subscribeWith(new DisposableSubscriber<Response<AccountResponse>>() {
                    @Override
                    public void onNext(Response<AccountResponse> response) {
                        if(view != null){
                            view.onCurrentUserUpdated(response.body());
                            Log.d("TAG", response.headers().toString());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if(view != null && t != null){
                            view.onCurrentUserUpdateError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        compositeDisposable.add(disposableSubscriber);
    }
}
