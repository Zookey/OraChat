package net.zoranpavlovic.orachat.core.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zoran on 28/07/2017.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(){
        return application.getSharedPreferences("orachat", Context.MODE_PRIVATE);
    }
}