package AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
import iService.iIngredientesXFichaService;
import iService.iUsuariosService;

public class UploadBackUp extends AsyncTask<Void, Integer, Boolean> {

    private Usuario user;
    Context context;

    public UploadBackUp(Context _context) {
        context = _context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean res = true;
        iUsuariosGestion ug = new UsuariosGestion();
        user = ug.read();
        res = res && upUser();
        res = res && upIngredientes();
        res = res && upFichas();
        res = res && upIngredientesxFicha();
        return res;
    }

    private Boolean upFichas(){
        Boolean result = true;
        iFichasService fs = new FichasService();
        iFichasGestion fg = new FichasGestion();
        fs.deleteAll(user.getEmail());
        ArrayList<FichaDiaria> fichas = fg.getAll();
        if(fichas != null){
            for(FichaDiaria ficha:fichas){
                result = result && fs.save(ficha, user.getEmail()) ;
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
                result = result && is.save(ing, user.getEmail());
            }
            return result;
        }
        return  false;
    }

    private Boolean upIngredientesxFicha(){
        Boolean result = true;
        iIngredientesXFichaService ixfs = new IngredientesXFichaService();
        iIngredientesXFichaGestion ixfg = new IngredientesXFichaGestion();
        ixfs.deleteAll(user.getEmail());
        ArrayList<IngredientesXFicha> ixf = ixfg.getAll();
        if(ixf != null){
            for(IngredientesXFicha i:ixf){
                result = result && ixfs.save(i, user.getEmail());
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        String msg = aBoolean ? "Backup creado correctamente" : "Error al intentar crear el backup";
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }
}
