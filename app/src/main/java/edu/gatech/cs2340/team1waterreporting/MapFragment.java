package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;


/**
 * A fragment which handles the editing of a user object.
 */
@SuppressWarnings("ChainedMethodCall")
public class MapFragment extends Fragment implements OnMapReadyCallback {

    /**
     * New instance of list of water reports.
     *
     * @return A new instance of fragment ListWaterReportsFragment.
     */
    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_map));

        FloatingActionButton mNewButton = (FloatingActionButton) v.findViewById(R.id.fab);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewButton(v);
            }
        });

        SupportMapFragment mMap =
            (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mMap.getMapAsync(this);

        return v;
    }

    /**
     * onClick handler that will transition to the form to create a new
     * water report.
     * @param v New button
     */
    @SuppressWarnings("UnusedParameters")
    private void onClickNewButton(View v) {
        ((MainDrawerActivity) getActivity()).switchFragment(NewWaterReportFragment.newInstance());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // populate mMap with markers
        List<WaterSourceReport> waterSourceReports = Model.getInstance().getWaterSourceReports();
        for (WaterSourceReport waterSourceReport : waterSourceReports) {
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(waterSourceReport.getLocation().toLatLng())
                    .title(String.format(Locale.ENGLISH, "%d: %s, %s",
                            waterSourceReport.getNumber(),
                            waterSourceReport.getWaterCondition(),
                            waterSourceReport.getWaterType()))
                    .snippet(String.format(Locale.ENGLISH, "By %s at %s.",
                            waterSourceReport.getReporter().getName(),
                            waterSourceReport.getDate().toString())));
            marker.setTag(waterSourceReport);
            marker.setVisible(true);
            marker.showInfoWindow();
            googleMap.moveCamera(CameraUpdateFactory.
                newLatLng(waterSourceReport.getLocation().toLatLng()));
        }
    }
}
