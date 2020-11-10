package iService;

import java.util.ArrayList;

import Models.IngredientesXFicha;

public interface iIngredientesXFichaService {
    public ArrayList<IngredientesXFicha> getAllFromUser(String email);
    public Boolean deleteAll(String email);
    public Boolean save(IngredientesXFicha ixf, String email);
}
