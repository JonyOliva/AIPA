package iGestion;

import java.util.ArrayList;

import Models.FichaDiaria;
import Models.Ingrediente;

public interface iFichasGestion {
    public boolean save(FichaDiaria ficha);
    public FichaDiaria get(String date);
    public Boolean deleteAll();
    public ArrayList<FichaDiaria> getAll();
}
