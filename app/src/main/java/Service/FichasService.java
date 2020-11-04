package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import Models.FichaDiaria;
import iService.iFichasService;

public class FichasService extends BaseService implements iFichasService {
    @Override
    public ArrayList<FichaDiaria> getAllFromUser(String email) {
        if(con == null)
            return null;
        try{
            ArrayList<FichaDiaria> fichas = new ArrayList<FichaDiaria>();
            Statement st = con.createStatement();
            String query = "SELECT idficha, fecha, comentario, tiempoejercicio, idsintoma FROM FichasDiaria WHERE usuario = ':email'";
            query = query.replace(":email", email);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                FichaDiaria ficha  = new FichaDiaria();
                ficha.setIdFicha(rs.getInt("idficha"));
                ficha.setFecha( rs.getString("fecha"));

                fichas.add(ficha);
            }
            return fichas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
