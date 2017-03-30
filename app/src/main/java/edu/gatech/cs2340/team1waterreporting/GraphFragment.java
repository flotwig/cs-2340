package edu.gatech.cs2340.team1waterreporting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import edu.gatech.cs2340.team1waterreporting.model.InMemoryDAO;
import edu.gatech.cs2340.team1waterreporting.model.Location;
import edu.gatech.cs2340.team1waterreporting.model.Model;
import edu.gatech.cs2340.team1waterreporting.model.UserInputException;
import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Trevor on 3/29/2017.
 */

public class GraphFragment extends Fragment {

    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mVirusContaminentPpm;
    private EditText mYear;

    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_graph_data, container, false);
        Button mSaveButton = (Button) view.findViewById(R.id.graph_enter);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSaveButton(v);
            }
        });

        mLatitude = (EditText) view.findViewById(R.id.graph_latitude);
        mLongitude = (EditText) view.findViewById(R.id.graph_longitude);
        mVirusContaminentPpm = (EditText) view.findViewById(R.id.graph_virus_or_contaminent);
        mYear = (EditText) view.findViewById(R.id.graph_year);
        GraphView graph = (GraphView) view.findViewById(R.id.graph1);
        graph.setVisibility(view.INVISIBLE);
        if(mSaveButton.isPressed()) {
            populateGraphView(view, graph);
            graph.setVisibility(view.VISIBLE);
        }

        return view;
    }

    private void populateGraphView(View view, GraphView graph) {

        DataPoint[] reports = new DataPoint[10];
        int i = 0;
        for (WaterPurityReport report : Model.getInstance().getWaterPurityReports()) {
            if (report.getDate().getYear() == Integer.parseInt(mYear.getText().toString())) {
                DataPoint r = new DataPoint(report.getDate(), report.getVirusPpm());
                reports[i] = r;
                i++;
            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(reports);
        graph.addSeries(series);
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
            WaterPurityReport.validatePpm(mVirusContaminentPpm.getText().toString());
        } catch (UserInputException e) {
            mVirusContaminentPpm.setError(e.getMessage());
            errorControl = mVirusContaminentPpm;
        }

        try {
            WaterPurityReport.validatePpm(mVirusContaminentPpm.getText().toString());
        } catch (UserInputException e) {
            mVirusContaminentPpm.setError(e.getMessage());
            errorControl = mVirusContaminentPpm;
        }
    }
}
