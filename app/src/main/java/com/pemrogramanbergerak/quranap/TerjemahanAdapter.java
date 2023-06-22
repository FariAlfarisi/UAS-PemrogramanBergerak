package com.pemrogramanbergerak.quranap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ModelTerjemahan.TranslationsItem;

public class TerjemahanAdapter extends RecyclerView.Adapter<TerjemahanAdapter.TerjemahanViewHolder> {
    private List<TranslationsItem> results;

    public TerjemahanAdapter(List<TranslationsItem> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public TerjemahanAdapter.TerjemahanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TerjemahanViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TerjemahanAdapter.TerjemahanViewHolder holder, int position) {
        TranslationsItem result = results.get(position);

        holder.textViewTerjemahanAyat.setText((result.getText()));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class TerjemahanViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTerjemahanAyat;
        public TerjemahanViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTerjemahanAyat = itemView.findViewById(R.id.tvTerjemahanAyat);
        }
    }
    public void setData(List<TranslationsItem> newDataList) {
        newDataList.clear();
        newDataList.addAll(newDataList);
        notifyDataSetChanged();
    }
}
