package edu.gatech.cs2340.team1waterreporting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;

/**
 * Adapter to translate an array of water source reports into a listview.
 */

public class WaterSourceReportArrayAdapter extends ArrayAdapter<WaterSourceReport> {
    public WaterSourceReportArrayAdapter(Context context, ArrayList<WaterSourceReport> waterSourceReports) {
        super(context, 0, waterSourceReports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WaterSourceReport waterSourceReport = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_water_source_report, parent, false);
        }
        TextView mNumber = (TextView) convertView.findViewById(R.id.number_text);
        TextView mRow = (TextView) convertView.findViewById(R.id.row_text);

        mNumber.setText(Integer.toString(waterSourceReport.getNumber()));
        mRow.setText(String.format(
                "%s, %s at %.3f, %.3f\n%s by %s",
                waterSourceReport.getWaterType().toString(),
                waterSourceReport.getWaterCondition().toString(),
                waterSourceReport.getLocation().getLatitude(),
                waterSourceReport.getLocation().getLongitude(),
                waterSourceReport.getDate().toString(),
                waterSourceReport.getReporter().getName()
        ));

        return convertView;
    }
}