package com.micolegio.lmmg.micolegio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityCreacionProfesor extends AppCompatActivity {
    private EditText nombre;
    private EditText email;
    private EditText telefono;
    private EditText username;
    private EditText pass1;
    private EditText pass2;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_profesor);
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

        nombre = (EditText) findViewById(R.id.eNombre);
        email = (EditText) findViewById(R.id.eEmail);
        telefono = (EditText) findViewById(R.id.eTelefono);
        username = (EditText) findViewById(R.id.eUsuario);
        pass1 = (EditText) findViewById(R.id.ePass1);
        pass2 = (EditText) findViewById(R.id.ePass2);
        usuario = new Usuario();

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


    public void crear(View v) {
        String result = "";

        Toast.makeText(this, "Registrando nuevo profesor....", Toast.LENGTH_SHORT).show();

        if (pass1.getText().toString().equals(pass2.getText().toString())) {

            result = usuario.registro(nombre.getText().toString(), email.getText().toString(), telefono.getText().toString(), username.getText().toString(), pass1.getText().toString(), "Profesor");

            if (result.equalsIgnoreCase("OK")) {
                Toast.makeText(this, "Nuevo profesor registrado correctamente", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
            } else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Los password no coinciden....", Toast.LENGTH_SHORT).show();
        }

    }
}
