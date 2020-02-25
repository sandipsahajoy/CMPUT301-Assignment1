package com.example.sandipsa_cardiobook;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

/**
 * This class handles the ListView which displays the list of measurements on the main activity.
 * It is responsible for binding of the data to visual the list. This also handles action
 * such as highlighting the colors of alerted systolic and diastolic readings.
 **/

public class MeasurementAdapter extends ArrayAdapter<Measurement> {

    private ArrayList<Measurement> measurementDataList;
    private Context context;

    public MeasurementAdapter(Context context, ArrayList<Measurement> measurementDataList) {
        super(context,0,measurementDataList);
        this.measurementDataList = measurementDataList;
        this.context = context;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.listlayout_measurement,parent,false);
        }

        Measurement measurement = measurementDataList.get(position);

        TextView date = view.findViewById(R.id.output_date);
        TextView time = view.findViewById(R.id.output_time);
        TextView systolic = view.findViewById(R.id.output_systolic);
        TextView diastolic = view.findViewById(R.id.output_diastolic);
        TextView heartrate = view.findViewById(R.id.output_heartrate);
        TextView comment = view.findViewById(R.id.output_comment);

        // Check for normal range pressures
        if (Integer.parseInt(measurement.getSystolic()) < 90
                || Integer.parseInt(measurement.getSystolic()) > 140) {
            systolic.setTextColor(Color.RED);
        }
        if (Integer.parseInt(measurement.getDiastolic())< 60
                || Integer.parseInt(measurement.getDiastolic()) > 90) {
            diastolic.setTextColor(Color.RED);
        }

        date.setText("Date: " + measurement.getDate());
        time.setText("Time: " + measurement.getTime());
        systolic.setText("Systolic Pressure (mm Hg): " + measurement.getSystolic());
        diastolic.setText("Diastolic Pressure (mm Hg): " + measurement.getDiastolic());
        heartrate.setText("Heart Rate (bpm): "+ measurement.getHeartrate());
        comment.setText("Comment: "+ measurement.getComment());

        return view;
    }
}

