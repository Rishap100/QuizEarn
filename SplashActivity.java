package com.quizearn.rishap.quiz20;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1500;

    MediaPlayer ouSound;
    Animation top_anim,bottom_anim;
    TextView logo;
    ImageView logo_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ouSound = MediaPlayer.create(this,R.raw.single_chime);
        ouSound.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, Splash2.class));
                finish();
            }
        },SPLASH_TIME_OUT);

        top_anim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        logo = (TextView)findViewById(R.id.splash_text);
        logo_img = (ImageView)findViewById(R.id.splash_logo);

        logo_img.setAnimation(top_anim);
        logo.setAnimation(bottom_anim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ouSound.release();
        finish();
    }
}
