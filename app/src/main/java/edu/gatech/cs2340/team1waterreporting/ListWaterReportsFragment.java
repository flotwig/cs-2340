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
@SuppressWarnings("ALL")
public class ListWaterReportsFragment extends Fragment {

    /**
     * New instance of list of water reports.
     *
     * @return A new instance of fragment ListWaterReportsFragment.
     */
    public static ListWaterReportsFragment newInstance() {
        return new ListWaterReportsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_water_reports, container, false);

        getActivity().setTitle(getString(R.string.title_fragment_list_water_reports));

        FloatingActionButton mNewButton = (FloatingActionButton) v.findViewById(R.id.fab);
        mNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNewButton(v);
            }
        });

        ListView mList = (ListView) v.findViewById(R.id.list);
        mList.setAdapter(new WaterSourceReportArrayAdapter(getContext(),
            (ArrayList) Model.getInstance().getWaterSourceReports()));

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
}
