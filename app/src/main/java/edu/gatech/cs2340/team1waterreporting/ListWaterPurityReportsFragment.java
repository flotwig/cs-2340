package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import edu.gatech.cs2340.team1waterreporting.model.Model;


/**
 * A fragment which handles the editing of a user object.
 */
public class ListWaterPurityReportsFragment extends Fragment {

    private ListView mList;
    private FloatingActionButton mNewButton;

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
        return new ListWaterPurityReportsFragment();
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
    private void onClickNewButton(View v) {
        ((MainDrawerActivity) getActivity()).switchFragment(NewWaterQualityReportFragment.newInstance());
    }
}
