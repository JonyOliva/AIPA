package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aipa.R;

import java.util.ArrayList;

import Adapter.IngredienteAdapter;
import Adapter.IngredienteListaAdapter;
import Gestion.IngredientesGestion;
import Gestion.IngredientesXFichaGestion;
import Gestion.UsuariosGestion;
import Models.Ingrediente;
import Models.IngredientesXFicha;
import Models.Usuario;

public class BusquedaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    //Vistas
    ListView lvSelected;
    RecyclerView rvLista;
    Button btnAgregar;
    //Adapters
    IngredienteAdapter ingAdapter;
    //Vars
    ArrayList<Ingrediente> ingredientsFound;
    ArrayList<Ingrediente> listaFiltrada;
    ArrayList<Ingrediente> selected = new ArrayList<>();
    Usuario user = new UsuariosGestion().read();
    Integer idFicha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Intent i = getIntent();
        idFicha = i.getIntExtra("idFicha",0);

        lvSelected = (ListView) findViewById(R.id.listIngredientes);
        lvSelected.setAdapter(new ArrayAdapter<Ingrediente>(getApplicationContext(), R.layout.ingrediente_lista_layout, selected));

        btnAgregar = (Button) findViewById(R.id.btnAgregarIngrediente);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IngredientesXFichaGestion ixfGestion  = new IngredientesXFichaGestion();
                Intent i = new Intent(getApplicationContext(), FichaDiariaActivity.class);

                for (Ingrediente ing : selected) {
                    IngredientesXFicha ixf = new IngredientesXFicha(idFicha, ing.getIdIngrediente());
                    if(!ixfGestion.exist(ixf)) {
                        ixfGestion.save(ixf);
                    }
                }
                Toast.makeText(getApplicationContext(), "Ingredientes agregados",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        ingredientsFound = new IngredientesGestion().getIngredientesXFase(1);
        ingAdapter = new IngredienteAdapter(ingredientsFound, selected, lvSelected, btnAgregar, getApplicationContext());

        rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(ingAdapter);
        rvLista.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.busqueda_layout, menu);
        MenuItem item = menu.findItem(R.id.buscador);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(this);
        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                ingAdapter.setFilter(new IngredientesGestion().getIngredientesXFase(1));
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                ingAdapter.setFilter(ingredientsFound);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        try {
            rvLista.setVisibility(View.VISIBLE);
            if (s.length() == 0) {
                rvLista.setVisibility(View.INVISIBLE);
            }

            listaFiltrada = filter(ingredientsFound, s);
            ingAdapter.setFilter(listaFiltrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public ArrayList<Ingrediente> filter(ArrayList<Ingrediente> original, String busqueda) {
        ArrayList<Ingrediente> filtrados = new ArrayList<>();
        try {
            busqueda = busqueda.toLowerCase();

            for (Ingrediente ingrediente : original) {
                String nombre = ingrediente.getNombre().toLowerCase();

                if (nombre.contains(busqueda)) {
                    filtrados.add(ingrediente);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filtrados;
    }


}