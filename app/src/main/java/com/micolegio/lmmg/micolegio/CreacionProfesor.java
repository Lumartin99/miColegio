package com.micolegio.lmmg.micolegio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class CreacionProfesor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_profesor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onBackPressed() {
        Intent vuelve = new Intent(CreacionProfesor.this, MenuprincipalActivity.class);
        startActivity(vuelve);
        overridePendingTransition(R.transition.left_in, R.transition.left_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Intent vuelve = new Intent(CreacionProfesor.this, MenuprincipalActivity.class);
                startActivity(vuelve);
                overridePendingTransition(R.transition.left_in, R.transition.left_out);
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/return true;
    }
}
