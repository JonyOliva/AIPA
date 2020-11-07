package Service;

import android.content.Context;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

import Models.Fase;
import Models.Usuario;
import iService.iUsuariosService;

public class UsuariosService extends BaseService implements iUsuariosService{

    public UsuariosService() {
    }


    @Override
    public Usuario getUser(String mail, String pass) {
        openConn();
        if(con == null)
            return null;
        try{
            Statement st = con.createStatement();
            String query = "SELECT * FROM Users where email=':mail'";
            if(!pass.isEmpty()){
                query +=  "and password =':pass'";
                query = query.replace(":pass", pass);
            }
            query = query.replace(":mail", mail);
            ResultSet rs = st.executeQuery(query);
            Usuario user = new Usuario();
            if (rs.next()) {
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setApellido(rs.getString("apellido"));
                user.setNombre(rs.getString("nombre"));
                user.setPeso(rs.getFloat("peso"));
                user.setAltura(rs.getFloat("altura"));
                Fase fase = new Fase();
                fase.setNroFase(rs.getInt("nrofase"));
                user.setFase(fase);

            }
            rs.close();
            con.close();
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertUser(Usuario user) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "INSERT INTO Users (`email`, `password`, `nombre`, `apellido`," +
                    " `peso`, `altura`, `nrofase`) VALUES (";
            String ins = "':email',':pass',':nom',':ap',:peso,:alt,:fase)";
            query += ins;
            query = query.replace(":email", user.getEmail());
            query = query.replace(":pass", user.getPassword());
            query = query.replace(":nom", user.getNombre());
            query = query.replace(":ap", user.getApellido());
            query = query.replace(":peso", Float.toString(user.getPeso()));
            query = query.replace(":alt", Float.toString(user.getAltura()));
            query = query.replace(":fase", Integer.toString(user.getFase().getNroFase()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(Usuario user) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "UPDATE Users SET Nombre = ':nom', Apellido = ':ap'," +
                    "Peso = :peso , Altura = :alt , nrofase = :fase " +
                    "WHERE email = ':email'";
            query = query.replace(":email", user.getEmail());
            query = query.replace(":nom", user.getNombre());
            query = query.replace(":ap", user.getApellido());
            query = query.replace(":peso", Float.toString(user.getPeso()));
            query = query.replace(":alt", Float.toString(user.getAltura()));
            query = query.replace(":fase", Integer.toString(user.getFase().getNroFase()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
