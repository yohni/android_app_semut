package id.sisemut.apps.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.sisemut.apps.R;

public class other extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner enamel,erosion,erosionNumb,trauma, traumaNumb,oral1, oral2, oral3, loc1, loc2, loc3,denUp, denLow,urgen;

    private LinearLayout btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        enamel = (Spinner) findViewById(R.id.enamel_flourosis_spinner);
        erosion = (Spinner) findViewById(R.id.dental_erosion_spinner);
        trauma = (Spinner) findViewById(R.id.dental_trauma_spinner);
        oral1 = (Spinner) findViewById(R.id.oral_mos_cond1);
        oral2 = (Spinner) findViewById(R.id.oral_mos_cond2);
        oral3 = (Spinner) findViewById(R.id.oral_mos_cond3);
        loc1 = (Spinner) findViewById(R.id.oral_mos_loc1);
        loc2 = (Spinner) findViewById(R.id.oral_mos_loc2);
        loc3 = (Spinner) findViewById(R.id.oral_mos_loc3);
        denUp = (Spinner) findViewById(R.id.dentures_upper_spinner);
        denLow = (Spinner) findViewById(R.id.dentures_lower_spinner);
        urgen = (Spinner) findViewById(R.id.intervention_spinner);

        enamel.setOnItemSelectedListener(this);

        List<String> enamelList = new ArrayList<>();
        enamelList.add("0 = Normal");
        enamelList.add("1 = Questionable");
        enamelList.add("2 = Very mild");
        enamelList.add("3 = Mild");
        enamelList.add("4 = Moderate");
        enamelList.add("5 = Severe");
        enamelList.add("8 = Excluded (crown, restoration, 'bracket'");
        enamelList.add("Not recorded");

        ArrayAdapter<String> enamelAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,enamelList);
        enamelAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        enamel.setAdapter(enamelAdap);

        erosion.setOnItemSelectedListener(this);

        List<String> erosionList = new ArrayList<>();
        erosionList.add("0 = No sign of erosion");
        erosionList.add("1 = Enamel lesion");
        erosionList.add("2 = Dentinal lesion");
        erosionList.add("3 = Pulp involvement");


        ArrayAdapter<String> erosionAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,erosionList);
        erosionAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        erosion.setAdapter(erosionAdap);


        trauma.setOnItemSelectedListener(this);

        List<String> traumaList = new ArrayList<>();
        traumaList.add("0 = Normal");
        traumaList.add("1 = Questionable");
        traumaList.add("2 = Very mild");
        traumaList.add("3 = Mild");
        traumaList.add("4 = Moderate");
        traumaList.add("5 = Severe");
        traumaList.add("8 = Excluded (crown, restoration, 'bracket'");
        traumaList.add("Not recorded");

        ArrayAdapter<String> traumaAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,traumaList);
        traumaAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trauma.setAdapter(traumaAdap);

        oral1.setOnItemSelectedListener(this);
        oral2.setOnItemSelectedListener(this);
        oral3.setOnItemSelectedListener(this);

        List<String> oralList = new ArrayList<>();
        oralList.add("0 = No abnormal condition");
        oralList.add("1 = Malignant tumour (oral cancer)");
        oralList.add("2 = Leukoplakia");
        oralList.add("3 = Lichen planus");
        oralList.add("4 = Ulceration (aphthous, herpetic, traumatic)");
        oralList.add("5 = Acute necrotizing ulcerative gingivitis (ANUG)");
        oralList.add("6 = Candidias");
        oralList.add("7 = Abscess");
        oralList.add("8 = Other condition (specify if possible)");
        oralList.add("9 = Not recorded");

        ArrayAdapter<String> oralAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,oralList);
        oralAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oral1.setAdapter(oralAdap);
        oral2.setAdapter(oralAdap);
        oral3.setAdapter(oralAdap);

        loc1.setOnItemSelectedListener(this);
        loc2.setOnItemSelectedListener(this);
        loc3.setOnItemSelectedListener(this);

        List<String> locList = new ArrayList<>();
        locList.add("0 = Vermillion border");
        locList.add("1 = Commissures");
        locList.add("2 = Lips");
        locList.add("3 = Sulci");
        locList.add("4 = Buccal mucosa");
        locList.add("5 = Floor of the mouth");
        locList.add("6 = Tongue");
        locList.add("7 = Hard and/or soft palate");
        locList.add("8 = Alveolar ridges/gingiva");
        locList.add("9 = Not recorded");

        ArrayAdapter<String> locAdap = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,locList);
        locAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loc1.setAdapter(locAdap);
        loc2.setAdapter(locAdap);
        loc3.setAdapter(locAdap);

        denUp.setOnItemSelectedListener(this);
        denLow.setOnItemSelectedListener(this);

        List<String> dentures = new ArrayList<>();
        dentures.add("0 = No denture");
        dentures.add("1 = Partial denture");
        dentures.add("2 = Complete denture");
        dentures.add("9 = Not recorded");

        ArrayAdapter<String> denturesAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,dentures);
        denturesAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        denUp.setAdapter(denturesAdapt);
        denLow.setAdapter(denturesAdapt);



        urgen.setOnItemSelectedListener(this);

        List<String> urgenList = new ArrayList<>();
        urgenList.add("0 = No treatment needed");
        urgenList.add("1 = Preventive or routine treatment needed");
        urgenList.add("2 = Prompt treatment(including scalling) needed");
        urgenList.add("3 = Immediate (urgent) treatment needed due to pain or infection of dental and/or oral origin");
        urgenList.add("4 = Referred for comprehensive evaluation or medical/dental treatment(system condition)");


        ArrayAdapter<String> urgenAdap = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, urgenList);
        urgenAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urgen.setAdapter(urgenAdap);


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
