package ua.com.kathien.donorua.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import ua.com.kathien.donorua.R;
import ua.com.kathien.donorua.models.Center;

/**
 * A placeholder fragment containing a simple view.
 */
public class CenterMapFragment extends Fragment {

    private MapView mMapView;
    private ArrayList<Center> centers;
    private GoogleMap googleMap;
    private static final int ONE_CENTER_CENTERED = 13;
    private static final int UKRAINE_CENTERED = 5;
    private static final String LOG_TAG = CenterMapFragment.class.getSimpleName();

    public CenterMapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_center_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        centers = new ArrayList<>();

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);

                centers = getActivity().getIntent().getParcelableArrayListExtra("OurCenters");

                for(Center center: centers) {
                    LatLng centerLatLng = new LatLng(center.getLoc().getLatitude(), center.getLoc().getLongitude());

                    googleMap.addMarker(new MarkerOptions()
                            .title(center.getName())
                            .snippet(center.getStreet())
                            .position(centerLatLng));

                }

                LatLng centerLatLng = new LatLng(centers.get(0).getLoc().getLatitude(), centers.get(0).getLoc().getLongitude());
                int zoomValue;
                if(centers.size() == 1) {
                    zoomValue = ONE_CENTER_CENTERED;
                } else {
                    zoomValue = UKRAINE_CENTERED;
                }

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, zoomValue));


            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
