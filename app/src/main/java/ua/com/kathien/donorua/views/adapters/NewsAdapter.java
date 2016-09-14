package ua.com.kathien.donorua.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.News;

/**
 * Created by kathien on 8/29/16.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> newsList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView newsTitleTextView;
        public TextView newsTextTextView;

        public ViewHolder(View v) {
            super(v);

            newsTitleTextView = (TextView) v.findViewById(R.id.text_news_title);
            newsTextTextView = (TextView) v.findViewById(R.id.text_news_content);
        }

    }


    public NewsAdapter(ArrayList<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_row, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        News newsItem = newsList.get(position);

        holder.newsTitleTextView.setText(newsItem.getTitle());
        holder.newsTextTextView.setText(newsItem.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }



}
