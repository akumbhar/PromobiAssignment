package com.promobi.promobiassignment.network;

import com.promobi.promobiassignment.network.entities.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Cybage
 */
public interface NYApi {


    @GET("/svc/topstories/v2/home.json")
    Call<News> getTopStories(@Query("api-key") String apiKey);
}
