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

public class clientAdapter extends FirestorePagingAdapter<clientModel , clientAdapter.clientViewHolder> {
    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    View view;
    Context context;
    public clientAdapter(@NonNull FirestorePagingOptions<clientModel> options) {
        super(options);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull clientViewHolder holder, int position, @NonNull clientModel model) {
            holder.name.setText(model.getName());
            holder.city.setText(model.getCity());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context , client_personal.class);
                    intent.putExtra("clientEmail" , model.getEmail());
                    context.startActivity(intent);
                }
            });
    }

    @NonNull
    @Override
    public clientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.client_card , parent , false);
        return new clientViewHolder(view);
    }

    public static class clientViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView city;
        public clientViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            city=itemView.findViewById(R.id.city);
        }
    }
}
