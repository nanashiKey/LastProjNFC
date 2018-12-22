package com.irfan.project.lastprojnfc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.irfan.project.lastprojnfc.AllData.PrefsManager;
import com.irfan.project.lastprojnfc.UserControl.Login;

public class MainActivity extends AppCompatActivity {

    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        ctx = this;
        boolean statusSplash = PrefsManager.sharedInstance(ctx).getStatusSplash();
        if(!statusSplash){
            Intent intent = new Intent(ctx, SplashS.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(ctx, Login.class);
            startActivity(intent);
            finish();
        }
    }
}
