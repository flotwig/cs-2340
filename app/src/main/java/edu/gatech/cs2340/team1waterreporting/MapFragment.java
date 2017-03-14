package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.User;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.UserRole;
import edu.gatech.cs2340.team1waterreporting.model.WaterCondition;
import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;
import edu.gatech.cs2340.team1waterreporting.model.WaterType;


/**
 * A fragment which handles the editing of a user object.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mMap;
    FloatingActionButton mNewButton;

    /**
     * Creates an empty ListWaterReportsFragment.
     */
    public MapFragment() {

    }

    /**
     * New instance of list of water reports.
     *
     * @return A new instance of fragment ListWaterReportsFragment.
     */
    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_map));

        mNewButton = (FloatingActionButton) v.findViewById(R.id.fab);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewButton(v);
            }
        });

        mMap = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMap.getMapAsync(this);

        return v;
    }

    /**
     * onClick handler that will transition to the form to create a new
     * water report.
     * @param v New button
     */
    public void onClickNewButton(View v) {
        ((MainDrawerActivity) getActivity()).switchFragment(NewWaterReportFragment.newInstance());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // populate mMap with markers
        List<WaterSourceReport> waterSourceReports = Model.getInstance().getWaterSourceReports();
        for (WaterSourceReport waterSourceReport : waterSourceReports) {
            LatLng location = new LatLng(waterSourceReport.getLocation().getLatitude(),
                    waterSourceReport.getLocation().getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(waterSourceReport.getWaterCondition().toString())
                    .visible(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)))
                    .showInfoWindow();
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}
