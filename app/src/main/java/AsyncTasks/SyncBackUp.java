package AsyncTasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.aipa.ui.MainActivity;

import java.util.ArrayList;

import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Gestion.IngredientesXFichaGestion;
import Gestion.UsuariosGestion;
import Models.FichaDiaria;
import Models.Ingrediente;
import Models.IngredientesXFicha;
import Models.Usuario;
import Service.FichasService;
import Service.IngredientesService;
import Service.IngredientesXFichaService;
import Service.UsuariosService;
import iGestion.iFichasGestion;
import iGestion.iIngredientesGestion;
import iGestion.iIngredientesXFichaGestion;
import iGestion.iUsuariosGestion;
import iService.iFichasService;
import iService.iIngredientesService;
import iService.iIngredientesXFicha;
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
        if(SyncUser()){
            SyncFichas();
            SyncIngredientesXficha();
        }

        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
        return null;
    }

    private Boolean SyncFichas(){
        Boolean result = true;
        iFichasService fs = new FichasService();
        iFichasGestion fg = new FichasGestion();
        fg.deleteAll();
        ArrayList<FichaDiaria> fichas = fs.getAllFromUser(email);
        for(FichaDiaria f:fichas){
            result = fg.save(f);
        }
        return result;
    }

    private Boolean SyncUser(){
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

    private Boolean SyncIngredientesXficha(){
        Boolean result = true;
        iIngredientesXFicha is = new IngredientesXFichaService();
        iIngredientesXFichaGestion ig = new IngredientesXFichaGestion();
        ig.deleteAll();
        ArrayList<IngredientesXFicha> ingredientes = is.getAllFromUser(email);
        for(IngredientesXFicha ing:ingredientes){
            result = ig.save(ing);
        }
        return result;
    }
}
