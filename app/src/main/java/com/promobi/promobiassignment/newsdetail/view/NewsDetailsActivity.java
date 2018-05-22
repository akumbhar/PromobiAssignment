package com.promobi.promobiassignment.newsdetail.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.promobi.promobiassignment.Constants;
import com.promobi.promobiassignment.NYApplication;
import com.promobi.promobiassignment.R;
import com.promobi.promobiassignment.databinding.ActivityNewsDetailsBinding;
import com.promobi.promobiassignment.newsdetail.presenter.NewsDetailPresenterImpl;

import javax.inject.Inject;


public class NewsDetailsActivity extends AppCompatActivity implements NewsDetailView {


    ActivityNewsDetailsBinding binding;

    @Inject
    NewsDetailPresenterImpl mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_details);
        String url = getIntent().getStringExtra(Constants.INTENT_URL);
        ((NYApplication) getApplicationContext()).getComponent().inject(this);
        mPresenter.onAttachView(this);
        mPresenter.loadUrl(url);

    }

    @Override
    public void loadUrl(String url) {

        binding.webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setTitle("Loading...");
                setProgress(progress * 100);
                if (progress == 100)
                    setTitle(R.string.app_name);
            }
        });
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(url);

    }


}
