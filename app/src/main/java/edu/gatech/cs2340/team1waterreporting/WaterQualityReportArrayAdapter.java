package edu.gatech.cs2340.team1waterreporting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;
import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;

/**
 * Adapter to translate an array of water source reports into a listview.
 */

public class WaterQualityReportArrayAdapter extends ArrayAdapter<WaterPurityReport> {
    public WaterQualityReportArrayAdapter(Context context, ArrayList<WaterPurityReport> waterPurityReports) {
        super(context, 0, waterPurityReports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaterPurityReport waterPurityReport = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_water_source_report, parent, false);
        }
        TextView mNumber = (TextView) convertView.findViewById(R.id.number_text);
        TextView mRow = (TextView) convertView.findViewById(R.id.row_text);

        mNumber.setText(Integer.toString(waterPurityReport.getNumber()));
        mRow.setText(String.format(
                "%s (%f virus PPM, %f contaminent PPM)\nby %s on %s\nLat: %f Lng: %f",
                waterPurityReport.getWaterCondition().toString(),
                waterPurityReport.getVirusPpm(),
                waterPurityReport.getContaminentPpm(),
                waterPurityReport.getReporter().getName(),
                waterPurityReport.getDate().toString(),
                waterPurityReport.getLocation().getLatitude(),
                waterPurityReport.getLocation().getLongitude()
        ));
        return convertView;
    }
}
