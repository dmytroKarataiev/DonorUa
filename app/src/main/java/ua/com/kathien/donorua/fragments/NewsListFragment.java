package ua.com.kathien.donorua.fragments;

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
import ua.com.kathien.donorua.models.News;
import ua.com.kathien.donorua.utils.DividerItemDecoration;
import ua.com.kathien.donorua.views.adapters.NewsAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsListFragment extends Fragment {

    private ArrayList<News> news;
    private RecyclerView newsRecyclerView;
    private static final String LOG_TAG = NewsListFragment.class.getSimpleName();

    public NewsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        news = getActivity().getIntent().getParcelableArrayListExtra("news");
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler_view);

        if(newsRecyclerView == null) {
            Log.v(LOG_TAG, "newsRecyclerView is null");
            return view;
        }

        newsRecyclerView.setHasFixedSize(true);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        newsRecyclerView.addItemDecoration(itemDecoration);
        newsRecyclerView.setAdapter(new NewsAdapter(news));

        return view;
    }
}
