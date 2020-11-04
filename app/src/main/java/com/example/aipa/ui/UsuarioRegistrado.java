package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aipa.R;

import Gestion.UsuariosGestion;
import Models.Fase;
import Models.Usuario;
import Service.SyncBackUp;
import Service.SyncDatabase;
import Service.UsuariosService;

public class UsuarioRegistrado extends AppCompatActivity {

    //COMPONENTES DEL ACTIVITY
    EditText Email;
    EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);

        Email = (EditText)findViewById(R.id.txtEmailR);
        Pass = (EditText)findViewById(R.id.txtPassR);
    }

    public void validarUsuario(View view){
            UsuariosGestion ug = new UsuariosGestion();
            SyncDatabase syncdb = new SyncDatabase();
            syncdb.execute();
            SyncBackUp sb = new SyncBackUp(Email.getText().toString(), Pass.getText().toString(),
                    this);
            sb.execute();
            Toast.makeText(this, "Procesando...", Toast.LENGTH_SHORT).show();
    }
}