package com.pemrogramanbergerak.quranap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailSurahActivity extends AppCompatActivity {

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

    }



}