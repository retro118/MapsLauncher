package com.example.onkar.mapslauncher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewtSignin;
    private ProgressDialog ProgressDailog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseAuth=FirebaseAuth.getInstance();

        ProgressDailog = new ProgressDialog(this);
        buttonRegister   = (Button)   findViewById(R.id.buttonRegister);
        editTextEmail    = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewtSignin  = (TextView) findViewById(R.id.textviewSignInReg);

        buttonRegister.setOnClickListener(this);
        textViewtSignin.setOnClickListener(this);

    }

    private void registerUser()
    {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

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

        ProgressDailog.setMessage("Working on your input...");
        ProgressDailog.show();


        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {

                            finish();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            Toast.makeText(RegisterActivity.this,"Registration complete!!",Toast.LENGTH_SHORT).show();

                            ProgressDailog.dismiss();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this,"could not complete the registration !!",Toast.LENGTH_SHORT).show();
                            ProgressDailog.dismiss();
                        }



                    }
                });



    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister)
        {
            registerUser();

        }

        if(view == textViewtSignin)
        {


            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        }


    }
}