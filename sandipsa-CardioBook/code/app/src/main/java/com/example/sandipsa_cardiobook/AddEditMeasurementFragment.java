package com.example.sandipsa_cardiobook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.textfield.TextInputLayout;
import java.util.regex.Pattern;

/**
 * This class allows a user to add, edit, or delete a measurement.
 * It guides the user through a dialog box to enter data in the fields
 * and alerts them if they did not fill in a field correctly.
**/

public class AddEditMeasurementFragment extends DialogFragment {


    private TextInputLayout date;
    private TextInputLayout time;
    private TextInputLayout systolic;
    private TextInputLayout diastolic;
    private TextInputLayout heartrate;
    private TextInputLayout comment;
    private OnFragmentInteractionListener listener;

    private Measurement clicked_measurement;
    private int clicked_position;
    private String dialog_title;
    private String errortext = null;

    static AddEditMeasurementFragment newInstance(Measurement measurement, int position){
        Bundle args_add = new Bundle();
        args_add.putSerializable("measurement",measurement);
        args_add.putSerializable("position",position);
        AddEditMeasurementFragment fragment = new AddEditMeasurementFragment();
        fragment.setArguments(args_add);
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        void onOkPressed_add(Measurement newMeasurement);
        void onOkPressed_edit(Measurement editMeasurement, int editPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    /**
     * reference:
     * https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
    **/
    public boolean format_check(String date, String time, String systolic, String diastolic, String heartrate) {

        Boolean date_valid, time_valid, systolic_valid, diastolic_valid, heartrate_valid;

        // determines if the date is valid, a valid date has the form yyyy-mm-dd
        if (date.isEmpty()){ date_valid = false; }
        else { date_valid = Pattern.compile("[0-9]{4}-[0-1][0-9]-[0-3][0-9]").matcher(date).matches(); }
        if (!date_valid && errortext==null){ errortext = "Invalid date. Try again!"; }

        // determines if the time is valid, a valid time has the form hh:mm
        if (time.isEmpty()){time_valid = false;}
        else { time_valid = Pattern.compile("[0-2][0-9]:[0-5][0-9]").matcher(time).matches();}
        if (!time_valid && errortext==null){ errortext = "Invalid time. Try again!"; }

        // determines if the systolic value is valid, a valid systolic value is a non-negative integer
        if (systolic.isEmpty()){systolic_valid = false;}
        else {systolic_valid = Pattern.compile("[0-9]+").matcher(systolic).matches();}
        if (!systolic_valid && errortext==null){ errortext = "Invalid systolic pressure. Try again!"; }

        // determines if the diastolic value is valid, a valid diastolic value is a non-negative integer
        if (diastolic.isEmpty()){diastolic_valid = false;}
        else {diastolic_valid = Pattern.compile("[0-9]+").matcher(diastolic).matches();}
        if (!diastolic_valid && errortext==null){ errortext = "Invalid diastolic pressure. Try again!"; }

        // determines if the heart rate value is valid, a valid heart rate value is a non-negative integer
        if(heartrate.isEmpty()){heartrate_valid = false;}
        else {heartrate_valid = Pattern.compile("[0-9]+").matcher(heartrate).matches();}
        if (!heartrate_valid && errortext==null){ errortext = "Invalid heart rate. Try again!"; }

        return (date_valid && time_valid && systolic_valid && diastolic_valid && heartrate_valid);
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_edit_fragmentlayout_measurement, null);
        date = view.findViewById((R.id.input_date));
        time = view.findViewById((R.id.input_time));
        systolic = view.findViewById((R.id.input_systolic));
        diastolic = view.findViewById((R.id.input_diastolic));
        heartrate = view.findViewById((R.id.input_heartrate));
        comment = view.findViewById((R.id.input_comment));

        final Bundle args_edit = getArguments();
        if (args_edit != null)
        {
            dialog_title = "EDIT Selected Measurement";
            clicked_measurement = (Measurement) args_edit.getSerializable("measurement");
            clicked_position = (int) args_edit.getSerializable("position");

            date.getEditText().setText(clicked_measurement.getDate());
            time.getEditText().setText(clicked_measurement.getTime());
            systolic.getEditText().setText(clicked_measurement.getSystolic());
            diastolic.getEditText().setText(clicked_measurement.getDiastolic());
            heartrate.getEditText().setText(clicked_measurement.getHeartrate());
            comment.getEditText().setText(clicked_measurement.getComment());
        }
        else
        {
            dialog_title = "ADD New Measurement";
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(dialog_title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String _date = date.getEditText().getText().toString();
                        String _time = time.getEditText().getText().toString();
                        String _systolic = systolic.getEditText().getText().toString();
                        String _diastolic = diastolic.getEditText().getText().toString();
                        String _heartrate = heartrate.getEditText().getText().toString();
                        String _comment = comment.getEditText().getText().toString();

                        // checks if input entry format is valid, if not provides error message about what the user should fix
                        boolean is_valid = format_check(_date, _time, _systolic, _diastolic, _heartrate);


                        // display the corresponding error message to the user
                        Toast.makeText(getContext(), errortext ,Toast.LENGTH_LONG).show();

                        if(is_valid)
                        {
                            if (args_edit != null)
                            {
                                clicked_measurement.setDate(_date);
                                clicked_measurement.setTime(_time);
                                clicked_measurement.setDiastolic(_diastolic);
                                clicked_measurement.setSystolic(_systolic);
                                clicked_measurement.setHeartrate(_heartrate);
                                clicked_measurement.setComment(_comment);
                                listener.onOkPressed_edit(clicked_measurement, clicked_position );

                                // display the corresponding message to the user
                                Toast.makeText(getContext(), "Selected record successfully edited!",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                listener.onOkPressed_add(new Measurement(_date,_time,_systolic,_diastolic,_heartrate,_comment));
                                // display the corresponding message to the user
                                Toast.makeText(getContext(), "New record successfully added!",Toast.LENGTH_LONG).show();
                            }
                        }


                    }}).create();


    }
}
