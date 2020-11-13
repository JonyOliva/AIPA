package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aipa.R;

import Gestion.IngredientesGestion;
import Models.Fase;
import Models.Ingrediente;

public class NuevoIngrediente extends AppCompatActivity {

    Spinner spFases;
    TextView txtNombrNuevoIngrediente;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_ingrediente);

        spFases = (Spinner) findViewById(R.id.spFases);
        txtNombrNuevoIngrediente = (TextView) findViewById(R.id.txtNombreNuevoIngrediente);
        btnGuardar = (Button) findViewById(R.id.btnGuardarNuevoIngrediente);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarIngrediente();
            }
        });
    }

    public void guardarIngrediente(){
        Ingrediente ingrediente = new Ingrediente();
        IngredientesGestion ingGestion = new IngredientesGestion();
        String nombre = txtNombrNuevoIngrediente.getText().toString();
        Fase fase = new Fase();
        fase.setDescripcion("");
        fase.setNroFase(Integer.parseInt(spFases.getSelectedItem().toString()));
        ingrediente.setFase(fase);
        ingrediente.setNombre(nombre);
        ingrediente.setPuntaje(0);

        if(nombre.length() == 0) {
            Toast.makeText(getApplicationContext(), "No puede quedar el nombre vacío.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ingGestion.existByName(nombre)) {
            Toast.makeText(getApplicationContext(), "El ingrediente ingresado ya existe.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(ingGestion.save(ingrediente)){
            txtNombrNuevoIngrediente.setText("");
            spFases.setSelection(0);
            Toast.makeText(getApplicationContext(),"Ingrediente guardado.", Toast.LENGTH_SHORT).show();
        }

    }


}