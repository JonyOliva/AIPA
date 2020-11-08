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

    @Override
    public Boolean deleteAll(String email) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "DELETE FROM IngredientesXFicha WHERE usuario =':mail';";
            query = query.replace(":mail", email);
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean save(IngredientesXFicha ixf, String email) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "INSERT INTO `IngredientesXFicha` (`idingrediente`, `idficha`," +
                    " `usuario`) VALUES (";
            String ins = ":ing,:ficha,':user')";
            query += ins;
            query = query.replace(":user", email);
            query = query.replace(":ficha", Integer.toString(ixf.getIdficha()));
            query = query.replace(":ing", Integer.toString(ixf.getIdingrediente()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
