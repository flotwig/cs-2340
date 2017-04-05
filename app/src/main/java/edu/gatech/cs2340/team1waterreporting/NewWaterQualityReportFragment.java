package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.WaterCondition;
import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;


/**
 * A fragment which handles the editing of a user object.
 */
public class NewWaterQualityReportFragment extends Fragment {
    private Spinner mWaterCondition;
    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mVirusPpm;
    private EditText mContaminantsPpm;

    /**
     * Creates an empty NewWaterReportFragment.
     */
    public NewWaterQualityReportFragment() {

    }

    /**
     * New instance of form to edit a user.
     *
     * @return A new instance of fragment NewWaterQualityReportFragment.
     */
    public static NewWaterQualityReportFragment newInstance() {
        return new NewWaterQualityReportFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_water_quality_report, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_new_water_quality_report));

        Button mSaveButton = (Button) v.findViewById(R.id.new_water_report_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveButton(v);
            }
        });

        mLatitude = (EditText) v.findViewById(R.id.new_water_report_latitude);
        mLongitude = (EditText) v.findViewById(R.id.new_water_report_longitude);
        mVirusPpm = (EditText) v.findViewById(R.id.new_water_report_virusppm);
        mContaminantsPpm = (EditText) v.findViewById(R.id.new_water_report_contaminant_ppm);

        mWaterCondition = (Spinner) v.findViewById(R.id.new_water_report_water_condition_spinner);

        ArrayAdapter standings = new ArrayAdapter(super.getContext(),android.R.layout.simple_spinner_item, WaterCondition.values());
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
    private void onClickSaveButton(View v) {
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

        try {
            WaterPurityReport.validatePpm(mVirusPpm.getText().toString());
        } catch (UserInputException e) {
            mVirusPpm.setError(e.getMessage());
            errorControl = mVirusPpm;
        }

        try {
            WaterPurityReport.validatePpm(mContaminantsPpm.getText().toString());
        } catch (UserInputException e) {
            mContaminantsPpm.setError(e.getMessage());
            errorControl = mContaminantsPpm;
        }

        if (errorControl != null) {
            errorControl.requestFocus();
        } else {
            WaterPurityReport waterPurityReport = new WaterPurityReport(
                    Model.getInstance().getNewWaterPurityReportId(),
                    Model.getInstance().getCurrentUser(),
                    new Location(
                            Float.parseFloat(mLatitude.getText().toString()),
                            Float.parseFloat(mLongitude.getText().toString())
                    ),
                    (WaterCondition) mWaterCondition.getSelectedItem(),
                    Float.parseFloat(mVirusPpm.getText().toString()),
                    Float.parseFloat(mContaminantsPpm.getText().toString())
            );
            Model.getInstance().addWaterPurityReport(waterPurityReport);
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
