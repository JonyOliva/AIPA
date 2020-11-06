package iService;

import java.util.ArrayList;

import Models.Ingrediente;

public interface iIngredientesService {
    public ArrayList<Ingrediente> getAllDefault();
    public ArrayList<Ingrediente> getAllForUser(String email);
    public Boolean save(Ingrediente ing, String email);
    public Boolean deleteAll(String email);
}
