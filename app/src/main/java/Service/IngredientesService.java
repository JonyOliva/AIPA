package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.Fase;
import Models.Ingrediente;
import iService.iIngredientesService;

public class IngredientesService extends BaseService implements iIngredientesService {
    @Override
    public ArrayList<Ingrediente> getAllDefault() {
        if(con == null)
            return null;
        try{
            ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            Statement st = con.createStatement();
            String query = "Select idingrediente, nombre, puntaje, fase, descripcion from Ingredientes inner join Fases on (nrofase=fase) where usuario='default'";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Ingrediente ing  = new Ingrediente();
                ing.setIdIngrediente(rs.getInt("idingrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setPuntaje(rs.getInt("puntaje"));
                ing.setFase(new Fase(rs.getInt("fase"), rs.getString("descripcion")));
                ingredientes.add(ing);
            }
            con.close();
            return ingredientes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Ingrediente> getAllForUser(String email) {
        if(con == null)
        return null;
        try{
            ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            Statement st = con.createStatement();
            String query = "Select idingrediente, nombre, puntaje, fase, descripcion from Ingredientes inner join Fases on (nrofase=fase)" +
                    "where usuario =':email'";
            query = query.replace(":email", email);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Ingrediente ing  = new Ingrediente();
                ing.setIdIngrediente(rs.getInt("idingrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setPuntaje(rs.getInt("puntaje"));
                ing.setFase(new Fase(rs.getInt("fase"), rs.getString("descripcion")));
                ingredientes.add(ing);
            }
            con.close();
            return ingredientes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean save(Ingrediente ing, String email) {
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "INSERT INTO `Ingredientes` (`nombre`, `puntaje`," +
                    " `fase`, `usuario`) VALUES (";
            String ins = "':nombre',:punt,:fase,':user')";
            query += ins;
            query = query.replace(":user", email);
            query = query.replace(":nombre", ing.getNombre());
            query = query.replace(":punt", Integer.toString(ing.getPuntaje()));
            query = query.replace(":fase", Integer.toString(ing.getFase().getNroFase()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteAll(String email) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "DELETE FROM Ingredientes WHERE usuario =':mail';";
            query = query.replace(":mail", email);
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
