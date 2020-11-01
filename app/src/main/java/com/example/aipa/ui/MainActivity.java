package com.example.aipa.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import Connection.DataDB;
import Connection.SQLHelper;
import Service.SyncDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLHelper sql = new SQLHelper(this, getString(R.string.dbName), null, 1);
        DataDB.setSqldb(sql);
        SyncDatabase syncdb = new SyncDatabase();
        syncdb.execute();

        Intent i = new Intent(this, CalendarioActivity.class);
        startActivity(i);
    }

}