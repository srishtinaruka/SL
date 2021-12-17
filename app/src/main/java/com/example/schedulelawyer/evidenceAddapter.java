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

public class evidenceAddapter extends FirestorePagingAdapter<evidenceModel , evidenceAddapter.evidenceViewHolder> {
    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    View view;
    Context context;
    String email;
    String caseId;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public evidenceAddapter(@NonNull FirestorePagingOptions<evidenceModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull evidenceViewHolder holder, int position, @NonNull evidenceModel model) {
            holder.name.setText(model.getName());
            holder.date.setText(model.getDate());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context , particular_evidence.class);
                    intent.putExtra("clientEmail" , email);
                    intent.putExtra("caseId" , caseId);
                    intent.putExtra("eviId" , model.getItemId());
                    context.startActivity(intent);
                }
            });
    }

    @NonNull
    @Override
    public evidenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.activity_evidence_card , parent , false);
        return new evidenceViewHolder(view);
    }

    public static class evidenceViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView date;
        public evidenceViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.case_evidence_name);
            date=itemView.findViewById(R.id.case_evidence_date);
        }
    }
}
