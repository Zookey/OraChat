package net.zoranpavlovic.orachat.di.component;

import net.zoranpavlovic.orachat.di.module.AppModule;
import net.zoranpavlovic.orachat.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Zoran on 28/07/2017.
 */

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
}