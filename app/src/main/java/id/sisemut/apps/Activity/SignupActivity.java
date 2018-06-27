package id.sisemut.apps.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import id.sisemut.apps.R;

public class SignupActivity extends AppCompatActivity {

    private EditText  et_name, et_email, et_password, et_password_confirm;
    private Button btn_signup;
    private TextView tv_signin;
    private FirebaseAuth auth;
    static String LoggedIn_Token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        // Check if token already exist
        if(auth.getCurrentUser() != null)
        {
            //User logged In
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        et_email = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        et_password_confirm = findViewById(R.id.et_password_confirm);
        btn_signup = findViewById(R.id.btn_signup);
        tv_signin = findViewById(R.id.tv_signin);

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String name = et_name.getText().toString().trim();
                String password_confirm = et_password_confirm.getText().toString().trim();
                final View view = findViewById(R.id.main_layout_signup);
                Snackbar snackbar_signup;

                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(name) && TextUtils.isEmpty(password_confirm)) {
                    snackbar_signup = Snackbar.make(view, R.string.empty_form, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(TextUtils.isEmpty(name)) {
                    snackbar_signup = Snackbar.make(view, R.string.empty_name, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(TextUtils.isEmpty(email)) {
                    snackbar_signup = Snackbar.make(view, R.string.empty_email, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(TextUtils.isEmpty(password)) {
                    snackbar_signup = Snackbar.make(view, R.string.empty_password, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(TextUtils.isEmpty(password_confirm)) {
                    snackbar_signup = Snackbar.make(view, R.string.empty_password_confirm, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(password.length() < 6) {
                    snackbar_signup = Snackbar.make(view, R.string.minimum_password, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(password_confirm.length() < 6) {
                    snackbar_signup = Snackbar.make(view, R.string.minimum_password_confirm, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else if(!password.equals(password_confirm)) {
                    snackbar_signup = Snackbar.make(view, R.string.different_password, Snackbar.LENGTH_SHORT);
                    snackbar_signup.show();
                    return;
                } else {
                    btn_signup.setEnabled(false);
                    btn_signup.setText(R.string.btn_loading);
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Snackbar snackbar_signup;
                            if (!task.isSuccessful()) {
                                btn_signup.setEnabled(true);
                                btn_signup.setText(R.string.btn_signup);
                                snackbar_signup = Snackbar.make(view, R.string.auth_failed, Snackbar.LENGTH_SHORT);
                                snackbar_signup.show();
                            } else {
                                btn_signup.setEnabled(false);
                                btn_signup.setText(R.string.btn_success);
                                userProfile();
                                finish();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            }
                        }

                        private void userProfile()
                        {
                            FirebaseUser user = auth.getCurrentUser();
                            if(user!= null)
                            {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(et_name.getText().toString().trim())
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d("TESTING", "User profile updated.");
                                                }
                                            }
                                        });
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
        btn_signup.setEnabled(true);
        btn_signup.setText(R.string.btn_signup);
    }
}
