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
import java.util.Date;
import java.util.Locale;

import AsyncTasks.GetSintomas;
import Gestion.FichasGestion;
import Models.FichaDiaria;
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

        Sintoma sintoma = new Sintoma();
        sintoma.setIdSintoma(spSintomas.getSelectedItemPosition()+1);
        fichaAnterior.setSintoma(sintoma);
        fg.update(fichaAnterior);
        redirectMain();
    }

    public void redirectMain(){
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }

}