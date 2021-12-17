package com.example.schedulelawyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.SnapshotParser;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class all_cases_page extends AppCompatActivity {
    FloatingActionButton addCase;
    RecyclerView caseRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cases_page);
        addCase=findViewById(R.id.addCase);
        addCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(all_cases_page.this , add_new_case.class);
                intent.putExtra("clientEmail" , getIntent().getStringExtra("clientEmail"));
                startActivity(intent);
            }
        });

        caseRecyclerView=findViewById(R.id.casesRecyclerView);
        caseRecyclerView.setLayoutManager(new LinearLayoutManager(all_cases_page.this));
        caseRecyclerView.setHasFixedSize(false);
        final PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        final FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("cases").document(getIntent().getStringExtra("clientEmail")).collection("case");
        FirestorePagingOptions<casesModel> options = new FirestorePagingOptions.Builder<casesModel>()
                .setQuery(query, config, new SnapshotParser<casesModel>() {
                    @NonNull
                    @Override
                    public casesModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        casesModel casesCardViewModel = snapshot.toObject(casesModel.class);
                        casesCardViewModel.setItemId(snapshot.getId());
                        return casesCardViewModel;
                    }
                })
                .build();
        casesAdapter adapter=new casesAdapter(options);
        adapter.setContext(this);
        adapter.setClientEmail(getIntent().getStringExtra("clientEmail"));
        adapter.startListening();
        caseRecyclerView.setAdapter(adapter);

    }
}