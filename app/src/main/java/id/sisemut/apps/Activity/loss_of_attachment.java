package id.sisemut.apps.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sisemut.apps.R;

public class loss_of_attachment extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spin1,spin2, spin3, spin4, spin5, spin6;
    private LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loss_of_attachment);

        spin1 = (Spinner) findViewById(R.id.loa_idx1);
        spin2 = (Spinner) findViewById(R.id.loa_idx2);
        spin3 = (Spinner) findViewById(R.id.loa_idx3);
        spin4 = (Spinner) findViewById(R.id.loa_idx4);
        spin5 = (Spinner) findViewById(R.id.loa_idx5);
        spin6 = (Spinner) findViewById(R.id.loa_idx6);


        spin1.setOnItemSelectedListener(this);



        List<String> loa = new ArrayList<>();
        loa.add("0 = 0-3 mm");
        loa.add("1 = 4-5 mm");
        loa.add("2 = 6-8 mm");
        loa.add("3 = 9-11 mm");
        loa.add("4 = 12 mm or more");
        loa.add("X = Excluded sextant");
        loa.add("9 = Not recorded");



        ArrayAdapter<String> loaAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, loa);
        loaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(loaAdapter);
        spin2.setAdapter(loaAdapter);
        spin3.setAdapter(loaAdapter);
        spin4.setAdapter(loaAdapter);
        spin5.setAdapter(loaAdapter);
        spin6.setAdapter(loaAdapter);


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
