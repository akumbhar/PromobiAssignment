package com.promobi.promobiassignment.topstories.model;

import android.content.Context;
import android.util.Log;

import com.promobi.promobiassignment.network.NYApi;
import com.promobi.promobiassignment.network.entities.News;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopStoriesInteractorImpl implements APIInteractor {

    @Inject
    NYApi service;

    @Inject
    @Named("key")
    String apiKey;

    Context context;

    ApiCallbacks apiCallbacks;

    @Inject
    public TopStoriesInteractorImpl(Context context) {

        this.context = context;
    }

    @Override
    public void loadData(ApiCallbacks callbacks) {

        this.apiCallbacks = callbacks;


        Observable<News> call = new NetworkLayer().getTopStories(apiKey);


        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsData -> {
                    apiCallbacks.onSuccess(newsData);
                }, error -> {

                    apiCallbacks.onError(error.getMessage());
                });




    }
}
