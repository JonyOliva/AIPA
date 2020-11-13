package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Gestion.FichasGestion;
import Gestion.SintomasGestion;
import Models.FichaDiaria;
import Models.Sintoma;

public class FichaDiariaActivity extends AppCompatActivity {

    EditText Ejercicio;
    EditText Comentario;
    Button btnGuardar;
    Button btnPlus;
    Button btnMinus;
    Button btnAgregar;
    String today;
    TextView lblFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_diaria);

        Ejercicio = (EditText)findViewById(R.id.txtTiempoDeEjercicio);
        Comentario= (EditText)findViewById(R.id.txtAnotaciones);
        btnGuardar = (Button)findViewById(R.id.btnSave);
        btnMinus = (Button)findViewById(R.id.btnMinus);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BusquedaActivity.class);
                FichasGestion fGestion = new FichasGestion();
                Integer idFicha = fGestion.getNextId();
                i.putExtra("idFicha", idFicha);
                startActivity(i);
            }
        });
        today = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        lblFecha= (TextView)findViewById(R.id.lblDate);
        lblFecha.setText(today);

       // Toast.makeText(getApplicationContext(), today, Toast.LENGTH_SHORT).show();
    }


    public void guardarFicha(View view){

        FichasGestion fg = new FichasGestion();
        FichaDiaria ficha = fg.get(today);

       if (ficha == null){

        ficha = new FichaDiaria();
        ficha.setFecha(today);

        ficha.setTiempoEjercicio(Integer.parseInt(Ejercicio.getText().toString()));
        ficha.setComentario(Comentario.getText().toString());

         fg.save(ficha);
       }

       else {
           ficha.setTiempoEjercicio(Integer.parseInt(Ejercicio.getText().toString()));
           ficha.setComentario(Comentario.getText().toString());
       fg.update(ficha);

       }


    }

}