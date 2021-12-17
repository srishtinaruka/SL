package com.example.schedulelawyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.firestore.SnapshotParser;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class all_evidence_page extends AppCompatActivity {
    FloatingActionButton add_evidence;
    RecyclerView evidenceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_evidence_page);
        add_evidence=findViewById(R.id.add_evidence_button);
        evidenceView=findViewById(R.id.evidenceView);
        evidenceView.setLayoutManager(new LinearLayoutManager(all_evidence_page.this));
        evidenceView.setHasFixedSize(false);
        final PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        final FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("cases").document(getIntent().getStringExtra("clientEmail"))
                .collection("case").document(getIntent().getStringExtra("caseId")).collection("evidences");
        FirestorePagingOptions<evidenceModel> options = new FirestorePagingOptions.Builder<evidenceModel>()
                .setQuery(query, config, new SnapshotParser<evidenceModel>() {
                    @NonNull
                    @Override
                    public evidenceModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        evidenceModel evidenceModel = snapshot.toObject(evidenceModel.class);
                        evidenceModel.setItemId(snapshot.getId());
                        return evidenceModel;
                    }
                })
                .build();
        evidenceAddapter adapter=new evidenceAddapter(options);
        adapter.setContext(this);
        adapter.setEmail(getIntent().getStringExtra("clientEmail"));
        adapter.setCaseId(getIntent().getStringExtra("caseId"));
        adapter.startListening();
        evidenceView.setAdapter(adapter);


        add_evidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(all_evidence_page.this , add_new_evidence.class);
                intent.putExtra("clientEmail" , getIntent().getStringExtra("clientEmail"));
                intent.putExtra("caseId" , getIntent().getStringExtra("caseId"));
                startActivity(intent);
            }
        });
    }
}