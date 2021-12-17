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

public class all_clients_page extends AppCompatActivity {
    FloatingActionButton addClient;
    RecyclerView clientRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_clients_page);
        addClient=findViewById(R.id.addClient);
        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(all_clients_page.this , add_new_client.class);
                startActivity(intent);
            }
        });

        clientRecyclerView=findViewById(R.id.cleintRecyclerView);
        clientRecyclerView.setLayoutManager(new LinearLayoutManager(all_clients_page.this));
        clientRecyclerView.setHasFixedSize(false);
        final PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .build();
        final FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
        Query query = firebaseFirestore.collection("clients");
        FirestorePagingOptions<clientModel> options = new FirestorePagingOptions.Builder<clientModel>()
                .setQuery(query, config, new SnapshotParser<clientModel>() {
                    @NonNull
                    @Override
                    public clientModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        clientModel clientCardViewModel = snapshot.toObject(clientModel.class);
                        return clientCardViewModel;
                    }
                })
                .build();
        clientAdapter adapter=new clientAdapter(options);
        adapter.setContext(this);
        adapter.startListening();
        clientRecyclerView.setAdapter(adapter);
    }
}