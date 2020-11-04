package Gestion;

import android.content.ContentValues;

import Models.Fase;
import iGestion.iFasesGestion;

public class FasesGestion extends BaseGestion implements iFasesGestion {

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
    public Boolean deleteAll() {
        int res = db.delete("Fases", null, null);
        return res > 0;
    }

}
