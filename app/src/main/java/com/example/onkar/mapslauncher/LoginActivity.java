package com.example.onkar.mapslauncher;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private TextView textViewmaps;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        if(firebaseAuth.getCurrentUser()!=null)
        {

           // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            Toast.makeText(this,"Successfully signed in with: " + user.getEmail(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ProfileActivity.class));

            //start profile activity here
        }
        else {
            // User is signed out

            Toast.makeText(this,"Successfully signed out.",Toast.LENGTH_SHORT).show();
        }

        editTextEmail    = (EditText) findViewById(R.id.editTextEmailLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        buttonSignIn     = (Button)   findViewById(R.id.buttonSignInLogin);
        textViewSignup   = (TextView) findViewById(R.id.textViewSignupLogin);
        textViewmaps     = (TextView) findViewById(R.id.textViewMAPS);
        progressDialog   = new ProgressDialog(this);




        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
        textViewmaps.setOnClickListener(this);


    }

    private void userLogin()
    {
        String email= editTextEmail.getText().toString().trim();
        String password= editTextPassword.getText().toString().trim();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"You should enter Email to contiue!!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"You should enter password to contiue!!",Toast.LENGTH_SHORT).show();
            return;
            //password is empty
        }

        progressDialog.setMessage("Working on your input...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            //start profile activity

                            finish();
                            startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonSignIn)
        {
            userLogin();


        }

        if (view == textViewSignup)
        {
            finish();
            startActivity(new Intent(this,RegisterActivity.class));
        }
        if(view==textViewmaps) {
            finish();
            startActivity(new Intent(this, MainActivity.class));

        }

    }
}
