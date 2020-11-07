package AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.FasesGestion;
import Gestion.SintomasGestion;
import Models.Fase;
import Models.Sintoma;
import Service.FasesService;
import Service.SintomasService;
import iGestion.iFasesGestion;
import iGestion.iSintomasGestion;
import iService.iFasesService;
import iService.iSintomasService;

public class SyncDatabase extends AsyncTask<Void, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        SyncSintomas();
        SyncFases();
        return null;
    }

    private Boolean SyncSintomas(){
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

    private Boolean SyncFases(){
        Boolean result = true;
        iFasesService fs = new FasesService();
        iFasesGestion fg = new FasesGestion();
        fg.deleteAll();
        ArrayList<Fase> fases = fs.getAll();
        for(Fase fase:fases){
            result = fg.save(fase);
        }
        return result;
    }

}
