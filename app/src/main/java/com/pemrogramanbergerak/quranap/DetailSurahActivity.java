package com.pemrogramanbergerak.quranap;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pemrogramanbergerak.quranap.ModelAyat.Ayat;
import com.pemrogramanbergerak.quranap.ModelAyat.VersesItem;
import com.pemrogramanbergerak.quranap.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pemrogramanbergerak.quranap.ModelTerjemahan.Terjemahan;
import com.pemrogramanbergerak.quranap.ModelTerjemahan.TranslationsItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AyatAdapter ayatAdapter;
    private final List<VersesItem> ayatResults = new ArrayList<>();
    private final List<TranslationsItem> terjemahanResults = new ArrayList<>();
    List<TranslationsItem> terjemahan;
    List<VersesItem> ayat;
    private MediaPlayer mediaPlayer;
    Button playbutton;
    TextView textViewNamaSurah;
    TextView textViewNameSimpleSurah;
    TextView textViewIDSurah;
    TextView textViewNameComplexSurah;
    TextView textViewNameArabicSurah;
    TextView textViewUrutanTurunSurah;
    TextView textViewTempatTurunSurah;
    TextView textViewJumlahAyatSurah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        String namaSurah = getIntent().getStringExtra("name_complex");
        textViewNamaSurah = findViewById(R.id.tvNamaSurah);
        textViewNamaSurah.setText(namaSurah);

        String nameSimple = getIntent().getStringExtra("name_simple");
        textViewNameSimpleSurah = findViewById(R.id.tvNamaSimpleSurah);
        textViewNameSimpleSurah.setText("Nama Surah: " + (nameSimple));

        String nameComplex = getIntent().getStringExtra("name_complex");
        textViewNameComplexSurah = findViewById(R.id.tvNamaComplexSurah);
        textViewNameComplexSurah.setText("Nama Complex: " + (nameComplex));

        int id = getIntent().getIntExtra("id", 1);
        textViewIDSurah = findViewById(R.id.tvIDSurah);
        textViewIDSurah.setText("Urutan Surah: " + (id));

        String nameArabic = getIntent().getStringExtra("name_arabic");
        textViewNameArabicSurah = findViewById(R.id.tvNamaArabicSurah);
        textViewNameArabicSurah.setText(nameArabic);

        int revelationOrder = getIntent().getIntExtra("revelation_order", 1);
        textViewUrutanTurunSurah = findViewById(R.id.tvUrutanTurunSurah);
        textViewUrutanTurunSurah.setText("Turun di Urutan ke: " + (revelationOrder));

        String revelationPlace = getIntent().getStringExtra("revelation_place");
        textViewTempatTurunSurah = findViewById(R.id.tvTempatTurunSurah);
        textViewTempatTurunSurah.setText("Tempat diturunkan: " + (revelationPlace));

        int versesCount = getIntent().getIntExtra("verses_count", 1);
        textViewJumlahAyatSurah = findViewById(R.id.tvJumlahAyatSurah);
        textViewJumlahAyatSurah.setText("Jumlah Ayat: " + (versesCount) + " ayat");

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        playbutton = findViewById(R.id.playButton);
        playbutton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        setUpView();
        setUpRecyclerView();
        getAyatFromApi(id);
        getTerjemahanFromApi(id);
    }


    private void getAyatFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Ayat>() {
            @Override
            public void onResponse(Call<Ayat> call, Response<Ayat> response) {
                if (response.isSuccessful()) {
                    ayat = response.body().getVerses();
                    Log.d("AyatTest", ayat.toString());
                    getAyatFromApi(getIntent().getIntExtra("id",1));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Ayat> call, Throwable t) {
                Log.d("AyatError", t.toString());
            }
        });
    }
    private void getTerjemahanFromApi(int id) {
        ApiService.endpoint().getTerjemahan(id).enqueue(new Callback<Terjemahan>() {
            @Override
            public void onResponse(Call<Terjemahan> call, Response<Terjemahan> response) {
                if (response.isSuccessful()) {
                    terjemahan = response.body().getTranslations();
                    Log.d("TerjemahanTest", terjemahan.toString());
                    ayatAdapter.setData(ayat, terjemahan);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Terjemahan> call, Throwable t) {
                Log.d("TerjemahanError", t.toString());
            }
        });
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    private void playAudio(String audio) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void setUpRecyclerView() {
        ayatAdapter = new AyatAdapter(ayatResults, terjemahanResults);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ayatAdapter);
    }
}