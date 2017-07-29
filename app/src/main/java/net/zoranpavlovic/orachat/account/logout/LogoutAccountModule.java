package net.zoranpavlovic.orachat.account.logout;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

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
    LogoutAccountView providesLogoutAccountView(){
        return view;
    }
}
