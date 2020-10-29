package Gestion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.aipa.R;

import java.time.LocalDate;

import Dao.DataDB;
import Dao.SQLHelper;
import Models.FichaDiaria;
import iGestion.iGestionFichas;

public class GestionFichas implements iGestionFichas {
    SQLiteDatabase db;

    public GestionFichas() {
        db = DataDB.getSqldb().getWritableDatabase();
    }

    @Override
    public boolean save() {
        return false;
    }

    public FichaDiaria get(LocalDate date){
        FichaDiaria ficha = new FichaDiaria();
        String query = "select idficha, fecha, comentario, tiempoejercicio, idsintoma from FichasDiarias where fecha=':date'";
        query = query.replace(":date", date.toString());
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;

        return ficha;
    }
}
