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

import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;

// Commenting out this import due to it not being used. Leaving it here in case that changes
// import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;

/**
 * Fragment to control and display graphs of contaminent PPM over time.
 */

public class GraphFragment extends Fragment {

    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mSearchRadiusMeters;
    private EditText mYear;
    private GraphView mGraph;

    private boolean doVirusPpm = false;

    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_data, container, false);
        Button mSaveButton = (Button) view.findViewById(R.id.graph_enter_contaminent);
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
        mGraph.setVisibility(view.INVISIBLE);

        return view;
    }

    private void populateGraphView(GraphView graph) {
        DataPoint[] reports = new DataPoint[10];
        List<WaterPurityReport> targetReports = Model.getInstance().getWaterPurityReportsByLocationYear(
                new Location(Float.parseFloat(mLatitude.getText().toString()), Float.parseFloat(mLongitude.getText().toString())),
                Float.parseFloat(mSearchRadiusMeters.getText().toString()),
                Integer.parseInt(mYear.getText().toString()));
        targetReports = Model.getInstance().getWaterPurityReports();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        LinkedList<Integer>[] months = new LinkedList[12];
        for (int i = 0; i < months.length; i++) {
            months[i] = new LinkedList<>();
        }
        for (WaterPurityReport waterPurityReport : targetReports) {
            months[waterPurityReport.getDate().getMonth()].add((int) ((doVirusPpm) ? waterPurityReport.getVirusPpm() : waterPurityReport.getContaminentPpm()));
        }
        int cMonth = 1;
        for (LinkedList<Integer> month : months) {
            int average = 0;
            for (int ppm : month) {
                average += ppm;
            }
            if (month.size() > 0) {
                average /= month.size();
                series.appendData(new DataPoint(cMonth, average), false, months.length);
            }
            cMonth++;
        }
        graph.getGridLabelRenderer().setNumHorizontalLabels(12);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Month");
        graph.getGridLabelRenderer().setVerticalAxisTitle("PPM");
        graph.removeAllSeries();
        graph.addSeries(series);
    }

    /**
     * onClick handler that will generate the appropriate graph.
     * @param v Save button
     */
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

        if (errorControl != null) {
            errorControl.requestFocus();
        } else {
            populateGraphView(mGraph);
            mGraph.setVisibility(View.VISIBLE);
        }
    }
}
