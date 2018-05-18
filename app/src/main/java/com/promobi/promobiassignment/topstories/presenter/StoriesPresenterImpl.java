package com.promobi.promobiassignment.topstories.presenter;

import com.promobi.promobiassignment.BaseView;
import com.promobi.promobiassignment.topstories.view.StoriesView;

import android.os.Handler;

/**
 * @author Cybage
 */
public class StoriesPresenterImpl implements StoriesPresenter {

    private static final int  SPLASH_TIME_OUT = 3 * 1000;
    StoriesView mView;

    @Override
    public void getTopStories() {

        mView.showLoading();

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {

                mView.hideLoading();

            }
        }, SPLASH_TIME_OUT);




    }


    @Override
    public void onAttachView(BaseView baseView) {
        mView = (StoriesView) baseView;
    }

    @Override
    public void onDetach() {

        mView = null;
    }
}
