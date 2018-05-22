package com.promobi.promobiassignment.network;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    String API_URL = "https://api.nytimes.com/";
    String API_KEY = "6dd57d27afbf4a689d74d4e747bfdcfe";


    @Provides
    @Named("url")
    String getAppUrl() {
        return API_URL;
    }


    @Provides
    @Named("key")
    String getApiKey() {
        return API_KEY;
    }

    @Provides
    HttpLoggingInterceptor getLogginInterceptor() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Provides
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor interceptor) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        return httpClient.build();

    }


    @Provides
    GsonConverterFactory getGsonConverterFactory() {

        return GsonConverterFactory.create();
    }


    @Provides
    Retrofit getRetrofit(OkHttpClient client, GsonConverterFactory factory, @Named("url") String apiUrl) {

        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(factory)
                .client(client)
                .build();

    }


    @Provides
    NYApi getAPIService(Retrofit retrofit) {

        return retrofit.create(NYApi.class);
    }


}
