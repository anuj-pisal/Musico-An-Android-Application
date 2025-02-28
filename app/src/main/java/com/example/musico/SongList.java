package com.example.musico;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongList extends AppCompatActivity {

    String albumTitle;
    int albumPicture;
    ImageView album_pict;
    TextView album_title;
    MyDatabaseHelper db;
    ArrayList<String> song_name, singer_name, song_image, song_path;
    ArrayList<Integer>  song_id;
    ArrayList<Integer> actual_song_paths, actual_song_images;
    RecyclerView recyclerView;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        Intent intent = getIntent();

        // getting the title and album image
        albumTitle = intent.getStringExtra(MainActivity.ALBUM_TITLE);
        albumPicture = intent.getIntExtra(MainActivity.ALBUM_PICT, -1);

        // setting the album image
        album_pict = findViewById(R.id.imageView31);
        if (albumPicture != -1) {
            album_pict.setImageResource(albumPicture);
        }

        // setting the title
        album_title = findViewById(R.id.textView100);
        album_title.setText(albumTitle);

        // retrieving the songs
        db = new MyDatabaseHelper(SongList.this, albumTitle);
        song_id = new ArrayList<>();
        song_name = new ArrayList<>();
        song_image = new ArrayList<>();
        song_path = new ArrayList<>();
        singer_name = new ArrayList<>();
        actual_song_paths = new ArrayList<>();
        actual_song_images = new ArrayList<>();

        recyclerView = findViewById(R.id.list_of_songs);

        // setting the songs in the list
        storeDatainArray();

        songAdapter = new SongAdapter(SongList.this, song_name, singer_name, actual_song_images, actual_song_paths, song_id);
        recyclerView.setAdapter(songAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SongList.this));
    }

    void storeDatainArray() {
        Cursor c = db.readAllData(albumTitle);
        if (c.getCount() == 0) {
            Toast.makeText(this, "No songs found", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {
                song_id.add(c.getInt(0));
                song_name.add(c.getString(1));
                singer_name.add(c.getString(2));
                song_path.add(c.getString(3));
                song_image.add(c.getString(4));
            }
        }

        getActualImages();
        getActualPaths();

        Log.d("SongList", "Song images " + song_image);
        Log.d("SongList", "Song paths " + song_image);
        Log.d("SongList", "Song id " + song_id);
    }

    public void getActualImages() {
        for (int i=0; i<song_image.size(); i++) {
            String song_id = song_image.get(i);
            String song_name = song_id.substring(song_id.lastIndexOf('.') + 1);
            int resID = getResources().getIdentifier(song_name, "drawable", getPackageName());
            actual_song_images.add(resID);
        }
        Log.d("SongList", "Song images " + actual_song_images);
    }

    public void getActualPaths() {
        for (int i=0; i<song_path.size(); i++) {
            String song_id = song_path.get(i);
            String song_name = song_id.substring(song_id.lastIndexOf('.') + 1);
            int resID = getResources().getIdentifier(song_name, "raw", getPackageName());
            actual_song_paths.add(resID);
        }
        Log.d("SongList", "Song paths " + actual_song_paths);
    }


}