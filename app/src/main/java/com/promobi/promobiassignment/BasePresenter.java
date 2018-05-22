package com.promobi.promobiassignment;


public interface BasePresenter<T extends BaseView> {

    public void onAttachView(T baseView);
    public void onDetach();
}
