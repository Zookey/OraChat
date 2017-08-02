package net.zoranpavlovic.orachat.account.update;

import net.zoranpavlovic.orachat.chats.update.UpdateChatView;
import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class UpdateCurrentUserModule {

    private UpdateCurrentUserView view;

    public UpdateCurrentUserModule(UpdateCurrentUserView view){
        this.view = view;
    }

    @FragmentScoped
    @Provides
    UpdateCurrentUserRepository providesRepository(Retrofit retrofit){
        return new UpdateCurrentUserRepository(retrofit);
    }

    @FragmentScoped
    @Provides
    UpdateCurrentUserView providesView(){
        return view;
    }

    @Provides
    @FragmentScoped
    UpdateCurrentUserPresenter providesPresenter(UpdateCurrentUserPresenterImpl presenter){
        return presenter;
    }
}
