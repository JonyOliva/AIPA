package Service;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.aipa.ui.MainActivity;

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


public class SyncBackUp extends AsyncTask<Void, Integer, Boolean> {

    private String email;
    private String pass;
    private Context context;

    public SyncBackUp(String mail, String passw, Context cont){
        email = mail;
        pass = passw;
        context = cont;
    }
    @Override
    protected Boolean doInBackground(Void... voids) {
        if(SyncUser(email)){
            SyncIngredientes(email);
        }

        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        return null;
    }

    private Boolean SyncFichas(){
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

    private Boolean SyncUser(String e){
        Boolean result = true;
        iUsuariosService us = new UsuariosService();
        iUsuariosGestion ug = new UsuariosGestion();
        ug.delete();
        Usuario user = us.getUser(email, pass);
        if(user == null) {
            return false;
        }
        result = ug.save(user);
        return result;
    }

    private Boolean SyncIngredientes(String e){
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ig.deleteAll();
        ArrayList<Ingrediente> ingredientes = is.getAllForUser(e);
        for(Ingrediente ing:ingredientes){
            result = ig.save(ing);
        }
        return result;
    }
}
