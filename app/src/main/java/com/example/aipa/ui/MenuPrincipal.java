package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void redirectConfig(View view){
        Intent i = new Intent(this, ConfiguracionActivity.class);
        startActivity(i);
    }

    public void redirectDailylog(View view){
        Intent i = new Intent(this, FichaDiariaActivity.class);
        startActivity(i);
    }


}