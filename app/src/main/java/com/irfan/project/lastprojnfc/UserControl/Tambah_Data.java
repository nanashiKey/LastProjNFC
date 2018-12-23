package com.irfan.project.lastprojnfc.UserControl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.irfan.project.lastprojnfc.AllData.HttpHandler;
import com.irfan.project.lastprojnfc.R;

public class Tambah_Data extends AppCompatActivity implements View.OnClickListener {

    EditText etjudul, ettanggal, etdesc, etpenulis;
    Button btnkirim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_data);

        etjudul = findViewById(R.id.etjudul);
        ettanggal = findViewById(R.id.etdate);
        etpenulis = findViewById(R.id.etpenulis);
        etdesc = findViewById(R.id.etdesc);
        btnkirim = findViewById(R.id.btnkirim);
        btnkirim.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnkirim:{
                    new TambahData().execute();
            }
        }
    }

    private class TambahData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {

            String judul, tanggal, penulis, deskripsi;
            judul = etjudul.getText().toString().replaceAll(" ", "%20");
            tanggal = ettanggal.getText().toString().replaceAll(" ", "%20");
            penulis = etpenulis.getText().toString().replaceAll(" ", "%20");
            deskripsi = etdesc.getText().toString().replaceAll(" ", "%20");


            String url = "http://simplesample.ngopidevteam.com/tambahdata.php?judul="+judul+"&tanggal="+tanggal+
                    "&penulis="+penulis+"&desc="+deskripsi;
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.e("Tambahdata class", "Respone from url: " + jsonStr);
        return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            startActivity(new Intent(Tambah_Data.this, TampilData.class));
            finish();
        }
    }

}
