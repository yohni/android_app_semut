package id.sisemut.apps.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import id.sisemut.apps.R;

public class project extends AppCompatActivity {

    LinearLayout general_inf,dentition_stat,period,loss,other;
    private LinearLayout btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        general_inf = (LinearLayout) findViewById(R.id.menu_proj_general_info);
        dentition_stat = (LinearLayout) findViewById(R.id.menu_proj_dentition_status);
        period = (LinearLayout) findViewById(R.id.menu_proj_period_status);
        loss = (LinearLayout) findViewById(R.id.menu_proj_loss_of_att);
        other = (LinearLayout) findViewById(R.id.menu_proj_other);

        general_inf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toGenInf = new Intent(project.this, General_info.class);
                startActivity(toGenInf);

            }
        });

        dentition_stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toDentStat = new Intent(project.this, DentitionStatusActivity.class);
                startActivity(toDentStat);

            }
        });

        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPeriod = new Intent(project.this, Periodontal_status.class);
                startActivity(toPeriod);

            }
        });

        loss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLoss = new Intent(project.this, loss_of_attachment.class);
                startActivity(toLoss);

            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toOther = new Intent(project.this, id.sisemut.apps.Activity.other.class);
                startActivity(toOther);

            }
        });

        btn = (LinearLayout) findViewById(R.id.btn_download);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast ts = Toast.makeText(getApplicationContext(),"Downloading . . .", Toast.LENGTH_LONG);
                ts.show();
            }
        });

    }
}
