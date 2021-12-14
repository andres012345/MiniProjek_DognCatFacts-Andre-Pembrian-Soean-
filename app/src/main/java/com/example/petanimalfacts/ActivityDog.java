package com.example.petanimalfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityDog extends AppCompatActivity {


    private RecyclerView recyclerView;
    Button btnforDogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dogfacts);

        recyclerView = findViewById(R.id.recycerlviewforDogs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnforDogs = findViewById(R.id.btn_morefactsDogs);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dog-facts-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnforDogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( ActivityDog.this, "10 Another Facts about Dogs", Toast.LENGTH_SHORT).show();

                JSONPlaceholder_Dog jsonPlaceholderDog = retrofit.create(JSONPlaceholder_Dog.class);
                Call<List<PostDog>> call = jsonPlaceholderDog.getPost();
                call.enqueue(new Callback<List<PostDog>>() {
                    @Override
                    public void onResponse(Call<List<PostDog>> call, Response<List<PostDog>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(ActivityDog.this, response.code(), Toast.LENGTH_LONG).show();
                            return ;
                        }
                        List<PostDog> postDogList = response.body();
                        PostAdapterDog postAdapterDog = new PostAdapterDog(ActivityDog.this, postDogList);
                        recyclerView.setAdapter(postAdapterDog);
                    }

                    @Override
                    public void onFailure(Call<List<PostDog>> call, Throwable t) {
                        Toast.makeText(ActivityDog.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        JSONPlaceholder_Dog jsonPlaceholderDog = retrofit.create(JSONPlaceholder_Dog.class);
        Call<List<PostDog>> call = jsonPlaceholderDog.getPost();
        call.enqueue(new Callback<List<PostDog>>() {
            @Override
            public void onResponse(Call<List<PostDog>> call, Response<List<PostDog>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityDog.this, response.code(), Toast.LENGTH_LONG).show();
                    return ;
                }
                List<PostDog> postDogList = response.body();
                PostAdapterDog postAdapterDog = new PostAdapterDog(ActivityDog.this, postDogList);
                recyclerView.setAdapter(postAdapterDog);

            }

            @Override
            public void onFailure(Call<List<PostDog>> call, Throwable t) {
                Toast.makeText(ActivityDog.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}