package Service;

import android.os.AsyncTask;

import java.util.ArrayList;

import Gestion.SintomasGestion;
import Models.Sintoma;
import iGestion.iSintomasGestion;
import iService.iSintomasService;

public class SyncDatabase extends AsyncTask<Void, Integer, Boolean> {
    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean result = true;
        iSintomasService ss = new SintomasService();
        iSintomasGestion gs = new SintomasGestion();
        ArrayList<Sintoma> sintomas = ss.getAll();
        for(Sintoma sin:sintomas){
           result = gs.save(sin);
        }
        if(!result) return result;



        return null;
    }
}
