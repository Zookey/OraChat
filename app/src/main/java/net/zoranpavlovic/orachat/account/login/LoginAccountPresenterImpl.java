package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.account.register.AccountResponse;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by osx on 29/07/2017.
 */

public class LoginAccountPresenterImpl implements LoginAccountPresenter {

    private LoginAccountView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private LoginRepository loginRepository;

    @Inject
    public LoginAccountPresenterImpl(LoginRepository loginRepository, LoginAccountView view) {
        this.loginRepository = loginRepository;
        this.view = view;
    }

    @Override
    public void onViewDestroy() {
        view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    @Override
    public void login(String email, String password) {
        DisposableSubscriber<AccountResponse> disposable =
                loginRepository.login(email, password)
                .subscribeWith(new DisposableSubscriber<AccountResponse>() {
                    @Override
                    public void onNext(AccountResponse response) {
                        if (view != null) {
                            view.onLoginSuccess(response);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (view != null && t != null) {
                            view.onLoginError(t.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        compositeDisposable.add(disposable);
    }
}
