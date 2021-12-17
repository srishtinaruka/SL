package com.example.schedulelawyer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class particular_case extends AppCompatActivity {
    TextView name;
    TextView number;
    TextView date;
    TextView type;
    TextView status;
    TextView fees;
    TextView oppName;
    TextView oppLawyer;
    TextView courtName;
    TextView judgeName;
    Button evidence_button;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_case);
        name=findViewById(R.id.name);
        number=findViewById(R.id.editCaseNo);
        date=findViewById(R.id.editdate);
        type=findViewById(R.id.editType);
        status=findViewById(R.id.editstatus);
        fees=findViewById(R.id.editFee);
        oppName=findViewById(R.id.editOpponentName);
        oppLawyer=findViewById(R.id.editOpponentLawyer);
        courtName=findViewById(R.id.editCourtName);
        judgeName=findViewById(R.id.editJudgeName);
        evidence_button=findViewById(R.id.evidence_button);

        db=FirebaseFirestore.getInstance();
        db.collection("cases").document(getIntent().getStringExtra("clientEmail")).collection("case")
                                    .document(getIntent().getStringExtra("caseId")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value.exists()){
                            name.setText(value.get("name").toString());
                            number.setText(value.get("number").toString());
                            date.setText(value.get("date").toString());
                            type.setText(value.get("type").toString());
                            status.setText(value.get("status").toString());
                            fees.setText(value.get("fees").toString());
                            oppLawyer.setText(value.get("oppLawyer").toString());
                            oppName.setText(value.get("oppName").toString());
                            courtName.setText(value.get("courtName").toString());
                            judgeName.setText(value.get("judgeName").toString());
                        }
            }
        });
        evidence_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(particular_case.this , all_evidence_page.class);
                intent.putExtra("clientEmail" , getIntent().getStringExtra("clientEmail"));
                intent.putExtra("caseId" , getIntent().getStringExtra("caseId"));
                startActivity(intent);
            }
        });
    }
}