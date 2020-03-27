package com.mobile.project3mobile.remote;

import com.mobile.project3mobile.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET(Constant.MOVIE)
    Call<Movie> getMovie ();
}
