package com.example.assignmentosos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {

    @GET("users")
    Call<List<post>> getPost();


}
