package com.micolegio.lmmg.micolegio;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class ActivityModificarProfesor extends AppCompatActivity {

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
        usuario = ComunicadorUsuario.getUsuario();

        nombre = (EditText) findViewById(R.id.eNombre);
        nombre.setText(usuario.getNombre());
        email = (EditText) findViewById(R.id.eEmail);
        email.setText(usuario.getEmail());
        telefono = (EditText) findViewById(R.id.eTelefono);
        telefono.setText(usuario.getTelefono());
        username = (EditText) findViewById(R.id.eUsuario);
        username.setText(usuario.getUsername());
        username.setEnabled(false);
        pass1 = (EditText) findViewById(R.id.ePass1);
        pass2 = (EditText) findViewById(R.id.ePass2);
        Button b = (Button) findViewById(R.id.bCrear);
        b.setText("Modificar");

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

        Toast.makeText(this, "Modificando profesor....", Toast.LENGTH_SHORT).show();
        if (pass1.getText().toString().equals(pass2.getText().toString())) {
            try {
                result = new AsyncModProfesor().execute(String.valueOf(usuario.getIdUser()), nombre.getText().toString(), email.getText().toString(), telefono.getText().toString(), pass1.getText().toString(), "").get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result.equalsIgnoreCase("OK")) {
                Toast.makeText(this, "Profesor modificado", Toast.LENGTH_SHORT).show();
                super.onBackPressed();
            } else {
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Los password no coinciden....", Toast.LENGTH_SHORT).show();
        }
    }
}
