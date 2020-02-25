package com.example.sandipsa_cardiobook;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * This class handles what he user sees when opening the app.
 * It contains a list of all the measurements and displays them.
 * data is serialized and de-serialized here since load from file and save on file happens here.
 **/

public class MainActivity extends AppCompatActivity implements AddEditMeasurementFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    private String FILENAME = "measurement_data.sav";
    private ArrayAdapter<Measurement> measurementAdapter;
    private ArrayList<Measurement> measurementDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView measurement_list = findViewById(R.id.measurement_list);
        FloatingActionButton add_Button = findViewById(R.id.add_button);

        measurementDataList = loadFromFile();
        measurementAdapter = new MeasurementAdapter(this, measurementDataList);
        measurement_list.setAdapter(measurementAdapter);



        add_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddEditMeasurementFragment().show(getSupportFragmentManager(), "ADD_MEASUREMENT");
            }
        });


        measurement_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Measurement temp = measurementDataList.get(position);
                AddEditMeasurementFragment.newInstance(temp, position).show(getSupportFragmentManager(), "EDIT_MEASUREMENT");
            }
        });

        /**reference:
         * https://stackoverflow.com/questions/23195208/how-to-pop-up-a-dialog-to-confirm-delete-when-user-long-press-on-the-list-item
         */
        measurement_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("DELETE");
                alert.setMessage("Are you sure to delete the record?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        measurementDataList.remove(position);
                        saveInFile();
                        show_instruction();
                        measurementAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
                return true;
            }
        });
    }


    @Override
    public void onOkPressed_add(Measurement newMeasurement) {
        measurementDataList.add(newMeasurement);
        saveInFile();
        show_instruction();
        measurementAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOkPressed_edit(Measurement editMeasurement, int editPosition) {
        measurementDataList.set(editPosition, editMeasurement);
        saveInFile();
        show_instruction();
        measurementAdapter.notifyDataSetChanged();
    }


    /**
     * checks if our measurement data list is empty or not.
     * If it's empty show the instruction.
     **/
    private void show_instruction(){

        TextView instruction = findViewById(R.id.instruction);
        instruction.setText("To ADD a new measurement, click '+' button."
                +"\n"+"To EDIT a measurement, short click on the record."
                +"\n"+"To DELETE a measurement, long click on the record.");
        if(measurementDataList.size()==0){

            instruction.setVisibility(View.VISIBLE);
        }
        else {

            instruction.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Loads ArrayList from a file(if exits, otherwise creates a new) using FileInputStream,
     * using gson to serialize data.
     * Source: https://github.com/ta301-ks/lonelyTwitter/
     */
    private ArrayList<Measurement> loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader read = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Measurement>>(){}.getType();
            measurementDataList = gson.fromJson(read, listType);
            // Called to check if the initial message of "Add a measurement" is to be shown or not
            show_instruction();
            return measurementDataList;
        }
        catch (FileNotFoundException e) {
            measurementDataList = new ArrayList<>();
            show_instruction();
            return measurementDataList;
        }
    }


    /**
     * Saves data from the ArrayList using FileOutputStream,
     * using gson to serialize data.
     * Source: https://github.com/ta301-ks/lonelyTwitter/
     */
    private void saveInFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(measurementDataList,write);
            write.flush();
            fos.close();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}



