package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;

/**
 * Fragment to control and display graphs of contaminant PPM over time.
 */

@SuppressWarnings({"unchecked", "ChainedMethodCall"})
public class GraphFragment extends Fragment {
    private static final int MONTHS_IN_YEAR = 12;

    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mSearchRadiusMeters;
    private EditText mYear;
    private GraphView mGraph;

    private boolean doVirusPpm = false;

    /**
     * Returns a new instance of the graph fragment.
     * @return GraphFragment
     */
    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_data, container, false);
        Button mSaveButton = (Button) view.findViewById(R.id.graph_enter_contaminant);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVirusPpm = false;
                onClickViewButton(v);
            }
        });

        mSaveButton = (Button) view.findViewById(R.id.graph_enter_virus);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doVirusPpm = true;
                onClickViewButton(v);
            }
        });


        mLatitude = (EditText) view.findViewById(R.id.graph_latitude);
        mLongitude = (EditText) view.findViewById(R.id.graph_longitude);
        mSearchRadiusMeters = (EditText) view.findViewById(R.id.graph_search_radius_meters);
        mYear = (EditText) view.findViewById(R.id.graph_year);
        mGraph = (GraphView) view.findViewById(R.id.graphView);
        mGraph.setVisibility(View.INVISIBLE);

        return view;
    }

    private void populateGraphView(GraphView graph) {
        List<WaterPurityReport> targetReports = Model.getInstance().getWaterPurityReports();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        LinkedList<Integer>[] months = new LinkedList[MONTHS_IN_YEAR];
        for (int i = 0; i < months.length; i++) {
            months[i] = new LinkedList<>();
        }
        for (WaterPurityReport waterPurityReport : targetReports) {
            months[waterPurityReport.getDate().getMonth()].add((int) ((doVirusPpm)
                ? waterPurityReport.getVirusPpm() : waterPurityReport.getContaminantPpm()));
        }
        int cMonth = 1;
        for (Deque<Integer> month : months) {
            int average = 0;
            for (int ppm : month) {
                average += ppm;
            }
            if (month.isEmpty()) {
                average /= month.size();
                series.appendData(new DataPoint(cMonth, average), false, months.length);
            }
            cMonth++;
        }
        graph.getGridLabelRenderer().setNumHorizontalLabels(MONTHS_IN_YEAR);
        graph.getGridLabelRenderer().setHorizontalAxisTitle(getString(R.string.month));
        graph.getGridLabelRenderer().setVerticalAxisTitle(getString(R.string.ppm));
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    /**
     * onClick handler that will generate the appropriate graph.
     * @param v Save button
     */
    @SuppressWarnings("UnusedParameters")
    private void onClickViewButton(View v) {
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

        float srm;
        try {
            srm = Float.parseFloat(mSearchRadiusMeters.getText().toString());
            if (srm <= 0) {
                mSearchRadiusMeters.setError(getString(R.string.error_search_radius_lte0));
                errorControl = mSearchRadiusMeters;
            }
        } catch (NumberFormatException e) {
            errorControl = mSearchRadiusMeters;
            mSearchRadiusMeters.setError(getString(R.string.error_search_radius_nan));
        }

        int year;
        try {
            year = Integer.parseInt(mYear.getText().toString());
            if ((year <= 0) || (year > (new Date()).getYear())) {
                mYear.setError(getString(R.string.error_year_bad));
                errorControl = mYear;
            }
        } catch (NumberFormatException e) {
            errorControl = mYear;
            mYear.setError(getString(R.string.error_year_nan));
        }

        if (errorControl != null) {
            errorControl.requestFocus();
        } else {
            populateGraphView(mGraph);
            mGraph.setVisibility(View.VISIBLE);
        }
    }
}
