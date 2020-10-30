package Gestion;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;

import Coneccion.DataDB;
import Models.FichaDiaria;
import iGestion.iFichasGestion;

public class FichasGestion implements iFichasGestion {
    private SQLiteDatabase db;

    public FichasGestion() {
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
