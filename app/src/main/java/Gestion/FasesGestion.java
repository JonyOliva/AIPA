package Gestion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import Conexion.DataDB;
import Models.Fase;
import iGestion.iFasesGestion;

public class FasesGestion implements iFasesGestion {
    private SQLiteDatabase db;
    public FasesGestion() {
        this.db = DataDB.getSqldb().getWritableDatabase();
    }

    @Override
    public Boolean save(Fase fase) {
        ContentValues values = new ContentValues();
        values.put("nrofase", fase.getNroFase());
        values.put("descripcion", fase.getDescripcion());
        long result;
        result = db.insert("Fases",null, values);
        return (result != -1);
    }

    @Override
    protected void finalize() throws Throwable {
        db.close();
        super.finalize();
    }
}
