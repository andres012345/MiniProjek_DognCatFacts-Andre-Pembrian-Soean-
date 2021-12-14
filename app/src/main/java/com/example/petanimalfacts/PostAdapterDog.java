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

public class PostAdapterDog extends RecyclerView.Adapter<PostAdapterDog.PostViewHolder> {

    List<PostDog> postDogList;
    Context context;

    public PostAdapterDog(Context context , List<PostDog> postDogs) {
         this.context = context;
         postDogList = postDogs;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdogs, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        PostDog postDog = postDogList.get(position);
        holder.facts.setText(postDog.getFact());
        holder.sharebuttonDogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Fact about dog : " + holder.facts.getText());
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Send To : "));

            }
        });
    }

    @Override
    public int getItemCount() {
        return postDogList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        TextView facts;
        ImageView sharebuttonDogs;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            sharebuttonDogs = itemView.findViewById(R.id.sharefactDogs);
            facts = itemView.findViewById((R.id.factsDog));

        }
    }
}
