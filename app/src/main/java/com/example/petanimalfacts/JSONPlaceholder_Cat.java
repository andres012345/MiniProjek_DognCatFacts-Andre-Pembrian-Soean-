package com.example.petanimalfacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder_Cat {
    @GET("facts/random?animal_type=cat&amount=10")
    Call<List<PostCat>> getPost();

}
