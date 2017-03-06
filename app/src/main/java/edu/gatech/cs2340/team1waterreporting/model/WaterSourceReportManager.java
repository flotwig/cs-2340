package edu.gatech.cs2340.team1waterreporting.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew on 3/6/2017.
 */

public class WaterSourceReportManager {

    private List<WaterSourceReport> _reports;

    public WaterSourceReportManager() {
        _reports = new ArrayList<>();
        makeSomeReports();
    }

    private void makeSomeReports() {
        //
    }

    public List<WaterSourceReport> getReportList() { return _reports; }
    public void addReport(WaterSourceReport r) {_reports.add(r);}


    public String getLastReportString() {
        return _reports.get(_reports.size() - 1).toString();
    }
    public WaterSourceReport getLastReport() { return _reports.get(_reports.size() - 1);}
}
