package net.zoranpavlovic.orachat.core;

import android.app.Application;

import net.zoranpavlovic.orachat.core.di.component.DaggerNetComponent;
import net.zoranpavlovic.orachat.core.di.component.NetComponent;
import net.zoranpavlovic.orachat.core.di.module.AppModule;
import net.zoranpavlovic.orachat.core.di.module.NetModule;

/**
 * Created by Zoran on 28/07/2017.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://private-anon-6e798b3943-oracodechallenge.apiary-mock.com"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}