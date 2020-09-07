package com.example.gadso;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface leaderservices {
    @GET("api/hours")
    Call<List<leaders>> getLeaders();
}
