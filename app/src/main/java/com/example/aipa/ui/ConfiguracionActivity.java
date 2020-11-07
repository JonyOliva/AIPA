package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aipa.R;

import Dialogs.ConfirmReset;
import Gestion.UsuariosGestion;
import Models.Fase;
import Models.Usuario;
import AsyncTasks.UploadBackUp;

public class ConfiguracionActivity extends AppCompatActivity {

    //COMPONENTES DEL ACTIVITY
    Usuario user;
    EditText Nombre;
    EditText Apellido;
    EditText Peso;
    EditText Altura;
    TextView Fase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        UsuariosGestion ug = new UsuariosGestion();
        user = ug.read();
        Nombre = (EditText)findViewById(R.id.txtNombre);
        Nombre.setText(user.getNombre());
        Apellido = (EditText)findViewById(R.id.txtApellido);
        Apellido.setText(user.getApellido());
        Peso = (EditText)findViewById(R.id.txtPeso);
        Peso.setText(Float.toString(user.getPeso()));
        Altura = (EditText)findViewById(R.id.txtAltura);
        Altura.setText(Float.toString(user.getAltura()));
        Fase = (TextView)findViewById(R.id.txtFase);
        Fase.setText("Fase " + Integer.toString(user.getFase().getNroFase()));

    }

    public void ModificarUsuario(View view){
        UsuariosGestion ug = new UsuariosGestion();
        Fase fase = new Fase(user.getFase().getNroFase(), Fase.getText().toString());
        user.setNombre(Nombre.getText().toString());
        user.setApellido(Apellido.getText().toString());
        user.setPeso(Float.parseFloat(Peso.getText().toString()));
        user.setAltura(Float.parseFloat(Altura.getText().toString()));
        user.setFase(fase);
        if(ug.update(user)){
            Toast.makeText(this, "Usuario modificado correctamente", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error al modificar el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    public void AumentarFase(View view) {
        if (user.getFase().getNroFase() < 4) {
            user.getFase().setNroFase(user.getFase().getNroFase() + 1);
            Fase.setText("Fase " + Integer.toString(user.getFase().getNroFase()));
        }
    }
    public void DisminuirFase(View view){
        if(user.getFase().getNroFase()  > 0) {
            user.getFase().setNroFase(user.getFase().getNroFase()-1);
            Fase.setText("Fase " + Integer.toString(user.getFase().getNroFase()));
        }

    }

    public void RealizarBackUp(View view){
        UploadBackUp upBKP = new UploadBackUp(this);
        Toast.makeText(this, "Realizando BackUp", Toast.LENGTH_SHORT).show();
        upBKP.execute();
    }

    public void ReiniciarApp(View view){
        ConfirmReset cr = new ConfirmReset();
        cr.show(getSupportFragmentManager(), null);
    }
}