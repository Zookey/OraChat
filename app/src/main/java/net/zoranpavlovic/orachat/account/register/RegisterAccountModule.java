package net.zoranpavlovic.orachat.account.register;

import net.zoranpavlovic.orachat.core.di.CustomScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 29/07/2017.
 */

@Module
public class RegisterAccountModule {

    private RegisterAccountView view;

    public RegisterAccountModule(RegisterAccountView view){
        this.view = view;
    }


    @Provides
    @CustomScope
    RegisterAccountView providesGetSurveyView(){
        return view;
    }
}
