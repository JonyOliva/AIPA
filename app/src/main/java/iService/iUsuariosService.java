package iService;

import Models.Usuario;

public interface iUsuariosService {
    public Usuario getUser(String mail, String pass);
    public boolean insertUser(Usuario user);
    public boolean updateUser(Usuario user);
}
