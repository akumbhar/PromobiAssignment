package com.promobi.promobiassignment;

import android.app.Application;

import com.promobi.promobiassignment.di.AppComponent;
import com.promobi.promobiassignment.di.AppModule;
import com.promobi.promobiassignment.di.DaggerAppComponent;

public class NYApplication extends Application {

    private AppComponent mComponent;

    public AppComponent getComponent() {
        return mComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mComponent = DaggerAppComponent.
                builder()
                .appModule(new AppModule(this))
                .build();

    }
}
