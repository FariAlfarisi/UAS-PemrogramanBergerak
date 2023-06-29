package com.pemrogramanbergerak.quranap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pemrogramanbergerak.quranap.ModelAudio.Audio;
import com.pemrogramanbergerak.quranap.ModelAudio.AudioFilesItem;
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

    private List<ChaptersItem> chaptersItems = new ArrayList<>();

    private List<AudioFilesItem> audioItems = new ArrayList<>();

    List<ChaptersItem> chaptersItemList;
    List<AudioFilesItem> audioFilesItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getChaptersFromApi();
        getAudioFromApi();
        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(chaptersItems, audioItems);
        RecyclerView.LayoutManager layoutManager = new  LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getChaptersFromApi(){
        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(@NonNull Call<Chapters> call, Response<Chapters> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    MainActivity.this.chaptersItemList = response.body().getChapters();
                    Log.d("Main",chaptersItemList.toString());
                    mainAdapter.setData(chaptersItemList, audioFilesItemList);
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain",t.toString());
            }
        });
    }

    private void getAudioFromApi(){
            ApiService.endpoint().getAudio().enqueue(new Callback<Audio>() {
                @Override
                public void onResponse(Call<Audio> call, Response<Audio> response) {
                    if (response.isSuccessful()){
                        MainActivity.this.audioFilesItemList = response.body().getAudioFiles();
                        Log.d("Audio",audioFilesItemList.toString());
                        getChaptersFromApi();
                    }
                }

                @Override
                public void onFailure(Call<Audio> call, Throwable t) {
                    Log.d("ErrorAudio",t.toString());
                }
            });
        }
}
