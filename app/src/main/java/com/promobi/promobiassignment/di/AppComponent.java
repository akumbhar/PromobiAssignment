package com.promobi.promobiassignment.di;

import com.promobi.promobiassignment.network.NetworkModule;
import com.promobi.promobiassignment.newsdetail.view.NewsDetailsActivity;
import com.promobi.promobiassignment.topstories.view.TopStoriesActivity;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(TopStoriesActivity activity);
    void inject(NewsDetailsActivity activity);

}
