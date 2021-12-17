package com.example.schedulelawyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Timer;

public class add_new_client extends AppCompatActivity {
    EditText name;
    EditText address;
    EditText city;
    EditText contact;
    EditText email;
    Button add;
    FirebaseFirestore db;
    HashMap<Object , Object> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_client);
        name=findViewById(R.id.editName);
        address=findViewById(R.id.editAddress);
        city=findViewById(R.id.editCity);
        contact=findViewById(R.id.editContact);
        email=findViewById(R.id.editEmail);
        add=findViewById(R.id.addNewClient);
        db=FirebaseFirestore.getInstance();
        data=new HashMap<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("name" , name.getText().toString());
                data.put("email" , email.getText().toString());
                data.put("address" , address.getText().toString());
                data.put("contact" , contact.getText().toString());
                data.put("city" , city.getText().toString());
                String clientEmail=email.getText().toString();
                db.collection("clients").document(clientEmail)
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void aVoid) {
                                Toast.makeText(add_new_client.this  , "Cleint addded successfully" , Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(add_new_client.this , all_clients_page.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                            }
                        });
            }
        });
    }
}