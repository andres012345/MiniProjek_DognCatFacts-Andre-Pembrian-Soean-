package com.example.petanimalfacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActivityCat extends AppCompatActivity {

    private RecyclerView recyclerView;
    Button btnforCats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catfacts);

        recyclerView = findViewById(R.id.recycerlviewforCats);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnforCats = findViewById(R.id.btn_morefactsCats);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnforCats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( ActivityCat.this, "10 Another Facts about Cats", Toast.LENGTH_SHORT).show();

                JSONPlaceholder_Cat jsonPlaceholderCat = retrofit.create(JSONPlaceholder_Cat.class);
                Call<List<PostCat>> call = jsonPlaceholderCat.getPost();
                call.enqueue(new Callback<List<PostCat>>() {
                    @Override
                    public void onResponse(Call<List<PostCat>> call, Response<List<PostCat>> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(ActivityCat.this, response.code(), Toast.LENGTH_LONG).show();
                            return ;
                        }
                        List<PostCat> postCatList = response.body();
                        PostAdapterCat postAdapterCat = new PostAdapterCat(ActivityCat.this, postCatList);
                        recyclerView.setAdapter(postAdapterCat);
                    }

                    @Override
                    public void onFailure(Call<List<PostCat>> call, Throwable t) {
                        Toast.makeText(ActivityCat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        JSONPlaceholder_Cat jsonPlaceholderCat = retrofit.create(JSONPlaceholder_Cat.class);
        Call<List<PostCat>> call = jsonPlaceholderCat.getPost();
        call.enqueue(new Callback<List<PostCat>>() {
            @Override
            public void onResponse(Call<List<PostCat>> call, Response<List<PostCat>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ActivityCat.this, response.code(), Toast.LENGTH_SHORT).show();
                    return ;
                }
                List<PostCat> postCatList = response.body();
                PostAdapterCat postAdapterCat = new PostAdapterCat(ActivityCat.this, postCatList);
                recyclerView.setAdapter(postAdapterCat);
            }

            @Override
            public void onFailure(Call<List<PostCat>> call, Throwable t) {
                Toast.makeText(ActivityCat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}