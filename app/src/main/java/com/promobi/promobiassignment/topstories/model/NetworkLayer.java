package com.promobi.promobiassignment.topstories.model;

import com.promobi.promobiassignment.network.NYApi;
import com.promobi.promobiassignment.network.entities.News;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Cybage
 */
public class NetworkLayer {

    String API_URL = "https://api.nytimes.com/";


    NYApi api;

    public NetworkLayer() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(NYApi.class);
    }

    public Call<News> getTopStories() {

        return api.getTopStories("6dd57d27afbf4a689d74d4e747bfdcfe");

    }

}
