package com.example.musico;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TabLayout tablayout;
    ConstraintLayout conslayout;
    MediaPlayer player;
    ImageView currentlyPlayingBtn = null;
    HashMap<Integer, String> albumNames = new HashMap<>();
    HashMap<String, Integer> albumPics = new HashMap<>();
    public static String ALBUM_TITLE = "album_title";
    public static String ALBUM_PICT = "album_picture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting hashmap
        albumNames.put(R.id.imageView, "biggest_hits_album1");
        albumNames.put(R.id.imageView4, "biggest_hits_album2");
        albumNames.put(R.id.imageView5, "biggest_hits_album3");
        albumNames.put(R.id.imageView6, "biggest_hits_album4");

        albumNames.put(R.id.imageView7, "trending_album1");
        albumNames.put(R.id.imageView8, "trending_album2");
        albumNames.put(R.id.imageView9, "trending_album3");
        albumNames.put(R.id.imageView10, "trending_album4");

        albumNames.put(R.id.imageView11, "hindi_album1");
        albumNames.put(R.id.imageView12, "hindi_album2");
        albumNames.put(R.id.imageView13, "hindi_album3");
        albumNames.put(R.id.imageView14, "hindi_album4");

        albumNames.put(R.id.imageView15, "eng_album1");
        albumNames.put(R.id.imageView16, "eng_album2");
        albumNames.put(R.id.imageView17, "eng_album3");
        albumNames.put(R.id.imageView18, "eng_album4");

        albumNames.put(R.id.imageView19, "marathi_album1");
        albumNames.put(R.id.imageView20, "marathi_album2");
        albumNames.put(R.id.imageView21, "marathi_album3");
        albumNames.put(R.id.imageView22, "marathi_album4");

        // images
        albumPics.put("R.drawable.list1_p1", R.drawable.list1_p1);
        albumPics.put("R.drawable.list1_p2", R.drawable.list1_p2);
        albumPics.put("R.drawable.list1_p3", R.drawable.list1_p3);
        albumPics.put("R.drawable.list1_p4", R.drawable.list1_p4);
        albumPics.put("R.drawable.list2_p1", R.drawable.list2_p1);
        albumPics.put("R.drawable.list2_p2", R.drawable.list2_p2);
        albumPics.put("R.drawable.list2_p3", R.drawable.list2_p3);
        albumPics.put("R.drawable.list2_p4", R.drawable.list2_p4);
        albumPics.put("R.drawable.list3_p1", R.drawable.list3_p1);
        albumPics.put("R.drawable.list3_p2", R.drawable.list3_p2);
        albumPics.put("R.drawable.list3_p3", R.drawable.list3_p3);
        albumPics.put("R.drawable.list3_p4", R.drawable.list3_p4);
        albumPics.put("R.drawable.list4_p1", R.drawable.list4_p1);
        albumPics.put("R.drawable.list4_p2", R.drawable.list4_p2);
        albumPics.put("R.drawable.list4_p3", R.drawable.list4_p3);
        albumPics.put("R.drawable.list4_p4", R.drawable.list4_p4);
        albumPics.put("R.drawable.list5_p1", R.drawable.list5_p1);
        albumPics.put("R.drawable.list5_p2", R.drawable.list5_p2);
        albumPics.put("R.drawable.list5_p3", R.drawable.list5_p3);
        albumPics.put("R.drawable.list5_p4", R.drawable.list5_p4);

        tablayout = (TabLayout) findViewById(R.id.tab_layout);
        conslayout = (ConstraintLayout) findViewById(R.id.constraint);

        getSupportFragmentManager().beginTransaction().replace(R.id.constraint, new Home())
                .addToBackStack(null)
                .commit();

        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                Fragment frag = null;

                switch (tab.getPosition()) {
                    case 0:
                        frag = new Home();
                        break;
                    case 1:
                        frag = new Search();
                        break;
                    case 2:
                        frag = new Library();
                        break;
                }

                assert frag != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.constraint, frag)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void playClicked(View v) {
        ImageView img = (ImageView) v;
        int clickedImg = v.getId();
        int song = 0;

        if (clickedImg == R.id.imageView24) {
            song = R.raw.i_think_they_call_this_love;
        } else if (clickedImg == R.id.imageView26) {
            song = R.raw.tum_prem_ho;
        } else if (clickedImg == R.id.imageView28) {
            song = R.raw.dhaga_dhaga;
        } else if (clickedImg == R.id.imageView30) {
            song = R.raw.aaya_re_toofan;
        }

        Log.d("SongPlayer", "Song played " + song);

        if (MusicPlayer.isPlaying()) {
            MusicPlayer.stopSong();

            if (currentlyPlayingBtn != null) {
                currentlyPlayingBtn.setImageResource(R.drawable.round_arrow_drop_down_circle_24);
                currentlyPlayingBtn.setBackground(null);
                currentlyPlayingBtn.setRotation(-90f);
            }
        }

        if (currentlyPlayingBtn != img) {
            MusicPlayer.playSong(this, song);
            img.setImageResource(R.drawable.round_audiotrack_24);
            img.setBackground(getResources().getDrawable(R.drawable.border));
            img.setRotation(0f);
            img.setAlpha(0.65f);
            img.animate().rotation(360f).setDuration(1000);

            ViewGroup.LayoutParams params = img.getLayoutParams();
            params.height = (int) (50 * getResources().getDisplayMetrics().density);
            params.width = (int) (50 * getResources().getDisplayMetrics().density);
            img.setLayoutParams(params);

            currentlyPlayingBtn = img;
            Log.d("SongPlayer", "Song id " + MusicPlayer.isPlaying());
        } else {
            currentlyPlayingBtn = null;
        }
    }

    public void showAlert(View v) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        final EditText playlist_name = new EditText(MainActivity.this);
        playlist_name.setText("");
        playlist_name.setHint("Enter playlist");
        playlist_name.setPadding(32, 32, 32, 32);

        String[] playlist_name_enter = {"", "", ""};
        final int[] playlist_enter_song_imgAndPath = {0, 0};

        int viewId = v.getId();

        if (viewId == R.id.imageView2) {
            playlist_name_enter[1] = "I Think They Call This Love";
            playlist_name_enter[0] = "Matthew Ifield";
            playlist_enter_song_imgAndPath[0] = R.drawable.i1_1;
            playlist_enter_song_imgAndPath[1] = R.raw.i_think_they_call_this_love;
        }
        else if(viewId == R.id.imageView32) {
            playlist_name_enter[1] = "Tum Prem Ho";
            playlist_name_enter[0] = "Mohit Lalwani, Bharat Kamal";
            playlist_enter_song_imgAndPath[0] = R.drawable.i2_1;
            playlist_enter_song_imgAndPath[1] = R.raw.tum_prem_ho;
        }
        else if(viewId == R.id.imageView33) {
            playlist_name_enter[1] = "Dhaga Dhaga";
            playlist_name_enter[0] = "Harsh Wavre, Anandi Joshi";
            playlist_enter_song_imgAndPath[0] = R.drawable.i3_1;
            playlist_enter_song_imgAndPath[1] = R.raw.dhaga_dhaga;
        }
        else if(viewId == R.id.imageView34) {
            playlist_name_enter[1] = "Aaya Re Toofan";
            playlist_name_enter[0] = "A.R.Rahman, Vaishali Samant";
            playlist_enter_song_imgAndPath[0] = R.drawable.i4_1;
            playlist_enter_song_imgAndPath[1] = R.raw.aaya_re_toofan;
        }

        alert
        .setTitle("Musico")
        .setView(playlist_name)
        .setPositiveButton("Add", (dialogInterface, i) -> {
            playlist_name_enter[0] = playlist_name.getText().toString();
            Toast.makeText(MainActivity.this, "Song added Successfully !", Toast.LENGTH_SHORT).show();
        })
        .show();
    }

    public void insertSong(View v) {
        Intent intent = new Intent(MainActivity.this, SongInsertion.class);
        startActivity(intent);
    }


    public void showList(View v) {

        ImageView album = (ImageView) v;

        int viewId = v.getId();
        String albumPictId = album.getTag().toString();

        Intent intent = new Intent(MainActivity.this, SongList.class);
        intent.putExtra(ALBUM_TITLE, albumNames.get(viewId));
        intent.putExtra(ALBUM_PICT, albumPics.get(albumPictId));

        startActivity(intent);
    }

    public void updateSong(View v) {
        String table_name = "";
        String col_id = "";
        String song_p = "R.raw.";
        String image_p = "R.drawable.";

        MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this, table_name);
        myDB.updateSong(table_name, col_id, song_p, image_p);
    }

}
