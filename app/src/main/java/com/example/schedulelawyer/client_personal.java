package com.example.schedulelawyer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class client_personal extends AppCompatActivity {
    TextView name;
    TextView address;
    TextView city;
    TextView contact;
    TextView email;
    Button cases;
    Button schedule;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_personal);
        name=findViewById(R.id.name);
        address=findViewById(R.id.editAddress);
        city=findViewById(R.id.editCity);
        contact=findViewById(R.id.editContact);
        email=findViewById(R.id.editEmail);
        cases=findViewById(R.id.cases_button);
        schedule=findViewById(R.id.schedule_button);
        db=FirebaseFirestore.getInstance();
        Log.i("rectify" , ""+getIntent().getStringExtra("clientEmail"));
        db.collection("clients").document(getIntent().getStringExtra("clientEmail")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value.exists()){
                            name.setText(value.get("name").toString());
                            address.setText(value.get("address").toString());
                            city.setText(value.get("city").toString());
                            contact.setText(value.get("contact").toString());
                            email.setText(value.get("email").toString());
                        }
            }
        });
        cases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(client_personal.this , all_cases_page.class);
                intent.putExtra("clientEmail" , getIntent().getStringExtra("clientEmail"));
                startActivity(intent);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(client_personal.this , add_new_schedule.class);
                intent.putExtra("clientEmail" , getIntent().getStringExtra("clientEmail"));
                intent.putExtra("clientName" , name.getText().toString());
                startActivity(intent);
            }
        });
    }
}