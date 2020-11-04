package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aipa.R;

import Connection.DataDB;
import Connection.SQLHelper;
import Gestion.UsuariosGestion;
import Models.Fase;
import Models.Usuario;
import Service.SyncDatabase;
import Service.UsuariosService;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    //COMPONENTES DEL ACTIVITY
    EditText Email;
    EditText Pass;
    EditText Nombre;
    EditText Apellido;
    EditText Peso;
    EditText Altura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        Email = (EditText)findViewById(R.id.txtEmail);
        Pass = (EditText)findViewById(R.id.txtPassword);
        Nombre = (EditText)findViewById(R.id.txtNombre);
        Apellido = (EditText)findViewById(R.id.txtApellido);
        Peso = (EditText)findViewById(R.id.txtPeso);
        Altura = (EditText)findViewById(R.id.txtAltura);
    }

    public void RedirUsuarioRegistrado(View view){
        Intent i = new Intent(this, UsuarioRegistrado.class);
        startActivity(i);
    }

    public void registrarUsuario(View view){
        UsuariosGestion ug = new UsuariosGestion();
        UsuariosService us = new UsuariosService();

        if(us.getUser(Email.getText().toString(), "") != null){
            Toast.makeText(this, "Ese email ya está registrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Validar()){
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
        }
    }

    public boolean Validar(){
        if(Email.getText().toString().isEmpty() || Pass.getText().toString().isEmpty() ||
                Nombre.getText().toString().isEmpty() || Apellido.getText().toString().isEmpty()
                || Peso.getText().toString().isEmpty() || Altura.getText().toString().isEmpty()){
            Toast.makeText(this, "No pueden haber campos vacíos.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!isEmailValid(Email.getText().toString())) {
            Toast.makeText(this, "El email ingresado es inválido.", Toast.LENGTH_SHORT).show();
            return false;
        }else
        {
            return true;
        }
    }
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}