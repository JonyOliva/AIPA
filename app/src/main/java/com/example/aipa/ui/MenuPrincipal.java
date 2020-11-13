package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Gestion.FichasGestion;
import Gestion.UsuariosGestion;


public class MenuPrincipal extends AppCompatActivity {
String today;
TextView fase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        String f = "Te encuentras en Fase ";
        UsuariosGestion ug = new UsuariosGestion();
        f += Integer.toString(ug.read().getFase().getNroFase());
        fase = (TextView)findViewById(R.id.textView3);
        fase.setText(f);
        today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        verifySymptoms();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        String f = "Te encuentras en Fase ";
        UsuariosGestion ug = new UsuariosGestion();
        f += Integer.toString(ug.read().getFase().getNroFase());
        fase = (TextView)findViewById(R.id.textView3);
        fase.setText(f);
    }

    public void redirectConfig(View view){
        Intent i = new Intent(this, ConfiguracionActivity.class);
        startActivity(i);
    }

    public void redirectDailylog(View view){
        Intent i = new Intent(this, FichaDiariaActivity.class);
        startActivity(i);
    }

    public void redirectCalendar(View view){
        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }

   private void verifySymptoms(){
    FichasGestion fs= new FichasGestion();
    if (fs.getAnterior(today)!= null){
        Intent i = new Intent(getApplicationContext(), SintomasActivity.class);
        startActivity(i);
    }

}


}