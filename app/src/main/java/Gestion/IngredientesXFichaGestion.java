package Gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Models.IngredientesXFicha;
import iGestion.iIngredientesXFichaGestion;

public class IngredientesXFichaGestion extends BaseGestion implements iIngredientesXFichaGestion {

    public IngredientesXFichaGestion(SQLiteDatabase db) {
        super(db);
    }

    public IngredientesXFichaGestion() {
        super();
    }

    @Override
    public Boolean save(IngredientesXFicha ixf) {
        ContentValues values = new ContentValues();
        values.put("idficha", ixf.getIdficha());
        values.put("idingrediente", ixf.getIdingrediente());
        long result;
        result = db.insert("IngredientesXFicha",null, values);
        return (result != -1);
    }

    @Override
    public ArrayList<IngredientesXFicha> getAll() {
        ArrayList<IngredientesXFicha> ixf = new ArrayList<IngredientesXFicha>();
        String query = "Select idingrediente,idficha from IngredientesXFicha";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        if(cursor.moveToFirst()){
            do {
                IngredientesXFicha ing = new IngredientesXFicha();
                ing.setIdingrediente(cursor.getInt(0));
                ing.setIdficha(cursor.getInt(1));
                ixf.add(ing);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return ixf;
    }

    @Override
    public Boolean deleteAll() {
        int res = db.delete("IngredientesXFicha", null, null);
        return res > 0;
    }
}
