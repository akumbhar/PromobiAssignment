package com.promobi.promobiassignment.topstories.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.promobi.promobiassignment.R;
import com.promobi.promobiassignment.network.entities.News;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.NewsViewHolder> {


    private List<News.Result> news;
    private Context mContext;

    public TopStoriesAdapter(Context context, List<News.Result> news) {
        this.news = news;
        mContext = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_stories_row, parent, false);

        return new NewsViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News.Result status = news.get(position);

        holder.txtTitle.setText(status.getTitle());
        holder.txtPublishDate.setText(status.getPublishedDate());
        List<News.Multimedium> multimedia = status.getMultimedia();
        for (News.Multimedium eachMedia : multimedia) {

            if (eachMedia.getFormat().equals("superJumbo")) {
                String url = eachMedia.getUrl();
                Glide.with(mContext).load(url).into(holder.imgNews);
            }

        }

    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public TextView txtPublishDate;
        public ImageView imgNews;


        public NewsViewHolder(View view) {
            super(view);
            txtTitle = (TextView) view.findViewById(R.id.tvTitle);
            txtPublishDate = (TextView) view.findViewById(R.id.tvPubDate);
            imgNews = (ImageView) view.findViewById(R.id.ivThumb);

        }


    }


}
