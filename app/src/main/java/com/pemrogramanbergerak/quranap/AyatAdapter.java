package com.pemrogramanbergerak.quranap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pemrogramanbergerak.quranap.ModelAyat.VersesItem;

import java.util.ArrayList;
import java.util.List;

import com.pemrogramanbergerak.quranap.ModelTerjemahan.TranslationsItem;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.AyatViewHolder> {
    private List<VersesItem> ayatResults;
    private List<TranslationsItem> terjemahanResults;

    public AyatAdapter(List<VersesItem> ayatItems, List<TranslationsItem> terjemahanItems){
        if (ayatItems == null) {
            ayatItems = new ArrayList<>();
        }
        if (terjemahanItems == null) {
            terjemahanItems = new ArrayList<>();
        }

        this.ayatResults = ayatItems;
        this.terjemahanResults = terjemahanItems;
    }


    @NonNull
    @Override
    public AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AyatViewHolder holder, int position) {
        VersesItem versesresults = ayatResults.get(position);
        TranslationsItem translationsresults = terjemahanResults.get(position);

        holder.textViewNomor.setText(String.valueOf(position + 1));
        holder.textViewAyatSurah.setText(versesresults.getTextUthmani());
        holder.textViewTerjemahanAyat.setText(translationsresults.getText());

    }

    @Override
    public int getItemCount() {
        return ayatResults.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyatSurah, textViewNomor, textViewTerjemahanAyat;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.tvNomorAyat);
            textViewAyatSurah = itemView.findViewById(R.id.tvAyatArab);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }
    public void setData(List<VersesItem> ayatDataList, List<TranslationsItem> terjemahanDataList) {
        if (ayatDataList == null) {
            ayatDataList = new ArrayList<>();
        }
        if (terjemahanDataList == null) {
            terjemahanDataList = new ArrayList<>();
        }

        ayatResults.clear();
        ayatResults.addAll(ayatDataList);

        terjemahanResults.clear();
        terjemahanResults.addAll(terjemahanDataList);
        notifyDataSetChanged();
    }

}

