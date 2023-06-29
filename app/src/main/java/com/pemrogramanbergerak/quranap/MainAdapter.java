package com.pemrogramanbergerak.quranap;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pemrogramanbergerak.quranap.ModelAudio.AudioFilesItem;
import com.pemrogramanbergerak.quranap.ModelSurah.ChaptersItem;


import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<ChaptersItem> chaptersItems;

    private List<AudioFilesItem> audioFilesItems;

    public MainAdapter(List<ChaptersItem> chaptersItemList, List<AudioFilesItem> audioFilesItemList) {
        this.chaptersItems = chaptersItemList;
        this.audioFilesItems = audioFilesItemList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.surah, parent, false );

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        ChaptersItem chapters = chaptersItems.get(position);
        AudioFilesItem audio = audioFilesItems.get(position);

        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);
                intent.putExtra("name_simple", chapters.getNameSimple());
                intent.putExtra("id", chapters.getId());
                intent.putExtra("name_arabic", chapters.getNameArabic());
                intent.putExtra("name_complex", chapters.getNameComplex());
                intent.putExtra("revelation_order", chapters.getRevelationOrder());
                intent.putExtra("revelation_place", chapters.getRevelationPlace());
                intent.putExtra("verses_count", chapters.getVersesCount());
                intent.putExtra("audio_url", audio.getAudioUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaptersItems.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewTerjemahanSurah, textViewSurahArab;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);

        }
    }

    public  void setData(List<ChaptersItem> surahData, List<AudioFilesItem> audioData){
        chaptersItems.clear();
        chaptersItems.addAll(surahData);

        audioFilesItems.clear();
        audioFilesItems.addAll(audioData);
        notifyDataSetChanged();
    }

}
