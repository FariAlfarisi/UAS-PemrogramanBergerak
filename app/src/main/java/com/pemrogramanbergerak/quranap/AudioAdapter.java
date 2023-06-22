package com.pemrogramanbergerak.quranap;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import android.media.MediaPlayer;

import ModelAudio.Audio;
import ModelAudio.AudioFilesItem;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.AudioViewHolder> {
    List<AudioFilesItem> audioList;
    private MediaPlayer mediaPlayer;

    public AudioAdapter(List<AudioFilesItem> audioList) {
        this.audioList = audioList;
        mediaPlayer = new MediaPlayer();
    }
    @NonNull
    @Override
    public AudioAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.audio, parent, false);
        return new AudioViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioAdapter.AudioViewHolder holder, int position) {
        holder.playButton.setOnClickListener(view ->  {
            AudioFilesItem audioFilesItem = audioList.get(position);

            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audioFilesItem.getAudioUrl());
            }
        });

    }

    private void playAudio(String audioUrl) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audioUrl);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder {
        public ImageButton playButton;
        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            playButton = itemView.findViewById(R.id.playbutton);
        }
    }

    public void setData(List<AudioFilesItem> newDataList) {
        newDataList.clear();
        newDataList.addAll(newDataList);
        notifyDataSetChanged();
    }
}
