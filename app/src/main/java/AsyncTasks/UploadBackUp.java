package AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Gestion.IngredientesXFichaGestion;
import Gestion.SintomasGestion;
import Gestion.UsuariosGestion;
import Models.FichaDiaria;
import Models.Ingrediente;
import Models.IngredientesXFicha;
import Models.Sintoma;
import Models.Usuario;
import Service.FichasService;
import Service.IngredientesService;
import Service.IngredientesXFichaService;
import Service.UsuariosService;
import iGestion.iFichasGestion;
import iGestion.iIngredientesGestion;
import iGestion.iIngredientesXFichaGestion;
import iGestion.iSintomasGestion;
import iGestion.iUsuariosGestion;
import iService.iFichasService;
import iService.iIngredientesService;
import iService.iIngredientesXFicha;
import iService.iSintomasService;
import iService.iUsuariosService;

public class UploadBackUp extends AsyncTask<Void, Integer, Boolean> {

    private Usuario user;

    @Override
    protected Boolean doInBackground(Void... voids) {
        iUsuariosGestion ug = new UsuariosGestion();
        user = ug.read();
        upUser();
        upIngredientes();
        upFichas();
        upIngredientesxFicha();
        return null;
    }

    private Boolean upFichas(){
        Boolean result = true;
        iFichasService fs = new FichasService();
        iFichasGestion fg = new FichasGestion();
        fs.deleteAll(user.getEmail());
        ArrayList<FichaDiaria> fichas = fg.getAll();
        if(fichas != null){
            for(FichaDiaria ficha:fichas){
                result = fs.save(ficha, user.getEmail());
            }
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
        if(ingredientes != null){
            for(Ingrediente ing:ingredientes){
                result = is.save(ing, user.getEmail());
            }
        }
        return result;
    }

    private Boolean upIngredientesxFicha(){
        Boolean result = true;
        iIngredientesXFicha ixfs = new IngredientesXFichaService();
        iIngredientesXFichaGestion ixfg = new IngredientesXFichaGestion();
        ixfs.deleteAll(user.getEmail());
        ArrayList<IngredientesXFicha> ixf = ixfg.getAll();
        if(ixf != null){
            for(IngredientesXFicha i:ixf){
                result = ixfs.save(i, user.getEmail());
            }
        }
        return result;
    }
}
