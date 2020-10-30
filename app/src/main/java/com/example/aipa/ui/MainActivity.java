package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.aipa.R;

import java.util.ArrayList;

import Coneccion.DataDB;
import Coneccion.SQLHelper;
import Gestion.SintomasGestion;
import Models.Sintoma;
import Service.SintomasService;
import iGestion.iSintomasGestion;
import iService.iSintomasService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sql = new SQLHelper(this, getString(R.string.dbName), null, 1);
        DataDB.setSqldb(sql);
        UpdateFromRemoteDatabase();

        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }

    void UpdateFromRemoteDatabase(){
        /*iSintomasService ss = new SintomasService();
        iSintomasGestion gs = new SintomasGestion();
        ArrayList<Sintoma> sintomas = iSintomasService ss = new SintomasService();
        for(int i; i < )*/

    }

}