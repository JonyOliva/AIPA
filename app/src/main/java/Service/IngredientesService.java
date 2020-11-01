package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Connection.DataDB;
import Models.Fase;
import Models.Ingrediente;
import iService.iIngredientesService;

public class IngredientesService implements iIngredientesService {

    private Connection con;

    public IngredientesService() {
        try{
            Class.forName(DataDB.driver);
            con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Ingrediente> getAll() {
        if(con == null)
            return null;
        try{
            ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
            Statement st = con.createStatement();
            String query = "Select idingrediente, nombre, puntaje, fase, descripcion from Ingredientes inner join Fases on (nrofase=fase)";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Ingrediente ing  = new Ingrediente();
                ing.setIdIngrediente(rs.getInt("idingrediente"));
                ing.setNombre(rs.getString("nombre"));
                ing.setPuntaje(rs.getInt("puntaje"));
                ing.setFase(new Fase(rs.getInt("fase"), rs.getString("descripcion")));
                ingredientes.add(ing);
            }
            return ingredientes;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
