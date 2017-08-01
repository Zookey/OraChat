package net.zoranpavlovic.orachat.core;

import android.app.Application;

import net.zoranpavlovic.orachat.core.di.component.AppComponent;
import net.zoranpavlovic.orachat.core.di.component.DaggerAppComponent;
import net.zoranpavlovic.orachat.core.di.module.AppModule;
import net.zoranpavlovic.orachat.core.di.module.NetModule;

/**
 * Created by Zoran on 28/07/2017.
 */

public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://private-93240c-oracodechallenge.apiary-mock.com"))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}