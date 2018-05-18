package com.promobi.promobiassignment;

/**
 * @author Cybage
 */
public interface BasePresenter<T extends BaseView> {

    public void onAttachView(T baseView);
    public void onDetach();
}
