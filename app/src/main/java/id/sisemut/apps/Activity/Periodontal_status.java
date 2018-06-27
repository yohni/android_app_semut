package id.sisemut.apps.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sisemut.apps.R;

public class Periodontal_status extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private Spinner mBleedSpinner, mPocketSpinner;
    private int[] idx_teeth = {18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28,48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38};
    private Button next,prev;
    private int idx=0;
    private TextView idxViewPeriod;
    private LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodontal_status);

        next = (Button) findViewById(R.id.btn_period_next);
        prev = (Button) findViewById(R.id.btn_period_prev);

        idxViewPeriod = (TextView) findViewById(R.id.number_period);

        mBleedSpinner = (Spinner) findViewById(R.id.bleeding_spinner);
        mPocketSpinner = (Spinner) findViewById(R.id.pocket_spinner);

        mBleedSpinner.setOnItemSelectedListener(this);


        List<String> gingival = new ArrayList<>();
        gingival.add("0 = Absence of condition");
        gingival.add("1 = Presence of condition");
        gingival.add("9 = Tooth excluded");
        gingival.add("X = Tooth not present");

        ArrayAdapter<String> bleedAdapt = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, gingival);

        mBleedSpinner.setAdapter(bleedAdapt);


        mPocketSpinner.setOnItemSelectedListener(this);



        List<String> pock = new ArrayList<>();
        pock.add("0 = Absence of condition");
        pock.add("1 = Pocket 4-5 mm");
        pock.add("2 = Pocket 6 mm or more");
        pock.add("9 = Tooth excluded");
        pock.add("X = Tooth not present");


        ArrayAdapter<String> pockAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pock);

        mPocketSpinner.setAdapter(pockAdapt);


        btn = (LinearLayout) findViewById(R.id.btn_save_dentition_status);
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
