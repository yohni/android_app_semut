package id.sisemut.apps.Activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class DentitionStatusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar toolbar;
    private Spinner mCrownSpinner, mRootSpinner;
    private int[] idx_teeth = {18,17,16,15,14,13,12,11,21,22,23,24,25,26,27,28,48,47,46,45,44,43,42,41,31,32,33,34,35,36,37,38};
    private Button next,prev;
    private int idx=0;
    private TextView idxView;
    private LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dentition_status);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        next = (Button) findViewById(R.id.btn_dentition_status_next);
        prev = (Button) findViewById(R.id.btn_dentition_status_prev);


        idxView = (TextView) findViewById(R.id.number_dentition_status);

        mCrownSpinner = (Spinner) findViewById(R.id.crown_spinner);
        mRootSpinner = (Spinner) findViewById(R.id.root_spinner);

        mCrownSpinner.setOnItemSelectedListener(this);
        mRootSpinner.setOnItemSelectedListener(this);

        List<String> crownList = new ArrayList<>();
        crownList.add("0 = Sound");
        crownList.add("1 = Caries");
        crownList.add("2 = Filled w/caries");
        crownList.add("3 = Filled, no caries");
        crownList.add("4 = Missing due to caries");
        crownList.add("5 = Missing for any another reason");
        crownList.add("6 = Fissure sealant");
        crownList.add("7 = Fixed dental prosthesis/crown abutment, veneer, implant");
        crownList.add("8 = Unerupted");
        crownList.add("9 = not recorded");


        final ArrayAdapter<String> crownAdapt = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,crownList);


        mCrownSpinner.setAdapter(crownAdapt);
        mRootSpinner.setAdapter(crownAdapt);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx < idx_teeth.length){
                    idx++;
                    idxView.setText(""+idx_teeth[idx]);

                    mCrownSpinner.setAdapter(crownAdapt);
                    mRootSpinner.setAdapter(crownAdapt);
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idx > 0){
                    idx--;
                    idxView.setText(""+idx_teeth[idx]);

                    mCrownSpinner.setAdapter(crownAdapt);
                    mRootSpinner.setAdapter(crownAdapt);
                }
            }
        });


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
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
