package iService;

import Models.Usuario;

public interface iUsuariosService {
    public Usuario getUser(String mail, String pass);
    public Usuario getUser(String mail);
}
