package net.zoranpavlovic.orachat.account.login;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 29/07/2017.
 */

@Module
public class LoginAccountModule {

    private LoginAccountView view;

    public LoginAccountModule(LoginAccountView view){
        this.view = view;
    }

    @Provides
    @FragmentScoped
    LoginAccountView providesLoginAccountView(){
        return view;
    }

    @Provides
    @FragmentScoped
    LoginAccountPresenter providesLoginAccountPresenter(LoginAccountPresenterImpl presenter){
        return presenter;
    }
}
