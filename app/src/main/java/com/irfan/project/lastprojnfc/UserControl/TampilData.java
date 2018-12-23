package com.irfan.project.lastprojnfc.UserControl;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.irfan.project.lastprojnfc.AllData.HttpHandler;
import com.irfan.project.lastprojnfc.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilData extends AppCompatActivity implements View.OnClickListener {

    FrameLayout frlayout;
    ListView lcview;
    FloatingActionButton fab, fab2;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<HashMap<String, String>> dataList;
    String urldata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampil_data);
        frlayout = findViewById(R.id.frlayout);
        lcview = findViewById(R.id.lcView);
        fab = findViewById(R.id.tambahdata);
        fab2 = findViewById(R.id.showprofile);
        urldata = "http://simplesample.ngopidevteam.com/showdata.php";

        layoutManager = new LinearLayoutManager(this);
        dataList = new ArrayList<>();
        new GetDataList().execute();
        fab.setOnClickListener(this);
        fab2.setOnClickListener(this);

        lcview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle b = new Bundle();
                b.putString("judul",dataList.get(i).get("judul"));
                startActivityForResult(new Intent(TampilData.this, Detail_Data.class).putExtras(b), 10021);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tambahdata:{
                startActivity(new Intent(TampilData.this, Tambah_Data.class));
            }break;
            case R.id.showprofile:{
                startActivity(new Intent(TampilData.this, Profile.class));
            }
        }
    }

    private class GetDataList extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(urldata);

            Log.e("Tampildata class", "Response from url: " + jsonStr);
            if (jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray everything = jsonObject.getJSONArray("data");

                    for (int i = 0; i < everything.length(); i++) {
                        JSONObject c = everything.getJSONObject(i);

                        String judul = c.getString("judul");
                        String penulis = c.getString("penulis");
                        String tahun_terbit = c.getString("tahun_terbit");
                        HashMap<String, String> buku = new HashMap<>();

                        buku.put("judul", judul);
                        buku.put("penulis", penulis);
                        buku.put("tahun_terbit", tahun_terbit);

                        // adding contact to contact list
                        dataList.add(buku);
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
            ListAdapter adapter = new SimpleAdapter(getApplicationContext(), dataList, R.layout.for_list,
                    new String[]{"judul", "penulis", "tahun_terbit"}
                    , new int[]{R.id.tvjdl, R.id.tvpenulis, R.id.tvtanggal});

            lcview.setAdapter(adapter);
        }
    }
}
