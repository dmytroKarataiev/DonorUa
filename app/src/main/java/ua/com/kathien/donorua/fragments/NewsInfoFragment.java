package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.URL;
import java.util.Date;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.News;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsInfoFragment extends Fragment {

    private News news;
    private TextView newsTestText;
    private Date datePublished;
    private Date lastUpdated;
    private URL image;
    private URL bigImage;
    private String shortDescription;
    private String title;
    private String description;

    public NewsInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_info, container, false);

        fillNewsItem();

        newsTestText = (TextView) view.findViewById(R.id.news_test_info);

        if(news != null && newsTestText != null) {
            newsTestText.setText(news.toString());
        }
        return view;
    }

    public void fillNewsItem() {
        news = getActivity().getIntent().getParcelableExtra(NewsListFragment.NEWS_EXTRA_TAG);
        datePublished = news.getDatePublished();
        lastUpdated = news.getLastUpdated();
        image = news.getImage();
        bigImage = news.getBigImage();
        shortDescription = news.getShortDescription();
        title = news.getTitle();
        description = news.getDescription();

    }
}
