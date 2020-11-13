package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aipa.R;

import java.util.ArrayList;

import Gestion.FichasGestion;
import Gestion.SintomasGestion;
import Models.FichaDiaria;
import Models.Sintoma;

public class FichaAnteriorActivity extends AppCompatActivity {
    TextView Fecha;
    TextView Comentario;
    TextView Ejercicio;
    TextView Sintomas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_anterior);
        Ejercicio = (TextView)findViewById(R.id.txtFAEjercicio);
        Comentario= (TextView)findViewById(R.id.txtFAComentario);
        Fecha = (TextView)findViewById(R.id.txtFAfecha);
        Sintomas = (TextView)findViewById(R.id.txtFASintomas);


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

    }


    public void redirectCalendar(){
        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }
}