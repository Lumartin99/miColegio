package com.micolegio.lmmg.micolegio;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuprincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EditText username;
    private EditText password;
    private EditText name;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = new Usuario();
        usuario = ComunicadorUsuario.getUsuario();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView text = (TextView) header.findViewById(R.id.head_tipo);
        ImageView img = (ImageView) header.findViewById(R.id.head_iconoRol);

        switch (usuario.getTipo()){
            case "Director":
                text.setText("Director");
                img.setImageResource(R.drawable.ic_director_v2);
                navigationView.inflateMenu(R.menu.menu_director);
                break;
            case "Padre":
                text.setText("Familia o tutor");
                img.setImageResource(R.drawable.ic_familia_v2);
                navigationView.inflateMenu(R.menu.menu_padres);
                break;
            case "Alumno":
                text.setText("Alumno");
                img.setImageResource(R.drawable.ic_alumno_v2);
                navigationView.inflateMenu(R.menu.menu_alumnos);
                break;
            case "Profesor":
                text.setText("Profesor");
                img.setImageResource(R.drawable.ic_profes_v2);
                navigationView.inflateMenu(R.menu.menu_profesor);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        salir();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profesores) {
            Intent creaProfesores = new Intent(MenuprincipalActivity.this, CreacionProfesor.class);
            startActivity(creaProfesores);
            overridePendingTransition(R.transition.left_in, R.transition.left_out);
        } else if (id == R.id.nav_logout) {
            salir();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void registrar(View v){
        String un = username.getText().toString();
        String nm = name.getText().toString();
        String pw = password.getText().toString();
        String tp = "tipo";
        String result = "Error";

        Toast.makeText(this, "Registrando nuevo usuario....", Toast.LENGTH_SHORT).show();

        result = usuario.Registro(un, pw, nm, tp);
        if (result.equalsIgnoreCase("Registro correcto")) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        }
    }

    public void cierraTeclado(View v){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void salir(){
        AlertDialog.Builder salir = new AlertDialog.Builder(this);

        salir.setMessage("¿Confirme que desea cerrar sesión? (Si cierra sesión dejará de recivir notificaciones)").setTitle("Cerrar");
        salir.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                aceptar_salida();
            }
        });
        salir.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        salir.show();
    }

    public void aceptar_salida(){
        Toast.makeText(this, "Saliendo....", Toast.LENGTH_SHORT).show();
        Intent vueltaLogin = new Intent(MenuprincipalActivity.this, MainActivity.class);
        startActivity(vueltaLogin);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

}


