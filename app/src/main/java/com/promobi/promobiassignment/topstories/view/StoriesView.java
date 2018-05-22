package com.promobi.promobiassignment.topstories.view;

import com.promobi.promobiassignment.BaseView;
import com.promobi.promobiassignment.network.entities.News;

import java.util.List;

public interface StoriesView extends BaseView {

    public void showLoading();

    public void hideLoading();

    public void showToast(String text);

    public void showResults(List<News.Result> newsList);

    public void navigateToDetailsScreen(String url);
}
