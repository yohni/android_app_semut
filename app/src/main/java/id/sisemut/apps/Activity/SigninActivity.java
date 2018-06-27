package id.sisemut.apps.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import id.sisemut.apps.R;

public class SigninActivity extends AppCompatActivity {

    private EditText et_email, et_password;
    private Button btn_signin;
    private TextView tv_signup, tv_forgot_password;
    private ProgressBar progress_bar_signin;
    private FirebaseAuth auth;
    static String LoggedIn_Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null)
        {
            //User logged In
            finish();
            startActivity(new Intent(SigninActivity.this, MainActivity.class));
        }

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signin = findViewById(R.id.btn_signin);
        tv_signup = findViewById(R.id.tv_signup);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(signup);
            }
        });

        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SigninActivity.this, "Add Funtion there", Toast.LENGTH_SHORT).show();
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                final View view = findViewById(R.id.main_layout_signin);
                Snackbar snackbar_signin;

                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    snackbar_signin = Snackbar.make(view, R.string.empty_form, Snackbar.LENGTH_SHORT);
                    snackbar_signin.show();
                    return;
                } else if (TextUtils.isEmpty(email)) {
                    snackbar_signin = Snackbar.make(view, R.string.empty_email, Snackbar.LENGTH_SHORT);
                    snackbar_signin.show();
                    return;
                } else if (TextUtils.isEmpty(password)) {
                    snackbar_signin = Snackbar.make(view, R.string.empty_password, Snackbar.LENGTH_SHORT);
                    snackbar_signin.show();
                    return;
                } else if (password.length() < 6) {
                    snackbar_signin = Snackbar.make(view, R.string.minimum_password, Snackbar.LENGTH_SHORT);
                    snackbar_signin.show();
                    return;
                } else {
                    btn_signin.setEnabled(false);
                    btn_signin.setText(R.string.btn_loading);
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Snackbar snackbar_signin;
                            if (!task.isSuccessful()) {
                                btn_signin.setEnabled(true);
                                btn_signin.setText(R.string.btn_signin);
                                snackbar_signin = Snackbar.make(view, R.string.auth_failed, Snackbar.LENGTH_SHORT);
                                snackbar_signin.show();
                            } else {
                                btn_signin.setEnabled(false);
                                btn_signin.setText(R.string.btn_success);
                                Intent i = new Intent(SigninActivity.this, MainActivity.class);
                                finish();
                                startActivity(i);
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btn_signin.setEnabled(true);
        btn_signin.setText(R.string.btn_signin);
    }
}
