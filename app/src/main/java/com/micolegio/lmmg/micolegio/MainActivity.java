package com.micolegio.lmmg.micolegio;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = new Usuario();

        setContentView(R.layout.login_main);

        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void login(View v) {
        String un = username.getText().toString();
        String pw = password.getText().toString();
        String result = "";

        Toast.makeText(this, "Intentando entrar....", Toast.LENGTH_SHORT).show();

        result = usuario.login(un, pw);

        if (result.equalsIgnoreCase("OK")) {
            Toast.makeText(this, "Usuario Logeado", Toast.LENGTH_SHORT).show();
            Intent startIntent = new Intent(MainActivity.this, MenuprincipalActivity.class);
            startIntent.putExtra("usuario", usuario.getNombre());
            startActivity(startIntent);
            overridePendingTransition(R.transition.left_in, R.transition.left_out);
        } else {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }

    public void cierraTeclado(View v){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
