package net.zoranpavlovic.orachat.account.current;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class GetCurrentUserModule {

    private GetCurrentUserView view;

    public GetCurrentUserModule(GetCurrentUserView view){
        this.view = view;
    }

    @Provides
    @FragmentScoped
    GetCurrentUserRepository getCurrentUserRepository(Retrofit retrofit){
        return new GetCurrentUserRepository(retrofit);
    }

    @Provides
    @FragmentScoped
    GetCurrentUserView providesView(){
        return view;
    }

    @Provides
    @FragmentScoped
    GetCurrentUserPresenter providesPresenter(GetCurrentUserPresenterImpl presenter){
        return presenter;
    }
}
