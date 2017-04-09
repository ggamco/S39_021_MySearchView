package com.gmbdesign.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String[] array = {"Gustavo", "Martin", "Eva", "Carlos", "Fermin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.fila, array);

        ListView lv = (ListView) findViewById(R.id.lista);
        lv.setAdapter(listAdapter);

        SearchView sv = (SearchView) findViewById(R.id.cajaBusqueda);
        //Para setear un listener al searchview usamos este concreto
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //Este metodo solo funciona cuando hacemos submit
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("TAG", "Cadena submit: " + query);
                return false;
            }

            //Este metodo hace un busqueda cada vez que metes un nuevo caracter
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("TAG", "Cadena query: " + newText);

                //TODO Filtrar la lista para que solo salgan los nombres que coinciden
                //contenedor de coincidentes
                List<String> listaCoincidencias = new ArrayList<String>();

                //Recorro el array de datos y si coinciden con la busqueda los almaceno
                for(String nombre : array){
                    if(nombre.toLowerCase().contains(newText)){
                        listaCoincidencias.add(nombre);
                    }
                }

                ListView lv = (ListView) findViewById(R.id.lista);
                ListAdapter listAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.fila, listaCoincidencias);
                lv.setAdapter(listAdapter);
                return false;
            }
        });
    }
}
