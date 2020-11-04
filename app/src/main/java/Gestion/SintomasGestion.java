package Gestion;

import android.content.ContentValues;

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

}
