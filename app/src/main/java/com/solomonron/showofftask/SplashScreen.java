package com.solomonron.showofftask;

import androidx.appcompat.app.AppCompatActivity;
import gr.net.maroulis.library.EasySplashScreen;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(2500)
                .withBackgroundColor(Color.parseColor("#074E72"))
                .withLogo(R.drawable.picture)
                .withHeaderText("Welcome!")
                .withFooterText("Ron Solomon 2019");


        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getFooterTextView().setTextColor(Color.WHITE);

        View view = config.create();

        setContentView(view);


    }
}
