package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Fase;
import iService.iFasesService;

public class FasesService extends BaseService implements iFasesService{
    @Override
    public ArrayList<Fase> getAll() {
        openConn();
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
            con.close();
            return fases;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
