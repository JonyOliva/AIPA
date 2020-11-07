package iService;

import java.util.ArrayList;

import Models.FichaDiaria;
import Models.Ingrediente;

public interface iFichasService {
	public ArrayList<FichaDiaria> getAllFromUser(String email);
	public Boolean deleteAll(String email);
	public Boolean save(FichaDiaria fd, String email);
}
