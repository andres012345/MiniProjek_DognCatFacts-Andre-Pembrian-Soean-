package com.example.petanimalfacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder_Dog {
    @GET("api/v1/resources/dogs?number=10")
    Call<List<PostDog>> getPost();

}
