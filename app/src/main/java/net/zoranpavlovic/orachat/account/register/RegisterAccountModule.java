package net.zoranpavlovic.orachat.account.register;

import android.content.SharedPreferences;

import net.zoranpavlovic.orachat.account.login.LoginRepository;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 29/07/2017.
 */

@Module
public class RegisterAccountModule {

    private RegisterAccountView view;
    private SharedPreferences sharedPreferences;

    public RegisterAccountModule(RegisterAccountView view, SharedPreferences sharedPreferences){
        this.view = view;
        this.sharedPreferences = sharedPreferences;
    }

    @Provides
    @FragmentScoped
    public RegisterRepository provideRepository(Retrofit retrofit){
        return new RegisterRepository(retrofit, sharedPreferences);
    }

    @Provides
    @FragmentScoped
    RegisterAccountView providesRegisterAccountView(){
        return view;
    }

    @Provides
    @FragmentScoped
    RegisterAccountPresenter providesRegisterAccountPresenter(RegisterRepository registerRepository){
        return new RegisterAccountPresenterImpl(registerRepository, view);
    }

}
