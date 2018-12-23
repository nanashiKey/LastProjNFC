package com.irfan.project.lastprojnfc.AllData;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.tech.NfcA;

public class PrefsManager {
    SharedPreferences prefs;
    Context ctx;
    private static PrefsManager instance;
    private String statusSplash = "StatusSplash";
    private String statusLogin = "Login";
    private String Nama = "NAMA";



    public static PrefsManager sharedInstance(Context ctx_){
        if(instance == null){
            instance = new PrefsManager(ctx_);
        }
        return instance;
    }
    private PrefsManager(Context context){
        ctx = context;
        this.prefs = context.getSharedPreferences("LATIHAN",
                Context.MODE_PRIVATE);
    }

    //set status splash screen
    public void setStatusSplash(boolean status){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(statusSplash, status);
        editor.apply();
    }

    //get status splash screen
    public boolean getStatusSplash(){
        return prefs.getBoolean(statusSplash, false);
    }

    public void setStatusLogin(boolean status){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(statusLogin, status);
        editor.apply();
    }

    public boolean getStatusLogin(){
        return prefs.getBoolean(statusLogin, false);
    }

    public void setNama(String nama){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Nama, nama);
        editor.apply();
    }

    public String getNama(){
        return prefs.getString(Nama, "");
    }
}
