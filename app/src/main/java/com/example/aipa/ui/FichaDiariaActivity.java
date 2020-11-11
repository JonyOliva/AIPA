package com.example.aipa.ui;

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

        //Sintoma -1 seria indefinido
        Sintoma sint = new Sintoma();
        sint.setIdSintoma(-1);
        ficha.setSintoma(sint);
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