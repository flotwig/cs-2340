package edu.gatech.cs2340.team1waterreporting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.team1waterreporting.model.WaterSourceReport;

/**
 * Adapter to translate an array of water source reports into a ListView.
 */

class WaterSourceReportArrayAdapter extends ArrayAdapter<WaterSourceReport> {
    /**
     * Constructs a new WaterSourceReportArrayAdapter
     * @param context context to operate in
     * @param waterSourceReports WaterSourceReport objects to show
     */
    public WaterSourceReportArrayAdapter(Context context,
        List<WaterSourceReport> waterSourceReports) {

        super(context, 0, waterSourceReports);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        WaterSourceReport waterSourceReport = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.item_water_source_report, parent, false);
        }
        if (waterSourceReport == null) {
            return convertView;
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
