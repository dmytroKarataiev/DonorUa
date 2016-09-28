package ua.com.kathien.donorua.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.activities.NewsInfoActivity;
import ua.com.kathien.donorua.activities.NewsListActivity;
import ua.com.kathien.donorua.models.News;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.utils.ItemClickSupport;
import ua.com.kathien.donorua.utils.NewsParser;
import ua.com.kathien.donorua.utils.OnlineHelper;
import ua.com.kathien.donorua.views.adapters.NewsAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsListFragment extends Fragment {

    private ArrayList<News> news;
    private RecyclerView newsRecyclerView;
    private boolean newsDownloaded;
    private NewsAdapter newsAdapter;
    private static final String LOG_TAG = NewsListFragment.class.getSimpleName();
    public static final String NEWS_EXTRA_TAG = "news";

    public NewsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        initComponents();

        newsRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);
        if(newsRecyclerView == null) {
            Log.v(LOG_TAG, "newsRecyclerView is null");
            return view;
        }
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        newsRecyclerView.addItemDecoration(itemDecoration);

        NewsShower newsShower = new NewsShower();
        newsShower.execute();

        ItemClickSupport.addTo(newsRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){
           @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               News newsClicked = news.get(position);

               Intent newsInfoIntent = new Intent(getActivity(), NewsInfoActivity.class);
               newsInfoIntent.putExtra(NEWS_EXTRA_TAG, newsClicked);
               startActivity(newsInfoIntent);
           }
        });

        return view;
    }



    private class NewsShower extends AsyncTask<Void, Void, ArrayList<News>> {
        public NewsShower() {
            super();
        }

        @Override
        protected ArrayList<News> doInBackground(Void... params) {
            news = parseAllNews();
            return news;
        }

        @Override
        protected void onPostExecute(ArrayList<News> result) {
            if (result == null) {
                Log.v(LOG_TAG, "news list is null");
                return;
            }

            newsAdapter = new NewsAdapter(result);
            newsRecyclerView.setAdapter(newsAdapter);

        }
    }

    private ArrayList<News> parseAllNews() {
        if (newsDownloaded) {
            return news;
        }
        if (!OnlineHelper.isOnline(getActivity())) {
            return null;
        }
        NewsParser newsParser = new NewsParser();
        newsParser.parseNews(news);
        newsDownloaded = true;

        return news;
    }

    public void initComponents() {
        news = new ArrayList<News>();
        newsDownloaded = false;
    }

}
