package net.zoranpavlovic.orachat.core;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by osx on 29/07/2017.
 */

public class HeaderInterceptor implements Interceptor {

    private Context context;

    public HeaderInterceptor(Context context){
        this.context = context;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}