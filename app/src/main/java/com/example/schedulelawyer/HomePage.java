package com.example.schedulelawyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

public class HomePage extends AppCompatActivity {
    Button clients;
    Button calendar;
    long startMills;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        Date date=new Date();
        startMills=date.getTime();
        clients=findViewById(R.id.clients);
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this , all_clients_page.class);
                startActivity(intent);
            }
        });

        calendar=findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri.Builder builder= CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder , startMills);
                Intent intent=new Intent(Intent.ACTION_VIEW).setData(builder.build());
                startActivity(intent);
            }
        });
    }
}