package com.irfan.project.lastprojnfc.UserControl;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.irfan.project.lastprojnfc.AllData.HttpHandler;
import com.irfan.project.lastprojnfc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Detail_Data extends AppCompatActivity {

    TextView tvjudul, tvtanggal, tvpenulis, tvdesc;
    Context ctx;
    String dataJudul, urldata, judul, penulis, tahun_terbit, keterangan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_data);
        ctx = this;

        Bundle b = getIntent().getExtras();
        if(b != null){
            dataJudul = b.getString("judul");
            Log.i("Data " , dataJudul);
        }else{
            Log.i("Data " , "data kosong");
        }

        String datajudul =  dataJudul.replaceAll(" ", "%20");
        urldata = "http://simplesample.ngopidevteam.com/detaildata.php?judul="+datajudul;

        tvjudul = findViewById(R.id.tvjudul);
        tvtanggal = findViewById(R.id.tvtanggal);
        tvpenulis = findViewById(R.id.tvpenulis);
        tvdesc = findViewById(R.id.tvdesc);

        new getDataDetail().execute();
    }

    private class getDataDetail extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(urldata);
            if (jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray everything = jsonObject.getJSONArray("data");
                    for (int i = 0; 0 < everything.length(); i++){
                        JSONObject c = everything.getJSONObject(i);

                         judul = c.getString("judul");
                         penulis = c.getString("penulis");
                         tahun_terbit = c.getString("tahun_terbit");
                         keterangan = c.getString("description");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            tvjudul.setText(judul);
            tvdesc.setText(keterangan);
            tvtanggal.setText(tahun_terbit);
            tvpenulis.setText(penulis);
        }
    }

}
