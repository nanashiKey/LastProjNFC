package com.irfan.project.lastprojnfc.UserControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.irfan.project.lastprojnfc.AllData.PrefsManager;
import com.irfan.project.lastprojnfc.R;

public class Profile extends AppCompatActivity implements View.OnClickListener {

    TextView nama, logout;
    String nama_;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_user);
        nama = findViewById(R.id.tvnama);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);

        nama_ = PrefsManager.sharedInstance(this).getNama();
        nama.setText(nama_);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logout:{
                startActivity(new Intent(Profile.this, Login.class));
                PrefsManager.sharedInstance(this).setStatusLogin(false);
                finish();
            }
        }
    }
}
