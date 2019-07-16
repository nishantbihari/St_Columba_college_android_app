package com.sr7d.shubhamraja.StColumbasCollege;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    final int TIME = 2000;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_Panel);
        setContentView(R.layout.activity_splash);
        pb= (ProgressBar)findViewById(R.id.pbm);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Splash.this.startActivity(new Intent(Splash.this,MainActivity.class));
                pb.setProgress(50);
                pb.setProgress(100);
                Splash.this.finish();
            }
        },(long) TIME);

    }
}
