package id.sisemut.apps.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.sisemut.apps.R;

public class General_info extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private EditText mEditDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Spinner mEthnicSpinner;
    private Spinner mLocation;
    private LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_info);

        mEditDate = (EditText) findViewById(R.id.general_input_edit_date);
        mEthnicSpinner = (Spinner) findViewById(R.id.ethnic_group_spinner);
        mLocation = (Spinner) findViewById(R.id.location_spinner);

        mEthnicSpinner.setOnItemSelectedListener(this);


        btn = (LinearLayout) findViewById(R.id.btn_save_dentition_status);

        List<String> ethnicList = new ArrayList<>();
        ethnicList.add("jawa");
        ethnicList.add("sunda");
        ethnicList.add("madura");
        ethnicList.add("melayu");

        ArrayAdapter<String> ethnicData = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ethnicList);


        ethnicData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mEthnicSpinner.setAdapter(ethnicData);



        mLocation.setOnItemSelectedListener(this);

        List<String> locationList = new ArrayList<>();
        locationList.add("jakarta");
        locationList.add("yohyakarta");
        locationList.add("bandung");
        locationList.add("surabaya");


        ArrayAdapter<String> locData = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, locationList);

        locData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mLocation.setAdapter(locData);



        mEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(General_info.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = month + "/" + dayOfMonth + "/" + year;
                mEditDate.setText(date);

            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast ts = Toast.makeText(getApplicationContext(),"saved", Toast.LENGTH_SHORT);
                ts.show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
