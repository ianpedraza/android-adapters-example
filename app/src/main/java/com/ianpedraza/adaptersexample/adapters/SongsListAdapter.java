package com.ianpedraza.adaptersexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ianpedraza.adaptersexample.R;
import com.ianpedraza.adaptersexample.domain.Song;
import com.ianpedraza.adaptersexample.interfaces.OnItemSelected;

import java.util.ArrayList;
import java.util.List;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.ViewHolder> {

    private final ArrayList<Song> songs;
    private final OnItemSelected<Song> onItemSelected;

    public SongsListAdapter(OnItemSelected<Song> onItemSelected) {
        this.songs = new ArrayList<>();
        this.onItemSelected = onItemSelected;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
        return new SongsListAdapter.ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder view, int position) {
        Song song = songs.get(position);
        view.tvItemSongTitle.setText(song.getTitle());
        view.tvItemSongAuthor.setText(song.getAuthor());
        view.itemView.setOnClickListener(v -> onItemSelected.onSelect(song));
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public void update(List<Song> data) {
        this.songs.clear();
        this.songs.addAll(data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItemSongTitle, tvItemSongAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setupUI();
        }

        private void setupUI() {
            tvItemSongTitle = itemView.findViewById(R.id.tvItemSongTitle);
            tvItemSongAuthor = itemView.findViewById(R.id.tvItemSongAuthor);
        }
    }

}
