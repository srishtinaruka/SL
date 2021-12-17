package com.example.schedulelawyer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;

public class casesAdapter extends FirestorePagingAdapter<casesModel , casesAdapter.casesViewHolder> {
    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    View view;
    Context context;
    String clientEmail;
    public casesAdapter(@NonNull FirestorePagingOptions<casesModel> options) {
        super(options);
    }

    public void setContext(Context context) {
        this.context = context;
    }
    public void setClientEmail(String clientEmail) {this.clientEmail = clientEmail; }
    @Override
    protected void onBindViewHolder(@NonNull casesViewHolder holder, int position, @NonNull casesModel model) {
        holder.caseName.setText(model.getName());
        holder.caseNumber.setText(model.getNumber());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context , particular_case.class);
                intent.putExtra("clientEmail" , clientEmail);
                intent.putExtra("caseId" , model.getItemId());
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public casesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_case_card, parent , false);
        return new casesViewHolder(view);
    }

    public static class casesViewHolder extends RecyclerView.ViewHolder{
        TextView caseName;
        TextView caseNumber;
        public casesViewHolder(@NonNull View itemView) {
            super(itemView);
            caseName=itemView.findViewById(R.id.case_card_casename);
            caseNumber=itemView.findViewById(R.id.case_card_case_number);
        }
    }
}
