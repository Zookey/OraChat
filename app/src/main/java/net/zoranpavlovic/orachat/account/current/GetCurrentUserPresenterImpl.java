package net.zoranpavlovic.orachat.account.current;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

public class GetCurrentUserPresenterImpl implements GetCurrentUserPresenter {

    private Retrofit retrofit;
    private GetCurrentUserView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public void GetCurrentUserPresenterImpl(Retrofit retrofit, GetCurrentUserView view){
        this.retrofit = retrofit;
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
    public void getCurrentUser() {

    }
}
