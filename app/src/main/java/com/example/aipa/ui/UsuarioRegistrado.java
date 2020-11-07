package com.example.aipa.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aipa.R;

import java.util.Timer;

import AsyncTasks.BackupUploadTimer;
import AsyncTasks.SyncBackUp;
import AsyncTasks.SyncDatabase;

public class UsuarioRegistrado extends AppCompatActivity {

    //COMPONENTES DEL ACTIVITY
    EditText Email;
    EditText Pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);

        Email = (EditText)findViewById(R.id.txtEmailR);
        Pass = (EditText)findViewById(R.id.txtPassR);
    }

    public void validarUsuario(View view){
            SyncDatabase syncdb = new SyncDatabase();
            syncdb.execute();
            SyncBackUp sb = new SyncBackUp(Email.getText().toString(), Pass.getText().toString(),
                    this);
            sb.execute();
            //Se programa la subida del backup
            final long horas = 72; //0.01 hs = 36 sec
            BackupUploadTimer backupUpload = new BackupUploadTimer();
            Timer timer = new Timer();
            long time = horas * 60 * 60 * 1000;
            timer.purge();
            timer.schedule(backupUpload, time/3, time);

            Toast.makeText(this, "Procesando...", Toast.LENGTH_SHORT).show();
    }
}