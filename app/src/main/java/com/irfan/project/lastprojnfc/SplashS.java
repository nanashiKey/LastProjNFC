package com.irfan.project.lastprojnfc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.irfan.project.lastprojnfc.AllData.PrefsManager;

public class SplashS extends Activity {
    private Context ctx;
    private static int lamaMuncul = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ctx = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ctx, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, lamaMuncul);

        PrefsManager.sharedInstance(ctx).setStatusSplash(true);
    }
}
