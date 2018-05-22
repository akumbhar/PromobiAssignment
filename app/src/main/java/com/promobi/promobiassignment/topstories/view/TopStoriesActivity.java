package com.promobi.promobiassignment.topstories.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.promobi.promobiassignment.Constants;
import com.promobi.promobiassignment.NYApplication;
import com.promobi.promobiassignment.R;
import com.promobi.promobiassignment.databinding.ActivityTopStoriesBinding;
import com.promobi.promobiassignment.network.entities.News;
import com.promobi.promobiassignment.newsdetail.view.NewsDetailsActivity;
import com.promobi.promobiassignment.topstories.presenter.StoriesPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TopStoriesActivity extends AppCompatActivity implements StoriesView {

    private static final String TAG = TopStoriesActivity.class.getSimpleName();
    private ActivityTopStoriesBinding binding;

    @Inject
    StoriesPresenterImpl mPresenter;

    private TopStoriesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_top_stories);
        ((NYApplication) getApplicationContext()).getComponent().inject(this);
        mPresenter.onAttachView(this);
        mPresenter.getTopStories();


        //doSomething();

    }

    private void doSomething() {


        Observable<Integer> observable = Observable.
                create(new ObservableOnSubscribe<Integer>() {
                           @Override
                           public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                               //Use onNext to emit each item in the stream//

                               e.onNext(1);
                               SystemClock.sleep(1000);
                               e.onNext(2);
                               SystemClock.sleep(1000);
                               e.onNext(3);
                               SystemClock.sleep(1000);
                               e.onNext(4);

                               //Once the Observable has emitted all items in the sequence, call onComplete//
                               e.onComplete();
                               //e.onError(new Throwable("Some Error!!"));
                           }
                       }
                );



        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.e(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: All Done!");
            }
        };

//Create our subscription//
        observable.subscribe(observer);




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

    @Override
    public void showResults(final List<News.Result> newsList) {

        adapter = new TopStoriesAdapter(this, newsList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        binding.listStories.setLayoutManager(llm);
        binding.listStories.setAdapter(adapter);


        binding.listStories.addOnItemTouchListener(
                new RecyclerItemClickListener(this, binding.listStories, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        String url = newsList.get(position).getUrl();
                        navigateToDetailsScreen(url);

                    }

                })
        );


    }

    @Override
    public void navigateToDetailsScreen(String url) {

        Intent intent = new Intent(this, NewsDetailsActivity.class);
        intent.putExtra(Constants.INTENT_URL, url);
        startActivity(intent);


    }
}
