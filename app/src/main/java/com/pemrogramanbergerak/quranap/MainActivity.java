package com.pemrogramanbergerak.quranap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pemrogramanbergerak.quranap.ModelSurah.Chapters;
import com.pemrogramanbergerak.quranap.ModelSurah.ChaptersItem;
import com.pemrogramanbergerak.quranap.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    private RecyclerView recyclerView;

    private List<ChaptersItem> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getDataFromApi() {
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {

                if (response.isSuccessful()) {
                    List<ChaptersItem> result =  response.body().getChapters();
                    Log.d("Main", result.toString());
                    mainAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
}