package com.example.onkar.mapslauncher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    MapsNav desi = new MapsNav();

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    private FirebaseAuth firebaseAuth;

    DataBaseHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);

        firebaseAuth=FirebaseAuth.getInstance();


        FirebaseUser user=firebaseAuth.getCurrentUser();
        /*AddData();*/

        if(isServicesOK()){
            startActivity(new Intent(this,Menu.class));
        }


        if(firebaseAuth.getCurrentUser()!=null)
        {

            // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
            Toast.makeText(this,"Successfully signed in with: " + user.getEmail(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ProfileActivity.class));

            //start profile activity here
        }

        Button btnmnu = (Button) findViewById(R.id.buttonMenu);
        btnmnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,desi.NAS+" Longitude to in string",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Menu.class);
                startActivity(intent);

            }
        });
    }




    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
