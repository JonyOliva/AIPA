package com.example.aipa.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aipa.R;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import AsyncTasks.GetSintomas;
import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Gestion.SintomasGestion;
import Models.FichaDiaria;
import Models.Ingrediente;
import Models.Sintoma;
import Service.SintomasService;

public class SintomasActivity extends AppCompatActivity {
Spinner spSintomas;
TextView txtFecha;
FichaDiaria fichaAnterior;
String today;
    FichasGestion fg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);
        spSintomas = (Spinner)findViewById(R.id.spSintomas);
        txtFecha = (TextView)findViewById(R.id.txtFechaSintomas);
        GetSintomas addSymptoms = new GetSintomas(spSintomas, getApplicationContext());
        addSymptoms.execute();
        today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        getFicha();
        txtFecha.setText(fichaAnterior.getFecha());
    }

    public void getFicha(){
        fg = new FichasGestion();
        fichaAnterior = fg.getAnterior(today);
        if (fichaAnterior == null){
          redirectMain();
        }
    }

    public void guardarSintomas(View view){

        SintomasGestion sg= new SintomasGestion();
        IngredientesGestion ig = new IngredientesGestion();
        Sintoma sintoma = sg.get(spSintomas.getSelectedItemPosition()+1);

        //sintoma.setIdSintoma(spSintomas.getSelectedItemPosition()+1);
        fichaAnterior.setSintoma(sintoma);
        fg.update(fichaAnterior);

        ArrayList<Ingrediente> listaingredientes = ig.getIngredientesXFicha(fichaAnterior.getIdFicha());

        if (listaingredientes != null){
        for (Ingrediente i:
            listaingredientes ) {
           int nuevopuntaje= i.getPuntaje() + sintoma.getModificadorPuntaje();
           if(nuevopuntaje > 5) nuevopuntaje = 5;
           if(nuevopuntaje < 0 ) nuevopuntaje = 0;
           if(nuevopuntaje >=0 && nuevopuntaje<=5){
               i.setPuntaje(nuevopuntaje);
               ig.update(i);
           }}

        }
        redirectMain();
    }

    public void redirectMain(){
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

}