package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import java.util.Timer;

import AsyncTasks.BackupUploadTimer;
import AsyncTasks.IngredientesSync;
import AsyncTasks.SyncDatabase;
import AsyncTasks.UploadBackUp;
import Gestion.UsuariosGestion;
import Models.Fase;
import Models.Usuario;
import Service.UsuariosService;
import iGestion.iUsuariosGestion;
import iService.iUsuariosService;

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                iUsuariosService us = new UsuariosService();
                if(us.getUser(Email.getText().toString(), "") != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Ese email ya está registrado.", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else if(Validar()){
                        Usuario user = new Usuario();

                        user.setFase(new Fase(0,""));
                        user.setEmail(Email.getText().toString());
                        user.setPassword(Pass.getText().toString());
                        user.setNombre(Nombre.getText().toString());
                        user.setApellido(Apellido.getText().toString());
                        user.setPeso(Float.parseFloat(Peso.getText().toString()));
                        user.setAltura(Float.parseFloat(Altura.getText().toString()));
                        ug.save(user);
                        //Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                            if(us.insertUser(user)) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Usuario registrado.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        Intent i = new Intent(getApplicationContext(), UsuarioRegistrado.class);
                        startActivity(i);
                }
            }
        }).start();
        if(!Validar()){
            Toast.makeText(this, "Los campos ingresados no son válidos.", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean Validar(){
        if(Email.getText().toString().isEmpty() || Pass.getText().toString().isEmpty() ||
                Nombre.getText().toString().isEmpty() || Apellido.getText().toString().isEmpty()
                || Peso.getText().toString().isEmpty() || Altura.getText().toString().isEmpty()){
            //Toast.makeText(this, "No pueden haber campos vacíos.", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!ValidarFloat()){
            //Toast.makeText(this, "El número ingresado es inválido.", Toast.LENGTH_SHORT).show();
            return false;
        }else if(!isEmailValid(Email.getText().toString())) {
            //Toast.makeText(this, "El email ingresado es inválido.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean ValidarFloat(){
        try {
                Float peso = Float.parseFloat(Peso.getText().toString());
                Float altura = Float.parseFloat(Altura.getText().toString());
                if(peso > 645 || altura > 280) return  false;
                return  true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return  false;
            }
    }


}