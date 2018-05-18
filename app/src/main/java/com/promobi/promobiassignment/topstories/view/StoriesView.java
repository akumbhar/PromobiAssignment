package com.promobi.promobiassignment.topstories.view;

import com.promobi.promobiassignment.BaseView;

/**
 * @author Cybage
 */
public interface StoriesView extends BaseView {

    public void showLoading();

    public void hideLoading();

    public void showToast(String text);
}
