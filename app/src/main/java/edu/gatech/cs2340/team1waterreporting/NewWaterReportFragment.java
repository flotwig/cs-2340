package edu.gatech.cs2340.team1waterreporting;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

import java.io.FileDescriptor;
import java.io.PrintWriter;
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
public class NewWaterReportFragment extends Fragment {
    private Spinner mWaterType;
    private Spinner mWaterCondition;
    private EditText mLatitude;
    private EditText mLongitude;

    /**
     * Creates an empty NewWaterReportFragment.
     */
    public NewWaterReportFragment() {

    }

    /**
     * New instance of form to edit a user.
     *
     * @return A new instance of fragment EditProfileFragment.
     */
    public static NewWaterReportFragment newInstance() {
        NewWaterReportFragment fragment = new NewWaterReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_water_report, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_new_water_report));

        Button mSaveButton = (Button) v.findViewById(R.id.new_water_report_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveButton(v);
            }
        });

        mLatitude = (EditText) v.findViewById(R.id.new_water_report_latitude);
        mLongitude = (EditText) v.findViewById(R.id.new_water_report_longitude);

        mWaterType = (Spinner) v.findViewById(R.id.new_water_report_water_type_spinner);
        mWaterCondition = (Spinner) v.findViewById(R.id.new_water_report_water_condition_spinner);

        ArrayAdapter<UserRole> standings = new ArrayAdapter(super.getContext(),android.R.layout.simple_spinner_item, WaterType.values());
        standings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWaterType.setAdapter(standings);

        standings = new ArrayAdapter(super.getContext(),android.R.layout.simple_spinner_item, WaterCondition.values());
        standings.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mWaterCondition.setAdapter(standings);

        return v;
    }

    /**
     * onClick handler that will save the new water report and
     * transition to the list view or alert the user to any input
     * validation errors.
     * @param v Save button
     */
    public void onClickSaveButton(View v) {
        EditText errorControl = null;

        try {
            Location.validateLatitude(mLatitude.getText().toString());
        } catch (UserInputException e) {
            mLatitude.setError(e.getMessage());
            errorControl = mLatitude;
        }

        try {
            Location.validateLongitude(mLongitude.getText().toString());
        } catch (UserInputException e) {
            mLongitude.setError(e.getMessage());
            errorControl = mLongitude;
        }

        if (errorControl != null) {
            errorControl.requestFocus();
        } else {
            WaterSourceReport waterSourceReport = new WaterSourceReport(
                    Model.getInstance().getNewWaterSourceReportId(),
                    Model.getInstance().getCurrentUser(),
                    new Location(
                            Float.parseFloat(mLatitude.getText().toString()),
                            Float.parseFloat(mLongitude.getText().toString())
                    ),
                    (WaterType) mWaterType.getSelectedItem(),
                    (WaterCondition) mWaterCondition.getSelectedItem()
            );
            Model.getInstance().addWaterSourceReport(waterSourceReport);
            ((MainDrawerActivity) getActivity()).switchFragment(ListWaterReportsFragment.newInstance());
        }
    }

    private Location getCurrentLocation() {
        /*GoogleApiClient googleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addApi(LocationServices.api)
                .build();*/
        return new Location(0,0);
    }
}
