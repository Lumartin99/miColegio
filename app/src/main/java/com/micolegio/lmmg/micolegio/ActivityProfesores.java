package com.micolegio.lmmg.micolegio;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class ActivityProfesores extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private ListView lista;
    private Adaptador_lista adaptador;
    private ArrayList<Usuario> profesores;
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_pofesor);
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

        mSearchView = (SearchView) findViewById(R.id.searchView);

        try {
            resultado = new AsyncGetUsuarios().execute("Profesor").get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        profesores = new ArrayList<>();

        try {
            JSONObject jsonResult = new JSONObject(resultado);
            JSONArray resultados = jsonResult.getJSONArray("result");

            if (resultados.length()!=0) {
                for (int i = 0; i < resultados.length(); i++) {
                    JSONObject fila = resultados.getJSONObject(i);
                    profesores.add(new Usuario(Integer.parseInt(fila.getString("idUsers")), fila.getString("username"), fila.getString("name"), fila.getString("phone"), fila.getString("type"), "", fila.getString("email")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        lista = (ListView) findViewById(R.id.listViewProfesores);

        adaptador = new Adaptador_lista(this, R.layout.e_profesor, profesores){
            @Override
            public void onEntrada(Usuario entrada, View view) {
                if (entrada != null) {
                    TextView nombre = (TextView) view.findViewById(R.id.nombreProfesor);
                    TextView id = (TextView) view.findViewById(R.id.userID);
                    if (nombre != null)
                        nombre.setText(entrada.getNombre());
                    id.setText(String.valueOf(entrada.getIdUser()));
                }
            }
        };

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Usuario temporal;
                temporal = (Usuario) lista.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), temporal.getNombre() + "\n" + temporal.getEmail() + "\n" + temporal.getTelefono() + "\n" , Toast.LENGTH_LONG).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                final Integer posicion = position;
                PopupMenu popup = new PopupMenu(ActivityProfesores.this, view);
                popup.getMenuInflater().inflate(R.menu.menu_usuarios, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nuevo_usuario:
                                Intent creaProfesores = new Intent(ActivityProfesores.this, ActivityCreacionProfesor.class);
                                startActivity(creaProfesores);
                                overridePendingTransition(R.transition.left_in, R.transition.left_out);
                                break;
                            case R.id.enviar_mensaje:
                                Usuario temporal1 = (Usuario) lista.getItemAtPosition(posicion);
                                ComunicadorUsuario.setUsuario(temporal1);
                                Intent envMensaje = new Intent(ActivityProfesores.this, ActivityEnviarMensaje.class);
                                startActivity(envMensaje);
                                overridePendingTransition(R.transition.left_in, R.transition.left_out);
                                break;
                            case R.id.ver_usuario:
                                Toast.makeText(ActivityProfesores.this, "Ver Datos ampliados", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.modificar_usuario:
                                Usuario temporal2 = (Usuario) lista.getItemAtPosition(posicion);
                                ComunicadorUsuario.setUsuario(temporal2);
                                Intent modProfesor = new Intent(ActivityProfesores.this, ActivityModificarProfesor.class);
                                startActivity(modProfesor);
                                overridePendingTransition(R.transition.left_in, R.transition.left_out);
                                break;
                            case R.id.borrar_usuario:
                                AlertDialog.Builder borrar = new AlertDialog.Builder(new ContextThemeWrapper(ActivityProfesores.this, R.style.myDialog));

                                borrar.setMessage("¿Confirma que desea borrar este usuario?").setTitle("Eliminar");
                                borrar.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(ActivityProfesores.this, "Borrando profesor", Toast.LENGTH_LONG).show();
                                        Usuario temporal;
                                        temporal = (Usuario) lista.getItemAtPosition(posicion);
                                        try {
                                            resultado = new AsyncDeleteUsuario().execute(String.valueOf(temporal.getIdUser())).get();
                                            if (resultado.contentEquals("OK")) {
                                                adaptador.removeItem(posicion);
                                                adaptador.notifyDataSetChanged();
                                                Toast.makeText(ActivityProfesores.this, "Profesor borrado", Toast.LENGTH_LONG).show();
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                borrar.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });
                                borrar.show();
                                break;
                        }
                        return true;
                    }

                });
                popup.show();
                return true;
            }
        });

        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(false);
        mSearchView.setQueryHint("Buscar Profesor");

        mSearchView.clearFocus();

    }

    public boolean onQueryTextChange(String newText) {
        adaptador.filtrar(newText);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        adaptador.filtrar(query);
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        mSearchView.clearFocus();

        try {
            resultado = new AsyncGetUsuarios().execute("Profesor").get();

        } catch (Exception e) {
            e.printStackTrace();
        }

        profesores = new ArrayList<>();

        try {
            JSONObject jsonResult = new JSONObject(resultado);
            JSONArray resultados = jsonResult.getJSONArray("result");

            if (resultados.length()!=0) {
                for (int i = 0; i < resultados.length(); i++) {
                    JSONObject fila = resultados.getJSONObject(i);
                    profesores.add(new Usuario(Integer.parseInt(fila.getString("idUsers")), fila.getString("username"), fila.getString("name"), fila.getString("phone"), fila.getString("type"), "", fila.getString("email")));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adaptador.cambiaLista(profesores);
        adaptador.notifyDataSetChanged();

    }


    public void cierraTeclado(View v){
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}
