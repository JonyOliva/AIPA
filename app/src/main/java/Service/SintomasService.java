package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Sintoma;
import iService.iSintomasService;

public class SintomasService extends BaseService implements iSintomasService {
    @Override
    public ArrayList<Sintoma> getAll() {
        openConn();
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
            con.close();
            return sintomas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
