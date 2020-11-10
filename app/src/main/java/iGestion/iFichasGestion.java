package iGestion;

import java.util.ArrayList;

import Models.FichaDiaria;

public interface iFichasGestion {
    public boolean save(FichaDiaria ficha);
    public FichaDiaria get(String date);
    public Boolean deleteAll();
    public ArrayList<FichaDiaria> getAll();
    public  Boolean update(FichaDiaria ficha);
    public FichaDiaria getAnterior();
}
