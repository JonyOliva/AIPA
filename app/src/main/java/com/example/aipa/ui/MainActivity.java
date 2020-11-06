package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import Connection.DataDB;
import Connection.SQLHelper;
import Gestion.UsuariosGestion;
import Models.Usuario;
import Service.FichasService;
import Service.IngredientesSync;
import Service.SyncDatabase;
import Service.UsuariosService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sql = new SQLHelper(this, getString(R.string.dbName), null, 1);
        DataDB.setSqldb(sql);

        UsuariosGestion ug = new UsuariosGestion();
        Usuario currentUser = ug.read();
        IngredientesSync syncing = new IngredientesSync();
        if(currentUser == null){
            SyncDatabase syncdb = new SyncDatabase();
            syncdb.execute();
            syncing.execute();
            Intent i = new Intent(this, RegistrarUsuarioActivity.class);
            startActivity(i);
        }else{
            syncing.execute(currentUser.getEmail());
            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);
        }

    }

}