package com.c1.fb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnfeeds;
    Button btnFR;
    Button btnfriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnfeeds= (Button) findViewById(R.id.btnfeed);
        btnFR= (Button) findViewById(R.id.btnFR);
        btnfriends= (Button) findViewById(R.id.btnfriends);
        btnfeeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,feed_main.class);
                startActivity(i);
            }
        });

        btnFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(MainActivity.this,FR_main.class);
                startActivity(i1);
            }
        });
        btnfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i4=new Intent(MainActivity.this,friendlist.class);
                startActivity(i4);
            }
        });
    }
}
