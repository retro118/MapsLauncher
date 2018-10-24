package com.example.onkar.mapslauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnNavi = (Button) findViewById(R.id.buttonnavi);
        btnNavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, MapsNav.class);
                startActivity(intent);
            }
        });

        Button btnLon = (Button) findViewById(R.id.btnloin);
        btnLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Toast.makeText(Menu.this,String.valueOf(da+" Longitude to in string"),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Menu.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        Button btnrg = (Button) findViewById(R.id.btnreg);
        btnrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        Button btnSOS = (Button) findViewById(R.id.btnsos);
        btnSOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, SosMessage.class);
                startActivity(intent);

            }
        });


    }
}
