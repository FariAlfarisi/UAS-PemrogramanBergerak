package com.pemrogramanbergerak.quranap.retrofit;

import com.pemrogramanbergerak.quranap.ModelSurah.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

}
