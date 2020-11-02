package iGestion;

import Models.Usuario;

public interface iUsuariosGestion {
    public Boolean save(Usuario usuario);
    public Usuario read();
}
