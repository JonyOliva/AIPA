package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Coneccion.DataDB;
import Models.Sintoma;
import iService.iSintomasService;

public class SintomasService implements iSintomasService {

    private Connection con;

    public SintomasService() {
        try{
            Class.forName(DataDB.driver);
            con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Sintoma> getAll() {
        if(con == null)
            return null;
        try{
            ArrayList<Sintoma> sintomas = new ArrayList<Sintoma>();
            Statement st = con.createStatement();
            String query = "SELECT idsintoma, descripcion, modpuntaje FROM Sintomas";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Sintoma sin  = new Sintoma();
                sin.setIdSintoma(rs.getInt("idsintoma"));
                sin.setDescripcion(rs.getString("descripcion"));
                sin.setModificadorPuntaje(rs.getInt("modpuntaje"));
                sintomas.add(sin);
            }
            return sintomas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
