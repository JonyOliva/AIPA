package Service;

import java.sql.ResultSet;
import java.sql.Statement;

import Models.Fase;
import Models.Usuario;
import iService.iUsuariosService;

public class UsuariosService extends BaseService implements iUsuariosService {

    @Override
    public Usuario getUser(String mail, String pass) {
        if(con == null)
            return null;
        try{
            Statement st = con.createStatement();
            String query = "SELECT * FROM Users where email=':mail'";
            if(pass.isEmpty()){
                query +=  "and password =':pass'";
                query = query.replace(":pass", pass);
            }
            query = query.replace(":mail", mail);
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                Usuario user = new Usuario();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setApellido(rs.getString("apellido"));
                user.setNombre(rs.getString("nombre"));
                user.setPeso(rs.getFloat("peso"));
                user.setAltura(rs.getFloat("altura"));
                Fase fase = new Fase();
                fase.setNroFase(rs.getInt("nrofase"));
                user.setFase(fase);

                return user;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
