package net.zoranpavlovic.orachat.core.di.module;

/**
 * Created by Zoran on 28/07/2017.
 */

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.zoranpavlovic.orachat.core.HeaderInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by osx on 24/07/2017.
 */

@Module
public class NetModule {

    private  String baseUrl;

    public NetModule(String baseUrl){
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    public Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Cache cache, Application application) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new HeaderInterceptor(application));
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }
}