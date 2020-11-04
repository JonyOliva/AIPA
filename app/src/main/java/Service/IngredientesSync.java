package Service;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.IngredientesGestion;
import Models.Ingrediente;
import iGestion.iIngredientesGestion;
import iService.iIngredientesService;

public class IngredientesSync extends AsyncTask<Void, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ArrayList<Ingrediente> ingredientes = is.getAllDefault();
        for(Ingrediente ing:ingredientes){
            result = ig.save(ing);
        }
        return result;
    }
}
