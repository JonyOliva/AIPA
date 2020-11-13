package Gestion;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import Models.Sintoma;
import iGestion.iSintomasGestion;

public class SintomasGestion extends BaseGestion implements iSintomasGestion {

    @Override
    public Boolean save(Sintoma sintoma) {
        ContentValues values = new ContentValues();
        values.put("idsintoma", sintoma.getIdSintoma());
        values.put("descripcion", sintoma.getDescripcion());
        values.put("modpuntaje", sintoma.getModificadorPuntaje());
        long result;
        result = db.insert("Sintomas",null, values); //si male sal insert() devuelve -1
        return (result != -1);
    }

    @Override
    public Boolean deleteAll() {
        int res = db.delete("Sintomas", null, null);
        return res > 0;
    }

    @Override
    public ArrayList<Sintoma> getAll() {
        ArrayList<Sintoma> sintomas = new ArrayList<Sintoma>();
        String query = "Select idsintoma, descripcion, modpuntaje from Sintomas";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        if(cursor.moveToFirst()){
            do {
                Sintoma s = new Sintoma();
                s.setIdSintoma(cursor.getInt(0));
                s.setDescripcion(cursor.getString(1));
                s.setModificadorPuntaje(cursor.getInt(2));

                sintomas.add(s);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return sintomas;
    }

}
