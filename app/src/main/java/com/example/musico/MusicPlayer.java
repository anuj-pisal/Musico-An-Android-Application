package com.example.musico;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MusicPlayer {

    private static MediaPlayer player;

    public static void playSong(Context context, int songResId) {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
            }
            player.release();
            player = null;
        }

        player = MediaPlayer.create(context, songResId);
        player.setOnCompletionListener(mp -> {
            stopSong();
        });
        player.start();
        player.setLooping(true);
    }

    public static void stopSong() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player = null;
        }
    }

    public static boolean isPlaying() {
        return player != null && player.isPlaying();
    }

    public static void pauseSong() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public static void resumeSong() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }
}
