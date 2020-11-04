package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aipa.R;

import Gestion.UsuariosGestion;
import Models.Fase;
import Models.Usuario;
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
        UsuariosService us = new UsuariosService();
        Usuario user = us.getUser(Email.getText().toString(), Pass.getText().toString());
        if( user != null){

        }

        /*if(Validar()){
            Usuario user = new Usuario();

            user.setFase(new Fase(0,""));
            user.setEmail(Email.getText().toString());
            user.setPassword(Pass.getText().toString());
            user.setNombre(Nombre.getText().toString());
            user.setApellido(Apellido.getText().toString());
            user.setPeso(Float.parseFloat(Peso.getText().toString()));
            user.setAltura(Float.parseFloat(Altura.getText().toString()));
            ug.save(user);
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();


            SyncDatabase syncdb = new SyncDatabase();
            syncdb.execute();

            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);
        }*/
    }
}