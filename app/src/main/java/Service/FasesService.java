package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.DataDB;
import Models.Fase;
import iService.iFasesService;

public class FasesService implements iFasesService {

    private Connection con;

    public FasesService() {
        try{
            Class.forName(DataDB.driver);
            con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Fase> getAll() {
        if(con == null)
            return null;
        try{
            ArrayList<Fase> fases = new ArrayList<Fase>();
            Statement st = con.createStatement();
            String query = "SELECT nrofase, descripcion FROM Fases";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Fase fase  = new Fase();
                fase.setNroFase(rs.getInt("nrofase"));
                fase.setDescripcion(rs.getString("descripcion"));
                fases.add(fase);
            }
            return fases;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
