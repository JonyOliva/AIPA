package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.aipa.R;

import Dao.DataDB;
import Dao.SQLHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sql = new SQLHelper(this, getString(R.string.dbName), null, 1);
        DataDB.setSqldb(sql);

        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }
}