package com.micolegio.lmmg.micolegio;

/**
 * Created by LuisMaria on 08/04/2016.
 */
public class ComunicadorUsuario {
    private static Usuario usuario = null;

    public static void setUsuario(Usuario user) {
        usuario = user;
    }

    public static Usuario getUsuario() {
        return usuario;
    }
}
