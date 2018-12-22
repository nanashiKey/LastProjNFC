package com.irfan.project.lastprojnfc.UserControl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.irfan.project.lastprojnfc.AllData.HttpHandler;
import com.irfan.project.lastprojnfc.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    EditText etUname, etPass;
    Button btnLogin;
    String link = "http://simplesample.ngopidevteam.com/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUname = findViewById(R.id.etUname);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);

    }

    private class GetDataEmail extends AsyncTask<Void, Void, Void>{
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
                    String getnama = jsonObject.getJSONObject("data")
                            .getString("username");
                    String getpass = jsonObject.getJSONObject("data")
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
            //
        }
    }
}
