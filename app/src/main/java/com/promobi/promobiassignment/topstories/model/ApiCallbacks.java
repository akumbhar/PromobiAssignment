package com.promobi.promobiassignment.topstories.model;

import com.promobi.promobiassignment.network.entities.News;


public interface ApiCallbacks {

    public void onSuccess(News news);
    public void onError(String error);
}
