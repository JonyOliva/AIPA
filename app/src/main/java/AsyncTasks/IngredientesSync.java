package AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.IngredientesGestion;
import Models.Ingrediente;
import Service.IngredientesService;
import iGestion.iIngredientesGestion;
import iService.iIngredientesService;

public class IngredientesSync extends AsyncTask<String, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(String... email) {
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ig.deleteAll();
        ArrayList<Ingrediente> ingredientes;
        if(email.length == 0){
            ingredientes = is.getAllDefault();
        }else{
            ingredientes = is.getAllForUser(email[0]);
        }
        if(ingredientes != null){
            for(Ingrediente ing:ingredientes){
                result = ig.save(ing);
            }
        }
        return result;
    }
}
