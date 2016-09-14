package ua.com.kathien.donorua.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    private static final int MY_PERMISSIONS_FINE_LOCATION = 2;
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

                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);

                switch(permissionCheck) {
                    case PackageManager.PERMISSION_GRANTED:
                        googleMap.setMyLocationEnabled(true);
                        break;

                    case PackageManager.PERMISSION_DENIED:
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_FINE_LOCATION);
                        break;

                    default:
                        Log.i(LOG_TAG, "ACCESS_FINE_LOCATION unexpected permission check result");
                        break;
                }

       //         googleMap.setMyLocationEnabled(true);

                centers = getActivity().getIntent().getParcelableArrayListExtra("OurCenters");

                for (Center center : centers) {
                    LatLng centerLatLng = new LatLng(center.getLoc().getLatitude(), center.getLoc().getLongitude());

                    googleMap.addMarker(new MarkerOptions()
                            .title(center.getName())
                            .snippet(center.getStreet())
                            .position(centerLatLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

                }

                LatLng centerLatLng = new LatLng(centers.get(0).getLoc().getLatitude(), centers.get(0).getLoc().getLongitude());
                int zoomValue;
                if (centers.size() == 1) {
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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch(requestCode) {
            case MY_PERMISSIONS_FINE_LOCATION:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    googleMap.setMyLocationEnabled(true);
                } else {
                    googleMap.setMyLocationEnabled(false);
                    /*Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Call permission needed", Snackbar.LENGTH_LONG);
                    snackbar.show();*/
                }
                break;
            default:
                Log.e(LOG_TAG, "Unknown permission request code");
                break;
        }
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
