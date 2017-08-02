package net.zoranpavlovic.orachat.account.logout;

import net.zoranpavlovic.orachat.account.login.LoginAccountPresenterImpl;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 29/07/2017.
 */

@Module
public class LogoutAccountModule {

    private LogoutAccountView view;

    public LogoutAccountModule(LogoutAccountView view){
        this.view = view;
    }


    @Provides
    @FragmentScoped
    LogoutRepository providesRepository(Retrofit retrofit){
        return new LogoutRepository(retrofit);
    }

    @Provides
    @FragmentScoped
    LogoutAccountView providesLogoutAccountView(){
        return view;
    }

    @Provides
    @FragmentScoped
    LogoutAccountPresenter providesLogoutAccountPresenter(LogoutAccountPresenterImpl presenter){
        return presenter;
    }
}
