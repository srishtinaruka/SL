package com.example.schedulelawyer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

public class particular_evidence extends AppCompatActivity {
    TextView evidence_name;
    TextView evidence_date;
    ImageView image;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_evidence);
        evidence_name = findViewById(R.id.edit_evidenceName);
        evidence_date = findViewById(R.id.edit_date);
        image= findViewById(R.id.evidence_img);
        db=FirebaseFirestore.getInstance();
        db.collection("cases").document(getIntent().getStringExtra("clientEmail"))
                .collection("case").document(getIntent().getStringExtra("caseId")).collection("evidences")
                .document(getIntent().getStringExtra("eviId")).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(value.exists()){
                        evidence_name.setText(value.get("name").toString());
                        evidence_date.setText(value.get("date").toString());
                        Picasso.get().load(value.get("link").toString()).fit().noFade().into(image);
                    }
            }
        });

}
}