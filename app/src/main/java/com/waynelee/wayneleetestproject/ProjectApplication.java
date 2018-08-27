package com.waynelee.wayneleetestproject;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.vondear.rxtool.RxTool;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wayne lee on 2017/6/28.
 */

public class ProjectApplication extends Application {

    private static ProjectApplication mApplication;
    private static final int TIMEOUT_READ = 15;
    private static final int TIMEOUT_CONNECTION = 15;
    private static OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Stetho.initializeWithDefaults(this);
        //常用工具类库初始化
        RxTool.init(this);
    }

    public OkHttpClient genericClient() {

        if (mOkHttpClient != null)
            return mOkHttpClient;

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level = BuildConfig.IS_DEBUG ?
                HttpLoggingInterceptor.Level.HEADERS :
                HttpLoggingInterceptor.Level.NONE;
        logInterceptor.setLevel(level);

        return mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .addInterceptor(logInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    public static ProjectApplication getApplication() {
        return mApplication;
    }

    public static Context getAppContext() {
        return mApplication.getApplicationContext();
    }
}
