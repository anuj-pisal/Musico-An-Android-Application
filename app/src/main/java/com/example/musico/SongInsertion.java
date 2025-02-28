package com.example.musico;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SongInsertion extends AppCompatActivity {

    EditText nameInput, singerInput, pathInput, imgInput;
    Button submitBtn;

    // mistakes to be corrected
    // 1. aye_udi_udi is set to aye_udi_udi_udi in hindi_album1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_insertion);
        nameInput = findViewById(R.id.editTextText);
        singerInput = findViewById(R.id.editTextText2);
        pathInput = findViewById(R.id.editTextText3);
        imgInput = findViewById(R.id.editTextText4);
        submitBtn = findViewById(R.id.button);



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String[][] songs = {
               //
               // };

                MyDatabaseHelper myDB = new MyDatabaseHelper(SongInsertion.this, "home_page_songs");
                myDB.createTableIfNotExists();

              //  for (String[] song : songs) {
              //      myDB.addSong(song[0], song[1], song[2], song[3]);
              //  }

                myDB.addSong(nameInput.getText().toString().trim(), singerInput.getText().toString().trim(),
                        pathInput.getText().toString().trim(),
                        imgInput.getText().toString().trim());
            }
        });
    }
}