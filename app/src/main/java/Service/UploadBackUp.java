package Service;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.IngredientesGestion;
import Gestion.SintomasGestion;
import Gestion.UsuariosGestion;
import Models.Ingrediente;
import Models.Sintoma;
import Models.Usuario;
import iGestion.iIngredientesGestion;
import iGestion.iSintomasGestion;
import iGestion.iUsuariosGestion;
import iService.iIngredientesService;
import iService.iSintomasService;
import iService.iUsuariosService;

public class UploadBackUp extends AsyncTask<Void, Integer, Boolean> {

    private Usuario user;
    private Context context;

    public UploadBackUp(Context context) {
        this.context = context;
        iUsuariosGestion ug = new UsuariosGestion();
        user = ug.read();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        if(upUser()) {
            //Toast.makeText(context, "BackUp realizado correctamente.", Toast.LENGTH_SHORT).show();
        }
        upIngredientes();
        return null;
    }

    private Boolean upFichas(){
        Boolean result = true;
        iSintomasService ss = new SintomasService();
        iSintomasGestion gs = new SintomasGestion();
        gs.deleteAll();
        ArrayList<Sintoma> sintomas = ss.getAll();
        for(Sintoma sin:sintomas){
            result = gs.save(sin);
        }
        return result;
    }

    private Boolean upUser(){
        iUsuariosService us = new UsuariosService();
        if(!us.insertUser(user)){
            us.updateUser(user);
        }
        return true;
    }

    private Boolean upIngredientes(){
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        is.deleteAll(user.getEmail());
        ArrayList<Ingrediente> ingredientes = ig.getAll();
        for(Ingrediente ing:ingredientes){
            result = is.save(ing, user.getEmail());
        }
        return result;
    }
}
