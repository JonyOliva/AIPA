package Gestion;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import Models.Fase;
import Models.FichaDiaria;
import Models.Ingrediente;
import Models.Sintoma;
import iGestion.iFichasGestion;

public class FichasGestion extends BaseGestion implements iFichasGestion {

    @Override
    public boolean save(FichaDiaria ficha) {
        ContentValues values = new ContentValues();
        values.put("fecha", ficha.getFecha());
        values.put("comentario", ficha.getComentario());
        values.put("tiempoejercicio", ficha.getTiempoEjercicio());
        values.put("idsintoma", ficha.getSintoma().getIdSintoma());
        long result;
        result = db.insert("FichasDiarias",null, values);
        return (result != -1);
    }

    public FichaDiaria get(String date){ //ejem: 2020-30-10
        String query = "select idficha, fecha, comentario, tiempoejercicio, idsintoma  from FichasDiarias where fecha=':date'";
        query = query.replace(":date", date);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        FichaDiaria f = new FichaDiaria();
        if(cursor.moveToFirst()){
            f.setIdFicha(cursor.getInt(0));
            f.setFecha(cursor.getString(1));
            f.setComentario(cursor.getString(2));
            f.setTiempoEjercicio(cursor.getInt(3));
            f.setSintoma(new Sintoma(cursor.getInt(4), "", 0));
        }
        cursor.close();
        return f;
    }

    @Override
    public Boolean deleteAll() {
        int res = db.delete("FichasDiarias", null, null);
        return res > 0;
    }

    @Override
    public ArrayList<FichaDiaria> getAll() {
        ArrayList<FichaDiaria> fichas = new ArrayList<FichaDiaria>();
        String query = "Select idficha, fecha, comentario, tiempoejercicio, idsintoma from FichasDiarias";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        if(cursor.moveToFirst()){
            do {
                FichaDiaria fd = new FichaDiaria();
                fd.setIdFicha(cursor.getInt(0));
                fd.setFecha(cursor.getString(1));
                fd.setComentario(cursor.getString(2));
                fd.setTiempoEjercicio(cursor.getInt(3));
                fd.setSintoma(new Sintoma(cursor.getInt(4),"",0));
                fichas.add(fd);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return fichas;
    }
}
