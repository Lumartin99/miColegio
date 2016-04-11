package com.micolegio.lmmg.micolegio;


import android.net.http.HttpResponseCache;
import android.os.AsyncTask;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by LuisMaria on 29/03/2016.
 */
public class Login extends AsyncTask<String, Void, String> {

    private String username;
    private String password;

    public Login() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        username = arg0[0];
        password = arg0[1];

        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {

            data = "&username=" + URLEncoder.encode(username, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://micolegio.no-ip.org/login.php";
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
/*
        try {
            JSONObject jsonResult = new JSONObject(result);
            JSONArray resultados = jsonResult.getJSONArray("result");

            if (resultados.length()==0){
                result="Usuario incorrecto";
            }
            for (int i = 0; i < resultados.length(); i++){
                JSONObject fila = resultados.getJSONObject(i);
                String username_bd = fila.getString("username");
                String password_bd = fila.getString("password");
                if (password.equals(password_bd)) {
                    result="OK";
                }
                else {
                    result="Password incorrecto";
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;*/
    }

    @Override
    protected void onPostExecute(String result) {
/*
        try {
            JSONObject jsonResult = new JSONObject(result);
            JSONArray resultados = jsonResult.getJSONArray("result");

            for (int i = 0; i < resultados.length(); i++){
                JSONObject fila = resultados.getJSONObject(i);
                String username_bd = fila.getString("username");
                String password_bd = fila.getString("password");
                if (password.equals(password_bd)) {
                    //Toast.makeText(context, "Login correto....", Toast.LENGTH_SHORT).show();
                    result="OK";
                }
                else {
                    //Toast.makeText(context, "Password incorrecto....", Toast.LENGTH_SHORT).show();
                    result="NOK";
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        super.onPostExecute(result);

    }
}
