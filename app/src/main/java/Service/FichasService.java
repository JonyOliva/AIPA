package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.FichaDiaria;
import Models.Sintoma;
import iService.iFichasService;

public class FichasService extends BaseService implements iFichasService {
    @Override
    public ArrayList<FichaDiaria> getAllFromUser(String email) {
        openConn();
        if(con == null)
            return null;
        try{
            ArrayList<FichaDiaria> fichas = new ArrayList<FichaDiaria>();
            Statement st = con.createStatement();
            String query = "SELECT idficha, fecha, comentario, tiempoejercicio, idsintoma FROM FichasDiarias WHERE usuario = ':email'";
            query = query.replace(":email", email);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                FichaDiaria ficha  = new FichaDiaria();
                ficha.setIdFicha(rs.getInt("idficha"));
                ficha.setFecha(rs.getString("fecha"));
                ficha.setComentario(rs.getString("comentario"));
                ficha.setTiempoEjercicio(rs.getInt("tiempoejercicio"));
                ficha.setSintoma(new Sintoma(rs.getInt("idsintoma"), "", 0));
                fichas.add(ficha);
            }
            con.close();
            return fichas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
