package com.micolegio.lmmg.micolegio;

import android.os.AsyncTask;

/**
 * Created by LuisMaria on 30/03/2016.
 */
public class Usuario {
    String username;
    String nombre;
    String tipo;
    String email;
    boolean logged;

    public Usuario(){
        tipo = "Director";
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

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
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
        if (resultado.equalsIgnoreCase("OK")){
            logged=true;
        }

        return resultado;
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
