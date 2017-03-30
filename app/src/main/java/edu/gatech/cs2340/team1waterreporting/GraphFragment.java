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

import edu.gatech.cs2340.team1waterreporting.model.Location;

/**
 * Created by Trevor on 3/29/2017.
 */

public class GraphFragment extends Fragment {

    private EditText mLatitude;
    private EditText mLongitude;
    private EditText mVirusPpm;
    private EditText mContaminentsPpm;

    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*View view = inflater.inflate(R.layout.fragment_graph_data, container, false);
        Button mSaveButton = (Button) view.findViewById(R.id.new_water_report_save_button);
        mLatitude = (EditText) view.findViewById(R.id.new_water_report_latitude);
        mLongitude = (EditText) view.findViewById(R.id.new_water_report_longitude);
        mVirusPpm = (EditText) view.findViewById(R.id.new_water_report_virusppm);
        if(mSaveButton.isPressed()) {*/
            View view = inflater.inflate(R.layout.fragment_graph, container, false);
            populateGraphView(view);
        //}

        return view;
    }

    private void populateGraphView(View view) {

        GraphView graph = (GraphView) view.findViewById(R.id.graph1);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
    }
}
