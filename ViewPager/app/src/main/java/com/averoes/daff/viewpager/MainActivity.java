package com.averoes.daff.viewpager;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    MediaPlayer audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = MediaPlayer.create(this, R.raw.untitled);

        if (Build.VERSION.SDK_INT >=19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        viewPager = findViewById(R.id.slider);

        PagerAdapter pagerAdapter = new PagerAdapter(this);

        viewPager.setAdapter(pagerAdapter);
        
        audio.start();

    }
    protected void onDestroy() {
        super.onDestroy();
        audio.stop();
    }
}
