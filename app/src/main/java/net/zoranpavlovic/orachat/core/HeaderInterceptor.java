package net.zoranpavlovic.orachat.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.util.Log;


import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by osx on 29/07/2017.
 */

public class HeaderInterceptor implements Interceptor {

    private Context context;

    @Inject
    SharedPreferences sharedPreferences;

    public HeaderInterceptor(Context context){
        this.context = context;

        sharedPreferences = ((App) context.getApplicationContext()).getAppComponent().getSharedPreferences();

    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String token = sharedPreferences.getString("Authorization", "");
        builder.addHeader("Authorization", token);
        return chain.proceed(builder.build());
    }
}