package net.zoranpavlovic.orachat.account.login;

import android.content.SharedPreferences;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 29/07/2017.
 */

@Module
public class LoginAccountModule {

    private LoginAccountView view;
    private SharedPreferences sharedPreferences;

    public LoginAccountModule(LoginAccountView view, SharedPreferences sharedPreferences){
        this.view = view;
        this.sharedPreferences = sharedPreferences;
    }

    @Provides
    @FragmentScoped
    public LoginAccountView providesLoginAccountView(){
        return view;
    }

    @Provides
    @FragmentScoped
    public LoginRepository provideLoginRepository(Retrofit retrofit){
        return new LoginRepository(retrofit, sharedPreferences);
    }

    @Provides
    @FragmentScoped
    public LoginAccountPresenter providesLoginAccountPresenter(LoginRepository loginRepository){
        return new LoginAccountPresenterImpl(loginRepository, view);
    }
}
