package com.micolegio.lmmg.micolegio;

/**
 * Created by LuisMaria on 29/03/2016.
 */

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



/**
 * Created by LuisMaria on 28/03/2016.
 */

public class AsyncSignup extends AsyncTask<String, Void, String> {


    public AsyncSignup() {
    }

    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... arg0) {
        String name = arg0[0];
        String email = arg0[1];
        String telefono = arg0[2];
        String username = arg0[3];
        String password = arg0[4];
        String type = arg0[5];



        String link;
        String data;
        BufferedReader bufferedReader;
        String result;


        try {
            data = "&name=" + URLEncoder.encode(name, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&phone=" + URLEncoder.encode(telefono, "UTF-8");
            data += "&username=" + URLEncoder.encode(username, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            data += "&type=" + URLEncoder.encode(type, "UTF-8");

            link = "http://micolegio.no-ip.org/signup.php";
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream dStream = new DataOutputStream(con.getOutputStream());
            dStream.writeBytes(data);
            dStream.flush();
            dStream.close();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            bufferedReader.close();
            con.disconnect();


            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

}

