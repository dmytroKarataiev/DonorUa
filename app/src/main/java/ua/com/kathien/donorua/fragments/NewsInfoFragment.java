package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.kathien.donorua.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsInfoFragment extends Fragment {

    public NewsInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_info, container, false);
    }
}