package com.promobi.promobiassignment.topstories.presenter;

import com.promobi.promobiassignment.BaseView;
import com.promobi.promobiassignment.network.entities.News;
import com.promobi.promobiassignment.topstories.model.ApiCallbacks;
import com.promobi.promobiassignment.topstories.model.TopStoriesInteractorImpl;
import com.promobi.promobiassignment.topstories.view.StoriesView;

import javax.inject.Inject;

public class StoriesPresenterImpl implements StoriesPresenter, ApiCallbacks {

    private static final int SPLASH_TIME_OUT = 3 * 1000;
    private StoriesView mView;

    private TopStoriesInteractorImpl interactor;

    @Inject
    StoriesPresenterImpl(TopStoriesInteractorImpl interactor) {

        this.interactor = interactor;
    }

    @Override
    public void getTopStories() {

        mView.showLoading();
        interactor.loadData(this);

    }


    @Override
    public void onAttachView(BaseView baseView) {
        mView = (StoriesView) baseView;
    }

    @Override
    public void onDetach() {

        mView = null;
    }

    @Override
    public void onSuccess(News news) {

        mView.hideLoading();
        if (news != null && news.getNumResults() > 0) {
            mView.showResults(news.getResults());
        } else {
            onError("No results found. Please try again");
        }

    }

    private void doLog(String message) {

        android.util.Log.e(" StoriesPresenterImpl ", message);
    }

    @Override
    public void onError(String error) {

        mView.hideLoading();
        mView.showToast(error);


    }
}
