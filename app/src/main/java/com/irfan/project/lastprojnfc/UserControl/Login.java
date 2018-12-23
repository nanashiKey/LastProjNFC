package com.irfan.project.lastprojnfc.UserControl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.irfan.project.lastprojnfc.AllData.HttpHandler;
import com.irfan.project.lastprojnfc.AllData.PrefsManager;
import com.irfan.project.lastprojnfc.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText etUname, etPass;
    Button btnLogin;
    String link = "http://simplesample.ngopidevteam.com/login.php";
    private ProgressDialog pDialog;

    String getnama, getpass, dataa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUname = findViewById(R.id.etUname);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        pDialog = new ProgressDialog(this);
        //setClick to Button
        btnLogin.setOnClickListener(this);

        boolean getlogin = PrefsManager.sharedInstance(this).getStatusLogin();
        if(getlogin){
            Intent intent = new Intent(Login.this, TampilData.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin :{
                new GetDataEmail().execute();
            }
        }
    }

    private class GetDataEmail extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String uname = etUname.getText().toString();
            String pass = etPass.getText().toString();
            String url = link+"?username="+uname+"&password="+pass;

            HttpHandler sh = new HttpHandler();
            String jsonstr = sh.makeServiceCallGet(url);

            Log.e("Login Class:","url message:\n"+jsonstr);
            if(jsonstr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonstr);

                     getnama = jsonObject.getJSONObject("data")
                            .getString("username");
                     getpass = jsonObject.getJSONObject("data")
                            .getString("pas");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(getnama == null || getpass == null){
                getnama = "username atau password salah";
            }else{
                Intent intent = new Intent(Login.this, TampilData.class);
                startActivity(intent);
                PrefsManager.sharedInstance(getApplicationContext()).setStatusLogin(true);
                PrefsManager.sharedInstance(getApplicationContext()).setNama(getnama);
                finish();
            }
            pDialog.dismiss();
            Toast.makeText(Login.this, "halo\n"+getnama, Toast.LENGTH_SHORT).show();
            //
        }
    }


}
