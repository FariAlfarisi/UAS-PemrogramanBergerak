package com.pemrogramanbergerak.quranap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pemrogramanbergerak.quranap.ModelAyat.Ayat;
import com.pemrogramanbergerak.quranap.ModelAyat.VersesItem;
import com.pemrogramanbergerak.quranap.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AyatAdapter ayatAdapter;
    List<VersesItem> results = new ArrayList<>();

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

        setUpView();
        setUpRecyclerView();
        getDataFromApi(id);

    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void setUpRecyclerView() {
        ayatAdapter = new AyatAdapter(results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ayatAdapter);
    }

    private void getDataFromApi(int id) {
        ApiService.endpoint().getAyat(id).enqueue(new Callback<Ayat>() {
            @Override
            public void onResponse(Call<Ayat> call, Response<Ayat> response) {
                if(response.isSuccessful()){
                    List<VersesItem> result = response.body().getVerses();
                    Log.d("AyatTest", result.toString());
                    ayatAdapter.setData(result);
                }
            }

            @Override
            public void onFailure(Call<Ayat> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });
    }
}