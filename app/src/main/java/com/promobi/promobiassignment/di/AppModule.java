package com.promobi.promobiassignment.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {

        mContext = context;
    }

    @Provides
    Context getApplicationContext() {

        return mContext.getApplicationContext();
    }
}
