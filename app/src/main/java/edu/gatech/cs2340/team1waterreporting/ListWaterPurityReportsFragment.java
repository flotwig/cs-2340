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

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ListWaterPurityReportsFragment extends Fragment {

    ListView mList;
    FloatingActionButton mNewButton;

    /**
     * Creates an empty ListWaterPurityReportsFragment.
     */
    public ListWaterPurityReportsFragment() {

    }

    /**
     * New instance of list of water purity reports.
     *
     * @return A new instance of fragment ListWaterPurityReportsFragment.
     */
    public static ListWaterPurityReportsFragment newInstance() {
        ListWaterPurityReportsFragment fragment = new ListWaterPurityReportsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_water_reports, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_list_water_reports));

        mNewButton = (FloatingActionButton) v.findViewById(R.id.fab);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewButton(v);
            }
        });

        mList = (ListView) v.findViewById(R.id.list);
        mList.setAdapter(new WaterQualityReportArrayAdapter(getContext(), (ArrayList) Model.getInstance().getWaterPurityReports()));

        return v;
    }

    /**
     * onClick handler that will transition to the form to create a new
     * water quality report.
     * @param v New button
     */
    public void onClickNewButton(View v) {
        ((MainDrawerActivity) getActivity()).switchFragment(NewWaterQualityReportFragment.newInstance());
    }
}
