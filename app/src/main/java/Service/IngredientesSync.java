package Service;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.IngredientesGestion;
import Models.Ingrediente;
import iGestion.iIngredientesGestion;
import iService.iIngredientesService;

public class IngredientesSync extends AsyncTask<String, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(String... email) {
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ArrayList<Ingrediente> ingredientes;
        if(email.length == 0){
            ingredientes = is.getAllDefault();
        }else{
            ingredientes = is.getAllForUser(email[0]);
        }
        for(Ingrediente ing:ingredientes){
            result = ig.save(ing);
        }
        return result;
    }
}
