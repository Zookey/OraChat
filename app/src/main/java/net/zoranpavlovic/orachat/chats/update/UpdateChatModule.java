package net.zoranpavlovic.orachat.chats.update;

import net.zoranpavlovic.orachat.core.di.FragmentScoped;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by osx on 30/07/2017.
 */

@Module
public class UpdateChatModule {

    private UpdateChatView view;

    public UpdateChatModule(UpdateChatView view){
        this.view = view;
    }


    @Provides
    @FragmentScoped
    UpdateChatRepository providesRepository(Retrofit retrofit){
        return new UpdateChatRepository(retrofit);
    }

    @Provides
    @FragmentScoped
    UpdateChatView providesView(){
        return view;
    }

    @Provides
    @FragmentScoped
    UpdateChatPresenter providePresenter(UpdateChatPresenterImpl presenter){
        return presenter;
    }
}
