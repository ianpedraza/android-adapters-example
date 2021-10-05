package com.ianpedraza.adaptersexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ianpedraza.adaptersexample.adapters.SongsListAdapter;
import com.ianpedraza.adaptersexample.domain.Song;
import com.ianpedraza.adaptersexample.interfaces.OnItemSelected;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelected<Song> {

    private RecyclerView rv;
    private SongsListAdapter songsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
    }

    private void setupUI() {
        rv = findViewById(R.id.rv);

        songsListAdapter = new SongsListAdapter(this);

        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(songsListAdapter);

        fetchData();
    }

    private void fetchData() {
        List<Song> myPlaylist = new ArrayList();

        for (int i = 0; i < 100; i++) {
            Song song = new Song();
            song.setTitle("Song " + i);
            song.setAuthor("Author " + i);

            myPlaylist.add(song);
        }

        songsListAdapter.update(myPlaylist);
        songsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSelect(Song item) {
        String msg = "You selected" + item.getTitle();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}