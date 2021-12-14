package com.example.petanimalfacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapterCat extends RecyclerView.Adapter<PostAdapterCat.PostViewHolder> {

    List<PostCat> postCatList;
    Context context;

    public PostAdapterCat(Context context , List<PostCat> postCats) {
         this.context = context;
         postCatList = postCats;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemcats, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        PostCat postCat = postCatList.get(position);
        holder.facts.setText(postCat.getText());

        holder.sharebuttonCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Fact about cat : " + holder.facts.getText());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Send To : "));

            }
        });

    }

    @Override
    public int getItemCount() {
        return postCatList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView facts;
        ImageView sharebuttonCats;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            sharebuttonCats = itemView.findViewById(R.id.sharefactCats);
            facts = itemView.findViewById((R.id.factsCat));

        }
    }
}
