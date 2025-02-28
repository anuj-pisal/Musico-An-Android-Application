package com.example.musico;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.view.View;
import  com.bumptech.glide.Glide;

public class CustomFloatingButton extends FrameLayout {

    public ImageView playButton, img_gif;

    public CustomFloatingButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomFloatingButton(Context context) {
        super(context);
        init(context);
    }
    final static boolean[] flag = {false};

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_floating_btn, this, true);
        playButton = findViewById(R.id.play_pause_button);
        img_gif = findViewById(R.id.play_song_gif);

        Glide.with(CustomFloatingButton.this)
                .asGif()
                .load(R.drawable.song_playing_img) // Your .gif file in drawable folder
                .into(img_gif);

        playButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag[0]) {
                    playButton.setImageResource(R.drawable.pause_button);
                    playButton.setBackground(getResources().getDrawable(R.drawable.border));
                    playButton.setRotation(0f);
                    MusicPlayer.resumeSong();
                    Glide.with(CustomFloatingButton.this)
                            .asGif()
                            .load(R.drawable.song_playing_img) // Your .gif file in drawable folder
                            .into(img_gif);

                    flag[0] = false;
                } else {
                    playButton.setImageResource(R.drawable.round_arrow_drop_down_circle_24);
                    playButton.setBackground(null);
                    playButton.setRotation(-90f);
                    MusicPlayer.pauseSong();
                    img_gif.setImageResource(R.drawable.song_playing_img);

                    flag[0] = true;
                }
            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        super.setOnClickListener(listener);
    }
}
