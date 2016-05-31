package com.micolegio.lmmg.micolegio;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMenuPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentPadres.OnFragmentInteractionListener, FragmentProfesores.OnFragmentInteractionListener, FragmentAlumnos.OnFragmentInteractionListener {

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
        TextView tipo = (TextView) header.findViewById(R.id.head_tipo);
        TextView nombre = (TextView) header.findViewById(R.id.head_nombre);
        ImageView img = (ImageView) header.findViewById(R.id.head_iconoRol);

        switch (usuario.getTipo()){
            case "Director":
                tipo.setText("Director");
                img.setImageResource(R.drawable.ic_director_v2);
                navigationView.inflateMenu(R.menu.menu_director);
                nombre.setText(usuario.getNombre());
                break;
            case "Padre":
                tipo.setText("Familia o tutor");
                img.setImageResource(R.drawable.ic_familia_v2);
                navigationView.inflateMenu(R.menu.menu_padres);
                nombre.setText(usuario.getNombre());
                break;
            case "Alumno":
                tipo.setText("Alumno");
                img.setImageResource(R.drawable.ic_alumno_v2);
                navigationView.inflateMenu(R.menu.menu_alumnos);
                nombre.setText(usuario.getNombre());
                break;
            case "Profesor":
                tipo.setText("Profesor");
                img.setImageResource(R.drawable.ic_profes_v2);
                navigationView.inflateMenu(R.menu.menu_profesor);
                nombre.setText(usuario.getNombre());
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
        /*Fragment fragment = null;
        Class fragmentClass = FragmentProfesores.class;
*/
        switch(item.getItemId()){
            case R.id.nav_profesores:
                //fragmentClass = FragmentProfesores.class;
                Intent profesores = new Intent(ActivityMenuPrincipal.this, ActivityProfesores.class);
                startActivity(profesores);
                overridePendingTransition(R.transition.left_in, R.transition.left_out);
                break;
            case R.id.nav_padres:
                //fragmentClass = FragmentPadres.class;
                break;
            case R.id.nav_alumnos:
                //fragmentClass = FragmentAlumnos.class;
                break;
            case R.id.nav_logout:
                salir();
                break;
            default:
                //fragmentClass = FragmentProfesores.class;
        }

        /*try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenido_principal, fragment).commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
*/
        return true;
    }

    public void cierraTeclado(View v){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void salir(){
        AlertDialog.Builder salir = new AlertDialog.Builder(this);

        salir.setMessage("¿Confirme que desea cerrar sesión? (Si cierra sesión dejará de recibir notificaciones)").setTitle("Cerrar");
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
        finish();
    }

    public void crearProfesor(View v){
        Intent creaProfesores = new Intent(ActivityMenuPrincipal.this, ActivityCreacionProfesor.class);
        startActivity(creaProfesores);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    public void modificarProfesor(View v){
        Intent modificaProfesores = new Intent(ActivityMenuPrincipal.this, ActivityModificarProfesor.class);
        startActivity(modificaProfesores);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    public void crearPadre(View v){
        Intent creaPadre = new Intent(ActivityMenuPrincipal.this, ActivityCreacionPadre.class);
        startActivity(creaPadre);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    public void modificarPadre(View v){
        Intent modificaPadre = new Intent(ActivityMenuPrincipal.this, ActivityModificarPadre.class);
        startActivity(modificaPadre);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    public void crearAlumno(View v){
        Intent creaPadre = new Intent(ActivityMenuPrincipal.this, ActivityCreacionAlumno.class);
        startActivity(creaPadre);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    public void modificarAlumno(View v){
        Intent modificaPadre = new Intent(ActivityMenuPrincipal.this, ActivityModificarAlumno.class);
        startActivity(modificaPadre);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    @Override
    public void onPadresFragmentInteraction(Uri uri){

    }

    @Override
    public void onProfesoresFragmentInteraction(Uri uri){

    }

    @Override
    public void onAlumnosFragmentInteraction(Uri uri){

    }
}


