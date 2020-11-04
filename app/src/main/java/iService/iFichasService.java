package iService;

import java.util.ArrayList;

import Models.FichaDiaria;

public interface iFichasService {
	public ArrayList<FichaDiaria> getAllFromUser(String email);
}
