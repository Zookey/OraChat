package net.zoranpavlovic.orachat.account.register;

import android.util.Log;

import net.zoranpavlovic.orachat.account.AccountService;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;

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
    private RegisterRepository registerRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RegisterAccountPresenterImpl(RegisterRepository registerRepository, RegisterAccountView view){
        this.registerRepository = registerRepository;
        this.view = view;
    }

    @Override
    public void register(String name, String email, String password, String confirm) {
        DisposableSubscriber<AccountResponse> disposable =
                registerRepository.register(name, email, password, confirm)
                .subscribeWith(new DisposableSubscriber<AccountResponse>() {
                    @Override
                    public void onNext(AccountResponse response) {
                        if(view != null){
                            view.onRegisterAccountSuccess(response);
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
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        view = null;
        if(compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
