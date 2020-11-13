package com.example.aipa.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aipa.R;

import java.util.Timer;

import AsyncTasks.BackupUploadTimer;
import AsyncTasks.IngredientesSync;
import AsyncTasks.SyncBackUp;
import AsyncTasks.SyncDatabase;

public class UsuarioRegistrado extends AppCompatActivity {

    //COMPONENTES DEL ACTIVITY
    EditText Email;
    EditText Pass;
    Button btnLogin;
    ProgressBar prCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_registrado);

        Email = (EditText)findViewById(R.id.txtEmailR);
        Pass = (EditText)findViewById(R.id.txtPassR);
        btnLogin = (Button)findViewById(R.id.btnConfirmar);
        prCircle = (ProgressBar)findViewById(R.id.progressBar);
    }

    public void validarUsuario(View view){
        if(Email.getText().toString().isEmpty() || Pass.getText().toString().isEmpty())
            return;
        btnLogin.setVisibility(View.GONE);
        prCircle.setVisibility(View.VISIBLE);
        SyncDatabase syncdb = new SyncDatabase();
        syncdb.execute();
        IngredientesSync is = new IngredientesSync();
        is.execute(Email.getText().toString());
        SyncBackUp sb = new SyncBackUp(Email.getText().toString(), Pass.getText().toString(),
                this);
        sb.execute();
        //Se programa la subida del backup
        final long horas = 72;
        BackupUploadTimer backupUpload = new BackupUploadTimer();
        Timer timer = new Timer();
        long time = horas * 60 * 60 * 1000;
        timer.schedule(backupUpload, time/3, time); //delay 20000 = 20 sec
    }
}