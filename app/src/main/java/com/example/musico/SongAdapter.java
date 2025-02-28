package com.example.musico;

import android.app.AlertDialog;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.file.LinkPermission;
import java.util.ArrayList;

public class SongAdapter extends  RecyclerView.Adapter<SongAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<String> song_name, singer_name;
    private  ArrayList<Integer> song_image, song_path, song_id;
    private int currentlyPlayingPosition = -1;
    SongAdapter(Context context,
                ArrayList<String> song_name,
                ArrayList<String> singer_name,
                ArrayList<Integer> song_image,
                ArrayList<Integer> song_path,
                ArrayList<Integer> song_id) {
        this.context = context;
        this.song_name = song_name;
        this.singer_name = singer_name;
        this.song_image = song_image;
        this.song_path = song_path;
        this.song_id = song_id;
    }

    @NonNull
    @Override
    public SongAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.MyViewHolder holder, int position) {
        holder.songName_text.setText(song_name.get(position));
        holder.songSinger_text.setText(singer_name.get(position));
        holder.img.setImageResource(song_image.get(position));

        if (position == currentlyPlayingPosition) {
            holder.songName_text.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            holder.songSinger_text.setTextColor(context.getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            holder.songName_text.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.songSinger_text.setTextColor(context.getResources().getColor(android.R.color.white));
        }

        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition == RecyclerView.NO_POSITION) return;

                boolean state = true;

                if (currentlyPlayingPosition == currentPosition) {
                    MusicPlayer.stopSong();
                    currentlyPlayingPosition = -1;
                } else {
                    MusicPlayer.playSong(context, song_path.get(currentPosition));
                    currentlyPlayingPosition = currentPosition;
                }
                notifyDataSetChanged();
            }
        });

        // alert box
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);

        final EditText playlist_name = new EditText(context);
        playlist_name.setText("");
        playlist_name.setHint("Enter playlist");

        playlist_name.setPadding(32, 32, 32, 32);

        final String[] playlist_enter_song = {"", "", ""};
        final int[] playlist_enter_song_imgAndPath = {0, 0};

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        alert
                        .setTitle("Musico")
                        .setView(playlist_name)
                        .setPositiveButton("Add", (dialogInterface, i) -> {
                            playlist_enter_song[0] = playlist_name.getText().toString();
                            playlist_enter_song[1] = holder.songName_text.getText().toString();
                            playlist_enter_song[2] = holder.songSinger_text.getText().toString();
                            playlist_enter_song_imgAndPath[0] = song_image.get(holder.getAdapterPosition());
                            playlist_enter_song_imgAndPath[1] = song_path.get(holder.getAdapterPosition());

                            //Toast.makeText(context, playlist_enter_song[0], Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context, playlist_enter_song[1], Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context, playlist_enter_song[2], Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context, playlist_enter_song_imgAndPath[0], Toast.LENGTH_SHORT).show();
                            //Toast.makeText(context, playlist_enter_song_imgAndPath[1], Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, "Song added Successfully !", Toast.LENGTH_SHORT).show();
                        })
                        .show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return song_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView songName_text, songSinger_text;
        ImageView img;
        ConstraintLayout cl;
        ImageView btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            songName_text = itemView.findViewById(R.id.songName);
            songSinger_text = itemView.findViewById(R.id.songSinger);
            img = itemView.findViewById(R.id.imageView36);
            cl = itemView.findViewById(R.id.box);
            btn = itemView.findViewById(R.id.imageView35);
        }
    }
}
