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
import Service.SintomasService;

public class FichaDiariaActivity extends AppCompatActivity {

    EditText Ejercicio;
    EditText Comentario;
    Button btnGuardar;
    Button btnPlus;
    Button btnMinus;
    Button btnAgregar;
    String today;
    TextView lblFecha;
    FichaDiaria ficha;

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
        today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

lblFecha= (TextView)findViewById(R.id.lblDate);
fill();

        lblFecha= (TextView)findViewById(R.id.lblDate);
        lblFecha.setText(today);


    }

    private void fill(){
        lblFecha.setText(today);
        if (getFicha()){
            Ejercicio.setText(Integer.toString(ficha.getTiempoEjercicio()));
            Comentario.setText(ficha.getComentario());}
        else {
            Ejercicio.setText("0");

        }
    }

    public boolean getFicha(){
        try {
            FichasGestion fg = new FichasGestion();
            ficha = fg.get(today);
            if (ficha != null){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void guardarFicha(View view){

        FichasGestion fg = new FichasGestion();
        int tiempo= Integer.parseInt(Ejercicio.getText().toString());
        String comentarios = Comentario.getText().toString();

       if (ficha == null){

        ficha = new FichaDiaria();
        ficha.setFecha(today);
        if (tiempo>=0 && tiempo <=500){
            ficha.setTiempoEjercicio(tiempo);
            if (comentarios.isEmpty())
            {ficha.setComentario(""); }
        else{ficha.setComentario(comentarios);}


            Sintoma sint = new Sintoma();
            sint.setIdSintoma(0);
            ficha.setSintoma(sint);


            try {
                fg.save(ficha);
                Toast.makeText(getApplicationContext(), "Cambios Guardados", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Sucedió un error", Toast.LENGTH_SHORT).show();
            }
             }
        else {
            Toast.makeText(getApplicationContext(), "Tiempo de Ejercicio inválido", Toast.LENGTH_SHORT).show();
        }
               }

       else {
           if (tiempo>=0 && tiempo <=500){
           ficha.setTiempoEjercicio(tiempo);

           ficha.setComentario(Comentario.getText().toString());
               try {
                   fg.update(ficha);
                   Toast.makeText(getApplicationContext(), "Cambios Guardados", Toast.LENGTH_SHORT).show();
               }
               catch (Exception e){
                   e.printStackTrace();
                   Toast.makeText(getApplicationContext(), "Sucedió un error", Toast.LENGTH_SHORT).show();
               }

           }
           else {
               Toast.makeText(getApplicationContext(), "Tiempo de Ejercicio inválido", Toast.LENGTH_SHORT).show();
           }
       }


    }

    public void Minus(View view){
        int nro= Integer.parseInt(Ejercicio.getText().toString());
        if (nro>0){
            nro--;
            Ejercicio.setText(Integer.toString(nro));
        }
        else
        {Toast.makeText(getApplicationContext(), "No puede ingresar números negativos", Toast.LENGTH_SHORT).show();}
    }

    public void Plus (View view){
        int nro= Integer.parseInt(Ejercicio.getText().toString());
        if (nro<500){
            nro++;
            Ejercicio.setText(Integer.toString(nro));
        }
        else
        {Toast.makeText(getApplicationContext(), "Máximo 500", Toast.LENGTH_SHORT).show();}

    }

    public void redirectMain(View view){
        Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
        startActivity(i);
    }


}