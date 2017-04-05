package edu.gatech.cs2340.team1waterreporting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.team1waterreporting.model.WaterPurityReport;

/**
 * Adapter to translate an array of water quality reports into a ListView.
 */

@SuppressWarnings("ChainedMethodCall")
class WaterQualityReportArrayAdapter extends ArrayAdapter<WaterPurityReport> {
    /**
     * Constructs a new WaterQualityReportArrayAdapter
     * @param context context to operate in
     * @param waterPurityReports WaterPurityReport objects to show
     */
    public WaterQualityReportArrayAdapter
        (Context context, List<WaterPurityReport> waterPurityReports) {

        super(context, 0, waterPurityReports);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        WaterPurityReport waterPurityReport = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.item_water_source_report, parent, false);
        }
        if (waterPurityReport == null) {
            return convertView;
        }
        TextView mNumber = (TextView) convertView.findViewById(R.id.number_text);
        TextView mRow = (TextView) convertView.findViewById(R.id.row_text);

        mNumber.setText(Integer.toString(waterPurityReport.getNumber()));
        mRow.setText(String.format(
                "%s (%f virus PPM, %f contaminant PPM)\nby %s on %s\nLat: %f Lng: %f",
                waterPurityReport.getWaterCondition().toString(),
                waterPurityReport.getVirusPpm(),
                waterPurityReport.getContaminantPpm(),
                waterPurityReport.getReporter().getName(),
                waterPurityReport.getDate().toString(),
                waterPurityReport.getLocation().getLatitude(),
                waterPurityReport.getLocation().getLongitude()
        ));
        return convertView;
    }
}
