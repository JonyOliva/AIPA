package Service;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.FasesGestion;
import Gestion.IngredientesGestion;
import Gestion.SintomasGestion;
import Models.Fase;
import Models.Ingrediente;
import Models.Sintoma;
import iGestion.iFasesGestion;
import iGestion.iIngredientesGestion;
import iGestion.iSintomasGestion;
import iService.iFasesService;
import iService.iIngredientesService;
import iService.iSintomasService;

public class SyncDatabase extends AsyncTask<Void, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        SyncSintomas();
        SyncFases();
        SyncIngredientes();
        return null;
    }

    private Boolean SyncSintomas(){
        Boolean result = true;
        iSintomasService ss = new SintomasService();
        iSintomasGestion gs = new SintomasGestion();
        ArrayList<Sintoma> sintomas = ss.getAll();
        for(Sintoma sin:sintomas){
            result = gs.save(sin);
        }
        return result;
    }

    private Boolean SyncFases(){
        Boolean result = true;
        iFasesService fs = new FasesService();
        iFasesGestion fg = new FasesGestion();
        ArrayList<Fase> fases = fs.getAll();
        for(Fase fase:fases){
            result = fg.save(fase);
        }
        return result;
    }

    private Boolean SyncIngredientes(){
        Boolean result = true;
        iIngredientesService is = new IngredientesService();
        iIngredientesGestion ig = new IngredientesGestion();
        ArrayList<Ingrediente> ingredientes = is.getAll();
        for(Ingrediente ing:ingredientes){
            result = ig.save(ing);
        }
        return result;
    }
}
