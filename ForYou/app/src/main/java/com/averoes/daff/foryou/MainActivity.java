package com.averoes.daff.foryou;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper slide;

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int img[] = {R.drawable.birthday, R.drawable.duaatu, R.drawable.bg, R.drawable.bg2, R.drawable.happy_birthday, R.drawable.kk};

        slide = findViewById(R.id.slide);

        audio = MediaPlayer.create(this, R.raw.marron5_8d);


        if (Build.VERSION.SDK_INT >= 19) {

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else {

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //for loop

        for (int i = 0; i < img.length; i++) {
            flipper(img[i]);
        }

        audio.start();

    }


    public void flipper(int img) {

        ImageView imageView = new ImageView(this);

        imageView.setBackgroundResource(img);


        slide.addView(imageView);
        slide.setFlipInterval(8000);
        slide.setAutoStart(true);

        //Animation

        slide.setInAnimation(this, android.R.anim.fade_in);
        slide.setOutAnimation(this, android.R.anim.fade_out);


    }


}

