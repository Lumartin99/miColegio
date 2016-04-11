package com.micolegio.lmmg.micolegio;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LuisMaria on 30/03/2016.
 */
public class Usuario {
    int idUser;
    String username;
    String nombre;
    String telefono;
    String tipo;
    String regId;
    String email;
    boolean logged;

    public Usuario(){
        logged=false;
    }

    public void setUsername(String username1){
        username = username1;
    }

    public void setNombre(String nombre1){
        nombre = nombre1;
    }

    public void setTipo(String tipo1){
        tipo = tipo1;
    }

    public void setEmail(String email1){
        email = email1;
    }

    public void setLogged(boolean logged1){
        logged = logged1;
    }

    public void setIdUser(int id){
        idUser = id;
    }

    public void setTelefono(String tlf) {
        telefono = tlf;
    }

    public void setRegId(String id){
        regId = id;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRegId() {
        return regId;
    }

    public String getUsername() {
        return username;
    }

    public boolean isLogged(){
        return logged;
    }

    public String login(String vUsername, String vPassword){
        String resultado = "Error";

        try {
        resultado = new Login().execute(vUsername, vPassword).get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonResult = new JSONObject(resultado);
            JSONArray resultados = jsonResult.getJSONArray("result");

            if (resultados.length()==0){
                return "Usuario incorrecto";
            }
            for (int i = 0; i < resultados.length(); i++){
                JSONObject fila = resultados.getJSONObject(i);

                String password_bd = fila.getString("password");
                if (vPassword.equals(password_bd)) {
                    setNombre(fila.getString("username"));
                    setEmail(fila.getString("email"));
                    setNombre(fila.getString("name"));
                    setIdUser(Integer.parseInt(fila.getString("idUsers")));
                    setRegId(fila.getString("regId"));
                    setTipo(fila.getString("type"));
                    logged=true;
                    return "OK";

                }
                else {
                    return "Password incorrecto";
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Error inesperado";
    }

    public String Registro(String vUsername, String vPassword, String vName, String vTipo){
        String resultado = "Error";

        try {
            resultado = new Signup().execute(vUsername, vPassword, vName, vTipo).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }


}
