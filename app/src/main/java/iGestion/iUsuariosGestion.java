package iGestion;

import Models.Usuario;

public interface iUsuariosGestion {
    public Boolean save(Usuario usuario);
    public Boolean update(Usuario usuario);
    public Usuario read();
    public Boolean delete();
}
