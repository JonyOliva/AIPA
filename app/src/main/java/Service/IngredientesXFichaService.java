package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.IngredientesXFicha;
import iService.iIngredientesXFicha;

public class IngredientesXFichaService extends BaseService implements iIngredientesXFicha {
    @Override
    public ArrayList<IngredientesXFicha> getAllFromUser(String email) {
        openConn();
        if(con == null)
            return null;
        try{
            ArrayList<IngredientesXFicha> ixfs = new ArrayList<IngredientesXFicha>();
            Statement st = con.createStatement();
            String query = "SELECT idficha, idingrediente FROM IngredientesXFicha WHERE usuario = ':email'";
            query = query.replace(":email", email);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                IngredientesXFicha ixf = new IngredientesXFicha();
                ixf.setIdficha(rs.getInt("idficha"));
                ixf.setIdingrediente(rs.getInt("idingrediente"));
                ixfs.add(ixf);
            }
            con.close();
            return ixfs;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
