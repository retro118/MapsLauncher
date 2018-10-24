package com.example.onkar.mapslauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonLogout;
    private TextView useremail;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button buttonSave;
    private EditText editTextFirstname,PETNO,PETMAIL,PeditTextECM,PeditTextECP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("databases");

        FirebaseUser user=firebaseAuth.getCurrentUser();

        Button btnmenu = (Button) findViewById(R.id.btnmenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ProfileActivity.this,Menu.class);
                startActivity(intent);

            }
        });


        useremail=(TextView) findViewById(R.id.TextviewProfileName);
        useremail.setText("Welcome "+user.getEmail());
        buttonLogout=(Button) findViewById(R.id.buttonProfileLogout);




        editTextFirstname=(EditText) findViewById(R.id.editTextFirstName);
        PETNO = (EditText) findViewById(R.id.ETNO);
        PETMAIL = (EditText) findViewById(R.id.ETMAIL);
        PeditTextECM = (EditText) findViewById(R.id.editTextECM);
        PeditTextECP = (EditText) findViewById(R.id.editTextECP);

        buttonSave=(Button) findViewById(R.id.buttonProfileSave);

        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


    }

    private void saveUserInformation()
    {
        String Uname = editTextFirstname.getText().toString().trim();
        String Uphone= PETNO.getText().toString().trim();
        String Uemail= PETMAIL.getText().toString().trim();
        String Ename=  PeditTextECM.getText().toString().trim();
        String Ephone= PeditTextECP.getText().toString().trim();


        FirebaseUser user=firebaseAuth.getCurrentUser();

        if (!TextUtils.isEmpty(Uname)) {

            String id=databaseReference.push().getKey();
            UserInformation userinformation=new UserInformation(Uname,Uphone,Uemail,Ename,Ephone);
            databaseReference.child(id).setValue(userinformation);
            databaseReference.child(user.getUid()).setValue(userinformation);
            Toast.makeText(this,"Information Saved....",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"AAi ghalya Naav taak na mc....",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onClick(View view) {
        if (view==buttonLogout)
        {
            firebaseAuth.signOut();
            Toast.makeText(this,"Signing Out...",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(this,LoginActivity.class));

        }

        if(view==buttonSave)
        {
            saveUserInformation();
        }


   }


}
