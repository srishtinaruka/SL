package com.example.schedulelawyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_new_schedule extends AppCompatActivity {
    EditText title;
    EditText edittime;
    EditText editDate;
    EditText editLocation;
    EditText editDescription;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_schedule);
        title=findViewById(R.id.spinner);
        editDate=findViewById(R.id.editDate);
        edittime=findViewById(R.id.edittime);
        editLocation=findViewById(R.id.editLocation);
        editDescription=findViewById(R.id.editDescription);
        add=findViewById(R.id.addSchedule);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_INSERT);
                intent.setData(CalendarContract.Events.CONTENT_URI);
                intent.putExtra(CalendarContract.Events.TITLE , title.getText().toString()+"-"+getIntent().getStringExtra("clientName"));
                intent.putExtra(CalendarContract.Events.DESCRIPTION , editDescription.getText().toString());
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION , editLocation.getText().toString());
                intent.putExtra(CalendarContract.Events.ALL_DAY , false);
                intent.putExtra(Intent.EXTRA_EMAIL , getIntent().getStringExtra("clientEmail"));

                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivity(intent);
                }else{
                    Toast.makeText(add_new_schedule.this , "Activity not found" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}