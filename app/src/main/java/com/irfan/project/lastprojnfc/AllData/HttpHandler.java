package com.irfan.project.lastprojnfc.AllData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {

    private static final String TAG = HttpHandler.class.getSimpleName();

    public HttpHandler(){
        //nothing
    }
    public String makeServiceCall(String reqURL){
        String response = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamtoString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    public String makeServiceCallGet(String reqURL){
        String response = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamtoString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private String convertStreamtoString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();

        String line;
        try{
            while((line = reader.readLine()) != null){
                sb.append(line).append('\n');
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
