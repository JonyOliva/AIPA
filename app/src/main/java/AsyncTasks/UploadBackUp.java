package AsyncTasks;

import android.os.AsyncTask;

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

    @Override
    protected Boolean doInBackground(Void... voids) {
        System.out.println("Backup iniciado");
        Boolean res = true;
        iUsuariosGestion ug = new UsuariosGestion();
        user = ug.read();
        res = res && upUser();
        res = delete();
        res = res && upIngredientes();
        res = res && upFichas();
        res = res && upIngredientesxFicha();
        System.out.println("Backup finalizado: " + res);
        return res;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        System.out.println("backup en background: " + values[0]);
    }

    private Boolean upFichas(){
        Boolean result = true;
        iFichasService fs = new FichasService();
        iFichasGestion fg = new FichasGestion();
        ArrayList<FichaDiaria> fichas = fg.getAll();
        publishProgress(3);
        if(fichas != null){
            for(FichaDiaria ficha:fichas){
                result = result && fs.save(ficha, user.getEmail()) ;
            }
        }
        return result;
    }

    private Boolean upUser(){
        iUsuariosService us = new UsuariosService();
        publishProgress(1);
        return us.updateUser(user);
    }

    private Boolean upIngredientes(){
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ArrayList<Ingrediente> ingredientes = ig.getAll();
        publishProgress(2);
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
        publishProgress(4);
        ArrayList<IngredientesXFicha> ixf = ixfg.getAll();
        if(ixf != null){
            for(IngredientesXFicha i:ixf){
                result = result && ixfs.save(i, user.getEmail());
            }
        }
        return result;
    }

    private Boolean delete(){
        try {
            iIngredientesXFichaService ixfs = new IngredientesXFichaService();
            ixfs.deleteAll(user.getEmail());
            iIngredientesService is = new IngredientesService();
            is.deleteAll(user.getEmail());
            iFichasService fs = new FichasService();
            fs.deleteAll(user.getEmail());
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }

    }

}
