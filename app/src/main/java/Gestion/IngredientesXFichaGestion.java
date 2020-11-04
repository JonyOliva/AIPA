package Gestion;

import android.content.ContentValues;

import Models.IngredientesXFicha;
import iGestion.iIngredientesXFichaGestion;

public class IngredientesXFichaGestion extends BaseGestion implements iIngredientesXFichaGestion {

    @Override
    public Boolean save(IngredientesXFicha ixf) {
        ContentValues values = new ContentValues();
        values.put("idficha", ixf.getIdficha());
        values.put("idingrediente", ixf.getIdingrediente());
        long result;
        result = db.insert("IngredientesXFicha",null, values);
        return (result != -1);
    }
}
