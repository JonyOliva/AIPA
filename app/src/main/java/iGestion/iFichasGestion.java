package iGestion;

import Models.FichaDiaria;

public interface iFichasGestion {
    public boolean save(FichaDiaria ficha);
    public FichaDiaria get(String date);
    public Boolean deleteAll();
}
