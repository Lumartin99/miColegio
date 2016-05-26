package com.micolegio.lmmg.micolegio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

public class ModificarPofesor extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView mSearchView;
    private ListView lista;

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

        ArrayList<Usuario> profesores = new ArrayList<Usuario>();

        profesores.add(new Usuario(0,"pepe","pepe","650565595","profesor","","pepe@gmail.com"));
        profesores.add(new Usuario(1,"juan","juan","650565595","profesor","","pepe@gmail.com"));
        profesores.add(new Usuario(2,"pablo","pablo","650565595","profesor","","pepe@gmail.com"));
        profesores.add(new Usuario(3,"pedro","pedro","650565595","profesor","","pepe@gmail.com"));

        ListView lista = (ListView) findViewById(R.id.listViewProfesores);
        lista.setAdapter(new Adaptador_lista(this, R.layout.e_profesor, profesores){
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView nombre = (TextView) view.findViewById(R.id.nombreProfesor);
                    if (nombre != null)
                        nombre.setText(((Usuario) entrada).getNombre());
                }
            }
        });

        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Buscar Profesor");

        lista.setTextFilterEnabled(true);
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            lista.clearTextFilter();
        } else {
            lista.setFilterText(newText.toString());
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
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


}
