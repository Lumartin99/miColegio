package com.micolegio.lmmg.micolegio;


import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class AsyncGetUsuarios extends AsyncTask<String, Void, String> {

    private String tipo;

    public AsyncGetUsuarios() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        tipo = arg0[0];


        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {

            data = "&tipo=" + URLEncoder.encode(tipo, "UTF-8");

            link = "http://micolegio.no-ip.org/select.php";
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
