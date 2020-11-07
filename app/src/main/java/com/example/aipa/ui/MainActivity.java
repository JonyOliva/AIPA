package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import AsyncTasks.SyncDatabase;
import Connection.DataDB;
import Connection.SQLHelper;
import Gestion.UsuariosGestion;
import Models.Usuario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sql = new SQLHelper(this, getString(R.string.dbName), null, 1);
        DataDB.setSqldb(sql);

        UsuariosGestion ug = new UsuariosGestion();
        Usuario currentUser = ug.read();
        if(currentUser == null){
            SyncDatabase syncdb = new SyncDatabase();
            syncdb.execute();
            Intent i = new Intent(this, RegistrarUsuarioActivity.class);
            startActivity(i);
        }else{
            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);
        }

    }

}