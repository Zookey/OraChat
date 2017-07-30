package net.zoranpavlovic.orachat.account.current;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class GetCurrentUserModule {

    private GetCurrentUserView view;

    public void GetCurrentUserModule(GetCurrentUserView view){
        this.view = view;
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
