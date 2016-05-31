package com.micolegio.lmmg.micolegio;


import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AsyncEnviaMensaje extends AsyncTask<String, Void, String> {

    private String idSen;
    private String idRec;
    private String asunto;
    private String message;


    public AsyncEnviaMensaje() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        idSen = arg0[0];
        idRec = arg0[1];
        asunto = arg0[2];
        message = arg0[3];



        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {

            data = "&idSen=" + URLEncoder.encode(idSen, "UTF-8");
            data += "&idRec=" + URLEncoder.encode(idRec, "UTF-8");
            data += "&asunto=" + URLEncoder.encode(asunto, "UTF-8");
            data += "&message=" + URLEncoder.encode(message, "UTF-8");
            data += "&date=" + URLEncoder.encode(getDateTime(), "UTF-8");

            link = "http://micolegio.no-ip.org/enviaMensaje.php";
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

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
