package com.example.aipa.ui;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Gestion.SintomasGestion;
import Models.FichaDiaria;
import Models.Ingrediente;
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
    ListView lstIngredientes;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnHome) {
            Intent i = new Intent(this, MenuPrincipal.class);
            startActivity(i);
                  }
        return super.onOptionsItemSelected(item);
    }

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
        lstIngredientes = (ListView)findViewById(R.id.lstIngredientes);

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
            Comentario.setText(ficha.getComentario());
           fillIngredients();

        }
        else {
            Ejercicio.setText("0");
            createFicha();

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

    public void fillIngredients(){
        IngredientesGestion ig = new IngredientesGestion();
        ArrayList <Ingrediente> listaingredientes = ig.getIngredientesXFicha(ficha.getIdFicha());

        if (listaingredientes!=null){

       ArrayAdapter adapter = new ArrayAdapter<Ingrediente>(this, android.R.layout.simple_list_item_1,listaingredientes);
        lstIngredientes.setAdapter(adapter); }
    }

    public void createFicha(){
        if (ficha == null){
            FichasGestion fg = new FichasGestion();
            String comentarios = Comentario.getText().toString();
            int tiempo= Integer.parseInt(Ejercicio.getText().toString());
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
                    Toast.makeText(getApplicationContext(), "¡Bienvenido de nuevo!", Toast.LENGTH_SHORT).show();
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

}