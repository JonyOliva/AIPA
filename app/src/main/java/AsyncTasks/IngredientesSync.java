package AsyncTasks;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

import Connection.DataDB;
import Gestion.IngredientesGestion;
import Models.Ingrediente;
import Service.IngredientesService;
import iGestion.iIngredientesGestion;
import iService.iIngredientesService;

public class IngredientesSync extends AsyncTask<String, Integer, Boolean> {
    private SQLiteDatabase db;
    @Override
    protected Boolean doInBackground(String... email) {
        db = DataDB.getSqldb().getWritableDatabase();
        db.beginTransaction();

        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion(db);
        ig.deleteAll();
        ArrayList<Ingrediente> ingredientes;
        if(email.length == 0){
            ingredientes = is.getAllDefault();
        }else{
            ingredientes = is.getAllForUser(email[0]);
            if(ingredientes.isEmpty())
                ingredientes = is.getAllDefault();
        }
        if(ingredientes != null){
            for(Ingrediente ing:ingredientes){
                result = ig.save(ing);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        return result;
    }

    @Override
    protected void onCancelled() {
        db.endTransaction();
        super.onCancelled();
    }
}
