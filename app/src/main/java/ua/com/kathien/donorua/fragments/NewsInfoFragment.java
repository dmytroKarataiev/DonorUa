package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.News;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsInfoFragment extends Fragment {

    private News news;
    private TextView newsTestText;

    public NewsInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_info, container, false);

        news = getActivity().getIntent().getParcelableExtra(NewsListFragment.NEWS_EXTRA_TAG);
        newsTestText = (TextView) view.findViewById(R.id.news_test_info);

        if(news != null && newsTestText != null) {
            newsTestText.setText(news.toString());
        }

        return view;
    }
}
