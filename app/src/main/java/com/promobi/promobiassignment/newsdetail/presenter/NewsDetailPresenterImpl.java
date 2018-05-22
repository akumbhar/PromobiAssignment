package com.promobi.promobiassignment.newsdetail.presenter;

import com.promobi.promobiassignment.BaseView;
import com.promobi.promobiassignment.newsdetail.view.NewsDetailView;

import javax.inject.Inject;

/**
 * @author Cybage
 */
public class NewsDetailPresenterImpl implements NewsDetailPresenter {


    NewsDetailView mView;

    @Inject
    public NewsDetailPresenterImpl() {

    }


    @Override
    public void loadUrl(String url) {

        mView.loadUrl(url);


    }

    @Override
    public void onAttachView(BaseView baseView) {
        mView = (NewsDetailView) baseView;

    }

    @Override
    public void onDetach() {

    }
}
