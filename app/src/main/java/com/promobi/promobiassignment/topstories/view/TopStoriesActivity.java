package com.promobi.promobiassignment.topstories.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.promobi.promobiassignment.R;
import com.promobi.promobiassignment.databinding.ActivityTopStoriesBinding;
import com.promobi.promobiassignment.topstories.presenter.StoriesPresenterImpl;

/**
 * @author Cybage
 */
public class TopStoriesActivity extends AppCompatActivity implements StoriesView {

    private ActivityTopStoriesBinding binding;

    StoriesPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_stories);
        doRetrofit();
        mPresenter = new StoriesPresenterImpl();
        mPresenter.onAttachView(this);
        mPresenter.getTopStories();

    }

    private void doRetrofit() {

         /* Call<News> call = new NetworkLayer().getTopStories();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {

                if (response.isSuccessful()) {

                    doLog("onResponse()..response.isSuccessful() : true" );

                } else {

                    doLog("onResponse()..response.isSuccessful() : false" );
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

                doLog("onFailure().." + t.getLocalizedMessage());
            }
        });*/
    }

    private void doLog(String message) {

        android.util.Log.e("TopStoriesActivity", message);
    }

    @Override
    public void showLoading() {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.txtLoading.setVisibility(View.VISIBLE);
        binding.listStories.setVisibility(View.GONE);


    }

    @Override
    public void hideLoading() {

        binding.progressBar.setVisibility(View.GONE);
        binding.txtLoading.setVisibility(View.GONE);
        binding.listStories.setVisibility(View.VISIBLE);

    }

    @Override
    public void showToast(String text) {


        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

    }
}
