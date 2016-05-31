package com.micolegio.lmmg.micolegio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityEnviarMensaje extends AppCompatActivity {
    private EditText asunto;
    private EditText mensaje;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensaje);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        asunto = (EditText) findViewById(R.id.eAsunto);
        mensaje = (EditText) findViewById(R.id.eMensaje);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public void cierraTeclado(View v){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }



    public void enviar(View v){
        String result = "";

        Toast.makeText(this, "Enviando mensaje....", Toast.LENGTH_SHORT).show();
        try {
            result = new AsyncEnviaMensaje().execute(String.valueOf(ComunicadorUsuarioLogged.getUsuario().getIdUser()), String.valueOf(ComunicadorUsuario.getUsuario().getIdUser()), asunto.getText().toString(), mensaje.getText().toString()).get();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (result.equalsIgnoreCase("OK")) {
            Toast.makeText(this, "Mensaje enviado correctamente", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelar(View v){
        onBackPressed();
    }
}
