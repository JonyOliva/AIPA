package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aipa.R;

import java.util.ArrayList;

import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Gestion.SintomasGestion;
import Models.FichaDiaria;
import Models.Ingrediente;
import Models.Sintoma;

public class FichaAnteriorActivity extends AppCompatActivity {
    TextView Fecha;
    TextView Comentario;
    TextView Ejercicio;
    TextView Sintomas;
    ListView lstIngredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_anterior);
        Ejercicio = (TextView)findViewById(R.id.txtFAEjercicio);
        Comentario= (TextView)findViewById(R.id.txtFAComentario);
        Fecha = (TextView)findViewById(R.id.txtFAfecha);
        Sintomas = (TextView)findViewById(R.id.txtFASintomas);
lstIngredientes=(ListView)findViewById(R.id.lstFAingredientes);

        String fecha = getIntent().getStringExtra("fecha");
        if (fecha != null){
            fill(fecha);
        }
        else{
            redirectCalendar();
        }

    }

    public void fill(String fecha){
        FichasGestion fg = new FichasGestion();
        FichaDiaria ficha = fg.get(fecha);
        
        Fecha.setText(fecha);
        Ejercicio.setText(Integer.toString(ficha.getTiempoEjercicio()));
        Comentario.setText(ficha.getComentario());

        SintomasGestion sg = new SintomasGestion();
        ArrayList<Sintoma> listasintomas = sg.getAll();

        Sintomas.setText(listasintomas.get(ficha.getSintoma().getIdSintoma()).getDescripcion());
        fillIngredients(ficha.getIdFicha());
    }

    public void fillIngredients(int idficha){
        IngredientesGestion ig = new IngredientesGestion();
        ArrayList <Ingrediente> listaingredientes = ig.getIngredientesXFicha(idficha);

        if (listaingredientes!=null){

            ArrayAdapter adapter = new ArrayAdapter<Ingrediente>(this,R.layout.lista_ingredientes,listaingredientes);
            lstIngredientes.setAdapter(adapter); }
    }


    public void redirectCalendar(){
        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }
}