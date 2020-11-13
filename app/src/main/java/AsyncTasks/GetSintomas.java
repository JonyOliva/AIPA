package AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import Gestion.SintomasGestion;
import Models.Sintoma;
import Service.SintomasService;

public class GetSintomas extends AsyncTask <String, Void, String> {
    private Spinner spinSintomas;
    private Context context;
    private static ArrayList<Sintoma> listaSintomas = new ArrayList<Sintoma>();
    private static ArrayList<String> listaNombres = new ArrayList<String>();
    public static  String result;

    public GetSintomas(Spinner sp, Context ct)
    {
        spinSintomas = sp;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try{
            SintomasService sg = new SintomasService();
            listaSintomas = sg.getAll();
            for (Sintoma s:
                listaSintomas) {
                listaNombres.add(s.toString());
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            result = "Error de conexión";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        spinSintomas.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item,listaNombres));
    }
}
