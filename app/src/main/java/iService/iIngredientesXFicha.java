package iService;

import java.util.ArrayList;

import Models.FichaDiaria;
import Models.IngredientesXFicha;

public interface iIngredientesXFicha {
    public ArrayList<IngredientesXFicha> getAllFromUser(String email);
    public Boolean deleteAll(String email);
    public Boolean save(IngredientesXFicha ixf, String email);
}
