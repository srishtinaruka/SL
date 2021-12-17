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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class add_new_case extends AppCompatActivity {
    EditText name;
    EditText number;
    EditText date;
    EditText type;
    EditText status;
    EditText fees;
    EditText oppName;
    EditText oppLawyer;
    EditText courtName;
    EditText judgeName;
    Button add;
    FirebaseFirestore db;
    HashMap<Object , Object> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_case);
        name=findViewById(R.id.editName);
        number=findViewById(R.id.editCaseNo);
        date=findViewById(R.id.editdate);
        type=findViewById(R.id.editType);
        status=findViewById(R.id.editstatus);
        fees=findViewById(R.id.editFee);
        oppName=findViewById(R.id.editOpponentName);
        oppLawyer=findViewById(R.id.editOpponentLawyer);
        courtName=findViewById(R.id.editCourtName);
        judgeName=findViewById(R.id.editJudgeName);
        add=findViewById(R.id.addButton);
        db=FirebaseFirestore.getInstance();
        data=new HashMap<>();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("name" , name.getText().toString());
                data.put("number" , number.getText().toString());
                data.put("date" , date.getText().toString());
                data.put("type" , type.getText().toString());
                data.put("status" , status.getText().toString());
                data.put("fees" , fees.getText().toString());
                data.put("oppName" , oppName.getText().toString());
                data.put("oppLawyer" , oppLawyer.getText().toString());
                data.put("courtName" , courtName.getText().toString());
                data.put("judgeName" , judgeName.getText().toString());
                String caseNumber=number.getText().toString();
                db.collection("cases").document(getIntent().getStringExtra("clientEmail")).collection("case").add(data).addOnSuccessListener(
                        new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(@NonNull DocumentReference documentReference) {
                                    Intent intent=new Intent(add_new_case.this , all_cases_page.class);
                                    startActivity(intent);
                            }
                        }
                );
            }
        });

    }
}